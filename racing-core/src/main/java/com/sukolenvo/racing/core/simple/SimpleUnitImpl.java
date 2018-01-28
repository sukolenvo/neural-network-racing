package com.sukolenvo.racing.core.simple;

import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.track.Point;

import java.util.Random;

public class SimpleUnitImpl extends Unit {

    private static Random random = new Random();

    public SimpleUnitImpl(Point start) {
        this(start.getX(), start.getY());
    }

    public SimpleUnitImpl(double startX, double startY) {
        setX(startX);
        setY(startY);
    }

    public void updateDirection() {
        angle += random.nextInt(MAX_ANGLE_DELTA * 2 + 1) - MAX_ANGLE_DELTA;
    }
}
