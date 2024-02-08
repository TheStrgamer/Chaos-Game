package org.example.model;

/**
 * <h1>Vector2D</h1>
 * A class representing a 2D vector. It has methods for adding and subtracting vectors, as well as
 * multiplying the vector by a scalar.
 */
public class Vector2D {

  private double x0;
  private double x1;


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
   * Constructs a new Vector2D object with the given x0 and x1 values.
   *
   * @param x0 the x0 value
   * @param x1 the x1 value
   */
  public Vector2D(double x0, double x1) {
    this.x0 = x0;
    this.x1 = x1;
  }

  /**
   * Returns the x0 value of this vector.
   *
   * @return the x0 value of this vector
   */
  public double getX0() {
    return x0;
  }

  /**
   * Returns the x1 value of this vector.
   *
   * @return the x1 value of this vector
   */
  public double getX1() {
    return x1;
  }

  /**
   * adds the given vector to this vector.
   *
   * @param vector the vector to add
   * @throws IllegalArgumentException if the given vector is null
   */
  public void add(Vector2D vector) {
    verifyNotNull(vector);
    x0 += vector.getX0();
    x1 += vector.getX1();
  }

  /**
   * Subtracts the given vector from this vector.
   *
   * @param vector the vector to subtract
   * @throws IllegalArgumentException if the given vector is null
   */
  public void subtract(Vector2D vector) {
    verifyNotNull(vector);
    x0 -= vector.getX0();
    x1 -= vector.getX1();
  }

  /**
   * Multiplies this vector by the given scalar.
   *
   * @param scalar the scalar to multiply by
   */
  public void multiply(double scalar) {
    x0 *= scalar;
    x1 *= scalar;
  }
}
