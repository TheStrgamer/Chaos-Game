package org.example.controller;


import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import org.example.model.chaosGame.ChaosCanvas;
import org.example.model.chaosGame.ChaosGame;

import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.observer.ChaosGameObserver;
import org.example.model.factory.ImageFactory;
import org.example.view.ChaosGameView;

/**
 * <h1>ChaosGameController</h1>
 * The controller class for the Chaos Game page of the application. It handles the logic for the
 * Chaos Game page, and is responsible for running the Chaos Game.
 */
public class ChaosGameController implements ChaosGameObserver {

  private final ChaosGame chaosGame;
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
  }

  /**
   * Method for running the Chaos Game for a given number of iterations.
   *
   * @param steps the number of iterations to run the Chaos Game for.
   */
  public void runIterations(int steps) {
    chaosGame.runSteps(steps);
  }

  /**
   * Method for converting a string representation of the number of iterations to an integer. Sets
   * the number of iterations to a default value if the string cannot be converted to an integer.
   *
   * @param iterations the string representation of the number of iterations.
   * @return the number of iterations to run the Chaos Game for.
   */
  public int getIterations(String iterations) {
    int defaultIterations = 0;
    int minIterations = 0;
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
   * Method for setting the combo box to empty.
   */
  public void setComboBoxEmpty() {
    chaosGameView.setComboBoxEmpty();
  }

  public void clearCanvas() {
    chaosGame.clearCanvas();
  }

  @Override
  public void updateDescription(ChaosGameDescription description) {
  }

  @Override
  public void updateCanvas(ChaosCanvas canvas) {
    chaosGameView.setImage(imageFactory.createImage(canvas));
  }

  /**
   * Method for getting the image of the Chaos Game.
   *
   * @return the image of the Chaos Game.
   */
  public Image getImage() {
    return imageFactory.createImage(chaosGame.getCanvas());
  }
}
