package application.view.renderingview;

import application.level.LevelBorders;
import application.rendering.Renderable;
import application.rendering.RenderingEngine;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.jfree.fx.FXGraphics2D;
import util.MathPoint2D;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CubeSpawnerView extends RenderingView {
    private List<Renderable> renderables;
    private Point2D mousePos;
    private Point2D distance;
    private Renderable clickedShape;

    @Override
    public void init() {
        this.lineSegments.addAll(new LevelBorders().getLineSegments());

        this.renderables = new ArrayList<>();
        this.renderables.add(
                new Renderable(
                        new Point2D.Double(100, 100),
                        new Rectangle2D.Double(0, 0, 50, 50),
                        Color.BLACK,
                        1
                )
        );

        this.mousePos = null;
        this.distance = null;
        this.clickedShape = null;
    }

    @Override
    public void initEngine() {
        this.engine = new RenderingEngine(this.lineSegments, this.rays, RenderingEngine.Mode.AREA);
    }

    @Override
    public Node getNode() {
        this.mainPane.setCenter(this.canvas);
        FXGraphics2D g2d = new FXGraphics2D(this.canvas.getGraphicsContext2D());

        Button spawnButton = getSpawnButton(g2d);

        this.mainPane.setTop(spawnButton);

        this.canvas.setOnMouseDragged(this::mouseDragged);
        this.canvas.setOnMousePressed(this::mousePressed);
        this.canvas.setOnMouseReleased(this::mouseReleased);

        draw(g2d);
        return this.mainPane;
    }

    private Button getSpawnButton(FXGraphics2D g2d) {
        Button spawnButton = new Button("Spawn cube");

        spawnButton.setOnAction(e -> {
            this.renderables.add(
                    new Renderable(
                            new Point2D.Double(200, 200),
                            new Rectangle2D.Double(0, 0, 50, 50),
                            Color.BLACK,
                            1
                    )
            );
            updateSegments();
            this.engine.update();
            draw(g2d);
        });
        return spawnButton;
    }

    @Override
    public String getName() {
        return "Cube Spawner";
    }

    public void mousePressed(MouseEvent e) {
        this.mousePos = new Point2D.Double(e.getX(), e.getY());

        if (e.isPrimaryButtonDown()) {

            for (Renderable r : this.renderables) {

                if (r.getTransformedShape().contains(this.mousePos)) {
                    this.clickedShape = r;
                    this.distance = MathPoint2D.subtract(r.getPosition(), this.mousePos);
                }
            }
        }

        if (e.isSecondaryButtonDown()) {
            updateSegments();
            this.engine.setPosition(this.mousePos);
        }

        this.engine.update();
        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }


    public void mouseDragged(MouseEvent e) {
        this.mousePos = new Point2D.Double(e.getX(), e.getY());

        if (this.clickedShape != null) {
            this.clickedShape.setPosition(MathPoint2D.add(this.mousePos, this.distance));
            updateSegments();
            this.engine.update();
        }

        draw(new FXGraphics2D(this.canvas.getGraphicsContext2D()));
    }

    private void updateSegments() {
        this.lineSegments.clear();
        for (Renderable r : this.renderables) {
            this.lineSegments.addAll(r.getLineSegments());
        }

        this.lineSegments.addAll(new LevelBorders().getLineSegments());
    }


    public void mouseReleased(MouseEvent e) {
        this.distance = null;
        this.clickedShape = null;
    }

    @Override
    public void draw(FXGraphics2D graphics) {
        graphics.setTransform(new AffineTransform());
        graphics.setBackground(Color.WHITE);
        graphics.clearRect(0, 0, (int) this.canvas.getWidth(), (int) this.canvas.getHeight());

        this.engine.draw(graphics);

        for (Renderable r : this.renderables) {
            r.draw(graphics);
        }
    }
}
