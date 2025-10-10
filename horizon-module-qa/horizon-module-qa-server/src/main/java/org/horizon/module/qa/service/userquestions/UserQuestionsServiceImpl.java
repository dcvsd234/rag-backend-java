package org.horizon.module.qa.service.userquestions;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.horizon.framework.ai.core.client.ModelToolsClient;
import org.horizon.framework.ai.core.dto.embed.EmbedResp;
import org.horizon.framework.ai.core.dto.ragorchestrator.AskReq;
import org.horizon.framework.ai.core.dto.ragorchestrator.AskResp;
import org.horizon.framework.ai.core.dto.ragorchestrator.SourceItem;
import org.horizon.framework.ai.core.infer.impl.AskToInfer;
import org.horizon.framework.ai.core.infer.impl.EmbedToInfer;
import org.horizon.framework.tenant.core.context.TenantContextHolder;
import org.horizon.module.qa.controller.admin.usersessions.vo.UserSessionsSaveReqVO;
import org.horizon.module.qa.dal.dataobject.useranswers.UserAnswersDO;
import org.horizon.module.qa.dal.dataobject.useranswersources.UserAnswerSourcesDO;
import org.horizon.module.qa.dal.dataobject.usersessions.UserSessionsDO;
import org.horizon.module.qa.service.useranswers.UserAnswersService;
import org.horizon.module.qa.service.useranswersources.UserAnswerSourcesService;
import org.horizon.module.qa.service.usersessions.UserSessionsService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import org.horizon.module.qa.controller.admin.userquestions.vo.*;
import org.horizon.module.qa.dal.dataobject.userquestions.UserQuestionsDO;
import org.horizon.framework.common.pojo.PageResult;
import org.horizon.framework.common.pojo.PageParam;
import org.horizon.framework.common.util.object.BeanUtils;

import org.horizon.module.qa.dal.mapper.userquestions.UserQuestionsMapper;

import static org.horizon.framework.common.exception.util.ServiceExceptionUtil.exception;
import static org.horizon.framework.common.util.collection.CollectionUtils.convertList;
import static org.horizon.framework.common.util.collection.CollectionUtils.diffList;
import static org.horizon.module.qa.enums.ErrorCodeConstants.*;

