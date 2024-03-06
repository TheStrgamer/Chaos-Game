package org.example.model;

/**
 * <h1>ChaosCanvas</h1>
 * A class representing a canvas for the chaos game. It has methods for setting and getting pixels
 * and for clearing the canvas.
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
   * Verifies that the given width and height are positive. Throws an IllegalArgumentException if the
   * given width or height is not positive.
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
   * Constructor for ChaosCanvas, creates an object with the given width, height, minimum coordinates and
   * maximum coordinates.
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
    this.width = width;
    this.height = height;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.canvas = new int[width][height];
    this.transformCoordsToIndices = new AffineTransform2D(
        new Matrix2x2(0, (height-1)/(minCoords.getX1()-maxCoords.getX1()),(width-1)/(maxCoords.getX0()-minCoords.getX0()),0),

        new Vector2D(((height - 1) * maxCoords.getX1()) / (maxCoords.getX1() - minCoords.getX1() )
        , ((width - 1) * minCoords.getX0()) / (minCoords.getX0()-maxCoords.getX0()))
    );
  }

  /**
   * Returns the pixel value at the given point.
   * @param point the point to get the pixel value at
   * @return the pixel value
   */
  public int getPixel(Vector2D point) {
    Vector2D indices = transformCoords(point);
    return canvas[(int) indices.getX0()][(int) indices.getX1()];
  }
  /**
   * Sets the pixel value at the given point to 1.
   * @param point the point to set the pixel value at
   */
  public void setPixel(Vector2D point) {
    Vector2D indices = transformCoords(point);
    canvas[(int) indices.getX0()][(int) indices.getX1()] = 1;
  }
  /**
   * Sets the pixel value at the given point to 0.
   * @param point the point to set the pixel value at
   */
  public void removePixel(Vector2D point) {
    Vector2D indices = transformCoords(point);
    canvas[(int) indices.getX0()][(int) indices.getX1()] = 0;
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
   * @param indices the indices to transform
   * @return the transformed coordinates
   */
  public Vector2D transformCoords(Vector2D indices) {
    return transformCoordsToIndices.transform(indices);
  }

}
