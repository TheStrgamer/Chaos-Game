package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.example.model.math.Complex;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class JuliaTransformTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {
    @Test
    @DisplayName("Constructor throws no exceptions with valid input")
    void testConstructorThrowsNoExceptions() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Transform method does not return null")
    void testTransformMethodReturnsValidComplex() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
        Complex complex = new Complex(3, 4);
        Complex result = (Complex) juliaTransform.transform(complex);
        assertNotNull(result);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Transform method transforms the given complex number correctly with positive sign")
    void testTransformMethodTransformsCorrectlyWithPositiveSign() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(0.3, 0.6), 1);
        Complex complex = new Complex(0.4, 0.2);
        Complex result = (Complex) juliaTransform.transform(complex);
        assertEquals(0.506, Math.round(result.getReal()), 3);
        assertEquals(0.395, Math.round(result.getImaginary()), 3);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }

    }

    @Test
    @DisplayName("Transform method transforms the given complex number correctly with negative sign")
    void testTransformMethodTransformsCorrectlyWithNegativeSign() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(0.3, 0.6), -1);
        Complex complex = new Complex(0.4, 0.2);
        Complex result = (Complex) juliaTransform.transform(complex);
        assertEquals(-0.506, Math.round(result.getReal()), 3);
        assertEquals(-0.395, Math.round(result.getImaginary()), 3);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Transform method returns opposite result with opposite sign")
    void testTransformReturnsOppositeResultWithOppositeSign() {
      try {
        JuliaTransform juliaTransformPositive = new JuliaTransform(new Complex(0.3, 0.6), 1);
        JuliaTransform juliaTransformNegative = new JuliaTransform(new Complex(0.3, 0.6), -1);
        Complex complex1 = new Complex(0.4, 0.2);
        Complex complex2 = new Complex(0.4, 0.2);
        Complex result_1 = (Complex) juliaTransformPositive.transform(complex1);
        Complex result_2 = (Complex) juliaTransformNegative.transform(complex2);

        assertEquals(-result_1.getReal(), result_2.getReal());
        assertEquals(-result_1.getImaginary(), result_2.getImaginary());

      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("toString returns the correct string")
    void testToStringReturnsCorrectString() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
        assertEquals("1.0, 2.0, 1", juliaTransform.toStringWithSign());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("toStringWithoutSign returns the correct string")
    void testToStringWithoutSignReturnsCorrectString() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
        assertEquals("1.0, 2.0", juliaTransform.toString());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("equals returns true when objects are equal")
    void testEqualsReturnsTrueWhenObjectsAreEqual() {
      try {
        JuliaTransform juliaTransform1 = new JuliaTransform(new Complex(1, 2), 1);
        JuliaTransform juliaTransform2 = new JuliaTransform(new Complex(1, 2), 1);
        assertTrue(juliaTransform1.equals(juliaTransform2));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("equals returns false when the signs are not equal")
    void testEqualsReturnsFalseWhenSignsAreNotEqual() {
      try {
        JuliaTransform juliaTransform1 = new JuliaTransform(new Complex(1, 2), 1);
        JuliaTransform juliaTransform2 = new JuliaTransform(new Complex(1, 2), -1);
        assertFalse(juliaTransform1.equals(juliaTransform2));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("equals returns false when comparing to affine transformation")
    void testEqualsReturnsFalseWhenComparingToAffineTransform() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
        AffineTransform2D affineTransform = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4), new Vector2D(5, 6));
        assertFalse(juliaTransform.equals(affineTransform));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("equals returns false when the complex numbers are not equal")
    void testEqualsReturnsFalseWhenComplexNumbersAreNotEqual() {
      try {
        JuliaTransform juliaTransform1 = new JuliaTransform(new Complex(1, 2), 1);
        JuliaTransform juliaTransform2 = new JuliaTransform(new Complex(2, 1), 1);
        assertFalse(juliaTransform1.equals(juliaTransform2));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getPoint returns the correct complex number")
    void testGetPointReturnsCorrectComplexNumber() {
      try {
        Complex complex = new Complex(1, 2);
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
        Complex testComplex = juliaTransform.getPoint();
        assertTrue(complex.equals(testComplex));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("Constructor throws IllegalArgumentException with null point")
    void testConstructorThrowsIllegalArgumentExceptionWithNullPoint() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(null, 1);
        fail("An exception was not thrown");
      } catch (IllegalArgumentException e) {
        assertEquals("Input must be of the Complex class", e.getMessage());
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException with invalid sign")
    void testConstructorThrowsIllegalArgumentExceptionWithInvalidSign() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 0);
        fail("An exception was not thrown");
      } catch (IllegalArgumentException e) {
        assertEquals("Sign must be 1 or -1", e.getMessage());
      }
    }

    @Test
    @DisplayName("Transform method throws IllegalArgumentException with null complex number")
    void testTransformMethodThrowsIllegalArgumentExceptionWithNullComplexNumber() {
      try {
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
        juliaTransform.transform(null);
        fail("An exception was not thrown");
      } catch (IllegalArgumentException e) {
        assertEquals("Vector cannot be null", e.getMessage());
      }
    }

  }

}