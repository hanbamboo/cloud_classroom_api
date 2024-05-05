package com.ruoyi.course.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.course.domain.CourseDTO;
import com.ruoyi.course.domain.CourseNumStatus;
import com.ruoyi.course.domain.CourseRecordDTO;
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
public class CourseRecordServiceImpl implements ICourseRecordService {
    @Autowired
    private CourseRecordMapper courseRecordMapper;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询学生拥有的课程
     *
     * @param id 学生拥有的课程主键
     * @return 学生拥有的课程
     */
    @Override
    public CourseRecord selectCourseRecordById(Long id) {
        return courseRecordMapper.selectCourseRecordById(id);
    }

    /**
     * 查询学生拥有的课程列表
     *
     * @param courseRecord 学生拥有的课程
     * @return 学生拥有的课程
     */
    @Override
    public List<CourseRecord> selectCourseRecordList(CourseRecord courseRecord) {
        return courseRecordMapper.selectCourseRecordList(courseRecord);

    }

    @Override
    public List<CourseRecord> selectCourseRecordListApp(CourseRecord courseRecord) {
        return courseRecordMapper.selectCourseRecordListApp(courseRecord);
    }

    @Override
    public List<CourseRecordDTO> selectCourseRecordCheckinStudent(CourseRecord courseRecord) {
        Object o = redisCache.getCacheObject("checkin:" + courseRecord.getCourseId() + ":student");
        List<CourseRecordDTO> courseRecordList = new ArrayList<>();
        if (o != null) {
            courseRecordList = (List<CourseRecordDTO>) o;
            if (!courseRecordList.isEmpty()) {
                return courseRecordList;
            }
        }
        courseRecordList = courseRecordMapper.selectCourseRecordCheckinStudent(courseRecord);
        redisCache.setCacheObject("checkin:" + courseRecord.getCourseId() + ":student", courseRecordList, 35, TimeUnit.MINUTES);
        return courseRecordList;
    }

    /**
     * 新增学生拥有的课程
     *
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    @Override
    public int insertCourseRecord(CourseRecord courseRecord) {
        courseRecord.setCreateTime(DateUtils.getNowDate());
        return courseRecordMapper.insertCourseRecord(courseRecord);
    }

    @Override
    public int insertCourseRecordWithIds(List<CourseRecord> records) {
        return courseRecordMapper.insertCourseRecordWithIds(records);
    }

    @Override
    public CourseNumStatus getCourseNumStatus(CourseNumStatus course) {
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setCourseId(course.getCourseId());
        List<CourseRecord> list = courseRecordMapper.selectCourseRecordList(courseRecord);
        Optional<CourseRecord> matchingRecord = list.stream()
                .filter(record -> record.getCourseId().equals(course.getCourseId()))
                .filter(record -> record.getStudentId().equals(course.getStudentId()))
                .findFirst();
        CourseNumStatus courseNumStatus = new CourseNumStatus();
        CourseRecord foundRecord = new CourseRecord();
        if (matchingRecord.isPresent()) {
            foundRecord = matchingRecord.get();
            courseRecordMapper.deleteCourseRecordById(foundRecord.getId());
            courseNumStatus.setExists("no");
            courseNumStatus.setSelectNum(list.size() - 1);
        } else {
            CourseRecord courseRecordAdd = new CourseRecord();
            courseRecordAdd.setCourseId(course.getCourseId());
            courseRecordAdd.setStudentId(course.getStudentId());
            courseRecordMapper.insertCourseRecord(courseRecordAdd);
            courseNumStatus.setSelectNum(list.size() + 1);
            courseNumStatus.setExists("yes");
        }
        courseNumStatus.setCapacity(course.getCapacity());
        courseNumStatus.setStudentId(foundRecord.getCourseId());
        return courseNumStatus;
    }

    /**
     * 修改学生拥有的课程
     *
     * @param courseRecord 学生拥有的课程
     * @return 结果
     */
    @Override
    public int updateCourseRecord(CourseRecord courseRecord) {
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
    public int deleteCourseRecordByIds(Long[] ids) {
        return courseRecordMapper.deleteCourseRecordByIds(ids);
    }

    /**
     * 删除学生拥有的课程信息
     *
     * @param id 学生拥有的课程主键
     * @return 结果
     */
    @Override
    public int deleteCourseRecordById(Long id) {
        return courseRecordMapper.deleteCourseRecordById(id);
    }
}
