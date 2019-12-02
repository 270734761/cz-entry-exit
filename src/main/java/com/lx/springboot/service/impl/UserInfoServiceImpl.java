package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.Enums.ApplyTypeEnum;
import com.lx.springboot.Enums.FlowStateEnum;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.mapper.UserInfoMapper;
import com.lx.springboot.service.AdvisoryNoticeService;
import com.lx.springboot.service.FlowStateService;
import com.lx.springboot.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 申请人 服务实现类
 * </p>
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private FlowStateService flowStateService;

    @Override
    public int addUserInfo(UserInfo userInfo) {
        userInfo.setFlowState(FlowStateEnum.SUBMITTED.getDesc());//已提交
       baseMapper.addUserInfo(userInfo);
        int userInfoId=userInfo.getId();
        FlowState flowState=new FlowState();
        flowState.setUserInfoId(userInfoId);
        flowState.setAlipayId(userInfo.getAlipayId());
        flowState.setTitle(ApplyTypeEnum.getDescByType(userInfo.getIdType()));
        flowState.setState(FlowStateEnum.SUBMITTED.getDesc());
        flowState.setIsValid(1);
        flowStateService.addFlowState(flowState);
        return userInfoId;
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
