package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.example.model.chaosGame.ChaosCanvas;
import org.example.model.math.Vector2D;
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
    @DisplayName("getPixel gets the pixel correctly")
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
    @DisplayName("getPixelFromCanvas gets the pixel without transforming the coordinates")
    void testGetPixelFromCanvasGetsCorrectPixel() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        assertEquals(0, chaosCanvas.getPixelFromCanvas(new Vector2D(0, 0)));
        assertEquals(0, chaosCanvas.getPixelFromCanvas(new Vector2D(chaosCanvas.getWidth()-1, chaosCanvas.getHeight()-1)));

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
        assertEquals(255, chaosCanvas.getPixel(new Vector2D(0, 0)));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("SetPixel with value sets the pixel correctly")
    void testSetPixelWithValueSetsCorrectly() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.setPixel(new Vector2D(0, 0),5);
        assertEquals(5, chaosCanvas.getPixel(new Vector2D(0, 0)));
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
        assertEquals(255, chaosCanvas.getPixel(new Vector2D(0, 0)));
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
        assertEquals(255, chaosCanvas.getPixel(new Vector2D(0, 0)));
        chaosCanvas.clear();
        assertEquals(0, chaosCanvas.getPixel(new Vector2D(0, 0)));
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getInfoString returns correct info")
    void testGetInfoStringReturnsCorrectInfo() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(101, 101, new Vector2D(0, 0),
            new Vector2D(100, 100));
        String result = chaosCanvas.getInfoString();
        assertNotNull(result);
        assertEquals("ChaosCanvas{" +
            "width=" + 101 +
            ", height=" + 101 +
            ", minCoords=0.0, 0.0" +
            ", maxCoords=100.0, 100.0" +
            '}', result);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("toString returns correct string")
    void testToStringReturnsCorrectString() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(11, 11, new Vector2D(0, 0),
            new Vector2D(10, 10));
        chaosCanvas.setPixel(new Vector2D(0, 0));
        chaosCanvas.setPixel(new Vector2D(10, 10));
        String result = chaosCanvas.toString();
        assertNotNull(result);
        assertEquals("          X\n"
            + "           \n"
            + "           \n"
            + "           \n"
            + "           \n"
            + "           \n"
            + "           \n"
            + "           \n"
            + "           \n"
            + "           \n"
            + "X          \n", result);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

  }


  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when width is negative")
    void constructorThrowsExceptionOnNegativeWidth() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(-100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when height is negative")
    void constructorThrowsExceptionOnNegativeHeight() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, -100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when width is negative")
    void constructorThrowsExceptionOnListNull() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(-100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when minimum coordinates are null")
    void constructorThrowsExceptionOnNullMinCoords() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, null, new Vector2D(200, 200));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when maximum coordinates are null")
    void constructorThrowsExceptionOnNullMaxCoords() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0), null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("getPixel throws IllegalArgumentException when given point is not within the given parameters")
    void getPixelThrowsExceptionOnPointNotWithinParameters() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.getPixel(new Vector2D(300, 300));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Point 300.0, 300.0 is not within the given parameters 0.0, 0.0; 200.0, 200.0");
      }
    }

    @Test
    @DisplayName("getPixel throws IllegalArgumentException when given point is null")
    void getPixelThrowsExceptionWhenPointIsNull() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.getPixel(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("setPixel does nothing when given point is not within the given parameters")
    void setPixelThrowsExceptionOnPointNotWithinParameters() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.setPixel(new Vector2D(300, 300));
        assertEquals(0, Arrays.stream(chaosCanvas.getCanvasArray()).flatMapToInt(Arrays::stream).max().getAsInt());
      } catch (Exception e) {
        fail(e.getMessage());
      }
    }

    @Test
    @DisplayName("setPixel throws IllegalArgumentException when given point is null")
    void setPixelThrowsExceptionWhenPointIsNull() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.setPixel(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices throws IllegalArgumentException when given point is not within the given parameters")
    void transformCoordsToIndicesThrowsExceptionOnPointNotWithinParameters() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.transformCoords(new Vector2D(300, 300));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Point 300.0, 300.0 is not within the given parameters 0.0, 0.0; 200.0, 200.0");
      }
    }

    @Test
    @DisplayName("transformCoordsToIndices throws IllegalArgumentException when given point is null")
    void transformCoordsToIndicesThrowsExceptionWhenPointIsNull() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.transformCoords(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("removePixel throws IllegalArgumentException when given point is not within the given parameters")
    void removePixelThrowsExceptionOnPointNotWithinParameters() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.removePixel(new Vector2D(300, 300));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Point 300.0, 300.0 is not within the given parameters 0.0, 0.0; 200.0, 200.0");
      }
    }

    @Test
    @DisplayName("removePixel throws IllegalArgumentException when given point is null")
    void removePixelThrowsExceptionWhenPointIsNull() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(0, 0),
            new Vector2D(200, 200));
        chaosCanvas.removePixel(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor Throws exception when minCoords are bigger than maxCoords")
    void ConstructorThrowsExceptionOnMinCoordsBiggerThanMaxCoords() {
      try {
        ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, new Vector2D(200, 200),
            new Vector2D(0, 0));
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Minimum coordinates must be less than maximum coordinates");
      }
    }
  }
}
