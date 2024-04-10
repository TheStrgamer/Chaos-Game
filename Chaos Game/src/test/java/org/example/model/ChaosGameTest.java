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
    @DisplayName("runSteps changes random points in the canvas")
    void testRunStepsChangesRandomPoints() {
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





  }
  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

  }

}