package org.example.model.exceptions;

/**
 * <h1>DescriptionFileEmptyException</h1>
 * Exception thrown when the description file is empty. Used by ChaosGameFileHandler to indicate
 * that the description file is empty.
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
