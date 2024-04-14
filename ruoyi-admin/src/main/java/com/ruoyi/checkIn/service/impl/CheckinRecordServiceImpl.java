package com.ruoyi.checkIn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.checkIn.domain.Checkin;
import com.ruoyi.checkIn.domain.CheckinInsertVo;
import com.ruoyi.checkIn.domain.CheckinVo;
import com.ruoyi.checkIn.mapper.CheckinMapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.websocket.server.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.checkIn.mapper.CheckinRecordMapper;
import com.ruoyi.checkIn.domain.CheckinRecord;
import com.ruoyi.checkIn.service.ICheckinRecordService;
import org.springframework.transaction.annotation.Transactional;

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
    private CheckinMapper checkinMapper;

    @Autowired
    private CheckinRecordMapper checkinRecordMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private WebSocketServer webSocketServer;

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

    @Override
    public CheckinRecord selectCheckinRecordByCheckinIdAndStudentId(String checkinId, Long studentId) {
        return checkinRecordMapper.selectCheckinRecordByCheckinIdAndStudentId(checkinId,studentId);
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

    @Override
    public List<CheckinRecord> selectCheckinRecordListRecord(CheckinVo checkinVo) {
        return checkinRecordMapper.selectCheckinRecordListRecord(checkinVo);
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AjaxResult appRecordCheckin(CheckinInsertVo checkinInsertVo) {
        Checkin checkin = checkinMapper.selectCheckinById(checkinInsertVo.getCheckinId());
        if(checkin==null){
            return AjaxResult.error("无法签到，签到数据错误！");
        }else{
            if(!Objects.equals(checkin.getMethod(), checkinInsertVo.getMethod())){
                return AjaxResult.error("无法签到，签到数据错误！");
            }else{
                Date date = DateUtils.getNowDate();
                if(date.before(checkin.getStartTime())){
                    return AjaxResult.error("无法签到，签到时间未到！");
                }
                SysUser user = SecurityUtils.getLoginUser().getUser();
                CheckinRecord checkinRecord = new CheckinRecord();
                checkinRecord.setCheckinId(checkinInsertVo.getCheckinId());
                checkinRecord.setCheckinTime(date);
                checkinRecord.setDevice(checkinInsertVo.getDevice());
                checkinRecord.setStudentId(checkinInsertVo.getStudentId());
                checkinRecord.setCreateTime(date);
                checkinRecord.setCreateBy(user.getUserName());
                if(date.after(checkin.getEndTime())){
                    checkinRecord.setStatus(2L);
                }else{
                    checkinRecord.setStatus(1L);
                }
                JSONObject result =  webSocketServer.addOnlineCheckinMember(checkin.getId(), checkinInsertVo.getStudentId(),date,user.getNickName(),user.getAvatar());
                checkinRecordMapper.insertCheckinRecord(checkinRecord);

                if(result!=null&&result.getBooleanValue("result")){
                    if(date.after(checkin.getEndTime())){
                        return AjaxResult.success("签到成功！（迟到）");
                    }
                    return AjaxResult.success("签到成功！");
                }else{
                    assert result != null;
                    return AjaxResult.error(result.getString("reason"));
                }

            }
        }
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
