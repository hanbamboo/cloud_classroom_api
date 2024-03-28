package com.ruoyi.checkIn.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 签到明细对象 checkin_record
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public class CheckinRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典 */
    @Excel(name = "二维码签到、位置签到、直接签到、密码签到、人脸识别签到字典")
    private String checkinId;

    /** 学生id */
    @Excel(name = "学生id")
    private Long studentId;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkinTime;

    /** 已签到、未签到、迟到字典 */
    @Excel(name = "已签到、未签到、迟到字典")
    private Long status;

    /** 签到地点 */
    @Excel(name = "签到地点")
    private String location;

    /** 签到设备 */
    @Excel(name = "签到设备")
    private String device;

    /** $column.columnComment */
    private Long delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCheckinId(String checkinId) 
    {
        this.checkinId = checkinId;
    }

    public String getCheckinId() 
    {
        return checkinId;
    }
    public void setStudentId(Long studentId) 
    {
        this.studentId = studentId;
    }

    public Long getStudentId() 
    {
        return studentId;
    }
    public void setCheckinTime(Date checkinTime) 
    {
        this.checkinTime = checkinTime;
    }

    public Date getCheckinTime() 
    {
        return checkinTime;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setDevice(String device) 
    {
        this.device = device;
    }

    public String getDevice() 
    {
        return device;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("checkinId", getCheckinId())
            .append("studentId", getStudentId())
            .append("checkinTime", getCheckinTime())
            .append("status", getStatus())
            .append("location", getLocation())
            .append("remark", getRemark())
            .append("device", getDevice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
