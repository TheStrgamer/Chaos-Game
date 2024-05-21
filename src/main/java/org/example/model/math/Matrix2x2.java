package org.example.model.math;

/**
 * <h2>Matrix2x2</h2>
 * <p>
 * A class representing a 2x2 matrix. A matrix is a rectangular array of numbers arranged in rows
 * and columns.
 * </p>
 * <p>
 * The class provides methods for multiplying the matrix with a vector and checking if two matrices
 * are equal.
 * </p>
 * @version 0.4.0
 * @since 0.1.0
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
   * Verifies that the given matrix object is not null. Throws an IllegalArgumentException if the given
   */
  private void verifyNotNull(Matrix2x2 matrix) {
    if (matrix == null) {
      throw new IllegalArgumentException("Matrix cannot be null");
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
   * Deep copy constructor for the Matrix2x2 class. Used to create a new Matrix2x2 object with the
   * same values as the given Matrix2x2 object.
   *
   * @param matrix the Matrix2x2 object to copy
   * @throws IllegalArgumentException if the given matrix is null
   */
  public Matrix2x2(Matrix2x2 matrix) {
    verifyNotNull(matrix);
    double[] values = matrix.getValues();
    this.a00 = values[0];
    this.a01 = values[1];
    this.a10 = values[2];
    this.a11 = values[3];
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
   * Checks if the given matrix object has the same values as this matrix.
   *
   * @param matrix the matrix to compare
   * @return true if the given matrix has the same values as this matrix, false otherwise
   */
  public boolean equals(Matrix2x2 matrix) {
    if (matrix == null) {
      return false;
    }
    return this.a00 == matrix.a00 && this.a01 == matrix.a01 && this.a10 == matrix.a10
        && this.a11 == matrix.a11;
  }

  /**
   * Returns the values of this matrix as an array.
   *
   * @return the values of this matrix as an array
   */
  public double[] getValues() {
    return new double[]{a00, a01, a10, a11};
  }

  /**
   * Returns a string representation of this matrix.
   *
   * @return the string of this matrix
   */
  public String toString() {
    return a00 + ", " + a01 + ", " + a10 + ", " + a11;
  }
}
