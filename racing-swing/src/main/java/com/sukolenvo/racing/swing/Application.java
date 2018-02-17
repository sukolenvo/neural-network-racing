package com.sukolenvo.racing.swing;

import com.sukolenvo.racing.core.AbstractUnitController;
import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.learning.LearningUnitController;
import com.sukolenvo.racing.core.track.SimpleRacingTrack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Font;

public class Application {

    private static final int PLAYER_COUNT = 20;

    public static void main(String[] args) {
        AbstractUnitController<? extends Unit> unitController =
                new LearningUnitController(PLAYER_COUNT, new SimpleRacingTrack());
        JFrame frame = createJFrame(unitController);
        JLabel infoLabel = createInfoLabel(frame);

        new Simulator(unitController, frame, infoLabel).beginSimulation();
    }

    public static JLabel createInfoLabel(JFrame frame) {
        JLabel generationLabel = new JLabel("Track simulator");
        generationLabel.setFont(new Font(null, Font.PLAIN, 18));
        generationLabel.setBounds(100,850,600,30);
        frame.add(generationLabel, 0);
        return generationLabel;
    }

    public static JFrame createJFrame(AbstractUnitController<? extends Unit> unitController) {
        JFrame frame = new JFrame("Racing");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 1000));
        frame.setLayout(null);
        unitController.getUnits().forEach(unit -> frame.getContentPane().add(new UnitPanel(unit)));
        frame.getContentPane().add(new RacingTrackPanel(unitController.getRacingTrack()));
        frame.pack();
        frame.setVisible(true);
        return frame;
    }
}