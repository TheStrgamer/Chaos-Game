package org.example.model;

import java.util.Random;

/**
 * <h1>ChaosGame</h1>
 * A class that represents a chaos game. The game is generated based on a description and a canvas.
 */
public class ChaosGame {

  private final ChaosCanvas canvas;
  private final ChaosGameDescription description;

  private final Vector2D currentPoint;

  private final Random random;

  /**
   * Constructs a new ChaosGame object with the given description and creates a canvas based on the values.
   *
   * @param description is the description to use.
   * @param width       is the width of the canvas.
   * @param height      is the height of the canvas.
   */
  public ChaosGame(ChaosGameDescription description, int width, int height) {
    this.canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
    this.description = description;
    this.currentPoint = new Vector2D(0, 0);
    this.random = new Random();
  }

  /**
   * Returns the canvas of this chaos game.
   *
   * @return the canvas of this chaos game.
   */
  public ChaosCanvas getCanvas() {
    return canvas;
  }

  /**
   * Runs the chaos game for the given number of steps.
   *
   * @param steps is the number of steps to run.
   */
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
