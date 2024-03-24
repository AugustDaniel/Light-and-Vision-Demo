package application.level.premade;

import application.level.LevelGenerator;
import util.LineSegment;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FirstPersonDemoLevel {

    public static List<LineSegment> getLineSegments(double scale) {
        int[][] map = new int[][]{
                {1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };

        return new LinkedList<>(LevelGenerator.getLineSegments(map, 32 * scale, 32 * scale));
    }
}
