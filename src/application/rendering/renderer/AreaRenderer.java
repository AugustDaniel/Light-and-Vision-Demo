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

        for (int i = 0; i < this.rays.size(); i++) {
            LineSegment ray = this.rays.get(i);
            LineSegment nextRay = this.rays.get((i + 1) % this.rays.size());

            Polygon p = new Polygon();
            p.addPoint((int) ray.getStart().getX(), (int) ray.getStart().getY());
            p.addPoint((int) ray.getEnd().getX(), (int) ray.getEnd().getY());
            p.addPoint((int) nextRay.getEnd().getX(), (int) nextRay.getEnd().getY());
            p.addPoint((int) nextRay.getStart().getX(), (int) nextRay.getStart().getY());

            this.toDraw.add(p);
        }
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setColor(Color.BLUE);
        for (Shape p : this.toDraw) {
            graphics.fillPolygon((Polygon) p);
        }
    }
}
