package com.ruoyi.approval.controller;

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
import com.ruoyi.approval.domain.ApprovalRecord;
import com.ruoyi.approval.service.IApprovalRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审批结果Controller
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/approval/record")
public class ApprovalRecordController extends BaseController
{
    @Autowired
    private IApprovalRecordService approvalRecordService;

    /**
     * 查询审批结果列表
     */
//    @PreAuthorize("@ss.hasPermi('approval:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(ApprovalRecord approvalRecord)
    {
        startPage();
        List<ApprovalRecord> list = approvalRecordService.selectApprovalRecordList(approvalRecord);
        return getDataTable(list);
    }

    /**
     * 导出审批结果列表
     */
    @PreAuthorize("@ss.hasPermi('approval:record:export')")
    @Log(title = "审批结果", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ApprovalRecord approvalRecord)
    {
        List<ApprovalRecord> list = approvalRecordService.selectApprovalRecordList(approvalRecord);
        ExcelUtil<ApprovalRecord> util = new ExcelUtil<ApprovalRecord>(ApprovalRecord.class);
        util.exportExcel(response, list, "审批结果数据");
    }

    /**
     * 获取审批结果详细信息
     */
//    @PreAuthorize("@ss.hasPermi('approval:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(approvalRecordService.selectApprovalRecordById(id));
    }

    @GetMapping(value = "/num/{id}")
    public AjaxResult getApprovalRecordNumById(@PathVariable("id") Long id)
    {
        return AjaxResult.success("操作成功！",approvalRecordService.getApprovalRecordNumById(id));
    }

    @GetMapping(value = "/app/get")
    public AjaxResult getApprovalRecordApp(ApprovalRecord approvalRecord)
    {
        return AjaxResult.success(approvalRecordService.getApprovalRecordApp(approvalRecord));
    }

    /**
     * 新增审批结果
     */
    @PreAuthorize("@ss.hasPermi('approval:record:add')")
    @Log(title = "审批结果", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ApprovalRecord approvalRecord)
    {
        return toAjax(approvalRecordService.insertApprovalRecord(approvalRecord));
    }

    /**
     * 修改审批结果
     */
//    @PreAuthorize("@ss.hasPermi('approval:record:edit')")
    @Log(title = "审批结果", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ApprovalRecord approvalRecord)
    {
        return toAjax(approvalRecordService.updateApprovalRecord(approvalRecord));
    }

    /**
     * 删除审批结果
     */
//    @PreAuthorize("@ss.hasPermi('approval:record:remove')")
    @Log(title = "审批结果", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(approvalRecordService.deleteApprovalRecordByIds(ids));
    }
}
