package org.example.view.components;

import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * <h1>DoubleNumberField</h1>
 * Custom TextField that only allows the input of valid double
 * numbers.
 */
public class DoubleNumberField extends TextField {

  /**
   * Constructor for the DoubleNumberField class.
   *
   * @param text the text to display in the field.
   */
  public DoubleNumberField(String text) {
    super(text.trim());
    this.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
      if (isInvalidInput(event)) {
        event.consume();
      } if (event.getCode()== KeyCode.ENTER) {
        this.getStyleClass().remove("unsaved");
        this.setTooltip(null);
      }
    });
    this.textProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue.equals(oldValue)) {
        return;
      }
      this.getStyleClass().add("unsaved");
      String tooltipText = "Press Enter to save changes";
      if (this.getText().isEmpty()) {
        tooltipText = "Type a number and press Enter to save changes";
      }
      this.setTooltip(new Tooltip(tooltipText));
    });
  }

  /**
   * Checks if the input is invalid.
   *
   * @param event the key event to check.
   * @return true if the input is invalid, false otherwise.
   */
  private boolean isInvalidInput(KeyEvent event) {
    String character = event.getCharacter();
    String currentText = getText();
    boolean isOnlyDot = character.equals(".") && !currentText.contains(".");
    boolean isFirstCharacter = getCaretPosition() == 0;
    boolean isValidMinus = isFirstCharacter && character.equals("-") && !currentText.contains("-");
    return !(character.matches("[0-9]") || isValidMinus || isOnlyDot);
  }

}
