//package com.onexip.externaljavafxnode.test;
//
//import com.onexip.externaljavafxnode.ExternalNode;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class Test1 extends Application {
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//    ExternalNode enode;
//
//    @Override
//    public void start(Stage primaryStage) {
//
//        primaryStage.setTitle("Main Stage Test");
//
//        StackPane pane = new StackPane();
//        pane.setStyle("-fx-background-color: red");
//
//        enode = new ExternalNode();
//        enode.setTranslateY(40);
//        enode.setMaxSize(300,300);
//
//        pane.getChildren().add(enode);
//        primaryStage.setScene(new Scene(pane, 400, 400));
//
//        primaryStage.show();
//    }
//}
