package com.ruoyi.checkIn.service;

import java.util.List;
import com.ruoyi.checkIn.domain.Checkin;
import com.ruoyi.checkIn.domain.CheckinHistoryVo;
import com.ruoyi.checkIn.domain.CheckinVo;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * 签到信息Service接口
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
public interface ICheckinService 
{
    /**
     * 查询签到信息
     * 
     * @param id 签到信息主键
     * @return 签到信息
     */
    public Checkin selectCheckinById(String id);

    /**
     * 查询当前时间中的签到信息
     *
     */
    public CheckinVo getCurrentCheckin(CheckinVo checkin);

    /**
     * 查询签到信息列表
     * 
     * @param checkin 签到信息
     * @return 签到信息集合
     */
    public List<Checkin> selectCheckinList(Checkin checkin);
    public List<CheckinHistoryVo> selectCheckinListApp(Checkin checkin);

    /**
     * 新增签到信息
     * 
     * @param checkin 签到信息
     * @return 结果
     */
    public AjaxResult insertCheckin(Checkin checkin);

    /**
     * 修改签到信息
     * 
     * @param checkin 签到信息
     * @return 结果
     */
    public int updateCheckin(Checkin checkin);
    public int cancleCheckin(Checkin checkin);

    /**
     * 批量删除签到信息
     * 
     * @param ids 需要删除的签到信息主键集合
     * @return 结果
     */
    public int deleteCheckinByIds(String[] ids);

    /**
     * 删除签到信息信息
     * 
     * @param id 签到信息主键
     * @return 结果
     */
    public int deleteCheckinById(String id);
}
