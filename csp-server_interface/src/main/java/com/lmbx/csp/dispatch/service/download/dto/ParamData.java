package com.lmbx.csp.dispatch.service.download.dto;

/**
 * @author wanhongwei
 */
public class ParamData {

    private String attrName;
    private String querySql;
    private String queryParameter;

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getQuerySql() {
        return querySql;
    }

    public void setQuerySql(String querySql) {
        this.querySql = querySql;
    }

    public String getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(String queryParameter) {
        this.queryParameter = queryParameter;
    }

    public ParamData(){
    }

    public ParamData(String attrName, String querySql, String queryParameter){
        this.attrName = attrName;
        this.querySql = querySql;
        this.queryParameter = queryParameter;
    }

    @Override
    public String toString() {
        return "ParamData{" + "attrName='" + attrName + '\'' + ", querySql='" + querySql + '\'' + ", queryParameter='"
               + queryParameter + '\'' + '}';
    }
}
