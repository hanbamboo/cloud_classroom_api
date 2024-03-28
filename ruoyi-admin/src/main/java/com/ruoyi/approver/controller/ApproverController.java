package com.ruoyi.approver.controller;

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
import com.ruoyi.approver.domain.Approver;
import com.ruoyi.approver.service.IApproverService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审批人管理Controller
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/approver/approver")
public class ApproverController extends BaseController
{
    @Autowired
    private IApproverService approverService;

    /**
     * 查询审批人管理列表
     */
    @PreAuthorize("@ss.hasPermi('approver:approver:list')")
    @GetMapping("/list")
    public TableDataInfo list(Approver approver)
    {
        startPage();
        List<Approver> list = approverService.selectApproverList(approver);
        return getDataTable(list);
    }

    /**
     * 导出审批人管理列表
     */
    @PreAuthorize("@ss.hasPermi('approver:approver:export')")
    @Log(title = "审批人管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Approver approver)
    {
        List<Approver> list = approverService.selectApproverList(approver);
        ExcelUtil<Approver> util = new ExcelUtil<Approver>(Approver.class);
        util.exportExcel(response, list, "审批人管理数据");
    }

    /**
     * 获取审批人管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('approver:approver:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(approverService.selectApproverById(id));
    }

    /**
     * 新增审批人管理
     */
    @PreAuthorize("@ss.hasPermi('approver:approver:add')")
    @Log(title = "审批人管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Approver approver)
    {
        return toAjax(approverService.insertApprover(approver));
    }

    /**
     * 修改审批人管理
     */
    @PreAuthorize("@ss.hasPermi('approver:approver:edit')")
    @Log(title = "审批人管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Approver approver)
    {
        return toAjax(approverService.updateApprover(approver));
    }

    /**
     * 删除审批人管理
     */
    @PreAuthorize("@ss.hasPermi('approver:approver:remove')")
    @Log(title = "审批人管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(approverService.deleteApproverByIds(ids));
    }
}
