package com.pku.xinfeng.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperLogExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table oper_log
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table oper_log
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table oper_log
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public OperLogExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table oper_log
	 * @mbggenerated
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andOper_typeIsNull() {
			addCriterion("oper_type is null");
			return (Criteria) this;
		}

		public Criteria andOper_typeIsNotNull() {
			addCriterion("oper_type is not null");
			return (Criteria) this;
		}

		public Criteria andOper_typeEqualTo(String value) {
			addCriterion("oper_type =", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeNotEqualTo(String value) {
			addCriterion("oper_type <>", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeGreaterThan(String value) {
			addCriterion("oper_type >", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeGreaterThanOrEqualTo(String value) {
			addCriterion("oper_type >=", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeLessThan(String value) {
			addCriterion("oper_type <", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeLessThanOrEqualTo(String value) {
			addCriterion("oper_type <=", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeLike(String value) {
			addCriterion("oper_type like", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeNotLike(String value) {
			addCriterion("oper_type not like", value, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeIn(List<String> values) {
			addCriterion("oper_type in", values, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeNotIn(List<String> values) {
			addCriterion("oper_type not in", values, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeBetween(String value1, String value2) {
			addCriterion("oper_type between", value1, value2, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_typeNotBetween(String value1, String value2) {
			addCriterion("oper_type not between", value1, value2, "oper_type");
			return (Criteria) this;
		}

		public Criteria andOper_dataIsNull() {
			addCriterion("oper_data is null");
			return (Criteria) this;
		}

		public Criteria andOper_dataIsNotNull() {
			addCriterion("oper_data is not null");
			return (Criteria) this;
		}

		public Criteria andOper_dataEqualTo(String value) {
			addCriterion("oper_data =", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataNotEqualTo(String value) {
			addCriterion("oper_data <>", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataGreaterThan(String value) {
			addCriterion("oper_data >", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataGreaterThanOrEqualTo(String value) {
			addCriterion("oper_data >=", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataLessThan(String value) {
			addCriterion("oper_data <", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataLessThanOrEqualTo(String value) {
			addCriterion("oper_data <=", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataLike(String value) {
			addCriterion("oper_data like", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataNotLike(String value) {
			addCriterion("oper_data not like", value, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataIn(List<String> values) {
			addCriterion("oper_data in", values, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataNotIn(List<String> values) {
			addCriterion("oper_data not in", values, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataBetween(String value1, String value2) {
			addCriterion("oper_data between", value1, value2, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_dataNotBetween(String value1, String value2) {
			addCriterion("oper_data not between", value1, value2, "oper_data");
			return (Criteria) this;
		}

		public Criteria andOper_timeIsNull() {
			addCriterion("oper_time is null");
			return (Criteria) this;
		}

		public Criteria andOper_timeIsNotNull() {
			addCriterion("oper_time is not null");
			return (Criteria) this;
		}

		public Criteria andOper_timeEqualTo(Date value) {
			addCriterion("oper_time =", value, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeNotEqualTo(Date value) {
			addCriterion("oper_time <>", value, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeGreaterThan(Date value) {
			addCriterion("oper_time >", value, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeGreaterThanOrEqualTo(Date value) {
			addCriterion("oper_time >=", value, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeLessThan(Date value) {
			addCriterion("oper_time <", value, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeLessThanOrEqualTo(Date value) {
			addCriterion("oper_time <=", value, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeIn(List<Date> values) {
			addCriterion("oper_time in", values, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeNotIn(List<Date> values) {
			addCriterion("oper_time not in", values, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeBetween(Date value1, Date value2) {
			addCriterion("oper_time between", value1, value2, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_timeNotBetween(Date value1, Date value2) {
			addCriterion("oper_time not between", value1, value2, "oper_time");
			return (Criteria) this;
		}

		public Criteria andOper_user_idIsNull() {
			addCriterion("oper_user_id is null");
			return (Criteria) this;
		}

		public Criteria andOper_user_idIsNotNull() {
			addCriterion("oper_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andOper_user_idEqualTo(String value) {
			addCriterion("oper_user_id =", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idNotEqualTo(String value) {
			addCriterion("oper_user_id <>", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idGreaterThan(String value) {
			addCriterion("oper_user_id >", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idGreaterThanOrEqualTo(String value) {
			addCriterion("oper_user_id >=", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idLessThan(String value) {
			addCriterion("oper_user_id <", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idLessThanOrEqualTo(String value) {
			addCriterion("oper_user_id <=", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idLike(String value) {
			addCriterion("oper_user_id like", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idNotLike(String value) {
			addCriterion("oper_user_id not like", value, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idIn(List<String> values) {
			addCriterion("oper_user_id in", values, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idNotIn(List<String> values) {
			addCriterion("oper_user_id not in", values, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idBetween(String value1, String value2) {
			addCriterion("oper_user_id between", value1, value2, "oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_user_idNotBetween(String value1, String value2) {
			addCriterion("oper_user_id not between", value1, value2,
					"oper_user_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idIsNull() {
			addCriterion("oper_equi_id is null");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idIsNotNull() {
			addCriterion("oper_equi_id is not null");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idEqualTo(String value) {
			addCriterion("oper_equi_id =", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idNotEqualTo(String value) {
			addCriterion("oper_equi_id <>", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idGreaterThan(String value) {
			addCriterion("oper_equi_id >", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idGreaterThanOrEqualTo(String value) {
			addCriterion("oper_equi_id >=", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idLessThan(String value) {
			addCriterion("oper_equi_id <", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idLessThanOrEqualTo(String value) {
			addCriterion("oper_equi_id <=", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idLike(String value) {
			addCriterion("oper_equi_id like", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idNotLike(String value) {
			addCriterion("oper_equi_id not like", value, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idIn(List<String> values) {
			addCriterion("oper_equi_id in", values, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idNotIn(List<String> values) {
			addCriterion("oper_equi_id not in", values, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idBetween(String value1, String value2) {
			addCriterion("oper_equi_id between", value1, value2, "oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_equi_idNotBetween(String value1, String value2) {
			addCriterion("oper_equi_id not between", value1, value2,
					"oper_equi_id");
			return (Criteria) this;
		}

		public Criteria andOper_levelIsNull() {
			addCriterion("oper_level is null");
			return (Criteria) this;
		}

		public Criteria andOper_levelIsNotNull() {
			addCriterion("oper_level is not null");
			return (Criteria) this;
		}

		public Criteria andOper_levelEqualTo(String value) {
			addCriterion("oper_level =", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelNotEqualTo(String value) {
			addCriterion("oper_level <>", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelGreaterThan(String value) {
			addCriterion("oper_level >", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelGreaterThanOrEqualTo(String value) {
			addCriterion("oper_level >=", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelLessThan(String value) {
			addCriterion("oper_level <", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelLessThanOrEqualTo(String value) {
			addCriterion("oper_level <=", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelLike(String value) {
			addCriterion("oper_level like", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelNotLike(String value) {
			addCriterion("oper_level not like", value, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelIn(List<String> values) {
			addCriterion("oper_level in", values, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelNotIn(List<String> values) {
			addCriterion("oper_level not in", values, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelBetween(String value1, String value2) {
			addCriterion("oper_level between", value1, value2, "oper_level");
			return (Criteria) this;
		}

		public Criteria andOper_levelNotBetween(String value1, String value2) {
			addCriterion("oper_level not between", value1, value2, "oper_level");
			return (Criteria) this;
		}

		public Criteria andNoteIsNull() {
			addCriterion("note is null");
			return (Criteria) this;
		}

		public Criteria andNoteIsNotNull() {
			addCriterion("note is not null");
			return (Criteria) this;
		}

		public Criteria andNoteEqualTo(String value) {
			addCriterion("note =", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotEqualTo(String value) {
			addCriterion("note <>", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteGreaterThan(String value) {
			addCriterion("note >", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteGreaterThanOrEqualTo(String value) {
			addCriterion("note >=", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLessThan(String value) {
			addCriterion("note <", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLessThanOrEqualTo(String value) {
			addCriterion("note <=", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteLike(String value) {
			addCriterion("note like", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotLike(String value) {
			addCriterion("note not like", value, "note");
			return (Criteria) this;
		}

		public Criteria andNoteIn(List<String> values) {
			addCriterion("note in", values, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotIn(List<String> values) {
			addCriterion("note not in", values, "note");
			return (Criteria) this;
		}

		public Criteria andNoteBetween(String value1, String value2) {
			addCriterion("note between", value1, value2, "note");
			return (Criteria) this;
		}

		public Criteria andNoteNotBetween(String value1, String value2) {
			addCriterion("note not between", value1, value2, "note");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table oper_log
	 * @mbggenerated
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table oper_log
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}