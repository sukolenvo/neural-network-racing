package com.sukolenvo.racing.swing;

import com.sukolenvo.racing.core.SimpleUnitImpl;
import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.UnitController;
import com.sukolenvo.racing.core.track.SimpleRacingTrack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static final int PLAYER_COUNT = 5;

    public static void main(String[] args) {
        List<Unit> players = new ArrayList<>();
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players.add(new SimpleUnitImpl(100, -125 - i));
        }
        SimpleRacingTrack racingTrack = new SimpleRacingTrack();
        UnitController unitController = new UnitController(players, racingTrack);
        JFrame frame = new JFrame("Racing");
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1000, 1000));
            frame.setLayout(null);
            players.forEach(unit -> frame.getContentPane().add(new UnitPanel(unit)));
            frame.getContentPane().add(new RacingTrackPanel(racingTrack));
            frame.pack();
            frame.setVisible(true);
        });
        unitController.start(frame::repaint);
    }
}