package com.ruoyi.myclass.approval.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审批结果对象 approval_record
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public class ApprovalRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 请假申请id */
    @Excel(name = "请假申请id")
    private String leaveId;

    /** 审批人id */
    @Excel(name = "审批人id")
    private Long approverId;

    /** 审批意见 */
    @Excel(name = "审批意见")
    private String comment;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvalTime;

    /** 待审批、已批准、已拒绝、已撤销、已转交、已过期等 */
    @Excel(name = "待审批、已批准、已拒绝、已撤销、已转交、已过期等")
    private Long status;

    /** 上级审批人 */
    @Excel(name = "上级审批人")
    private Long superiorId;

    /** 转交审批人 */
    @Excel(name = "转交审批人")
    private Long forwardId;

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
    public void setLeaveId(String leaveId) 
    {
        this.leaveId = leaveId;
    }

    public String getLeaveId() 
    {
        return leaveId;
    }
    public void setApproverId(Long approverId) 
    {
        this.approverId = approverId;
    }

    public Long getApproverId() 
    {
        return approverId;
    }
    public void setComment(String comment) 
    {
        this.comment = comment;
    }

    public String getComment() 
    {
        return comment;
    }
    public void setApprovalTime(Date approvalTime) 
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime() 
    {
        return approvalTime;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setSuperiorId(Long superiorId) 
    {
        this.superiorId = superiorId;
    }

    public Long getSuperiorId() 
    {
        return superiorId;
    }
    public void setForwardId(Long forwardId) 
    {
        this.forwardId = forwardId;
    }

    public Long getForwardId() 
    {
        return forwardId;
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
            .append("leaveId", getLeaveId())
            .append("approverId", getApproverId())
            .append("comment", getComment())
            .append("approvalTime", getApprovalTime())
            .append("status", getStatus())
            .append("superiorId", getSuperiorId())
            .append("forwardId", getForwardId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
