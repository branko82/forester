package com.rhcloud.forester.core;

import com.itextpdf.text.BaseColor;

public class Line {
    private Point endPoint;
    private Point startPoint;
    private float angle;
    private BaseColor color;

    public Line(Point startPoint, Point endPoint, float angle, BaseColor color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.angle = angle;
        this.color = color;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public float getAngle() {
        return angle;
    }

    public BaseColor getColor() {
        return color;
    }

    public void setColor(BaseColor color) {
        this.color = color;
    }
}
