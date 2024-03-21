package org.example.model;

import java.util.List;

/**
 * A class that represents a description of a chaos game.
 * The description includes the minimum and maximum coordinates of the game,
 * as well as a list of transforms that are used to generate the game.
 */
public class ChaosGameDescription {
  private Vector2D minCoords;
  private Vector2D maxCoords;
  List<Transform2D> transforms;

  /**
   * Verifies that the given Vector2D object is not null.
   * @param vector  is the vector to use.
   *
   * @throws IllegalArgumentException if the given vector is null.
   */
  private void verifyNotNullVector(Vector2D vector) {
    if (vector == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }
  }

  /**
   * Verifies that the given transform2D is not null.
   * @param transform  is the transform to use.
   *
   * @throws IllegalArgumentException if the given vector is null.
   */
  private void verifyNotNullTransform(Transform2D transform) {
    if (transform == null) {
      throw new IllegalArgumentException("Objects in Transform2D list cannot be null");
    }
  }

  /**
   * Verifies that the given list of transforms is not null, not empty and does not
   * contain any null objects.
   * @param listTransforms a list of transforms2D objects.
   *
   * @throws IllegalArgumentException if the list is null, empty of contains null
   *                                  objects.
   */
  private void verifyListNotNullAndNotEmpty(List<Transform2D> listTransforms) {
    if (listTransforms == null) {
      throw new IllegalArgumentException("List cannot be null");
    }
    if (listTransforms.isEmpty()) {
      throw new IllegalArgumentException("List cannot be empty");
    }
    for (Transform2D listTransform : listTransforms) {
      verifyNotNullTransform(listTransform);
    }
  }

  /**
   * Constructs a new ChaosGameDescription object with the given minimum and maximum coordinates,
   * as well as a list of transforms.
   *
   * @param minCoords  is the minimum coordinates to use.
   * @param maxCoords  is the maximum coordinates to use.
   * @param transforms is the list of transforms to use.
   *
   * @throws IllegalArgumentException if the given minimum or maximum coordinates are null,
   *                                  if the given list of transforms is null, empty or contains
   *                                  null objects
   */
  public ChaosGameDescription(Vector2D minCoords, Vector2D maxCoords,
                              List<Transform2D> transforms) {
    verifyNotNullVector(minCoords);
    verifyNotNullVector(maxCoords);
    verifyListNotNullAndNotEmpty(transforms);
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

  /**
   * Returns the maximum coordinates of the game.
   *
   * @return the maximum coordinates.
   */
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

  public String getTransformType() {
    Transform2D transform = transforms.get(0);
    if (transform instanceof AffineTransform2D) {
      return "Affine";
    } else if (transform instanceof JuliaTransform) {
      return "Julia";
    } else {
      throw new IllegalArgumentException("Invalid transform type");
    }
  }

  /**
   * Returns a string representation of the ChaosGameDescription object.
   *
   * @return a string representation of the ChaosGameDescription object.
   */
  public String toString() {
    StringBuilder result = new StringBuilder(getTransformType() + "\n");
    result.append(minCoords).append("\n");
    result.append(maxCoords).append("\n");
    for (Transform2D transform : transforms) {
      result.append(transform).append("\n");
    }
    return result.toString();
  }
}
