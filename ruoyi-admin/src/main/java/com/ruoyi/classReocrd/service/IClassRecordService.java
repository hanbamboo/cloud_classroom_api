package com.ruoyi.classReocrd.service;

import java.util.List;
import com.ruoyi.classReocrd.domain.ClassRecord;

/**
 * 班级表Service接口
 * 
 * @author hanbamboo
 * @date 2024-03-27
 */
public interface IClassRecordService 
{
    /**
     * 查询班级表
     * 
     * @param id 班级表主键
     * @return 班级表
     */
    public ClassRecord selectClassRecordById(Long id);

    /**
     * 查询班级表列表
     * 
     * @param classRecord 班级表
     * @return 班级表集合
     */
    public List<ClassRecord> selectClassRecordList(ClassRecord classRecord);

    /**
     * 新增班级表
     * 
     * @param classRecord 班级表
     * @return 结果
     */
    public int insertClassRecord(ClassRecord classRecord);

    /**
     * 修改班级表
     * 
     * @param classRecord 班级表
     * @return 结果
     */
    public int updateClassRecord(ClassRecord classRecord);

    /**
     * 批量删除班级表
     * 
     * @param ids 需要删除的班级表主键集合
     * @return 结果
     */
    public int deleteClassRecordByIds(Long[] ids);

    /**
     * 删除班级表信息
     * 
     * @param id 班级表主键
     * @return 结果
     */
    public int deleteClassRecordById(Long id);
}
