package org.example.model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.example.model.exceptions.DescriptionFileEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
public class DescriptionFileEmptyExceptionTest {

  @Test
  @DisplayName("DescriptionFileEmptyException throws exception with correct message")
  public void testDescriptionFileEmptyException() {
    try {
      throw new DescriptionFileEmptyException();
    } catch (DescriptionFileEmptyException e) {
      assertEquals(e.getMessage(),"Description file is empty, and cannot be used to create a ChaosGameDescription.");
    } catch (Exception e) {
      fail("DescriptionFileEmptyException should be thrown");
    }
  }

  @Test
  @DisplayName("DescriptionFileEmptyException throws exception with given message")
  public void testDescriptionFileEmptyExceptionWithMessage() {
    try {
      throw new DescriptionFileEmptyException("Test message");
    } catch (DescriptionFileEmptyException e) {
      assertEquals(e.getMessage(),"Test message");
    } catch (Exception e) {
      fail("DescriptionFileEmptyException should be thrown");
    }
  }



}
