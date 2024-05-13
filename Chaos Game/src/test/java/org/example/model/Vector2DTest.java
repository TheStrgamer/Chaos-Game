package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.example.model.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class Vector2DTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    //Todo test tostring

    @Test
    @DisplayName("getX0 returns the x0 value of the vector")
    void testGetX0returnsCorrectValue() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        assertEquals(1, vector.getX0());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getX1 returns the x1 value of the vector")
    void testGetX1returnsCorrectValue() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        assertEquals(2, vector.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("add method returns a new vector")
    void testAddMethodReturnsNewVector() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        Vector2D vector2 = new Vector2D(3, 4);
        Vector2D vector3 = vector.add(vector2);
        assertNotEquals(vector, vector3);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("add method returns vector with correct values")
    void testAddMethodReturnsVectorWithCorrectValues() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        Vector2D vector2 = new Vector2D(3, 4);
        Vector2D vector3 = vector.add(vector2);
        assertEquals(4, vector3.getX0());
        assertEquals(6, vector3.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("subtract method returns a new vector")
    void testSubtractMethodReturnsNewVector() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        Vector2D vector2 = new Vector2D(3, 4);
        Vector2D vector3 = vector.subtract(vector2);
        assertNotEquals(vector, vector3);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("subtract method returns vector with correct values")
    void testSubtractMethodReturnsVectorWithCorrectValues() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        Vector2D vector2 = new Vector2D(3, 4);
        Vector2D vector3 = vector.subtract(vector2);
        assertEquals(-2, vector3.getX0());
        assertEquals(-2, vector3.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("multiply method returns a new vector")
    void testMultiplyMethodReturnsNewVector() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        Vector2D vector2 = vector.multiply(2);
        assertNotEquals(vector, vector2);
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("multiply method returns vector with correct values")
    void testMultiplyMethodReturnsVectorWithCorrectValues() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        Vector2D vector2 = vector.multiply(2);
        assertEquals(2, vector2.getX0());
        assertEquals(4, vector2.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("setter sets the x0 value of this vector")
    void testSetX0SetsCorrectly() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        vector.setX0(3);
        assertEquals(3, vector.getX0());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("setter sets the x1 value of this vector")
    void testSetX1SetsCorrectly() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        vector.setX1(3);
        assertEquals(3, vector.getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("toString returns the correct string")
    void testToStringReturnsCorrectString() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        assertEquals("1.0, 2.0", vector.toString());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }
  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {


    @Test
    @DisplayName("add method throws IllegalArgumentException when given vector is null")
    void testAddMethodThrowsOnNull() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        vector.add(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("subtract method throws IllegalArgumentException when given vector is null")
    void testSubtractMethodThrowsOnNull() {
      try {
        Vector2D vector = new Vector2D(1, 2);
        vector.subtract(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }
  }

}