package application.rendering;

import application.Drawable;
import application.Updatable;
import application.raycasting.AreaRayCaster;
import application.raycasting.RayCaster;
import application.raycasting.VisionRayCaster;
import application.rendering.renderer.AreaRenderer;
import application.rendering.renderer.LightRenderer;
import application.rendering.renderer.Renderer;
import application.rendering.renderer.VisionRenderer;
import org.jfree.fx.FXGraphics2D;
import util.LineSegment;

import java.awt.geom.Point2D;
import java.util.List;


public class RenderingEngine implements Drawable, Updatable {

    private RayCaster rayCaster;
    private Renderer renderer;

    public enum Mode {AREA, VISION, LIGHT}

    public RenderingEngine(List<LineSegment> lineSegments, List<LineSegment> rays, Mode mode) {
        switch (mode) {
            case AREA:
                this.rayCaster = new AreaRayCaster(lineSegments, rays);
                this.renderer = new AreaRenderer(rays);
                break;
            case LIGHT:
                this.rayCaster = new AreaRayCaster(lineSegments, rays);
                this.renderer = new LightRenderer(rays);
                break;
            case VISION:
                this.rayCaster = new VisionRayCaster(lineSegments, rays);
                this.renderer = new VisionRenderer(rays);
                break;
        }
    }

    @Override
    public void update() {
        this.rayCaster.update();
        this.renderer.update();
    }

    public void setPosition(Point2D position) {
        this.rayCaster.setPosition(position);
    }

    public void setAngle(double angle) {
        this.rayCaster.setAngle(angle);
    }

    public Point2D getPosition() {
        return this.rayCaster.getPosition();
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        this.renderer.draw(graphics);
    }
}
