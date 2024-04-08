package com.ruoyi.checkIn.mapper;

import java.util.List;
import com.ruoyi.checkIn.domain.Checkin;
import com.ruoyi.checkIn.domain.CheckinVo;

/**
 * 签到信息Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface CheckinMapper 
{
    /**
     * 查询签到信息
     * 
     * @param id 签到信息主键
     * @return 签到信息
     */
    public Checkin selectCheckinById(String id);
    public Checkin getCurrentCheckin(CheckinVo checkinVo);
    public Checkin getCurrentCheckinStudent(CheckinVo checkinVo);

    /**
     * 查询签到信息列表
     * 
     * @param checkin 签到信息
     * @return 签到信息集合
     */
    public List<Checkin> selectCheckinList(Checkin checkin);

    /**
     * 新增签到信息
     * 
     * @param checkin 签到信息
     * @return 结果
     */
    public int insertCheckin(Checkin checkin);

    /**
     * 修改签到信息
     * 
     * @param checkin 签到信息
     * @return 结果
     */
    public int updateCheckin(Checkin checkin);

    /**
     * 删除签到信息
     * 
     * @param id 签到信息主键
     * @return 结果
     */
    public int deleteCheckinById(String id);

    /**
     * 批量删除签到信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckinByIds(String[] ids);
}
