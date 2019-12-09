package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.FlowState;

import java.util.List;

/**
 * <p>
 * 流转状态 Mapper 接口
 * </p>
 */
public interface FlowStateMapper extends BaseMapper<FlowState> {

    int addFlowState(FlowState flowState);

    List<FlowState> getFlowStateByParam(FlowState flowState);

    List<FlowState> queryFlowStateDetail(Integer applyId);

    int queryMaxFlowState(Integer applyId);

    int deleteById(Long id);
}
