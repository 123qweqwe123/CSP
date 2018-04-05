package com.lmbx.csp.data.conf.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CspConferenceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CspConferenceExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andConfNoIsNull() {
            addCriterion("CONF_NO is null");
            return (Criteria) this;
        }

        public Criteria andConfNoIsNotNull() {
            addCriterion("CONF_NO is not null");
            return (Criteria) this;
        }

        public Criteria andConfNoEqualTo(String value) {
            addCriterion("CONF_NO =", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoNotEqualTo(String value) {
            addCriterion("CONF_NO <>", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoGreaterThan(String value) {
            addCriterion("CONF_NO >", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_NO >=", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoLessThan(String value) {
            addCriterion("CONF_NO <", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoLessThanOrEqualTo(String value) {
            addCriterion("CONF_NO <=", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoLike(String value) {
            addCriterion("CONF_NO like", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoNotLike(String value) {
            addCriterion("CONF_NO not like", value, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoIn(List<String> values) {
            addCriterion("CONF_NO in", values, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoNotIn(List<String> values) {
            addCriterion("CONF_NO not in", values, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoBetween(String value1, String value2) {
            addCriterion("CONF_NO between", value1, value2, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfNoNotBetween(String value1, String value2) {
            addCriterion("CONF_NO not between", value1, value2, "confNo");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNull() {
            addCriterion("CONF_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andConfTypeIsNotNull() {
            addCriterion("CONF_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andConfTypeEqualTo(Short value) {
            addCriterion("CONF_TYPE =", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotEqualTo(Short value) {
            addCriterion("CONF_TYPE <>", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThan(Short value) {
            addCriterion("CONF_TYPE >", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("CONF_TYPE >=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThan(Short value) {
            addCriterion("CONF_TYPE <", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeLessThanOrEqualTo(Short value) {
            addCriterion("CONF_TYPE <=", value, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeIn(List<Short> values) {
            addCriterion("CONF_TYPE in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotIn(List<Short> values) {
            addCriterion("CONF_TYPE not in", values, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeBetween(Short value1, Short value2) {
            addCriterion("CONF_TYPE between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfTypeNotBetween(Short value1, Short value2) {
            addCriterion("CONF_TYPE not between", value1, value2, "confType");
            return (Criteria) this;
        }

        public Criteria andConfFormIsNull() {
            addCriterion("CONF_FORM is null");
            return (Criteria) this;
        }

        public Criteria andConfFormIsNotNull() {
            addCriterion("CONF_FORM is not null");
            return (Criteria) this;
        }

        public Criteria andConfFormEqualTo(String value) {
            addCriterion("CONF_FORM =", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormNotEqualTo(String value) {
            addCriterion("CONF_FORM <>", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormGreaterThan(String value) {
            addCriterion("CONF_FORM >", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_FORM >=", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormLessThan(String value) {
            addCriterion("CONF_FORM <", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormLessThanOrEqualTo(String value) {
            addCriterion("CONF_FORM <=", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormLike(String value) {
            addCriterion("CONF_FORM like", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormNotLike(String value) {
            addCriterion("CONF_FORM not like", value, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormIn(List<String> values) {
            addCriterion("CONF_FORM in", values, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormNotIn(List<String> values) {
            addCriterion("CONF_FORM not in", values, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormBetween(String value1, String value2) {
            addCriterion("CONF_FORM between", value1, value2, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfFormNotBetween(String value1, String value2) {
            addCriterion("CONF_FORM not between", value1, value2, "confForm");
            return (Criteria) this;
        }

        public Criteria andConfHostIsNull() {
            addCriterion("CONF_HOST is null");
            return (Criteria) this;
        }

        public Criteria andConfHostIsNotNull() {
            addCriterion("CONF_HOST is not null");
            return (Criteria) this;
        }

        public Criteria andConfHostEqualTo(String value) {
            addCriterion("CONF_HOST =", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostNotEqualTo(String value) {
            addCriterion("CONF_HOST <>", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostGreaterThan(String value) {
            addCriterion("CONF_HOST >", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_HOST >=", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostLessThan(String value) {
            addCriterion("CONF_HOST <", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostLessThanOrEqualTo(String value) {
            addCriterion("CONF_HOST <=", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostLike(String value) {
            addCriterion("CONF_HOST like", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostNotLike(String value) {
            addCriterion("CONF_HOST not like", value, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostIn(List<String> values) {
            addCriterion("CONF_HOST in", values, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostNotIn(List<String> values) {
            addCriterion("CONF_HOST not in", values, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostBetween(String value1, String value2) {
            addCriterion("CONF_HOST between", value1, value2, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfHostNotBetween(String value1, String value2) {
            addCriterion("CONF_HOST not between", value1, value2, "confHost");
            return (Criteria) this;
        }

        public Criteria andConfNameIsNull() {
            addCriterion("CONF_NAME is null");
            return (Criteria) this;
        }

        public Criteria andConfNameIsNotNull() {
            addCriterion("CONF_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andConfNameEqualTo(String value) {
            addCriterion("CONF_NAME =", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotEqualTo(String value) {
            addCriterion("CONF_NAME <>", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameGreaterThan(String value) {
            addCriterion("CONF_NAME >", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_NAME >=", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLessThan(String value) {
            addCriterion("CONF_NAME <", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLessThanOrEqualTo(String value) {
            addCriterion("CONF_NAME <=", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLike(String value) {
            addCriterion("CONF_NAME like", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotLike(String value) {
            addCriterion("CONF_NAME not like", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameIn(List<String> values) {
            addCriterion("CONF_NAME in", values, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotIn(List<String> values) {
            addCriterion("CONF_NAME not in", values, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameBetween(String value1, String value2) {
            addCriterion("CONF_NAME between", value1, value2, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotBetween(String value1, String value2) {
            addCriterion("CONF_NAME not between", value1, value2, "confName");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserIsNull() {
            addCriterion("CONF_ORGANISER is null");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserIsNotNull() {
            addCriterion("CONF_ORGANISER is not null");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserEqualTo(String value) {
            addCriterion("CONF_ORGANISER =", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserNotEqualTo(String value) {
            addCriterion("CONF_ORGANISER <>", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserGreaterThan(String value) {
            addCriterion("CONF_ORGANISER >", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_ORGANISER >=", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserLessThan(String value) {
            addCriterion("CONF_ORGANISER <", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserLessThanOrEqualTo(String value) {
            addCriterion("CONF_ORGANISER <=", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserLike(String value) {
            addCriterion("CONF_ORGANISER like", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserNotLike(String value) {
            addCriterion("CONF_ORGANISER not like", value, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserIn(List<String> values) {
            addCriterion("CONF_ORGANISER in", values, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserNotIn(List<String> values) {
            addCriterion("CONF_ORGANISER not in", values, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserBetween(String value1, String value2) {
            addCriterion("CONF_ORGANISER between", value1, value2, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfOrganiserNotBetween(String value1, String value2) {
            addCriterion("CONF_ORGANISER not between", value1, value2, "confOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserIsNull() {
            addCriterion("CONF_CO_ORGANISER is null");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserIsNotNull() {
            addCriterion("CONF_CO_ORGANISER is not null");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserEqualTo(String value) {
            addCriterion("CONF_CO_ORGANISER =", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserNotEqualTo(String value) {
            addCriterion("CONF_CO_ORGANISER <>", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserGreaterThan(String value) {
            addCriterion("CONF_CO_ORGANISER >", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_CO_ORGANISER >=", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserLessThan(String value) {
            addCriterion("CONF_CO_ORGANISER <", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserLessThanOrEqualTo(String value) {
            addCriterion("CONF_CO_ORGANISER <=", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserLike(String value) {
            addCriterion("CONF_CO_ORGANISER like", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserNotLike(String value) {
            addCriterion("CONF_CO_ORGANISER not like", value, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserIn(List<String> values) {
            addCriterion("CONF_CO_ORGANISER in", values, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserNotIn(List<String> values) {
            addCriterion("CONF_CO_ORGANISER not in", values, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserBetween(String value1, String value2) {
            addCriterion("CONF_CO_ORGANISER between", value1, value2, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfCoOrganiserNotBetween(String value1, String value2) {
            addCriterion("CONF_CO_ORGANISER not between", value1, value2, "confCoOrganiser");
            return (Criteria) this;
        }

        public Criteria andConfPicIsNull() {
            addCriterion("CONF_PIC is null");
            return (Criteria) this;
        }

        public Criteria andConfPicIsNotNull() {
            addCriterion("CONF_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andConfPicEqualTo(String value) {
            addCriterion("CONF_PIC =", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicNotEqualTo(String value) {
            addCriterion("CONF_PIC <>", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicGreaterThan(String value) {
            addCriterion("CONF_PIC >", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_PIC >=", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicLessThan(String value) {
            addCriterion("CONF_PIC <", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicLessThanOrEqualTo(String value) {
            addCriterion("CONF_PIC <=", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicLike(String value) {
            addCriterion("CONF_PIC like", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicNotLike(String value) {
            addCriterion("CONF_PIC not like", value, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicIn(List<String> values) {
            addCriterion("CONF_PIC in", values, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicNotIn(List<String> values) {
            addCriterion("CONF_PIC not in", values, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicBetween(String value1, String value2) {
            addCriterion("CONF_PIC between", value1, value2, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfPicNotBetween(String value1, String value2) {
            addCriterion("CONF_PIC not between", value1, value2, "confPic");
            return (Criteria) this;
        }

        public Criteria andConfTopicIsNull() {
            addCriterion("CONF_TOPIC is null");
            return (Criteria) this;
        }

        public Criteria andConfTopicIsNotNull() {
            addCriterion("CONF_TOPIC is not null");
            return (Criteria) this;
        }

        public Criteria andConfTopicEqualTo(String value) {
            addCriterion("CONF_TOPIC =", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicNotEqualTo(String value) {
            addCriterion("CONF_TOPIC <>", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicGreaterThan(String value) {
            addCriterion("CONF_TOPIC >", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_TOPIC >=", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicLessThan(String value) {
            addCriterion("CONF_TOPIC <", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicLessThanOrEqualTo(String value) {
            addCriterion("CONF_TOPIC <=", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicLike(String value) {
            addCriterion("CONF_TOPIC like", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicNotLike(String value) {
            addCriterion("CONF_TOPIC not like", value, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicIn(List<String> values) {
            addCriterion("CONF_TOPIC in", values, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicNotIn(List<String> values) {
            addCriterion("CONF_TOPIC not in", values, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicBetween(String value1, String value2) {
            addCriterion("CONF_TOPIC between", value1, value2, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfTopicNotBetween(String value1, String value2) {
            addCriterion("CONF_TOPIC not between", value1, value2, "confTopic");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionIsNull() {
            addCriterion("CONF_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionIsNotNull() {
            addCriterion("CONF_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionEqualTo(String value) {
            addCriterion("CONF_DESCRIPTION =", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionNotEqualTo(String value) {
            addCriterion("CONF_DESCRIPTION <>", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionGreaterThan(String value) {
            addCriterion("CONF_DESCRIPTION >", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_DESCRIPTION >=", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionLessThan(String value) {
            addCriterion("CONF_DESCRIPTION <", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionLessThanOrEqualTo(String value) {
            addCriterion("CONF_DESCRIPTION <=", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionLike(String value) {
            addCriterion("CONF_DESCRIPTION like", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionNotLike(String value) {
            addCriterion("CONF_DESCRIPTION not like", value, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionIn(List<String> values) {
            addCriterion("CONF_DESCRIPTION in", values, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionNotIn(List<String> values) {
            addCriterion("CONF_DESCRIPTION not in", values, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionBetween(String value1, String value2) {
            addCriterion("CONF_DESCRIPTION between", value1, value2, "confDescription");
            return (Criteria) this;
        }

        public Criteria andConfDescriptionNotBetween(String value1, String value2) {
            addCriterion("CONF_DESCRIPTION not between", value1, value2, "confDescription");
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
            addCriterionForJDBCDate("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterionForJDBCDate("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterionForJDBCDate("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("START_TIME not between", value1, value2, "startTime");
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
            addCriterionForJDBCDate("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterionForJDBCDate("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterionForJDBCDate("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("END_TIME not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIsNull() {
            addCriterion("CONF_PLACE is null");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIsNotNull() {
            addCriterion("CONF_PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andConfPlaceEqualTo(String value) {
            addCriterion("CONF_PLACE =", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceNotEqualTo(String value) {
            addCriterion("CONF_PLACE <>", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceGreaterThan(String value) {
            addCriterion("CONF_PLACE >", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("CONF_PLACE >=", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceLessThan(String value) {
            addCriterion("CONF_PLACE <", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceLessThanOrEqualTo(String value) {
            addCriterion("CONF_PLACE <=", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceLike(String value) {
            addCriterion("CONF_PLACE like", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceNotLike(String value) {
            addCriterion("CONF_PLACE not like", value, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceIn(List<String> values) {
            addCriterion("CONF_PLACE in", values, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceNotIn(List<String> values) {
            addCriterion("CONF_PLACE not in", values, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceBetween(String value1, String value2) {
            addCriterion("CONF_PLACE between", value1, value2, "confPlace");
            return (Criteria) this;
        }

        public Criteria andConfPlaceNotBetween(String value1, String value2) {
            addCriterion("CONF_PLACE not between", value1, value2, "confPlace");
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

        public Criteria andExpectPersonIsNull() {
            addCriterion("EXPECT_PERSON is null");
            return (Criteria) this;
        }

        public Criteria andExpectPersonIsNotNull() {
            addCriterion("EXPECT_PERSON is not null");
            return (Criteria) this;
        }

        public Criteria andExpectPersonEqualTo(Integer value) {
            addCriterion("EXPECT_PERSON =", value, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonNotEqualTo(Integer value) {
            addCriterion("EXPECT_PERSON <>", value, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonGreaterThan(Integer value) {
            addCriterion("EXPECT_PERSON >", value, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonGreaterThanOrEqualTo(Integer value) {
            addCriterion("EXPECT_PERSON >=", value, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonLessThan(Integer value) {
            addCriterion("EXPECT_PERSON <", value, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonLessThanOrEqualTo(Integer value) {
            addCriterion("EXPECT_PERSON <=", value, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonIn(List<Integer> values) {
            addCriterion("EXPECT_PERSON in", values, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonNotIn(List<Integer> values) {
            addCriterion("EXPECT_PERSON not in", values, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonBetween(Integer value1, Integer value2) {
            addCriterion("EXPECT_PERSON between", value1, value2, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andExpectPersonNotBetween(Integer value1, Integer value2) {
            addCriterion("EXPECT_PERSON not between", value1, value2, "expectPerson");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNull() {
            addCriterion("PLACE is null");
            return (Criteria) this;
        }

        public Criteria andPlaceIsNotNull() {
            addCriterion("PLACE is not null");
            return (Criteria) this;
        }

        public Criteria andPlaceEqualTo(String value) {
            addCriterion("PLACE =", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotEqualTo(String value) {
            addCriterion("PLACE <>", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThan(String value) {
            addCriterion("PLACE >", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceGreaterThanOrEqualTo(String value) {
            addCriterion("PLACE >=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThan(String value) {
            addCriterion("PLACE <", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLessThanOrEqualTo(String value) {
            addCriterion("PLACE <=", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceLike(String value) {
            addCriterion("PLACE like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotLike(String value) {
            addCriterion("PLACE not like", value, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceIn(List<String> values) {
            addCriterion("PLACE in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotIn(List<String> values) {
            addCriterion("PLACE not in", values, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceBetween(String value1, String value2) {
            addCriterion("PLACE between", value1, value2, "place");
            return (Criteria) this;
        }

        public Criteria andPlaceNotBetween(String value1, String value2) {
            addCriterion("PLACE not between", value1, value2, "place");
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