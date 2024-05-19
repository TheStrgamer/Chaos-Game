package org.example.view;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.controller.MainController;


/**
 * <h1>App</h1>
 * The main class for the Chaos Game application.
 * Responsible for starting the application.
 *
 */
public class App extends Application {


  /**
   * the start method for the application.
   * @param primaryStage the primary stage for the application.
   */
  @Override
  public void start(Stage primaryStage) {
    MainController mainController = new MainController(primaryStage);
    primaryStage.setTitle("ChaosGame Application");
    primaryStage.show();

  }



}


