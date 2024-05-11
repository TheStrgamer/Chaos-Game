package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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
        assertEquals(description.toString(), "Affine    #Transform type\n"
            + "0.0, 0.0    #minimum coordinates\n"
            + "1.0, 1.0    #maximum coordinates\n"
            + "0.5, 0.0, 0.0, 0.5, 0.0, 0.0    #transform\n"
            + "0.5, 0.0, 0.0, 0.5, 0.5, 0.0    #transform\n"
            + "0.5, 0.0, 0.0, 0.5, 0.25, 0.5    #transform\n");
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
        assertEquals(description.toString(), "Affine    #Transform type\n"
            + "-2.65, 0.0    #minimum coordinates\n"
            + "2.65, 10.0    #maximum coordinates\n"
            + "0.0, 0.0, 0.0, 0.16, 0.0, 0.0    #transform\n"
            + "0.85, 0.04, -0.04, 0.85, 0.0, 1.6    #transform\n"
            + "0.2, -0.26, 0.23, 0.22, 0.0, 1.6    #transform\n"
            + "-0.15, 0.28, 0.26, 0.24, 0.0, 0.44    #transform\n");

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
        assertEquals(description.toString(), "Julia    #Transform type\n"
            + "-1.6, -1.2    #minimum coordinates\n"
            + "1.6, 1.2    #maximum coordinates\n"
            + "-0.74543, 0.11301    #transform\n"
            + "-0.74543, 0.11301    #transform\n");

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
        assertEquals(description.toString(), "Julia    #Transform type\n"
            + "-1.6, -1.2    #minimum coordinates\n"
            + "1.6, 1.2    #maximum coordinates\n"
            + "0.15546, -0.6369    #transform\n"
            + "0.15546, -0.6369    #transform\n");

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
        assertEquals(description.toString(), "Julia    #Transform type\n"
            + "-1.6, -1.2    #minimum coordinates\n"
            + "1.6, 1.2    #maximum coordinates\n"
            + "0.42428198918101634, 0.14423276134020324    #transform\n"
            + "0.42428198918101634, 0.14423276134020324    #transform\n");

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
        assertEquals(description.toString(), "Affine    #Transform type\n"
            + "-0.25, -0.75    #minimum coordinates\n"
            + "1.25, 0.5    #maximum coordinates\n"
            + "-0.03535949538401151, -0.4532831417408121, 0.6116778800475406, -0.3032324309903214, 0.07659661687866781, -0.7402694095766407    #transform\n"
            + "0.8752702951432678, -0.9741038761368701, 0.9323165704394973, -0.2067485177080317, 0.0674954419906022, -0.5411159639984362    #transform\n");

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
        assertEquals(description.toString(), "Affine    #Transform type\n"
            + "-6.0, -2.5    #minimum coordinates\n"
            + "2.5, 6.0    #maximum coordinates\n"
            + "0.7735, 0.2473, -0.3505, 0.868, -1.5435, -0.0778    #transform\n"
            + "-0.1939, -0.6283, -0.5006, 0.0103, 0.4887, -1.0326    #transform\n");

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
        assertEquals(description.toString(), "Affine    #Transform type\n"
            + "-2.0, -2.0    #minimum coordinates\n"
            + "2.0, 2.0    #maximum coordinates\n"
            + "-0.5639, -0.1856, -0.9115, 0.7954, 0.841, 0.1493    #transform\n"
            + "-0.5211, -0.3527, -0.5343, 0.5359, 0.1484, 0.4851    #transform\n");

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
