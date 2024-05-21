package org.example.model.chaosgame;

import java.util.ArrayList;
import java.util.List;
import org.example.model.observer.ChaosGameObserver;

/**
 * <h2>Game.</h2>
 * <p>
 * A class that represents a chaos game. It contains the canvas and description of the game, and
 * notifies observers of changes in the canvas and description.
 * </p>
 *
 * @version 0.4.0
 * @since 0.4.0
 */
abstract class Game {

  protected ChaosCanvas canvas;
  protected ChaosGameDescription description;
  protected int canvasWidth;
  protected int canvasHeight;
  protected final List<ChaosGameObserver> observers = new ArrayList<>();
  protected double zoom = 1.0;


  /**
   * Verifies that the given width and height are positive. Throws an IllegalArgumentException if
   * the given width or height is not positive.
   *
   * @param width  the width to verify
   * @param height the height to verify
   * @throws IllegalArgumentException if the given width or height is not positive
   */
  protected void verifyValidCanvasSize(int width, int height) {
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
  }

  /**
   * Verifies that the given description is not null. Throws an IllegalArgumentException if the
   * given description is null.
   *
   * @param description the description to verify
   * @throws IllegalArgumentException if the given description is null
   */
  protected void verifyNotNullDescription(ChaosGameDescription description) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }
  }

  /**
   * Verifies that the given steps is more than zero. Throws an IllegalArgumentException if the
   * given steps is less than or equal to zero.
   *
   * @param steps the steps to verify
   * @throws IllegalArgumentException if the given steps is less than or equal to zero
   */
  protected void verifyStepsPositive(int steps) {
    if (steps < 0) {
      throw new IllegalArgumentException("Steps need to be more than or equal to 0");
    }
  }

  /**
   * runs the game with the given parameters.
   *
   * @param steps the number of steps to run
   */
  public abstract void runSteps(int steps);


  /**
   * Adds an observer to the list of observers listening to this chaos game.
   *
   * @param observer is the observer to add.
   */
  public void addObserver(ChaosGameObserver observer) {
    observers.add(observer);
  }

  /**
   * Removes an observer from the list of observers listening to this chaos game.
   *
   * @param observer is the observer to remove.
   */
  public void removeObserver(ChaosGameObserver observer) {
    observers.remove(observer);
  }

  /**
   * Notifies all observers that the canvas has changed.
   */
  protected void notifyCanvasChanged() {
    for (ChaosGameObserver observer : observers) {
      observer.updateCanvas(canvas);
    }
  }

  /**
   * Notifies all observers that the description has changed.
   */
  protected void notifyDescriptionChanged() {
    for (ChaosGameObserver observer : observers) {
      observer.updateDescription(description);
    }
  }

  /**
   * Sets the description of the game.
   *
   * @param description the description to set
   */
  public abstract void setDescription(ChaosGameDescription description);


  /**
   * Sets the canvas size of the game.
   *
   * @param width  the width of the canvas
   * @param height the height of the canvas
   * @throws IllegalArgumentException if the given width or height is not positive
   */
  public void setCanvasSize(int width, int height) {
    verifyValidCanvasSize(width, height);
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(),
        description.getMaxCoords());
    this.canvasWidth = width;
    this.canvasHeight = height;
    notifyCanvasChanged();
  }

  /**
   * Returns the canvas of this chaos game.
   *
   * @return the canvas of this chaos game.
   */
  public ChaosCanvas getCanvas() {
    return new ChaosCanvas(canvas);
  }

  /**
   * Returns the description of this chaos game.
   *
   * @return the description of this chaos game.
   */
  public ChaosGameDescription getDescription() {
    return new ChaosGameDescription(description);
  }

}
