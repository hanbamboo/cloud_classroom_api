package com.ruoyi.approver.mapper;

import java.util.List;
import com.ruoyi.approver.domain.Approver;

/**
 * 审批人管理Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface ApproverMapper 
{
    /**
     * 查询审批人管理
     * 
     * @param id 审批人管理主键
     * @return 审批人管理
     */
    public Approver selectApproverById(Long id);

    /**
     * 查询审批人管理列表
     * 
     * @param approver 审批人管理
     * @return 审批人管理集合
     */
    public List<Approver> selectApproverList(Approver approver);

    /**
     * 新增审批人管理
     * 
     * @param approver 审批人管理
     * @return 结果
     */
    public int insertApprover(Approver approver);

    /**
     * 修改审批人管理
     * 
     * @param approver 审批人管理
     * @return 结果
     */
    public int updateApprover(Approver approver);

    /**
     * 删除审批人管理
     * 
     * @param id 审批人管理主键
     * @return 结果
     */
    public int deleteApproverById(Long id);

    /**
     * 批量删除审批人管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApproverByIds(Long[] ids);
}
