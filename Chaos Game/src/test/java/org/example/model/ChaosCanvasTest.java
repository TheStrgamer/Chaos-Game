package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChaosCanvasTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Constructor throws no exceptions with valid input")
    void testConstructorThrowsNoExceptions() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("GetPixel gets the pixel correctly")
    void testGetPixelGetsCorrectly() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        assertEquals(0, chaosCanvas.getPixel(new Vector2D(0, 0)));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("SetPixel throws no exceptions with valid input")
    void testSetPixelThrowsNoExceptions() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.setPixel(new Vector2D(0, 0));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("SetPixel sets the pixel correctly")
    void testSetPixelSetsCorrectly() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.setPixel(new Vector2D(0, 0));
        assertEquals(1, chaosCanvas.getPixel(new Vector2D(0, 0)));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("SetPixel does not set other pixels")
    void testSetPixelDoesNotSetOtherPixels() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.setPixel(new Vector2D(0, 0));
        assertEquals(0, chaosCanvas.getPixel(new Vector2D(0, 1)));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices throws no exceptions with valid input")
    void testTransformCoordsToIndicesThrowsNoExceptions() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.transformCoords(new Vector2D(0, 0));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("RemovePixel throws no exceptions with valid input")
    void testRemovePixelThrowsNoExceptions() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.removePixel(new Vector2D(0, 0));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("RemovePixel removes the pixel correctly")
    void testRemovePixelRemovesCorrectly() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.setPixel(new Vector2D(0, 0));
        assertEquals(1, chaosCanvas.getPixel(new Vector2D(0, 0)));
        chaosCanvas.removePixel(new Vector2D(0, 0));
        assertEquals(0, chaosCanvas.getPixel(new Vector2D(0, 0)));
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
        ChaosCanvas chaosCanvas = new ChaosCanvas(21, 21, new Vector2D(0, 0), new Vector2D(5, 5));
        Vector2D result = chaosCanvas.transformCoords(new Vector2D(2, 2));
        assertEquals(12, result.getX0());
        assertEquals(8, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices transforms the coordinates correctly with negative and positive values")
    void testTransformCoordsToIndicesTransformsCorrectlyWithNegativeAndPositiveValues() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(-50, -50),
            new Vector2D(50, 50));
        Vector2D result = chaosCanvas.transformCoords(new Vector2D(0, 0));
        assertEquals(50, result.getX0());
        assertEquals(50, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices transforms the coordinates correctly with negative coords")
    void testTransformCoordsToIndicesTransformsCorrectlyWithNegativeCoords() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(-100, -100),
            new Vector2D(0, 0));
        Vector2D result = chaosCanvas.transformCoords(new Vector2D(-50, -50));
        assertEquals(50, result.getX0());
        assertEquals(50, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices transforms max coords to top right corner indices")
    void testTransformCoordsToIndicesTransformsMaxCoordsToTopRight() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(0, 0),
            new Vector2D(100, 100));
        Vector2D result = chaosCanvas.transformCoords(new Vector2D(100, 100));
        assertEquals(0, result.getX0());
        assertEquals(100, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices transforms min coords to bottom left corner indices")
    void testTransformCoordsToIndicesTransformsMinCoordsToBottomLeft() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(0, 0),
            new Vector2D(100, 100));
        Vector2D result = chaosCanvas.transformCoords(new Vector2D(0, 0));
        assertEquals(100, result.getX0());
        assertEquals(0, result.getX1());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getCanvasArray returns the canvas correctly")
    void testGetCanvasArrayReturnsCorrectly() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(0, 0),
            new Vector2D(100, 100));
        int[][] result = chaosCanvas.getCanvasArray();
        assertNotNull(result);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getCanvasArray returns the canvas with correct dimensions")
    void testGetCanvasArrayReturnsCorrectlyWithCorrectDimensions() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(0, 0),
            new Vector2D(100, 100));
        int[][] result = chaosCanvas.getCanvasArray();
        assertEquals(101, result.length);
        assertEquals(101, result[0].length);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("getCanvasArray returns a new array")
    void testGetCanvasArrayReturnsNewArray() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(0, 0),
            new Vector2D(100, 100));
        int[][] result = chaosCanvas.getCanvasArray();
        assertNotEquals(chaosCanvas.getCanvasArray(), result);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("clear method clears the canvas")
    void testClearMethodClearsCanvas() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(0, 0),
            new Vector2D(100, 100));
        chaosCanvas.setPixel(new Vector2D(0, 0));
        assertEquals(1, chaosCanvas.getPixel(new Vector2D(0, 0)));
        chaosCanvas.clear();
        assertEquals(0, chaosCanvas.getPixel(new Vector2D(0, 0)));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }



  }
}