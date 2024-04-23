package org.example.controller;


import javafx.scene.layout.VBox;
import org.example.model.ChaosGame;

import org.example.model.ImageFactory;
import org.example.view.ChaosGameView;

/**
 * <h1>ChaosGameController</h1>
 * The controller class for the Chaos Game page of the application. It handles the logic for the
 * Chaos Game page, and is responsible for running the Chaos Game.
 */
public class ChaosGameController {

  private ChaosGame chaosGame;
  private final ChaosGameView chaosGameView;

  private final MainController mainController;

  private final ImageFactory imageFactory;

  /**
   * Constructor for the ChaosGameController class.
   *
   * @param mainController the main controller for the application. Used for switching between
   *                       views.
   * @param chaosGame      the Chaos Game model for the application.
   */
  public ChaosGameController(MainController mainController, ChaosGame chaosGame) {
    this.mainController = mainController;
    this.chaosGame = chaosGame;
    this.chaosGameView = new ChaosGameView(this, mainController);
    this.imageFactory = new ImageFactory();

  }

  /**
   * Method for running the Chaos Game for a given number of iterations.
   *
   * @param iterations string representation of the number of iterations to run the Chaos Game for.
   */
  public void runIterations(String iterations) {
    int steps = getIterations(iterations);
    chaosGame.runSteps(steps);
    chaosGameView.setImage(imageFactory.createImage(chaosGame.getCanvas()));
  }

  /**
   * Method for running the Chaos Game for a given number of iterations.
   *
   * @param steps the number of iterations to run the Chaos Game for.
   */
  public void runIterations(int steps) {
    chaosGame.runSteps(steps);
    chaosGameView.setImage(imageFactory.createImage(chaosGame.getCanvas()));
  }

  /**
   * Method for converting a string representation of the number of iterations to an integer. Sets
   * the number of iterations to a default value if the string cannot be converted to an integer.
   *
   * @param iterations the string representation of the number of iterations.
   * @return the number of iterations to run the Chaos Game for.
   */
  public int getIterations(String iterations) {
    int defaultIterations = 10000000;
    int minIterations = 1;
    try {
      return Math.max(Integer.parseInt(iterations), minIterations);
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
      return defaultIterations;
    }
  }

  /**
   * Method for getting the layout of the Chaos Game view.
   * @return the layout of the Chaos Game view.
   */
  public VBox getLayout() {
    return chaosGameView.getLayout();
  }

  /**
   * Method for setting the Chaos Game model for the controller.
   *
   * @param chaosGame the Chaos Game model to set.
   */
  public void setChaosGame(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  /**
   * Method for refreshing the image of the Chaos Game view.
   */
  public void refreshImage() {
    chaosGameView.setImage(imageFactory.createImage(chaosGame.getCanvas()));
  }


}
