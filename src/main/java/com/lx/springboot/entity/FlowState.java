package com.lx.springboot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 申请人信息
 * </p>
 */
public class FlowState implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer   id ;

    private Integer userInfoId;//申请单表主键

    private String   title ;//标题

    private String   note ;//备注

    private String   state;//状态

    private Date createTime;

    private Date updateTime;

    private Integer   isValid ;// '是否有效1：有效 0：无效',

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public String toString() {
        return "FlowState{" +
                "id=" + id +
                ", userInfoId=" + userInfoId +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", state='" + state + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isValid=" + isValid +
                '}';
    }
}
