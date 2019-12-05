package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.Customer;
import com.lx.springboot.entity.ServiceNetwork;

import java.util.List;
import java.util.Map;

public interface ServiceNetworkMapper extends BaseMapper<ServiceNetwork> {

    List<ServiceNetwork> getAllServiceNetwork();

}
