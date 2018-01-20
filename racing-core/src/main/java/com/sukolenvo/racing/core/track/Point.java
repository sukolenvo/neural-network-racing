package com.sukolenvo.racing.core.track;

import com.sukolenvo.racing.core.Unit;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {

    private double x;
    private double y;

    public double distanceTo(Point to) {
        return Math.sqrt((x - to.x) * (x - to.x) + (y - to.y) * (y - to.y));
    }

    public double distanceTo(Unit to) {
        return distanceTo(new Point(to.getX(), to.getY()));
    }

    public Point moveTo(double angle, double distance) {
        return new Point(x + Math.cos(Math.toRadians(angle)) * distance,
                y + Math.sin(Math.toRadians(angle)) * distance);
    }
}