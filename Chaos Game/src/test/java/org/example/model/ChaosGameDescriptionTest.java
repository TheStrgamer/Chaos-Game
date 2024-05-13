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
    @DisplayName("Constructor creates object with correct values")
    void constructorCreatesObjectWithCorrectValues() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals(1, chaosGameDescription.getMinCoords().getX0());
        assertEquals(2, chaosGameDescription.getMinCoords().getX1());
        assertEquals(3, chaosGameDescription.getMaxCoords().getX0());
        assertEquals(4, chaosGameDescription.getMaxCoords().getX1());
        assertEquals(transforms, chaosGameDescription.getTransforms());
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Constructor creates object with correct weights")
    void constructorCreatesObjectWithCorrectWeights() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms);
        assertEquals(1, chaosGameDescription.getWeight(0));
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("Weighted constructor creates object with correct weights")
    void weightedConstructorCreatesObjectWithCorrectWeights() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        List<Integer> weights = new ArrayList<>();
        weights.add(5);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        assertEquals(5, chaosGameDescription.getWeight(0));
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getWeight returns the correct weight")
    void getWeightReturnsCorrectValue() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        List<Integer> weights = new ArrayList<>();
        weights.add(5);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        assertEquals(5, chaosGameDescription.getWeight(0));
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getWeights returns the list of weights")
    void getWeightsReturnsCorrectList() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        List<Integer> weights = new ArrayList<>();
        weights.add(5);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        assertEquals(weights, chaosGameDescription.getWeights());
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("setWeight sets the correct weight")
    void setWeightSetsCorrectValue() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));

        List<Integer> weights = new ArrayList<>();
        weights.add(5);
        weights.add(4);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        assertEquals(5, chaosGameDescription.getWeight(0));

        chaosGameDescription.setWeight(0, 10);
        assertEquals(10, chaosGameDescription.getWeight(0));
        assertEquals(4, chaosGameDescription.getWeight(1));
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("setWeights sets the correct weights")
    void setWeightsSetsCorrectList() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));

        List<Integer> weights = new ArrayList<>();
        weights.add(5);
        weights.add(4);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        List<Integer> newWeights = new ArrayList<>();
        newWeights.add(10);
        newWeights.add(20);
        chaosGameDescription.setWeights(newWeights);
        assertEquals(newWeights.get(0), chaosGameDescription.getWeights().get(0));
        assertEquals(newWeights.get(1), chaosGameDescription.getWeights().get(1));
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("sumOfWeights returns the correct sum of weights")
    void sumOfWeightsReturnsCorrectValue() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        transforms.add(new JuliaTransform(new Complex(1, 3), 1));
        List<Integer> weights = new ArrayList<>();
        weights.add(5);
        weights.add(4);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        assertEquals(9, chaosGameDescription.sumOfWeights());
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("sumOfWeightsBelowindex returns the correct sum of weights")
    void sumOfWeightsBelowIndexReturnsCorrectValue() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        transforms.add(new JuliaTransform(new Complex(1, 3), -1));
        transforms.add(new JuliaTransform(new Complex(1, 4), 1));

        List<Integer> weights = new ArrayList<>();
        weights.add(5);
        weights.add(4);
        weights.add(4);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        assertEquals(9, chaosGameDescription.sumOfWeightsBelowIndex(2));
        assertEquals(5, chaosGameDescription.sumOfWeightsBelowIndex(1));

      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }

    @Test
    @DisplayName("getTransformWithWeight returns the correct transform")
    void getTransformWithWeightReturnsCorrectValue() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        JuliaTransform juliaTransform1 = new JuliaTransform(new Complex(1, 3), 1);
        JuliaTransform juliaTransform2 = new JuliaTransform(new Complex(1, 3), -1);
        transforms.add(juliaTransform1);
        transforms.add(juliaTransform2);
        List<Integer> weights = new ArrayList<>();
        weights.add(1);
        weights.add(2);
        ChaosGameDescription chaosGameDescription = new ChaosGameDescription(new Vector2D(1, 2),
            new Vector2D(3, 4),
            transforms, weights);
        assertEquals(juliaTransform1, chaosGameDescription.getTransformWithWeight(0));
        assertEquals(juliaTransform2, chaosGameDescription.getTransformWithWeight(1));
        assertEquals(juliaTransform2, chaosGameDescription.getTransformWithWeight(2));

      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }


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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
      }
    }


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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        expectedList.add("1.0, 3.0, 1, 1");
        assertEquals(expectedList, chaosGameDescription.getTransformsAsStringList());
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        fail("An exception was thrown with the message " + e.getMessage());
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
        expectedList.add("1.0, 2.0, 3.0, 4.0, 1.0, 2.0, 1");
        assertEquals(expectedList, chaosGameDescription.getTransformsAsStringList());
      } catch (Exception e) {
        fail("An exception was thrown with the message " + e.getMessage());
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
