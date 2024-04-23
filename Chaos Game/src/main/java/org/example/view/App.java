package org.example.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.controller.ChaosGameController;
import org.example.controller.MainController;
import org.example.model.ChaosGame;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameDescriptionFactory;
import org.example.model.ImageFactory;

public class App extends Application {

//  public static void main(String[] args) {

//    Gui gui = new Gui();
//    gui.run();

//  }

  @Override
  public void start(Stage primaryStage) {
    MainController mainController = new MainController(primaryStage);
    mainController.switchToChaosGameView();
    primaryStage.setTitle("ChaosGame Application");
    primaryStage.show();

  }



}


