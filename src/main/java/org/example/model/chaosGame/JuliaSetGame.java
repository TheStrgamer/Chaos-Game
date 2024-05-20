package org.example.model.chaosGame;

import java.util.Objects;
import org.example.model.math.Complex;
import org.example.model.math.Vector2D;
import org.example.model.transform.JuliaTransform;

/**
 * <h2>JuliaSetGame</h2>
 * <p>
 * The JulisSetGame class is used to generate a Julia set set based on a description and a canvas.
 * </p>
 * <p>
 * The Julia set game extends the Game class, and is used to calculate the value of each pixel in
 * the canvas based on the Julia set values
 * </p>
 * <p>
 * The run method is inspired by the pseudocode from <a href="https://en.wikipedia.org/wiki/Julia_set">...</a>
 * </p>
 *
 * @version 0.4.0
 * @since 0.4.0
 */
public class JuliaSetGame extends Game {

  private int maxIterations;
  private double escapeRadius;

  private double xOffset = 0;
  private double yOffset = 0;


  /**
   * Verifies that the given description is of type Julia. Throws an IllegalArgumentException if the
   * given description is not of type Julia. Multiple transforms are allowed, but only the first one
   * is used.
   *
   * @param description the description to verify
   * @throws IllegalArgumentException if the given description is not of type Julia
   */
  private void verifyDescriptionJulia(ChaosGameDescription description) {
    if (!Objects.equals(description.getTransformType(), "Julia")) {
      throw new IllegalArgumentException("Description must be of type Julia");
    }
  }

  /**
   * Verifies that the given value is positive. Throws an IllegalArgumentException if the given
   * value is not positive.
   *
   * @param value the value to verify
   * @throws IllegalArgumentException if the given value is not positive
   */
  private void verifyPositiveValue(double value, String name) {
    if (value <= 0) {
      throw new IllegalArgumentException(name + " must be positive");
    }
  }

  /**
   * Constructor for Julia set game, creates an object with the given description, width, height,
   * max iterations and escape radius.
   *
   * @param description   the description to use
   * @param width         the width to use
   * @param height        the height to use
   * @param maxIterations the maximum number of iterations
   * @param escapeRadius  the escape radius
   * @throws IllegalArgumentException if the given width or height is not positive, or if the given
   *                                  description is null
   */
  public JuliaSetGame(ChaosGameDescription description, int width, int height,
      int maxIterations, double escapeRadius) {
    verifyNotNullDescription(description);
    verifyDescriptionJulia(description);
    verifyValidCanvasSize(width, height);
    verifyPositiveValue(maxIterations, "Max iterations");
    verifyPositiveValue(escapeRadius, "Escape radius");

    this.canvasWidth = width;
    this.canvasHeight = height;
    setDescription(description);
    this.maxIterations = maxIterations;
    this.escapeRadius = escapeRadius;
  }

  /**
   * Sets the description of the Julia set.
   *
   * @param description the description to set
   */
  @Override
  public void setDescription(ChaosGameDescription description) {
    verifyNotNullDescription(description);
    verifyDescriptionJulia(description);
    this.description = description;
    this.canvas = new ChaosCanvas(canvasWidth, canvasHeight, description.getMinCoords(),
        description.getMaxCoords());
    zoom = 1.0;
    notifyDescriptionChanged();
  }

  /**
   * Runs the julia set for each pixel.
   *
   * @param maxIterations the maximum number of iterations to run per pixel
   */
  @Override
  public void runSteps(int maxIterations) {
    verifyPositiveValue(maxIterations, "Max iterations");
    this.maxIterations = maxIterations;
    JuliaTransform transform = (JuliaTransform) description.getTransforms().get(0);

    Complex c = transform.getPoint();
    double cx = c.getReal();
    double cy = c.getImaginary();

    for (int x0 = 0; x0 < canvasWidth; x0++) {
      for (int x1 = 0; x1 < canvasHeight; x1++) {
        calculatePoint(x0, x1, cx, cy);
      }
    }
    notifyCanvasChanged();

  }

  /**
   * Sets escape radius of the Julia set.
   *
   * @param escapeRadius the escape radius to set
   * @throws IllegalArgumentException if the given escape radius is not positive
   */
  public void setEscapeRadius(double escapeRadius) {
    verifyPositiveValue(escapeRadius, "Escape radius");
    this.escapeRadius = escapeRadius;
  }

  /**
   * Calculates the value of the pixel at the given point, and sets it on the canvas.
   *
   * @param x0 the x-coordinate of the point
   * @param x1 the y-coordinate of the point
   * @param cx the x-coordinate of the complex number
   * @param cy the y-coordinate of the complex number
   */
  public void calculatePoint(int x0, int x1, double cx, double cy) {
    double[] coords = scaleCoords(x0, x1);
    double zx = coords[0];
    double zy = coords[1];

    int iteration = 0;
    while (zx * zx + zy * zy < escapeRadius && iteration < this.maxIterations) {
      double tmp = zx * zx - zy * zy;
      zy = 2.0 * zx * zy + cy;
      zx = tmp + cx;
      iteration++;
    }

    Vector2D point = new Vector2D(x1, x0);
    canvas.putPixel(point, calculateValue(iteration, zx, zy));
  }

  /**
   * Updates the description of the Julia set.
   */
  public void updateDescription() {
    this.canvas = new ChaosCanvas(canvasWidth, canvasHeight, description.getMinCoords(),
        description.getMaxCoords());
    notifyDescriptionChanged();
    notifyCanvasChanged();
  }

  /**
   * Scales the coordinates of the given pixel.
   *
   * @param x0 the x-coordinate of the pixel
   * @param x1 the y-coordinate of the pixel
   * @return the scaled coordinates of the pixel
   */
  private double[] scaleCoords(double x0, double x1) {
    double[] coords = new double[2];
    coords[0] = xOffset + zoom * (x0 - (double) canvasWidth / 2) * 4.0 / canvasWidth;
    coords[1] = yOffset + zoom * (x1 - (double) canvasHeight / 2) * 4.0 / canvasWidth;
    return coords;
  }

  /**
   * Calculates the value of the pixel at the given iteration.
   *
   * @param iteration the iteration to calculate the value at
   * @param zx        the x-coordinate of the pixel
   * @param zy        the y-coordinate of the pixel
   * @return the value of the pixel at the given iteration
   */
  public int calculateValue(int iteration, double zx, double zy) {
    if (iteration == this.maxIterations) {
      return 0;
    } else {
      double abs_z = zx * zx + zy * zy;
      int value = (int) (iteration + 1 - Math.log(Math.log(abs_z)) / Math.log(2));
      double multiplier = Math.pow((double) value / this.maxIterations, 0.2);
      value = (int) (maxIterations - this.maxIterations * multiplier);
      int maxColorValue = 755;
      return (int) ((double) value / maxIterations * maxColorValue);
    }
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
    xOffset -= vector.getX0() * zoom / 10;
    yOffset += vector.getX1() * zoom / 10;
    notifyDescriptionChanged();
  }


  /**
   * Returns the zoom level of the Julia set. used for testing purposes
   *
   * @return the zoom level
   */
  public double getZoom() {
    return zoom;
  }

  /**
   * Returns the x offset of the Julia set. used for testing purposes
   *
   * @return the x offset
   */
  public double getxOffset() {
    return xOffset;
  }

  /**
   * Returns the y offset of the Julia set. used for testing purposes
   *
   * @return the y offset
   */
  public double getyOffset() {
    return yOffset;
  }
}
