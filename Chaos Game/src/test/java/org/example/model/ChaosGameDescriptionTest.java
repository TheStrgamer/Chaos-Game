package org.example.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ChaosGameDescriptionTest {

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("getMinCoords returns the correct minCoords values.")
    void getMinCoordsReturnsCorrectValue() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(2, 4), -1));
        Vector2D minCoordsTest = new Vector2D(1, 2);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals(minCoordsTest.getX0(), chaosGameDescription.getMinCoords().getX0());
        assertEquals(minCoordsTest.getX1(), chaosGameDescription.getMinCoords().getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getMaxCoords returns the correct maxCoords values.")
    void getMaxCoordsReturnsCorrectValue() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(2, 4), -1));
        Vector2D maxCoordsTest = new Vector2D(3, 4);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals(maxCoordsTest.getX0(), chaosGameDescription.getMaxCoords().getX0());
        assertEquals(maxCoordsTest.getX1(), chaosGameDescription.getMaxCoords().getX1());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransforms returns the first object in transforms list at index one.")
    void getTransformsReturnsCorrectObjectAtIndexOne() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(2, 4), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4), transforms);
        assertEquals(transforms.get(1), chaosGameDescription.getTransforms().get(1));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransforms returns the transforms list.")
    void getTransformsReturnsList() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(2, 4), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals(transforms, chaosGameDescription.getTransforms());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransforms returns the transforms list with combination of different object types.")
    void getTransformsReturnsCorrectValueCombination() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new AffineTransform2D(new Matrix2x2(1, 2, 3, 4), new Vector2D(1, 2)));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals(transforms, chaosGameDescription.getTransforms());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    //Todo legg til tester for tostring og getTransformType, på både affine og julia transform

  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {


    @Test
    @DisplayName("Constructor throws IllegalArgumentException when given vector 1 is null")
    void constructorThrowsExceptionOnNullVector1() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(2, 4), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(null,
            new Vector2D(1, 2),
            transforms);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when given vector 2 is null")
    void constructorThrowsExceptionOnNullVector2() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(2, 4), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            null,
            transforms);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Vector cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when given list is empty")
    void constructorThrowsExceptionOnEmptyList() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "List cannot be empty");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when given list contains a null object")
    void constructorThrowsExceptionOnNullInList() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(null);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "Objects in Transform2D list cannot be null");
      }
    }

    @Test
    @DisplayName("Constructor throws IllegalArgumentException when given list is null")
    void constructorThrowsExceptionOnListNull() {
      try {
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            null);
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals(e.getMessage(), "List cannot be null");
      }
    }
  }
}
