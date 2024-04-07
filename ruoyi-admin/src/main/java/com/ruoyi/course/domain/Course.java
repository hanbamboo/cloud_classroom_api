package com.ruoyi.course.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程对象 course
 * 
 * @author hanbamboo
 * @date 2024-03-31
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 课程名 */
    @Excel(name = "课程名")
    private String name;

    /** 课程描述 */
    @Excel(name = "课程描述")
    private String desp;

    /** 必修课、选修课、实验课字典 */
    @Excel(name = "必修课、选修课、实验课字典")
    private Long type;

    /** 上课时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "上课时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    /** 教师id */
    @Excel(name = "教师id")
    private Long teacherId;

    /** 授课学生数 */
    @Excel(name = "授课学生数")
    private Long capacity;

    /** 授课中、已结束、已取消字典 */
    @Excel(name = "授课中、已结束、已取消字典")
    private Long status;

    /** 课程标签 */
    @Excel(name = "课程标签")
    private String tags;

    /** 开课学院 */
    @Excel(name = "开课学院")
    private Long deptId;

    /** 课程封面 */
    @Excel(name = "课程封面")
    private String cover;

    /** 课程学分 */
    @Excel(name = "课程学分")
    private BigDecimal credits;

    /** 授课课时 */
    @Excel(name = "授课课时")
    private Long period;

    /** 标志 */
    private Long delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDesp(String desp) 
    {
        this.desp = desp;
    }

    public String getDesp() 
    {
        return desp;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setCapacity(Long capacity) 
    {
        this.capacity = capacity;
    }

    public Long getCapacity() 
    {
        return capacity;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setTags(String tags) 
    {
        this.tags = tags;
    }

    public String getTags() 
    {
        return tags;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }
    public void setCredits(BigDecimal credits) 
    {
        this.credits = credits;
    }

    public BigDecimal getCredits() 
    {
        return credits;
    }
    public void setPeriod(Long period) 
    {
        this.period = period;
    }

    public Long getPeriod() 
    {
        return period;
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
            .append("name", getName())
            .append("desp", getDesp())
            .append("type", getType())
            .append("time", getTime())
            .append("teacherId", getTeacherId())
            .append("capacity", getCapacity())
            .append("status", getStatus())
            .append("tags", getTags())
            .append("deptId", getDeptId())
            .append("cover", getCover())
            .append("credits", getCredits())
            .append("period", getPeriod())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("remark", getRemark())
            .toString();
    }
}
