package org.example.model.chaosGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.model.observer.ChaosGameObserver;
import org.example.model.transform.Transform2D;
import org.example.model.math.Vector2D;

/**
 * <h1>ChaosGame</h1>
 * A class that represents a chaos game. The game is generated based on a description and a canvas.
 */
public class ChaosGame {

  private ChaosCanvas canvas;
  private ChaosGameDescription description;

  private final Vector2D currentPoint;

  private final Random random;

  private int canvasWidth;
  private int canvasHeight;

  double zoom = 1.0;

  private final List<ChaosGameObserver> observers = new ArrayList<>();

  /**
   * Verifies that the given width and height are positive. Throws an IllegalArgumentException if
   * the given width or height is not positive.
   *
   * @param width  the width to verify
   * @param height the height to verify
   * @throws IllegalArgumentException if the given width or height is not positive
   */
  private void verifyValidCanvasSize(int width, int height) {
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
  private void verifyNotNullDescription(ChaosGameDescription description) {
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
  private void verifyStepsPositive(int steps) {
    if (steps < 0) {
      throw new IllegalArgumentException("Steps need to be more than or equal to 0");
    }
  }

  /**
   * Constructs a new ChaosGame object with the given description and creates a canvas based on the
   * values.
   *
   * @param description is the description to use.
   * @param width       is the width of the canvas.
   * @param height      is the height of the canvas.
   */
  public ChaosGame(ChaosGameDescription description, int width, int height) {
    verifyNotNullDescription(description);
    verifyValidCanvasSize(width, height);

    this.canvasWidth = width;
    this.canvasHeight = height;
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
    setDescription(description);

  }

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
  private void notifyCanvasChanged() {
    for (ChaosGameObserver observer : observers) {
      observer.updateCanvas(canvas);
    }
  }

  /**
   * Notifies all observers that the description has changed.
   */
  private void notifyDescriptionChanged() {
    for (ChaosGameObserver observer : observers) {
      observer.updateDescription(description);
    }
  }

  /**
   * Sets the description of this chaos game. The canvas is reset to a new canvas based on the
   * description. Notifies all observers that the description has changed.
   *
   * @param description is the description to use.
   */
  public void setDescription(ChaosGameDescription description) {
    verifyNotNullDescription(description);
    currentPoint.setX0(0);
    currentPoint.setX1(0);
    this.description = description;
    this.canvas = new ChaosCanvas(canvasWidth, canvasHeight, description.getMinCoords(),
        description.getMaxCoords());
    zoom = 1.0;
    notifyCanvasChanged();

    notifyDescriptionChanged();
  }
  /**
   * Updates the description of this chaos game. The canvas is reset to a new canvas based on the
   * description. Notifies all observers that the description has changed.
   *
   */
  public void updateDescription() {
    currentPoint.setX0(0);
    currentPoint.setX1(0);
    this.canvas = new ChaosCanvas(canvasWidth, canvasHeight, description.getMinCoords(),
        description.getMaxCoords());
    notifyDescriptionChanged();
    notifyCanvasChanged();
  }

  /**
   * Sets the canvas size of this chaos game. The canvas is reset to a new canvas with the given
   * parameters. Notifies all observers that the canvas has changed.
   *
   * @param width  is the width of the canvas.
   * @param height is the height of the canvas.
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
    return canvas;
  }

  /**
   * Returns the description of this chaos game.
   *
   * @return the description of this chaos game.
   */
  public ChaosGameDescription getDescription() {
    return description;
  }

  /**
   * Clears the canvas of this chaos game. Notifies all observers that the canvas has changed.
   */
  public void clearCanvas() {
    canvas.clear();
    notifyCanvasChanged();
  }

  /**
   * Changes the zoom of the canvas.
   *
   * @param multiplier the multiplier to change the zoom with.
   */
  public void changeZoom(double multiplier) {
    zoom += zoom*multiplier;
    description.changeZoom(multiplier);
    updateDescription();
  }

  /**
   * Moves the canvas by the given vector.
   *
   * @param vector the vector to move the canvas by.
   */
  public void moveCanvas(Vector2D vector) {
    description.moveCanvas(vector);
    updateDescription();

  }



  /**
   * Runs the chaos game for the given number of steps. Notifies all observers that the canvas has
   * changed after all steps have been run.
   *
   * @param steps is the number of steps to run.
   */
  public void runSteps(int steps) {
    verifyStepsPositive(steps);
    int sumOfWeights = description.sumOfWeights();
    int value = (int) Math.min(10/zoom+1, 255);
    for (int i = 0; i < steps; i++) {
      try {
        int randomInt = random.nextInt(sumOfWeights);
        Transform2D transform = description.getTransformWithWeight(randomInt);
        Vector2D tmp = transform.transform(currentPoint);
        currentPoint.setX0(tmp.getX0());
        currentPoint.setX1(tmp.getX1());

        canvas.setPixel(currentPoint, value);
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid description");
      }
    }
    notifyCanvasChanged();
  }
}
