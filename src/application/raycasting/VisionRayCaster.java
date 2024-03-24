package application.raycasting;

import util.LineSegment;
import util.MathPoint2D;

import java.util.List;

public class VisionRayCaster extends RayCaster {

    public VisionRayCaster(List<LineSegment> lineSegments, List<LineSegment> rays) {
        super(lineSegments, rays);
    }

    @Override
    public void update() {
        if (this.position == null) {
            return;
        }

        this.rays.clear();

        for (double i = this.angle - 0.75; i <= this.angle + 0.75; i += 0.01) {
            this.rays.add(
                    new LineSegment(
                            this.position,
                            MathPoint2D.scale(MathPoint2D.setAngle(MathPoint2D.UNITVECTOR, i), 10000)
                    )
            );
        }

        super.update();
    }
}
