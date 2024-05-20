package org.example.view;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.controller.MainController;


/**
 * <h2>App</h2>
 * <p>
 * The main class for the Chaos Game application. Responsible for starting the application.
 * </p>
 * <p>
 * The class extends the Application class from JavaFX and overrides the start method.
 * </p>
 *
 * @version 0.4.0
 * @since 0.3.0
 */
public class App extends Application {


  /**
   * the start method for the application.
   *
   * @param primaryStage the primary stage for the application.
   */
  @Override
  public void start(Stage primaryStage) {
    MainController mainController = new MainController(primaryStage);
    primaryStage.setTitle("ChaosGame Application");
    primaryStage.show();

  }


}


