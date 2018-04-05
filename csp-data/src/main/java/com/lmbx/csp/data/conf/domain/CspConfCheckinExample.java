package com.lmbx.csp.data.conf.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CspConfCheckinExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CspConfCheckinExample() {
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

        public Criteria andCheckinTypeIsNull() {
            addCriterion("CHECKIN_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeIsNotNull() {
            addCriterion("CHECKIN_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeEqualTo(Short value) {
            addCriterion("CHECKIN_TYPE =", value, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeNotEqualTo(Short value) {
            addCriterion("CHECKIN_TYPE <>", value, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeGreaterThan(Short value) {
            addCriterion("CHECKIN_TYPE >", value, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("CHECKIN_TYPE >=", value, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeLessThan(Short value) {
            addCriterion("CHECKIN_TYPE <", value, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeLessThanOrEqualTo(Short value) {
            addCriterion("CHECKIN_TYPE <=", value, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeIn(List<Short> values) {
            addCriterion("CHECKIN_TYPE in", values, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeNotIn(List<Short> values) {
            addCriterion("CHECKIN_TYPE not in", values, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeBetween(Short value1, Short value2) {
            addCriterion("CHECKIN_TYPE between", value1, value2, "checkinType");
            return (Criteria) this;
        }

        public Criteria andCheckinTypeNotBetween(Short value1, Short value2) {
            addCriterion("CHECKIN_TYPE not between", value1, value2, "checkinType");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Short value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Short value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Short value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Short value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Short value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Short value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Short> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Short> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Short value1, Short value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Short value1, Short value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDataVersionIsNull() {
            addCriterion("DATA_VERSION is null");
            return (Criteria) this;
        }

        public Criteria andDataVersionIsNotNull() {
            addCriterion("DATA_VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andDataVersionEqualTo(String value) {
            addCriterion("DATA_VERSION =", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionNotEqualTo(String value) {
            addCriterion("DATA_VERSION <>", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionGreaterThan(String value) {
            addCriterion("DATA_VERSION >", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_VERSION >=", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionLessThan(String value) {
            addCriterion("DATA_VERSION <", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionLessThanOrEqualTo(String value) {
            addCriterion("DATA_VERSION <=", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionLike(String value) {
            addCriterion("DATA_VERSION like", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionNotLike(String value) {
            addCriterion("DATA_VERSION not like", value, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionIn(List<String> values) {
            addCriterion("DATA_VERSION in", values, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionNotIn(List<String> values) {
            addCriterion("DATA_VERSION not in", values, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionBetween(String value1, String value2) {
            addCriterion("DATA_VERSION between", value1, value2, "dataVersion");
            return (Criteria) this;
        }

        public Criteria andDataVersionNotBetween(String value1, String value2) {
            addCriterion("DATA_VERSION not between", value1, value2, "dataVersion");
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

        public Criteria andCourseIdIsNull() {
            addCriterion("COURSE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("COURSE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(String value) {
            addCriterion("COURSE_ID =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(String value) {
            addCriterion("COURSE_ID <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(String value) {
            addCriterion("COURSE_ID >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(String value) {
            addCriterion("COURSE_ID >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(String value) {
            addCriterion("COURSE_ID <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(String value) {
            addCriterion("COURSE_ID <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLike(String value) {
            addCriterion("COURSE_ID like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotLike(String value) {
            addCriterion("COURSE_ID not like", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<String> values) {
            addCriterion("COURSE_ID in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<String> values) {
            addCriterion("COURSE_ID not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(String value1, String value2) {
            addCriterion("COURSE_ID between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(String value1, String value2) {
            addCriterion("COURSE_ID not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCheckinNoIsNull() {
            addCriterion("CHECKIN_NO is null");
            return (Criteria) this;
        }

        public Criteria andCheckinNoIsNotNull() {
            addCriterion("CHECKIN_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinNoEqualTo(String value) {
            addCriterion("CHECKIN_NO =", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoNotEqualTo(String value) {
            addCriterion("CHECKIN_NO <>", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoGreaterThan(String value) {
            addCriterion("CHECKIN_NO >", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoGreaterThanOrEqualTo(String value) {
            addCriterion("CHECKIN_NO >=", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoLessThan(String value) {
            addCriterion("CHECKIN_NO <", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoLessThanOrEqualTo(String value) {
            addCriterion("CHECKIN_NO <=", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoLike(String value) {
            addCriterion("CHECKIN_NO like", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoNotLike(String value) {
            addCriterion("CHECKIN_NO not like", value, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoIn(List<String> values) {
            addCriterion("CHECKIN_NO in", values, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoNotIn(List<String> values) {
            addCriterion("CHECKIN_NO not in", values, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoBetween(String value1, String value2) {
            addCriterion("CHECKIN_NO between", value1, value2, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNoNotBetween(String value1, String value2) {
            addCriterion("CHECKIN_NO not between", value1, value2, "checkinNo");
            return (Criteria) this;
        }

        public Criteria andCheckinNameIsNull() {
            addCriterion("CHECKIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCheckinNameIsNotNull() {
            addCriterion("CHECKIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinNameEqualTo(String value) {
            addCriterion("CHECKIN_NAME =", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameNotEqualTo(String value) {
            addCriterion("CHECKIN_NAME <>", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameGreaterThan(String value) {
            addCriterion("CHECKIN_NAME >", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHECKIN_NAME >=", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameLessThan(String value) {
            addCriterion("CHECKIN_NAME <", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameLessThanOrEqualTo(String value) {
            addCriterion("CHECKIN_NAME <=", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameLike(String value) {
            addCriterion("CHECKIN_NAME like", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameNotLike(String value) {
            addCriterion("CHECKIN_NAME not like", value, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameIn(List<String> values) {
            addCriterion("CHECKIN_NAME in", values, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameNotIn(List<String> values) {
            addCriterion("CHECKIN_NAME not in", values, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameBetween(String value1, String value2) {
            addCriterion("CHECKIN_NAME between", value1, value2, "checkinName");
            return (Criteria) this;
        }

        public Criteria andCheckinNameNotBetween(String value1, String value2) {
            addCriterion("CHECKIN_NAME not between", value1, value2, "checkinName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeIsNull() {
            addCriterion("PERSON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPersonTypeIsNotNull() {
            addCriterion("PERSON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPersonTypeEqualTo(String value) {
            addCriterion("PERSON_TYPE =", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNotEqualTo(String value) {
            addCriterion("PERSON_TYPE <>", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeGreaterThan(String value) {
            addCriterion("PERSON_TYPE >", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PERSON_TYPE >=", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeLessThan(String value) {
            addCriterion("PERSON_TYPE <", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeLessThanOrEqualTo(String value) {
            addCriterion("PERSON_TYPE <=", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeLike(String value) {
            addCriterion("PERSON_TYPE like", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNotLike(String value) {
            addCriterion("PERSON_TYPE not like", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeIn(List<String> values) {
            addCriterion("PERSON_TYPE in", values, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNotIn(List<String> values) {
            addCriterion("PERSON_TYPE not in", values, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeBetween(String value1, String value2) {
            addCriterion("PERSON_TYPE between", value1, value2, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNotBetween(String value1, String value2) {
            addCriterion("PERSON_TYPE not between", value1, value2, "personType");
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