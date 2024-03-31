package com.ruoyi.classReocrd.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classReocrd.mapper.ClassRecordMapper;
import com.ruoyi.classReocrd.domain.ClassRecord;
import com.ruoyi.classReocrd.service.IClassRecordService;

/**
 * 班级表Service业务层处理
 * 
 * @author hanbamboo
 * @date 2024-03-27
 */
@Service
public class ClassRecordServiceImpl implements IClassRecordService 
{
    @Autowired
    private ClassRecordMapper classRecordMapper;

    /**
     * 查询班级表
     * 
     * @param id 班级表主键
     * @return 班级表
     */
    @Override
    public ClassRecord selectClassRecordById(Long id)
    {
        return classRecordMapper.selectClassRecordById(id);
    }

    /**
     * 查询班级表列表
     * 
     * @param classRecord 班级表
     * @return 班级表
     */
    @Override
    public List<ClassRecord> selectClassRecordList(ClassRecord classRecord)
    {
        return classRecordMapper.selectClassRecordList(classRecord);
    } @Override
    public List<ClassRecord> selectClassRecordListApp(ClassRecord classRecord)
    {
        return classRecordMapper.selectClassRecordListApp(classRecord);
    }

    /**
     * 新增班级表
     * 
     * @param classRecord 班级表
     * @return 结果
     */
    @Override
    public int insertClassRecord(ClassRecord classRecord)
    {
        classRecord.setCreateTime(DateUtils.getNowDate());
        return classRecordMapper.insertClassRecord(classRecord);
    }

    /**
     * 修改班级表
     * 
     * @param classRecord 班级表
     * @return 结果
     */
    @Override
    public int updateClassRecord(ClassRecord classRecord)
    {
        classRecord.setUpdateTime(DateUtils.getNowDate());
        return classRecordMapper.updateClassRecord(classRecord);
    }

    /**
     * 批量删除班级表
     * 
     * @param ids 需要删除的班级表主键
     * @return 结果
     */
    @Override
    public int deleteClassRecordByIds(Long[] ids)
    {
        return classRecordMapper.deleteClassRecordByIds(ids);
    }

    /**
     * 删除班级表信息
     * 
     * @param id 班级表主键
     * @return 结果
     */
    @Override
    public int deleteClassRecordById(Long id)
    {
        return classRecordMapper.deleteClassRecordById(id);
    }
}
