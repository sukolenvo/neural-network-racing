package com.sukolenvo.racing.core.track;

import lombok.extern.slf4j.Slf4j;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Simple track implementation with static 8 points. Approximate view:
 * *****
 * *
 * ***   *
 * *   *
 * *   *
 * *   *
 * *   *
 * *   *
 *
 * @author vadym
 */
@Slf4j
public class SimpleRacingTrack implements RacingTrack {

    private static final Point A = new Point(100, -100);
    private static final Point B = new Point(521, -100);
    private static final Point C = new Point(900, -479);
    private static final Point D = new Point(900, -900);
    private static final Point E = new Point(100, -150);
    private static final Point F = new Point(500, -150);
    private static final Point G = new Point(850, -500);
    private static final Point H = new Point(850, -900);

    private static final List<Border> LINES = Arrays.asList(new Border(A, B),
            new Border(C, B), new Border(C, D), new Border(E, F), new Border(F, G), new Border(G, H),
            new Border(A, E), new Border(H, D));

    public int getWidth() {
        return 1000;
    }

    public int getHeight() {
        return 1000;
    }

    public BufferedImage getBackground() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        Graphics2D graphics = image.createGraphics();
        graphics.setBackground(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(Color.BLACK);
        BasicStroke bs = new BasicStroke(2);
        graphics.setStroke(bs);
        graphics.drawLine((int) A.getX(), (int) -A.getY(), (int) B.getX(), (int) -B.getY());
        graphics.drawLine((int) B.getX(), (int) -B.getY(), (int) C.getX(), (int) -C.getY());
        graphics.drawLine((int) C.getX(), (int) -C.getY(), (int) D.getX(), (int) -D.getY());
        graphics.drawLine((int) E.getX(), (int) -E.getY(), (int) F.getX(), (int) -F.getY());
        graphics.drawLine((int) F.getX(), (int) -F.getY(), (int) G.getX(), (int) -G.getY());
        graphics.drawLine((int) G.getX(), (int) -G.getY(), (int) H.getX(), (int) -H.getY());
        return image;
    }

    @Override
    public Point getWall(double xFrom, double yFrom, double direction) throws WallNotFoundException {
        Line vector = new Line(xFrom, yFrom, direction);
        return LINES.stream()
                .map(line -> {
                    try {
                        Point crossPoint = vector.crossPoint(line);
                        if (vector.sameAngle(new Line(xFrom, yFrom, crossPoint.getX(), crossPoint.getY()))
                                && line.isOnBorder(crossPoint)) {
                            return crossPoint;
                        }
                    } catch (NoCrossPointException e) {
                        log.debug("No cross point", e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingDouble(point -> point.distanceTo(new Point(xFrom, yFrom))))
                .findFirst()
                .orElseThrow(() -> new WallNotFoundException("Wall not found for (" + xFrom + ";" + yFrom + ")" +
                        " with angle: " + direction));
    }
}
