package com.lx.springboot.utils;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@ToString
public class TableUtils<T> {

    private String code;

    private String msg;

    private Long count;

    private List<T> data;
    
    private boolean is;
    
    private String tip;
    public TableUtils() {
        this("0", "", 0L, null);
    }
    public TableUtils(Page<T> page) {
        this("0", "", page == null ? 0 : page.getTotalElements(), page == null ? null : page.getContent());
    }

    public TableUtils(Long count, List<T> data) {
        this("0", "", count, data);
    }

    public TableUtils(String code, String msg, Long count, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }
    public TableUtils(String code, String msg, List<T> data, Long count, boolean is, String tip) {
        this.code = code;
        this.msg = msg;
        this.count=count;
        this.data = data;
        this.is=is;
        this.tip=tip;
    }
}
