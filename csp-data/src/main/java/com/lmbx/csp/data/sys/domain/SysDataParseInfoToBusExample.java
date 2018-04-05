package com.lmbx.csp.data.sys.domain;

import java.util.ArrayList;
import java.util.List;

public class SysDataParseInfoToBusExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDataParseInfoToBusExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andInfoIdIsNull() {
            addCriterion("INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andInfoIdIsNotNull() {
            addCriterion("INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInfoIdEqualTo(String value) {
            addCriterion("INFO_ID =", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotEqualTo(String value) {
            addCriterion("INFO_ID <>", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThan(String value) {
            addCriterion("INFO_ID >", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("INFO_ID >=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThan(String value) {
            addCriterion("INFO_ID <", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLessThanOrEqualTo(String value) {
            addCriterion("INFO_ID <=", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdLike(String value) {
            addCriterion("INFO_ID like", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotLike(String value) {
            addCriterion("INFO_ID not like", value, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdIn(List<String> values) {
            addCriterion("INFO_ID in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotIn(List<String> values) {
            addCriterion("INFO_ID not in", values, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdBetween(String value1, String value2) {
            addCriterion("INFO_ID between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andInfoIdNotBetween(String value1, String value2) {
            addCriterion("INFO_ID not between", value1, value2, "infoId");
            return (Criteria) this;
        }

        public Criteria andBusIdIsNull() {
            addCriterion("BUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andBusIdIsNotNull() {
            addCriterion("BUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBusIdEqualTo(String value) {
            addCriterion("BUS_ID =", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotEqualTo(String value) {
            addCriterion("BUS_ID <>", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdGreaterThan(String value) {
            addCriterion("BUS_ID >", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdGreaterThanOrEqualTo(String value) {
            addCriterion("BUS_ID >=", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdLessThan(String value) {
            addCriterion("BUS_ID <", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdLessThanOrEqualTo(String value) {
            addCriterion("BUS_ID <=", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdLike(String value) {
            addCriterion("BUS_ID like", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotLike(String value) {
            addCriterion("BUS_ID not like", value, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdIn(List<String> values) {
            addCriterion("BUS_ID in", values, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotIn(List<String> values) {
            addCriterion("BUS_ID not in", values, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdBetween(String value1, String value2) {
            addCriterion("BUS_ID between", value1, value2, "busId");
            return (Criteria) this;
        }

        public Criteria andBusIdNotBetween(String value1, String value2) {
            addCriterion("BUS_ID not between", value1, value2, "busId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
}