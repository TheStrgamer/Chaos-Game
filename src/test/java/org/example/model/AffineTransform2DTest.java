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

class AffineTransform2DTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Constructor throws no exceptions with valid input")
    void testConstructorThrowsNoExceptions() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Constructor sets the values correctly")
    void testConstructorSetsValuesCorrectly() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        assertEquals(1, affineTransform2D.getMatrix().getValues()[0]);
        assertEquals(2, affineTransform2D.getMatrix().getValues()[1]);
        assertEquals(3, affineTransform2D.getMatrix().getValues()[2]);
        assertEquals(4, affineTransform2D.getMatrix().getValues()[3]);
        assertEquals(5, affineTransform2D.getVector().getX0());
        assertEquals(6, affineTransform2D.getVector().getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Deep copy constructor throws no exceptions with valid input")
    void testDeepCopyConstructorThrowsNoExceptions() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(affineTransform2D);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Deep copy constructor sets the values correctly")
    void testDeepCopyConstructorSetsValuesCorrectly() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(affineTransform2D);
        assertEquals(1, affineTransform2D2.getMatrix().getValues()[0]);
        assertEquals(2, affineTransform2D2.getMatrix().getValues()[1]);
        assertEquals(3, affineTransform2D2.getMatrix().getValues()[2]);
        assertEquals(4, affineTransform2D2.getMatrix().getValues()[3]);
        assertEquals(5, affineTransform2D2.getVector().getX0());
        assertEquals(6, affineTransform2D2.getVector().getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Deep copy constructor creates a new object")
    void testDeepCopyConstructorReturnsNewObject() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(affineTransform2D);
        assertNotEquals(affineTransform2D, affineTransform2D2);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Transform method does not return null")
    void testTransformMethodReturnsValidVector() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        Vector2D vector = new Vector2D(7, 8);
        Vector2D result = affineTransform2D.transform(vector);
        assertNotNull(result);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Transform method transforms the given vector correctly")
    void testTransformMethodTransformsCorrectly() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(.5, 1, 1, .5),
            new Vector2D(3, 1));
        Vector2D vector = new Vector2D(1, 2);
        Vector2D result = affineTransform2D.transform(vector);
        assertEquals(5.5, result.getX0());
        assertEquals(3, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Transform method transforms the given vector correctly with different values")
    void testTransformMethodTransformsCorrectlyWithDifferentValues() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(2, 3, 6, 9),
            new Vector2D(1, 10));
        Vector2D vector = new Vector2D(2, 5);
        Vector2D result = affineTransform2D.transform(vector);
        assertEquals(20, result.getX0());
        assertEquals(67, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("toString returns a string")
    void testToStringReturnsString() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        assertEquals("1.0, 2.0, 3.0, 4.0, 5.0, 6.0", affineTransform2D.toString());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("equals returns true when values are equal")
    void testEqualsReturnsTrue() {
      try {
        AffineTransform2D affineTransform2D1 = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        assertTrue(affineTransform2D1.equals(affineTransform2D2));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("equals returns false when vectors are not equal")
    void testEqualsReturnsFalse() {
      try {
        AffineTransform2D affineTransform2D1 = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(7, 8));
        assertFalse(affineTransform2D1.equals(affineTransform2D2));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("equals returns false when matrices are not equal")
    void testEqualsReturnsFalseWhenMatricesAreNotEqual() {
      try {
        AffineTransform2D affineTransform2D1 = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        AffineTransform2D affineTransform2D2 = new AffineTransform2D(new Matrix2x2(5, 6, 7, 8),
            new Vector2D(5, 6));
        assertFalse(affineTransform2D1.equals(affineTransform2D2));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("equals returns false when comparing to a julia transformation")
    void testEqualsReturnsFalseWhenComparingToJuliaTransform() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        JuliaTransform juliaTransform = new JuliaTransform(new Complex(1, 2), 1);
        assertFalse(affineTransform2D.equals(juliaTransform));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }


  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("Transform method throws exception when given vector is null")
    void testTransformMethodThrowsOnNull() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        affineTransform2D.transform(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor throws exception when given matrix is null")
    void testConstructorThrowsOnNullMatrix() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(null, new Vector2D(5, 6));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Matrix cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor throws exception when given vector is null")
    void testConstructorThrowsOnNullVector() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }
  }

}