/**
 * RAG 用户问题表（终端用户的每一次提问记录） Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@RequiredArgsConstructor
public class UserQuestionsServiceImpl implements UserQuestionsService {

    private final ModelToolsClient modelToolsClient;

    @Resource
    private UserQuestionsMapper userQuestionsMapper;

    @Resource
    @Lazy
    private final UserSessionsService userSessionsService;

    @Resource
    @Lazy
    private final UserAnswersService userAnswersService;

    @Resource
    @Lazy
    private final UserAnswerSourcesService userAnswerSourcesService;


    @Override
    public UserQuestionsFullRespVO createUserQuestions(UserQuestionsSaveReqVO createReqVO) {

        //租户ID
        Long tenantId = TenantContextHolder.getTenantId();
        String tenantIdStr= String.valueOf(tenantId);
        //用户原始提问
        String originalText = createReqVO.getOriginalText();
        //语言
        String langId = createReqVO.getLangId();
        //用户ID
        Long userId = createReqVO.getUserId();
        //匿名用户ID
        String anonUserId = createReqVO.getAnonUserId();

        UserSessionsDO userSessions =  userSessionsService.getUserSessionsId(anonUserId);

        if(ObjectUtil.isEmpty(userSessions)){
            userSessions = new UserSessionsDO();
        }


        String sessionId = String.valueOf(userSessions.getId());
        //2，拼接 rag_orchestrator 的请求参数

        AskReq askReq = new AskReq();
        //(1)用户提出的问题
        askReq.setQuestion(originalText);
        //(2)租户 ID
        askReq.setTenantId(String.valueOf(tenantId));
        //(3)语言 ID
        askReq.setLangId(langId);
        //(4)会话标识
        askReq.setSessionId(sessionId);

        //3, AI 应用调用
        AskResp askResp= modelToolsClient.generate(
                new AskToInfer().convert(askReq),
                AskResp.class
        );

        /** 用户输入的原始问题（母语） */

        Map<String, Object> additionalProperties = askResp.getAdditionalProperties();
        //original_question -> 永驻者办理手续
        String originalQuestion = String.valueOf(additionalProperties.get("original_question"));
        //String originalQuestion = "用户输入的原始问题（母语）";

        /** 用户输入统一翻译为日语后的内容 */
        String translatedInput =    String.valueOf(additionalProperties.get("translated_input"));
        //String translatedInput =   "用户输入统一翻译为日语后的内容";

        /** 模型在日语下生成的原始回答 */
        String generatedText =  String.valueOf(additionalProperties.get("generated_text"));
        //String generatedText = "原始回答";

        /** 翻译回用户母语的最终回答 */
        String finalText = String.valueOf(additionalProperties.get("final_text"));
        //String finalText = "用户母语的最终回答";


        //4,会话表
        //(1)租户ID
        userSessions.setTenantId(tenantId);
        //(2)用户ID
        userSessions.setUserId(null);
        //会话累计
        userSessions.setTokenUsed(10);
        //状态
        userSessions.setStatus("active");

        //(8)创建者ID
        userSessions.setCreator(tenantIdStr);
        //(9)更新者ID
        userSessions.setUpdater(tenantIdStr);

        //交互时间，每次更新
        //开始时间
        //结束时间   会话超时 /弃用，用户主动关闭
        LocalDateTime now = LocalDateTime.now();
        userSessions.setLastActivity(now);

        userSessions.setStartTime(now);

        if(StrUtil.isEmpty(sessionId)){
            userSessionsService.createUserSessions(userSessions);
        }else{
            userSessionsService.updateUserSessions(userSessions);
        }


        //5，用户问题表 插入
        UserQuestionsDO userQuestions = BeanUtils.toBean(createReqVO, UserQuestionsDO.class);
        //(1)租户ID
        userQuestions.setTenantId(tenantId);
        //(2)用户ID
        userQuestions.setUserId(null);
        //(3)匿名用户ID
        userQuestions.setAnonUserId(String.valueOf(anonUserId));
        //(4)会话ID
        userQuestions.setSessionId(String.valueOf(userSessions.getId()));
        //(5)语言
        userQuestions.setLangId(langId);
        //(6)用户提问
        userQuestions.setOriginalText(originalText);
        //(7)翻译文本
        userQuestions.setTranslatedText(translatedInput);
        //(8)创建者ID
        userQuestions.setCreator(tenantIdStr);
        //(9)更新者ID
        userQuestions.setUpdater(tenantIdStr);

        userQuestionsMapper.insertSelective(userQuestions);


        //6，用户回复表 插入

        UserAnswersDO userAnswers = new UserAnswersDO();
        //(1)租户ID
        userAnswers.setTenantId(tenantId);
        //(2)用户ID
        userAnswers.setUserId(userId);
        //(3)用户问题表ID
        userAnswers.setQuestionsId(userQuestions.getId());
        //(4)语言
        userAnswers.setLangId(langId);
        //(5)最终答案
        userAnswers.setAnswerText(generatedText);
        //(6)翻译文本
        userAnswers.setTranslatedText(finalText);
        //(7)模型
        userAnswers.setModelName("plamo");
        //(8)是否删除
        userAnswers.setDeleted(false);
        //(9)创建者ID
        userAnswers.setCreator(tenantIdStr);
        //(10)更新者ID
        userAnswers.setUpdater(tenantIdStr);

        userAnswersService.createUserAnswers(userAnswers);

        //6，回复来源表 插入
        List<SourceItem> sources = askResp.getSources();
        List<UserAnswerSourcesDO> userAnswerSourceList = new ArrayList<>();
        for (SourceItem source : sources) {

            Map<String, Object> metadata =source.getMetadata();

            UserAnswerSourcesDO userAnswerSources = new UserAnswerSourcesDO();
            //(1)用户问题表ID
            userAnswerSources.setQuestionsId(userQuestions.getId());
            //(2)来源类型source_type
            String source_type=String.valueOf(metadata.get("source_type"));
            userAnswerSources.setSourceType(source_type);
            //(3)来源ID
            Long source_id=Long.parseLong(metadata.get("source_id").toString());
            userAnswerSources.setSourceId(source_id);
            //(4)相似度

            Double rerankScore = metadata.get("rerank_score") == null
                    ? null
                    : Double.valueOf(metadata.get("rerank_score").toString());
            userAnswerSources.setScore(rerankScore);
            //(5)排名顺序
            Integer rank = metadata.get("rank") == null
                    ? null
                    : Integer.valueOf(metadata.get("rank").toString());
            userAnswerSources.setRank(rank);
            //(6)是否删除
            userAnswerSources.setDeleted(false);
            //(7)创建者ID
            userAnswerSources.setCreator(tenantIdStr);
            //(8)更新者ID
            userAnswerSources.setUpdater(tenantIdStr);

            userAnswerSourceList.add(userAnswerSources);
        }

        userAnswerSourcesService.batchCreateUserAnswerSources(userAnswerSourceList);

        UserQuestionsFullRespVO userQuestionsFullResp = new UserQuestionsFullRespVO();
        userQuestionsFullResp.setUserAnswer(userAnswers);
        userQuestionsFullResp.setUserAnswerSources(userAnswerSourceList);
        userQuestionsFullResp.setUserQuestion(userQuestions);

        // 返回
        return userQuestionsFullResp;
    }

    @Override
    public void updateUserQuestions(UserQuestionsSaveReqVO updateReqVO) {
        // 校验存在
        validateUserQuestionsExists(updateReqVO.getId());
        // 更新
        UserQuestionsDO updateObj = BeanUtils.toBean(updateReqVO, UserQuestionsDO.class);
        userQuestionsMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserQuestions(Long id) {
        // 校验存在
        validateUserQuestionsExists(id);
        // 删除
        userQuestionsMapper.deleteById(id);
    }

    @Override
    public void deleteUserQuestionsListByIds(List<Long> ids) {
        // 删除
        userQuestionsMapper.deleteByIds(ids);
    }


    private void validateUserQuestionsExists(Long id) {
        if (userQuestionsMapper.selectById(id) == null) {
            throw exception(USER_QUESTIONS_NOT_EXISTS);
        }
    }

    @Override
    public UserQuestionsDO getUserQuestions(Long id) {
        return userQuestionsMapper.selectById(id);
    }

    @Override
    public PageResult<UserQuestionsDO> getUserQuestionsPage(UserQuestionsPageReqVO pageReqVO) {
        return userQuestionsMapper.selectPage(pageReqVO);
    }

}