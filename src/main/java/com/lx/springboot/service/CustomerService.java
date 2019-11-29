package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.Customer;
import com.lx.springboot.entity.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 */
public interface CustomerService extends IService<Customer> {

    int addCustomer(Customer customer);

    List<Customer> getAllCustomer();

    List<Customer> getCustomerByParams(Map<String,String> param);
}
