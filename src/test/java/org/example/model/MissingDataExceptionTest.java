package org.example.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.example.model.exceptions.MissingDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MissingDataExceptionTest {

  @Test
  @DisplayName("MissingDataException throws exception with correct message")
  public void testMissingDataException() {
    try {
      throw new MissingDataException();
    } catch (MissingDataException e) {
      assertEquals(e.getMessage(),"Description file is missing necessary data, and cannot be used to create a ChaosGameDescription.");
    } catch (Exception e) {
      fail("MissingDataException should be thrown");
    }
  }

  @Test
  @DisplayName("MissingDataException throws exception with given message")
  public void testMissingDataExceptionWithMessage() {
    try {
      throw new MissingDataException("Test message");
    } catch (MissingDataException e) {
      assertEquals(e.getMessage(),"Test message");
    } catch (Exception e) {
      fail("MissingDataException should be thrown");
    }
  }



}
