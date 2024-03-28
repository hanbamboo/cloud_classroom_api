package com.ruoyi.notification.mapper;

import java.util.List;
import com.ruoyi.notification.domain.Notification;

/**
 * 课堂通知Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface NotificationMapper 
{
    /**
     * 查询课堂通知
     * 
     * @param id 课堂通知主键
     * @return 课堂通知
     */
    public Notification selectNotificationById(Long id);

    /**
     * 查询课堂通知列表
     * 
     * @param notification 课堂通知
     * @return 课堂通知集合
     */
    public List<Notification> selectNotificationList(Notification notification);

    /**
     * 新增课堂通知
     * 
     * @param notification 课堂通知
     * @return 结果
     */
    public int insertNotification(Notification notification);

    /**
     * 修改课堂通知
     * 
     * @param notification 课堂通知
     * @return 结果
     */
    public int updateNotification(Notification notification);

    /**
     * 删除课堂通知
     * 
     * @param id 课堂通知主键
     * @return 结果
     */
    public int deleteNotificationById(Long id);

    /**
     * 批量删除课堂通知
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNotificationByIds(Long[] ids);
}
