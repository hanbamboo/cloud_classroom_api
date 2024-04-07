package com.ruoyi.course.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程对象 course
 *
 * @author hanbamboo
 * @date 2024-03-31
 */
@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String desp;
    private Long type;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
    private Long teacherId;
    private Long studentId;
    private String teacherName;
    private String exists;
    private Integer selected;
    private Long capacity;
    private Long status;
    private String tags;
    private Long deptId;
    private String deptName;
    private String cover;
    private Double credits;
    private Long period;
    private String remark;
}
