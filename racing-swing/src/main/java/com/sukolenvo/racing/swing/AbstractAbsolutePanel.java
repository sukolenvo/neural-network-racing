package com.sukolenvo.racing.swing;

import javax.swing.JPanel;
import java.awt.Dimension;

public class AbstractAbsolutePanel extends JPanel {

    public AbstractAbsolutePanel() {
        setPreferredSize(new Dimension(1000, 1000));
        setBounds(0, 0, 1000, 1000);
    }
}
