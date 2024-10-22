package org.example.model.math;

/**
 * <h2>Vector2D.</h2>
 * <p>
 * A class representing a 2D vector. It has methods for adding and subtracting vectors, as well as
 * multiplying the vector by a scalar.
 * </p>
 * <p>
 * A 2d vector is represented by two values, which in this class are called x0 and x1.
 * </p>
 *
 * @version 1.0.0
 * @since 0.1.0
 */
public class Vector2D {

  private double x0;
  private double x1;


  /**
   * Checks if the given vector object is null and throws an IllegalArgumentException if it is.
   *
   * @param vector the double to check
   * @param name   the name to use in the exception message
   * @throws IllegalArgumentException if the given vector is null
   */
  protected void verifyNotNull(Vector2D vector, String name) {
    if (vector == null) {
      throw new IllegalArgumentException(name + " cannot be null");
    }
  }

  /**
   * Constructs a new Vector2D object with the given x0 and x1 values.
   *
   * @param x0 the x0 value
   * @param x1 the x1 value
   */
  public Vector2D(double x0, double x1) {
    setX0(x0);
    setX1(x1);
  }

  /**
   * Deep copy constructor for Vector2D class.
   *
   * @param vector the Vector2D object to copy
   */
  public Vector2D(Vector2D vector) {
    verifyNotNull(vector, "Vector");
    setX0(vector.getX0());
    setX1(vector.getX1());
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
   * Sets the x0 value of this vector.
   *
   * @param x0 the new x0 value
   */
  public void setX0(double x0) {
    this.x0 = x0;
  }

  /**
   * Sets the x1 value of this vector.
   *
   * @param x1 the new x1 value
   */
  public void setX1(double x1) {
    this.x1 = x1;
  }

  /**
   * adds the given vector to this vector, and returns the result as a new vector.
   *
   * @param vector the vector to add
   * @return the new vector
   * @throws IllegalArgumentException if the given vector is null
   */
  public Vector2D add(Vector2D vector) {
    verifyNotNull(vector, "Vector");
    double newX0 = getX0() + vector.getX0();
    double newX1 = getX1() + vector.getX1();
    return new Vector2D(newX0, newX1);
  }

  /**
   * Subtracts the given vector from this vector.
   *
   * @param vector the vector to subtract
   * @return the new vector
   * @throws IllegalArgumentException if the given vector is null
   */
  public Vector2D subtract(Vector2D vector) {
    verifyNotNull(vector, "Vector");
    double newX0 = x0 - vector.getX0();
    double newX1 = x1 - vector.getX1();
    return new Vector2D(newX0, newX1);
  }

  /**
   * Multiplies this vector by the given scalar.
   *
   * @param scalar the scalar to multiply by
   * @return the new vector
   */
  public Vector2D multiply(double scalar) {
    double newX0 = x0 * scalar;
    double newX1 = x1 * scalar;
    return new Vector2D(newX0, newX1);
  }

  /**
   * Checks if the given vector object has the same values as this vector.
   *
   * @param vector the vector to compare
   * @return true if the given vector has the same values as this vector, false otherwise
   */
  public boolean equals(Vector2D vector) {
    if ((vector instanceof Complex) || vector == null) {
      return false;
    }
    return x0 == vector.getX0() && x1 == vector.getX1();
  }

  public String toString() {
    return x0 + ", " + x1;
  }
}
