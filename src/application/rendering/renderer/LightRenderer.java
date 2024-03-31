package application.rendering.renderer;

import org.jfree.fx.FXGraphics2D;
import util.LineSegment;

import java.awt.*;
import java.util.List;

public class LightRenderer extends AreaRenderer {

    public LightRenderer(List<LineSegment> rays) {
        super(rays);
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        RadialGradientPaint light = new RadialGradientPaint(pos, 500, new float[]{0.1f, 0.6f} ,new Color[]{Color.YELLOW, Color.BLACK});
        graphics.setPaint(light);
        graphics.fill(combinedPoly);
        graphics.setPaint(null);
    }
}
