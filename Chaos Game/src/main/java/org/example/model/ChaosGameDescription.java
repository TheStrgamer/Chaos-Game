package org.example.model;

import java.util.List;

/**
 * A class that represents a description of a chaos game.
 *
 * The description includes the minimum and maximum coordinates of the game,
 * as well as a list of transforms that are used to generate the game.
 */
public class ChaosGameDescription {
  private Vector2D minCoords;
  private Vector2D maxCoords;
  List<Transform2D> transforms;

  public ChaosGameDescription(Vector2D minCoords, Vector2D maxCoords,
                              List<Transform2D> transforms) {
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.transforms = transforms;
  }

  /**
   * Returns the minimum and maximum coordinates of the game.
   *
   * @return a pair of vectors, where the first vector is the minimum
   *         coordinates and the second vector is the maximum coordinates.
   */

  public Vector2D getMinCoords() {
    return minCoords;
  }

  public Vector2D getMaxCoords() {
    return maxCoords;
  }

  /**
   * Returns the list of transforms that are used to generate the game.
   *
   * @return a list of transforms.
   */
  public List<Transform2D> getTransforms() {
    return transforms;
  }
}
