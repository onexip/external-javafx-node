package com.onexip.externaljavafxnode.test;

import com.onexip.externaljavafxnode.ExternalContent;
import insidefx.undecorator.Undecorator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class JavaFXDemo extends ExternalContent {
    public Stage primaryStage;
    public StackPane root;

    public static void main(String[] args) {

    }

    public void start() {
        primaryStage = new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);


        primaryStage.setTitle("Hello World!");
        Button btn = new Button("Say Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    @Override
    public void setPositionBQ(double x, double y) {
        Platform.runLater(() -> {
            primaryStage.setX(x);
            primaryStage.setY(y);
        });
    }

    @Override
    public void setSizeBQ(double x, double y) {
        Platform.runLater(() -> {
            primaryStage.setWidth(x);
            primaryStage.setHeight(y);
        });
    }

    @Override
    public void setCloseRequestBQ() {
        Platform.runLater(() -> primaryStage.close());
    }

    @Override
    public void setIconifiedBQ(boolean iconified) {
        Platform.runLater(() -> primaryStage.setIconified(iconified));
    }


    @Override
    public void init() {
        Platform.runLater(() -> start());
    }
}
