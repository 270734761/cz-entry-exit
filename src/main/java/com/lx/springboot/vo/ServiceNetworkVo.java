package com.lx.springboot.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: workspace
 * @description: ServiceNetwork
 * @author: zhangjun
 * @create: 2019-12-05 17:26
 */
public class ServiceNetworkVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 网点名称
     */
    private String networkName;

    /**
     * 网点地址
     */
    private String networkAddress;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 受理事项
     */
    private String accepteMatter;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 是否有效
     */
    private Integer isValid;

    /**
     * 地理坐标
     */
    private String coordinate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccepteMatter() {
        return accepteMatter;
    }

    public void setAccepteMatter(String accepteMatter) {
        this.accepteMatter = accepteMatter;
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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }
}
