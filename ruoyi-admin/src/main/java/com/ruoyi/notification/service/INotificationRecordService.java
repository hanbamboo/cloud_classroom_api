package com.ruoyi.notification.service;

import java.util.List;
import com.ruoyi.notification.domain.NotificationRecord;

/**
 * 已读通知Service接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface INotificationRecordService 
{
    /**
     * 查询已读通知
     * 
     * @param id 已读通知主键
     * @return 已读通知
     */
    public NotificationRecord selectNotificationRecordById(Long id);

    /**
     * 查询已读通知列表
     * 
     * @param notificationRecord 已读通知
     * @return 已读通知集合
     */
    public List<NotificationRecord> selectNotificationRecordList(NotificationRecord notificationRecord);

    /**
     * 新增已读通知
     * 
     * @param notificationRecord 已读通知
     * @return 结果
     */
    public int insertNotificationRecord(NotificationRecord notificationRecord);

    /**
     * 修改已读通知
     * 
     * @param notificationRecord 已读通知
     * @return 结果
     */
    public int updateNotificationRecord(NotificationRecord notificationRecord);

    /**
     * 批量删除已读通知
     * 
     * @param ids 需要删除的已读通知主键集合
     * @return 结果
     */
    public int deleteNotificationRecordByIds(Long[] ids);

    /**
     * 删除已读通知信息
     * 
     * @param id 已读通知主键
     * @return 结果
     */
    public int deleteNotificationRecordById(Long id);
}
