package com.sukolenvo.racing.swing;

import com.sukolenvo.racing.core.track.RacingTrack;

import java.awt.Graphics;
import java.awt.Image;

public class RacingTrackPanel extends AbstractAbsolutePanel {

    private final Image background;

    public RacingTrackPanel(RacingTrack racingTrack) {
        background = racingTrack.getBackground();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, this);
    }
}
