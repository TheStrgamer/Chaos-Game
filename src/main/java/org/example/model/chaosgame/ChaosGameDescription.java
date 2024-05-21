package org.example.model.chaosgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.example.model.math.Vector2D;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.example.model.transform.Transform2D;

/**
 * <h2>ChaosGameDescription.</h2>
 * <p>
 * A class that represents a description of a chaos game. The description includes the minimum and
 * maximum coordinates of the game, as well as a list of transforms that are used to generate the
 * game.
 * </p>
 *
 * @version 1.0.0
 * @since 0.2.0
 */
public class ChaosGameDescription {

  private final Vector2D minCoords;
  private final Vector2D maxCoords;
  List<Transform2D> transforms;
  List<Integer> weights;
  int weightSum;


  /**
   * Verifies that the given Vector2D object is not null.
   *
   * @param vector is the vector to use.
   * @throws IllegalArgumentException if the given vector is null.
   */
  private void verifyNotNullVector(Vector2D vector) {
    if (vector == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }
  }

  /**
   * Verifies that the given index is within the bounds of the given size.
   *
   * @param index is the index to verify.
   * @param size  is the size to verify.
   * @throws IllegalArgumentException if the given index is out of bounds.
   */
  private void verifyWithinBounds(int index, int size) {
    if (index < 0 || index >= size) {
      throw new IllegalArgumentException("Index out of bounds");
    }
  }

  /**
   * Verifies that the given transform2D is not null.
   *
   * @param transform is the transform to use.
   * @throws IllegalArgumentException if the given vector is null.
   */
  private void verifyNotNullTransform(Transform2D transform) {
    if (transform == null) {
      throw new IllegalArgumentException("Objects in Transform2D list cannot be null");
    }
  }

