package com.sukolenvo.racing.core.track;

import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {

    @Test
    public void testLine_angle() throws Exception {
        Line line = new Line(1, 1, 45);
        assertEquals(1., line.getK(), 0.001);
        assertEquals(1., line.getA(), 0.001);
        assertEquals(-1., line.getB(), 0.001);
        assertEquals(0., line.getC(), 0.001);
        line = new Line(1, 2, 45);
        assertEquals(1., line.getK(), 0.001);
        assertEquals(1., line.getA(), 0.001);
        assertEquals(-1., line.getB(), 0.001);
        assertEquals(1., line.getC(), 0.001);
    }

    @Test
    public void testLine_twoPoints() throws Exception {
        Line line = new Line(1, 1, 2, 2);
        assertEquals(1., line.getK(), 0.001);
        assertEquals(1., line.getA(), 0.001);
        assertEquals(-1., line.getB(), 0.001);
        assertEquals(0, line.getC(), 0.001);
        line = new Line(2, 3, 1, 2);
        assertEquals(1, line.getK(), 0.001);
        assertEquals(-1., line.getA(), 0.001);
        assertEquals(1., line.getB(), 0.001);
        assertEquals(-1, line.getC(), 0.001);
    }

    @Test
    public void testLine_horizontal() throws Exception {
        Line line = new Line(900, 900, 850, 900);
        assertEquals(.0, line.getA(), 0.001);
        assertEquals(50., line.getB(), 0.001);
        assertEquals(-45000, line.getC(), 0.001);
    }

    @Test(expected = NoCrossPointException.class)
    public void crossPoint_parallel() throws Exception {
        new Line(1, 1).crossPoint(new Line(1, 0));
    }

    @Test
    public void crossPoint() throws Exception {
        Point point = new Line(0, 0, 0, 2).crossPoint(new Line(0, 0, 2, 0));
        assertEquals(0., point.getX(), 0.001);
        assertEquals(0., point.getY(), 0.001);
    }


    @Test
    public void crossPoint_withAngle() throws Exception {
        Point point = new Line(100, -125,3).crossPoint(new Line(100, -100, 500, -100));
        assertEquals(point.toString(), 577.028417, point.getX(), 0.001);
        assertEquals(point.toString(),-100., point.getY(), 0.001);
    }

    @Test
    public void crossPoint2() throws Exception {
        Point point = new Line(549.5516487020275, -134.85199985897177, -1)
                .crossPoint(new Line(521, -100, 900, -479));
        assertEquals(point.toString(), 555.9639, point.getX(), 0.001);
        assertEquals(point.toString(), -134.963926, point.getY(), 0.001);
    }

    @Test
    public void crossPoint3() throws Exception {
        Point point = new Line(564.5980314129116, -111.56847710188016, 1)
                .crossPoint(new Line(521, -100, 900, -479));
        assertEquals(point.toString(), 533.117963, point.getX(), 0.001);
        assertEquals(point.toString(), -112.11796372, point.getY(), 0.001);
    }

    @Test
    public void compareAngle() throws Exception {
        double k = new Line(549.5516487020275, -134.85199985897177, 555.9639, -134.963926).getK();
        assertEquals(k + "", -1, Math.toDegrees(Math.atan(k)), 0.001);
    }

    @Test
    public void saveDirection() throws Exception {
        boolean same = new Line(564.5980314129116, -111.56847710188016, 1)
                .sameAngle(new Line(564.5980314129116, -111.56847710188016, 533.117963, -112.11796372));
        assertTrue(same);
    }
}