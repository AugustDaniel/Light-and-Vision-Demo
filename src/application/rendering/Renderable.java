package application.rendering;

import application.Drawable;
import org.jfree.fx.FXGraphics2D;
import util.LineSegment;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Renderable implements Drawable {

    private static final double LINEOFFSET = 0.0000001;

    private Point2D position;
    private Shape shape;
    private Color color;
    private double scale;

    public Renderable(Point2D position, Shape shape, Color color, double scale) {
        this.position = position;
        this.shape = shape;
        this.color = color;
        this.scale = scale;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getPosition() {
        return this.position;
    }

    public AffineTransform getTransformation() {
        AffineTransform transform = new AffineTransform();
        transform.translate(this.position.getX(), this.position.getY());
        transform.scale(this.scale, this.scale);
        return transform;
    }

    public Shape getTransformedShape() {
        return this.getTransformation().createTransformedShape(this.shape);
    }

    public List<LineSegment> getLineSegments() {
        List<LineSegment> lineSegments = new ArrayList<>();
        Rectangle2D bounds = getTransformedShape().getBounds2D();

        double x = bounds.getX();
        double y = bounds.getY();
        double width = bounds.getWidth();
        double height = bounds.getHeight();

        Point2D upperLeft = new Point2D.Double(x, y);
        Point2D upperRight = new Point2D.Double(x + width, y);
        Point2D lowerLeft = new Point2D.Double(x, y + height);
        Point2D lowerRight = new Point2D.Double(x + width, y + height);

        Collections.addAll(lineSegments,
                new LineSegment(
                        upperLeft.getX() - LINEOFFSET,
                        upperLeft.getY(),
                        upperRight.getX() + LINEOFFSET,
                        upperRight.getY()
                ),
                new LineSegment(
                        upperRight.getX(),
                        upperRight.getY() - LINEOFFSET,
                        lowerRight.getX(),
                        lowerRight.getY() + LINEOFFSET
                ),
                new LineSegment(
                        lowerRight.getX() + LINEOFFSET,
                        lowerRight.getY(),
                        lowerLeft.getX() - LINEOFFSET,
                        lowerLeft.getY()
                ),
                new LineSegment(
                        lowerLeft.getX(),
                        lowerLeft.getY() + LINEOFFSET,
                        upperLeft.getX(),
                        upperLeft.getY() - LINEOFFSET
                )
        );

        return lineSegments;
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setColor(this.color);
        graphics.draw(this.getTransformedShape());
    }
}
