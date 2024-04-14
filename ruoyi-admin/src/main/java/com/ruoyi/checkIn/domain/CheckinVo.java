package com.ruoyi.checkIn.domain;


import lombok.Data;
import java.util.Date;

@Data
public class CheckinVo {

    private String id;
    private String roleKey;

    private Long courseId;
    private String courseName;

    private Long studentId;

    private Long teacherId;
    private String teacherName;

    private Date startTime;

    private Date endTime;

    private Long method;
    private String methodName;

    private String source;

    private Long delFlag;

    private int duration;
}
