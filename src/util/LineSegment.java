package util;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class LineSegment {
    private Point2D start;
    private Point2D end;

    public LineSegment(Point2D start, Point2D end) {
        this.start = start;
        this.end = end;
    }

    public LineSegment(double x1, double y1, double x2, double y2) {
        this(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2));
    }

    public Point2D getStart() {
        return this.start;
    }

    public Point2D getEnd() {
        return this.end;
    }

    public void setEnd(Point2D end) {
        this.end = end;
    }

    public Point2D[] getPoints() {
        return new Point2D[]{this.start, this.end};
    }

    public Point2D getDirection() {
        return MathPoint2D.subtract(this.end, this.start);
    }

    public void draw(Graphics2D graphics) {
        graphics.draw(new Line2D.Double(
                this.start.getX(),
                this.start.getY(),
                this.end.getX(),
                this.end.getY()));
    }
}
