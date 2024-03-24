package util;

import java.awt.geom.Point2D;

public final class MathPoint2D {

    public static final Point2D UNITVECTOR = new Point2D.Double(1, 0);

    public static Point2D add(Point2D p1, Point2D p2) {
        return new Point2D.Double(
                p1.getX() + p2.getX(),
                p1.getY() + p2.getY()
        );
    }

    public static Point2D subtract(Point2D p1, Point2D p2) {
        return new Point2D.Double(
                p1.getX() - p2.getX(),
                p1.getY() - p2.getY()
        );
    }

    public static double getAngle(Point2D p) {
        return Math.atan2(p.getY(), p.getX());
    }

    public static Point2D setAngle(Point2D p, double angle) {
        double length = getLength(p);
        double newX = length * Math.cos(angle);
        double newY = length * Math.sin(angle);

        return new Point2D.Double(newX, newY);
    }

    public static Point2D scale(Point2D p, double scale) {
        return new Point2D.Double(p.getX() * scale, p.getY() * scale);
    }

    public static double getLength(Point2D p) {
        return Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
    }

    public static Point2D normalize(Point2D p) {
        return new Point2D.Double(
                p.getX() / getLength(p),
                p.getY() / getLength(p)
        );
    }
}
