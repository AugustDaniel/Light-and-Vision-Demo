package application.rendering.renderer;

import org.jfree.fx.FXGraphics2D;
import util.LineSegment;

import java.awt.*;
import java.util.List;

public class AreaRenderer extends Renderer {

    public AreaRenderer(List<LineSegment> rays) {
        super(rays);
    }

    @Override
    public void update() {
        this.toDraw.clear();

        int counter = 0;
        for (LineSegment ray : this.rays) {
            LineSegment nextRay = this.rays.get((counter + 1) % this.rays.size());

            Polygon p = new Polygon();
            p.addPoint((int) ray.getStart().getX(), (int) ray.getStart().getY());
            p.addPoint((int) ray.getEnd().getX(), (int) ray.getEnd().getY());
            p.addPoint((int) nextRay.getEnd().getX(), (int) nextRay.getEnd().getY());
            p.addPoint((int) nextRay.getStart().getX(), (int) nextRay.getStart().getY());

            this.toDraw.add(p);
            counter++;
        }
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setColor(Color.BLUE);
        for (Shape p : this.toDraw) {
            graphics.fill(p);
        }
    }
}
