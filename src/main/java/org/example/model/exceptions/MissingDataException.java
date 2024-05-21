package org.example.model.exceptions;

/**
 * <h2>MissingDataException</h2>
 * <p>
 * Exception thrown when the description file is missing data. Used by ChaosGameFileHandler to
 * indicate that the description file does not have enough data to make a description.
 * </p>
 *
 * @version 0.4.0
 * @since 0.4.0
 */
public class MissingDataException extends RuntimeException {

  /**
   * Constructor for the MissingDataDescription class. Sets the message to "Description file is
   * missing necessary data, and cannot be used to create a ChaosGameDescription"
   */
  public MissingDataException() {
    super(
        "Description file is missing necessary data, and cannot be used to create a ChaosGameDescription.");
  }

  /**
   * Constructor for the MissingDataDescription class. Uses the message provided as the message for
   * the exception.
   *
   * @param message the message to display when the exception is thrown.
   */
  public MissingDataException(String message) {
    super(message);
  }

}
