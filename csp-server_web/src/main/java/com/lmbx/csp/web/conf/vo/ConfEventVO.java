package com.lmbx.csp.web.conf.vo;

import java.util.Date;

/**
 * Description:
 * 
 * <pre>
 *     日常安排事件，用于前端展示
 * </pre>
 *
 * @author javahuang
 * @since 2017/12/18 上午10:52
 */
public class ConfEventVO {

    private String title;
    private Boolean allDay;
    private Date   start;
    private Date   end;
    private String desc;
    private String id;
    /**
     * 事件类型 1、会议 2、课程 3、签到
     */
    private Short type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getAllDay() {
        return allDay;
    }

    public void setAllDay(Boolean allDay) {
        this.allDay = allDay;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
