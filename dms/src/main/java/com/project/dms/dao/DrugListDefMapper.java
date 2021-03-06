package com.project.dms.dao;

import com.project.dms.entiy.DrugListDef;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface DrugListDefMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_list_def
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer drugId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_list_def
     *
     * @mbggenerated
     */
    int insert(DrugListDef record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_list_def
     *
     * @mbggenerated
     */
    DrugListDef selectByPrimaryKey(Integer drugId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_list_def
     *
     * @mbggenerated
     */
    List<DrugListDef> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table drug_list_def
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(DrugListDef record);
}