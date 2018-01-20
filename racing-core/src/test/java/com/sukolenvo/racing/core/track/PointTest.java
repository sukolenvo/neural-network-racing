package com.sukolenvo.racing.core.track;

import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    @Test
    public void testMoveTo_right() throws Exception {
        Point end = new Point(0, 0).moveTo(0, 10);
        assertEquals(10., end.getX(), 0.001);
        assertEquals(0., end.getY(), 0.001);
    }

    @Test
    public void testMoveTo_bottom() throws Exception {
        Point end = new Point(0, 0).moveTo(-90, 10);
        assertEquals(0, end.getX(), 0.001);
        assertEquals(-10, end.getY(), 0.001);
    }

    @Test
    public void testMoveTo_angle() throws Exception {
        Point end = new Point(150, -150).moveTo(-2, 10);
        assertEquals(end.toString(), 159.993908, end.getX(), 0.001);
        assertEquals(end.toString(), -150.3489, end.getY(), 0.001);
        end = new Point(150, -150).moveTo(28, 10);
        assertEquals(end.toString(), 158.82947, end.getX(), 0.001);
        assertEquals(end.toString(), -145.305, end.getY(), 0.001);
        end = new Point(150, -150).moveTo(-32, 10);
        assertEquals(end.toString(), 158.48048, end.getX(), 0.001);
        assertEquals(end.toString(), -155.2991, end.getY(), 0.001);
    }

    @Test
    public void testMoveTo_left() throws Exception {
        Point end = new Point(150, -150).moveTo(180, 10);
        assertEquals(end.toString(), 140, end.getX(), 0.001);
        assertEquals(end.toString(), -150, end.getY(), 0.001);
    }

    @Test
    public void distanceTo() throws Exception {
        double distance = new Point(564.5980314129116, -111.56847710188016)
                .distanceTo(new Point(533.117963, -112.11796372));
        assertEquals(distance + "", 31.4848, distance, 0.01);
    }

    @Test
    public void distanceTo2() throws Exception {
        double distance = new Point(564.5980314129116, -111.56847710188016)
                .distanceTo(new Point(1227.355640705945, -100));
        assertEquals(distance + "", 662.85856, distance, 0.01);
    }
}