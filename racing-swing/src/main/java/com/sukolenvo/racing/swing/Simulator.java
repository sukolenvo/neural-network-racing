package com.sukolenvo.racing.swing;

import com.sukolenvo.racing.core.AbstractUnitController;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Simulator {

    private static final long STEP_DELAY_MS = 15;
    private static final int MAX_GENERATIONS = 300;

    private final AbstractUnitController<?> controller;
    private final JFrame frame;
    private final JLabel generationLabel;

    public Simulator(AbstractUnitController<?> controller, JFrame frame, JLabel generationLabel) {
        this.controller = controller;
        this.frame = frame;
        this.generationLabel = generationLabel;
    }

    void beginSimulation() {
        for (int i = 0; i < MAX_GENERATIONS; i ++) {
            generationLabel.setText("Generation: " + i);
            while (!controller.isFinished()) {
                controller.makeStep();
                try {
                    Thread.sleep(STEP_DELAY_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.repaint();
            }
            if (controller.isTrackCompleted()) {
                generationLabel.setText("Track completed in " + i + " generations");
                break;
            }
            controller.reset();
        }
    }
}
