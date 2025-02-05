package org.example.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * <h2>ErrorHandlingView.</h2>
 * <p>
 * The view class for the error handling popup window layout.
 * </p>
 * <p>
 * The class lets the user set the message for the error window, and creates the layout for the
 * error popup.
 * </p>
 *
 * @version 1.0.0
 * @since 0.4.0
 */
public class ErrorHandlingView implements PageViewInterface {

  private final VBox layout;

  /**
   * Constructor for the ErrorHandlingView class.
   *
   * @param errorMessage the error message to display in the popup window.
   */
  public ErrorHandlingView(String errorMessage) {
    layout = createPopUpError(errorMessage);
  }

  /**
   * Creates the layout for the error handling popup window. The layout contains an error message
   * and an "OK" button to close the popup window.
   *
   * @param errorMessage the error message to display in the popup window.
   * @return the layout for the error handling popup window.
   */
  private VBox createPopUpError(String errorMessage) {
    VBox layout = new VBox();
    Button closeButton = new Button("OK");
    Label errorLabel = new Label(errorMessage);
    errorLabel.setPadding(new javafx.geometry.Insets(10, 30, 10, 30));
    closeButton.setOnAction(e -> layout.getScene().getWindow().hide());
    layout.getStyleClass().add("error-box");
    layout.getChildren().add(errorLabel);
    layout.getChildren().add(closeButton);
    return layout;
  }

  @Override
  public VBox getLayout() {
    return layout;
  }
}
