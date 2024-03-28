package com.ruoyi.approver.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 审批人管理对象 approver
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public class Approver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 教师id */
    @Excel(name = "教师id")
    private Long approverId;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String approverName;

    /** 角色id */
    @Excel(name = "角色id")
    private Long roleId;

    /** 学院id */
    @Excel(name = "学院id")
    private Long deptId;

    /** 审批权限 */
    @Excel(name = "审批权限")
    private String authority;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contact;

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
    public void setApproverId(Long approverId) 
    {
        this.approverId = approverId;
    }

    public Long getApproverId() 
    {
        return approverId;
    }
    public void setApproverName(String approverName) 
    {
        this.approverName = approverName;
    }

    public String getApproverName() 
    {
        return approverName;
    }
    public void setRoleId(Long roleId) 
    {
        this.roleId = roleId;
    }

    public Long getRoleId() 
    {
        return roleId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setAuthority(String authority) 
    {
        this.authority = authority;
    }

    public String getAuthority() 
    {
        return authority;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
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
            .append("approverId", getApproverId())
            .append("approverName", getApproverName())
            .append("roleId", getRoleId())
            .append("deptId", getDeptId())
            .append("authority", getAuthority())
            .append("contact", getContact())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
