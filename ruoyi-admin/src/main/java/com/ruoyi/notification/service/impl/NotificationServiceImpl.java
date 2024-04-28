package com.ruoyi.notification.service.impl;

import java.util.List;

import com.alibaba.fastjson2.JSONArray;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.notification.domain.NotificationRecord;
import com.ruoyi.notification.mapper.NotificationRecordMapper;
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

    /**
     * 查询课堂通知
     *
     * @param id 课堂通知主键
     * @return 课堂通知
     */
    @Override
    public Notification selectNotificationById(Long id) {
        return notificationMapper.selectNotificationById(id);
    }

    /**
     * 查询课堂通知列表
     *
     * @param notification 课堂通知
     * @return 课堂通知
     */
    @Override
    public List<Notification> selectNotificationList(Notification notification) {
        return notificationMapper.selectNotificationList(notification);
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
        List<Notification> notifications = notificationMapper.getNotificationListByRecipientId(notification.getRecipientId());
        Long num = 0L;
        for (Notification notice : notifications) {
            NotificationRecord record = new NotificationRecord();
            record = notificationRecordMapper.selectNotificationRecordById(notice.getId());
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
