package application.view.renderingview;

import application.level.LevelFirstPersonDemo;
import application.rendering.RenderingEngine;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import org.jfree.fx.FXGraphics2D;
import util.LineSegment;
import util.MathPoint2D;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class FirstPersonView extends RenderingView {
    private double angle;

    @Override
    public void init() {
        this.angle = Math.PI / 2;
        this.lineSegments = new LevelFirstPersonDemo().getLineSegments();
    }

    @Override
    public void initEngine() {
        this.engine = new RenderingEngine(this.lineSegments, this.rays, RenderingEngine.Mode.VISION);
        this.engine.setPosition(new Point2D.Double(50, 50));
        this.engine.setAngle(this.angle);
        this.engine.update();
    }

    @Override
    public Node getNode() {
        this.mainPane.setCenter(this.canvas);
        this.canvas.setOnMousePressed(this::mousePressed);
        this.canvas.setOnScroll(this::mouseScrolled);

        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
        return this.mainPane;
    }

    private void mouseScrolled(ScrollEvent scrollEvent) {
        this.angle += scrollEvent.getDeltaY() / 150;

        this.engine.setAngle(this.angle);
        this.engine.update();
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }

    private void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.isPrimaryButtonDown()) {
            this.engine.setPosition(
                    MathPoint2D.add(this.engine.getPosition(), MathPoint2D.scale(MathPoint2D.setAngle(MathPoint2D.UNITVECTOR, this.angle), 10))
            );
        }

        this.engine.update();
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }

    @Override
    public String getName() {
        return "First Person Perspective";
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, (int) this.canvas.getWidth(), (int) this.canvas.getHeight());

        this.engine.draw(graphics);

        graphics.setTransform(new AffineTransform());

        graphics.translate(this.canvas.getWidth() / 2, 0);
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, (int) this.canvas.getWidth(), (int) this.canvas.getHeight());

        for (LineSegment ray : this.rays) {
            ray.draw(graphics);
        }

        for (LineSegment line : this.lineSegments) {
            line.draw(graphics);
        }
    }
}
