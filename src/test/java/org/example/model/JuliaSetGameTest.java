package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.example.model.chaosgame.ChaosGameDescription;
import org.example.model.chaosgame.JuliaSetGame;
import org.example.model.math.Complex;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.example.model.transform.Transform2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class JuliaSetGameTest {

  @Nested
  class PositiveTests {

    @Test
    @DisplayName("constructor throws no exception with valid parameters")
    void testConstructorThrowsNoErrorsWithValidParameters() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
      } catch (Exception e) {
        fail("Julia set constructor should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("constructor creates Julia set object")
    void testConstructorCreatesJuliasetObject() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        assertNotNull(juliaSetGame);
      } catch (Exception e) {
        fail("Julia set constructor should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("constructor creates Julia set object with correct parameters")
    void testConstructorCreatesJuliaSetObjectWithCorrectParameters() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        assertEquals(juliaSetGame.getCanvas().getWidth(), 100);
        assertEquals(juliaSetGame.getCanvas().getHeight(), 100);
      } catch (Exception e) {
        fail("Julia set constructor should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("setCanvasSize throws no exception with valid parameters")
    void testSetCanvasSizeThrowsNoErrorsWithValidParameters() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        juliaSetGame.setCanvasSize(200, 200);
      } catch (Exception e) {
        fail("setCanvasSize should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("setCanvasSize changes canvas size")
    void testSetCanvasSizeChangesCanvasSize() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        assertEquals(juliaSetGame.getCanvas().getWidth(), 100);
        assertEquals(juliaSetGame.getCanvas().getHeight(), 100);

        juliaSetGame.setCanvasSize(200, 200);

        assertEquals(juliaSetGame.getCanvas().getWidth(), 200);
        assertEquals(juliaSetGame.getCanvas().getHeight(), 200);
      } catch (Exception e) {
        fail("setCanvasSize should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("changeZoom throws no exception with valid parameters")
    void testChangeZoomThrowsNoErrorsWithValidParameters() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        juliaSetGame.changeZoom(1.0);
      } catch (Exception e) {
        fail("changeZoom should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("changeZoom changes zoom level")
    void testChangeZoomChangesZoomLevel() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        assertEquals(juliaSetGame.getZoom(), 1.0);

        juliaSetGame.changeZoom(1.0);

        assertEquals(juliaSetGame.getZoom(), 2.0);
      } catch (Exception e) {
        fail("changeZoom should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("moveCanvas throws no exception with valid parameters")
    void testMoveCanvasThrowsNoErrorsWithValidParameters() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        juliaSetGame.moveCanvas(new Vector2D(1.0, 1.0));
      } catch (Exception e) {
        fail("moveCanvas should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("moveCanvas moves canvas")
    void testMoveCanvasMovesCanvas() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        assertEquals(juliaSetGame.getOffsetX(), 0.0);
        assertEquals(juliaSetGame.getOffsetY(), 0.0);

        juliaSetGame.moveCanvas(new Vector2D(1.0, 1.0));

        assertEquals(juliaSetGame.getOffsetX(), -.1);
        assertEquals(juliaSetGame.getOffsetY(), .1);
      } catch (Exception e) {
        fail("moveCanvas should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("calculateValue calculates the value correctly")
    void testCalculateValueCalculatesValueCorrectly() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
        assertEquals(juliaSetGame.calculateValue(1, 1, 1), 407);
      } catch (Exception e) {
        fail("calculateValue should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("calculatePoint throws no exception with valid parameters")
    void testCalculatePointThrowsNoExceptions() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
        juliaSetGame.calculatePoint(1, 1, 1.0, 1.0);
      } catch (Exception e) {
        fail("calculatePoint should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("runSteps throws no exception with valid parameters")
    void testRunStepsThrowsNoExceptions() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
        juliaSetGame.runSteps(100);
      } catch (Exception e) {
        fail("runSteps should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("setDescription sets the description correctly")
    void testSetDescriptionSetsDescriptionCorrectly() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription1 = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);
        ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(new Vector2D(1, 1),
            new Vector2D(2, 2), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription1, 100, 100, 100, 2.0);
        juliaSetGame.setDescription(chaosGameDescription2);

        assertTrue(juliaSetGame.getDescription().equals(chaosGameDescription2));
      } catch (Exception e) {
        fail("setDescription should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("setDescription notifies observers")
    void testSetDescriptionNotifiesObservers() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription1 = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);
        ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(new Vector2D(1, 1),
            new Vector2D(2, 2), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription1, 100, 100, 100, 2.0);
        TestListener observer = new TestListener();
        juliaSetGame.addObserver(observer);
        juliaSetGame.setDescription(chaosGameDescription2);

        assertTrue(observer.isNotifiedDescription());
      } catch (Exception e) {
        fail("setDescription should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("runSteps notifies observers")
    void testRunStepsNotifiesObservers() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
        TestListener observer = new TestListener();
        juliaSetGame.addObserver(observer);
        juliaSetGame.runSteps(100);

        assertTrue(observer.isNotifiedCanvas());
      } catch (Exception e) {
        fail("runSteps should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("changeZoom notifies observers")
    void testChangeZoomNotifiesObservers() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
        TestListener observer = new TestListener();
        juliaSetGame.addObserver(observer);
        juliaSetGame.changeZoom(1.0);

        assertTrue(observer.isNotifiedCanvas());
      } catch (Exception e) {
        fail("changeZoom should not throw an exception with valid parameters");
      }
    }

    @Test
    @DisplayName("moveCanvas notifies observers")
    void testMoveCanvasNotifiesObservers() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
        TestListener observer = new TestListener();
        juliaSetGame.addObserver(observer);
        juliaSetGame.moveCanvas(new Vector2D(1.0, 1.0));

        assertTrue(observer.isNotifiedDescription());
      } catch (Exception e) {
        fail("moveCanvas should not throw an exception with valid parameters");
      }
    }
    @Test
    @DisplayName("removeObserver removes observer")
    void testRemoveObserverRemovesObserver() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);
        TestListener observer = new TestListener();
        juliaSetGame.addObserver(observer);
        juliaSetGame.removeObserver(observer);
        juliaSetGame.moveCanvas(new Vector2D(1.0, 1.0));

        assertFalse(observer.isNotifiedDescription());
      } catch (Exception e) {
        fail("removeObserver should not throw an exception with valid parameters");
      }
    }

  }


  @Nested
  class NegativeTests {

    @Test
    @DisplayName("constructor throws exception with null ChaosGameDescription")
    void testConstructorThrowsExceptionWithNullChaosGameDescription() {
      try {
        JuliaSetGame juliaSetGame = new JuliaSetGame(null, 100, 100, 100, 2.0);
        fail("Constructor should throw an exception with null ChaosGameDescription");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Description cannot be null");
      }
    }

    @Test
    @DisplayName("constructor throws exception with negative width")
    void testConstructorThrowsExceptionWithNegativeWidth() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, -100, 100, 100, 2.0);
        fail("Julia set constructor should throw an exception with negative width");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("constructor throws exception with negative height")
    void testConstructorThrowsExceptionWithNegativeHeight() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, -100, 100, 2.0);
        fail("Julia set constructor should throw an exception with negative height");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("constructor throws exception with negative maxIterations")
    void testConstructorThrowsExceptionWithNegativeMaxIterations() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);
        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, -100, 2.0);
        fail("Julia set constructor should throw an exception with negative maxIterations");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Max iterations must be positive");
      }
    }

    @Test
    @DisplayName("constructor throws exception with negative escapeRadius")
    void testConstructorThrowsExceptionWithNegativeEscapeRadius() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);
        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, -2.0);
        fail("Julia set constructor should throw an exception with negative escapeRadius");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Escape radius must be positive");
      }
    }

    @Test
    @DisplayName("constructor throws exception with affine description")
    void testConstructorThrowsExceptionWithAffineDescription() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(1, 2, 3, 4), new Vector2D(5, 6)));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);
        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, -2.0);
        fail("Julia set constructor should throw an exception with negative escapeRadius");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Description must be of type Julia");
      }
    }

    @Test
    @DisplayName("setDescription throws exception with null description")
    void testSetDescriptionThrowsExceptionWithNullDescription() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);
        JuliaSetGame juliaSetGame = new JuliaSetGame(chaosGameDescription, 100, 100, 100, 2.0);

        juliaSetGame.setDescription(null);
        fail("setDescription should throw an exception with null description");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Description cannot be null");
      }
    }

    @Test
    @DisplayName("setDescription throws exception with affine description")
    void testSetDescriptionThrowsExceptionWithAffineDescription() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(1, 2, 3, 4), new Vector2D(5, 6)));
        ChaosGameDescription affineDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms);

        List<Transform2D> transforms2 = new ArrayList<>();
        transforms2.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription juliaDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms2);

        JuliaSetGame juliaSetGame = new JuliaSetGame(juliaDescription, 100, 100, 100, 2.0);

        juliaSetGame.setDescription(affineDescription);
        fail("setDescription should throw an exception with affine description");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Description must be of type Julia");
      }
    }

    @Test
    @DisplayName("setCanvasSize throws exception with negative width")
    void testSetCanvasSizeThrowsExceptionWithNegativeWidth() {
      try {
        List<Transform2D> transforms2 = new ArrayList<>();
        transforms2.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription juliaDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms2);

        JuliaSetGame juliaSetGame = new JuliaSetGame(juliaDescription, 100, 100, 100, 2.0);

        juliaSetGame.setCanvasSize(-100, 100);
        fail("setCanvasSize should throw an exception with negative width");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("setCanvasSize throws exception with negative height")
    void testSetCanvasSizeThrowsExceptionWithNegativeHeight() {
      try {
        List<Transform2D> transforms2 = new ArrayList<>();
        transforms2.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription juliaDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms2);

        JuliaSetGame juliaSetGame = new JuliaSetGame(juliaDescription, 100, 100, 100, 2.0);
        juliaSetGame.setCanvasSize(100, -100);
        fail("setCanvasSize should throw an exception with negative height");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Width and height must be positive");
      }
    }

    @Test
    @DisplayName("runSteps throws exception with negative maxIterations")
    void testRunStepsThrowsExceptionWithNegativeMaxIterations() {
      try {
        List<Transform2D> transforms2 = new ArrayList<>();
        transforms2.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription juliaDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms2);

        JuliaSetGame juliaSetGame = new JuliaSetGame(juliaDescription, 100, 100, 100, 2.0);

        juliaSetGame.runSteps(-100);
        fail("runSteps should throw an exception with negative maxIterations");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Max iterations must be positive");
      }
    }

    @Test
    @DisplayName("setEscapeRadius throws exception with negative escape radius")
    void testRunStepsThrowsExceptionWithNegativeRadius() {
      try {
        List<Transform2D> transforms2 = new ArrayList<>();
        transforms2.add(new JuliaTransform(new Complex(.7, .1), 1));
        ChaosGameDescription juliaDescription = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(1, 1), transforms2);

        JuliaSetGame juliaSetGame = new JuliaSetGame(juliaDescription, 100, 100, 100, 2.0);
        juliaSetGame.setEscapeRadius(-1);
        fail("setEscapeRadius should throw an exception with negative steps");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Escape radius must be positive");
      }
    }

  }
}