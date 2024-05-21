package org.example.view;

import javafx.scene.layout.VBox;

/**
 * <h2>PageViewInterface.</h2>
 * <p>
 * Interface for the view classes of the application. Ensures that all view classes have a getLayout
 * method.
 * </p>
 *
 * @version 0.4.0
 * @since 0.3.0
 */
public interface PageViewInterface {

  /**
   * Returns the layout of the view.
   *
   * @return the layout of the view.
   */
  VBox getLayout();

}
