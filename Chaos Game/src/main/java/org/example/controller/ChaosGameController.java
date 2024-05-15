package org.example.controller;


import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

  private boolean autoRunOnDescriptionChange = false;
  private int steps;

  private Color color = new Color(0, 0, 0, 1);

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
   * Method for running the Chaos Game for a set number of iterations.
   */
  public void runIterations() {
    chaosGame.runSteps(steps);
  }

  /**
   * Method for running the Chaos Game for a set number of iterations.
   *
   * @param iterations the number of iterations to run the Chaos Game for.
   */
  public void runIterations(int iterations) {
    chaosGame.runSteps(iterations);
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
   *
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

  /**
   * Method for clearing the canvas of the Chaos Game.
   */
  public void clearCanvas() {
    chaosGame.clearCanvas();
  }

  /**
   * Method for setting the auto run on description change.
   *
   * @param autoRun true if the Chaos Game should run automatically when the description changes,
   *                false otherwise.
   */
  public void setAutoRun(boolean autoRun) {
    this.autoRunOnDescriptionChange = autoRun;
  }

  /**
   * Method for setting the number of steps to run the Chaos Game.
   *
   * @param steps the number of steps to run the Chaos Game.
   */
  public void setSteps(int steps) {
    this.steps = steps;
  }

  /**
   * Method for setting the color of the Chaos Game.
   *
   * @param color the color to set.
   */
  public void setColor(Color color) {
    this.color = color;
    refreshImage();
  }


  /**
   * Method for setting the number of steps to run the Chaos Game.
   *
   * @param stepsString the number of steps to run the Chaos Game.
   */
  public void setSteps(String stepsString) {
    this.steps = getIterations(stepsString);
  }

  /**
   * Updates the canvas size of the Chaos Game. If auto run is true, it runst the chaos game.
   *
   * @param width  the width
   * @param height the height
   */
  public void setCanvasSize(int width, int height) {
    chaosGame.setCanvasSize(width, height);
    if (autoRunOnDescriptionChange) {
      runIterations(steps/5);

    }
  }

  /**
   * Refreshes the image of the Chaos Game. Used when the canvas is updated, or the color is changed.
   */
  private void refreshImage() {
    chaosGameView.setImage(imageFactory.createImage(chaosGame.getCanvas(),color));
  }

  /**
   * Updates the description of the Chaos Game.
   *
   * @param description the new description.
   */
  @Override
  public void updateDescription(ChaosGameDescription description) {
    if (autoRunOnDescriptionChange) {
      runIterations();
    }
  }

  /**
   * Updates the canvas of the Chaos Game.
   *
   * @param canvas the new canvas.
   */
  @Override
  public void updateCanvas(ChaosCanvas canvas) {
    chaosGameView.setImage(imageFactory.createImage(canvas,color));
  }
}
