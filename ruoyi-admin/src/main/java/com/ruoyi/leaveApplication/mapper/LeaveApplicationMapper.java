package com.ruoyi.leaveApplication.mapper;

import java.util.List;
import com.ruoyi.leaveApplication.domain.LeaveApplication;

/**
 * 学生请假信息Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface LeaveApplicationMapper 
{
    /**
     * 查询学生请假信息
     * 
     * @param id 学生请假信息主键
     * @return 学生请假信息
     */
    public LeaveApplication selectLeaveApplicationById(Long id);

    /**
     * 查询学生请假信息列表
     * 
     * @param leaveApplication 学生请假信息
     * @return 学生请假信息集合
     */
    public List<LeaveApplication> selectLeaveApplicationList(LeaveApplication leaveApplication);

    /**
     * 新增学生请假信息
     * 
     * @param leaveApplication 学生请假信息
     * @return 结果
     */
    public int insertLeaveApplication(LeaveApplication leaveApplication);

    /**
     * 修改学生请假信息
     * 
     * @param leaveApplication 学生请假信息
     * @return 结果
     */
    public int updateLeaveApplication(LeaveApplication leaveApplication);

    /**
     * 删除学生请假信息
     * 
     * @param id 学生请假信息主键
     * @return 结果
     */
    public int deleteLeaveApplicationById(Long id);

    /**
     * 批量删除学生请假信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLeaveApplicationByIds(Long[] ids);
}
