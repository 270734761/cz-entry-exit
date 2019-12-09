package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.EntryExitApply;

import java.util.List;

public interface EntryExitApplyMapper extends BaseMapper<EntryExitApply> {

    int addEntryExitApply(EntryExitApply entryExitApply);

    /**
     * 获取所有的申请人信息
     *
     * @return
     */
    List<EntryExitApply> getAllEntryExitApply();

    List<EntryExitApply> getEntryExitApplyByParam(EntryExitApply entryExitApply);


    int deleteById(Long id);
}
