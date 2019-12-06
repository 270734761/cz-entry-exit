package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 流转状态 服务类
 * </p>
 */
public interface FlowStateService extends IService<FlowState> {

    int addFlowState(FlowState flowState);


    List<FlowState> getFlowStateByParam(FlowState flowState);

    List<FlowState> queryFlowStateDetail(Integer applyId);

    int  queryMaxFlowState(Integer applyId);

    int deleteById(Long id);
}
