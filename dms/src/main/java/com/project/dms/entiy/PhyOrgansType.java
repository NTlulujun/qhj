package com.project.dms.entiy;

import java.io.Serializable;

public class PhyOrgansType implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phy_organs_type.ORGANS_SEQ
     *
     * @mbggenerated
     */
    private Integer organsSeq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phy_organs_type.ORGANS_TYPE
     *
     * @mbggenerated
     */
    private Integer organsType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column phy_organs_type.ORGANS_NAME
     *
     * @mbggenerated
     */
    private String organsName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table phy_organs_type
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column phy_organs_type.ORGANS_SEQ
     *
     * @return the value of phy_organs_type.ORGANS_SEQ
     *
     * @mbggenerated
     */
    public Integer getOrgansSeq() {
        return organsSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column phy_organs_type.ORGANS_SEQ
     *
     * @param organsSeq the value for phy_organs_type.ORGANS_SEQ
     *
     * @mbggenerated
     */
    public void setOrgansSeq(Integer organsSeq) {
        this.organsSeq = organsSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column phy_organs_type.ORGANS_TYPE
     *
     * @return the value of phy_organs_type.ORGANS_TYPE
     *
     * @mbggenerated
     */
    public Integer getOrgansType() {
        return organsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column phy_organs_type.ORGANS_TYPE
     *
     * @param organsType the value for phy_organs_type.ORGANS_TYPE
     *
     * @mbggenerated
     */
    public void setOrgansType(Integer organsType) {
        this.organsType = organsType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column phy_organs_type.ORGANS_NAME
     *
     * @return the value of phy_organs_type.ORGANS_NAME
     *
     * @mbggenerated
     */
    public String getOrgansName() {
        return organsName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column phy_organs_type.ORGANS_NAME
     *
     * @param organsName the value for phy_organs_type.ORGANS_NAME
     *
     * @mbggenerated
     */
    public void setOrgansName(String organsName) {
        this.organsName = organsName == null ? null : organsName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table phy_organs_type
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organsSeq=").append(organsSeq);
        sb.append(", organsType=").append(organsType);
        sb.append(", organsName=").append(organsName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}