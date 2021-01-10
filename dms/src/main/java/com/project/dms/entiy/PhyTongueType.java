package com.project.dms.entiy;

import java.io.Serializable;

public class PhyTongueType implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column phy_tongue_type.TONGUE_SEQ
	 * @mbggenerated
	 */
	private Integer tongueSeq;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column phy_tongue_type.TONGUE_PRI
	 * @mbggenerated
	 */
	private Integer tonguePri;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column phy_tongue_type.TONGUE_COLOR
	 * @mbggenerated
	 */
	private String tongueColor;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table phy_tongue_type
	 * @mbggenerated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column phy_tongue_type.TONGUE_SEQ
	 * @return  the value of phy_tongue_type.TONGUE_SEQ
	 * @mbggenerated
	 */
	public Integer getTongueSeq() {
		return tongueSeq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column phy_tongue_type.TONGUE_SEQ
	 * @param tongueSeq  the value for phy_tongue_type.TONGUE_SEQ
	 * @mbggenerated
	 */
	public void setTongueSeq(Integer tongueSeq) {
		this.tongueSeq = tongueSeq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column phy_tongue_type.TONGUE_PRI
	 * @return  the value of phy_tongue_type.TONGUE_PRI
	 * @mbggenerated
	 */
	public Integer getTonguePri() {
		return tonguePri;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column phy_tongue_type.TONGUE_PRI
	 * @param tonguePri  the value for phy_tongue_type.TONGUE_PRI
	 * @mbggenerated
	 */
	public void setTonguePri(Integer tonguePri) {
		this.tonguePri = tonguePri;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column phy_tongue_type.TONGUE_COLOR
	 * @return  the value of phy_tongue_type.TONGUE_COLOR
	 * @mbggenerated
	 */
	public String getTongueColor() {
		return tongueColor;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column phy_tongue_type.TONGUE_COLOR
	 * @param tongueColor  the value for phy_tongue_type.TONGUE_COLOR
	 * @mbggenerated
	 */
	public void setTongueColor(String tongueColor) {
		this.tongueColor = tongueColor == null ? null : tongueColor.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table phy_tongue_type
	 * @mbggenerated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", tongueSeq=").append(tongueSeq);
		sb.append(", tonguePri=").append(tonguePri);
		sb.append(", tongueColor=").append(tongueColor);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}