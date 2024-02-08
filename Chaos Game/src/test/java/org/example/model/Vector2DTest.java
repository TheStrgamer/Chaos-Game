package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Vector2DTest {

  @Test
  @DisplayName("getX0 returns the x0 value of the vector")
  void getX0returnsCorrectValue() {
    try {
      Vector2D vector = new Vector2D(1, 2);
      assertEquals(1, vector.getX0());
    } catch (Exception e) {
      fail("An exception was thrown");
    }
  }

  @Test
  @DisplayName("getX1 returns the x1 value of the vector")
  void getX1returnsCorrectValue() {
    try {
      Vector2D vector = new Vector2D(1, 2);
      assertEquals(2, vector.getX1());
    } catch (Exception e) {
      fail("An exception was thrown");
    }
  }

  @Test
  @DisplayName("add method throws IllegalArgumentException when given vector is null")
  void verifyAddMethodThrowsOnNull() {
    try {
      Vector2D vector = new Vector2D(1, 2);
      vector.add(null);
      fail("An exception was not thrown");
    } catch (Exception e) {
      assertEquals(e.getMessage(),"Vector cannot be null");
    }
  }
  @Test
  @DisplayName("add method adds the given vector to this vector")
  void verifyAddMethodAddsCorrectly() {
    try {
      Vector2D vector = new Vector2D(1, 2);
      Vector2D vector2 = new Vector2D(3, 4);
      vector.add(vector2);
      assertEquals(4, vector.getX0());
      assertEquals(6, vector.getX1());
    } catch (Exception e) {
      fail("An exception was thrown");
    }
  }
  @Test
  @DisplayName("subtract method throws IllegalArgumentException when given vector is null")
  void verifySubtractMethodThrowsOnNull() {
    try {
      Vector2D vector = new Vector2D(1, 2);
      vector.subtract(null);
      fail("An exception was not thrown");
    } catch (Exception e) {
      assertEquals(e.getMessage(),"Vector cannot be null");
    }
  }
  @Test
  @DisplayName("subtract method subtracts the given vector from this vector")
  void verifySubtractMethodSubtractsCorrectly() {
    try {
      Vector2D vector = new Vector2D(1, 2);
      Vector2D vector2 = new Vector2D(3, 4);
      vector.subtract(vector2);
      assertEquals(-2, vector.getX0());
      assertEquals(-2, vector.getX1());
    } catch (Exception e) {
      fail("An exception was thrown");
    }
  }
  @Test
  @DisplayName("multiply method multiplies the given scalar with this vector")
  void verifyMultiplyMethodMultipliesCorrectly() {
    try {
      Vector2D vector = new Vector2D(1, 2);
      vector.multiply(2);
      assertEquals(2, vector.getX0());
      assertEquals(4, vector.getX1());
    } catch (Exception e) {
      fail("An exception was thrown");
    }
  }

}