package application.level;

import util.LineSegment;

import java.awt.geom.Point2D;
import java.util.Collections;

public class LevelMouseFollower extends Level {

    @Override
    public void initialiseLevel() {
        Level borders = new LevelBorders();

        getLineSegments().addAll(borders.getLineSegments());
        Collections.addAll(this.lineSegments,
                new LineSegment(
                        new Point2D.Double(100, 100),
                        new Point2D.Double(200, 300)
                ),
                new LineSegment(
                        new Point2D.Double(460, 200),
                        new Point2D.Double(600, 300)
                ),
                new LineSegment(
                        new Point2D.Double(300, 90),
                        new Point2D.Double(400, 100)
                ),
                new LineSegment(
                        new Point2D.Double(250, 400),
                        new Point2D.Double(350, 350)
                )
        );
    }
}
