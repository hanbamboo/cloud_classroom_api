package com.ruoyi.classReocrd.controller;

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
import com.ruoyi.classReocrd.domain.ClassRecord;
import com.ruoyi.classReocrd.service.IClassRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 班级表Controller
 * 
 * @author hanbamboo
 * @date 2024-03-27
 */
@RestController
@RequestMapping("/class/record")
public class ClassRecordController extends BaseController
{
    @Autowired
    private IClassRecordService classRecordService;

    /**
     * 查询班级表列表
     */
    @PreAuthorize("@ss.hasPermi('class:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(ClassRecord classRecord)
    {
        startPage();
        List<ClassRecord> list = classRecordService.selectClassRecordList(classRecord);
        return getDataTable(list);
    }

    /**
     * 导出班级表列表
     */
    @PreAuthorize("@ss.hasPermi('class:record:export')")
    @Log(title = "班级表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ClassRecord classRecord)
    {
        List<ClassRecord> list = classRecordService.selectClassRecordList(classRecord);
        ExcelUtil<ClassRecord> util = new ExcelUtil<ClassRecord>(ClassRecord.class);
        util.exportExcel(response, list, "班级表数据");
    }

    /**
     * 获取班级表详细信息
     */
    @PreAuthorize("@ss.hasPermi('class:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(classRecordService.selectClassRecordById(id));
    }

    /**
     * 新增班级表
     */
    @PreAuthorize("@ss.hasPermi('class:record:add')")
    @Log(title = "班级表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ClassRecord classRecord)
    {
        return toAjax(classRecordService.insertClassRecord(classRecord));
    }

    /**
     * 修改班级表
     */
    @PreAuthorize("@ss.hasPermi('class:record:edit')")
    @Log(title = "班级表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ClassRecord classRecord)
    {
        return toAjax(classRecordService.updateClassRecord(classRecord));
    }

    /**
     * 删除班级表
     */
    @PreAuthorize("@ss.hasPermi('class:record:remove')")
    @Log(title = "班级表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(classRecordService.deleteClassRecordByIds(ids));
    }
}
