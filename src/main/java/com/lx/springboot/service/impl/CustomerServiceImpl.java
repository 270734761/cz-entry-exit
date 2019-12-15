package com.lx.springboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lx.springboot.entity.Customer;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.mapper.CustomerMapper;
import com.lx.springboot.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.utils.EnhanceBeanUtils;
import com.lx.springboot.vo.CustomerVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 */
@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public int addCustomer(CustomerVo customerVo) {
        Customer customer=new Customer();
        EnhanceBeanUtils.copyProperties(customerVo,customer);
        return baseMapper.addCustomer(customer);
    }

    @Override
    public int updateCustomer(CustomerVo customerVo) {
        Customer customer=new Customer();
        EnhanceBeanUtils.copyProperties(customerVo,customer);
        return baseMapper.updateCustomer(customer);
    }

    @Override
    public List<CustomerVo> getAllCustomer() {
        List<CustomerVo> list=new ArrayList<CustomerVo>();
        List<Customer> customerList=baseMapper.getAllCustomer();
        if(CollectionUtils.isNotEmpty(customerList)){
            for(Customer customer:customerList){
                CustomerVo customerVo=new CustomerVo();
                EnhanceBeanUtils.copyProperties(customer,customerVo);
                list.add(customerVo);
            }
        }
        return list;
    }

    @Override
    public List<CustomerVo> getCustomerByParams(Map<String,String> param) {
        List<CustomerVo> list=new ArrayList<CustomerVo>();
        List<Customer> customerList=baseMapper.getCustomerByParams(param);
        if(CollectionUtils.isNotEmpty(customerList)){
            for(Customer customer:customerList){
                CustomerVo customerVo=new CustomerVo();
                EnhanceBeanUtils.copyProperties(customer,customerVo);
                list.add(customerVo);
            }
        }
        return list;
    }

    @Override
    public int addOrupdateCustomer(EntryExitApply entryExitApply){
        try{
            Map<String,String> param=new HashMap<String,String>();
            param.put("alipayId",entryExitApply.getAlipayId());
            List<Customer> customerList= baseMapper.getCustomerByParams(param);
            if(CollectionUtils.isNotEmpty(customerList)){
                log.info("CustomerServiceImpl.addOrupdateCustomer update entryExitApply:"+ JSONObject.toJSONString(entryExitApply));
                Customer customer=new Customer();
                customer.setCustomerName(entryExitApply.getNamef()+entryExitApply.getNamel());
                String namePinyin=entryExitApply.getNamepinf()+" "+entryExitApply.getNamepinl();
                if(StringUtils.isNotEmpty(namePinyin)){
                    customer.setNamePinyin(namePinyin.toUpperCase());
                }
                customer.setContactName(entryExitApply.getContactName());
                customer.setContactPhone(entryExitApply.getContactPhone());
                customer.setMailAddress(entryExitApply.getConsigneeAdress());
                customer.setAlipayId(entryExitApply.getAlipayId());
                return baseMapper.updateCustomer(customer);
            }else{
                log.info("CustomerServiceImpl.addOrupdateCustomer add entryExitApply:"+ JSONObject.toJSONString(entryExitApply));
                String sseionId = UUID.randomUUID().toString();
                Customer customer=new Customer();
                customer.setAlipayId(entryExitApply.getAlipayId());
                customer.setCzSessionId(sseionId);
                customer.setStatus("1");
                customer.setCustomerName(entryExitApply.getNamef()+entryExitApply.getNamel());
                String namePinyin=entryExitApply.getNamepinf()+" "+entryExitApply.getNamepinl();
                if(StringUtils.isNotEmpty(namePinyin)){
                    customer.setNamePinyin(namePinyin.toUpperCase());
                }
                customer.setContactName(entryExitApply.getContactName());
                customer.setContactPhone(entryExitApply.getContactPhone());
                customer.setMailAddress(entryExitApply.getConsigneeAdress());
                customer.setMailAddressNote("邮寄地址使用您的淘宝地址，为保证数据一致性，请前往淘宝网修改");
                baseMapper.addCustomer(customer);
            }
        }catch(Exception e){
            log.error("CustomerServiceImpl.addOrupdateCustomer is error entryExitApply:"+ JSONObject.toJSONString(entryExitApply),e);
        }
        return 1;
    }
}
