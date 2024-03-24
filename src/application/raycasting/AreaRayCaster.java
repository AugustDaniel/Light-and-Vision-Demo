package application.raycasting;

import util.LineSegment;
import util.MathPoint2D;

import java.awt.geom.Point2D;
import java.util.List;

public class AreaRayCaster extends RayCaster {

    public AreaRayCaster(List<LineSegment> lineSegments, List<LineSegment> rays) {
        super(lineSegments, rays);
    }

    @Override
    public void update() {
        if (this.position == null) {
            return;
        }

        this.rays.clear();

        for (LineSegment lineSegment : this.lineSegments) {
            for (Point2D point : lineSegment.getPoints()) {
                Point2D mousePosToSegment = MathPoint2D.subtract(point, this.position);

                LineSegment directRay = new LineSegment(
                        this.position,
                        point
                );

                this.rays.add(directRay);

                double offset = 0.001;
                for (int i = 0; i <= 1; i++) {
                    LineSegment offsetRay = new LineSegment(
                            this.position,
                            MathPoint2D.add(this.position,
                                    MathPoint2D.setAngle(mousePosToSegment, MathPoint2D.getAngle(mousePosToSegment) + offset))
                    );

                    this.rays.add(offsetRay);
                    offset *= -1;
                }
            }
        }

        super.update();
    }
}
