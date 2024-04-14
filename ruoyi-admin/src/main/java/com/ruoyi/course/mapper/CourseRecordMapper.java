package com.ruoyi.course.mapper;

import java.util.List;

import com.ruoyi.course.domain.CourseNumStatus;
import com.ruoyi.course.domain.CourseRecord;
import com.ruoyi.course.domain.CourseRecordDTO;

/**
 * 学生拥有的课程Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-31
 */
public interface CourseRecordMapper 
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
    public List<CourseRecord> selectCourseRecordListApp(CourseRecord courseRecord);
    public List<CourseRecordDTO> selectCourseRecordCheckinStudent(CourseRecord courseRecord);

    /**
     * 新增学生拥有的课程
     * 
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    public int insertCourseRecord(CourseRecord courseRecord);
    public int insertCourseRecordWithIds(List<CourseRecord> records);
    public CourseNumStatus getCourseStudentNumber(CourseNumStatus course);

    /**
     * 修改学生拥有的课程
     * 
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    public int updateCourseRecord(CourseRecord courseRecord);

    /**
     * 删除学生拥有的课程
     * 
     * @param id 学生拥有的课程主键
     * @return 结果
     */
    public int deleteCourseRecordById(Long id);

    /**
     * 批量删除学生拥有的课程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseRecordByIds(Long[] ids);
}
