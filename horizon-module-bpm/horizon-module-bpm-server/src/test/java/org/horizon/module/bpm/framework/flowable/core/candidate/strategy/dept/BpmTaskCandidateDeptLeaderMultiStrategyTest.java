package org.horizon.module.bpm.framework.flowable.core.candidate.strategy.dept;

import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.test.core.ut.BaseMockitoUnitTest;
import org.horizon.module.system.api.dept.DeptApi;
import org.horizon.module.system.api.dept.dto.DeptRespDTO;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Set;

import static org.horizon.framework.common.pojo.CommonResult.success;
import static org.horizon.framework.test.core.util.RandomUtils.randomPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BpmTaskCandidateDeptLeaderMultiStrategyTest extends BaseMockitoUnitTest {

    @InjectMocks
    private BpmTaskCandidateDeptLeaderMultiStrategy strategy;

    @Mock
    private DeptApi deptApi;

    @Test
    public void testCalculateUsers() {
        // 准备参数
        String param = "10,20|2";
        // mock 方法
        when(deptApi.getDept(any())).thenAnswer((Answer< CommonResult<DeptRespDTO>>) invocationOnMock -> {
            Long deptId = invocationOnMock.getArgument(0);
            return success(randomPojo(DeptRespDTO.class, o -> o.setId(deptId).setParentId(deptId * 100).setLeaderUserId(deptId + 1)));
        });

        // 调用
        Set<Long> userIds = strategy.calculateUsers(param);
        // 断言结果
        assertEquals(Sets.newLinkedHashSet(11L, 1001L, 21L, 2001L), userIds);
    }

}
