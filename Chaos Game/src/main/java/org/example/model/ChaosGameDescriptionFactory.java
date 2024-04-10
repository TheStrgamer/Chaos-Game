package org.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>ChaosGameDescriptionFactory</h1>
 * A factory class that creates ChaosGameDescription objects based on the given type.
 */
public class ChaosGameDescriptionFactory {


  /**
   * Creates a ChaosGameDescription object based on the given type.
   *
   * @param type is the type of description to create.
   * @return the ChaosGameDescription object.
   */
  public ChaosGameDescription createDescription(String type) {
    if (type.equals("Sierpinski")) {
      return createSierpinskiDescription();
    } else if (type.equals("Barnsley")) {
      return createBarnsleyDescription();
    } else if (type.equals("Julia")) {
      return createJuliaDescription();
    } else {
      throw new IllegalArgumentException("Invalid description type");
    }
  }

  /**
   * Creates a ChaosGameDescription object with the sierpinski values.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createSierpinskiDescription() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector1 = new Vector2D(0, 0);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector2 = new Vector2D(0.5, 0);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    Matrix2x2 matrix3 = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector3 = new Vector2D(0.25, 0.5);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);

    transforms.add(transform1);
    transforms.add(transform2);
    transforms.add(transform3);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Creates a ChaosGameDescription object with the barnsley values.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createBarnsleyDescription() {
    Vector2D minCoords = new Vector2D(-2.65, 0);
    Vector2D maxCoords = new Vector2D(2.65, 10);
    List<Transform2D> transforms = new ArrayList<>();

    Matrix2x2 matrix1 = new Matrix2x2(0, 0, 0, 0.16);
    Vector2D vector1 = new Vector2D(0, 0);
    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);

    Matrix2x2 matrix2 = new Matrix2x2(0.85, 0.04, -0.04, 0.85);
    Vector2D vector2 = new Vector2D(0, 1.6);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);

    Matrix2x2 matrix3 = new Matrix2x2(0.2, -0.26, 0.23, 0.22);
    Vector2D vector3 = new Vector2D(0, 1.6);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);

    Matrix2x2 matrix4 = new Matrix2x2(-0.15, 0.28, 0.26, 0.24);
    Vector2D vector4 = new Vector2D(0, 0.44);
    AffineTransform2D transform4 = new AffineTransform2D(matrix4, vector4);

    transforms.add(transform1);
    transforms.add(transform2);
    transforms.add(transform3);
    transforms.add(transform4);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Creates a ChaosGameDescription object with the julia values.
   *
   * @return the ChaosGameDescription object.
   */
  private ChaosGameDescription createJuliaDescription() {
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    List<Transform2D> transforms = new ArrayList<>();

    Complex complex1 = new Complex(-0.74543, 0.11301);
    JuliaTransform transform1 = new JuliaTransform(complex1, 1);
    JuliaTransform transform2 = new JuliaTransform(complex1, -1);

    transforms.add(transform1);
    transforms.add(transform2);
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

}
