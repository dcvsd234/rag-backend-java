package org.horizon.module.knowledge.convert.faqembeddings;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import org.horizon.framework.mybatis.core.dataobject.BaseDO;

@Data                 // 生成 getter/setter/toString/equals/hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FaqEmbeddingIndex {

    public FaqEmbeddingIndex(Long faqId,int qaRole){
        this.faqId=faqId;
        this.qaRole=qaRole;
    }
    private Long faqId;   // 对应 FAQ 主表 ID
    private int qaRole;   // 0=Question, 1=Answer
    private String text;  // 待嵌入的文本
}
