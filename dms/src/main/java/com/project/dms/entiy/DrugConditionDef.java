package com.project.dms.entiy;

import java.io.Serializable;
import java.math.BigDecimal;

public class DrugConditionDef implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column drug_condition_def.CONDITION_IDX
	 * @mbggenerated
	 */
	private Integer conditionIdx;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column drug_condition_def.CONDITION_SEQ
	 * @mbggenerated
	 */
	private Integer conditionSeq;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column drug_condition_def.CONDITION_PART
	 * @mbggenerated
	 */
	private String conditionPart;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column drug_condition_def.DRUG_TYPE
	 * @mbggenerated
	 */
	private String drugType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column drug_condition_def.UNIT_QTY
	 * @mbggenerated
	 */
	private BigDecimal unitQty;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column drug_condition_def.DRUG_ID
	 * @mbggenerated
	 */
	private Integer drugId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table drug_condition_def
	 * @mbggenerated
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column drug_condition_def.CONDITION_IDX
	 * @return  the value of drug_condition_def.CONDITION_IDX
	 * @mbggenerated
	 */
	public Integer getConditionIdx() {
		return conditionIdx;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column drug_condition_def.CONDITION_IDX
	 * @param conditionIdx  the value for drug_condition_def.CONDITION_IDX
	 * @mbggenerated
	 */
	public void setConditionIdx(Integer conditionIdx) {
		this.conditionIdx = conditionIdx;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column drug_condition_def.CONDITION_SEQ
	 * @return  the value of drug_condition_def.CONDITION_SEQ
	 * @mbggenerated
	 */
	public Integer getConditionSeq() {
		return conditionSeq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column drug_condition_def.CONDITION_SEQ
	 * @param conditionSeq  the value for drug_condition_def.CONDITION_SEQ
	 * @mbggenerated
	 */
	public void setConditionSeq(Integer conditionSeq) {
		this.conditionSeq = conditionSeq;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column drug_condition_def.CONDITION_PART
	 * @return  the value of drug_condition_def.CONDITION_PART
	 * @mbggenerated
	 */
	public String getConditionPart() {
		return conditionPart;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column drug_condition_def.CONDITION_PART
	 * @param conditionPart  the value for drug_condition_def.CONDITION_PART
	 * @mbggenerated
	 */
	public void setConditionPart(String conditionPart) {
		this.conditionPart = conditionPart == null ? null : conditionPart
				.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column drug_condition_def.DRUG_TYPE
	 * @return  the value of drug_condition_def.DRUG_TYPE
	 * @mbggenerated
	 */
	public String getDrugType() {
		return drugType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column drug_condition_def.DRUG_TYPE
	 * @param drugType  the value for drug_condition_def.DRUG_TYPE
	 * @mbggenerated
	 */
	public void setDrugType(String drugType) {
		this.drugType = drugType == null ? null : drugType.trim();
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column drug_condition_def.UNIT_QTY
	 * @return  the value of drug_condition_def.UNIT_QTY
	 * @mbggenerated
	 */
	public BigDecimal getUnitQty() {
		return unitQty;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column drug_condition_def.UNIT_QTY
	 * @param unitQty  the value for drug_condition_def.UNIT_QTY
	 * @mbggenerated
	 */
	public void setUnitQty(BigDecimal unitQty) {
		this.unitQty = unitQty;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column drug_condition_def.DRUG_ID
	 * @return  the value of drug_condition_def.DRUG_ID
	 * @mbggenerated
	 */
	public Integer getDrugId() {
		return drugId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column drug_condition_def.DRUG_ID
	 * @param drugId  the value for drug_condition_def.DRUG_ID
	 * @mbggenerated
	 */
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table drug_condition_def
	 * @mbggenerated
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", conditionIdx=").append(conditionIdx);
		sb.append(", conditionSeq=").append(conditionSeq);
		sb.append(", conditionPart=").append(conditionPart);
		sb.append(", drugType=").append(drugType);
		sb.append(", unitQty=").append(unitQty);
		sb.append(", drugId=").append(drugId);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}