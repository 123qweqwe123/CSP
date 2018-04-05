package com.lmbx.csp.data.sys.domain;

public class SysDashboardComponent {
    private String id;

    private String i;

    private Short x;

    private Short y;

    private Short w;

    private Short h;

    private Short maxW;

    private Short minW;

    private Short minH;

    private Short maxH;

    private Short isStatic;

    private Short isDraggable;

    private Short isResizable;

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i == null ? null : i.trim();
    }

    public Short getX() {
        return x;
    }

    public void setX(Short x) {
        this.x = x;
    }

    public Short getY() {
        return y;
    }

    public void setY(Short y) {
        this.y = y;
    }

    public Short getW() {
        return w;
    }

    public void setW(Short w) {
        this.w = w;
    }

    public Short getH() {
        return h;
    }

    public void setH(Short h) {
        this.h = h;
    }

    public Short getMaxW() {
        return maxW;
    }

    public void setMaxW(Short maxW) {
        this.maxW = maxW;
    }

    public Short getMinW() {
        return minW;
    }

    public void setMinW(Short minW) {
        this.minW = minW;
    }

    public Short getMinH() {
        return minH;
    }

    public void setMinH(Short minH) {
        this.minH = minH;
    }

    public Short getMaxH() {
        return maxH;
    }

    public void setMaxH(Short maxH) {
        this.maxH = maxH;
    }

    public Short getIsStatic() {
        return isStatic;
    }

    public void setIsStatic(Short isStatic) {
        this.isStatic = isStatic;
    }

    public Short getIsDraggable() {
        return isDraggable;
    }

    public void setIsDraggable(Short isDraggable) {
        this.isDraggable = isDraggable;
    }

    public Short getIsResizable() {
        return isResizable;
    }

    public void setIsResizable(Short isResizable) {
        this.isResizable = isResizable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}