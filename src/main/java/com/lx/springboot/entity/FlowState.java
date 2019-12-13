package com.lx.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    private Integer applyId;//申请单表主键

    private String   title ;//标题

    private String   note ;//备注

    private Integer   flowState;//状态

    private String   flowStateDesc;//状态描述

    private String alipayId;//支付宝id

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public Integer getFlowState() {
        return flowState;
    }

    public void setFlowState(Integer flowState) {
        this.flowState = flowState;
    }

    public String getFlowStateDesc() {
        return flowStateDesc;
    }

    public void setFlowStateDesc(String flowStateDesc) {
        this.flowStateDesc = flowStateDesc;
    }

    @Override
    public String toString() {
        return "FlowState{" +
                "id=" + id +
                ", applyId=" + applyId +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", flowState='" + flowState + '\'' +
                ", flowStateDesc='" + flowStateDesc + '\'' +
                ", alipayId='" + alipayId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isValid=" + isValid +
                '}';
    }
}
