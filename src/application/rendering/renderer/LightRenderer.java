package application.rendering.renderer;

import org.jfree.fx.FXGraphics2D;
import util.LineSegment;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.List;

public class LightRenderer extends AreaRenderer {

    private Area combinedPoly;
    private Point2D pos;

    public LightRenderer(List<LineSegment> rays) {
        super(rays);
        this.pos = new Point2D.Double(0,0);
        this.combinedPoly = new Area();
    }

    @Override
    public void update() {
        super.update();

        if (this.rays.isEmpty()) {
            return;
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
        RadialGradientPaint light = new RadialGradientPaint(pos, 500, new float[]{0.1f, 0.5f} ,new Color[]{Color.YELLOW, Color.BLACK});
        graphics.setPaint(light);
        graphics.fill(combinedPoly);
        graphics.setPaint(null);
    }
}
