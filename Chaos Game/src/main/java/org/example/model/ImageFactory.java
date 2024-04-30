package org.example.model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;


/**
 * <h1>ImageFactory</h1>
 * The ImageFactory class creates an image from a ChaosCanvas object.
 */
public class ImageFactory {

  /**
   * Creates an image from a ChaosCanvas object.
   *
   * @param canvas is the canvas to create the image from.
   * @return the image created from the canvas.
   */
  public Image createImage(ChaosCanvas canvas) {
      try {

      WritableImage image = new WritableImage(canvas.getWidth(), canvas.getHeight());
      PixelWriter writer = image.getPixelWriter();
      for (int i = 0; i < canvas.getWidth(); i++) {
          for (int j = 0; j < canvas.getHeight(); j++) {
            if (canvas.getPixelFromCanvas(new Vector2D(i,j)) == 0) {
                  writer.setArgb(i, j, 0x00FFFFFF);
              } else {
                int argb = canvas.getPixelFromCanvas(new Vector2D(i,j));
                writer.setArgb(i, j,(argb << 24));
            }
          }
      }
      return image;
} catch (Exception e) {
      System.out.println("Error: "+ e.getMessage());
      return null;
      }

  }

}
