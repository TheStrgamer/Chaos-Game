package org.example.view;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * <h1>IntegerField</h1>
 * Custom TextField that only allows the input of valid Integers.
 */
public class WeightAndIterationsField extends TextField {

  /**
   * Constructor for the IntegerField class.
   *
   * @param text the text to display in the field.
   */
  public WeightAndIterationsField(String text) {
    super(text);
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
