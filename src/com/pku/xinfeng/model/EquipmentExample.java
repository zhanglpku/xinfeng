package com.pku.xinfeng.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

public class EquipmentExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table equipment
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table equipment
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table equipment
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public EquipmentExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table equipment
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table equipment
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

		protected void addCriterionForJDBCDate(String condition, Date value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value.getTime()),
					property);
		}

		protected void addCriterionForJDBCDate(String condition,
				List<Date> values, String property) {
			if (values == null || values.size() == 0) {
				throw new RuntimeException("Value list for " + property
						+ " cannot be null or empty");
			}
			List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
			Iterator<Date> iter = values.iterator();
			while (iter.hasNext()) {
				dateList.add(new java.sql.Date(iter.next().getTime()));
			}
			addCriterion(condition, dateList, property);
		}

		protected void addCriterionForJDBCDate(String condition, Date value1,
				Date value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			addCriterion(condition, new java.sql.Date(value1.getTime()),
					new java.sql.Date(value2.getTime()), property);
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andMax_airflowIsNull() {
			addCriterion("max_airflow is null");
			return (Criteria) this;
		}

		public Criteria andMax_airflowIsNotNull() {
			addCriterion("max_airflow is not null");
			return (Criteria) this;
		}

		public Criteria andMax_airflowEqualTo(Integer value) {
			addCriterion("max_airflow =", value, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowNotEqualTo(Integer value) {
			addCriterion("max_airflow <>", value, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowGreaterThan(Integer value) {
			addCriterion("max_airflow >", value, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowGreaterThanOrEqualTo(Integer value) {
			addCriterion("max_airflow >=", value, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowLessThan(Integer value) {
			addCriterion("max_airflow <", value, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowLessThanOrEqualTo(Integer value) {
			addCriterion("max_airflow <=", value, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowIn(List<Integer> values) {
			addCriterion("max_airflow in", values, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowNotIn(List<Integer> values) {
			addCriterion("max_airflow not in", values, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowBetween(Integer value1, Integer value2) {
			addCriterion("max_airflow between", value1, value2, "max_airflow");
			return (Criteria) this;
		}

		public Criteria andMax_airflowNotBetween(Integer value1, Integer value2) {
			addCriterion("max_airflow not between", value1, value2,
					"max_airflow");
			return (Criteria) this;
		}

		public Criteria andFactory_dateIsNull() {
			addCriterion("factory_date is null");
			return (Criteria) this;
		}

		public Criteria andFactory_dateIsNotNull() {
			addCriterion("factory_date is not null");
			return (Criteria) this;
		}

		public Criteria andFactory_dateEqualTo(Date value) {
			addCriterionForJDBCDate("factory_date =", value, "factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateNotEqualTo(Date value) {
			addCriterionForJDBCDate("factory_date <>", value, "factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateGreaterThan(Date value) {
			addCriterionForJDBCDate("factory_date >", value, "factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("factory_date >=", value, "factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateLessThan(Date value) {
			addCriterionForJDBCDate("factory_date <", value, "factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("factory_date <=", value, "factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateIn(List<Date> values) {
			addCriterionForJDBCDate("factory_date in", values, "factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateNotIn(List<Date> values) {
			addCriterionForJDBCDate("factory_date not in", values,
					"factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("factory_date between", value1, value2,
					"factory_date");
			return (Criteria) this;
		}

		public Criteria andFactory_dateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("factory_date not between", value1, value2,
					"factory_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateIsNull() {
			addCriterion("install_date is null");
			return (Criteria) this;
		}

		public Criteria andInstall_dateIsNotNull() {
			addCriterion("install_date is not null");
			return (Criteria) this;
		}

		public Criteria andInstall_dateEqualTo(Date value) {
			addCriterionForJDBCDate("install_date =", value, "install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateNotEqualTo(Date value) {
			addCriterionForJDBCDate("install_date <>", value, "install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateGreaterThan(Date value) {
			addCriterionForJDBCDate("install_date >", value, "install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("install_date >=", value, "install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateLessThan(Date value) {
			addCriterionForJDBCDate("install_date <", value, "install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("install_date <=", value, "install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateIn(List<Date> values) {
			addCriterionForJDBCDate("install_date in", values, "install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateNotIn(List<Date> values) {
			addCriterionForJDBCDate("install_date not in", values,
					"install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("install_date between", value1, value2,
					"install_date");
			return (Criteria) this;
		}

		public Criteria andInstall_dateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("install_date not between", value1, value2,
					"install_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateIsNull() {
			addCriterion("repair_date is null");
			return (Criteria) this;
		}

		public Criteria andRepair_dateIsNotNull() {
			addCriterion("repair_date is not null");
			return (Criteria) this;
		}

		public Criteria andRepair_dateEqualTo(Date value) {
			addCriterionForJDBCDate("repair_date =", value, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateNotEqualTo(Date value) {
			addCriterionForJDBCDate("repair_date <>", value, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateGreaterThan(Date value) {
			addCriterionForJDBCDate("repair_date >", value, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("repair_date >=", value, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateLessThan(Date value) {
			addCriterionForJDBCDate("repair_date <", value, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("repair_date <=", value, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateIn(List<Date> values) {
			addCriterionForJDBCDate("repair_date in", values, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateNotIn(List<Date> values) {
			addCriterionForJDBCDate("repair_date not in", values, "repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("repair_date between", value1, value2,
					"repair_date");
			return (Criteria) this;
		}

		public Criteria andRepair_dateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("repair_date not between", value1, value2,
					"repair_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateIsNull() {
			addCriterion("filter_date is null");
			return (Criteria) this;
		}

		public Criteria andFilter_dateIsNotNull() {
			addCriterion("filter_date is not null");
			return (Criteria) this;
		}

		public Criteria andFilter_dateEqualTo(Date value) {
			addCriterionForJDBCDate("filter_date =", value, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateNotEqualTo(Date value) {
			addCriterionForJDBCDate("filter_date <>", value, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateGreaterThan(Date value) {
			addCriterionForJDBCDate("filter_date >", value, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateGreaterThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("filter_date >=", value, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateLessThan(Date value) {
			addCriterionForJDBCDate("filter_date <", value, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateLessThanOrEqualTo(Date value) {
			addCriterionForJDBCDate("filter_date <=", value, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateIn(List<Date> values) {
			addCriterionForJDBCDate("filter_date in", values, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateNotIn(List<Date> values) {
			addCriterionForJDBCDate("filter_date not in", values, "filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("filter_date between", value1, value2,
					"filter_date");
			return (Criteria) this;
		}

		public Criteria andFilter_dateNotBetween(Date value1, Date value2) {
			addCriterionForJDBCDate("filter_date not between", value1, value2,
					"filter_date");
			return (Criteria) this;
		}

		public Criteria andVersionIsNull() {
			addCriterion("version is null");
			return (Criteria) this;
		}

		public Criteria andVersionIsNotNull() {
			addCriterion("version is not null");
			return (Criteria) this;
		}

		public Criteria andVersionEqualTo(String value) {
			addCriterion("version =", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotEqualTo(String value) {
			addCriterion("version <>", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThan(String value) {
			addCriterion("version >", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionGreaterThanOrEqualTo(String value) {
			addCriterion("version >=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThan(String value) {
			addCriterion("version <", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLessThanOrEqualTo(String value) {
			addCriterion("version <=", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionLike(String value) {
			addCriterion("version like", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotLike(String value) {
			addCriterion("version not like", value, "version");
			return (Criteria) this;
		}

		public Criteria andVersionIn(List<String> values) {
			addCriterion("version in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotIn(List<String> values) {
			addCriterion("version not in", values, "version");
			return (Criteria) this;
		}

		public Criteria andVersionBetween(String value1, String value2) {
			addCriterion("version between", value1, value2, "version");
			return (Criteria) this;
		}

		public Criteria andVersionNotBetween(String value1, String value2) {
			addCriterion("version not between", value1, value2, "version");
			return (Criteria) this;
		}

		public Criteria andCodeIsNull() {
			addCriterion("code is null");
			return (Criteria) this;
		}

		public Criteria andCodeIsNotNull() {
			addCriterion("code is not null");
			return (Criteria) this;
		}

		public Criteria andCodeEqualTo(String value) {
			addCriterion("code =", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotEqualTo(String value) {
			addCriterion("code <>", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThan(String value) {
			addCriterion("code >", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeGreaterThanOrEqualTo(String value) {
			addCriterion("code >=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThan(String value) {
			addCriterion("code <", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLessThanOrEqualTo(String value) {
			addCriterion("code <=", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeLike(String value) {
			addCriterion("code like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotLike(String value) {
			addCriterion("code not like", value, "code");
			return (Criteria) this;
		}

		public Criteria andCodeIn(List<String> values) {
			addCriterion("code in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotIn(List<String> values) {
			addCriterion("code not in", values, "code");
			return (Criteria) this;
		}

		public Criteria andCodeBetween(String value1, String value2) {
			addCriterion("code between", value1, value2, "code");
			return (Criteria) this;
		}

		public Criteria andCodeNotBetween(String value1, String value2) {
			addCriterion("code not between", value1, value2, "code");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table equipment
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
     * This class corresponds to the database table equipment
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}