package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.Enums.TypeEnum;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.FlowStateService;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = {"/queryFlowState"})
    @ResponseBody
    public List<FlowState> queryFlowState(@RequestBody FlowState flowState){
        List<FlowState> flowStateList=new ArrayList<FlowState>();
        try{
            AdvisoryNotice advisoryNotice =new AdvisoryNotice();
            log.info("FlowStateController.queryFlowState flowState:"+JSONObject.toJSONString(flowState));
            flowStateList=flowStateService.getFlowStateByParam(flowState);
        }catch(Exception e){
            log.error("FlowStateController.queryFlowState is error flowState:"+JSONObject.toJSONString(flowState),e);
        }
        return flowStateList;
    }
}
