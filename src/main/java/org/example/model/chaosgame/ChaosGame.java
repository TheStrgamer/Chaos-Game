package org.example.model.chaosgame;

import java.util.Random;
import org.example.model.math.Vector2D;
import org.example.model.transform.Transform2D;

/**
 * <h2>ChaosGame.</h2>
 * <p>
 * A class that represents a chaos game. The game is generated based on a description and a canvas.
 * </p>
 * <p>
 * The chaos game extends the Game class, and is used to transform a given point based on randomly
 * selected transforms from a given description. The transformed point increases in value in the
 * canvas, and the canvas is updated after the given number of steps is run.
 * </p>
 *
 * @version 0.4.0
 * @since 0.2.0
 */
public class ChaosGame extends Game {

  private final Vector2D currentPoint;

  private final Random random;


  /**
   * Constructs a new ChaosGame object with the given description and creates a canvas based on the
   * values.
   *
   * @param description is the description to use.
   * @param width       is the width of the canvas.
   * @param height      is the height of the canvas.
   * @throws IllegalArgumentException if the given width or height is not positive, or if the given
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
   * Sets the description of this chaos game. The canvas is reset to a new canvas based on the
   * description. Notifies all observers that the description has changed.
   *
   * @param description is the description to use.
   * @throws IllegalArgumentException if the given description is null.
   */
  @Override
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
   * Changes the zoom of the canvas.
   *
   * @param multiplier the multiplier to change the zoom with.
   */
  public void changeZoom(double multiplier) {
    zoom += zoom * multiplier;
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
   * @throws IllegalArgumentException if the given number of steps is not positive.
   */
  @Override
  public void runSteps(int steps) {
    verifyStepsPositive(steps);
    int sumOfWeights = description.sumOfWeights();
    int value = (int) Math.min(10 / zoom + 1, 255);
    for (int i = 0; i < steps; i++) {
      try {
        int randomInt = random.nextInt(sumOfWeights);
        Transform2D transform = description.getTransformWithWeight(randomInt);
        Vector2D tmp = transform.transform(currentPoint);
        currentPoint.setX0(tmp.getX0());
        currentPoint.setX1(tmp.getX1());

        canvas.setPixelWithCoords(currentPoint, value);
      } catch (Exception e) {
        throw new IllegalArgumentException("Invalid description");
      }
    }
    notifyCanvasChanged();
  }
}
