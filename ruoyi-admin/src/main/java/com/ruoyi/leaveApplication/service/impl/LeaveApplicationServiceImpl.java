package com.ruoyi.leaveApplication.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.approval.domain.ApprovalRecord;
import com.ruoyi.approval.mapper.ApprovalRecordMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.leaveApplication.mapper.LeaveApplicationMapper;
import com.ruoyi.leaveApplication.domain.LeaveApplication;
import com.ruoyi.leaveApplication.service.ILeaveApplicationService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ApprovalRecordMapper approvalRecordMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public int insertLeaveApplication(LeaveApplication leaveApplication)
    {
        Date date = DateUtils.getNowDate();
        leaveApplication.setId(UUID.fastUUID().toString());
        leaveApplication.setStatus(0L);
        leaveApplication.setCreateTime(date);
        leaveApplication.setApplyTime(date);
        JSONArray approver = leaveApplication.getApprover();
        for (int i = 0; i < approver.size(); i++) {
            if(approver.get(i)!=null){
                Map<String,Object> object = (Map<String, Object>) approver.get(i);
                if(object.get("id")!=null){
                    ApprovalRecord approvalRecord = new ApprovalRecord();
                    approvalRecord.setLeaveId(leaveApplication.getId());
                    approvalRecord.setStatus(0L);
                    approvalRecord.setApproverId(Long.parseLong(String.valueOf(object.get("id"))));
                   if(i==0){
                       Map<String,Object> forwardMap =  (Map<String, Object>) approver.get(i+1);
                       approvalRecord.setForwardId(Long.parseLong(String.valueOf(forwardMap.get("id"))));
                   }else if (i != approver.size() - 1){
                        Map<String,Object> superiorMap =  (Map<String, Object>) approver.get(i-1);
                        Map<String,Object> forwardMap =  (Map<String, Object>) approver.get(i+1);
                        approvalRecord.setSuperiorId(Long.parseLong(String.valueOf(superiorMap.get("id"))));
                        approvalRecord.setForwardId(Long.parseLong(String.valueOf(forwardMap.get("id"))));
                    }else if (i == approver.size()-1&&!leaveApplication.getStudentId().equals(SecurityUtils.getUserId())){
                        Map<String,Object> superiorMap =  (Map<String, Object>) approver.get(i-1);
                        approvalRecord.setSuperiorId(Long.parseLong(String.valueOf(superiorMap.get("id"))));
                    }

                    approvalRecordMapper.insertApprovalRecord(approvalRecord);
                }
            }
        }

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
        leaveApplication.setUpdateBy(SecurityUtils.getUsername());
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
