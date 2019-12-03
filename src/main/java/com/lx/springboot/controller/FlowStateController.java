package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.Enums.TypeEnum;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.service.AdvisoryNoticeService;
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
import java.util.List;

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
    private UserInfoService userInfoService;

    @RequestMapping(value = {"/queryFlowStateList"})
    @ResponseBody
    public List<FlowStateVo> queryFlowStateList(String alipayId){
        log.info("FlowStateController.queryFlowStateList alipayId:"+alipayId);
        List<FlowStateVo> flowStateVoList=new ArrayList<FlowStateVo>();
        try{
            UserInfo userInfo=new UserInfo();
            userInfo.setAlipayId(alipayId);
            List<UserInfo> userInfoList=userInfoService.getUserInfoByParam(userInfo);
            if(CollectionUtils.isNotEmpty(userInfoList)){
                for(UserInfo user:userInfoList){
                    FlowState flowState=new FlowState();
                    flowState.setUserInfoId(user.getId());
                    List<FlowState> flowStateList=flowStateService.getFlowStateByParam(flowState);
                    if(CollectionUtils.isNotEmpty(flowStateList)){
                        FlowState flow=flowStateList.get(0);
                        FlowStateVo flowStateVo=new FlowStateVo();
                        EnhanceBeanUtils.copyProperties(flow,flowStateVo);
                        flowStateVoList.add(flowStateVo);
                    }
                }
            }
        }catch(Exception e){
            log.error("FlowStateController.queryFlowStateList is error alipayId:"+alipayId,e);
        }
        return flowStateVoList;
    }

    @RequestMapping(value = {"/queryFlowStateDetail"})
    @ResponseBody
    public List<FlowStateVo> queryFlowStateDetail(Integer userInfoId) {
        log.info("FlowStateController.queryFlowStateDetail userInfoId:" + userInfoId);
        List<FlowStateVo> flowStateVoList = new ArrayList<FlowStateVo>();
        try {
            FlowState flowState = new FlowState();
            flowState.setUserInfoId(userInfoId);
            List<FlowState> flowStateList = flowStateService.getFlowStateByParam(flowState);
            if (CollectionUtils.isNotEmpty(flowStateList)) {
                for (FlowState flow : flowStateList) {
                    FlowStateVo flowStateVo = new FlowStateVo();
                    EnhanceBeanUtils.copyProperties(flow, flowStateVo);
                    flowStateVoList.add(flowStateVo);
                }
            }
        } catch (Exception e) {
            log.error("FlowStateController.queryFlowStateDetail is error userInfoId:" + userInfoId, e);
        }
        return flowStateVoList;
    }
}
