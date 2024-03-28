package com.ruoyi.approval.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.approval.mapper.ApprovalRecordMapper;
import com.ruoyi.approval.domain.ApprovalRecord;
import com.ruoyi.approval.service.IApprovalRecordService;

/**
 * 审批结果Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class ApprovalRecordServiceImpl implements IApprovalRecordService 
{
    @Autowired
    private ApprovalRecordMapper approvalRecordMapper;

    /**
     * 查询审批结果
     * 
     * @param id 审批结果主键
     * @return 审批结果
     */
    @Override
    public ApprovalRecord selectApprovalRecordById(Long id)
    {
        return approvalRecordMapper.selectApprovalRecordById(id);
    }

    /**
     * 查询审批结果列表
     * 
     * @param approvalRecord 审批结果
     * @return 审批结果
     */
    @Override
    public List<ApprovalRecord> selectApprovalRecordList(ApprovalRecord approvalRecord)
    {
        return approvalRecordMapper.selectApprovalRecordList(approvalRecord);
    }

    /**
     * 新增审批结果
     * 
     * @param approvalRecord 审批结果
     * @return 结果
     */
    @Override
    public int insertApprovalRecord(ApprovalRecord approvalRecord)
    {
        approvalRecord.setCreateTime(DateUtils.getNowDate());
        return approvalRecordMapper.insertApprovalRecord(approvalRecord);
    }

    /**
     * 修改审批结果
     * 
     * @param approvalRecord 审批结果
     * @return 结果
     */
    @Override
    public int updateApprovalRecord(ApprovalRecord approvalRecord)
    {
        approvalRecord.setUpdateTime(DateUtils.getNowDate());
        return approvalRecordMapper.updateApprovalRecord(approvalRecord);
    }

    /**
     * 批量删除审批结果
     * 
     * @param ids 需要删除的审批结果主键
     * @return 结果
     */
    @Override
    public int deleteApprovalRecordByIds(Long[] ids)
    {
        return approvalRecordMapper.deleteApprovalRecordByIds(ids);
    }

    /**
     * 删除审批结果信息
     * 
     * @param id 审批结果主键
     * @return 结果
     */
    @Override
    public int deleteApprovalRecordById(Long id)
    {
        return approvalRecordMapper.deleteApprovalRecordById(id);
    }
}
