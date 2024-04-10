package org.example.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class Matrix2x2Test {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

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
    @DisplayName("toString returns the correct string")
    void testToStringReturnsCorrectString() {
      try {
        Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
        assertEquals("1.0, 2.0, 3.0, 4.0", matrix.toString());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    //Todo test toString
    //fikk også feil der jeg transformerte en vector, og så satt piksel, som gjorde enda en
    // transformasjon for å gjøre til indecies. tidligere ble transformasjonenen lagret i vektor
    // istedenfor å returnere en ny, men har fikset det. legg til test for å sørge for at
    // lignende feil  ikke skjer igjen. kan også gjøre noe lignende på complex
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
