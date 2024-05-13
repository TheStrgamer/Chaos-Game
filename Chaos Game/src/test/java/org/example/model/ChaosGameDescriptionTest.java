package org.example.model;

import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.math.Complex;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.example.model.transform.Transform2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

    @Test
    @DisplayName("toString returns the correct string with julia transform")
    void testToStringReturnsString() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(2, 4), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        String expectedString = "Julia    #Transform type\n" +
            "1.0, 2.0    #minimum coordinates\n" +
            "3.0, 4.0    #maximum coordinates\n" +
            "1.0, 3.0    #transform\n" +
            "2.0, 4.0    #transform\n";
        assertEquals(expectedString, chaosGameDescription.toString());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("toString returns the correct string with affine transform")
    void testToStringReturnsStringWithAffine() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(1, 2, 3, 4), new Vector2D(1, 2)));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        String expectedString = "Affine    #Transform type\n" +
            "1.0, 2.0    #minimum coordinates\n" +
            "3.0, 4.0    #maximum coordinates\n" +
            "1.0, 2.0, 3.0, 4.0, 1.0, 2.0    #transform\n";
        assertEquals(expectedString, chaosGameDescription.toString());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransformType returns the correct transform (affine) type")
    void testGetTransformTypeReturnsCorrectAffineType() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(1, 2, 3, 4), new Vector2D(1, 2)));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals("Affine", chaosGameDescription.getTransformType());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransformType returns the correct transform (julia) type")
    void testGetTransformTypeReturnsCorrectJuliaType() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals("Julia", chaosGameDescription.getTransformType());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransformsAsStringList returns the correct string with julia transform")
    void testGetTransformsAsStringListReturnsString() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("1.0, 3.0");
        assertEquals(expectedList, chaosGameDescription.getTransformsAsStringList());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns true when objects are equal")
    void testEqualsReturnsTrueWithEqualObjects() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        ChaosGameDescription chaosGameDescription1 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertTrue(chaosGameDescription1.equals(chaosGameDescription2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when minVectors are not equal")
    void testEqualsReturnsFalseWithDifferentMinVectors() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        ChaosGameDescription chaosGameDescription1 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(new Vector2D(2, 2),
            new Vector2D(3, 4),
            transforms);
        assertFalse(chaosGameDescription1.equals(chaosGameDescription2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when maxVectors are not equal")
    void testEqualsReturnsFalseWithDifferentMaxVectors() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        ChaosGameDescription chaosGameDescription1 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(4, 4),
            transforms);
        assertFalse(chaosGameDescription1.equals(chaosGameDescription2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when transforms are not equal")
    void testEqualsReturnsFalseWithDifferentTransforms() {
      try {
        List<Transform2D> transforms1 = new ArrayList<>();
        transforms1.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms1.add(new JuliaTransform(new Complex(1, 3), -1));
        List<Transform2D> transforms2 = new ArrayList<>();
        transforms2.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms2.add(new JuliaTransform(new Complex(1, 3), 1));
        ChaosGameDescription chaosGameDescription1 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms1);
        ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms2);
        assertFalse(chaosGameDescription1.equals(chaosGameDescription2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when ammount of transforms are not equal")
    void testEqualsReturnsFalseWithDifferentAmountOfTransforms() {
      try {
        List<Transform2D> transforms1 = new ArrayList<>();
        transforms1.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms1.add(new JuliaTransform(new Complex(1, 3), -1));
        List<Transform2D> transforms2 = new ArrayList<>();
        transforms2.add(new JuliaTransform(new Complex(1, 3), 1));
        ChaosGameDescription chaosGameDescription1 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms1);
        ChaosGameDescription chaosGameDescription2 = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms2);
        assertFalse(chaosGameDescription1.equals(chaosGameDescription2));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("equals method returns false when comparing to null")
    void testEqualsReturnsFalseWithNull() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertFalse(chaosGameDescription.equals(null));
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransformsAsList returns the correct list with julia transform")
    void testGetTransformsAsListReturnsList() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals(transforms, chaosGameDescription.getTransformsAsList());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

    @Test
    @DisplayName("getTransformsAsStringList returns the correct string with affine transform")
    void testGetTransformsAsStringListReturnsStringWithAffine() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(1, 2, 3, 4), new Vector2D(1, 2)));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        List<String> expectedList = new ArrayList<>();
        expectedList.add("1.0, 2.0, 3.0, 4.0, 1.0, 2.0");
        assertEquals(expectedList, chaosGameDescription.getTransformsAsStringList());
      } catch (Exception e) {
        fail("An exception was thrown");
      }
    }

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
