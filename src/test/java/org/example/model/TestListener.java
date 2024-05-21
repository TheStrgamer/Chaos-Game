package org.example.model;

import org.example.model.chaosgame.ChaosCanvas;
import org.example.model.chaosgame.ChaosGameDescription;
import org.example.model.observer.ChaosGameObserver;

/**
 * <h2>TestObserver</h2>
 * A class that implements the ChaosGameObserver interface for testing purposes.
 * It is used to check if the observer is notified about changes in the description and canvas.
 *
 * @version 0.3.0
 * @since 0.3.0
 */
public class TestListener implements ChaosGameObserver {

  private boolean notifiedDescription = false;
  private boolean notifiedCanvas = false;

  /**
   * returns true if the observer has been notified about a change in the description
   * @return true if the observer has been notified, false otherwise
   */
  public boolean isNotifiedDescription() {
    return notifiedDescription;
  }
  /**
   * returns true if the observer has been notified about a change in the canvas
   * @return true if the observer has been notified, false otherwise
   */
  public boolean isNotifiedCanvas() {
    return notifiedCanvas;
  }

  /**
   * Listens for changes in the description and updates the observer
   * @param description the new description 
   */
  @Override
  public void updateDescription(ChaosGameDescription description) {
    notifiedDescription = true;
  }

  /**
   * Listens for changes in the canvas and updates the observer
   * @param canvas the new canvas
   */
  @Override
  public void updateCanvas(ChaosCanvas canvas) {
    notifiedCanvas = true;
  }
}