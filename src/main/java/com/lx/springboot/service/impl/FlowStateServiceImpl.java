package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.mapper.FlowStateMapper;
import com.lx.springboot.service.FlowStateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 流转状态 服务实现类
 * </p>
 */
@Service
public class FlowStateServiceImpl extends ServiceImpl<FlowStateMapper, FlowState> implements FlowStateService {

    @Override
    public int addFlowState(FlowState flowState) {
        return baseMapper.addFlowState(flowState);
    }

    @Override
    public List<FlowState> getFlowStateByParam(FlowState flowState) {
        return baseMapper.getFlowStateByParam(flowState);
    }

    @Override
    public List<FlowState> queryFlowStateDetail(Integer applyId) {
        return baseMapper.queryFlowStateDetail(applyId);
    }

    @Override
    public int queryMaxFlowState(Integer applyId) {
        return baseMapper.queryMaxFlowState(applyId);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }
}
