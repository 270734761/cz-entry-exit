package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.Customer;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.vo.CustomerVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 */
public interface CustomerService extends IService<Customer> {

    int addCustomer(CustomerVo customerVo);

    int updateCustomer(CustomerVo customerVo);


    List<CustomerVo> getAllCustomer();

    List<CustomerVo> getCustomerByParams(Map<String,String> param);

     int addOrupdateCustomer(EntryExitApply entryExitApply);
}
