package com.ruoyi.classReocrd.mapper;

import java.util.List;
import com.ruoyi.classReocrd.domain.ClassRecord;

/**
 * 班级表Mapper接口
 * 
 * @author hanbamboo
 * @date 2024-03-27
 */
public interface ClassRecordMapper 
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
    public List<ClassRecord> selectClassRecordListApp(ClassRecord classRecord);

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
     * 删除班级表
     * 
     * @param id 班级表主键
     * @return 结果
     */
    public int deleteClassRecordById(Long id);

    /**
     * 批量删除班级表
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassRecordByIds(Long[] ids);
}
