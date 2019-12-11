package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.service.EntryExitApplyService;
import com.lx.springboot.utils.EnhanceBeanUtils;
import com.lx.springboot.vo.EntryExitApplyVo;
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
 * 申请人 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping(value = {"/entryExitApply"})
public class EntryExitApplyController {


    @Autowired
    private EntryExitApplyService entryExitApplyService;

    @RequestMapping(value = {"/addEntryExitApply"})
    @ResponseBody
    public String addEntryExitApply(@RequestBody EntryExitApply entryExitApply){
        JSONObject json=new JSONObject();
        try{
            log.info("EntryExitApplyController.addEntryExitApply entryExitApply:"+JSONObject.toJSONString(entryExitApply));
            Integer i = entryExitApplyService.addEntryExitApply(entryExitApply);
            json.put("id",i);
        }catch(Exception e){
            json.put("success",false);
            log.error("UserInfoController addUserInfo is error",e);
        }
        log.info("EntryExitApplyController.addEntryExitApply is success entryExitApply:"+JSONObject.toJSONString(entryExitApply));
        json.put("success",true);
        return json.toJSONString();
    }

    @RequestMapping(value = {"/getAllEntryExitApply"})
    @ResponseBody
    public String getAllEntryExitApply(){
        List<EntryExitApplyVo> entryExitApplyVoList=new ArrayList<EntryExitApplyVo>();
        try{
            log.info("EntryExitApplyController.getAllEntryExitApply start");
            List<EntryExitApply> entryExitApplyList = entryExitApplyService.getAllEntryExitApply();
            if(CollectionUtils.isNotEmpty(entryExitApplyList)){
                for(EntryExitApply entryExitApply:entryExitApplyList){
                    EntryExitApplyVo entryExitApplyVo=new EntryExitApplyVo();
                    EnhanceBeanUtils.copyProperties(entryExitApply,entryExitApplyVo);
                    entryExitApplyVoList.add(entryExitApplyVo);
                }
            }
        }catch(Exception e){
            log.error("EntryExitApplyController.getAllEntryExitApply is error",e);
        }
        return JSONObject.toJSONString(entryExitApplyVoList);
    }

    @RequestMapping(value = {"/getEntryExitApplyByParam"})
    @ResponseBody
    public List<EntryExitApplyVo> getEntryExitApplyByParam(@RequestBody EntryExitApply entryExitApply){
        List<EntryExitApplyVo> entryExitApplyVoList=new ArrayList<EntryExitApplyVo>();
        try{
            log.info("EntryExitApplyController.getEntryExitApplyByParam start entryExitApply:"+JSONObject.toJSONString(entryExitApply));
            List<EntryExitApply> entryExitApplyList = entryExitApplyService.getEntryExitApplyByParam(entryExitApply);
            if(CollectionUtils.isNotEmpty(entryExitApplyList)){
                for(EntryExitApply entryExitA:entryExitApplyList){
                    EntryExitApplyVo entryExitApplyVo=new EntryExitApplyVo();
                    EnhanceBeanUtils.copyProperties(entryExitA,entryExitApplyVo);
                    entryExitApplyVoList.add(entryExitApplyVo);
                }
            }
        }catch(Exception e){
            log.error("EntryExitApplyController.getEntryExitApplyByParam is error entryExitApply:"+JSONObject.toJSONString(entryExitApply),e);
        }
        log.info("EntryExitApplyController.getEntryExitApplyByParam is success");
        return entryExitApplyVoList;
    }

    @RequestMapping(value = {"/deleteById"})
    @ResponseBody
    public String deleteById(Long id){
        try{
            log.info("EntryExitApplyController deleteById start id:"+id);
            Integer i = entryExitApplyService.deleteById(id);
        }catch(Exception e){
            log.error("EntryExitApplyController deleteById is error id:"+id,e);
        }
        return "success";
    }

}
