package com.ruoyi.notification.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.notification.domain.NotificationRecord;
import com.ruoyi.notification.service.INotificationRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 已读通知Controller
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/notification/record")
public class NotificationRecordController extends BaseController
{
    @Autowired
    private INotificationRecordService notificationRecordService;

    /**
     * 查询已读通知列表
     */
    @PreAuthorize("@ss.hasPermi('notification:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(NotificationRecord notificationRecord)
    {
        startPage();
        List<NotificationRecord> list = notificationRecordService.selectNotificationRecordList(notificationRecord);
        return getDataTable(list);
    }

    /**
     * 导出已读通知列表
     */
    @PreAuthorize("@ss.hasPermi('notification:record:export')")
    @Log(title = "已读通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NotificationRecord notificationRecord)
    {
        List<NotificationRecord> list = notificationRecordService.selectNotificationRecordList(notificationRecord);
        ExcelUtil<NotificationRecord> util = new ExcelUtil<NotificationRecord>(NotificationRecord.class);
        util.exportExcel(response, list, "已读通知数据");
    }

    /**
     * 获取已读通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('notification:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(notificationRecordService.selectNotificationRecordById(id));
    }

    /**
     * 新增已读通知
     */
    @PreAuthorize("@ss.hasPermi('notification:record:add')")
    @Log(title = "已读通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NotificationRecord notificationRecord)
    {
        return toAjax(notificationRecordService.insertNotificationRecord(notificationRecord));
    }

    /**
     * 修改已读通知
     */
    @PreAuthorize("@ss.hasPermi('notification:record:edit')")
    @Log(title = "已读通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NotificationRecord notificationRecord)
    {
        return toAjax(notificationRecordService.updateNotificationRecord(notificationRecord));
    }

    /**
     * 删除已读通知
     */
    @PreAuthorize("@ss.hasPermi('notification:record:remove')")
    @Log(title = "已读通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(notificationRecordService.deleteNotificationRecordByIds(ids));
    }
}
