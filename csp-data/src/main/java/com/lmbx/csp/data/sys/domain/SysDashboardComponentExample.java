package com.lmbx.csp.data.sys.domain;

import java.util.ArrayList;
import java.util.List;

public class SysDashboardComponentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDashboardComponentExample() {
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

        public Criteria andMaxWIsNull() {
            addCriterion("MAX_W is null");
            return (Criteria) this;
        }

        public Criteria andMaxWIsNotNull() {
            addCriterion("MAX_W is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWEqualTo(Short value) {
            addCriterion("MAX_W =", value, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWNotEqualTo(Short value) {
            addCriterion("MAX_W <>", value, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWGreaterThan(Short value) {
            addCriterion("MAX_W >", value, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWGreaterThanOrEqualTo(Short value) {
            addCriterion("MAX_W >=", value, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWLessThan(Short value) {
            addCriterion("MAX_W <", value, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWLessThanOrEqualTo(Short value) {
            addCriterion("MAX_W <=", value, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWIn(List<Short> values) {
            addCriterion("MAX_W in", values, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWNotIn(List<Short> values) {
            addCriterion("MAX_W not in", values, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWBetween(Short value1, Short value2) {
            addCriterion("MAX_W between", value1, value2, "maxW");
            return (Criteria) this;
        }

        public Criteria andMaxWNotBetween(Short value1, Short value2) {
            addCriterion("MAX_W not between", value1, value2, "maxW");
            return (Criteria) this;
        }

        public Criteria andMinWIsNull() {
            addCriterion("MIN_W is null");
            return (Criteria) this;
        }

        public Criteria andMinWIsNotNull() {
            addCriterion("MIN_W is not null");
            return (Criteria) this;
        }

        public Criteria andMinWEqualTo(Short value) {
            addCriterion("MIN_W =", value, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWNotEqualTo(Short value) {
            addCriterion("MIN_W <>", value, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWGreaterThan(Short value) {
            addCriterion("MIN_W >", value, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWGreaterThanOrEqualTo(Short value) {
            addCriterion("MIN_W >=", value, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWLessThan(Short value) {
            addCriterion("MIN_W <", value, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWLessThanOrEqualTo(Short value) {
            addCriterion("MIN_W <=", value, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWIn(List<Short> values) {
            addCriterion("MIN_W in", values, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWNotIn(List<Short> values) {
            addCriterion("MIN_W not in", values, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWBetween(Short value1, Short value2) {
            addCriterion("MIN_W between", value1, value2, "minW");
            return (Criteria) this;
        }

        public Criteria andMinWNotBetween(Short value1, Short value2) {
            addCriterion("MIN_W not between", value1, value2, "minW");
            return (Criteria) this;
        }

        public Criteria andMinHIsNull() {
            addCriterion("MIN_H is null");
            return (Criteria) this;
        }

        public Criteria andMinHIsNotNull() {
            addCriterion("MIN_H is not null");
            return (Criteria) this;
        }

        public Criteria andMinHEqualTo(Short value) {
            addCriterion("MIN_H =", value, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHNotEqualTo(Short value) {
            addCriterion("MIN_H <>", value, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHGreaterThan(Short value) {
            addCriterion("MIN_H >", value, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHGreaterThanOrEqualTo(Short value) {
            addCriterion("MIN_H >=", value, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHLessThan(Short value) {
            addCriterion("MIN_H <", value, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHLessThanOrEqualTo(Short value) {
            addCriterion("MIN_H <=", value, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHIn(List<Short> values) {
            addCriterion("MIN_H in", values, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHNotIn(List<Short> values) {
            addCriterion("MIN_H not in", values, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHBetween(Short value1, Short value2) {
            addCriterion("MIN_H between", value1, value2, "minH");
            return (Criteria) this;
        }

        public Criteria andMinHNotBetween(Short value1, Short value2) {
            addCriterion("MIN_H not between", value1, value2, "minH");
            return (Criteria) this;
        }

        public Criteria andMaxHIsNull() {
            addCriterion("MAX_H is null");
            return (Criteria) this;
        }

        public Criteria andMaxHIsNotNull() {
            addCriterion("MAX_H is not null");
            return (Criteria) this;
        }

        public Criteria andMaxHEqualTo(Short value) {
            addCriterion("MAX_H =", value, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHNotEqualTo(Short value) {
            addCriterion("MAX_H <>", value, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHGreaterThan(Short value) {
            addCriterion("MAX_H >", value, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHGreaterThanOrEqualTo(Short value) {
            addCriterion("MAX_H >=", value, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHLessThan(Short value) {
            addCriterion("MAX_H <", value, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHLessThanOrEqualTo(Short value) {
            addCriterion("MAX_H <=", value, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHIn(List<Short> values) {
            addCriterion("MAX_H in", values, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHNotIn(List<Short> values) {
            addCriterion("MAX_H not in", values, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHBetween(Short value1, Short value2) {
            addCriterion("MAX_H between", value1, value2, "maxH");
            return (Criteria) this;
        }

        public Criteria andMaxHNotBetween(Short value1, Short value2) {
            addCriterion("MAX_H not between", value1, value2, "maxH");
            return (Criteria) this;
        }

        public Criteria andIsStaticIsNull() {
            addCriterion("IS_STATIC is null");
            return (Criteria) this;
        }

        public Criteria andIsStaticIsNotNull() {
            addCriterion("IS_STATIC is not null");
            return (Criteria) this;
        }

        public Criteria andIsStaticEqualTo(Short value) {
            addCriterion("IS_STATIC =", value, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticNotEqualTo(Short value) {
            addCriterion("IS_STATIC <>", value, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticGreaterThan(Short value) {
            addCriterion("IS_STATIC >", value, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_STATIC >=", value, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticLessThan(Short value) {
            addCriterion("IS_STATIC <", value, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticLessThanOrEqualTo(Short value) {
            addCriterion("IS_STATIC <=", value, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticIn(List<Short> values) {
            addCriterion("IS_STATIC in", values, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticNotIn(List<Short> values) {
            addCriterion("IS_STATIC not in", values, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticBetween(Short value1, Short value2) {
            addCriterion("IS_STATIC between", value1, value2, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsStaticNotBetween(Short value1, Short value2) {
            addCriterion("IS_STATIC not between", value1, value2, "isStatic");
            return (Criteria) this;
        }

        public Criteria andIsDraggableIsNull() {
            addCriterion("IS_DRAGGABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsDraggableIsNotNull() {
            addCriterion("IS_DRAGGABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDraggableEqualTo(Short value) {
            addCriterion("IS_DRAGGABLE =", value, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableNotEqualTo(Short value) {
            addCriterion("IS_DRAGGABLE <>", value, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableGreaterThan(Short value) {
            addCriterion("IS_DRAGGABLE >", value, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_DRAGGABLE >=", value, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableLessThan(Short value) {
            addCriterion("IS_DRAGGABLE <", value, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableLessThanOrEqualTo(Short value) {
            addCriterion("IS_DRAGGABLE <=", value, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableIn(List<Short> values) {
            addCriterion("IS_DRAGGABLE in", values, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableNotIn(List<Short> values) {
            addCriterion("IS_DRAGGABLE not in", values, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableBetween(Short value1, Short value2) {
            addCriterion("IS_DRAGGABLE between", value1, value2, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsDraggableNotBetween(Short value1, Short value2) {
            addCriterion("IS_DRAGGABLE not between", value1, value2, "isDraggable");
            return (Criteria) this;
        }

        public Criteria andIsResizableIsNull() {
            addCriterion("IS_RESIZABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsResizableIsNotNull() {
            addCriterion("IS_RESIZABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsResizableEqualTo(Short value) {
            addCriterion("IS_RESIZABLE =", value, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableNotEqualTo(Short value) {
            addCriterion("IS_RESIZABLE <>", value, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableGreaterThan(Short value) {
            addCriterion("IS_RESIZABLE >", value, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableGreaterThanOrEqualTo(Short value) {
            addCriterion("IS_RESIZABLE >=", value, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableLessThan(Short value) {
            addCriterion("IS_RESIZABLE <", value, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableLessThanOrEqualTo(Short value) {
            addCriterion("IS_RESIZABLE <=", value, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableIn(List<Short> values) {
            addCriterion("IS_RESIZABLE in", values, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableNotIn(List<Short> values) {
            addCriterion("IS_RESIZABLE not in", values, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableBetween(Short value1, Short value2) {
            addCriterion("IS_RESIZABLE between", value1, value2, "isResizable");
            return (Criteria) this;
        }

        public Criteria andIsResizableNotBetween(Short value1, Short value2) {
            addCriterion("IS_RESIZABLE not between", value1, value2, "isResizable");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
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