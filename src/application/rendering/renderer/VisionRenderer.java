package application.rendering.renderer;

import org.jfree.fx.FXGraphics2D;
import util.LineSegment;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;

public class VisionRenderer extends Renderer {

    private static final int LENGTH_THRESHOLD = 125;
    private static final int WALL_LENGTH = 400 * 32;
    private static final int MIDDLE = 200;

    public VisionRenderer(List<LineSegment> rays) {
        super(rays);
    }

    @Override
    public void update() {
        this.toDraw.clear();

        double xPos = 0;
        for (LineSegment ray : this.rays) {
            double length = ((WALL_LENGTH) / ray.getStart().distance(ray.getEnd()));

            if(length > LENGTH_THRESHOLD) {
                length = LENGTH_THRESHOLD;
            }

            this.toDraw.add(new Line2D.Double(
                            new Point2D.Double(xPos, MIDDLE - length),
                            new Point2D.Double(xPos, MIDDLE + length)
                    )
            );

            xPos += 2;
        }
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setColor(Color.BLUE);

        for(Shape s : this.toDraw) {
            graphics.draw(s);
        }
    }
}
