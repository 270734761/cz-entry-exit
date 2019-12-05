package com.lx.springboot.service.impl;

import com.lx.springboot.entity.Customer;
import com.lx.springboot.mapper.CustomerMapper;
import com.lx.springboot.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.utils.EnhanceBeanUtils;
import com.lx.springboot.vo.CustomerVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 */
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
}
