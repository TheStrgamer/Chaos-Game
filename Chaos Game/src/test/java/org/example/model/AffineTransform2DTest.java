package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

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
    @DisplayName("Transform method does not return null")
    void testTransformMethodReturnsValidVector() {
      try {
        AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(1, 2, 3, 4),
            new Vector2D(5, 6));
        Vector2D vector = new Vector2D(7, 8);
        Vector2D result = (Vector2D) affineTransform2D.transform(vector);
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
        Vector2D result = (Vector2D) affineTransform2D.transform(vector);
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
        Vector2D result = (Vector2D) affineTransform2D.transform(vector);
        assertEquals(20, result.getX0());
        assertEquals(67, result.getX1());
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