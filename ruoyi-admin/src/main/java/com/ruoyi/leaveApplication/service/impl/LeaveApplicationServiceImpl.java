package com.ruoyi.leaveApplication.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.leaveApplication.mapper.LeaveApplicationMapper;
import com.ruoyi.leaveApplication.domain.LeaveApplication;
import com.ruoyi.leaveApplication.service.ILeaveApplicationService;

/**
 * 学生请假信息Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class LeaveApplicationServiceImpl implements ILeaveApplicationService 
{
    @Autowired
    private LeaveApplicationMapper leaveApplicationMapper;

    /**
     * 查询学生请假信息
     * 
     * @param id 学生请假信息主键
     * @return 学生请假信息
     */
    @Override
    public LeaveApplication selectLeaveApplicationById(Long id)
    {
        return leaveApplicationMapper.selectLeaveApplicationById(id);
    }

    /**
     * 查询学生请假信息列表
     * 
     * @param leaveApplication 学生请假信息
     * @return 学生请假信息
     */
    @Override
    public List<LeaveApplication> selectLeaveApplicationList(LeaveApplication leaveApplication)
    {
        return leaveApplicationMapper.selectLeaveApplicationList(leaveApplication);
    }

    /**
     * 新增学生请假信息
     * 
     * @param leaveApplication 学生请假信息
     * @return 结果
     */
    @Override
    public int insertLeaveApplication(LeaveApplication leaveApplication)
    {
        leaveApplication.setCreateTime(DateUtils.getNowDate());
        return leaveApplicationMapper.insertLeaveApplication(leaveApplication);
    }

    /**
     * 修改学生请假信息
     * 
     * @param leaveApplication 学生请假信息
     * @return 结果
     */
    @Override
    public int updateLeaveApplication(LeaveApplication leaveApplication)
    {
        leaveApplication.setUpdateTime(DateUtils.getNowDate());
        return leaveApplicationMapper.updateLeaveApplication(leaveApplication);
    }

    /**
     * 批量删除学生请假信息
     * 
     * @param ids 需要删除的学生请假信息主键
     * @return 结果
     */
    @Override
    public int deleteLeaveApplicationByIds(Long[] ids)
    {
        return leaveApplicationMapper.deleteLeaveApplicationByIds(ids);
    }

    /**
     * 删除学生请假信息信息
     * 
     * @param id 学生请假信息主键
     * @return 结果
     */
    @Override
    public int deleteLeaveApplicationById(Long id)
    {
        return leaveApplicationMapper.deleteLeaveApplicationById(id);
    }
}
