package com.ruoyi.course.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.course.domain.CourseDTO;
import com.ruoyi.course.domain.CourseNumStatus;
import com.ruoyi.course.mapper.CourseRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.course.mapper.CourseMapper;
import com.ruoyi.course.domain.Course;
import com.ruoyi.course.service.ICourseService;

/**
 * 课程Service业务层处理
 *
 * @author hanbamboo
 * @date 2024-03-31
 */
@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private CourseRecordMapper courseRecordMapper;

    /**
     * 查询课程
     *
     * @param id 课程主键
     * @return 课程
     */
    @Override
    public CourseDTO selectCourseById(Long id) {
        return courseMapper.selectCourseById(id);
    }

    /**
     * 查询课程列表
     *
     * @param course 课程
     * @return 课程
     */
    @Override
    public List<Course> selectCourseList(Course course) {
        return courseMapper.selectCourseList(course);
    }

    @Override
    public List<CourseDTO> selectCourseListApp(CourseDTO course) {
        List<CourseDTO> courses = courseMapper.selectCourseListApp(course);
        CourseNumStatus courseNumStatus = new CourseNumStatus();
        courseNumStatus.setStudentId(course.getStudentId());
        courses.forEach(item -> {
            courseNumStatus.setCourseId(item.getId());
            CourseNumStatus res = courseRecordMapper.getCourseStudentNumber(courseNumStatus);
           item.setSelected(res.getSelectNum());
           item.setExists(res.getExists());
        });
        return courses;
    }

    /**
     * 新增课程
     *
     * @param course 课程
     * @return 结果
     */
    @Override
    public int insertCourse(Course course) {
        course.setCreateTime(DateUtils.getNowDate());
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改课程
     *
     * @param course 课程
     * @return 结果
     */
    @Override
    public int updateCourse(Course course) {
        course.setUpdateTime(DateUtils.getNowDate());
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除课程
     *
     * @param ids 需要删除的课程主键
     * @return 结果
     */
    @Override
    public int deleteCourseByIds(Long[] ids) {
        return courseMapper.deleteCourseByIds(ids);
    }

    /**
     * 删除课程信息
     *
     * @param id 课程主键
     * @return 结果
     */
    @Override
    public int deleteCourseById(Long id) {
        return courseMapper.deleteCourseById(id);
    }
}
