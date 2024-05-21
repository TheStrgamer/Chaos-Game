package org.example.model.math;

/**
 * <h2>Complex</h2>
 * <p>
 * A class representing a complex number. It extends the Vector2D class and adds methods for
 * performing complex number operations.
 * </p>
 * <p>
 * A complex number is a number that can be expressed in the form a + bi, where a and b are real
 * numbers, and i is an imaginary number with the property i^2 = -1.
 * </p>
 *
 * @version 0.4.0
 * @since 0.1.0
 */
public class Complex extends Vector2D {

  /**
   * checks if the given vector is of the complex class
   *
   * @param vector the vector to check
   * @throws IllegalArgumentException if the given vector is not of the Complex class
   */
  private void verifyVectorIsComplex(Vector2D vector) throws IllegalArgumentException {
    if (!(vector instanceof Complex)) {
      throw new IllegalArgumentException("Input must be of the Complex class");
    }
  }

  /**
   * Constructs a new Complex object with the given real and imaginary values.
   *
   * @param real      the real value
   * @param imaginary the imaginary value
   * @throws IllegalArgumentException if the given vector is null or not of the Complex class
   */
  public Complex(double real, double imaginary) {
    super(real, imaginary);
  }

  /**
   * Deep copy constructor for the Complex class. Used to create a new Complex object with the same
   *
   * @param complex the Complex object to copy
   */
  public Complex(Complex complex) {
    super(complex);
  }

  /**
   * Returns the real part of this complex number.
   *
   * @return the real part of this complex number
   */
  public double getReal() {
    return getX0();
  }

  /**
   * Returns the imaginary part of this complex number.
   *
   * @return the imaginary part of this complex number
   */
  public double getImaginary() {
    return getX1();
  }

  /**
   * Adds the given complex number to this complex number.
   *
   * @param complex the complex number to add
   * @return the sum of this complex number and the given complex number
   * @throws IllegalArgumentException if the given complex number is null or not of the Complex
   *                                  class
   */
  public Complex add(Vector2D complex) {
    verifyNotNull(complex, "Complex");
    verifyVectorIsComplex(complex);
    Vector2D result = super.add(complex);
    return new Complex(result.getX0(), result.getX1());
  }

  /**
   * Subtracts the given complex number from this complex number.
   *
   * @param complex the complex number to subtract
   * @return the difference between this complex number and the given complex number
   * @throws IllegalArgumentException if the given complex number is null or not of the Complex
   *                                  class
   */
  public Complex subtract(Vector2D complex) {
    verifyNotNull(complex, "Complex");
    verifyVectorIsComplex(complex);
    Vector2D result = super.subtract(complex);
    return new Complex(result.getX0(), result.getX1());
  }

  /**
   * Checks if the given complex number has the same values as this complex number.
   *
   * @param vector the vector to compare
   * @return true if the given complex number has the same values as this complex number, false
   */
  @Override
  public boolean equals(Vector2D vector) {
    if (!(vector instanceof Complex complex)) {
      return false;
    }
    return getReal() == complex.getReal() && getImaginary() == complex.getImaginary();
  }

  /**
   * Returns the square root of this complex number as a new complex numer.
   *
   * @return the root of the complex number
   */
  public Complex sqrt() {
    double real = getReal();
    double imaginary = getImaginary();
    double r = Math.sqrt(real * real + imaginary * imaginary);
    double newReal = Math.sqrt((r + real) / 2);
    double newImaginary = Math.sqrt((r - real) / 2) * Math.signum(imaginary);

    return new Complex(newReal, newImaginary);
  }

  /**
   * Multiplies the given complex number by given scalar.
   *
   * @param scalar the scalar to multiply by
   * @return the product of the complex number and the scalar
   */
  public Complex multiply(double scalar) {
    Vector2D result = super.multiply(scalar);
    return new Complex(result.getX0(), result.getX1());
  }

  /**
   * Returns a string representation of the complex number.
   *
   * @return string of complex number
   */
  public String toString() {
    return getReal() + ", " + getImaginary();
  }

}
