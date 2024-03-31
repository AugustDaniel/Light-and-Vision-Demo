package application.view.renderingview;

import application.level.premade.MouseFollowerLevel;
import application.rendering.RenderingEngine;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;
import util.LineSegment;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class MouseFollowerView extends RenderingView {

    @Override
    public void init() {
        this.lineSegments.addAll(MouseFollowerLevel.getLineSegments(canvas.getWidth(), canvas.getHeight() + 25));
    }

    @Override
    public void initEngine() {
        this.engine = new RenderingEngine(this.lineSegments, this.rays, RenderingEngine.Mode.LIGHT);
        this.engine.update();
    }

    @Override
    public Node getNode() {
        this.mainPane.setCenter(this.canvas);
        this.canvas.setOnMouseMoved(this::mouseMoved);
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
        return this.mainPane;
    }

    private void mouseMoved(MouseEvent mouseEvent) {
        this.engine.setPosition(new Point2D.Double(mouseEvent.getX(), mouseEvent.getY()));
        this.engine.update();
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }

    @Override
    public String getName() {
        return "Mouse Follower";
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.BLACK);
        graphics.clearRect(0, 0, (int) this.canvas.getWidth(), (int) this.canvas.getHeight());

        this.engine.draw(graphics);

        graphics.setColor(Color.WHITE);
        for (LineSegment s : this.lineSegments) {
            s.draw(graphics);
        }
        graphics.setColor(Color.BLACK);
    }
}
