package com.lx.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lx.springboot.entity.EntryExitApply;
import com.lx.springboot.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 出入境申请 服务类
 * </p>
 */
public interface EntryExitApplyService extends IService<EntryExitApply> {

    int addEntryExitApply(EntryExitApply entryExitApply);

    List<EntryExitApply> getAllEntryExitApply();

    List<EntryExitApply> getEntryExitApplyByParam(EntryExitApply entryExitApply);

    int deleteById(Long id);
}
