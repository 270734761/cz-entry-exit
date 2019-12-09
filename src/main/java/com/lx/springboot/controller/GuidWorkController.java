package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.Enums.TypeEnum;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.utils.EnhanceBeanUtils;
import com.lx.springboot.vo.AdvisoryNoticeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
    public List<AdvisoryNoticeVo> queryGuid(){
        List<AdvisoryNoticeVo> advisoryNoticeVoList=new ArrayList<AdvisoryNoticeVo>();
        try{
            AdvisoryNotice advisoryNotice =new AdvisoryNotice();
            advisoryNotice.setStart(0);
            advisoryNotice.setEnd(7);
            advisoryNotice.setType(TypeEnum.GUID.getModelType());
            log.info("AdvisoryNoticeController queryNotice advisoryNotice:"+JSONObject.toJSONString(advisoryNotice));
            List<AdvisoryNotice> noticeList=advisoryNoticeService.getAdvisoryNoticeByParam(advisoryNotice);
            if(CollectionUtils.isNotEmpty(noticeList)){
                for(AdvisoryNotice notice:noticeList){
                    AdvisoryNoticeVo advisoryNoticeVo=new AdvisoryNoticeVo();
                    EnhanceBeanUtils.copyProperties(notice,advisoryNoticeVo);
                    advisoryNoticeVoList.add(advisoryNoticeVo);
                }
            }
        }catch(Exception e){
            log.error("AdvisoryNoticeController queryNotice is error",e);
        }
        return advisoryNoticeVoList;
    }

    @RequestMapping(value = {"/getDetailById"})
    @ResponseBody
    public String getDetailById(Integer id){
        log.info("AdvisoryNoticeController.getDetailById id:"+id);
        JSONObject json=new JSONObject();
        String detailUrl="";
        try{
            detailUrl = advisoryNoticeService.getDetailById(id);
            json.put("detailUrl",detailUrl);
        }catch(Exception e){
            log.error("AdvisoryNoticeController.getDetailById is error id:"+id,e);
        }
        log.info("AdvisoryNoticeController.getDetailById is success");
        return json.toJSONString();
    }



}
