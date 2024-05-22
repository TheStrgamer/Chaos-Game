package org.example.model;

import java.util.List;
import org.example.model.chaosgame.ChaosGame;
import org.example.model.chaosgame.ChaosGameDescription;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.Transform2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChaosGameObserverTest {

private ChaosGameDescription chaosGameDescription;
  private ChaosGame chaosGame;
  private TestListener testListener;

  @BeforeEach
  public void init() {
    List<Transform2D> transforms = List.of(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0)));
    chaosGameDescription = new ChaosGameDescription(new Vector2D(0, 0), new Vector2D(1, 1), transforms);

    chaosGame = new ChaosGame(chaosGameDescription,100,100);
    testListener = new TestListener();
    chaosGame.addObserver(testListener);
  }

  @Test
  @DisplayName("Listener is notified when changing description")
  public void testUpdateDescription() {
    chaosGame.setDescription(chaosGameDescription);
    assert(testListener.isNotifiedDescription());
  }

  @Test
  @DisplayName("Listener is notified when changing canvas")
  public void testUpdateCanvas() {
    chaosGame.setCanvasSize(100, 100);
    assert(testListener.isNotifiedCanvas());
  }

  @Test
  @DisplayName("Listener is not notified when removed from observer list")
  public void testNotUpdateDescription() {
    chaosGame.removeObserver(testListener);
    chaosGame.setDescription(chaosGameDescription);
    assert(!testListener.isNotifiedDescription());
  }





}
