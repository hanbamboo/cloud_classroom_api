package com.ruoyi.checkIn.controller;

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
import com.ruoyi.checkIn.domain.CheckinRecord;
import com.ruoyi.checkIn.service.ICheckinRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 签到明细Controller
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/checkIn/record")
public class CheckinRecordController extends BaseController
{
    @Autowired
    private ICheckinRecordService checkinRecordService;

    /**
     * 查询签到明细列表
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckinRecord checkinRecord)
    {
        startPage();
        List<CheckinRecord> list = checkinRecordService.selectCheckinRecordList(checkinRecord);
        return getDataTable(list);
    }

    @GetMapping("/app/list")
    public AjaxResult listApp(CheckinRecord checkinRecord)
    {
        List<CheckinRecord> list = checkinRecordService.selectCheckinRecordList(checkinRecord);
        return AjaxResult.success(list);
    }

    /**
     * 导出签到明细列表
     */
    @PreAuthorize("@ss.hasPermi('checkIn:record:export')")
    @Log(title = "签到明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckinRecord checkinRecord)
    {
        List<CheckinRecord> list = checkinRecordService.selectCheckinRecordList(checkinRecord);
        ExcelUtil<CheckinRecord> util = new ExcelUtil<CheckinRecord>(CheckinRecord.class);
        util.exportExcel(response, list, "签到明细数据");
    }

    /**
     * 获取签到明细详细信息
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkinRecordService.selectCheckinRecordById(id));
    }

    /**
     * 新增签到明细
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:record:add')")
    @Log(title = "签到明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckinRecord checkinRecord)
    {
        return toAjax(checkinRecordService.insertCheckinRecord(checkinRecord));
    }

    /**
     * 修改签到明细
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:record:edit')")
    @Log(title = "签到明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckinRecord checkinRecord)
    {
        return toAjax(checkinRecordService.updateCheckinRecord(checkinRecord));
    }

    /**
     * 删除签到明细
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:record:remove')")
    @Log(title = "签到明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkinRecordService.deleteCheckinRecordByIds(ids));
    }
}
