package application.level;

import util.LineSegment;

import java.util.LinkedList;
import java.util.List;

public class LevelTest extends Level {

    private final int WIDTH = 650;
    private final int HEIGHT = 600;

    @Override
    public void initialiseLevel() {
        int[][] level = new int[][] {
                {1,1,1,0,0},
                {0,0,0,0,0},
                {1,1,0,1,1},
                {0,0,1,0,0}
        };

        lineSegments.addAll(LevelGenerator.getLineSegments(level, 64, 64));
        lineSegments.addAll(LevelGenerator.getLevelBorders(WIDTH, HEIGHT));
    }
}
