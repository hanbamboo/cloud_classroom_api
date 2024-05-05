package com.ruoyi.notification.service.impl;

import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.notification.domain.NotificationRecord;
import com.ruoyi.notification.mapper.NotificationRecordMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.notification.mapper.NotificationMapper;
import com.ruoyi.notification.domain.Notification;
import com.ruoyi.notification.service.INotificationService;

/**
 * 课堂通知Service业务层处理
 *
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class NotificationServiceImpl implements INotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private NotificationRecordMapper notificationRecordMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询课堂通知
     *
     * @param id 课堂通知主键
     * @return 课堂通知
     */
    @Override
    public Notification selectNotificationById(Long id) {
        Notification notification =  notificationMapper.selectNotificationById(id);
        notification.setIsRead(0);
        notification.setHasRead(0L);
        notification.setTotalRead(0L);
        NotificationRecord record = new NotificationRecord();
        record.setNotificationId(id);
        JSONArray users = JSONArray.parseArray(notification.getRecipientId());
        notification.setTotalRead(Long.parseLong(String.valueOf(users.size())));
        for (Object user : users) {
            if("-1".equals(user.toString())||"-2".equals(user.toString())||"-3".equals(user.toString())){
                notification.setHasRead((long) notificationRecordMapper.selectNotificationRecordList(record).size());
                continue;
            }
            record.setUserId(Long.parseLong(user.toString()));
            NotificationRecord temp = notificationRecordMapper.selectNotificationRecord(record);
            if (temp != null) {
                notification.setHasRead(notification.getHasRead() + 1);
            }
        }
        record = notificationRecordMapper.selectNotificationRecordByNoticeId(notification.getId(),SecurityUtils.getUserId());
        if (record != null) {
            notification.setIsRead(1);
        }
        return notification;
    }

    /**
     * 查询课堂通知列表
     *
     * @param notification 课堂通知
     * @return 课堂通知
     */
    @Override
    public List<Notification> selectNotificationList(Notification notification) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("-1");
        String roleKey = SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleKey();
        if("student".equals(roleKey)){
            jsonArray.add(-3);
        }else if ("instructor".equals(roleKey)|| "teacher".equals(roleKey)){
            jsonArray.add(-2);
        }
        String[] array = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            array[i] = jsonArray.get(i).toString();
        }
        notification.setRoles(array);
        List<Notification> notifications = notificationMapper.selectNotificationList(notification);
        for (Notification notice : notifications) {
            notice.setIsRead(0);
            notice.setHasRead(0L);
            notice.setTotalRead(0L);
            NotificationRecord record = new NotificationRecord();
            record.setNotificationId(notice.getId());
            JSONArray users = JSONArray.parseArray(notice.getRecipientId());
            notice.setTotalRead(Long.parseLong(String.valueOf(users.size())));
            for (Object user : users) {
                record.setUserId(Long.parseLong(user.toString()));
                NotificationRecord temp = notificationRecordMapper.selectNotificationRecord(record);
                if (temp != null) {
                    notice.setHasRead(notice.getHasRead() + 1);
                }
            }
            record = notificationRecordMapper.selectNotificationRecordByNoticeId(notice.getId(), SecurityUtils.getUserId());
            if (record != null) {
                notice.setIsRead(1);
            }
        }
        return notifications;
    }

    /**
     * 新增课堂通知
     *
     * @param notification 课堂通知
     * @return 结果
     */
    @Override
    public int insertNotification(Notification notification) {
        notification.setCreateTime(DateUtils.getNowDate());
        notification.setCreateBy(SecurityUtils.getUsername());
        if (StringUtils.isEmpty(notification.getRecipientId())) {
            notification.setRecipientId(new JSONArray().toJSONString());
        }
        return notificationMapper.insertNotification(notification);
    }

    @Override
    public Long getNotificationNum(Notification notification) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("-1");
        String roleKey = SecurityUtils.getLoginUser().getUser().getRoles().get(0).getRoleKey();
        if("student".equals(roleKey)){
            jsonArray.add(-3);
        }else if ("instructor".equals(roleKey)|| "teacher".equals(roleKey)){
            jsonArray.add(-2);
        }
        String[] array = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            array[i] = jsonArray.get(i).toString();
        }
        notification.setRoles(array);
        List<Notification> notifications = notificationMapper.selectNotificationList(notification);
        Long num = 0L;
        for (Notification notice : notifications) {
            NotificationRecord record = new NotificationRecord();
            record = notificationRecordMapper.selectNotificationRecordByNoticeId(notice.getId(),SecurityUtils.getUserId());
            if (record == null || record.getUserId() == null || record.getUserId() != Long.parseLong(notification.getRecipientId())) {
                num++;
            }
        }
        return num;
    }

    /**
     * 修改课堂通知
     *
     * @param notification 课堂通知
     * @return 结果
     */
    @Override
    public int updateNotification(Notification notification) {
        notification.setUpdateTime(DateUtils.getNowDate());
        notification.setUpdateBy(SecurityUtils.getUsername());
        return notificationMapper.updateNotification(notification);
    }

    /**
     * 批量删除课堂通知
     *
     * @param ids 需要删除的课堂通知主键
     * @return 结果
     */
    @Override
    public int deleteNotificationByIds(Long[] ids) {
        return notificationMapper.deleteNotificationByIds(ids);
    }

    /**
     * 删除课堂通知信息
     *
     * @param id 课堂通知主键
     * @return 结果
     */
    @Override
    public int deleteNotificationById(Long id) {
        return notificationMapper.deleteNotificationById(id);
    }
}
