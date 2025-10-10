package org.horizon.module.qa.controller.admin.userquestions.vo;

import lombok.Data;
import org.horizon.module.qa.dal.dataobject.useranswers.UserAnswersDO;
import org.horizon.module.qa.dal.dataobject.useranswersources.UserAnswerSourcesDO;
import org.horizon.module.qa.dal.dataobject.userquestions.UserQuestionsDO;

import java.util.List;

@Data
public class UserQuestionsFullRespVO {

    /** 用户问题 */
    private UserQuestionsDO userQuestion;

    /** 用户答案 */
    private UserAnswersDO userAnswer;

    /** 答案来源 */
    private List<UserAnswerSourcesDO> userAnswerSources;
}
