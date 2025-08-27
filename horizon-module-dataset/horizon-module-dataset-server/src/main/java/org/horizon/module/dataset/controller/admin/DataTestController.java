package org.horizon.module.dataset.controller.admin;

import org.horizon.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.horizon.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 数据提交")
@RestController
@RequestMapping("/admin/data/test1")
@Validated
public class DataTestController {

    // 这个构造方法，只是方便大家，验证 Controller 有生效
    public DataTestController() {
        System.out.println(getClass() + "生效啦！！！");
    }

    @GetMapping("/get")
    @Operation(summary = "获取 test 信息")
    public CommonResult<String> get() {
        return success("true");
    }

}
