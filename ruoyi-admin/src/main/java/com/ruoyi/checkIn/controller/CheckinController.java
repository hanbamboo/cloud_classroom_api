package com.ruoyi.checkIn.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.checkIn.domain.CheckinVo;
import org.aspectj.weaver.loadtime.Aj;
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
import com.ruoyi.checkIn.domain.Checkin;
import com.ruoyi.checkIn.service.ICheckinService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 签到信息Controller
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@RestController
@RequestMapping("/checkIn/checkin")
public class CheckinController extends BaseController
{
    @Autowired
    private ICheckinService checkinService;

    /**
     * 查询签到信息列表
     */
    @PreAuthorize("@ss.hasPermi('checkIn:checkin:list')")
    @GetMapping("/list")
    public TableDataInfo list(Checkin checkin)
    {
        startPage();
        List<Checkin> list = checkinService.selectCheckinList(checkin);
        return getDataTable(list);
    }

    /**
     * 查询当前时间中的签到信息
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:checkin:list')")
    @PostMapping("/current")
    public AjaxResult current(@RequestBody CheckinVo checkin)
    {
        CheckinVo checkinData = checkinService.getCurrentCheckin(checkin);
        if(checkinData != null){
            return AjaxResult.success(checkinService.getCurrentCheckin(checkin)) ;
        }else {
            return AjaxResult.success("当前没有签到信息") ;
        }

    }


    /**
     * 导出签到信息列表
     */
    @PreAuthorize("@ss.hasPermi('checkIn:checkin:export')")
    @Log(title = "签到信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Checkin checkin)
    {
        List<Checkin> list = checkinService.selectCheckinList(checkin);
        ExcelUtil<Checkin> util = new ExcelUtil<Checkin>(Checkin.class);
        util.exportExcel(response, list, "签到信息数据");
    }

    /**
     * 获取签到信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('checkIn:checkin:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(checkinService.selectCheckinById(id));
    }

    /**
     * 新增签到信息
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:checkin:add')")
    @Log(title = "签到信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Checkin checkin)
    {
        return toAjax(checkinService.insertCheckin(checkin));
    }

    /**
     * 修改签到信息
     */
//    @PreAuthorize("@ss.hasPermi('checkIn:checkin:edit')")
    @Log(title = "签到信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Checkin checkin)
    {
        return toAjax(checkinService.updateCheckin(checkin));
    }

    /**
     * 删除签到信息
     */
    @PreAuthorize("@ss.hasPermi('checkIn:checkin:remove')")
    @Log(title = "签到信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(checkinService.deleteCheckinByIds(ids));
    }
}
