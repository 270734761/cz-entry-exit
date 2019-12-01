package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 流转状态 Mapper 接口
 * </p>
 */
public interface FlowStateMapper extends BaseMapper<FlowState> {

    int addFlowState(FlowState flowState);

    List<FlowState> getFlowStateByParam(FlowState flowState);

    int deleteById(Long id);
}
