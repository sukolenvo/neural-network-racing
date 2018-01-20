package com.sukolenvo.racing.core.track;

import org.junit.Test;

import static org.junit.Assert.*;

public class BorderTest {
    @Test
    public void isOnBorder() throws Exception {
        Border border = new Border(new Point(521, -100), new Point(900, -479));
        Point point = new Line(100, -125, 0).crossPoint(border);
        assertTrue(point.toString(), border.isOnBorder(point));
    }

}