package com.ruoyi.checkIn.mapper;

import java.util.List;
import com.ruoyi.checkIn.domain.CheckinRecord;

/**
 * 签到明细Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface CheckinRecordMapper 
{
    /**
     * 查询签到明细
     * 
     * @param id 签到明细主键
     * @return 签到明细
     */
    public CheckinRecord selectCheckinRecordById(Long id);

    /**
     * 查询签到明细列表
     * 
     * @param checkinRecord 签到明细
     * @return 签到明细集合
     */
    public List<CheckinRecord> selectCheckinRecordList(CheckinRecord checkinRecord);

    /**
     * 新增签到明细
     * 
     * @param checkinRecord 签到明细
     * @return 结果
     */
    public int insertCheckinRecord(CheckinRecord checkinRecord);

    /**
     * 修改签到明细
     * 
     * @param checkinRecord 签到明细
     * @return 结果
     */
    public int updateCheckinRecord(CheckinRecord checkinRecord);

    /**
     * 删除签到明细
     * 
     * @param id 签到明细主键
     * @return 结果
     */
    public int deleteCheckinRecordById(Long id);

    /**
     * 批量删除签到明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckinRecordByIds(Long[] ids);
}
