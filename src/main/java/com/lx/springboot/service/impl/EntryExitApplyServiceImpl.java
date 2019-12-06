package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.Enums.ApplyTypeEnum;
import com.lx.springboot.Enums.FlowStateEnum;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.entity.UserInfo;
import com.lx.springboot.mapper.EntryExitApplyMapper;
import com.lx.springboot.mapper.UserInfoMapper;
import com.lx.springboot.service.CustomerService;
import com.lx.springboot.service.EntryExitApplyService;
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
public class EntryExitApplyServiceImpl extends ServiceImpl<EntryExitApplyMapper, EntryExitApply> implements EntryExitApplyService {

    @Autowired
    private FlowStateService flowStateService;
    @Autowired
    private CustomerService customerService;

    @Override
    public int addEntryExitApply(EntryExitApply entryExitApply) {
        entryExitApply.setFlowState(FlowStateEnum.SUBMITTED.getDesc());//已提交
        entryExitApply.setIsValid(1);
        baseMapper.addEntryExitApply(entryExitApply);
        int id=entryExitApply.getId();
        //创建办件流转信息
        addFlowState(entryExitApply);
        //更新会员信息
        updateCustomer(entryExitApply);
        return id;
    }

    public int updateCustomer(EntryExitApply entryExitApply){
        Map<String,String> param=new HashMap<String,String>();
        param.put("alipayId",entryExitApply.getAlipayId());
        List<CustomerVo> customerList= customerService.getCustomerByParams(param);
        if(CollectionUtils.isNotEmpty(customerList)){
            CustomerVo customerVo=new CustomerVo();
            customerVo.setCustomerName(entryExitApply.getNamef()+entryExitApply.getNamel());
            String namePinyin=entryExitApply.getNamepinf()+" "+entryExitApply.getNamepinl();
            if(StringUtils.isNotEmpty(namePinyin)){
                customerVo.setNamePinyin(namePinyin.toUpperCase());
            }
            customerVo.setContactName(entryExitApply.getContactName());
            customerVo.setContactPhone(entryExitApply.getContactPhone());
            customerVo.setMailAddress(entryExitApply.getConsigneeAdress());
            customerVo.setAlipayId(entryExitApply.getAlipayId());
            return customerService.updateCustomer(customerVo);
        }
        return 1;
    }

    public void addFlowState(EntryExitApply entryExitApply){
        int i=1;
        for (FlowStateEnum value : FlowStateEnum.values()) {
            FlowState flowState=new FlowState();
            flowState.setApplyId(entryExitApply.getId());
            flowState.setAlipayId(entryExitApply.getAlipayId());
            flowState.setTitle(ApplyTypeEnum.getDescByType(entryExitApply.getIdType()));
            flowState.setFlowStateDesc(value.getDesc());
            flowState.setFlowState(i);
            if("submitted".equals(value.getModelType())){
                flowState.setIsValid(1);
            }else{
                flowState.setIsValid(0);
            }
            i++;
            flowStateService.addFlowState(flowState);
        }
    }

    @Override
    public List<EntryExitApply> getAllEntryExitApply() {
        return baseMapper.getAllEntryExitApply();
    }

    @Override
    public List<EntryExitApply> getEntryExitApplyByParam(EntryExitApply entryExitApply) {
        return baseMapper.getEntryExitApplyByParam(entryExitApply);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }
}
