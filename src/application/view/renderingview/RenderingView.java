package application.view.renderingview;

import application.Drawable;
import application.rendering.RenderingEngine;
import application.view.View;
import javafx.scene.layout.BorderPane;
import org.jfree.fx.ResizableCanvas;
import util.LineSegment;

import java.util.ArrayList;
import java.util.List;

abstract public class RenderingView implements View, Drawable {

    protected ResizableCanvas canvas;
    protected BorderPane mainPane;
    protected List<LineSegment> lineSegments;
    protected List<LineSegment> rays;
    protected RenderingEngine engine;

    public RenderingView() {
        this.mainPane = new BorderPane();
        this.canvas = new ResizableCanvas(this::draw, this.mainPane);
        this.lineSegments = new ArrayList<>();
        this.rays = new ArrayList<>();
        init();
        initEngine();
    }

    abstract public void init();

    abstract public void initEngine();
}
