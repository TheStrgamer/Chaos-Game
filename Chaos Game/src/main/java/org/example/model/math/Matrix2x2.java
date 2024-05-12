package org.example.model.math;

/**
 * <h1>Matrix2x2</h1>
 * A class representing a 2x2 matrix. It has methods for multiplying the matrix with a vector.
 */
public class Matrix2x2 {

  private final double a00;
  private final double a01;
  private final double a10;
  private final double a11;

  /**
   * Checks if the given vector object is null and throws an IllegalArgumentException if it is.
   *
   * @param vector the double to check
   * @throws IllegalArgumentException if the given vector is null
   */
  private void verifyNotNull(Vector2D vector) {
    if (vector == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }
  }

  /**
   * Constructs a new Matrix2x2 object with the given values.
   *
   * @param a00 the a00 value
   * @param a01 the a01 value
   * @param a10 the a10 value
   * @param a11 the a11 value
   */

  public Matrix2x2(double a00, double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  /**
   * Multiplies this matrix with the given vector.
   *
   * @param vector the vector to multiply with
   * @return the resulting vector
   * @throws IllegalArgumentException if the given vector is null
   */
  public Vector2D multiply(Vector2D vector) {
    verifyNotNull(vector);
    double x0 = a00 * vector.getX0() + a01 * vector.getX1();
    double x1 = a10 * vector.getX0() + a11 * vector.getX1();

    return new Vector2D(x0, x1);
  }

  /**
   * Returns a string representation of this matrix.
   *
   * @return a string representation of this matrix
   */
  public String toString() {
    return a00 + ", " + a01 + ", " + a10 + ", " + a11;
  }
}
