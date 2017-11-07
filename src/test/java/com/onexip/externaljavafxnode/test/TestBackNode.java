package com.onexip.externaljavafxnode.test;

import com.onexip.externaljavafxnode.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestBackNode extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        pane.setStyle("-fx-background-color: black;");

        JOGL2NewtDemo externalContent = new JOGL2NewtDemo();
//        SwingDemo externalContent = new SwingDemo();
//        JavaFXDemo externalContent = new JavaFXDemo();

        ExternalNode bnode = new ExternalNode(externalContent);
        bnode.setMaxSize(200, 200);
        pane.getChildren().add(bnode);

        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();




    }



}

