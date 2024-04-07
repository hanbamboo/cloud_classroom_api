package com.ruoyi.course.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.course.domain.CourseNumStatus;
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
import com.ruoyi.course.domain.CourseRecord;
import com.ruoyi.course.service.ICourseRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 学生拥有的课程Controller
 *
 * @author hanbamboo
 * @date 2024-03-31
 */
@RestController
@RequestMapping("/course/record")
public class CourseRecordController extends BaseController {
    @Autowired
    private ICourseRecordService courseRecordService;

    /**
     * 查询学生拥有的课程列表
     */
    @PreAuthorize("@ss.hasPermi('course:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourseRecord courseRecord) {
        startPage();
        List<CourseRecord> list = courseRecordService.selectCourseRecordList(courseRecord);
        return getDataTable(list);
    }


    /**
     * 查询学生在某个课程中的人数与存在与否 选中取消 未选则选中
     */
    @PreAuthorize("@ss.hasPermi('course:record:list')")
    @PostMapping("/courseNumStatus")
    public AjaxResult courseNumStatus(@RequestBody CourseNumStatus course) {
        return AjaxResult.success(courseRecordService.getCourseNumStatus(course));
    }


    /**
     * 导出学生拥有的课程列表
     */
    @PreAuthorize("@ss.hasPermi('course:record:export')")
    @Log(title = "学生拥有的课程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseRecord courseRecord) {
        List<CourseRecord> list = courseRecordService.selectCourseRecordList(courseRecord);
        ExcelUtil<CourseRecord> util = new ExcelUtil<CourseRecord>(CourseRecord.class);
        util.exportExcel(response, list, "学生拥有的课程数据");
    }

    /**
     * 获取学生拥有的课程详细信息
     */
    @PreAuthorize("@ss.hasPermi('course:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(courseRecordService.selectCourseRecordById(id));
    }

    /**
     * 新增学生拥有的课程
     */
    @PreAuthorize("@ss.hasPermi('course:record:add')")
    @Log(title = "学生拥有的课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourseRecord courseRecord) {
        return toAjax(courseRecordService.insertCourseRecord(courseRecord));
    }

    /**
     * 根据多个id新增学生拥有的课程
     */
    @PreAuthorize("@ss.hasPermi('course:record:add')")
    @Log(title = "学生拥有的课程", businessType = BusinessType.INSERT)
    @PostMapping("/addBacth")
    public AjaxResult addWithId(@RequestBody List<CourseRecord> records) {
        return toAjax(courseRecordService.insertCourseRecordWithIds(records));
    }

    /**
     * 修改学生拥有的课程
     */
    @PreAuthorize("@ss.hasPermi('course:record:edit')")
    @Log(title = "学生拥有的课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourseRecord courseRecord) {
        return toAjax(courseRecordService.updateCourseRecord(courseRecord));
    }

    /**
     * 删除学生拥有的课程
     */
    @PreAuthorize("@ss.hasPermi('course:record:remove')")
    @Log(title = "学生拥有的课程", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(courseRecordService.deleteCourseRecordByIds(ids));
    }
}
