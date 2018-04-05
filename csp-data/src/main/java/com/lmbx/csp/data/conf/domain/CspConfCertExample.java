package com.lmbx.csp.data.conf.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CspConfCertExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CspConfCertExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdIsNull() {
            addCriterion("LAYOUTFILE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdIsNotNull() {
            addCriterion("LAYOUTFILE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdEqualTo(String value) {
            addCriterion("LAYOUTFILE_ID =", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdNotEqualTo(String value) {
            addCriterion("LAYOUTFILE_ID <>", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdGreaterThan(String value) {
            addCriterion("LAYOUTFILE_ID >", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdGreaterThanOrEqualTo(String value) {
            addCriterion("LAYOUTFILE_ID >=", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdLessThan(String value) {
            addCriterion("LAYOUTFILE_ID <", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdLessThanOrEqualTo(String value) {
            addCriterion("LAYOUTFILE_ID <=", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdLike(String value) {
            addCriterion("LAYOUTFILE_ID like", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdNotLike(String value) {
            addCriterion("LAYOUTFILE_ID not like", value, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdIn(List<String> values) {
            addCriterion("LAYOUTFILE_ID in", values, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdNotIn(List<String> values) {
            addCriterion("LAYOUTFILE_ID not in", values, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdBetween(String value1, String value2) {
            addCriterion("LAYOUTFILE_ID between", value1, value2, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andLayoutfileIdNotBetween(String value1, String value2) {
            addCriterion("LAYOUTFILE_ID not between", value1, value2, "layoutfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdIsNull() {
            addCriterion("BOTTOMFILE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdIsNotNull() {
            addCriterion("BOTTOMFILE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdEqualTo(String value) {
            addCriterion("BOTTOMFILE_ID =", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdNotEqualTo(String value) {
            addCriterion("BOTTOMFILE_ID <>", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdGreaterThan(String value) {
            addCriterion("BOTTOMFILE_ID >", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdGreaterThanOrEqualTo(String value) {
            addCriterion("BOTTOMFILE_ID >=", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdLessThan(String value) {
            addCriterion("BOTTOMFILE_ID <", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdLessThanOrEqualTo(String value) {
            addCriterion("BOTTOMFILE_ID <=", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdLike(String value) {
            addCriterion("BOTTOMFILE_ID like", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdNotLike(String value) {
            addCriterion("BOTTOMFILE_ID not like", value, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdIn(List<String> values) {
            addCriterion("BOTTOMFILE_ID in", values, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdNotIn(List<String> values) {
            addCriterion("BOTTOMFILE_ID not in", values, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdBetween(String value1, String value2) {
            addCriterion("BOTTOMFILE_ID between", value1, value2, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andBottomfileIdNotBetween(String value1, String value2) {
            addCriterion("BOTTOMFILE_ID not between", value1, value2, "bottomfileId");
            return (Criteria) this;
        }

        public Criteria andConfIdIsNull() {
            addCriterion("CONF_ID is null");
            return (Criteria) this;
        }

        public Criteria andConfIdIsNotNull() {
            addCriterion("CONF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConfIdEqualTo(String value) {
            addCriterion("CONF_ID =", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotEqualTo(String value) {
            addCriterion("CONF_ID <>", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdGreaterThan(String value) {
            addCriterion("CONF_ID >", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_ID >=", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdLessThan(String value) {
            addCriterion("CONF_ID <", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdLessThanOrEqualTo(String value) {
            addCriterion("CONF_ID <=", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdLike(String value) {
            addCriterion("CONF_ID like", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotLike(String value) {
            addCriterion("CONF_ID not like", value, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdIn(List<String> values) {
            addCriterion("CONF_ID in", values, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotIn(List<String> values) {
            addCriterion("CONF_ID not in", values, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdBetween(String value1, String value2) {
            addCriterion("CONF_ID between", value1, value2, "confId");
            return (Criteria) this;
        }

        public Criteria andConfIdNotBetween(String value1, String value2) {
            addCriterion("CONF_ID not between", value1, value2, "confId");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeIsNull() {
            addCriterion("IDENTITY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeIsNotNull() {
            addCriterion("IDENTITY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeEqualTo(Short value) {
            addCriterion("IDENTITY_TYPE =", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeNotEqualTo(Short value) {
            addCriterion("IDENTITY_TYPE <>", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeGreaterThan(Short value) {
            addCriterion("IDENTITY_TYPE >", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("IDENTITY_TYPE >=", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeLessThan(Short value) {
            addCriterion("IDENTITY_TYPE <", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeLessThanOrEqualTo(Short value) {
            addCriterion("IDENTITY_TYPE <=", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeIn(List<Short> values) {
            addCriterion("IDENTITY_TYPE in", values, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeNotIn(List<Short> values) {
            addCriterion("IDENTITY_TYPE not in", values, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeBetween(Short value1, Short value2) {
            addCriterion("IDENTITY_TYPE between", value1, value2, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeNotBetween(Short value1, Short value2) {
            addCriterion("IDENTITY_TYPE not between", value1, value2, "identityType");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("UPDATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("UPDATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("UPDATE_BY =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("UPDATE_BY <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("UPDATE_BY >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("UPDATE_BY <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_BY <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("UPDATE_BY like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("UPDATE_BY not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("UPDATE_BY in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("UPDATE_BY not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("UPDATE_BY between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("UPDATE_BY not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
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