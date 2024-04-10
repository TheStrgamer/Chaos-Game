package org.example.model;

import java.util.Random;

public class ChaosGame {

  private final ChaosCanvas canvas;
  private final ChaosGameDescription description;

  private final Vector2D currentPoint;

  private final Random random;

  public ChaosGame(ChaosGameDescription description, int width, int height) {
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
    this.description = description;
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
  }

  public ChaosCanvas getCanvas() {
    return canvas;
  }

  public void runSteps(int steps) {
    for (int i = 0; i < steps; i++) {
      try {
        Transform2D transform = description.getTransforms()
            .get(random.nextInt(description.getTransforms().size()));
        Vector2D tmp = transform.transform(currentPoint);
        currentPoint.setX0(tmp.getX0());
        currentPoint.setX1(tmp.getX1());

        canvas.setPixel(currentPoint);
      } catch (Exception e) {
        System.out.println(e.getMessage());
        System.out.println(currentPoint.toString()+"\n");
      }

    }
  }
}
