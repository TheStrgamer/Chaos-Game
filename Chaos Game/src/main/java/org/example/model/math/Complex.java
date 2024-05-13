package org.example.model.math;

/**
 * <h1>Complex</h1>
 * A class representing a complex number. It extends the Vector2D class and adds methods for
 * performing complex number operations.
 */
public class Complex extends Vector2D {

  /**
   * checks if the given vector is of the complex class
   *
   * @param vector the vector to check
   */
  private void verifyVectorIsComplex(Vector2D vector) throws IllegalArgumentException {
    if (!(vector instanceof Complex)) {
      throw new IllegalArgumentException("Input must be of the Complex class");
    }
  }

  /**
   * checks if the given complex number is not null
   *
   * @param complex the complex number to check
   */
  private void verifyComplexIsNotNull(Vector2D complex) throws IllegalArgumentException {
    if (complex == null) {
      throw new IllegalArgumentException("Complex cannot be null");
    }
  }

  /**
   * Constructs a new Complex object with the given real and imaginary values.
   *
   * @param real      the real value
   * @param imaginary the imaginary value
   */
  public Complex(double real, double imaginary) {
    super(real, imaginary);
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
   */
  public Complex add(Vector2D complex) {
    verifyComplexIsNotNull(complex);
    verifyVectorIsComplex(complex);
    Vector2D result = super.add(complex);
    return new Complex(result.getX0(), result.getX1());
  }

  /**
   * Subtracts the given complex number from this complex number.
   *
   * @param complex the complex number to subtract
   */
  public Complex subtract(Vector2D complex) {
    verifyComplexIsNotNull(complex);
    verifyVectorIsComplex(complex);
    Vector2D result = super.subtract(complex);
    return new Complex(result.getX0(), result.getX1());
  }

  /**
   * Returns the square root of this complex number.
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
   */
  public Complex multiply(double scalar) {
    Vector2D result = super.multiply(scalar);
    return new Complex(result.getX0(), result.getX1());
  }

  /**
   * Returns a string representation of the complex number.
   */
  public String toString() {
    return getReal() + ", " + getImaginary();
  }

}
