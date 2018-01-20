package com.sukolenvo.racing.core;

import com.sukolenvo.racing.core.track.Point;
import lombok.Data;

/**
 * @author vadym
 */
@Data
public abstract class Unit {

    protected static final int MAX_ANGLE_DELTA = 4;

    /**
     * From top left corner.
     */
    protected double x;
    /**
     * From top left corner.
     */
    protected double y;
    /**
     * Angle between OX and unit direction on cartesian plot.
     */
    protected double angle;
    /**
     * Unit is no longer able to move.
     */
    protected boolean disabled;

    public Point getPosition() {
        return new Point(x, y);
    }

    public abstract void updateDirection();

    public void moveTo(Point to) {
        this.x = to.getX();
        this.y = to.getY();
    }
}
