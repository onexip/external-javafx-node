package com.onexip.externaljavafxnode;

import insidefx.undecorator.Undecorator;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class ExternalNode extends StackPane {

    private Stage mainStage;
    private ExternalContent_AC externalContent;

    private Parent pane;
    private Undecorator undecorator;
    private Scene scene;


    public ExternalNode(ExternalContent_AC externalContent) {
        this.externalContent = externalContent;

        this.setStyle("-fx-background-color: transparent");


        this.sceneProperty().addListener((ObservableValue<? extends Scene> observable, Scene oldScene, Scene newScene) -> {
            if (newScene == null || newScene == scene) return;

            scene = newScene;
            pane = newScene.getRoot();

            newScene.windowProperty().addListener((observable1, oldValue, newWindow) -> {
                undecorator = new Undecorator((Stage) newWindow, (Region) pane);
                scene.setRoot(undecorator);


                Shape clip = Shape.subtract(getShapeFromPane(pane, undecorator), getShapeFromPane(pane, this));
                clip.setFill(javafx.scene.paint.Color.ORANGE);
                pane.setClip(clip);

                undecorator.getStylesheets().addAll("skin/undecorator.css", "/window.css");
                undecorator.setStyle("-fx-background-color: null;");

                scene.setFill(javafx.scene.paint.Color.TRANSPARENT);

                init(newWindow);
            });
        });
    }


    private void init(javafx.stage.Window stage) {

        this.mainStage = (Stage) stage;
        this.mainStage.initStyle(StageStyle.TRANSPARENT);



        mainStage.setOnShown((e)->{
            new Thread(() -> externalContent.init()).start();
        });


        this.widthProperty().addListener(this::sizeChanged);
        this.heightProperty().addListener(this::sizeChanged);


        this.mainStage.xProperty().addListener(this::postionChanged);
        this.mainStage.yProperty().addListener(this::postionChanged);

        this.mainStage.widthProperty().addListener(this::positionAndSizeChanged);
        this.mainStage.heightProperty().addListener(this::positionAndSizeChanged);
        

        mainStage.setOnCloseRequest((WindowEvent event) -> {
            ClosingJob job = new ClosingJob();
            externalContent.uiChanged(job);
        });
        mainStage.iconifiedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
            IconifiedJob job = new IconifiedJob(newValue);
            externalContent.uiChanged(job);
        });

    }

    private double getStageX() {
        return localToScreen(getBoundsInLocal()).getMinX();
    }

    private double getStageY() {
        return localToScreen(getBoundsInLocal()).getMinY();
    }


    public Shape getShapeFromPane(Parent referenceNode, Pane node) {

        Rectangle shape = new Rectangle();

        Bounds dimensionNodeInReferenceNodeCoords2 = referenceNode.sceneToLocal(node.localToScene(node.getBoundsInLocal()));

        shape.setWidth(dimensionNodeInReferenceNodeCoords2.getWidth());
        shape.setHeight(dimensionNodeInReferenceNodeCoords2.getHeight());
        shape.setX(dimensionNodeInReferenceNodeCoords2.getMinX());
        shape.setY(dimensionNodeInReferenceNodeCoords2.getMinY());

        node.boundsInLocalProperty().addListener((observable, oldValue, newValue) -> {
            Bounds dimensionNodeInReferenceNodeCoords = referenceNode.sceneToLocal(node.localToScene(newValue));
            shape.setWidth(dimensionNodeInReferenceNodeCoords.getWidth());
            shape.setHeight(dimensionNodeInReferenceNodeCoords.getHeight());
            shape.setX(dimensionNodeInReferenceNodeCoords.getMinX());
            shape.setY(dimensionNodeInReferenceNodeCoords.getMinY());
        });

        return shape;
    }

    private void sizeChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        Shape clip2 = Shape.subtract(getShapeFromPane(pane, undecorator), getShapeFromPane(pane, this));
        clip2.setFill(Color.ORANGE);
        pane.setClip(clip2);

        SizeJob job = new SizeJob(getWidth(), getHeight());
        externalContent.uiChanged(job);
    }

    private void postionChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        PositionJob job = new PositionJob((int) getStageX(), (int) getStageY());
        externalContent.uiChanged(job);
    }

    private void positionAndSizeChanged(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        Shape clip2 = Shape.subtract(getShapeFromPane(pane, undecorator), getShapeFromPane(pane, this));
        clip2.setFill(Color.ORANGE);
        pane.setClip(clip2);

        PositionJob job = new PositionJob((int) getStageX(), (int) getStageY());
        externalContent.uiChanged(job);
    }
}

