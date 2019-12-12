package com.lx.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.Enums.ApplyTypeEnum;
import com.lx.springboot.Enums.FlowStateEnum;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.mapper.FlowStateMapper;
import com.lx.springboot.service.FlowStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 流转状态 服务实现类
 * </p>
 */
@Slf4j
@Service
public class FlowStateServiceImpl extends ServiceImpl<FlowStateMapper, FlowState> implements FlowStateService {

    @Override
    public int addFlowState(FlowState flowState) {
        return baseMapper.addFlowState(flowState);
    }

    @Override
    public int insertFlowState(EntryExitApply entryExitApply) {
        try{
            int i=1;
            for (FlowStateEnum value : FlowStateEnum.values()) {
                FlowState flowState=new FlowState();
                flowState.setApplyId(entryExitApply.getId());
                flowState.setAlipayId(entryExitApply.getAlipayId());
                flowState.setTitle(entryExitApply.getIdType());
                flowState.setFlowStateDesc(value.getDesc());
                flowState.setFlowState(i);
                if("submitted".equals(value.getModelType())){
                    flowState.setIsValid(1);
                }else{
                    flowState.setIsValid(0);
                }
                i++;
                baseMapper.addFlowState(flowState);
            }
        }catch(Exception e){
          log.error("FlowStateServiceImpl.insertFlowState is error entryExitApply:"+ JSONObject.toJSONString(entryExitApply),e);
        }
        return 1;
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
