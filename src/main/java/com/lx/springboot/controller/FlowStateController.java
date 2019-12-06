package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.Enums.TypeEnum;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.EntryExitApplyService;
import com.lx.springboot.service.FlowStateService;
import com.lx.springboot.service.UserInfoService;
import com.lx.springboot.utils.EnhanceBeanUtils;
import com.lx.springboot.vo.FlowStateVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 流转状态 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping(value = {"/flowState"})
public class FlowStateController {

    @Autowired
    private FlowStateService flowStateService;

    @Autowired
    private EntryExitApplyService entryExitApplyService;

    @RequestMapping(value = {"/queryFlowStateList"})
    @ResponseBody
    public List<FlowStateVo> queryFlowStateList(String alipayId) {
        log.info("FlowStateController.queryFlowStateList alipayId:" + alipayId);
        List<FlowStateVo> flowStateVoList = new ArrayList<FlowStateVo>();
        try {
            EntryExitApply entryExitApply=new EntryExitApply();
            entryExitApply.setAlipayId(alipayId);
            List<EntryExitApply> entryExitApplyList=entryExitApplyService.getEntryExitApplyByParam(entryExitApply);
            if (CollectionUtils.isNotEmpty(entryExitApplyList)) {
                for (EntryExitApply entryExitA : entryExitApplyList) {
                    FlowState flowState = new FlowState();
                    flowState.setApplyId(entryExitA.getId());
                    List<FlowState> flowStateList = flowStateService.getFlowStateByParam(flowState);
                    if (CollectionUtils.isNotEmpty(flowStateList)) {
                        FlowState flow = flowStateList.get(0);
                        FlowStateVo flowStateVo = new FlowStateVo();
                        EnhanceBeanUtils.copyProperties(flow, flowStateVo);
                        flowStateVoList.add(flowStateVo);
                    }
                }
            }
        } catch (Exception e) {
            log.error("FlowStateController.queryFlowStateList is error alipayId:" + alipayId, e);
        }
        return flowStateVoList;
    }

    @RequestMapping(value = {"/queryFlowStateDetail"})
    @ResponseBody
    public Map<String, Object> queryFlowStateDetail(Integer applyId) {
        log.info("FlowStateController.queryFlowStateDetail applyId:" + applyId);
        Map<String, Object> map = new HashMap<String, Object>();
        List<FlowStateVo> flowStateVoList = new ArrayList<FlowStateVo>();
        int flowStateTep = flowStateService.queryMaxFlowState(applyId);
        try {
            List<FlowState> flowStateList = flowStateService.queryFlowStateDetail(applyId);
            if (CollectionUtils.isNotEmpty(flowStateList)) {
                flowStateList.forEach(flow->{
                    FlowStateVo flowStateVo = new FlowStateVo();
                    EnhanceBeanUtils.copyProperties(flow, flowStateVo);
                    flowStateVoList.add(flowStateVo);
                });
            }
        } catch (Exception e) {
            log.error("FlowStateController.queryFlowStateDetail is error applyId:" + applyId, e);
        }
        map.put("flowStateVoList", flowStateVoList);
        map.put("flowStateTep", flowStateTep);
        return map;
    }
}
