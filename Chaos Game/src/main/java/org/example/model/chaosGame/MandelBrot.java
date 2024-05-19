package org.example.model.chaosGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.example.model.math.Complex;
import org.example.model.math.Vector2D;
import org.example.model.observer.ChaosGameObserver;
import org.example.model.transform.JuliaTransform;

public class MandelBrot  {


  private ChaosCanvas canvas;
  private ChaosGameDescription description;

  private final Vector2D currentPoint;

  private final Random random;

  private int canvasWidth;
  private int canvasHeight;

  double zoom = 1.0;
  private final int maxIterations;
  private final double escapeRadius;

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
   * Constructor for MandelBrot, creates an object with the given description, width, height, max
   * iterations and escape radius.
   *
   * @param description the description to use
   * @param width       the width to use
   * @param height      the height to use
   * @param maxIterations the maximum number of iterations
   * @param escapeRadius the escape radius
   * @throws IllegalArgumentException if the given width or height is not positive, or if the given
   *                                  description is null
   */
  public MandelBrot(ChaosGameDescription description, int width, int height,
      int maxIterations, double escapeRadius) {
    verifyNotNullDescription(description);
    verifyValidCanvasSize(width, height);

    this.canvasWidth = width;
    this.canvasHeight = height;
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
    setDescription(description);
    this.maxIterations = maxIterations;
    this.escapeRadius = escapeRadius;
  }
  public void setDescription(ChaosGameDescription description) {
    verifyNotNullDescription(description);
    currentPoint.setX0(0);
    currentPoint.setX1(0);
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

  public void runSteps(int steps) {
    JuliaTransform transform = (JuliaTransform) description.getTransforms().get(0);

    Complex c = transform.getPoint();
    double cx = c.getReal();
    double cy = c.getImaginary();

    for (int i = 0; i < steps; i++) {
      for (int x = 0; x < canvasWidth; x++) {
        for (int y = 0; y < canvasHeight; y++) {
          double zx = xOffset + zoom*(x - (double) canvasWidth / 2) * 4.0 / canvasWidth;
          double zy = yOffset + zoom*(y - (double) canvasHeight / 2) * 4.0 / canvasWidth;
          int iteration = 0;
          while (zx * zx + zy * zy < escapeRadius && iteration < maxIterations) {
            double xtemp = zx * zx - zy * zy;
            zy = 2.0 * zx * zy + cy;
            zx = xtemp+cx;
            iteration++;
          }

          Vector2D point = new Vector2D(y,x);
          if (iteration == maxIterations) {
            canvas.putPixel(point, 0);
          } else {
            double abs_z = zx * zx + zy * zy;
            int value = (int) (iteration + 1 - Math.log(Math.log(abs_z)) / Math.log(2));
            double multiplier = Math.pow((double) value /maxIterations, 0.2);

            canvas.putPixel(point, (int) (765-maxIterations*multiplier*5/2));
          }

        }
      }
    }
    notifyCanvasChanged();

  }
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
    xOffset -= vector.getX0()*zoom/10;
    yOffset += vector.getX1()*zoom/10;
    notifyDescriptionChanged();
  }

  public void setCanvasSize(int width, int height) {
    verifyValidCanvasSize(width, height);
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(),
        description.getMaxCoords());
    this.canvasWidth = width;
    this.canvasHeight = height;
    notifyCanvasChanged();
  }
}
