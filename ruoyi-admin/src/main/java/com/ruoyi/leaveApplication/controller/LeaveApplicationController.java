package com.ruoyi.leaveApplication.controller;

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
import com.ruoyi.leaveApplication.domain.LeaveApplication;
import com.ruoyi.leaveApplication.service.ILeaveApplicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生请假信息Controller
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/leaveApplication/leave")
public class LeaveApplicationController extends BaseController
{
    @Autowired
    private ILeaveApplicationService leaveApplicationService;

    /**
     * 查询学生请假信息列表
     */
//    @PreAuthorize("@ss.hasPermi('leaveApplication:leave:list')")
    @GetMapping("/list")
    public TableDataInfo list(LeaveApplication leaveApplication)
    {
        startPage();
        List<LeaveApplication> list = leaveApplicationService.selectLeaveApplicationList(leaveApplication);
        return getDataTable(list);
    }

    /**
     * 导出学生请假信息列表
     */
    @PreAuthorize("@ss.hasPermi('leaveApplication:leave:export')")
    @Log(title = "学生请假信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LeaveApplication leaveApplication)
    {
        List<LeaveApplication> list = leaveApplicationService.selectLeaveApplicationList(leaveApplication);
        ExcelUtil<LeaveApplication> util = new ExcelUtil<LeaveApplication>(LeaveApplication.class);
        util.exportExcel(response, list, "学生请假信息数据");
    }

    /**
     * 获取学生请假信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('leaveApplication:leave:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(leaveApplicationService.selectLeaveApplicationById(id));
    }

    /**
     * 新增学生请假信息
     */
//    @PreAuthorize("@ss.hasPermi('leaveApplication:leave:add')")
    @Log(title = "学生请假信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LeaveApplication leaveApplication)
    {
        return toAjax(leaveApplicationService.insertLeaveApplication(leaveApplication));
    }

    /**
     * 修改学生请假信息
     */
//    @PreAuthorize("@ss.hasPermi('leaveApplication:leave:edit')")
    @Log(title = "学生请假信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LeaveApplication leaveApplication)
    {
        return toAjax(leaveApplicationService.updateLeaveApplication(leaveApplication));
    }

    /**
     * 删除学生请假信息
     */
//    @PreAuthorize("@ss.hasPermi('leaveApplication:leave:remove')")
    @Log(title = "学生请假信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(leaveApplicationService.deleteLeaveApplicationByIds(ids));
    }
}
