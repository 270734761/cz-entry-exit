package com.lx.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lx.springboot.Enums.ApplyTypeEnum;
import com.lx.springboot.Enums.FlowStateEnum;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.entity.FlowState;
import com.lx.springboot.mapper.EntryExitApplyMapper;
import com.lx.springboot.service.CustomerService;
import com.lx.springboot.service.EntryExitApplyService;
import com.lx.springboot.service.FlowStateService;
import com.lx.springboot.vo.CustomerVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        flowStateService.insertFlowState(entryExitApply);
        //增加更新会员信息
        customerService.addOrupdateCustomer(entryExitApply);
        return id;
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
