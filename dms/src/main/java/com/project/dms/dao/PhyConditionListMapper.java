package com.project.dms.dao;

import com.project.dms.entiy.PhyConditionList;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PhyConditionListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phy_condition_list
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer conditionSubIdx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phy_condition_list
     *
     * @mbggenerated
     */
    int insert(PhyConditionList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phy_condition_list
     *
     * @mbggenerated
     */
    PhyConditionList selectByPrimaryKey(Integer conditionSubIdx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phy_condition_list
     *
     * @mbggenerated
     */
    List<PhyConditionList> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phy_condition_list
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PhyConditionList record);
}