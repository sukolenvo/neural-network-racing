package com.sukolenvo.racing.core.track;

import lombok.Data;

@Data
public class Line {

    //ax + by + c = 0
    private double a;
    private double b;
    private double c;

    /**
     * y = kx + b
     */
    public Line(double k, double b) {
        this.a = k;
        this.b = -1;
        this.c = b;
    }

    public Line(double x, double y, double angle) {
        double k = Math.tan(Math.toRadians(angle));
        double b = y - k * x;
        this.a = k;
        this.b = -1;
        this.c = b;
    }

    /**
     *  x - x1	  y - y1
     * ------- = -------
     * x2 - x1   y2 - y1
     *
     */
    public Line(double x1, double y1, double x2, double y2) {
        a = y2 - y1;
        b = x1 - x2;
        c = y1 * (x2 - x1) - x1 * (y2 - y1);
    }

    public Line(Point a, Point b) {
        this(a.getX(), a.getY(), b.getX(), b.getY());
    }

    public Point crossPoint(Line with) throws NoCrossPointException {
        if (Double.compare(a / b, with.a / with.b) == 0) {
            throw new NoCrossPointException(this + " with " + with);
        }
        if (a == 0) {
            double y = -c / b;
            double x = -(with.b * y + with.c) / with.a;
            return new Point(x, y);
        } else {
            double y = (c * with.a - with.c * a) / (with.b * a - b * with.a);
            double x = -(b * y + c) / a;
            return new Point(x, y);
        }
    }

    public double getK() {
        return -a / b;
    }

    public boolean sameAngle(Line with) {
        return Math.abs(Math.toDegrees(Math.atan(getK())) - Math.toDegrees(Math.atan(with.getK()))) < 0.01;
    }
}
