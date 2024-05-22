package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.example.model.chaosgame.ChaosGameDescription;
import org.example.model.factory.ChaosGameDescriptionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ChaosGameDescriptionFactoryTest {

  private static ChaosGameDescriptionFactory factory;

  @BeforeAll
  static void init() {
    factory = new ChaosGameDescriptionFactory();
  }

  @Nested
  class PositiveTests {

    @Test
    @DisplayName("createDescription returns a description")
    void testCreateDescription() {
      ChaosGameDescription description = factory.createDescription("Sierpinski");
      assert (description != null);
    }

    @Test
    @DisplayName("createDescription returns the sierpinski description when given the correct string")
    void testCreateDescriptionSierpinski() {
      try {
        ChaosGameDescription description = factory.createDescription("Sierpinski");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Affine    #Transform type
            0.0, 0.0    #minimum coordinates
            1.0, 1.0    #maximum coordinates
            0.5, 0.0, 0.0, 0.5, 0.0, 0.0    #transform
            0.5, 0.0, 0.0, 0.5, 0.5, 0.0    #transform
            0.5, 0.0, 0.0, 0.5, 0.25, 0.5    #transform
            """);
      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription returns the barnsley fern description when given the correct string")
    void testCreateDescriptionBarnsleyFern() {
      try {
        ChaosGameDescription description = factory.createDescription("Barnsley");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Affine    #Transform type
            -2.65, 0.0    #minimum coordinates
            2.65, 10.0    #maximum coordinates
            0.0, 0.0, 0.0, 0.16, 0.0, 0.0    #transform
            0.85, 0.04, -0.04, 0.85, 0.0, 1.6    #transform
            0.2, -0.26, 0.23, 0.22, 0.0, 1.6    #transform
            -0.15, 0.28, 0.26, 0.24, 0.0, 0.44    #transform
            """);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription returns the julia description when given the correct string")
    void testCreateDescriptionJulia() {
      try {
        ChaosGameDescription description = factory.createDescription("Julia");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Julia    #Transform type
            -1.6, -1.2    #minimum coordinates
            1.6, 1.2    #maximum coordinates
            -0.74543, 0.11301    #transform
            -0.74543, 0.11301    #transform
            """);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription returns the second julia description when given the correct string")
    void testCreateDescriptionJulia2() {
      try {
        ChaosGameDescription description = factory.createDescription("Julia2");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Julia    #Transform type
            -1.6, -1.2    #minimum coordinates
            1.6, 1.2    #maximum coordinates
            0.15546, -0.6369    #transform
            0.15546, -0.6369    #transform
            """);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription returns the third julia description when given the correct string")
    void testCreateDescriptionJulia3() {
      try {
        ChaosGameDescription description = factory.createDescription("Julia3");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Julia    #Transform type
            -1.6, -1.2    #minimum coordinates
            1.6, 1.2    #maximum coordinates
            0.42428198918101634, 0.14423276134020324    #transform
            0.42428198918101634, 0.14423276134020324    #transform
            """);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription returns the diamond description when given the correct string")
    void testCreateDescriptionDiamond() {
      try {
        ChaosGameDescription description = factory.createDescription("Diamond");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Affine    #Transform type
            -0.25, -0.75    #minimum coordinates
            1.25, 0.5    #maximum coordinates
            -0.035359, -0.45328, 0.61167, -0.30323, 0.0765966, -0.740269    #transform
            0.87527, -0.9741, 0.9323, -0.2067, 0.067495, -0.54111    #transform
            """);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription returns the plant description when given the correct string")
    void testCreateDescriptionPlant() {
      try {
        ChaosGameDescription description = factory.createDescription("Plant");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Affine    #Transform type
            -6.0, -2.5    #minimum coordinates
            2.5, 6.0    #maximum coordinates
            0.7735, 0.2473, -0.3505, 0.868, -1.5435, -0.0778    #transform
            -0.1939, -0.6283, -0.5006, 0.0103, 0.4887, -1.0326    #transform
            """);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription returns the flower description when given the correct string")
    void testCreateDescriptionFlower() {
      try {
        ChaosGameDescription description = factory.createDescription("Flower");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Affine    #Transform type
            -2.0, -2.0    #minimum coordinates
            2.0, 2.0    #maximum coordinates
            -0.5639, -0.1856, -0.9115, 0.7954, 0.841, 0.1493    #transform
            -0.5211, -0.3527, -0.5343, 0.5359, 0.1484, 0.4851    #transform
            """);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription creates random julia description when given the correct string")
    void testCreateDescriptionRandomJulia() {
      try {
        ChaosGameDescription description = factory.createDescription("JuliaRandom");
        assertNotNull(description);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription creates random affine description when given the correct string")
    void testCreateDescriptionRandomAffine() {
      try {
        ChaosGameDescription description = factory.createDescription("AffineRandom");
        assertNotNull(description);

      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
    }

    @Test
    @DisplayName("createDescription creates snake description when given the correct string")
    void testCreateSnakeDescription() {
      try {
        ChaosGameDescription description = factory.createDescription("Snake");
        assertNotNull(description);
        assertEquals(description.toString(), """
            Affine    #Transform type
            -1.0, -6.0    #minimum coordinates
            3.0, 0.0    #maximum coordinates
            -0.99, -0.04, 0.29, 0.71, 1.35, -1.9    #transform
            -0.58, -0.51, 0.05, 0.5, -0.09, -0.07    #transform
            0.79, 0.53, -0.41, 0.2, 1.78, -1.93    #transform
            """);
      } catch (IllegalArgumentException e) {
        fail("Exception thrown");
      }
  }

  @Test
  @DisplayName("createDescription creates spine description when given the correct string")
  void testCreateSpineDescription() {
    try {
      ChaosGameDescription description = factory.createDescription("Spine");
      assertNotNull(description);
      assertEquals(description.toString(), """
          Affine    #Transform type
          -3.5, -7.9    #minimum coordinates
          8.5, 3.8    #maximum coordinates
          0.94, 0.25, -0.17, 0.73, 1.9, -0.8    #transform
          -0.13, 0.38, -0.66, -0.57, 0.37, 0.04    #transform
          """);
    } catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  @DisplayName("createDescription creates empty affine description when given the correct string")
  void testCreateEmptyAffineDescription() {
    try {
      ChaosGameDescription description = factory.createDescription("EmptyAffine");
      assertNotNull(description);
      assertEquals(description.toString(), """
          Affine    #Transform type
          -1.0, -1.0    #minimum coordinates
          1.0, 1.0    #maximum coordinates
          1.0, 0.0, 0.0, 1.0, 0.0, 0.0    #transform
          """);
    } catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }

  @Test
  @DisplayName("createDescription creates empty julia description when given the correct string")
  void testCreateEmptyJuliaDescription() {
    try {
      ChaosGameDescription description = factory.createDescription("EmptyJulia");
      assertNotNull(description);
      assertEquals(description.toString(), """
          Julia    #Transform type
          -1.6, -1.2    #minimum coordinates
          1.6, 1.2    #maximum coordinates
          0.0, 0.0    #transform
          0.0, 0.0    #transform
          """);
    } catch (IllegalArgumentException e) {
      fail("Exception thrown");
    }
  }
  }


  @Nested
  class NegativeTests {

    @Test
    @DisplayName("createDescription throws an exception when given an invalid string")
    void testCreateDescriptionInvalidString() {
      try {
        factory.createDescription("Invalid");
        fail("No exception thrown");
      } catch (IllegalArgumentException e) {
        assertEquals("Invalid description type", e.getMessage());
      }
    }


  }

}