  /**
   * Verifies that the given description is not null.
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
   * Verifies that the given list of transforms is not null, not empty and does not contain any null
   * objects.
   *
   * @param listTransforms a list of transforms2D objects.
   * @throws IllegalArgumentException if the list is null, empty of contains null objects.
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
   * Verifies that the given list of integers is not null, not empty and does not contain any null
   * objects.
   *
   * @param weights a list of transforms2D objects.
   * @throws IllegalArgumentException if the list is null, empty of contains null objects.
   */
  private void verifyWeightsNotNullAndNotEmpty(List<Integer> weights) {
    if (weights == null) {
      throw new IllegalArgumentException("List cannot be null");
    }
    if (weights.isEmpty()) {
      throw new IllegalArgumentException("List cannot be empty");
    }
    for (Integer weight : weights) {
      if (weight == null) {
        throw new IllegalArgumentException("Objects in Transform2D list cannot be null");
      }
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
   * Constructs a new ChaosGameDescription object with the given minimum and maximum coordinates, as
   * well as a list of transforms.
   *
   * @param minCoords  is the minimum coordinates to use.
   * @param maxCoords  is the maximum coordinates to use.
   * @param transforms is the list of transforms to use.
   * @throws IllegalArgumentException if the given minimum or maximum coordinates are null, if the
   *                                  given list of transforms is null, empty or contains null
   *                                  objects
   */
  public ChaosGameDescription(Vector2D minCoords, Vector2D maxCoords,
      List<Transform2D> transforms) {
    verifyNotNullVector(minCoords);
    verifyNotNullVector(maxCoords);
    minMaxCoordsValid(minCoords, maxCoords);
    verifyListNotNullAndNotEmpty(transforms);
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.transforms = transforms;
    this.weights = new ArrayList<>(Collections.nCopies(transforms.size(), 1));
    calculateWeightSum();

  }

  /**
   * Deep copy constructor for the ChaosGameDescription class. Used to create a new
   * ChaosGameDescription with the same values as the given description
   *
   * @param description the ChaosGameDescription object to copy
   * @throws IllegalArgumentException if the given description is null
   */
  public ChaosGameDescription(ChaosGameDescription description) {
    verifyNotNullDescription(description);
    this.minCoords = new Vector2D(description.minCoords);
    this.maxCoords = new Vector2D(description.maxCoords);
    this.transforms = description.getTransforms();
    this.weights = description.getWeights();
    calculateWeightSum();
  }

  /**
   * Constructs a new ChaosGameDescription object with the given minimum and maximum coordinates, as
   * well as a list of transforms, and a list of weights.
   *
   * @param minCoords  is the minimum coordinates to use.
   * @param maxCoords  is the maximum coordinates to use.
   * @param transforms is the list of transforms to use.
   * @param weights    is the list of weights to use.
   * @throws IllegalArgumentException if the given minimum or maximum coordinates are null, if the
   *                                  given list of transforms is null, empty or contains null
   *                                  objects
   */
  public ChaosGameDescription(Vector2D minCoords, Vector2D maxCoords,
      List<Transform2D> transforms, List<Integer> weights) {
    verifyNotNullVector(minCoords);
    verifyNotNullVector(maxCoords);
    minMaxCoordsValid(minCoords, maxCoords);
    verifyListNotNullAndNotEmpty(transforms);
    verifyWeightsNotNullAndNotEmpty(weights);
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.transforms = transforms;
    this.weights = weights;
    calculateWeightSum();

  }

  /**
   * Returns the minimum and maximum coordinates of the game.
   *
   * @return a pair of vectors, where the first vector is the minimum coordinates and the second
   *        vector is the maximum coordinates.
   */

  public Vector2D getMinCoords() {
    return new Vector2D(minCoords);
  }

  /**
   * Returns the maximum coordinates of the game.
   *
   * @return the maximum coordinates.
   */
  public Vector2D getMaxCoords() {
    return new Vector2D(maxCoords);
  }

  /**
   * Returns the list of transforms that are used to generate the game.
   *
   * @return a list of transforms.
   */
  public List<Transform2D> getTransforms() {
    return transforms.stream().map(Transform2D::deepCopy).collect(Collectors.toList());
  }

  /**
   * Returns a string representation of the type of transform used in the description.
   *
   * @return the type of transform.
   */
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
   * Returns a list of string representations of the transforms.
   *
   * @return a list of the transforms.
   */
  public List<String> getTransformsAsStringList() {
    List<String> transformsAsString = new ArrayList<>();

    if (getTransformType().equals("Julia")) {
      for (int i = 0; i < transforms.size(); i += 2) {
        transformsAsString.add(
            transforms.get(i).toString() + ", " + weights.get(i) + ", " + weights.get(i + 1));
      }
    } else {
      for (Transform2D transform : transforms) {
        transformsAsString.add(
            transform.toString() + ", " + weights.get(transforms.indexOf(transform)));
      }
    }
    return transformsAsString;
  }

  /**
   * Checks if the given ChaosGameDescription object has the same values as this
   * ChaosGameDescription.
   *
   * @param description the ChaosGameDescription to compare
   * @return true if the given ChaosGameDescription has the same values as this
   *        ChaosGameDescription, false otherwise
   */
  public boolean equals(ChaosGameDescription description) {
    if (description == null) {
      return false;
    }
    if (!minCoords.equals(description.minCoords) || !maxCoords.equals(description.maxCoords)) {
      return false;
    }
    if (transforms.size() != description.transforms.size()) {
      return false;
    }
    return IntStream.range(0, transforms.size())
        .allMatch(i -> transforms.get(i).equals(description.transforms.get(i)));
  }

  /**
   * Returns the weight of the transform at the given index. Throws an IllegalArgumentException if
   * the index is out of bounds.
   *
   * @param index the index of the transform.
   * @return the weight of the transform.
   * @throws IllegalArgumentException if the index is out of bounds.
   */
  public int getWeight(int index) {
    verifyWithinBounds(index, weights.size());
    return weights.get(index);
  }

  /**
   * Sets the weight of the transform at the given index. Throws an IllegalArgumentException if the
   * index is out of bounds.
   *
   * @param index  the index of the transform.
   * @param weight the weight of the transform.
   * @throws IllegalArgumentException if the index is out of bounds.
   */
  public void setWeight(int index, int weight) {
    verifyWithinBounds(index, weights.size());
    weights.set(index, weight);
    calculateWeightSum();
  }

  /**
   * Sets the weights of the transforms, replacing the current list of weights. Throws an
   * IllegalArgumentException if the list of weights is null, empty or contains null objects.
   *
   * @param weights the list of weights.
   * @throws IllegalArgumentException if the list of weights is null, empty or contains null
   *                                  objects.
   */
  public void setWeights(List<Integer> weights) {
    verifyWeightsNotNullAndNotEmpty(weights);
    this.weights = weights;
    calculateWeightSum();
  }

  /**
   * Returns the list of weights of the transforms.
   *
   * @return the list of weights of the transforms.
   */
  public List<Integer> getWeights() {
    return new ArrayList<>(this.weights);
  }

  /**
   * Returns the sum of the weights of all the transforms.
   *
   * @return the sum of the weights of all the transforms.
   */
  public int sumOfWeights() {
    return weightSum;
  }

  /**
   * Recalculates the sum of the weights of all the transforms.
   */
  private void calculateWeightSum() {
    weightSum = weights.stream().mapToInt(Integer::intValue).sum();
  }

  /**
   * Returns the sum of the weights of all the transforms below the given index.
   *
   * @param index the index of the transform.
   * @return the sum of the weights of all the transforms below the given index.
   * @throws IllegalArgumentException if the index is out of bounds.
   */
  public int sumOfWeightsBelowIndex(int index) {
    verifyWithinBounds(index, weights.size() + 1);
    int sum = 0;
    for (int i = 0; i < index; i++) {
      sum += getWeight(i);
    }
    return sum;
  }

  /**
   * Returns the transform with the given index, taking into account the weight of the transform.
   *
   * @param index the weight of the transform.
   * @return the transform with the given weight.
   * @throws IllegalArgumentException if the index is out of bounds.
   */
  public Transform2D getTransformWithWeight(int index) {
    verifyWithinBounds(index, weightSum);
    for (int i = 0; i < transforms.size(); i++) {
      if (index < sumOfWeightsBelowIndex(i + 1)) {
        return transforms.get(i);
      }
    }
    return null;
  }

  /**
   * Returns a string representation of the ChaosGameDescription object.
   *
   * @return a string representation of the ChaosGameDescription object.
   */
  public String toString() {
    return String.format("%s    #Transform type\n", getTransformType())
        + String.format("%s    #minimum coordinates\n", minCoords)
        + String.format("%s    #maximum coordinates\n", maxCoords)
        + transforms.stream()
        .map(transform -> transform.toString() + "    #transform\n")
        .collect(Collectors.joining());
  }

  /**
   * Changes the zoom of the description by multiplying the minimum and maximum coordinates by the
   * given multiplier.
   *
   * @param multiplier the multiplier to use.
   */
  public void changeZoom(double multiplier) {
    double xdiff = maxCoords.getX0() - minCoords.getX0();
    double ydiff = maxCoords.getX1() - minCoords.getX1();
    Vector2D newDiff = new Vector2D(xdiff * multiplier, ydiff * multiplier);

    minCoords.setX0(minCoords.getX0() - newDiff.getX0() / 2);
    minCoords.setX1(minCoords.getX1() - newDiff.getX1() / 2);
    maxCoords.setX0(maxCoords.getX0() + newDiff.getX0() / 2);
    maxCoords.setX1(maxCoords.getX1() + newDiff.getX1() / 2);

  }

  /**
   * Moves the description based on the given vector in percentage of the min and max coordinates.
   *
   * @param vector the vector to move the description by.
   */
  public void moveCanvas(Vector2D vector) {
    double xdiff = maxCoords.getX0() - minCoords.getX0();
    double ydiff = maxCoords.getX1() - minCoords.getX1();
    Vector2D newDiff = new Vector2D(xdiff * vector.getX0() / 100, ydiff * vector.getX1() / 100);

    minCoords.setX0(minCoords.getX0() - newDiff.getX0());
    minCoords.setX1(minCoords.getX1() - newDiff.getX1());
    maxCoords.setX0(maxCoords.getX0() - newDiff.getX0());
    maxCoords.setX1(maxCoords.getX1() - newDiff.getX1());
    roundCoords();
  }

  /**
   * Rounds the coordinates to 4 decimals.
   */
  private void roundCoords() {
    int amountOfDecimals = 5;
    int multiplier = (int) Math.pow(10, amountOfDecimals);
    minCoords.setX0((double) Math.round(minCoords.getX0() * multiplier) / multiplier);
    minCoords.setX1((double) Math.round(minCoords.getX1() * multiplier) / multiplier);
    maxCoords.setX0((double) Math.round(maxCoords.getX0() * multiplier) / multiplier);
    maxCoords.setX1((double) Math.round(maxCoords.getX1() * multiplier) / multiplier);
  }
}
