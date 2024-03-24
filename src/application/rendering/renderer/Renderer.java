package application.rendering.renderer;

import application.Drawable;
import application.Updatable;
import util.LineSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract public class Renderer implements Drawable, Updatable {

    protected List<LineSegment> rays;
    protected List<Shape> toDraw;

    public Renderer(List<LineSegment> rays) {
        this.rays = rays;
        this.toDraw = new ArrayList<>();
    }
}
