package com.mfcookie.excel.mapper;

public class SysSystemVO {
    private String systemName;
    private String systemKey;
    private String description;
    private String state;
    private String createUid;
    private String createTime;

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateUid() {
        return createUid;
    }

    public void setCreateUid(String createUid) {
        this.createUid = createUid;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysSystemVO{" +
                "systemName='" + systemName + '\'' +
                ", systemKey='" + systemKey + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", createUid='" + createUid + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
