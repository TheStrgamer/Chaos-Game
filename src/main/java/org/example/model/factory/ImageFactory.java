package org.example.model.factory;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.example.model.chaosgame.ChaosCanvas;
import org.example.model.math.Vector2D;


/**
 * <h2>ImageFactory.</h2>
 * <p>
 * The ImageFactory class creates an image from a ChaosCanvas object. The class is used to create
 * images that are displayed on the interface.</p>
 * <p>The color of each pixel is calculated based on the value of the canvas, and the gradient made
 * with the color chosen by the user</p>
 *
 * @version 0.4.0
 * @since 0.3.0
 */
public class ImageFactory {

  /**
   * Constructor for ImageFactory.
   */
  public ImageFactory() {
  }


  /**
   * Verifies that the canvas is not null.
   *
   * @param canvas is the canvas to verify.
   * @throws IllegalArgumentException if the canvas is null.
   */
  private void verifyCanvasNotNull(ChaosCanvas canvas) {
    if (canvas == null) {
      throw new IllegalArgumentException("Canvas cannot be null");
    }
  }

  /**
   * Creates an image from a ChaosCanvas object.
   *
   * @param canvas is the canvas to create the image from.
   * @return the image created from the canvas.
   * @throws IllegalArgumentException if the canvas is null.
   */
  public Image createImage(ChaosCanvas canvas) {
    return createImageInternal(canvas, Color.BLACK);
  }

  /**
   * Creates an image from a ChaosCanvas object with a specified color.
   *
   * @param canvas is the canvas to create the image from.
   * @param color  is the color to use for the image.
   * @return the image created from the canvas.
   * @throws IllegalArgumentException if the canvas is null.
   */
  public Image createImage(ChaosCanvas canvas, Color color) {
    return createImageInternal(canvas, color);
  }

  /**
   * Creates an image from a ChaosCanvas object with a specified color.
   *
   * @param canvas is the canvas to create the image from.
   * @param color  is the color to use for the image.
   * @return the image created from the canvas.
   * @throws IllegalArgumentException if the canvas is null.
   */
  private Image createImageInternal(ChaosCanvas canvas, Color color) {
    try {
      verifyCanvasNotNull(canvas);
      WritableImage image = new WritableImage(canvas.getWidth(), canvas.getHeight());
      PixelWriter writer = image.getPixelWriter();
      for (int i = 0; i < canvas.getWidth(); i++) {
        for (int j = 0; j < canvas.getHeight(); j++) {
          int pixelValue = canvas.getPixelFromCanvas(new Vector2D(i, j));
          Color interpolatedColor = getColorFromValue(pixelValue, color);
          writer.setColor(i, j, interpolatedColor);
        }
      }
      return image;
    } catch (Exception e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Gets the color from a value.
   *
   * @param value is the value to get the color from.
   * @param color is the color to interpolate.
   * @return the color from the value.
   */
  private Color getColorFromValue(int value, Color color) {
    if (value == 0) {
      return Color.TRANSPARENT;
    } else {
      if (value <= 255) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), value / 255.0);
      } else {
        double alpha = 1.0 / (1.0 + Math.pow((value - 255) / 100.0, 2));
        return new Color(color.getRed() * alpha, color.getGreen() * alpha, color.getBlue() * alpha,
            1.0);
      }
    }
  }

}
