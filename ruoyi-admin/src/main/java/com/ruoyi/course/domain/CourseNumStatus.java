package com.ruoyi.course.domain;


import lombok.Data;


/**
 * 课程对象 course
 * 
 * @author hanbamboo
 * @date 2024-03-31
 */

@Data
public class CourseNumStatus
{
    private Long id;
    private Long studentId;
    private Long courseId;
    private Long capacity;
    private Integer selectNum;
    private String exists;
}
