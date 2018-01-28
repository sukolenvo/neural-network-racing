package com.sukolenvo.racing.swing;

import com.sukolenvo.racing.core.AbstractUnitController;
import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.learning.LearningUnitController;
import com.sukolenvo.racing.core.track.SimpleRacingTrack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;

public class Application {

    private static final int PLAYER_COUNT = 20;

    public static void main(String[] args) {
        AbstractUnitController<? extends Unit> simpleUnitController =
                new LearningUnitController(PLAYER_COUNT, new SimpleRacingTrack());
        JFrame frame = new JFrame("Racing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setLayout(null);
        JLabel generationLabel = new JLabel("Generation: 0");
        generationLabel.setFont(new Font(null, Font.PLAIN, 18));
        generationLabel.setBounds(100,850,200,30);
        frame.add(generationLabel);
        simpleUnitController.getUnits().forEach(unit -> frame.getContentPane().add(new UnitPanel(unit)));
        frame.getContentPane().add(new RacingTrackPanel(simpleUnitController.getRacingTrack()));
        frame.pack();
        frame.setVisible(true);
        simpleUnitController.start(frame::repaint, generation -> generationLabel.setText("Generation: " + generation));
    }
}