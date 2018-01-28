package com.sukolenvo.racing.core.track;

import com.sukolenvo.racing.core.Unit;

import java.awt.*;

/**
 * @author vadym
 */
public interface RacingTrack {

    int getWidth();

    int getHeight();

    Image getBackground();

    Point getWall(double xFrom, double yFrom, double direction) throws WallNotFoundException;

    default Point getWall(Unit unit) {
        return getWall(unit.getX(), unit.getY(), unit.getAngle());
    }

    Point getInitialPosition();

    double fitnessFunction(Point position);
}
