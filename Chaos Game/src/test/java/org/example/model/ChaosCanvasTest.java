package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChaosCanvasTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests{

    @Test
    @DisplayName("Constructor throws no exceptions with valid input")
    void testConstructorThrowsNoExceptions() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100,new Vector2D(0,0), new Vector2D(200,200));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("SetPixel sets the pixel correctly")
    void testSetPixelSetsCorrectly() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100,new Vector2D(0,0), new Vector2D(200,200));
        chaosCanvas.setPixel(new Vector2D(0,0));
        assertEquals(1, chaosCanvas.getPixel(new Vector2D(0,0)));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices transforms the coordinates correctly")
    void testTransformCoordsToIndicesTransformsCorrectly() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(10, 10, new Vector2D(0, 0), new Vector2D(2, 2));
        Vector2D result = chaosCanvas.transformCoords(new Vector2D(1, 1));
        assertEquals(4.5, result.getX0());
        assertEquals(4.5, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("transformCoordsToIndices transforms the coordinates correctly with different values")
    void testTransformCoordsToIndicesTransformsCorrectlyWithDifferentValues() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(10, 10, new Vector2D(0, 0), new Vector2D(2, 2));
        Vector2D result = chaosCanvas.transformCoords(new Vector2D(1, 1));
        assertEquals(4.5, result.getX0());
        assertEquals(4.5, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
  }
}