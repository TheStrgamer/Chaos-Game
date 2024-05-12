package org.example.model;

/**
 * <h1>JuliaTransform</h1>
 * A class representing a Julia transformation. The goal of this class is to transform a given
 * complex number using the Julia transformation.
 * <p>
 * The class implements the Transform2D interface
 * </p>
 */
public class JuliaTransform implements Transform2D {

  private final Complex point;
  private final int sign;

  /**
   * Verifies that the given vector is of the Complex class, and not null. Throws an
   * IllegalArgumentException if the given vector is not of the Complex class or is null.
   *
   * @param vector the vector to verify
   * @throws IllegalArgumentException if the given vector is not of the Complex class or is null
   */
  private void verifyValidComplex(Vector2D vector) throws IllegalArgumentException {
    if (!(vector instanceof Complex)) {
      throw new IllegalArgumentException("Input must be of the Complex class");
    }
  }

  /**
   * Verifies that the given sign is either 1 or -1. Throws an IllegalArgumentException if the given
   * sign is not 1 or -1.
   *
   * @param sign the sign to verify
   * @throws IllegalArgumentException if the given sign is not 1 or -1
   */
  private void verifySign(int sign) throws IllegalArgumentException {
    if (sign != 1 && sign != -1) {
      throw new IllegalArgumentException("Sign must be 1 or -1");
    }
  }

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
   * Constructs a new JuliaTransform object with the given point and sign.
   *
   * @param point the point to use
   * @param sign  the sign to use
   * @throws IllegalArgumentException if the given point is not of the Complex class or is null, or
   *                                  if
   */
  public JuliaTransform(Complex point, int sign) throws IllegalArgumentException {
    verifyValidComplex(point);
    verifySign(sign);
    this.point = point;
    this.sign = sign;
  }


  /**
   * Transforms the given vector using the Julia transformation.
   *
   * @param vector the vector to transform
   * @return the transformed vector
   * @throws IllegalArgumentException if the given vector is not of the Complex class or is null
   */
  @Override
  public Vector2D transform(Vector2D vector) throws IllegalArgumentException {
    verifyNotNull(vector);
    Complex result = new Complex(vector.getX0(), vector.getX1());
    result = result.subtract(point);
    result = result.sqrt();
    result = result.multiply(sign);

    return result;


  }

  /**
   * Checks if the given Julia transformation object has the same values as this Julia
   * transformation.
   *
   * @param transform the Julia transformation to compare
   * @return true if the given Julia transformation has the same values as this Julia transformation,
   * false otherwise
   */
  @Override
  public boolean equals(Transform2D transform) {
    if (transform == null || !(transform instanceof JuliaTransform)) {
      return false;
    }
    JuliaTransform juliaTransform = (JuliaTransform) transform;
    return point.equals(juliaTransform.point) && sign == juliaTransform.sign;
  }

  /**
   * Returns a string representation of the Julia transformation with the sign.
   *
   * @return a string representation of the Julia transformation
   */
  public String toStringWithSign() {
    return point.toString() + ", " + sign;
  }

  /**
   * Returns a string representation of the Julia transformation without the sign.
   * Used when writing to file.
   *
   * @return a string representation of the Julia transformation without the sign
   */
  public String toString() {
    return point.toString();
  }


}
