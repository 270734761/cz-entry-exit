package com.lx.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lx.springboot.entity.AdvisoryNotice;
import com.lx.springboot.entity.UserInfo;

import java.util.List;

/**
 * <p>
 * 咨询公告 Mapper 接口
 * </p>
 */
public interface AdvisoryNoticeMapper extends BaseMapper<AdvisoryNotice> {

    int addAdvisoryNotice(AdvisoryNotice advisoryNotice);

    List<AdvisoryNotice> getAllAdvisoryNotice();

    List<AdvisoryNotice> getAdvisoryNoticePageByparam(AdvisoryNotice advisoryNotice);

    List<AdvisoryNotice> getAdvisoryNoticeByParam(AdvisoryNotice advisoryNotice);

    String getReadingProtocalByParam(AdvisoryNotice advisoryNotice);
    String getDetailById(Integer id);

    int deleteById(Long id);
}
