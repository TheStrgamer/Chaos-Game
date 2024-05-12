package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import javafx.scene.image.Image;
import org.example.model.chaosGame.ChaosCanvas;
import org.example.model.factory.ImageFactory;
import org.example.model.math.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ImageFactoryTest {

  public static ChaosCanvas canvas;
  public static ImageFactory factory;

  @BeforeAll
  static void init() {
    factory = new ImageFactory();
    canvas = new ChaosCanvas(100, 100, new Vector2D(0,0), new Vector2D(1,1));

  }

  @Nested
  class PositiveTests {

    @Test
    @DisplayName("createImage throws no exception with valid parameters")
    void testCreateImageThrowsNoErrorsWithValidParameters() {
      try {
        factory.createImage(canvas);
      } catch (Exception e) {
        assert(false);
      }
    }

    @Test
    @DisplayName("createImage returns a valid image with valid parameters")
    void testCreateImageReturnsValidImageWithValidParameters() {
      try {
        Image image = factory.createImage(canvas);
        assertNotNull(image);
      } catch (Exception e) {
        assert(false);
      }
    }

    @Test
    @DisplayName("createImage returns an image with the correct width and height")
    void testCreateImageReturnsImageWithCorrectWidthAndHeight() {
      Image image = factory.createImage(canvas);
      assertEquals(image.getWidth(), 100);
      assertEquals(image.getHeight(), 100);
    }



  }

  @Nested
  class NegativeTests {

      @Test
      @DisplayName("createImage throws an exception with a null canvas")
      void testCreateImageThrowsExceptionWithNullCanvas() {
        try {
          factory.createImage(null);
          fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
          assertEquals(e.getMessage(), "Canvas cannot be null");
        }
      }

  }

}
