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
import com.ruoyi.notification.domain.Notification;
import com.ruoyi.notification.service.INotificationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 课堂通知Controller
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/notification/info")
public class NotificationController extends BaseController
{
    @Autowired
    private INotificationService notificationService;

    /**
     * 查询课堂通知列表
     */
//    @PreAuthorize("@ss.hasPermi('notification:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(Notification notification)
    {
        startPage();
        List<Notification> list = notificationService.selectNotificationList(notification);
        return getDataTable(list);
    }

    /**
     * 导出课堂通知列表
     */
    @PreAuthorize("@ss.hasPermi('notification:info:export')")
    @Log(title = "课堂通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Notification notification)
    {
        List<Notification> list = notificationService.selectNotificationList(notification);
        ExcelUtil<Notification> util = new ExcelUtil<Notification>(Notification.class);
        util.exportExcel(response, list, "课堂通知数据");
    }

    /**
     * 获取课堂通知详细信息
     */
//    @PreAuthorize("@ss.hasPermi('notification:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(notificationService.selectNotificationById(id));
    }

    /**
     * 新增课堂通知
     */
//    @PreAuthorize("@ss.hasPermi('notification:info:add')")
    @Log(title = "课堂通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Notification notification)
    {
        return toAjax(notificationService.insertNotification(notification));
    }

    @Log(title = "课堂通知", businessType = BusinessType.INSERT)
    @GetMapping("/app/num")
    public AjaxResult getNotificationNum(Notification notification)
    {
        return AjaxResult.success("操作成功！",notificationService.getNotificationNum(notification));
    }

    /**
     * 修改课堂通知
     */
//    @PreAuthorize("@ss.hasPermi('notification:info:edit')")
    @Log(title = "课堂通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Notification notification)
    {
        return toAjax(notificationService.updateNotification(notification));
    }

    /**
     * 删除课堂通知
     */
//    @PreAuthorize("@ss.hasPermi('notification:info:remove')")
    @Log(title = "课堂通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(notificationService.deleteNotificationByIds(ids));
    }
}
