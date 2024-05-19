package org.example.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.chaosGame.ChaosGameFileHandler;
import org.example.model.exceptions.MissingDataException;
import org.example.model.math.Complex;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.example.model.transform.Transform2D;
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

      List<Transform2D> transformsJulia = new ArrayList<>();
      transformsJulia.add(new JuliaTransform(new Complex(1, 3), 1));
      ChaosGameDescription descriptionJulia = new ChaosGameDescription(new Vector2D(0, 0),
          new Vector2D(100, 100), transformsJulia);

      ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
      chaosGameFileHandler.writeToFile(descriptionJulia,
          "src/test/resources/testfiles/testFileJulia.txt");
      chaosGameFileHandler.writeToFile(description, "src/test/resources/testfiles/testFile.txt");
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
        chaosGameFileHandler.writeToFile(description, "src/test/resources/testfiles/testFile.txt");
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("ReadFromFile throws no exceptions with valid input")
    void testReadFromFileThrowsNoExceptions() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFile.txt");
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
            "src/test/resources/testfiles/testFile.txt");
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
            "src/test/resources/testfiles/testFile");
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
            "src/test/resources/testfiles/testFile");
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
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFile");
        assertEquals(description.getMinCoords().toString(), "0.0, 0.0");
        assertEquals(description.getMaxCoords().toString(), "100.0, 100.0");
        assertEquals(description.getTransforms().get(0).toString(),
            "0.5, 0.0, 0.0, 0.5, 50.0, 0.0");

      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile skips empty lines")
    void testReadFileSkipsEmptyLines() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileEmptyLines.txt");
        assertNotNull(description);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile makes a ChaosGameDescription object with julia transform")
    void testReadFileMakesChaosGameDescriptionObjectWithJulia() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileJulia");
        assertNotNull(description);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFile makes a ChaosGameDescription object with correct values with julia transform")
    void testReadFileMakesChaosGameDescriptionObjectWithCorrectValuesWithJulia() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileJulia.txt");
        assertEquals(description.getMinCoords().toString(), "0.0, 0.0");
        assertEquals(description.getMaxCoords().toString(), "100.0, 100.0");
        assertEquals(description.getTransforms().get(0).toString(),
            "1.0, 3.0");
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFromFile, with no comments text file, return object that is not null")
    void testReadFromFileWithNoCommentsTextFileReturnsObjectNotNull() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileNoComments.txt");
        assertNotNull(description);
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFromFile, with no comments text file, reads correctly")
    void testReadFromFileWithNoCommentsTextFileReadsCorrectly() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileNoComments.txt");
        assertEquals("0.0, 0.0", description.getMinCoords().toString());
        assertEquals("100.0, 100.0", description.getMaxCoords().toString());
        assertEquals("0.5, 0.0, 0.0, 0.5, 50.0, 0.0",
            description.getTransforms().get(0).toString());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }
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
            "src/test/resources/testfiles/nonExistantFile.txt");
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
        chaosGameFileHandler.writeToFile(null, "src/test/resources/testfiles/testFile.txt");
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals("Description cannot be null", e.getMessage());
      }
    }

    @Test
    @DisplayName("readFromFile throws exception with invalid transform type")
    void testReadFromFileThrowsExceptionWithInvalidTransformType() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileNoType.txt");
        fail("An exception was not thrown");
      } catch (MissingDataException e) {
        assertEquals(
            "Invalid transform type",
            e.getMessage());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFromFile throws exception with invalid values")
    void testReadFromFileThrowsExceptionWithInvalidValues() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileInvalidValues.txt");
        fail("An exception was not thrown");
      } catch (Exception e) {
        assertEquals("Non-double value found in the file", e.getMessage());
      }
    }

    @Test
    @DisplayName("readFromFile throws MissingDataException with no transform values")
    void testReadFromFileThrowsMissingDataExceptionWithNoTransformValues() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileNoTransforms.txt");
        fail("An exception was not thrown");
      } catch (MissingDataException e) {
        assertEquals(
            "Description file is missing transform data, and cannot be used to create a ChaosGameDescription.",
            e.getMessage());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }

    @Test
    @DisplayName("readFromFile throws MissingDataException with no coords")
    void testReadFromFileThrowsMissingDataExceptionWithNoCoords() {
      try {
        ChaosGameFileHandler chaosGameFileHandler = new ChaosGameFileHandler();
        ChaosGameDescription description = chaosGameFileHandler.readFromFile(
            "src/test/resources/testfiles/testFileNoCoords.txt");
        fail("An exception was not thrown");
      } catch (MissingDataException e) {
        assertEquals(
            "Description file is missing coordinate data, and cannot be used to create a ChaosGameDescription.",
            e.getMessage());
      } catch (Exception e) {
        fail("An exception was thrown with the message: " + e.getMessage());
      }
    }


  }
}