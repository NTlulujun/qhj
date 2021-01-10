package com.project.dms.dao;

import com.project.dms.entiy.PhyTongueType;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface PhyTongueTypeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table phy_tongue_type
	 * @mbggenerated
	 */
	int deleteByPrimaryKey(Integer tongueSeq);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table phy_tongue_type
	 * @mbggenerated
	 */
	int insert(PhyTongueType record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table phy_tongue_type
	 * @mbggenerated
	 */
	PhyTongueType selectByPrimaryKey(Integer tongueSeq);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table phy_tongue_type
	 * @mbggenerated
	 */
	List<PhyTongueType> selectAll();

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table phy_tongue_type
	 * @mbggenerated
	 */
	int updateByPrimaryKey(PhyTongueType record);
}