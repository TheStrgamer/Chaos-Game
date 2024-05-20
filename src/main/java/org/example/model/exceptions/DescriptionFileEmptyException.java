package org.example.model.exceptions;

/**
 * <h2>DescriptionFileEmptyException</h2>
 * <p>
 * Exception thrown when the description file is empty. Used by ChaosGameFileHandler to indicate
 * that the description file is empty.
 * </p>
 * @version 0.4.0
 * @since 0.4.0
 */
public class DescriptionFileEmptyException extends RuntimeException {

  /**
   * Constructor for the DescriptionFileEmptyException class. Sets the message to "Description file
   * is empty, and cannot be used to create a ChaosGameDescription."
   */
  public DescriptionFileEmptyException() {
    super("Description file is empty, and cannot be used to create a ChaosGameDescription.");
  }

  /**
   * Constructor for the DescriptionFileEmptyException class. Uses the message provided as the
   * message for the exception.
   *
   * @param message the message to display when the exception is thrown.
   */
  public DescriptionFileEmptyException(String message) {
    super(message);
  }

}
