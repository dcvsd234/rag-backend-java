package org.horizon.module.dataset.controller.app;

import org.horizon.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.horizon.framework.common.pojo.CommonResult.success;

@Tag(name = "用户 App - 资讯获得")
@RestController
@RequestMapping("/app/data/test")
@Validated
public class AppDataTestController {

    // 这个构造方法，只是方便大家，验证 Controller 有生效
    public AppDataTestController() {
        System.out.println(getClass() + "生效啦！！！");
    }

    @GetMapping("/get")
    @Operation(summary = "获取 test 信息")
    public CommonResult<String> get() {
        return success("true");
    }

}
