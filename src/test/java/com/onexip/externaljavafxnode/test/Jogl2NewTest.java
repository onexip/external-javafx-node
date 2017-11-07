//package com.onexip.externaljavafxnode.test;
//
//import com.jogamp.newt.Window;
//import com.jogamp.newt.opengl.GLWindow;
//import com.jogamp.opengl.GLCapabilities;
//import com.jogamp.opengl.GLProfile;
//import com.onexip.externaljavafxnode.test.JOGL2NewtDemo;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.stage.Stage;
//
//public class Jogl2NewTest extends Application {
//
//    private JOGL2NewtDemo newt;
//
//    public static void main(String[] args) {
//            launch(args);
//        }
//
//        @Override
//        public void start(Stage primaryStage) {
//
//            // Get the default OpenGL profile, reflecting the best for your running platform
//            GLProfile glp = GLProfile.getDefault();
//            // Specifies a set of OpenGL capabilities, based on your profile.
//            GLCapabilities caps = new GLCapabilities(glp);
//            // Create the OpenGL rendering canvas
//            GLWindow window = GLWindow.create(caps);
//
//
//            Thread newtRunner = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    JOGL2NewtDemo newt = new JOGL2NewtDemo(window);
//                }
//            });
//
//            newtRunner.start();
//
//            StackPane pane = new StackPane();
//            pane.setStyle("-fx-background-color: red");
//
//            //pane.getChildren().add();
//
//            primaryStage.setScene(new Scene(pane));
//
//            primaryStage.show();
//
//        }
//
//
//}
//
//
