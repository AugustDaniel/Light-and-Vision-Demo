package application.raycasting;

import application.Updatable;
import util.LineSegment;
import util.MathPoint2D;

import java.awt.geom.Point2D;
import java.util.List;

abstract public class RayCaster implements Updatable {

    protected List<LineSegment> lineSegments;
    protected List<LineSegment> rays;
    protected Point2D position;
    protected double angle;

    public RayCaster(List<LineSegment> lineSegments, List<LineSegment> rays) {
        this.lineSegments = lineSegments;
        this.rays = rays;
        this.angle = 0;
    }

    @Override
    public void update() {
        checkRays();
        sortRays();
    }

    public void checkRays() {
        for (LineSegment ray : this.rays) {

            double closestIntersection = 10000000;
            Point2D rayEnd = ray.getEnd();

            for (LineSegment lineSegment : this.lineSegments) {
                Point2D intersection = getIntersection(ray, lineSegment);

                if (intersection != null) {
                    double distance = ray.getStart().distance(intersection);

                    if (distance < closestIntersection) {
                        closestIntersection = distance;
                        rayEnd = intersection;
                    }
                }
            }

            ray.setEnd(rayEnd);
        }
    }

    // https://ncase.me/sight-and-light/
    public Point2D getIntersection(LineSegment i, LineSegment j) {
        //check if lines are parallel
        if (MathPoint2D.normalize(i.getDirection())
                .equals(MathPoint2D.normalize(j.getDirection())
                )) {
            return null;
        }

        // defining ray
        double r_px = i.getStart().getX();
        double r_dx = i.getDirection().getX();
        double r_py = i.getStart().getY();
        double r_dy = i.getDirection().getY();

        // defining segment
        double s_px = j.getStart().getX();
        double s_dx = j.getDirection().getX();
        double s_py = j.getStart().getY();
        double s_dy = j.getDirection().getY();

        // solve for T2
        double T2 = (r_dx * (s_py - r_py) + r_dy * (r_px - s_px)) / (s_dx * r_dy - s_dy * r_dx);

        // Plug the value of T2 to get T1
        double T1 = (s_px + s_dx * T2 - r_px) / r_dx;

        // check for intersection
        if (T1 > 0
                && (T2 > 0 && T2 < 1)) {

            // fill in t1 in equation of ray
            return MathPoint2D.add(
                    i.getStart(),
                    MathPoint2D.scale(i.getDirection(), T1));

        }

        return null;
    }

    private void sortRays() {
        rays.sort((r1, r2) -> {
                    double relativeAngle1 = adjustAngle(MathPoint2D.getAngle(r1.getDirection()) - angle);
                    double relativeAngle2 = adjustAngle(MathPoint2D.getAngle(r2.getDirection()) - angle);
                    return Double.compare(relativeAngle1, relativeAngle2);
                }
        );
    }

    private double adjustAngle(double angle) {
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public Point2D getPosition() {
        return this.position;
    }
}
