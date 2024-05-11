package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChaosGameTest {
  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("Constructor throws no exceptions with valid input")
    void testConstructorThrowsNoExceptions() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("Constructor creates a ChaosGame")
    void testConstructorCreatesChaosGame() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        assertNotNull(chaosGame);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
    @Test
    @DisplayName("getCanvas returns a canvas")
    void testGetCanvasReturnsCanvas() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        assertNotNull(chaosGame.getCanvas());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getDescription returns a description")
    void testGetDescriptionReturnsDescription() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        assertNotNull(chaosGame.getDescription());
        assertEquals(chaosGame.getDescription(), description);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("runSteps changes points in the canvas")
    void testRunStepsChangesPoints() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        chaosGame.runSteps(1000);

        ChaosGame chaosGame2 = new ChaosGame(description, 100,100);
        chaosGame2.runSteps(1000);

        assertNotEquals(chaosGame.getCanvas(), chaosGame2.getCanvas());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("changeDescription changes the description")
    void testChangeDescriptionChangesDescription() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        ChaosGameDescription description2 = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        chaosGame.setDescription(description2);

        assertNotEquals(chaosGame.getDescription(), description);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("setCanvasSize changes the canvas")
    void testSetCanvasSizeChangesCanvas() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);

        assertEquals(chaosGame.getCanvas().getHeight(), 100);
        assertEquals(chaosGame.getCanvas().getHeight(), 100);

        chaosGame.setCanvasSize(300,300);
        assertEquals(chaosGame.getCanvas().getWidth(), 300);
        assertEquals(chaosGame.getCanvas().getHeight(), 300);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }





  }
  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("Constructor throws an exception with null description")
    void testConstructorThrowsExceptionWithNullDescription() {
      try {
        ChaosGame chaosGame = new ChaosGame(null, 100,100);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Description cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor throws an exception with negative width")
    void testConstructorThrowsExceptionWithNegativeWidth() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, -100,100);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("Constructor throws an exception with negative height")
    void testConstructorThrowsExceptionWithNegativeHeight() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,-100);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("runSteps throws an exception with negative steps")
    void testRunStepsThrowsExceptionWithNegativeSteps() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        chaosGame.runSteps(-100);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Steps need to be more than 0");
      }
    }

    @Test
    @DisplayName("setDescription throws an exception with null description")
    void testSetDescriptionThrowsExceptionWithNullDescription() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        chaosGame.setDescription(null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Description cannot be null");
      }
    }

    @Test
    @DisplayName("setCanvasSize throws an exception with negative width")
    void testSetCanvasSizeThrowsExceptionWithNegativeWidth() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        chaosGame.setCanvasSize(-100,100);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("setCanvasSize throws an exception with negative height")
    void testSetCanvasSizeThrowsExceptionWithNegativeHeight() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(100,100),transforms );
        ChaosGame chaosGame = new ChaosGame(description, 100,100);
        chaosGame.setCanvasSize(100,-100);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }



  }

}