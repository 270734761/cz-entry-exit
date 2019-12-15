package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.springboot.Enums.TypeEnum;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.ServiceNetwork;
import com.lx.springboot.query.AdvisoryNoticeQuery;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.ServiceNetworkService;
import com.lx.springboot.utils.EnhanceBeanUtils;
import com.lx.springboot.utils.TableUtils;
import com.lx.springboot.vo.AdvisoryNoticeVo;
import com.lx.springboot.vo.ServiceNetworkVo;
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
@RequestMapping(value = {"/advisoryNotice"})
public class AdvisoryNoticeController {

    @Autowired
    private AdvisoryNoticeService advisoryNoticeService;
    @Autowired
    private ServiceNetworkService serviceNetworkService;

    @RequestMapping(value = {"/addNotice"})
    @ResponseBody
    public String addAdvisoryNotice(@RequestBody AdvisoryNotice advisoryNotice){
        try{
            log.info("AdvisoryNoticeController.addAdvisoryNotice advisoryNotice:"+JSONObject.toJSONString(advisoryNotice));
            Integer i = advisoryNoticeService.addAdvisoryNotice(advisoryNotice);
        }catch(Exception e){
            log.error("UserInfoController addUserInfo is error",e);
        }
        return "success";
    }

    @RequestMapping(value = {"/getAllAdvisoryNotice"})
    @ResponseBody
    public String getAllAdvisoryNotice(){
        List<AdvisoryNotice> advisoryNoticeList=null;
        try{
            log.info("AdvisoryNoticeController.addAdvisoryNotice start");
            advisoryNoticeList = advisoryNoticeService.getAllAdvisoryNotice();
        }catch(Exception e){
            log.error("AdvisoryNoticeController.addAdvisoryNotice is error",e);
        }
        return JSONObject.toJSONString(advisoryNoticeList);
    }

    @RequestMapping(value = {"/getAdvisoryNoticeByParam"})
    @ResponseBody
    public List<AdvisoryNoticeVo> getAdvisoryNoticeByParam(){
        List<AdvisoryNoticeVo> advisoryNoticeVoList=new ArrayList<AdvisoryNoticeVo>();
        try{
            log.info("AdvisoryNoticeController.getAdvisoryNoticeByParam start");
            AdvisoryNotice advisoryNotice =new AdvisoryNotice();
            advisoryNotice.setStart(0);
            advisoryNotice.setEnd(2);
            advisoryNotice.setNoticeType(TypeEnum.NOTICE.getModelType());
            List<AdvisoryNotice> advisoryNoticeList = advisoryNoticeService.getAdvisoryNoticeByParam(advisoryNotice);
            if(CollectionUtils.isNotEmpty(advisoryNoticeList)){
                for(AdvisoryNotice notice:advisoryNoticeList){
                    AdvisoryNoticeVo advisoryNoticeVo=new AdvisoryNoticeVo();
                    EnhanceBeanUtils.copyProperties(notice,advisoryNoticeVo);
                    advisoryNoticeVoList.add(advisoryNoticeVo);
                }
            }
        }catch(Exception e){
            log.error("AdvisoryNoticeController.getAdvisoryNoticeByParam is error",e);
        }
        log.info("AdvisoryNoticeController.getAdvisoryNoticeByParam is success");
        return advisoryNoticeVoList;
    }

