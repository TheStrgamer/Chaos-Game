package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.example.model.math.Complex;
import org.example.model.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ComplexTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("getReal returns the real part of the complex number")
    void testGetRealReturnsCorrectValue() {
      try {
        Complex complex = new Complex(1, 2);
        assertEquals(1, complex.getReal());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getImaginary returns the imaginary part of the complex number")
    void testGetImaginaryReturnsCorrectValue() {
      try {
        Complex complex = new Complex(1, 2);
        assertEquals(2, complex.getImaginary());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }


    @Test
    @DisplayName("add method returns a new complex number")
    void testAddMethodReturnsNewComplex() {
      try {
        Complex complex = new Complex(1, 2);
        Complex complex2 = new Complex(3, 4);
        Complex complex3 = complex.add(complex2);
        assertNotEquals(complex, complex3);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("add method returns a complex number with the correct values")
    void testAddMethodReturnsComplexWithCorrectValues() {
      try {
        Complex complex = new Complex(1, 2);
        Complex complex2 = new Complex(3, 4);
        Complex complex3 = complex.add(complex2);
        assertEquals(4, complex3.getReal());
        assertEquals(6, complex3.getImaginary());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("subtract method returns a new complex number")
    void testSubtractMethodReturnsNewComplex() {
      try {
        Complex complex = new Complex(1, 2);
        Complex complex2 = new Complex(3, 4);
        Complex complex3 = complex.subtract(complex2);
        assertNotEquals(complex, complex3);
      } catch (Exception e) {
        fail("An exception was thrown" + e.getMessage());
      }
    }

    @Test
    @DisplayName("subtract method returns a complex number with the correct values")
    void testSubtractMethodReturnsComplexWithCorrectValues() {
      try {
        Complex complex = new Complex(1, 2);
        Complex complex2 = new Complex(3, 4);
        Complex complex3 = complex.subtract(complex2);
        assertEquals(-2, complex3.getReal());
        assertEquals(-2, complex3.getImaginary());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("sqrt method returns a new complex number")
    void testSqrtMethodReturnsNewComplex() {
      try {
        Complex complex = new Complex(3, 4);
        Complex complex2 = complex.sqrt();
        assertNotEquals(complex, complex2);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("sqrt method returns a complex number with the correct values")
    void testSqrtMethodReturnsComplexWithCorrectValues() {
      try {
        Complex complex = new Complex(3, 4);
        Complex complex2 = complex.sqrt();
        assertEquals(2, complex2.getReal(), 0.001);
        assertEquals(1, complex2.getImaginary(), 0.001);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("multiply method multiplies the complex number with scalar")
    void testMultiplyMethodReturnsNewComplex() {
      try {
        Complex complex = new Complex(1, 2);
        Complex result = complex.multiply(2);
        assertEquals(2, result.getReal());
        assertEquals(4, result.getImaginary());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }


    @Test
    @DisplayName("setX0 sets the x0 value of the vector")
    void testSetX0SetsCorrectly() {
      try {
        Complex complex = new Complex(1, 2);
        complex.setX0(3);
        assertEquals(3, complex.getReal());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("setX1 sets the x1 value of the vector")
    void testSetX1SetsCorrectly() {
      try {
        Complex complex = new Complex(1, 2);
        complex.setX1(3);
        assertEquals(3, complex.getImaginary());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("toString returns correct string")
    void testToString() {
      try {
        Complex complex = new Complex(1, 2);
        assertEquals("1.0, 2.0", complex.toString());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns true when complex numbers are equal")
    void testEqualsReturnsTrueWithEqualValues() {
      try {
        Complex complex1 = new Complex(1, 2);
        Complex complex2 = new Complex(1, 2);
        assertTrue(complex1.equals(complex2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when complex numbers are not equal")
    void testEqualsReturnsFalseWithDifferentComplex() {
      try {
        Complex complex1 = new Complex(1, 2);
        Complex complex2 = new Complex(3, 4);
        assertFalse(complex1.equals(complex2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when comparing to null")
      void testEqualsReturnsFalseWithNull() {
      try {
        Complex complex = new Complex(1, 2);
        assertFalse(complex.equals(null));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when comparing to a vector")
    void testEqualsReturnsFalseWithVector() {
      try {
        Complex complex = new Complex(1, 2);
        Vector2D vector = new Vector2D(3, 4);
        assertFalse(complex.equals(vector));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("add throws IllegalArgumentException when given a vector")
    void testCantAddVectorAndComplex() {
      try {
        Complex complex = new Complex(1, 2);
        Vector2D vector = new Vector2D(3, 4);
        complex.add(vector);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Input must be of the Complex class");
      }
    }

    @Test
    @DisplayName("subtract throws IllegalArgumentException when given a vector")
    void testCantSubtractVectorAndComplex() {
      try {
        Complex complex = new Complex(1, 2);
        Vector2D vector = new Vector2D(3, 4);
        complex.subtract(vector);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Input must be of the Complex class");
      }
    }

    @Test
    @DisplayName("add throws IllegalArgumentException when given null")
    void testAddMethodThrowsOnNull() {
      try {
        Complex complex = new Complex(1, 2);
        complex.add(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Complex cannot be null");
      }
    }

    @Test
    @DisplayName("subtract throws IllegalArgumentException when given null")
    void testSubtractMethodThrowsOnNull() {
      try {
        Complex complex = new Complex(1, 2);
        complex.subtract(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Complex cannot be null");
      }
    }
  }
}