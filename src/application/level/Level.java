package application.level;

import util.LineSegment;

import java.util.ArrayList;
import java.util.List;

abstract public class Level {

    protected List<LineSegment> lineSegments;

    public Level() {
        this.lineSegments = new ArrayList<>();
        initialiseLevel();
    }

    abstract public void initialiseLevel();

    public List<LineSegment> getLineSegments() {
        return this.lineSegments;
    }
}
