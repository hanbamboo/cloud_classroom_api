package com.ruoyi.course.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.course.mapper.CourseRecordMapper;
import com.ruoyi.course.domain.CourseRecord;
import com.ruoyi.course.service.ICourseRecordService;

/**
 * 学生拥有的课程Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-31
 */
@Service
public class CourseRecordServiceImpl implements ICourseRecordService 
{
    @Autowired
    private CourseRecordMapper courseRecordMapper;

    /**
     * 查询学生拥有的课程
     * 
     * @param id 学生拥有的课程主键
     * @return 学生拥有的课程
     */
    @Override
    public CourseRecord selectCourseRecordById(Long id)
    {
        return courseRecordMapper.selectCourseRecordById(id);
    }

    /**
     * 查询学生拥有的课程列表
     * 
     * @param courseRecord 学生拥有的课程
     * @return 学生拥有的课程
     */
    @Override
    public List<CourseRecord> selectCourseRecordList(CourseRecord courseRecord)
    {
        return courseRecordMapper.selectCourseRecordList(courseRecord);
    }

    /**
     * 新增学生拥有的课程
     * 
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    @Override
    public int insertCourseRecord(CourseRecord courseRecord)
    {
        courseRecord.setCreateTime(DateUtils.getNowDate());
        return courseRecordMapper.insertCourseRecord(courseRecord);
    }

    /**
     * 修改学生拥有的课程
     * 
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    @Override
    public int updateCourseRecord(CourseRecord courseRecord)
    {
        courseRecord.setUpdateTime(DateUtils.getNowDate());
        return courseRecordMapper.updateCourseRecord(courseRecord);
    }

    /**
     * 批量删除学生拥有的课程
     * 
     * @param ids 需要删除的学生拥有的课程主键
     * @return 结果
     */
    @Override
    public int deleteCourseRecordByIds(Long[] ids)
    {
        return courseRecordMapper.deleteCourseRecordByIds(ids);
    }

    /**
     * 删除学生拥有的课程信息
     * 
     * @param id 学生拥有的课程主键
     * @return 结果
     */
    @Override
    public int deleteCourseRecordById(Long id)
    {
        return courseRecordMapper.deleteCourseRecordById(id);
    }
}