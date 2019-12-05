package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.Enums.ApplyTypeEnum;
import com.lx.springboot.Enums.FlowStateEnum;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.mapper.UserInfoMapper;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.CustomerService;
import com.lx.springboot.service.FlowStateService;
import com.lx.springboot.service.UserInfoService;
import com.lx.springboot.vo.CustomerVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 申请人 服务实现类
 * </p>
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private FlowStateService flowStateService;
    @Autowired
    private CustomerService customerService;

    @Override
    public int addUserInfo(UserInfo userInfo) {
        userInfo.setFlowState(FlowStateEnum.SUBMITTED.getDesc());//已提交
        userInfo.setIsValid(1);
        baseMapper.addUserInfo(userInfo);
        int userInfoId=userInfo.getId();
        //创建办件流转信息
        addFlowState(userInfo);
        //更新会员信息
        updateCustomer(userInfo);
        return userInfoId;
    }

    public int updateCustomer(UserInfo userInfo){
        Map<String,String> param=new HashMap<String,String>();
        param.put("alipayId",userInfo.getAlipayId());
        List<CustomerVo> customerList= customerService.getCustomerByParams(param);
        if(CollectionUtils.isNotEmpty(customerList)){
            CustomerVo customerVo=new CustomerVo();
            customerVo.setCustomerName(userInfo.getNamef()+userInfo.getNamel());
            String namePinyin=userInfo.getNamepinf()+" "+userInfo.getNamepinl();
            if(StringUtils.isNotEmpty(namePinyin)){
                customerVo.setNamePinyin(namePinyin.toUpperCase());
            }
            customerVo.setContactName(userInfo.getContactName());
            customerVo.setContactPhone(userInfo.getContactPhone());
            customerVo.setMailAddress(userInfo.getConsigneeAdress());
            customerVo.setAlipayId(userInfo.getAlipayId());
            return customerService.updateCustomer(customerVo);
        }
        return 1;
    }

    public void addFlowState(UserInfo userInfo){
        for (FlowStateEnum value : FlowStateEnum.values()) {
            FlowState flowState=new FlowState();
            flowState.setUserInfoId(userInfo.getId());
            flowState.setAlipayId(userInfo.getAlipayId());
            flowState.setTitle(ApplyTypeEnum.getDescByType(userInfo.getIdType()));
            flowState.setState(value.getDesc());
            if("submitted".equals(value.getModelType())){
                flowState.setIsValid(1);
            }else{
                flowState.setIsValid(0);
            }
            flowStateService.addFlowState(flowState);
        }
    }

    @Override
    public List<UserInfo> getAllUserInfo() {
        return baseMapper.getAllUserInfo();
    }

    @Override
    public List<UserInfo> getUserInfoByParam(UserInfo userInfo) {
        return baseMapper.getUserInfoByParam(userInfo);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }
}
