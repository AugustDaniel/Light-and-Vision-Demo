package application.level;

import util.LineSegment;

import java.awt.geom.Point2D;
import java.util.Collections;

public class LevelFirstPersonDemo extends Level {

    @Override
    public void initialiseLevel() {
        Collections.addAll(this.lineSegments,
                new LineSegment(
                        new Point2D.Double(0, 0),
                        new Point2D.Double(0, 400)
                ),
                new LineSegment(
                        new Point2D.Double(0, 0),
                        new Point2D.Double(0, 100)
                ),
                new LineSegment(
                        new Point2D.Double(100, 0),
                        new Point2D.Double(100, 300)
                ),
                new LineSegment(
                        new Point2D.Double(0, 400),
                        new Point2D.Double(300, 400)
                ),
                new LineSegment(
                        new Point2D.Double(100, 300),
                        new Point2D.Double(500, 300)
                ),
                new LineSegment(
                        new Point2D.Double(0, 400),
                        new Point2D.Double(0, 700)
                ),
                new LineSegment(
                        new Point2D.Double(0, 700),
                        new Point2D.Double(500, 700)
                ),
                new LineSegment(
                        new Point2D.Double(500, 700),
                        new Point2D.Double(500, 300)
                )
        );
    }
}
