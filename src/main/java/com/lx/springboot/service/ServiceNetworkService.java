package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.Customer;
import com.lx.springboot.entity.ServiceNetwork;
import com.lx.springboot.vo.CustomerVo;
import com.lx.springboot.vo.ServiceNetworkVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 */
public interface ServiceNetworkService extends IService<ServiceNetwork> {

    List<ServiceNetwork> getAllServiceNetwork();

}
