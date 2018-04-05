package com.lmbx.csp.data.sys.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysAccountDashboardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysAccountDashboardExample() {
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

        public Criteria andAccountIdIsNull() {
            addCriterion("ACCOUNT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("ACCOUNT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(String value) {
            addCriterion("ACCOUNT_ID =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(String value) {
            addCriterion("ACCOUNT_ID <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(String value) {
            addCriterion("ACCOUNT_ID >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_ID >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(String value) {
            addCriterion("ACCOUNT_ID <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_ID <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLike(String value) {
            addCriterion("ACCOUNT_ID like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotLike(String value) {
            addCriterion("ACCOUNT_ID not like", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<String> values) {
            addCriterion("ACCOUNT_ID in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<String> values) {
            addCriterion("ACCOUNT_ID not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(String value1, String value2) {
            addCriterion("ACCOUNT_ID between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_ID not between", value1, value2, "accountId");
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

        public Criteria andXIsNull() {
            addCriterion("X is null");
            return (Criteria) this;
        }

        public Criteria andXIsNotNull() {
            addCriterion("X is not null");
            return (Criteria) this;
        }

        public Criteria andXEqualTo(Short value) {
            addCriterion("X =", value, "x");
            return (Criteria) this;
        }

        public Criteria andXNotEqualTo(Short value) {
            addCriterion("X <>", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThan(Short value) {
            addCriterion("X >", value, "x");
            return (Criteria) this;
        }

        public Criteria andXGreaterThanOrEqualTo(Short value) {
            addCriterion("X >=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThan(Short value) {
            addCriterion("X <", value, "x");
            return (Criteria) this;
        }

        public Criteria andXLessThanOrEqualTo(Short value) {
            addCriterion("X <=", value, "x");
            return (Criteria) this;
        }

        public Criteria andXIn(List<Short> values) {
            addCriterion("X in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXNotIn(List<Short> values) {
            addCriterion("X not in", values, "x");
            return (Criteria) this;
        }

        public Criteria andXBetween(Short value1, Short value2) {
            addCriterion("X between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andXNotBetween(Short value1, Short value2) {
            addCriterion("X not between", value1, value2, "x");
            return (Criteria) this;
        }

        public Criteria andYIsNull() {
            addCriterion("Y is null");
            return (Criteria) this;
        }

        public Criteria andYIsNotNull() {
            addCriterion("Y is not null");
            return (Criteria) this;
        }

        public Criteria andYEqualTo(Short value) {
            addCriterion("Y =", value, "y");
            return (Criteria) this;
        }

        public Criteria andYNotEqualTo(Short value) {
            addCriterion("Y <>", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThan(Short value) {
            addCriterion("Y >", value, "y");
            return (Criteria) this;
        }

        public Criteria andYGreaterThanOrEqualTo(Short value) {
            addCriterion("Y >=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThan(Short value) {
            addCriterion("Y <", value, "y");
            return (Criteria) this;
        }

        public Criteria andYLessThanOrEqualTo(Short value) {
            addCriterion("Y <=", value, "y");
            return (Criteria) this;
        }

        public Criteria andYIn(List<Short> values) {
            addCriterion("Y in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYNotIn(List<Short> values) {
            addCriterion("Y not in", values, "y");
            return (Criteria) this;
        }

        public Criteria andYBetween(Short value1, Short value2) {
            addCriterion("Y between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andYNotBetween(Short value1, Short value2) {
            addCriterion("Y not between", value1, value2, "y");
            return (Criteria) this;
        }

        public Criteria andWIsNull() {
            addCriterion("W is null");
            return (Criteria) this;
        }

        public Criteria andWIsNotNull() {
            addCriterion("W is not null");
            return (Criteria) this;
        }

        public Criteria andWEqualTo(Short value) {
            addCriterion("W =", value, "w");
            return (Criteria) this;
        }

        public Criteria andWNotEqualTo(Short value) {
            addCriterion("W <>", value, "w");
            return (Criteria) this;
        }

        public Criteria andWGreaterThan(Short value) {
            addCriterion("W >", value, "w");
            return (Criteria) this;
        }

        public Criteria andWGreaterThanOrEqualTo(Short value) {
            addCriterion("W >=", value, "w");
            return (Criteria) this;
        }

        public Criteria andWLessThan(Short value) {
            addCriterion("W <", value, "w");
            return (Criteria) this;
        }

        public Criteria andWLessThanOrEqualTo(Short value) {
            addCriterion("W <=", value, "w");
            return (Criteria) this;
        }

        public Criteria andWIn(List<Short> values) {
            addCriterion("W in", values, "w");
            return (Criteria) this;
        }

        public Criteria andWNotIn(List<Short> values) {
            addCriterion("W not in", values, "w");
            return (Criteria) this;
        }

        public Criteria andWBetween(Short value1, Short value2) {
            addCriterion("W between", value1, value2, "w");
            return (Criteria) this;
        }

        public Criteria andWNotBetween(Short value1, Short value2) {
            addCriterion("W not between", value1, value2, "w");
            return (Criteria) this;
        }

        public Criteria andHIsNull() {
            addCriterion("H is null");
            return (Criteria) this;
        }

        public Criteria andHIsNotNull() {
            addCriterion("H is not null");
            return (Criteria) this;
        }

        public Criteria andHEqualTo(Short value) {
            addCriterion("H =", value, "h");
            return (Criteria) this;
        }

        public Criteria andHNotEqualTo(Short value) {
            addCriterion("H <>", value, "h");
            return (Criteria) this;
        }

        public Criteria andHGreaterThan(Short value) {
            addCriterion("H >", value, "h");
            return (Criteria) this;
        }

        public Criteria andHGreaterThanOrEqualTo(Short value) {
            addCriterion("H >=", value, "h");
            return (Criteria) this;
        }

        public Criteria andHLessThan(Short value) {
            addCriterion("H <", value, "h");
            return (Criteria) this;
        }

        public Criteria andHLessThanOrEqualTo(Short value) {
            addCriterion("H <=", value, "h");
            return (Criteria) this;
        }

        public Criteria andHIn(List<Short> values) {
            addCriterion("H in", values, "h");
            return (Criteria) this;
        }

        public Criteria andHNotIn(List<Short> values) {
            addCriterion("H not in", values, "h");
            return (Criteria) this;
        }

        public Criteria andHBetween(Short value1, Short value2) {
            addCriterion("H between", value1, value2, "h");
            return (Criteria) this;
        }

        public Criteria andHNotBetween(Short value1, Short value2) {
            addCriterion("H not between", value1, value2, "h");
            return (Criteria) this;
        }

        public Criteria andIIsNull() {
            addCriterion("I is null");
            return (Criteria) this;
        }

        public Criteria andIIsNotNull() {
            addCriterion("I is not null");
            return (Criteria) this;
        }

        public Criteria andIEqualTo(String value) {
            addCriterion("I =", value, "i");
            return (Criteria) this;
        }

        public Criteria andINotEqualTo(String value) {
            addCriterion("I <>", value, "i");
            return (Criteria) this;
        }

        public Criteria andIGreaterThan(String value) {
            addCriterion("I >", value, "i");
            return (Criteria) this;
        }

        public Criteria andIGreaterThanOrEqualTo(String value) {
            addCriterion("I >=", value, "i");
            return (Criteria) this;
        }

        public Criteria andILessThan(String value) {
            addCriterion("I <", value, "i");
            return (Criteria) this;
        }

        public Criteria andILessThanOrEqualTo(String value) {
            addCriterion("I <=", value, "i");
            return (Criteria) this;
        }

        public Criteria andILike(String value) {
            addCriterion("I like", value, "i");
            return (Criteria) this;
        }

        public Criteria andINotLike(String value) {
            addCriterion("I not like", value, "i");
            return (Criteria) this;
        }

        public Criteria andIIn(List<String> values) {
            addCriterion("I in", values, "i");
            return (Criteria) this;
        }

        public Criteria andINotIn(List<String> values) {
            addCriterion("I not in", values, "i");
            return (Criteria) this;
        }

        public Criteria andIBetween(String value1, String value2) {
            addCriterion("I between", value1, value2, "i");
            return (Criteria) this;
        }

        public Criteria andINotBetween(String value1, String value2) {
            addCriterion("I not between", value1, value2, "i");
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