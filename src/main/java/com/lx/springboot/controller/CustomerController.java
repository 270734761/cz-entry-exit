package com.lx.springboot.controller;


import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.service.CustomerService;
import com.lx.springboot.vo.CustomerVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping
    @RequiresPermissions("customer:list")
    public String getAllCustomer(Model model){
        List<CustomerVo> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers);
        return "customer";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("customer:delete")
    public String deleteCustomer(Integer id){
        customerService.removeById(id);
        return "redirect:/customer";
    }

    @RequestMapping(value = {"/getCustomerByParam"})
    @ResponseBody
    public CustomerVo getCustomerByParam(String alipayId){
        CustomerVo customerVo=new CustomerVo();
        try{
            log.info("CustomerController.getCustomerByParam start alipayId:"+ alipayId);
            
            Map<String,String> param=new HashMap<String,String>();
            param.put("alipayId",alipayId);
            List<CustomerVo> customerVoList = customerService.getCustomerByParams(param);
            log.info("CustomerController.getCustomerByParam start alipayId:"+ alipayId+",customerVoList:"+ JSONObject.toJSONString(customerVoList));
            customerVo=customerVoList.get(0);
        }catch(Exception e){
            log.error("CustomerController.getCustomerByParam is error alipayId:"+alipayId,e);
        }
        log.info("UserInfoController getCustomerByParam is success");
        return customerVo;
    }

    @RequestMapping(value = {"/updateCustomerByParam"})
    @ResponseBody
    public String updateCustomerByParam(@RequestBody CustomerVo customerVo){
        JSONObject json=new JSONObject();
        try{
            log.info("CustomerController.updateCustomerByParam start customerVo:"+ JSONObject.toJSONString(customerVo));
            int i =customerService.updateCustomer(customerVo);
            json.put("id",i);
        }catch(Exception e){
            json.put("success",false);
            log.error("CustomerController.updateCustomerByParam is error customerVo:"+ JSONObject.toJSONString(customerVo),e);
        }
        json.put("success",true);
        log.info("UserInfoController updateCustomerByParam is success");
        return json.toJSONString();
    }

}
