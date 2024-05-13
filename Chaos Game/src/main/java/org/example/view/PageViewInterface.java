package org.example.view;

import javafx.scene.layout.VBox;

/**
 * <h1>PageViewInterface</h1>
 * Interface for the view classes of the application.
 * Ensures that all view classes have a getLayout method.
 */
public interface PageViewInterface {

  /**
   * Method for getting the layout of the view.
   *
   * @return the layout of the view.
   */
  VBox getLayout();

}
