//package org.horizon.module.dataset.convert.qa;
//
//import org.horizon.framework.common.pojo.PageResult;
//import org.horizon.framework.common.util.json.JsonUtils;
//import org.horizon.module.dataset.api.vo.qa.PreviewQAResp;
//import org.horizon.module.dataset.dal.dataobject.qa.QaCardDO;
//import org.mapstruct.*;
//import org.mapstruct.factory.Mappers;
//
//import java.util.Collections;
//import java.util.List;
//
///**
// * QA Convert（简洁稳定版）
// * - DO <-> VO 基础映射
// * - evidencesJson(String) <-> evidences(List<String>) 通过 @Named 方法转换
// */
//@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//public interface QaConvert {
//
//    QaConvert INSTANCE = Mappers.getMapper(QaConvert.class);
//
//    // ============ DO -> VO ============
//    @Mappings({
//            @Mapping(source = "evidencesJson", target = "evidences", qualifiedByName = "jsonToList")
//    })
//    PreviewQAResp convert(QaCardDO bean);
//
//    List<PreviewQAResp> convertList(List<QaCardDO> list);
//
//    default PageResult<PreviewQAResp> convertPage(PageResult<QaCardDO> page) {
//        return new PageResult<>(convertList(page.getList()), page.getTotal());
//    }
//
//    // ============ 构建 DO（用于保存） ============
//    @Mappings({
//            @Mapping(target = "id", ignore = true),
//            @Mapping(source = "evidences", target = "evidencesJson", qualifiedByName = "listToJson")
//            // 如果 QaCardDO 里还有 createdAt/updatedAt 等字段，这里可以统一 ignore 或在 Service 里设置
//            // @Mapping(target = "createdAt", ignore = true),
//            // @Mapping(target = "updatedAt", ignore = true)
//    })
//    QaCardDO buildDo(String tenantId,
//                     String question,
//                     String prompt,
//                     List<String> evidences,
//                     String answer,
//                     Long docId);
//
//    // ============ 类型转换辅助 ============
//    @Named("jsonToList")
//    default List<String> jsonToList(String json) {
//        if (json == null || json.isBlank()) return Collections.emptyList();
//        return JsonUtils.parseArray(json, String.class);
//    }
//
//    @Named("listToJson")
//    default String listToJson(List<String> list) {
//        return JsonUtils.toJsonString(list);
//    }
//}