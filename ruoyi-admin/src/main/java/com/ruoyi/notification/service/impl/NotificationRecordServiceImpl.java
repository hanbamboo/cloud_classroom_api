package com.ruoyi.notification.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.notification.mapper.NotificationRecordMapper;
import com.ruoyi.notification.domain.NotificationRecord;
import com.ruoyi.notification.service.INotificationRecordService;

/**
 * 已读通知Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class NotificationRecordServiceImpl implements INotificationRecordService 
{
    @Autowired
    private NotificationRecordMapper notificationRecordMapper;

    /**
     * 查询已读通知
     * 
     * @param id 已读通知主键
     * @return 已读通知
     */
    @Override
    public NotificationRecord selectNotificationRecordById(Long id)
    {
        return notificationRecordMapper.selectNotificationRecordById(id);
    }

    /**
     * 查询已读通知列表
     * 
     * @param notificationRecord 已读通知
     * @return 已读通知
     */
    @Override
    public List<NotificationRecord> selectNotificationRecordList(NotificationRecord notificationRecord)
    {
        return notificationRecordMapper.selectNotificationRecordList(notificationRecord);
    }

    /**
     * 新增已读通知
     * 
     * @param notificationRecord 已读通知
     * @return 结果
     */
    @Override
    public int insertNotificationRecord(NotificationRecord notificationRecord)
    {
        notificationRecord.setCreateTime(DateUtils.getNowDate());
        return notificationRecordMapper.insertNotificationRecord(notificationRecord);
    }

    /**
     * 修改已读通知
     * 
     * @param notificationRecord 已读通知
     * @return 结果
     */
    @Override
    public int updateNotificationRecord(NotificationRecord notificationRecord)
    {
        notificationRecord.setUpdateTime(DateUtils.getNowDate());
        return notificationRecordMapper.updateNotificationRecord(notificationRecord);
    }

    /**
     * 批量删除已读通知
     * 
     * @param ids 需要删除的已读通知主键
     * @return 结果
     */
    @Override
    public int deleteNotificationRecordByIds(Long[] ids)
    {
        return notificationRecordMapper.deleteNotificationRecordByIds(ids);
    }

    /**
     * 删除已读通知信息
     * 
     * @param id 已读通知主键
     * @return 结果
     */
    @Override
    public int deleteNotificationRecordById(Long id)
    {
        return notificationRecordMapper.deleteNotificationRecordById(id);
    }
}
