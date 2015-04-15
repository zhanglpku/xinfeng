package com.pku.xinfeng.model;

import java.util.ArrayList;
import java.util.List;

public class CityCodeExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table city_code
	 * @mbggenerated
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table city_code
	 * @mbggenerated
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table city_code
	 * @mbggenerated
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public CityCodeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
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
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table city_code
	 * @mbggenerated
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table city_code
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

		public Criteria andCityNameIsNull() {
			addCriterion("cityName is null");
			return (Criteria) this;
		}

		public Criteria andCityNameIsNotNull() {
			addCriterion("cityName is not null");
			return (Criteria) this;
		}

		public Criteria andCityNameEqualTo(String value) {
			addCriterion("cityName =", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotEqualTo(String value) {
			addCriterion("cityName <>", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameGreaterThan(String value) {
			addCriterion("cityName >", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameGreaterThanOrEqualTo(String value) {
			addCriterion("cityName >=", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameLessThan(String value) {
			addCriterion("cityName <", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameLessThanOrEqualTo(String value) {
			addCriterion("cityName <=", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameLike(String value) {
			addCriterion("cityName like", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotLike(String value) {
			addCriterion("cityName not like", value, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameIn(List<String> values) {
			addCriterion("cityName in", values, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotIn(List<String> values) {
			addCriterion("cityName not in", values, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameBetween(String value1, String value2) {
			addCriterion("cityName between", value1, value2, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameNotBetween(String value1, String value2) {
			addCriterion("cityName not between", value1, value2, "cityName");
			return (Criteria) this;
		}

		public Criteria andCityNameEnIsNull() {
			addCriterion("cityNameEn is null");
			return (Criteria) this;
		}

		public Criteria andCityNameEnIsNotNull() {
			addCriterion("cityNameEn is not null");
			return (Criteria) this;
		}

		public Criteria andCityNameEnEqualTo(String value) {
			addCriterion("cityNameEn =", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnNotEqualTo(String value) {
			addCriterion("cityNameEn <>", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnGreaterThan(String value) {
			addCriterion("cityNameEn >", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnGreaterThanOrEqualTo(String value) {
			addCriterion("cityNameEn >=", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnLessThan(String value) {
			addCriterion("cityNameEn <", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnLessThanOrEqualTo(String value) {
			addCriterion("cityNameEn <=", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnLike(String value) {
			addCriterion("cityNameEn like", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnNotLike(String value) {
			addCriterion("cityNameEn not like", value, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnIn(List<String> values) {
			addCriterion("cityNameEn in", values, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnNotIn(List<String> values) {
			addCriterion("cityNameEn not in", values, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnBetween(String value1, String value2) {
			addCriterion("cityNameEn between", value1, value2, "cityNameEn");
			return (Criteria) this;
		}

		public Criteria andCityNameEnNotBetween(String value1, String value2) {
			addCriterion("cityNameEn not between", value1, value2, "cityNameEn");
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
	 * This class was generated by MyBatis Generator. This class corresponds to the database table city_code
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
     * This class corresponds to the database table city_code
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}