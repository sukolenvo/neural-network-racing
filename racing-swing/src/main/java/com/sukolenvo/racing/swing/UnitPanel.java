package com.sukolenvo.racing.swing;

import com.sukolenvo.racing.core.Unit;
import com.sukolenvo.racing.core.track.Point;

import java.awt.Color;
import java.awt.Graphics;

public class UnitPanel extends AbstractAbsolutePanel {

    private final Unit unit;

    public UnitPanel(Unit unit) {
        this.unit = unit;
        setBackground(new Color(0, 0, 0, 0));
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        Point arrowEnd = unit.getPosition().moveTo(unit.getAngle(), 10);
        graphics.setColor(unit.isDisabled() ? Color.RED : Color.GREEN);
        graphics.drawLine((int) unit.getX(), (int) -unit.getY(), (int) arrowEnd.getX(), (int) -arrowEnd.getY());
        Point subline1Start = arrowEnd.moveTo(unit.getAngle() - 180 + 30, 3);
        graphics.drawLine((int) subline1Start.getX(), (int) -subline1Start.getY(),
                (int) arrowEnd.getX(), (int) -arrowEnd.getY());
        Point subline2Start = arrowEnd.moveTo(unit.getAngle() - 180 - 30, 3);
        graphics.drawLine((int) subline2Start.getX(), (int) -subline2Start.getY(),
                (int) arrowEnd.getX(), (int) -arrowEnd.getY());
    }
}
