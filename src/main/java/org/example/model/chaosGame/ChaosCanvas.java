package org.example.model.chaosGame;

import org.example.model.transform.AffineTransform2D;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;

/**
 * <h2>ChaosCanvas</h2>
 * <p>
 * A class representing a canvas for the chaos game. It has methods for setting and getting pixels
 * and for clearing the canvas.
 * </p>
 * <p>
 * The canvas has a tostring method that returns a ascii representation of the canvas, and a
 * getInfoString method that returns the details of the canvas.
 * </p>
 *
 * @version 0.4.0
 * @since 0.2.0
 */
public class ChaosCanvas {

  private final int[][] canvas;
  private final int width;
  private final int height;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;
  private final AffineTransform2D transformCoordsToIndices;


  /**
   * Verifies that the given vector is not null. Throws an IllegalArgumentException if the given
   * vector is null.
   *
   * @param vector the vector to verify
   * @throws IllegalArgumentException if the given vector is null
   */
  private void verifyNotNull(Vector2D vector) {
    if (vector == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }
  }

  /**
   * Verifies that the given point is within the given parameters. Throws an
   * IllegalArgumentException if the given point is not within the given parameters.
   *
   * @param point the point to verify
   * @throws IllegalArgumentException if the given point is not within the given parameters
   */

  private void verifyPointWithinParameters(Vector2D point) {
    if (point.getX0() < minCoords.getX0() || point.getX0() > maxCoords.getX0() ||
        point.getX1() < minCoords.getX1() || point.getX1() > maxCoords.getX1()) {
      throw new IllegalArgumentException(
          "Point " + point + " is not within the given parameters " + minCoords + "; " + maxCoords);
    }
  }

  /**
   * Returns true if the given point is within the given parameters, and false otherwise.
   *
   * @param point the point to check
   * @return true if the given point is within the given parameters, and false otherwise
   */
  private boolean pointWithinParameters(Vector2D point) {
    return !(point.getX0() < minCoords.getX0()) && !(point.getX0() > maxCoords.getX0()) &&
        !(point.getX1() < minCoords.getX1()) && !(point.getX1() > maxCoords.getX1());
  }

