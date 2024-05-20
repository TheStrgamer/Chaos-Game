package org.example.model.observer;

import org.example.model.chaosGame.ChaosCanvas;
import org.example.model.chaosGame.ChaosGameDescription;

/**
 * <h2>ChaosGameObserver</h2>
 * <p>
 * The ChaosGameObserver interface is used to observe changes in the Chaos Game model.
 * </p>
 * <p>
 * The interface is used by the controller classes that need to observe changes in the Chaos Game
 * model.
 * </p>
 *
 * @version 0.4.0
 * @since 0.3.0
 */
public interface ChaosGameObserver {

  /**
   * Observes a change in the description of the Chaos Game.
   *
   * @param description the new description of the Chaos Game.
   */
  void updateDescription(ChaosGameDescription description);

  /**
   * Observes a change in the canvas of the Chaos Game.
   *
   * @param canvas the new canvas of the Chaos Game.
   */
  void updateCanvas(ChaosCanvas canvas);

}
