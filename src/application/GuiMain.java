package application;

import application.view.View;
import application.view.renderingview.CubeSpawnerView;
import application.view.renderingview.FirstPersonView;
import application.view.renderingview.MouseFollowerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiMain extends Application {

    private List<View> views;

    public static void main(String[] args) {
        launch(GuiMain.class);
    }

    @Override
    public void init() {
        this.views = new ArrayList<>();
        Collections.addAll(this.views,
                new MouseFollowerView(),
                new CubeSpawnerView(),
                new FirstPersonView()
        );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TabPane pane = new TabPane();

        for (View v : this.views) {
            pane.getTabs().add(new Tab(v.getName(), v.getNode()));
        }

        pane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        primaryStage.setScene(new Scene(pane));
        primaryStage.setTitle("Light and Vision Demo Jayson Leander");
        primaryStage.show();
    }
}
