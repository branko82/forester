package com.rhcloud.forester.core;

public class Position {

    public static Point getNextPoint(Point point, float angle, int diameter) {
        float endX = (float) Math.sin(Math.toRadians(angle)) * diameter;
        float endY = (float) Math.cos(Math.toRadians(angle)) * diameter;
        return new Point(point.getX()+endX, point.getY()+endY);
    }

}
