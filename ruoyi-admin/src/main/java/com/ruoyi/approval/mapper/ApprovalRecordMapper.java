package com.ruoyi.approval.mapper;

import java.util.List;
import com.ruoyi.approval.domain.ApprovalRecord;
import com.ruoyi.approval.domain.ApprovalRecordDTO;

/**
 * 审批结果Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface ApprovalRecordMapper 
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
    public List<ApprovalRecordDTO> selectApprovalRecordListByLeaveId(String leaveId);

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
     * 删除审批结果
     * 
     * @param id 审批结果主键
     * @return 结果
     */
    public int deleteApprovalRecordById(Long id);

    /**
     * 批量删除审批结果
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApprovalRecordByIds(Long[] ids);
}
