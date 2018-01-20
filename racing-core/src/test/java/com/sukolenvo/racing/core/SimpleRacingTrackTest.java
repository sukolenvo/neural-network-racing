package com.sukolenvo.racing.core;


import com.sukolenvo.racing.core.track.Point;
import com.sukolenvo.racing.core.track.SimpleRacingTrack;
import org.junit.Assert;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;

public class SimpleRacingTrackTest {

    @Test
    public void getBackground() throws Exception {
        BufferedImage background = new SimpleRacingTrack().getBackground();
        Assert.assertNotNull(background);
    }

    @Test
    public void getWall_horizontal() throws Exception {
        Point wall = new SimpleRacingTrack().getWall(100, -125, 0);
        assertEquals(546, wall.getX(), 0.01);
        assertEquals(-125, wall.getY(), 0.01);
    }

    @Test
    public void getWall_angle3() throws Exception {
        Point wall = new SimpleRacingTrack().getWall(100, -125, 3);
        assertEquals(wall.toString(), 523.7901018, wall.getX(), 0.01);
        assertEquals(wall.toString(), -102.79010, wall.getY(), 0.01);
    }

    @Test
    public void getWall() throws Exception {
        Point wall = new SimpleRacingTrack().getWall(564.5980314129116, -111.56847710188016, 1);
        assertEquals(wall.toString(), 533.1179637272, wall.getX(), 0.01);
        assertEquals(wall.toString(), -112.11796, wall.getY(), 0.01);
    }

    @Test
    public void getWall2() throws Exception {
        Point wall = new SimpleRacingTrack().getWall(474.84695265, -125.2618924, 0);
        assertEquals(wall.toString(), 546.2618, wall.getX(), 0.01);
        assertEquals(wall.toString(), -125.2618, wall.getY(), 0.01);
    }

    @Test
    public void getWall3() throws Exception {
        Point wall = new SimpleRacingTrack().getWall(154.9756347043961, -123.7786239226601, 4);
        assertEquals(wall.toString(), 100, wall.getX(), 0.01);
        assertEquals(wall.toString(), -127.62289, wall.getY(), 0.01);
    }
}