  /**
   * Verifies that the given width and height are positive. Throws an IllegalArgumentException if
   * the given width or height is not positive.
   *
   * @param width  the width to verify
   * @param height the height to verify
   * @throws IllegalArgumentException if the given width or height is not positive
   */
  private void verifyDimensions(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be positive");
    }
  }

  /**
   * Verifies that the given minimum and maximum coordinates are valid. Throws an
   * IllegalArgumentException if the given coordinates are not valid.
   *
   * @param minCoords the minimum coordinates to verify
   * @param maxCoords the maximum coordinates to verify
   * @throws IllegalArgumentException if the given minimum or maximum coordinates are equal, or if
   *                                  the minimum coordinates are greater than the maximum
   *                                  coordinates
   */
  private void minMaxCoordsValid(Vector2D minCoords, Vector2D maxCoords) {
    if (minCoords.getX0() >= maxCoords.getX0() || minCoords.getX1() >= maxCoords.getX1()) {
      throw new IllegalArgumentException(
          "Minimum coordinates must be less than maximum coordinates");
    }
  }

  /**
   * Returns whether the given point is outside the canvas.
   *
   * @param point the point to check
   * @return true if the point is outside the canvas, false otherwise
   */
  private boolean pointOutsideCanvas(Vector2D point) {
    return !(point.getX0() >= 0 && point.getX0() < height && point.getX1() >= 0
        && point.getX1() < width);
  }

  /**
   * Constructor for ChaosCanvas, creates an object with the given width, height, minimum
   * coordinates and maximum coordinates.
   *
   * @param width     the width to use
   * @param height    the height to use
   * @param minCoords the minimum coordinates to use
   * @param maxCoords the maximum coordinates to use
   * @throws IllegalArgumentException if the given width or height is not positive, or if the given
   *                                  minimum or maximum coordinates are null
   */
  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
    verifyDimensions(width, height);
    verifyNotNull(minCoords);
    verifyNotNull(maxCoords);
    minMaxCoordsValid(minCoords, maxCoords);
    this.width = width;
    this.height = height;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.canvas = new int[width][height];
    this.transformCoordsToIndices = new AffineTransform2D(
        new Matrix2x2(0, (height - 1) / (minCoords.getX1() - maxCoords.getX1()),
            (width - 1) / (maxCoords.getX0() - minCoords.getX0()), 0),

        new Vector2D(((height - 1) * maxCoords.getX1()) / (maxCoords.getX1() - minCoords.getX1())
            , ((width - 1) * minCoords.getX0()) / (minCoords.getX0() - maxCoords.getX0()))
    );
  }

  /**
   * Deep copy constructor for ChaosCanvas class. Used to create a new ChaosCanvas with the same
   * values as the given canvas.
   *
   * @param canvas the ChaosCanvas object to copy
   */
  public ChaosCanvas(ChaosCanvas canvas) {
    this.width = canvas.getWidth();
    this.height = canvas.getHeight();
    this.minCoords = new Vector2D(canvas.minCoords);
    this.maxCoords = new Vector2D(canvas.maxCoords);
    this.canvas = canvas.getCanvasArray();
    this.transformCoordsToIndices = new AffineTransform2D(
        new Matrix2x2(0, (height - 1) / (minCoords.getX1() - maxCoords.getX1()),
            (width - 1) / (maxCoords.getX0() - minCoords.getX0()), 0),

        new Vector2D(((height - 1) * maxCoords.getX1()) / (maxCoords.getX1() - minCoords.getX1())
            , ((width - 1) * minCoords.getX0()) / (minCoords.getX0() - maxCoords.getX0()))
    );
  }

  /**
   * Returns the pixel value at the given point.
   *
   * @param point the point to get the pixel value at
   * @return the pixel value
   */
  public int getPixel(Vector2D point) {
    verifyNotNull(point);
    verifyPointWithinParameters(point);
    Vector2D indices = transformCoords(point);
    return canvas[(int) indices.getX1()][(int) indices.getX0()];
  }

  /**
   * Returns the pixel value at the given point. Used to create image from canvas
   *
   * @param pixel the point to get the pixel value at
   * @return the pixel value
   */
  public int getPixelFromCanvas(Vector2D pixel) {
    verifyNotNull(pixel);
    return canvas[(int) pixel.getX0()][(int) pixel.getX1()];
  }

  /**
   * Sets the pixel value at the given point to 255. Does nothing if the point is not within the
   * parameters.
   *
   * @param point the point to set the pixel value at
   */
  public void setPixel(Vector2D point) {
    verifyNotNull(point);
    if (!pointWithinParameters(point)) {
      return;
    }
    Vector2D indices = transformCoords(point);
    canvas[(int) indices.getX1()][(int) indices.getX0()] = 255;
  }

  /**
   * Adds the given value to the current pixel value at the given point. Does nothing if the point
   * is not within the parameters.
   *
   * @param point the point to set the pixel value at
   * @param value the value to add
   */
  public void setPixel(Vector2D point, int value) {
    verifyNotNull(point);
    if (!pointWithinParameters(point)) {
      return;
    }
    Vector2D indices = transformCoords(point);

    int newValue = getPixel(point) + value;
    newValue = Math.min(newValue, 755);

    canvas[(int) indices.getX1()][(int) indices.getX0()] = newValue;

  }

  /**
   * Puts the given value to the current pixel value at the given point in the canvas, does not
   * transform the point.
   *
   * @param point the point to set the pixel value at
   * @param value the value to put
   */
  public void putPixel(Vector2D point, int value) {
    verifyNotNull(point);
    if (pointOutsideCanvas(point)) {
      return;
    }
    canvas[(int) point.getX1()][(int) point.getX0()] = value;

  }


  /**
   * Sets the pixel value at the given point to 0.
   *
   * @param point the point to set the pixel value at
   */
  public void removePixel(Vector2D point) {
    verifyNotNull(point);
    verifyPointWithinParameters(point);
    Vector2D indices = transformCoords(point);
    canvas[(int) indices.getX1()][(int) indices.getX0()] = 0;
  }

  /**
   * Returns a deep copy of the canvas as a 2D array of integers.
   *
   * @return the canvas as a 2D array of integers
   */
  public int[][] getCanvasArray() {
    return canvas.clone();
  }

  /**
   * Clears the canvas by setting all pixels to 0.
   */
  public void clear() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        canvas[i][j] = 0;
      }
    }
  }

  /**
   * transforms the given indices to coordinates. Main goal is testing.
   *
   * @param coord the indices to transform
   * @return the transformed coordinates
   */
  public Vector2D transformCoords(Vector2D coord) {
    verifyNotNull(coord);
    verifyPointWithinParameters(coord);
    return transformCoordsToIndices.transform(coord);
  }

  /**
   * Returns the width of the canvas.
   *
   * @return the width of the canvas
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the canvas.
   *
   * @return the height of the canvas
   */
  public int getHeight() {
    return height;
  }

  /**
   * Returns a string with the details of the canvas. These include dimensions and coordinates.
   *
   * @return the details of the canvas
   */
  public String getInfoString() {
    return "ChaosCanvas{" +
        "width=" + width +
        ", height=" + height +
        ", minCoords=" + minCoords +
        ", maxCoords=" + maxCoords +
        '}';
  }

  /**
   * Returns a string representation of the canvas.
   *
   * @return a string representation of the canvas
   */
  public String toString() {
    StringBuilder canvasString = new StringBuilder();
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (canvas[j][i] == 0) {
          canvasString.append(" ");
        } else {
          canvasString.append("X");
        }
      }
      canvasString.append("\n");
    }
    return canvasString.toString();
  }


}
