package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.entity.Customer;
import com.lx.springboot.entity.ServiceNetwork;
import com.lx.springboot.mapper.CustomerMapper;
import com.lx.springboot.mapper.ServiceNetworkMapper;
import com.lx.springboot.service.CustomerService;
import com.lx.springboot.service.ServiceNetworkService;
import com.lx.springboot.utils.EnhanceBeanUtils;
import com.lx.springboot.vo.CustomerVo;
import com.lx.springboot.vo.ServiceNetworkVo;
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
public class ServiceNetworkServiceImpl extends ServiceImpl<ServiceNetworkMapper, ServiceNetwork> implements ServiceNetworkService {


    @Override
    public List<ServiceNetwork> getAllServiceNetwork() {
       return baseMapper.getAllServiceNetwork();
    }
}
