package com.ruoyi.checkIn.service.impl;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.ruoyi.checkIn.domain.CheckinHistoryVo;
import com.ruoyi.checkIn.domain.CheckinVo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.websocket.server.WebSocketServer;
import com.ruoyi.course.domain.Course;
import com.ruoyi.course.domain.CourseDTO;
import com.ruoyi.course.domain.CourseRecord;
import com.ruoyi.course.domain.CourseRecordDTO;
import com.ruoyi.course.mapper.CourseMapper;
import com.ruoyi.course.mapper.CourseRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.checkIn.mapper.CheckinMapper;
import com.ruoyi.checkIn.domain.Checkin;
import com.ruoyi.checkIn.service.ICheckinService;

/**
 * 签到信息Service业务层处理
 *
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class CheckinServiceImpl implements ICheckinService {
    @Autowired
    private CheckinMapper checkinMapper;
    @Autowired
    private CourseRecordMapper courseRecordMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private WebSocketServer webSocketServer;
    @Autowired
    private RedisCache redisCache;

    /**
     * 查询签到信息
     *
     * @param id 签到信息主键
     * @return 签到信息
     */
    @Override
    public Checkin selectCheckinById(String id) {
        return checkinMapper.selectCheckinById(id);
    }

    @Override
    public CheckinVo getCurrentCheckin(CheckinVo checkin) {
        Collection<String> keys = redisCache.keys("checkin:*");
        if (keys.isEmpty()) {
            return null;
        }
        if ("student".equals(checkin.getRoleKey())) {
            CourseRecord courseRecord = new CourseRecord();
            courseRecord.setStudentId(checkin.getTeacherId());
            List<CourseRecord> records = courseRecordMapper.selectCourseRecordList(courseRecord);
            for (CourseRecord record : records) {
                String key = "checkin:" + record.getCourseId() + ":info";
                if (keys.contains(key) && Objects.equals(record.getStudentId(), checkin.getTeacherId())) {
                    Object checkinValue = redisCache.getCacheObject(key);
                    if (checkinValue != null) {
                        return (CheckinVo) checkinValue;
                    }
                }
            }
        } else {
            List<Course> courses = courseMapper.selectCourseByTeacherId(checkin.getTeacherId());
            if (courses != null) {
                for (Course course : courses) {
                    String key = "checkin:" + course.getId() + ":info";
                    if (keys.contains(key)) {
                        Object checkinValue = redisCache.getCacheObject(key);
                        if (checkinValue != null) {
                            return (CheckinVo) checkinValue;
                        }
                    }

                }

            }
        }

        return null;
    }

    /**
     * 查询签到信息列表
     *
     * @param checkin 签到信息
     * @return 签到信息
     */
    @Override
    public List<Checkin> selectCheckinList(Checkin checkin) {
        return checkinMapper.selectCheckinList(checkin);
    }

    @Override
    public List<CheckinHistoryVo> selectCheckinListApp(Checkin checkin) {
        List<CheckinHistoryVo> list = checkinMapper.selectCheckinListApp(checkin);
        DecimalFormat df = new DecimalFormat("#.##");
        list.forEach(item -> {
            double rate = item.getCourseCountPeople() * 1.0 / item.getCourseCountTotal();
            String formattedRate = df.format(rate);
            item.setCheckinRateNormal(Double.parseDouble(formattedRate));
            rate = item.getCourseCountOut() * 1.0 / item.getCourseCountTotal();
            formattedRate = df.format(rate);
            item.setCheckinRateOut(Double.parseDouble(formattedRate));
        });
        return list;
    }

    /**
     * 新增签到信息
     *
     * @param checkin 签到信息
     * @return 结果
     */
    @Override
    public AjaxResult insertCheckin(Checkin checkin) {
        checkin.setCreateTime(DateUtils.getNowDate());
        Object data = redisCache.getCacheObject("checkin:" + checkin.getCourseId() + ":info");
        if (data != null) {
            return AjaxResult.error("当前已存在进行中的签到！请勿重复发布！");
        }
        CourseDTO course = courseMapper.selectCourseById(checkin.getCourseId());
        CheckinVo checkinVo = new CheckinVo();
        List<SysDictData> dictData = redisCache.getCacheObject("sys_dict:class_checkin_type");
        for (SysDictData dictDatum : dictData) {
            if (checkin.getMethod().toString().equals(dictDatum.getDictValue())) {
                checkinVo.setMethodName(dictDatum.getDictLabel());
            }
        }
        checkinVo.setCourseId(checkin.getCourseId());
        checkinVo.setTeacherId(checkin.getTeacherId());
        checkinVo.setId(checkin.getId());
        checkinVo.setMethod(checkin.getMethod());
        checkinVo.setSource(checkin.getSource());
        checkinVo.setCourseName(course.getName());
        checkinVo.setTeacherName(course.getTeacherName());
        checkinVo.setStartTime(checkin.getStartTime());
        checkinVo.setEndTime(checkin.getEndTime());
        // 留出签到最后期限五分钟
        long diffInMilliseconds = (checkin.getEndTime().getTime() - checkin.getStartTime().getTime()) / 1000 + (60 * 5);
        redisCache.setCacheObject("checkin:" + checkin.getCourseId() + ":info", checkinVo, (int) diffInMilliseconds, TimeUnit.SECONDS);
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setCourseId(checkin.getCourseId());
        List<CourseRecordDTO> records = courseRecordMapper.selectCourseRecordCheckinStudent(courseRecord);
        redisCache.setCacheObject("checkin:" + checkin.getCourseId() + ":student", records, (int) diffInMilliseconds, TimeUnit.SECONDS);
        webSocketServer.addOnlineCheckinMap(checkinVo.getId(), checkinVo.getCourseId(), checkin.getEndTime());
        checkinMapper.insertCheckin(checkin);
        return AjaxResult.success();
    }

    /**
     * 修改签到信息
     *
     * @param checkin 签到信息
     * @return 结果
     */
    @Override
    public int updateCheckin(Checkin checkin) {
        checkin.setUpdateTime(DateUtils.getNowDate());
        return checkinMapper.updateCheckin(checkin);
    }

    /**
     * 批量删除签到信息
     *
     * @param ids 需要删除的签到信息主键
     * @return 结果
     */
    @Override
    public int deleteCheckinByIds(String[] ids) {
        return checkinMapper.deleteCheckinByIds(ids);
    }

    /**
     * 删除签到信息信息
     *
     * @param id 签到信息主键
     * @return 结果
     */
    @Override
    public int deleteCheckinById(String id) {
        return checkinMapper.deleteCheckinById(id);
    }
}
