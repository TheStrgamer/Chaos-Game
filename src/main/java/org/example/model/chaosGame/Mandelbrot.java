package org.example.model.chaosGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.example.model.math.Complex;
import org.example.model.math.Vector2D;
import org.example.model.observer.ChaosGameObserver;
import org.example.model.transform.JuliaTransform;

/**
 * <h1>Mandelbrot</h1>
 * The Mandelbrot class is used to generate a Mandelbrot set based on a description and a canvas.
 */
public class Mandelbrot {

  private ChaosCanvas canvas;
  private ChaosGameDescription description;

  private int canvasWidth;
  private int canvasHeight;

  double zoom = 1.0;
  private int maxIterations;
  private double escapeRadius;

  private double xOffset = 0;
  private double yOffset = 0;
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
   * Verifies that the given value is positive. Throws an IllegalArgumentException if the given value
   * is not positive.
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
   * Constructor for Mandelbrot, creates an object with the given description, width, height, max
   * iterations and escape radius.
   *
   * @param description   the description to use
   * @param width         the width to use
   * @param height        the height to use
   * @param maxIterations the maximum number of iterations
   * @param escapeRadius  the escape radius
   * @throws IllegalArgumentException if the given width or height is not positive, or if the given
   *                                  description is null
   */
  public Mandelbrot(ChaosGameDescription description, int width, int height,
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
   * Sets the description of the Mandelbrot.
   *
   * @param description the description to set
   */
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
   * Runs the steps of the Mandelbrot.
   *
   * @param maxIterations the maximum number of iterations to run
   */
  public void runSteps(int maxIterations, double escapeRadius) {
    verifyPositiveValue(maxIterations, "Max iterations");
    verifyPositiveValue(escapeRadius, "Escape radius");
    this.maxIterations = maxIterations;
    this.escapeRadius = escapeRadius;
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
   * Updates the description of the Mandelbrot.
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
   * Sets the size of the canvas.
   *
   * @param width  the width of the canvas
   * @param height the height of the canvas
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
   * Returns the description used by the mandelbrot.
   * used for testing purposes
   *
   * @return the description
   */
  public ChaosGameDescription getDescription() {
    return description;
  }

  /**
   * Returns the canvas used by the mandelbrot.
   * used for testing purposes
   *
   * @return the canvas
   */
  public ChaosCanvas getCanvas() {
    return canvas;
  }

  /**
   * Returns the zoom level of the mandelbrot.
   * used for testing purposes
   *
   * @return the zoom level
   */
  public double getZoom() {
    return zoom;
  }

  /**
   * Returns the x offset of the mandelbrot.
   * used for testing purposes
   *
   * @return the x offset
   */
  public double getxOffset() {
    return xOffset;
  }

  /**
   * Returns the y offset of the mandelbrot.
   * used for testing purposes
   *
   * @return the y offset
   */
  public double getyOffset() {
    return yOffset;
  }
}
