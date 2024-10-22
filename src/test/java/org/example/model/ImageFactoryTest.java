package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.example.model.chaosgame.ChaosCanvas;
import org.example.model.factory.ImageFactory;
import org.example.model.math.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ImageFactoryTest {

  public static ChaosCanvas canvas;
  public static ImageFactory factory;

  @BeforeAll
  static void init() {
    factory = new ImageFactory();
    canvas = new ChaosCanvas(10, 10, new Vector2D(0,0), new Vector2D(10,10));
  }
  @BeforeEach
  void setUp() {
    canvas.clear();
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
      assertEquals(image.getWidth(), 10);
      assertEquals(image.getHeight(), 10);
    }

    @Test
    @DisplayName("createImage sets the correct color for a pixel")
    void testCreateImageSetsCorrectColorForPixel() {
      canvas.setPixelWithCoords(new Vector2D(0, 10));
      Image image = factory.createImage(canvas);
      assertEquals(0xFF000000,image.getPixelReader().getArgb(0, 0) );
      assertEquals(0x00000000,image.getPixelReader().getArgb(1, 1));

    }

    @Test
    @DisplayName("createImage sets the correct color for a pixel with a specified color red")
    void testCreateImageSetsCorrectColorForPixelWithSpecifiedColorRed() {
      canvas.setPixelWithPointOnCanvas(new Vector2D(0, 0),255);
      Color color = Color.RED;
      Image image = factory.createImage(canvas,color);
      assertEquals(0xFFFF0000,image.getPixelReader().getArgb(0, 0) );
      assertEquals(0x00000000,image.getPixelReader().getArgb(1, 1));
    }
    @Test
    @DisplayName("createImage sets the correct color for a pixel with a specified color blue")
    void testCreateImageSetsCorrectColorForPixelWithSpecifiedColorBlue() {
      canvas.setPixelWithPointOnCanvas(new Vector2D(0, 0),255);
      Color color = Color.BLUE;
      Image image = factory.createImage(canvas,color);
      assertEquals(0xFF0000FF,image.getPixelReader().getArgb(0, 0) );
      assertEquals(0x00000000,image.getPixelReader().getArgb(1, 1));
    }

    @Test
    @DisplayName("createImage sets the correct color for a pixel with a specified color green")
    void testCreateImageSetsCorrectColorForPixelWithSpecifiedColorGreen() {
      canvas.setPixelWithPointOnCanvas(new Vector2D(0, 0),255);
      Color color = new Color(0,1,0,1);
      Image image = factory.createImage(canvas,color);
      assertEquals(0xFF00FF00,image.getPixelReader().getArgb(0, 0) );
      assertEquals(0x00000000,image.getPixelReader().getArgb(1, 1));
    }

    @Test
    @DisplayName("createImage starts darkening the color after 255")
    void testCreateImageDarkensColorAfter255() {
      canvas.setPixelWithPointOnCanvas(new Vector2D(0, 0),355);

      Color color = Color.RED;
      Image image = factory.createImage(canvas,color);
      assertTrue(image.getPixelReader().getArgb(0, 0) < 0xFFFF0000);
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
