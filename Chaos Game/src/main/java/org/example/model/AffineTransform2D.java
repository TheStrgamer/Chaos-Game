package org.example.model;

/**
 * <h1>AffineTransform2D</h1>
 * A class representing an affine transformation in 2D space. It has a method for transforming a
 * vector using the affine transformation.
 */
public class AffineTransform2D implements Transform2D {

  private final Matrix2x2 matrix;
  private final Vector2D vector;


  /**
   * Verifies that the given vector is not null. Throws an IllegalArgumentException if the given
   * vector is null.
   *
   * @param vector the vector to verify
   * @throws IllegalArgumentException if the given vector is null
   */
  private void verifyVectorNotNull(Vector2D vector) {
    if (vector == null) {
      throw new IllegalArgumentException("Vector cannot be null");
    }
  }

  /**
   * Verifies that the given matrix is not null. Throws an IllegalArgumentException if the given
   * matrix is null.
   *
   * @param matrix the matrix to verify
   * @throws IllegalArgumentException if the given matrix is null
   */
  private void verifyMatrixNotNull(Matrix2x2 matrix) {
    if (matrix == null) {
      throw new IllegalArgumentException("Matrix cannot be null");
    }
  }

  /**
   * Constructs a new AffineTransform2D object with the given matrix and vector.
   *
   * @param matrix the matrix to use
   * @param vector the vector to use
   * @throws IllegalArgumentException if the given matrix or vector is null
   */
  public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
    verifyMatrixNotNull(matrix);
    verifyVectorNotNull(vector);
    this.matrix = matrix;
    this.vector = vector;
  }

  /**
   * Transforms the given vector using the affine transformation.
   *
   * @param vector the vector to transform
   * @return the transformed vector
   * @throws IllegalArgumentException if the given vector is null
   */
  @Override
  public Vector2D transform(Vector2D vector) {
    verifyVectorNotNull(vector);
    return matrix.multiply(vector).add(this.vector);
  }

  /**
   * Checks if the given AffineTransform2D object has the same values as this AffineTransform2D
   * @param transform the object to compare
   * @return true if the given AffineTransform2D object has the same values as this AffineTransform2D
   */
  @Override
  public boolean equals(Transform2D transform) {
    if (this == transform) {
      return true;
    }
    if (!(transform instanceof AffineTransform2D affineTransform)) {
      return false;
    }
    return matrix.equals(affineTransform.matrix) && vector.equals(affineTransform.vector);
  }

  /**
   * Returns a string representation of this AffineTransform2D object.
   * @return a string representation of this AffineTransform2D object
   */

  public String toString() {return matrix.toString() + ", " + vector;}


}
