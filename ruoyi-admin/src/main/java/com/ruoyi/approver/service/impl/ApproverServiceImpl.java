package com.ruoyi.approver.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.approver.mapper.ApproverMapper;
import com.ruoyi.approver.domain.Approver;
import com.ruoyi.approver.service.IApproverService;

/**
 * 审批人管理Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class ApproverServiceImpl implements IApproverService 
{
    @Autowired
    private ApproverMapper approverMapper;

    /**
     * 查询审批人管理
     * 
     * @param id 审批人管理主键
     * @return 审批人管理
     */
    @Override
    public Approver selectApproverById(Long id)
    {
        return approverMapper.selectApproverById(id);
    }

    /**
     * 查询审批人管理列表
     * 
     * @param approver 审批人管理
     * @return 审批人管理
     */
    @Override
    public List<Approver> selectApproverList(Approver approver)
    {
        return approverMapper.selectApproverList(approver);
    }

    /**
     * 新增审批人管理
     * 
     * @param approver 审批人管理
     * @return 结果
     */
    @Override
    public int insertApprover(Approver approver)
    {
        approver.setCreateTime(DateUtils.getNowDate());
        return approverMapper.insertApprover(approver);
    }

    /**
     * 修改审批人管理
     * 
     * @param approver 审批人管理
     * @return 结果
     */
    @Override
    public int updateApprover(Approver approver)
    {
        approver.setUpdateTime(DateUtils.getNowDate());
        return approverMapper.updateApprover(approver);
    }

    /**
     * 批量删除审批人管理
     * 
     * @param ids 需要删除的审批人管理主键
     * @return 结果
     */
    @Override
    public int deleteApproverByIds(Long[] ids)
    {
        return approverMapper.deleteApproverByIds(ids);
    }

    /**
     * 删除审批人管理信息
     * 
     * @param id 审批人管理主键
     * @return 结果
     */
    @Override
    public int deleteApproverById(Long id)
    {
        return approverMapper.deleteApproverById(id);
    }
}
