package org.example.model;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;


public class ImageFactory {
  public Image createImage(ChaosCanvas canvas) {
      try {

      WritableImage image = new WritableImage(canvas.getWidth(), canvas.getHeight());
      PixelWriter writer = image.getPixelWriter();
      for (int i = 0; i < canvas.getWidth(); i++) {
          for (int j = 0; j < canvas.getHeight(); j++) {
            if (canvas.getPixelFromCanvas(new Vector2D(i,j)) == 0) {
                  writer.setArgb(i, j, 0xFFFFFFFF);
              } else {
                  writer.setArgb(i, j, 0xFF000000);
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
