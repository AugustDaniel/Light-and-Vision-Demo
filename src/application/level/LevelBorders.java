package application.level;

import util.LineSegment;

import java.awt.geom.Point2D;
import java.util.Collections;

public class LevelBorders extends Level {

    private final int WIDTH = 650;
    private final int HEIGHT = 600;

    @Override
    public void initialiseLevel() {
        Collections.addAll(this.lineSegments,
                new LineSegment(
                        new Point2D.Double(0, 0),
                        new Point2D.Double(WIDTH, 0)
                ),
                new LineSegment(
                        new Point2D.Double(WIDTH, 0),
                        new Point2D.Double(WIDTH, HEIGHT)
                ),
                new LineSegment(
                        new Point2D.Double(WIDTH, HEIGHT),
                        new Point2D.Double(0, HEIGHT)
                ),
                new LineSegment(
                        new Point2D.Double(0, HEIGHT),
                        new Point2D.Double(0, 0)
                )
        );
    }
}
