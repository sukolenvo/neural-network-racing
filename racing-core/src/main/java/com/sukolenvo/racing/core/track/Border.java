package com.sukolenvo.racing.core.track;

public class Border extends Line {

    private Point a;
    private Point b;

    public Border(Point a, Point b) {
        super(a, b);
        this.a = a;
        this.b = b;
    }

    /**
     * Check if point belongs to border. By checking segment [a,b].
     *
     * @param check point to check, should belong line (a, b)
     */
    public boolean isOnBorder(Point check) {
        if (a.getX() > b.getX()) {
            if (check.getX() - a.getX() > 0.001 || check.getX() - b.getX() < -0.001) {
                return false;
            }
        } else {
            if (check.getX() - a.getX() < -0.001 || check.getX() - b.getX() > 0.001) {
                return false;
            }
        }
        if (a.getY() > b.getY()) {
            if (check.getY() - a.getY() > 0.001 || check.getY() - b.getY() < -0.001) {
                return false;
            }
        } else {
            if (check.getY() - a.getY() < -0.001 || check.getY() - b.getY() > 0.001) {
                return false;
            }
        }
        return true;
    }
}
