package com.lmbx.csp.data.sys.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysDataParseQcExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDataParseQcExample() {
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

        public Criteria andFileIdIsNull() {
            addCriterion("FILE_ID is null");
            return (Criteria) this;
        }

        public Criteria andFileIdIsNotNull() {
            addCriterion("FILE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFileIdEqualTo(String value) {
            addCriterion("FILE_ID =", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotEqualTo(String value) {
            addCriterion("FILE_ID <>", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThan(String value) {
            addCriterion("FILE_ID >", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_ID >=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThan(String value) {
            addCriterion("FILE_ID <", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLessThanOrEqualTo(String value) {
            addCriterion("FILE_ID <=", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdLike(String value) {
            addCriterion("FILE_ID like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotLike(String value) {
            addCriterion("FILE_ID not like", value, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdIn(List<String> values) {
            addCriterion("FILE_ID in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotIn(List<String> values) {
            addCriterion("FILE_ID not in", values, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdBetween(String value1, String value2) {
            addCriterion("FILE_ID between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileIdNotBetween(String value1, String value2) {
            addCriterion("FILE_ID not between", value1, value2, "fileId");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
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

        public Criteria andProjectIdIsNull() {
            addCriterion("PROJECT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("PROJECT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("PROJECT_ID =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("PROJECT_ID <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("PROJECT_ID >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("PROJECT_ID <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("PROJECT_ID <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("PROJECT_ID like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("PROJECT_ID not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("PROJECT_ID in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("PROJECT_ID not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("PROJECT_ID between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("PROJECT_ID not between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andLccCodeIsNull() {
            addCriterion("LCC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andLccCodeIsNotNull() {
            addCriterion("LCC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andLccCodeEqualTo(String value) {
            addCriterion("LCC_CODE =", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotEqualTo(String value) {
            addCriterion("LCC_CODE <>", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeGreaterThan(String value) {
            addCriterion("LCC_CODE >", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeGreaterThanOrEqualTo(String value) {
            addCriterion("LCC_CODE >=", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLessThan(String value) {
            addCriterion("LCC_CODE <", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLessThanOrEqualTo(String value) {
            addCriterion("LCC_CODE <=", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeLike(String value) {
            addCriterion("LCC_CODE like", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotLike(String value) {
            addCriterion("LCC_CODE not like", value, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeIn(List<String> values) {
            addCriterion("LCC_CODE in", values, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotIn(List<String> values) {
            addCriterion("LCC_CODE not in", values, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeBetween(String value1, String value2) {
            addCriterion("LCC_CODE between", value1, value2, "lccCode");
            return (Criteria) this;
        }

        public Criteria andLccCodeNotBetween(String value1, String value2) {
            addCriterion("LCC_CODE not between", value1, value2, "lccCode");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("DATA_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("DATA_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("DATA_TYPE =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("DATA_TYPE <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("DATA_TYPE >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("DATA_TYPE <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("DATA_TYPE like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("DATA_TYPE not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("DATA_TYPE in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("DATA_TYPE not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("DATA_TYPE between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("DATA_TYPE not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataFormatIsNull() {
            addCriterion("DATA_FORMAT is null");
            return (Criteria) this;
        }

        public Criteria andDataFormatIsNotNull() {
            addCriterion("DATA_FORMAT is not null");
            return (Criteria) this;
        }

        public Criteria andDataFormatEqualTo(String value) {
            addCriterion("DATA_FORMAT =", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotEqualTo(String value) {
            addCriterion("DATA_FORMAT <>", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatGreaterThan(String value) {
            addCriterion("DATA_FORMAT >", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_FORMAT >=", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLessThan(String value) {
            addCriterion("DATA_FORMAT <", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLessThanOrEqualTo(String value) {
            addCriterion("DATA_FORMAT <=", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLike(String value) {
            addCriterion("DATA_FORMAT like", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotLike(String value) {
            addCriterion("DATA_FORMAT not like", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatIn(List<String> values) {
            addCriterion("DATA_FORMAT in", values, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotIn(List<String> values) {
            addCriterion("DATA_FORMAT not in", values, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatBetween(String value1, String value2) {
            addCriterion("DATA_FORMAT between", value1, value2, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotBetween(String value1, String value2) {
            addCriterion("DATA_FORMAT not between", value1, value2, "dataFormat");
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

        public Criteria andZipNameIsNull() {
            addCriterion("ZIP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andZipNameIsNotNull() {
            addCriterion("ZIP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andZipNameEqualTo(String value) {
            addCriterion("ZIP_NAME =", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameNotEqualTo(String value) {
            addCriterion("ZIP_NAME <>", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameGreaterThan(String value) {
            addCriterion("ZIP_NAME >", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameGreaterThanOrEqualTo(String value) {
            addCriterion("ZIP_NAME >=", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameLessThan(String value) {
            addCriterion("ZIP_NAME <", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameLessThanOrEqualTo(String value) {
            addCriterion("ZIP_NAME <=", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameLike(String value) {
            addCriterion("ZIP_NAME like", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameNotLike(String value) {
            addCriterion("ZIP_NAME not like", value, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameIn(List<String> values) {
            addCriterion("ZIP_NAME in", values, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameNotIn(List<String> values) {
            addCriterion("ZIP_NAME not in", values, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameBetween(String value1, String value2) {
            addCriterion("ZIP_NAME between", value1, value2, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipNameNotBetween(String value1, String value2) {
            addCriterion("ZIP_NAME not between", value1, value2, "zipName");
            return (Criteria) this;
        }

        public Criteria andZipPathIsNull() {
            addCriterion("ZIP_PATH is null");
            return (Criteria) this;
        }

        public Criteria andZipPathIsNotNull() {
            addCriterion("ZIP_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andZipPathEqualTo(String value) {
            addCriterion("ZIP_PATH =", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathNotEqualTo(String value) {
            addCriterion("ZIP_PATH <>", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathGreaterThan(String value) {
            addCriterion("ZIP_PATH >", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathGreaterThanOrEqualTo(String value) {
            addCriterion("ZIP_PATH >=", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathLessThan(String value) {
            addCriterion("ZIP_PATH <", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathLessThanOrEqualTo(String value) {
            addCriterion("ZIP_PATH <=", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathLike(String value) {
            addCriterion("ZIP_PATH like", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathNotLike(String value) {
            addCriterion("ZIP_PATH not like", value, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathIn(List<String> values) {
            addCriterion("ZIP_PATH in", values, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathNotIn(List<String> values) {
            addCriterion("ZIP_PATH not in", values, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathBetween(String value1, String value2) {
            addCriterion("ZIP_PATH between", value1, value2, "zipPath");
            return (Criteria) this;
        }

        public Criteria andZipPathNotBetween(String value1, String value2) {
            addCriterion("ZIP_PATH not between", value1, value2, "zipPath");
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

        public Criteria andHandleSucIsNull() {
            addCriterion("HANDLE_SUC is null");
            return (Criteria) this;
        }

        public Criteria andHandleSucIsNotNull() {
            addCriterion("HANDLE_SUC is not null");
            return (Criteria) this;
        }

        public Criteria andHandleSucEqualTo(Short value) {
            addCriterion("HANDLE_SUC =", value, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucNotEqualTo(Short value) {
            addCriterion("HANDLE_SUC <>", value, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucGreaterThan(Short value) {
            addCriterion("HANDLE_SUC >", value, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucGreaterThanOrEqualTo(Short value) {
            addCriterion("HANDLE_SUC >=", value, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucLessThan(Short value) {
            addCriterion("HANDLE_SUC <", value, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucLessThanOrEqualTo(Short value) {
            addCriterion("HANDLE_SUC <=", value, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucIn(List<Short> values) {
            addCriterion("HANDLE_SUC in", values, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucNotIn(List<Short> values) {
            addCriterion("HANDLE_SUC not in", values, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucBetween(Short value1, Short value2) {
            addCriterion("HANDLE_SUC between", value1, value2, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andHandleSucNotBetween(Short value1, Short value2) {
            addCriterion("HANDLE_SUC not between", value1, value2, "handleSuc");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNull() {
            addCriterion("ERROR_CODE is null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIsNotNull() {
            addCriterion("ERROR_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCodeEqualTo(String value) {
            addCriterion("ERROR_CODE =", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotEqualTo(String value) {
            addCriterion("ERROR_CODE <>", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThan(String value) {
            addCriterion("ERROR_CODE >", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_CODE >=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThan(String value) {
            addCriterion("ERROR_CODE <", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLessThanOrEqualTo(String value) {
            addCriterion("ERROR_CODE <=", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeLike(String value) {
            addCriterion("ERROR_CODE like", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotLike(String value) {
            addCriterion("ERROR_CODE not like", value, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeIn(List<String> values) {
            addCriterion("ERROR_CODE in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotIn(List<String> values) {
            addCriterion("ERROR_CODE not in", values, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeBetween(String value1, String value2) {
            addCriterion("ERROR_CODE between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorCodeNotBetween(String value1, String value2) {
            addCriterion("ERROR_CODE not between", value1, value2, "errorCode");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNull() {
            addCriterion("ERROR_MSG is null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIsNotNull() {
            addCriterion("ERROR_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andErrorMsgEqualTo(String value) {
            addCriterion("ERROR_MSG =", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotEqualTo(String value) {
            addCriterion("ERROR_MSG <>", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThan(String value) {
            addCriterion("ERROR_MSG >", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgGreaterThanOrEqualTo(String value) {
            addCriterion("ERROR_MSG >=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThan(String value) {
            addCriterion("ERROR_MSG <", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLessThanOrEqualTo(String value) {
            addCriterion("ERROR_MSG <=", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgLike(String value) {
            addCriterion("ERROR_MSG like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotLike(String value) {
            addCriterion("ERROR_MSG not like", value, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgIn(List<String> values) {
            addCriterion("ERROR_MSG in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotIn(List<String> values) {
            addCriterion("ERROR_MSG not in", values, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgBetween(String value1, String value2) {
            addCriterion("ERROR_MSG between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andErrorMsgNotBetween(String value1, String value2) {
            addCriterion("ERROR_MSG not between", value1, value2, "errorMsg");
            return (Criteria) this;
        }

        public Criteria andValue1IsNull() {
            addCriterion("VALUE1 is null");
            return (Criteria) this;
        }

        public Criteria andValue1IsNotNull() {
            addCriterion("VALUE1 is not null");
            return (Criteria) this;
        }

        public Criteria andValue1EqualTo(String value) {
            addCriterion("VALUE1 =", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotEqualTo(String value) {
            addCriterion("VALUE1 <>", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1GreaterThan(String value) {
            addCriterion("VALUE1 >", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1GreaterThanOrEqualTo(String value) {
            addCriterion("VALUE1 >=", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1LessThan(String value) {
            addCriterion("VALUE1 <", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1LessThanOrEqualTo(String value) {
            addCriterion("VALUE1 <=", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1Like(String value) {
            addCriterion("VALUE1 like", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotLike(String value) {
            addCriterion("VALUE1 not like", value, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1In(List<String> values) {
            addCriterion("VALUE1 in", values, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotIn(List<String> values) {
            addCriterion("VALUE1 not in", values, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1Between(String value1, String value2) {
            addCriterion("VALUE1 between", value1, value2, "value1");
            return (Criteria) this;
        }

        public Criteria andValue1NotBetween(String value1, String value2) {
            addCriterion("VALUE1 not between", value1, value2, "value1");
            return (Criteria) this;
        }

        public Criteria andValue2IsNull() {
            addCriterion("VALUE2 is null");
            return (Criteria) this;
        }

        public Criteria andValue2IsNotNull() {
            addCriterion("VALUE2 is not null");
            return (Criteria) this;
        }

        public Criteria andValue2EqualTo(String value) {
            addCriterion("VALUE2 =", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotEqualTo(String value) {
            addCriterion("VALUE2 <>", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2GreaterThan(String value) {
            addCriterion("VALUE2 >", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2GreaterThanOrEqualTo(String value) {
            addCriterion("VALUE2 >=", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2LessThan(String value) {
            addCriterion("VALUE2 <", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2LessThanOrEqualTo(String value) {
            addCriterion("VALUE2 <=", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2Like(String value) {
            addCriterion("VALUE2 like", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotLike(String value) {
            addCriterion("VALUE2 not like", value, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2In(List<String> values) {
            addCriterion("VALUE2 in", values, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotIn(List<String> values) {
            addCriterion("VALUE2 not in", values, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2Between(String value1, String value2) {
            addCriterion("VALUE2 between", value1, value2, "value2");
            return (Criteria) this;
        }

        public Criteria andValue2NotBetween(String value1, String value2) {
            addCriterion("VALUE2 not between", value1, value2, "value2");
            return (Criteria) this;
        }

        public Criteria andValue3IsNull() {
            addCriterion("VALUE3 is null");
            return (Criteria) this;
        }

        public Criteria andValue3IsNotNull() {
            addCriterion("VALUE3 is not null");
            return (Criteria) this;
        }

        public Criteria andValue3EqualTo(String value) {
            addCriterion("VALUE3 =", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3NotEqualTo(String value) {
            addCriterion("VALUE3 <>", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3GreaterThan(String value) {
            addCriterion("VALUE3 >", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3GreaterThanOrEqualTo(String value) {
            addCriterion("VALUE3 >=", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3LessThan(String value) {
            addCriterion("VALUE3 <", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3LessThanOrEqualTo(String value) {
            addCriterion("VALUE3 <=", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3Like(String value) {
            addCriterion("VALUE3 like", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3NotLike(String value) {
            addCriterion("VALUE3 not like", value, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3In(List<String> values) {
            addCriterion("VALUE3 in", values, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3NotIn(List<String> values) {
            addCriterion("VALUE3 not in", values, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3Between(String value1, String value2) {
            addCriterion("VALUE3 between", value1, value2, "value3");
            return (Criteria) this;
        }

        public Criteria andValue3NotBetween(String value1, String value2) {
            addCriterion("VALUE3 not between", value1, value2, "value3");
            return (Criteria) this;
        }

        public Criteria andValue4IsNull() {
            addCriterion("VALUE4 is null");
            return (Criteria) this;
        }

        public Criteria andValue4IsNotNull() {
            addCriterion("VALUE4 is not null");
            return (Criteria) this;
        }

        public Criteria andValue4EqualTo(String value) {
            addCriterion("VALUE4 =", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4NotEqualTo(String value) {
            addCriterion("VALUE4 <>", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4GreaterThan(String value) {
            addCriterion("VALUE4 >", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4GreaterThanOrEqualTo(String value) {
            addCriterion("VALUE4 >=", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4LessThan(String value) {
            addCriterion("VALUE4 <", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4LessThanOrEqualTo(String value) {
            addCriterion("VALUE4 <=", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4Like(String value) {
            addCriterion("VALUE4 like", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4NotLike(String value) {
            addCriterion("VALUE4 not like", value, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4In(List<String> values) {
            addCriterion("VALUE4 in", values, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4NotIn(List<String> values) {
            addCriterion("VALUE4 not in", values, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4Between(String value1, String value2) {
            addCriterion("VALUE4 between", value1, value2, "value4");
            return (Criteria) this;
        }

        public Criteria andValue4NotBetween(String value1, String value2) {
            addCriterion("VALUE4 not between", value1, value2, "value4");
            return (Criteria) this;
        }

        public Criteria andValue5IsNull() {
            addCriterion("VALUE5 is null");
            return (Criteria) this;
        }

        public Criteria andValue5IsNotNull() {
            addCriterion("VALUE5 is not null");
            return (Criteria) this;
        }

        public Criteria andValue5EqualTo(String value) {
            addCriterion("VALUE5 =", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5NotEqualTo(String value) {
            addCriterion("VALUE5 <>", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5GreaterThan(String value) {
            addCriterion("VALUE5 >", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5GreaterThanOrEqualTo(String value) {
            addCriterion("VALUE5 >=", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5LessThan(String value) {
            addCriterion("VALUE5 <", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5LessThanOrEqualTo(String value) {
            addCriterion("VALUE5 <=", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5Like(String value) {
            addCriterion("VALUE5 like", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5NotLike(String value) {
            addCriterion("VALUE5 not like", value, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5In(List<String> values) {
            addCriterion("VALUE5 in", values, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5NotIn(List<String> values) {
            addCriterion("VALUE5 not in", values, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5Between(String value1, String value2) {
            addCriterion("VALUE5 between", value1, value2, "value5");
            return (Criteria) this;
        }

        public Criteria andValue5NotBetween(String value1, String value2) {
            addCriterion("VALUE5 not between", value1, value2, "value5");
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