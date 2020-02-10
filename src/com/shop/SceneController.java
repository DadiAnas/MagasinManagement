package com.shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class SceneController  extends Application {
    private HashMap<String, String> screenMap = new HashMap<>();
    public HashMap<String, Stage> stageList = new HashMap<>();
    public Stage stage;
    public Scene scene;
    private static SceneController sceneController;

    public static SceneController getSceneController(){
        if(sceneController==null){
            sceneController = new SceneController();
        }
        return sceneController;
    }
    public SceneController() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
    }
    public void show(String sceneName) throws Exception {
        Stage primaryStage= new Stage();
        Parent pane = FXMLLoader.load(getClass().getResource(screenMap.get(sceneName)));
        Scene newScene = new Scene( pane );
        primaryStage.setScene(newScene);
        primaryStage.setTitle("Magasin");
        primaryStage.getIcons().add(new Image("images/window_icon.png"));
        this.stage = primaryStage;
        stageList.put("2",primaryStage);
        start(primaryStage);
    }

    public Scene getScene(){
        return this.scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return this.stage;
    }
    public void addScene(String name, String FXML){
        screenMap.put(name, FXML);
    }

    public void setScene(String name) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(screenMap.get(name)));
        this.scene = new Scene( pane );
        stage.setScene(this.scene);
    }
}