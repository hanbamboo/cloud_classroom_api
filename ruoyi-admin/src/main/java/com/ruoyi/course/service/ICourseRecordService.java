package com.ruoyi.course.service;

import java.util.List;

import com.ruoyi.course.domain.CourseNumStatus;
import com.ruoyi.course.domain.CourseRecord;

/**
 * 学生拥有的课程Service接口
 * 
 * @author hanbamboo
 * @date 2024-03-31
 */
public interface ICourseRecordService 
{
    /**
     * 查询学生拥有的课程
     * 
     * @param id 学生拥有的课程主键
     * @return 学生拥有的课程
     */
    public CourseRecord selectCourseRecordById(Long id);

    /**
     * 查询学生拥有的课程列表
     * 
     * @param courseRecord 学生拥有的课程
     * @return 学生拥有的课程集合
     */
    public List<CourseRecord> selectCourseRecordList(CourseRecord courseRecord);

    /**
     * 新增学生拥有的课程
     * 
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    public int insertCourseRecord(CourseRecord courseRecord);
    public int insertCourseRecordWithIds(List<CourseRecord> records);

    /**
     * 查询学生在某个课程中的人数
     */
    public CourseNumStatus getCourseNumStatus(CourseNumStatus course);

    /**
     * 修改学生拥有的课程
     * 
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    public int updateCourseRecord(CourseRecord courseRecord);

    /**
     * 批量删除学生拥有的课程
     * 
     * @param ids 需要删除的学生拥有的课程主键集合
     * @return 结果
     */
    public int deleteCourseRecordByIds(Long[] ids);

    /**
     * 删除学生拥有的课程信息
     * 
     * @param id 学生拥有的课程主键
     * @return 结果
     */
    public int deleteCourseRecordById(Long id);
}
