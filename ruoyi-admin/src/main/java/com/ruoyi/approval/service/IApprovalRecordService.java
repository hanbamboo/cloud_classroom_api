package com.ruoyi.approval.service;

import java.util.List;
import com.ruoyi.approval.domain.ApprovalRecord;

/**
 * 审批结果Service接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface IApprovalRecordService 
{
    /**
     * 查询审批结果
     * 
     * @param id 审批结果主键
     * @return 审批结果
     */
    public ApprovalRecord selectApprovalRecordById(Long id);

    /**
     * 查询审批结果列表
     * 
     * @param approvalRecord 审批结果
     * @return 审批结果集合
     */
    public List<ApprovalRecord> selectApprovalRecordList(ApprovalRecord approvalRecord);
    public List<ApprovalRecord> getApprovalRecordApp(ApprovalRecord approvalRecord);

    /**
     * 新增审批结果
     * 
     * @param approvalRecord 审批结果
     * @return 结果
     */
    public int insertApprovalRecord(ApprovalRecord approvalRecord);
    public Long getApprovalRecordNumById(Long id);

    /**
     * 修改审批结果
     * 
     * @param approvalRecord 审批结果
     * @return 结果
     */
    public int updateApprovalRecord(ApprovalRecord approvalRecord);

    /**
     * 批量删除审批结果
     * 
     * @param ids 需要删除的审批结果主键集合
     * @return 结果
     */
    public int deleteApprovalRecordByIds(Long[] ids);

    /**
     * 删除审批结果信息
     * 
     * @param id 审批结果主键
     * @return 结果
     */
    public int deleteApprovalRecordById(Long id);
}
