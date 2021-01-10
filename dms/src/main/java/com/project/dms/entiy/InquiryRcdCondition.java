package com.project.dms.entiy;

import java.io.Serializable;
import java.util.Date;

public class InquiryRcdCondition implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inquiry_rcd_condition.CONDITION_SUB_ID
     *
     * @mbggenerated
     */
    private Integer conditionSubId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inquiry_rcd_condition.INQUIRY_ID
     *
     * @mbggenerated
     */
    private Integer inquiryId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inquiry_rcd_condition.CONDITION_ID
     *
     * @mbggenerated
     */
    private Integer conditionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inquiry_rcd_condition.SYMPTOMS_SEQ
     *
     * @mbggenerated
     */
    private Integer symptomsSeq;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column inquiry_rcd_condition.CREATE_TIME
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table inquiry_rcd_condition
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inquiry_rcd_condition.CONDITION_SUB_ID
     *
     * @return the value of inquiry_rcd_condition.CONDITION_SUB_ID
     *
     * @mbggenerated
     */
    public Integer getConditionSubId() {
        return conditionSubId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inquiry_rcd_condition.CONDITION_SUB_ID
     *
     * @param conditionSubId the value for inquiry_rcd_condition.CONDITION_SUB_ID
     *
     * @mbggenerated
     */
    public void setConditionSubId(Integer conditionSubId) {
        this.conditionSubId = conditionSubId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inquiry_rcd_condition.INQUIRY_ID
     *
     * @return the value of inquiry_rcd_condition.INQUIRY_ID
     *
     * @mbggenerated
     */
    public Integer getInquiryId() {
        return inquiryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inquiry_rcd_condition.INQUIRY_ID
     *
     * @param inquiryId the value for inquiry_rcd_condition.INQUIRY_ID
     *
     * @mbggenerated
     */
    public void setInquiryId(Integer inquiryId) {
        this.inquiryId = inquiryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inquiry_rcd_condition.CONDITION_ID
     *
     * @return the value of inquiry_rcd_condition.CONDITION_ID
     *
     * @mbggenerated
     */
    public Integer getConditionId() {
        return conditionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inquiry_rcd_condition.CONDITION_ID
     *
     * @param conditionId the value for inquiry_rcd_condition.CONDITION_ID
     *
     * @mbggenerated
     */
    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inquiry_rcd_condition.SYMPTOMS_SEQ
     *
     * @return the value of inquiry_rcd_condition.SYMPTOMS_SEQ
     *
     * @mbggenerated
     */
    public Integer getSymptomsSeq() {
        return symptomsSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inquiry_rcd_condition.SYMPTOMS_SEQ
     *
     * @param symptomsSeq the value for inquiry_rcd_condition.SYMPTOMS_SEQ
     *
     * @mbggenerated
     */
    public void setSymptomsSeq(Integer symptomsSeq) {
        this.symptomsSeq = symptomsSeq;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column inquiry_rcd_condition.CREATE_TIME
     *
     * @return the value of inquiry_rcd_condition.CREATE_TIME
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column inquiry_rcd_condition.CREATE_TIME
     *
     * @param createTime the value for inquiry_rcd_condition.CREATE_TIME
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table inquiry_rcd_condition
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", conditionSubId=").append(conditionSubId);
        sb.append(", inquiryId=").append(inquiryId);
        sb.append(", conditionId=").append(conditionId);
        sb.append(", symptomsSeq=").append(symptomsSeq);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}