package org.example.view.components;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * <h2>WeightAndIterationsField.</h2>
 * <p>
 * Custom TextField that only allows the input of valid Integers.
 * </p>
 * <p>
 * If the input is invalid, the field will not accept it and consume the event. The field will also
 * display a tooltip to inform the user if the input is not updated.
 * </p>
 *
 * @version 1.0.0
 * @since 0.4.0
 */
public class WeightAndIterationsField extends TextField {

  /**
   * Constructor for the IntegerField class.
   *
   * @param text the text to display in the field.
   */
  public WeightAndIterationsField(String text) {
    super(text.trim());
    this.addEventFilter(KeyEvent.KEY_TYPED, event -> {
      if (isInvalidInput(event)) {
        event.consume();
      }
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
    return !(character.matches("[0-9]"));
  }

}
