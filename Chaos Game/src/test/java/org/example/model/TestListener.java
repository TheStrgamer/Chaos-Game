package org.example.model;

/**
 * <h1>TestObserver</h1>
 * A class that implements the ChaosGameObserver interface for testing purposes.
 * It is used to check if the observer is notified about changes in the description and canvas. 
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