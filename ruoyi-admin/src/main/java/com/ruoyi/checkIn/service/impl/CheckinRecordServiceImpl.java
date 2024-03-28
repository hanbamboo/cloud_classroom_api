package com.ruoyi.checkIn.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.checkIn.mapper.CheckinRecordMapper;
import com.ruoyi.checkIn.domain.CheckinRecord;
import com.ruoyi.checkIn.service.ICheckinRecordService;

/**
 * 签到明细Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class CheckinRecordServiceImpl implements ICheckinRecordService 
{
    @Autowired
    private CheckinRecordMapper checkinRecordMapper;

    /**
     * 查询签到明细
     * 
     * @param id 签到明细主键
     * @return 签到明细
     */
    @Override
    public CheckinRecord selectCheckinRecordById(Long id)
    {
        return checkinRecordMapper.selectCheckinRecordById(id);
    }

    /**
     * 查询签到明细列表
     * 
     * @param checkinRecord 签到明细
     * @return 签到明细
     */
    @Override
    public List<CheckinRecord> selectCheckinRecordList(CheckinRecord checkinRecord)
    {
        return checkinRecordMapper.selectCheckinRecordList(checkinRecord);
    }

    /**
     * 新增签到明细
     * 
     * @param checkinRecord 签到明细
     * @return 结果
     */
    @Override
    public int insertCheckinRecord(CheckinRecord checkinRecord)
    {
        checkinRecord.setCreateTime(DateUtils.getNowDate());
        return checkinRecordMapper.insertCheckinRecord(checkinRecord);
    }

    /**
     * 修改签到明细
     * 
     * @param checkinRecord 签到明细
     * @return 结果
     */
    @Override
    public int updateCheckinRecord(CheckinRecord checkinRecord)
    {
        checkinRecord.setUpdateTime(DateUtils.getNowDate());
        return checkinRecordMapper.updateCheckinRecord(checkinRecord);
    }

    /**
     * 批量删除签到明细
     * 
     * @param ids 需要删除的签到明细主键
     * @return 结果
     */
    @Override
    public int deleteCheckinRecordByIds(Long[] ids)
    {
        return checkinRecordMapper.deleteCheckinRecordByIds(ids);
    }

    /**
     * 删除签到明细信息
     * 
     * @param id 签到明细主键
     * @return 结果
     */
    @Override
    public int deleteCheckinRecordById(Long id)
    {
        return checkinRecordMapper.deleteCheckinRecordById(id);
    }
}
