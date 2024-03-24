package application.level.premade;

import application.level.LevelGenerator;
import util.LineSegment;

import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MouseFollowerLevel {

    public static List<LineSegment> getLineSegments(double screenWidth, double screenHeight) {
        List<LineSegment> lineSegments = new LinkedList<>(LevelGenerator.getLevelBorders(screenWidth, screenHeight));

        Collections.addAll(lineSegments,
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

        return lineSegments;
    }
}
