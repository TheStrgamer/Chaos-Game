package org.example.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * <h1>ErrorHandlingView</h1>
 * The view class for the error handling popup window layout.
 */
public class ErrorHandlingView implements PageViewInterface {

  private VBox layout;

  /**
   * Constructor for the ErrorHandlingView class.
   *
   * @param errorMessage the error message to display in the popup window.
   */
  public ErrorHandlingView(String errorMessage) {
    layout = createPopUpError(errorMessage);
  }

  /**
   * Creates the layout for the error handling popup window. The layout contains
   * an error message and an "OK" button to close the popup window.
   *
   * @param errorMessage the error message to display in the popup window.
   * @return the layout for the error handling popup window.
   */
  public VBox createPopUpError(String errorMessage) {
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
