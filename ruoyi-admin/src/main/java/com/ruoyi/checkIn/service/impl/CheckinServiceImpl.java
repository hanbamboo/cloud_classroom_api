package com.ruoyi.checkIn.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.checkIn.mapper.CheckinMapper;
import com.ruoyi.checkIn.domain.Checkin;
import com.ruoyi.checkIn.service.ICheckinService;

/**
 * 签到信息Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-26
 */
@Service
public class CheckinServiceImpl implements ICheckinService 
{
    @Autowired
    private CheckinMapper checkinMapper;

    /**
     * 查询签到信息
     * 
     * @param id 签到信息主键
     * @return 签到信息
     */
    @Override
    public Checkin selectCheckinById(String id)
    {
        return checkinMapper.selectCheckinById(id);
    }

    /**
     * 查询签到信息列表
     * 
     * @param checkin 签到信息
     * @return 签到信息
     */
    @Override
    public List<Checkin> selectCheckinList(Checkin checkin)
    {
        return checkinMapper.selectCheckinList(checkin);
    }

    /**
     * 新增签到信息
     * 
     * @param checkin 签到信息
     * @return 结果
     */
    @Override
    public int insertCheckin(Checkin checkin)
    {
        checkin.setCreateTime(DateUtils.getNowDate());
        return checkinMapper.insertCheckin(checkin);
    }

    /**
     * 修改签到信息
     * 
     * @param checkin 签到信息
     * @return 结果
     */
    @Override
    public int updateCheckin(Checkin checkin)
    {
        checkin.setUpdateTime(DateUtils.getNowDate());
        return checkinMapper.updateCheckin(checkin);
    }

    /**
     * 批量删除签到信息
     * 
     * @param ids 需要删除的签到信息主键
     * @return 结果
     */
    @Override
    public int deleteCheckinByIds(String[] ids)
    {
        return checkinMapper.deleteCheckinByIds(ids);
    }

    /**
     * 删除签到信息信息
     * 
     * @param id 签到信息主键
     * @return 结果
     */
    @Override
    public int deleteCheckinById(String id)
    {
        return checkinMapper.deleteCheckinById(id);
    }
}
