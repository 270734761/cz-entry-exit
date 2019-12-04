package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.Enums.TypeEnum;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 咨询公告 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping(value = {"/guidWork"})
public class GuidWorkController {

    @Autowired
    private AdvisoryNoticeService advisoryNoticeService;

    @RequestMapping(value = {"/queryGuid"})
    @ResponseBody
    public List<AdvisoryNotice> queryGuid(){
        List<AdvisoryNotice> noticeList=new ArrayList<AdvisoryNotice>();
        try{
            AdvisoryNotice advisoryNotice =new AdvisoryNotice();
            log.info("AdvisoryNoticeController queryNotice advisoryNotice:"+JSONObject.toJSONString(advisoryNotice));
            advisoryNotice.setStart(0);
            advisoryNotice.setEnd(10);
            advisoryNotice.setType(TypeEnum.GUID.getModelType());
            noticeList=advisoryNoticeService.getAdvisoryNoticeByParam(advisoryNotice);
        }catch(Exception e){
            log.error("AdvisoryNoticeController queryNotice is error",e);
        }
        return noticeList;
    }

    @RequestMapping(value = {"/getDetailById"})
    @ResponseBody
    public String getDetailById(Integer id){
        log.info("AdvisoryNoticeController.getDetailById id:"+id);
        JSONObject json=new JSONObject();
        String contentDetail="";
        try{
            contentDetail = advisoryNoticeService.getDetailById(id);
            json.put("contentDetail",contentDetail);
        }catch(Exception e){
            log.error("AdvisoryNoticeController.getDetailById is error id:"+id,e);
        }
        log.info("AdvisoryNoticeController.getDetailById is success");
        return json.toJSONString();
    }



}
