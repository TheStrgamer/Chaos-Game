package org.example.controller;


import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.example.model.chaosGame.ChaosCanvas;
import org.example.model.chaosGame.ChaosGame;

import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.chaosGame.Mandelbrot;
import org.example.model.math.Vector2D;
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
  private final Mandelbrot mandelBrot;
  private final ChaosGameView chaosGameView;

  private final ImageFactory imageFactory;

  private boolean autoRunOnDescriptionChange = false;
  private int steps;
  private int maxIterations = 255;

  private Color color = new Color(1, 0, 0, 1);

  private ChaosCanvas currentCanvas;
  private ChaosGameDescription currentDescription;

  private boolean mandelbrotMode = false;

  /**
   * Constructor for the ChaosGameController class.
   *
   * @param mainController the main controller for the application. Used for switching between
   *                       views.
   * @param chaosGame      the Chaos Game model for the application.
   */
  public ChaosGameController(MainController mainController, ChaosGame chaosGame,
      Mandelbrot mandelBrot) {
    this.chaosGame = chaosGame;
    this.mandelBrot = mandelBrot;
    this.currentCanvas = chaosGame.getCanvas();
    this.currentDescription = chaosGame.getDescription();
    this.chaosGameView = new ChaosGameView(this, mainController);
    this.imageFactory = new ImageFactory();
  }

  /**
   * Sets mandelbrotMode to true or false. This is used to determine if the julia set is calculated
   * with chaos game or mandelbrot.
   */
  public void setMandelbrotMode(boolean mode) {
    this.mandelbrotMode = mode;
    if (autoRunOnDescriptionChange && getTransformType().equals("Julia")) {
      runIterations();
    }
  }

  /**
   * Returns the value of mandelbrotMode.
   *
   * @return true if the mandelbrot set is calculated, false otherwise.
   */
  public boolean getMandelbrotMode() {
    return this.mandelbrotMode;
  }

  /**
   * Checks if the current description is a Julia set and mandelbrotMode is true.
   *
   * @return true if the mandelbrot set is calculated, false otherwise.
   */
  private boolean isMandelbrotMode() {
    return (getTransformType().equals("Julia") && mandelbrotMode);
  }

  /**
   * Returns the transform type of the current description.
   *
   * @return the transform type of the current description.
   */
  private String getTransformType() {
    return currentDescription.getTransformType();
  }

  /**
   * Runs the Chaos Game for a set number of iterations.
   */
  public void runIterations() {
    if (isMandelbrotMode()) {
      mandelBrot.runSteps(maxIterations);
    } else {
      chaosGame.runSteps(steps);
    }
  }

  /**
   * Runs the Chaos Game for a set number of iterations.
   *
   * @param iterations the number of iterations to run the Chaos Game for.
   */
  public void runIterations(int iterations) {
    if (isMandelbrotMode()) {
      mandelBrot.runSteps(maxIterations);
    } else {
      chaosGame.runSteps(iterations);
    }
  }

  /**
   * Converts a string representation of the number of iterations to an integer. Sets the number of
   * iterations to a default value if the string cannot be converted to an integer.
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
   * Returns the layout of the Chaos Game view.
   *
   * @return the layout of the Chaos Game view.
   */
  public VBox getLayout() {
    return chaosGameView.getLayout();
  }

  /**
   * Sets the combo box to empty.
   */
  public void setComboBoxEmpty() {
    chaosGameView.setComboBoxEmpty();
  }

  /**
   * Clears the canvas of the Chaos Game.
   */
  public void clearCanvas() {
    currentCanvas.clear();
    chaosGameView.setImage(imageFactory.createImage(currentCanvas, color));
  }

  /**
   * Sets the auto run on description change.
   *
   * @param autoRun true if the Chaos Game should run automatically when the description changes,
   *                false otherwise.
   */
  public void setAutoRun(boolean autoRun) {
    this.autoRunOnDescriptionChange = autoRun;
  }


  /**
   * Sets the color of the Chaos Game.
   *
   * @param color the color to set.
   */
  public void setColor(Color color) {
    this.color = color;
    refreshImage();
  }

  /**
   * Returns the color of the Chaos Game.
   *
   * @return the color of the Chaos Game.
   */
  public Color getColor() {
    return color;
  }

  /**
   * Sets the number of steps to run the Chaos Game.
   *
   * @param steps the number of steps to run the Chaos Game.
   */
  public void setSteps(int steps) {
    this.steps = steps;
  }

  /**
   * Sets the number of steps to run the Chaos Game. Converts the string to an integer.
   *
   * @param stepsString the number of steps to run the Chaos Game.
   */
  public void setSteps(String stepsString) {
    this.steps = getIterations(stepsString);
  }

  /**
   * Sets the max iterations of the Mandelbrot.
   *
   * @param maxIterations the max iterations to set
   */
  public void setMaxIterations(int maxIterations) {
    this.maxIterations = maxIterations;
  }


  /**
   * Sets the escape radius of the Mandelbrot.
   *
   * @param escapeRadius the escape radius to set
   */
  public void setEscapeRadius(double escapeRadius) {
    mandelBrot.setEscapeRadius(escapeRadius);
  }


  /**
   * Updates the canvas size of the Chaos Game. If auto run is true, it runs the chaos game.
   *
   * @param width  the width
   * @param height the height
   */
  public void setCanvasSize(int width, int height) {
    chaosGame.setCanvasSize(width, height - 60);
    mandelBrot.setCanvasSize(width, height - 60);
    if (autoRunOnDescriptionChange) {
      runIterations(steps / 5);
    }
    chaosGameView.adjustButtonLayout(width);
  }

  /**
   * Refreshes the image of the Chaos Game. Used when the canvas is updated, or the color is
   * changed.
   */
  private void refreshImage() {
    chaosGameView.setImage(imageFactory.createImage(currentCanvas, color));
  }

  /**
   * Changes the zoom of the Chaos Game.
   *
   * @param multiplier the multiplier to change the zoom by.
   */
  public void changeZoom(double multiplier) {
    if (isMandelbrotMode()) {
      mandelBrot.changeZoom(multiplier);
    } else {
      chaosGame.changeZoom(multiplier);
    }
  }

  /**
   * Sets the key listeners for the Chaos Game.
   *
   * @param scene the scene to set the key listeners for.
   */
  public void setKeyListeners(Scene scene) {
    chaosGameView.setKeyListeners(scene);
  }

  /**
   * Moves the canvas of the Chaos Game.
   *
   * @param x0 the x-coordinate to move the canvas by.
   * @param x1 the y-coordinate to move the canvas by.
   */
  public void moveCanvas(double x0, double x1) {
    if (isMandelbrotMode()) {
      mandelBrot.moveCanvas(new Vector2D(x0, x1));
    } else {
      chaosGame.moveCanvas(new Vector2D(x0, x1));
    }
  }


  /**
   * Updates the description of the Chaos Game.
   *
   * @param description the new description.
   */
  @Override
  public void updateDescription(ChaosGameDescription description) {
    currentDescription = description;
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
    currentCanvas = canvas;
    chaosGameView.setImage(imageFactory.createImage(canvas, color));
  }

  /**
   * Sets the description of the Chaos Game.
   *
   * @param currentDescription the new description.
   */
  public void setDescription(ChaosGameDescription currentDescription) {
    this.currentDescription = currentDescription;
    if (currentDescription.getTransformType().equals("Julia")) {
      mandelBrot.setDescription(currentDescription);
    }
    chaosGame.setDescription(currentDescription);

  }

  /**
   * Method for getting the image of the Chaos Game.
   *
   * @return the image of the Chaos Game.
   */
  public Image getImage() {
    return imageFactory.createImage(currentCanvas, color);
  }
}