    @RequestMapping(value = {"/getDetailById"})
    @ResponseBody
    public String getDetailById(Integer id){
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

    @RequestMapping(value = {"/deleteById"})
    @ResponseBody
    public String deleteById(Long id){
        try{
            log.info("AdvisoryNoticeController deleteById start id:"+id);
            Integer i = advisoryNoticeService.deleteById(id);
        }catch(Exception e){
            log.error("AdvisoryNoticeController.deleteById is error id:"+id,e);
        }
        return "success";
    }

    @RequestMapping(value = {"/getReadingProtocol"})
    @ResponseBody
    public String getReadingProtocol(){
        String readingProtocal="";
        JSONObject json=new JSONObject();
        AdvisoryNotice advisoryNotice =new AdvisoryNotice();
        try{
            log.info("AdvisoryNoticeController.getReadingProtocol start");
            advisoryNotice.setStart(0);
            advisoryNotice.setEnd(2);
            advisoryNotice.setNoticeType(TypeEnum.READINGPROTOCAL.getModelType());
            readingProtocal = advisoryNoticeService.getReadingProtocalByParam(advisoryNotice);
            json.put("readingProtocal",readingProtocal);
        }catch(Exception e){
            log.error("AdvisoryNoticeController.getReadingProtocol is error advisoryNotice:"+JSONObject.toJSONString(advisoryNotice),e);
        }
        log.info("AdvisoryNoticeController.getReadingProtocol is success");
        return json.toJSONString();
    }
    @RequestMapping(value = {"/getAdvisoryNoticeList"})
    @ResponseBody
    public TableUtils<List<AdvisoryNoticeVo>> getAdvisoryNoticeList(@RequestBody AdvisoryNoticeQuery advisoryNoticeQuery){
        List<AdvisoryNotice> advisoryNoticeList=null;
        List<AdvisoryNoticeVo> AdvisoryNoticeVoList=new ArrayList<AdvisoryNoticeVo>();
        try{
            log.info("AdvisoryNoticeController.getAdvisoryNoticeList start advisoryNoticeQuery:"+JSONObject.toJSONString(advisoryNoticeQuery));
            AdvisoryNotice advisoryNotice=new AdvisoryNotice();
            advisoryNotice.setNoticeType(advisoryNoticeQuery.getType());
            //获取第1页，10条内容，默认查询总数count
            Page page = PageHelper.startPage(advisoryNoticeQuery.getPage(), advisoryNoticeQuery.getLimit());
            //紧跟着的第一个select方法会被分页
            advisoryNoticeList = advisoryNoticeService.getAdvisoryNoticePageByparam(advisoryNotice);
            if(CollectionUtils.isNotEmpty(advisoryNoticeList)){
                for(AdvisoryNotice notice:advisoryNoticeList){
                    AdvisoryNoticeVo advisoryNoticeVo=new AdvisoryNoticeVo();
                    EnhanceBeanUtils.copyProperties(notice,advisoryNoticeVo);
                    AdvisoryNoticeVoList.add(advisoryNoticeVo);
                }
            }
            //用PageInfo对结果进行包装
            PageInfo pageInfo = new PageInfo(page.getResult());
            return new TableUtils(pageInfo.getTotal(), AdvisoryNoticeVoList);
        }catch(Exception e){
            log.error("AdvisoryNoticeController.addAdvisoryNotice is error advisoryNoticeQuery:"+JSONObject.toJSONString(advisoryNoticeQuery),e);
            return new TableUtils();
        }
    }

    @RequestMapping(value = {"/getAllServiceNetwork"})
    @ResponseBody
    public List<ServiceNetworkVo> getAllServiceNetwork(){
        List<ServiceNetworkVo> list=new ArrayList<ServiceNetworkVo>();
        try{
            log.info("AdvisoryNoticeController.getAllServiceNetwork start");
            List<ServiceNetwork> ServiceNetworkList=serviceNetworkService.getAllServiceNetwork();
            if(CollectionUtils.isNotEmpty(ServiceNetworkList)){
                for(ServiceNetwork serviceNetwork:ServiceNetworkList){
                    ServiceNetworkVo serviceNetworkVo=new ServiceNetworkVo();
                    EnhanceBeanUtils.copyProperties(serviceNetwork,serviceNetworkVo);
                    list.add(serviceNetworkVo);
                }
            }
        }catch(Exception e){
            log.error("AdvisoryNoticeController.getAllServiceNetwork is error",e);
        }
        log.info("AdvisoryNoticeController.getAllServiceNetwork is success");
        return list;
    }

}
