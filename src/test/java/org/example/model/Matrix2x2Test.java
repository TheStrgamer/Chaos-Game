package org.example.model;

import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class Matrix2x2Test {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("constructor throws no exception with valid parameters")
    void testConstructorThrowsNoErrorsWithValidParameters() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("deep copy constructor throws no exception with valid parameters")
    void testDeepCopyConstructorThrowsNoErrorsWithValidParameters() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Matrix2x2 matrix2 = new Matrix2x2(matrix);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("constructor sets the values correctly")
    void testConstructorSetsValuesCorrectly() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        double[] values = matrix.getValues();
        assertEquals(1, values[0]);
        assertEquals(2, values[1]);
        assertEquals(3, values[2]);
        assertEquals(4, values[3]);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("deep copy constructor sets the values correctly")
    void testDeepCopyConstructorSetsValuesCorrectly() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Matrix2x2 matrix2 = new Matrix2x2(matrix);
        double[] values = matrix2.getValues();
        assertEquals(1, values[0]);
        assertEquals(2, values[1]);
        assertEquals(3, values[2]);
        assertEquals(4, values[3]);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("deep copy constructor returns a new object")
    void testDeepCopyConstructorReturnsNewObject() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Matrix2x2 matrix2 = new Matrix2x2(matrix);
        assertNotEquals(matrix, matrix2);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("Multiply method returns correct vector")
    void multiplyMethodCalculatesCorrectlyFirstValues() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Vector2D result_vector = matrix.multiply(new Vector2D(1, 2));
        assertEquals(5, result_vector.getX0());
        assertEquals(11, result_vector.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("Multiply method returns correct vector")
    void multiplyMethodCalculatesCorrectlySecondValues() {
      try {
        Matrix2x2 matrix = new Matrix2x2(3, 3, 4, 4);
        Vector2D result_vector = matrix.multiply(new Vector2D(5, 0));
        assertEquals(15, result_vector.getX0());
        assertEquals(20, result_vector.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("toString returns correct string")
    void testToString() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        assertEquals("1.0, 2.0, 3.0, 4.0", matrix.toString());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("Matrix can multiply vector twice without changing the result")
    void testMultiplyTwice() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Vector2D vector = new Vector2D(1, 2);
        Vector2D result1 = matrix.multiply(vector);
        Vector2D result2 = matrix.multiply(vector);
        assertEquals(result1.getX0(), result2.getX0());
        assertEquals(result1.getX1(), result2.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns true when matrices are equal")
    void testEqualsReturnsTrueWhenMatricesAreEqual() {
      try {
        Matrix2x2 matrix1 = new Matrix2x2(1, 2, 3, 4);
        Matrix2x2 matrix2 = new Matrix2x2(1, 2, 3, 4);
        assertTrue(matrix1.equals(matrix2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when matrices are not equal")
    void testEqualsReturnsFalseWhenMatricesAreNotEqual() {
      try {
        Matrix2x2 matrix1 = new Matrix2x2(1, 2, 3, 4);
        Matrix2x2 matrix2 = new Matrix2x2(5, 6, 7, 8);
        assertFalse(matrix1.equals(matrix2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when comparing to null")
    void testEqualsReturnsFalseWhenComparingToNull() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        assertFalse(matrix.equals(null));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

  }

  @Nested
  @DisplayName("Negative Tests")
  class NegativeTests {

    @Test
    @DisplayName("Multiply method throws exception when given vector is null")
    void testSubtractMethodThrowsOnNull() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        Vector2D vector = null;
        matrix.multiply(vector);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }
  }
}
