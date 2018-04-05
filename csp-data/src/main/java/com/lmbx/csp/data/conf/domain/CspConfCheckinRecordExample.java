package com.lmbx.csp.data.conf.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CspConfCheckinRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CspConfCheckinRecordExample() {
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

        public Criteria andVisitorIdIsNull() {
            addCriterion("VISITOR_ID is null");
            return (Criteria) this;
        }

        public Criteria andVisitorIdIsNotNull() {
            addCriterion("VISITOR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorIdEqualTo(String value) {
            addCriterion("VISITOR_ID =", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotEqualTo(String value) {
            addCriterion("VISITOR_ID <>", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdGreaterThan(String value) {
            addCriterion("VISITOR_ID >", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdGreaterThanOrEqualTo(String value) {
            addCriterion("VISITOR_ID >=", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdLessThan(String value) {
            addCriterion("VISITOR_ID <", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdLessThanOrEqualTo(String value) {
            addCriterion("VISITOR_ID <=", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdLike(String value) {
            addCriterion("VISITOR_ID like", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotLike(String value) {
            addCriterion("VISITOR_ID not like", value, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdIn(List<String> values) {
            addCriterion("VISITOR_ID in", values, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotIn(List<String> values) {
            addCriterion("VISITOR_ID not in", values, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdBetween(String value1, String value2) {
            addCriterion("VISITOR_ID between", value1, value2, "visitorId");
            return (Criteria) this;
        }

        public Criteria andVisitorIdNotBetween(String value1, String value2) {
            addCriterion("VISITOR_ID not between", value1, value2, "visitorId");
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

        public Criteria andConfPlaceIdIsNull() {
            addCriterion("CONF_PLACE_ID is null");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdIsNotNull() {
            addCriterion("CONF_PLACE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdEqualTo(String value) {
            addCriterion("CONF_PLACE_ID =", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdNotEqualTo(String value) {
            addCriterion("CONF_PLACE_ID <>", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdGreaterThan(String value) {
            addCriterion("CONF_PLACE_ID >", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_PLACE_ID >=", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdLessThan(String value) {
            addCriterion("CONF_PLACE_ID <", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdLessThanOrEqualTo(String value) {
            addCriterion("CONF_PLACE_ID <=", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdLike(String value) {
            addCriterion("CONF_PLACE_ID like", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdNotLike(String value) {
            addCriterion("CONF_PLACE_ID not like", value, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdIn(List<String> values) {
            addCriterion("CONF_PLACE_ID in", values, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdNotIn(List<String> values) {
            addCriterion("CONF_PLACE_ID not in", values, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdBetween(String value1, String value2) {
            addCriterion("CONF_PLACE_ID between", value1, value2, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIdNotBetween(String value1, String value2) {
            addCriterion("CONF_PLACE_ID not between", value1, value2, "confPlaceId");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoIsNull() {
            addCriterion("VISITOR_CONF_NO is null");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoIsNotNull() {
            addCriterion("VISITOR_CONF_NO is not null");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoEqualTo(String value) {
            addCriterion("VISITOR_CONF_NO =", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoNotEqualTo(String value) {
            addCriterion("VISITOR_CONF_NO <>", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoGreaterThan(String value) {
            addCriterion("VISITOR_CONF_NO >", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoGreaterThanOrEqualTo(String value) {
            addCriterion("VISITOR_CONF_NO >=", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoLessThan(String value) {
            addCriterion("VISITOR_CONF_NO <", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoLessThanOrEqualTo(String value) {
            addCriterion("VISITOR_CONF_NO <=", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoLike(String value) {
            addCriterion("VISITOR_CONF_NO like", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoNotLike(String value) {
            addCriterion("VISITOR_CONF_NO not like", value, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoIn(List<String> values) {
            addCriterion("VISITOR_CONF_NO in", values, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoNotIn(List<String> values) {
            addCriterion("VISITOR_CONF_NO not in", values, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoBetween(String value1, String value2) {
            addCriterion("VISITOR_CONF_NO between", value1, value2, "visitorConfNo");
            return (Criteria) this;
        }

        public Criteria andVisitorConfNoNotBetween(String value1, String value2) {
            addCriterion("VISITOR_CONF_NO not between", value1, value2, "visitorConfNo");
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

        public Criteria andCheckinByIsNull() {
            addCriterion("CHECKIN_BY is null");
            return (Criteria) this;
        }

        public Criteria andCheckinByIsNotNull() {
            addCriterion("CHECKIN_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinByEqualTo(String value) {
            addCriterion("CHECKIN_BY =", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByNotEqualTo(String value) {
            addCriterion("CHECKIN_BY <>", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByGreaterThan(String value) {
            addCriterion("CHECKIN_BY >", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByGreaterThanOrEqualTo(String value) {
            addCriterion("CHECKIN_BY >=", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByLessThan(String value) {
            addCriterion("CHECKIN_BY <", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByLessThanOrEqualTo(String value) {
            addCriterion("CHECKIN_BY <=", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByLike(String value) {
            addCriterion("CHECKIN_BY like", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByNotLike(String value) {
            addCriterion("CHECKIN_BY not like", value, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByIn(List<String> values) {
            addCriterion("CHECKIN_BY in", values, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByNotIn(List<String> values) {
            addCriterion("CHECKIN_BY not in", values, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByBetween(String value1, String value2) {
            addCriterion("CHECKIN_BY between", value1, value2, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinByNotBetween(String value1, String value2) {
            addCriterion("CHECKIN_BY not between", value1, value2, "checkinBy");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeIsNull() {
            addCriterion("CHECKIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeIsNotNull() {
            addCriterion("CHECKIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeEqualTo(Date value) {
            addCriterion("CHECKIN_TIME =", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeNotEqualTo(Date value) {
            addCriterion("CHECKIN_TIME <>", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeGreaterThan(Date value) {
            addCriterion("CHECKIN_TIME >", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CHECKIN_TIME >=", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeLessThan(Date value) {
            addCriterion("CHECKIN_TIME <", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeLessThanOrEqualTo(Date value) {
            addCriterion("CHECKIN_TIME <=", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeIn(List<Date> values) {
            addCriterion("CHECKIN_TIME in", values, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeNotIn(List<Date> values) {
            addCriterion("CHECKIN_TIME not in", values, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeBetween(Date value1, Date value2) {
            addCriterion("CHECKIN_TIME between", value1, value2, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeNotBetween(Date value1, Date value2) {
            addCriterion("CHECKIN_TIME not between", value1, value2, "checkinTime");
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

        public Criteria andSrcIdIsNull() {
            addCriterion("SRC_ID is null");
            return (Criteria) this;
        }

        public Criteria andSrcIdIsNotNull() {
            addCriterion("SRC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSrcIdEqualTo(String value) {
            addCriterion("SRC_ID =", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotEqualTo(String value) {
            addCriterion("SRC_ID <>", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdGreaterThan(String value) {
            addCriterion("SRC_ID >", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdGreaterThanOrEqualTo(String value) {
            addCriterion("SRC_ID >=", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdLessThan(String value) {
            addCriterion("SRC_ID <", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdLessThanOrEqualTo(String value) {
            addCriterion("SRC_ID <=", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdLike(String value) {
            addCriterion("SRC_ID like", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotLike(String value) {
            addCriterion("SRC_ID not like", value, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdIn(List<String> values) {
            addCriterion("SRC_ID in", values, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotIn(List<String> values) {
            addCriterion("SRC_ID not in", values, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdBetween(String value1, String value2) {
            addCriterion("SRC_ID between", value1, value2, "srcId");
            return (Criteria) this;
        }

        public Criteria andSrcIdNotBetween(String value1, String value2) {
            addCriterion("SRC_ID not between", value1, value2, "srcId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdIsNull() {
            addCriterion("CHECKIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andCheckinIdIsNotNull() {
            addCriterion("CHECKIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinIdEqualTo(String value) {
            addCriterion("CHECKIN_ID =", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdNotEqualTo(String value) {
            addCriterion("CHECKIN_ID <>", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdGreaterThan(String value) {
            addCriterion("CHECKIN_ID >", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHECKIN_ID >=", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdLessThan(String value) {
            addCriterion("CHECKIN_ID <", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdLessThanOrEqualTo(String value) {
            addCriterion("CHECKIN_ID <=", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdLike(String value) {
            addCriterion("CHECKIN_ID like", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdNotLike(String value) {
            addCriterion("CHECKIN_ID not like", value, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdIn(List<String> values) {
            addCriterion("CHECKIN_ID in", values, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdNotIn(List<String> values) {
            addCriterion("CHECKIN_ID not in", values, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdBetween(String value1, String value2) {
            addCriterion("CHECKIN_ID between", value1, value2, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinIdNotBetween(String value1, String value2) {
            addCriterion("CHECKIN_ID not between", value1, value2, "checkinId");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceIsNull() {
            addCriterion("CHECKIN_SOURCE is null");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceIsNotNull() {
            addCriterion("CHECKIN_SOURCE is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceEqualTo(Short value) {
            addCriterion("CHECKIN_SOURCE =", value, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceNotEqualTo(Short value) {
            addCriterion("CHECKIN_SOURCE <>", value, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceGreaterThan(Short value) {
            addCriterion("CHECKIN_SOURCE >", value, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceGreaterThanOrEqualTo(Short value) {
            addCriterion("CHECKIN_SOURCE >=", value, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceLessThan(Short value) {
            addCriterion("CHECKIN_SOURCE <", value, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceLessThanOrEqualTo(Short value) {
            addCriterion("CHECKIN_SOURCE <=", value, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceIn(List<Short> values) {
            addCriterion("CHECKIN_SOURCE in", values, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceNotIn(List<Short> values) {
            addCriterion("CHECKIN_SOURCE not in", values, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceBetween(Short value1, Short value2) {
            addCriterion("CHECKIN_SOURCE between", value1, value2, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andCheckinSourceNotBetween(Short value1, Short value2) {
            addCriterion("CHECKIN_SOURCE not between", value1, value2, "checkinSource");
            return (Criteria) this;
        }

        public Criteria andTerminalIdIsNull() {
            addCriterion("TERMINAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andTerminalIdIsNotNull() {
            addCriterion("TERMINAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTerminalIdEqualTo(String value) {
            addCriterion("TERMINAL_ID =", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotEqualTo(String value) {
            addCriterion("TERMINAL_ID <>", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdGreaterThan(String value) {
            addCriterion("TERMINAL_ID >", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdGreaterThanOrEqualTo(String value) {
            addCriterion("TERMINAL_ID >=", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLessThan(String value) {
            addCriterion("TERMINAL_ID <", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLessThanOrEqualTo(String value) {
            addCriterion("TERMINAL_ID <=", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdLike(String value) {
            addCriterion("TERMINAL_ID like", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotLike(String value) {
            addCriterion("TERMINAL_ID not like", value, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdIn(List<String> values) {
            addCriterion("TERMINAL_ID in", values, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotIn(List<String> values) {
            addCriterion("TERMINAL_ID not in", values, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdBetween(String value1, String value2) {
            addCriterion("TERMINAL_ID between", value1, value2, "terminalId");
            return (Criteria) this;
        }

        public Criteria andTerminalIdNotBetween(String value1, String value2) {
            addCriterion("TERMINAL_ID not between", value1, value2, "terminalId");
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