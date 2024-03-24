package application.level;

import application.rendering.Renderable;
import util.LineSegment;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

public class LevelGenerator {

    public static List<LineSegment> getLineSegments(int[][] map, double blockWidth, double blockHeight) {
        List<LineSegment> lineSegments = new LinkedList<>();

        for (int x = 0; x < map.length; x++) {

            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == 1) {
                    lineSegments.addAll(
                            new Renderable(
                                    new Point2D.Double(y * blockWidth, x * blockWidth),
                                    new Rectangle2D.Double(0, 0, blockWidth, blockHeight),
                                    Color.PINK,
                                    1)
                                    .getLineSegments()
                    );
                }
            }
        }

        return lineSegments;
    }

    public static List<LineSegment> getLevelBorders(double levelWidth, double levelHeight) {
        return new LinkedList<>(
                new Renderable(new Point2D.Double(0, 0),
                new Rectangle2D.Double(0, 0, levelWidth, levelHeight),
                Color.PINK,
                1)
                .getLineSegments()
        );
    }
}
