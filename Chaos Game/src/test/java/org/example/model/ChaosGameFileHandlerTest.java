package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ChaosGameFileHandlerTest {

  @BeforeAll
  static void init() {
    try {
      List<Transform2D> transforms = new ArrayList<>();
      transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
      ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0, 0),
          new Vector2D(100, 100), transforms);

      ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
      chaosGameFileHandler.writeToFile(description, "test/testFile.txt");
    } catch (Exception e) {
      fail("An exception was thrown with the message: " + e.getMessage());
    }
  }

  @Nested
  @DisplayName("Positive tests")
  class PositiveTests {

    @Test
    @DisplayName("WriteToFile throws no exceptions with valid input")
    void testWriteToFileThrowsNoExceptions() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(100, 100), transforms);

        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        chaosGameFileHandler.writeToFile(description, "test/testFile.txt");
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("ReadFromFile throws no exceptions with valid input")
    void testReadFromFileThrowsNoExceptions() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile("test/testFile.txt");
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile reads correctly without filetype or path")
    void testReadFileReadsCorrectlyWithoutFiletypeOrPath() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile("test/testFile");
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile reads correctly with path and filetype")
    void testReadFileReadsCorrectlyWithPathAndType() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "chaosFiles/test/testFile.txt");
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile reads correctly with path and not filetype")
    void testReadFileReadsCorrectlyWithPathAndNotType() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "chaosFiles/test/testFile");
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile makes a ChaosGameDescription object with affine transform")
    void testReadFileMakesChaosGameDescriptionObjectWithAffine() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "chaosFiles/test/testFile");
        assertNotNull(description);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile makes a ChaosGameDescription object with correct values with affine transform")
    void testReadFileMakesChaosGameDescriptionObjectWithCorrectValuesWithAffine() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile("test/testFile");
        assertEquals(description.getMinCoords().toString(), "0.0, 0.0");
        assertEquals(description.getMaxCoords().toString(), "100.0, 100.0");
        assertEquals(description.getTransforms().get(0).toString(),
            "0.5, 0.0, 0.0, 0.5, 50.0, 0.0");

      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    //TODO legg til tester med julia transform også.
    //bruk filen testFileJulia.txt, som ligger i chaosFIles/test mappa
    //trenger du andre filer for andre tester, eller lager dem i julia testen, legg dem i test mappa
    //tester for fil uten kommentar, og for en fil som har tomme linjer midt i kan også være nyttig.
    //

  }

  @Nested
  @DisplayName("Negative tests")
  class NegativeTests {

    @Test
    @DisplayName("readFromFile throws exception with invalid input")
    void testReadFromFileThrowsExceptionWithInvalidInput() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "test/invalidFile.txt");
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals("File not found", e.getMessage());
      }
    }

    @Test
    @DisplayName("writeToFile throws exception with invalid input")
    void testWriteToFileThrowsExceptionWithInvalidInput() {
      try {
        List<Transform2D> transforms = new ArrayList<>();
        transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(50, 0)));
        ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0, 0),
            new Vector2D(100, 100), transforms);

        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        chaosGameFileHandler.writeToFile(description, "test/invalidFile.txt");
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals("File not found", e.getMessage());
      }
    }

    @Test
    @DisplayName("writeToFile throws exception with null description")
    void testWriteToFileThrowsExceptionWithNullDescription() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        chaosGameFileHandler.writeToFile(null, "test/testFile.txt");
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals("Description cannot be null", e.getMessage());
      }
    }

    //Todo lag tester for invalidTransformType txt filen og invalid values filen.


  }

}