package com.lx.springboot.mapper;

import com.lx.springboot.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    List<Customer> getAllCustomer();

    int addCustomer(Customer customer);

    int updateCustomer(Customer customer);

    List<Customer> getCustomerByParams(Map<String,String> param);
}
