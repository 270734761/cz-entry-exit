package com.lx.springboot.query;

import com.lx.springboot.entity.AdvisoryNotice;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhangjun
 * @date 2019/12/04
 */
@Data
@ToString
@EqualsAndHashCode
public class AdvisoryNoticeQuery implements Serializable {

    private static final long serialVersionUID = -6062160134979070657L;


    private boolean usePaging = false;
    private int page = 1;
    private int limit = 10;
    private int offset;
    private String sortColumns;
    private String   type ;// 类型

}
