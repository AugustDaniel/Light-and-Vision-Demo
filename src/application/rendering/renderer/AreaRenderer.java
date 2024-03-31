package application.rendering.renderer;

import org.jfree.fx.FXGraphics2D;
import util.LineSegment;


import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;

public class AreaRenderer extends Renderer {

    protected Area combinedPoly;
    protected Point2D pos;

    public AreaRenderer(List<LineSegment> rays) {
        super(rays);
        this.combinedPoly = new Area();
        this.pos = new Point2D.Double(0, 0);
    }

    @Override
    public void update() {
        this.toDraw.clear();

        if (this.rays.isEmpty()) {
            return;
        }

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

        this.combinedPoly = new Area();
        this.pos = this.rays.get(0).getStart();

        this.toDraw.forEach(e -> {
                    Area a = new Area(e);
                    combinedPoly.add(a);
                }
        );
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fill(combinedPoly);
        graphics.setColor(Color.BLACK);
        graphics.fill(new Ellipse2D.Double(pos.getX() - 12, pos.getY() - 12, 24, 24));
    }
}
