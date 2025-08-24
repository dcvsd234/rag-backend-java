package org.horizon.module.bpm.framework.flowable.core.candidate.strategy.dept;

import org.horizon.framework.common.pojo.CommonResult;
import org.horizon.framework.test.core.ut.BaseMockitoUnitTest;
import org.horizon.module.bpm.service.task.BpmProcessInstanceService;
import org.horizon.module.system.api.dept.DeptApi;
import org.horizon.module.system.api.dept.dto.DeptRespDTO;
import org.horizon.module.system.api.user.AdminUserApi;
import org.horizon.module.system.api.user.dto.AdminUserRespDTO;
import org.assertj.core.util.Sets;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Set;

import static org.horizon.framework.common.pojo.CommonResult.success;
import static org.horizon.framework.test.core.util.RandomUtils.randomPojo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BpmTaskCandidateStartUserDeptLeaderMultiStrategyTest extends BaseMockitoUnitTest {

    @InjectMocks
    private BpmTaskCandidateStartUserDeptLeaderMultiStrategy strategy;

    @Mock
    private BpmProcessInstanceService processInstanceService;

    @Mock
    private AdminUserApi adminUserApi;
    @Mock
    private DeptApi deptApi;

    @Test
    public void testCalculateUsersByTask() {
        // 准备参数
        String param = "2";
        // mock 方法（获得流程发起人）
        Long startUserId = 1L;
        ProcessInstance processInstance = mock(ProcessInstance.class);
        DelegateExecution execution = mock(DelegateExecution.class);
        when(processInstanceService.getProcessInstance(eq(execution.getProcessInstanceId()))).thenReturn(processInstance);
        when(processInstance.getStartUserId()).thenReturn(startUserId.toString());
        // mock 方法（获取发起人的 multi 部门负责人）
        mockGetStartUserDept(startUserId);

        // 调用
        Set<Long> userIds = strategy.calculateUsersByTask(execution, param);
        // 断言
        assertEquals(Sets.newLinkedHashSet(11L, 1001L), userIds);
    }

    @Test
    public void testCalculateUsersByActivity() {
        // 准备参数
        String param = "2";
        // mock 方法
        Long startUserId = 1L;
        mockGetStartUserDept(startUserId);

        // 调用
        Set<Long> userIds = strategy.calculateUsersByActivity(null, null, param,
                startUserId, null, null);
        // 断言
        assertEquals(Sets.newLinkedHashSet(11L, 1001L), userIds);
    }

    private void mockGetStartUserDept(Long startUserId) {
        when(adminUserApi.getUser(eq(startUserId))).thenReturn(
                success(randomPojo(AdminUserRespDTO.class, o -> o.setId(startUserId).setDeptId(10L))));
        when(deptApi.getDept(any())).thenAnswer((Answer< CommonResult<DeptRespDTO>>) invocationOnMock -> {
            Long deptId = invocationOnMock.getArgument(0);
            return success(randomPojo(DeptRespDTO.class, o -> o.setId(deptId).setParentId(deptId * 100).setLeaderUserId(deptId + 1)));
        });
    }

}
