package org.example.model.chaosgame;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.model.exceptions.DescriptionFileEmptyException;
import org.example.model.exceptions.MissingDataException;
import org.example.model.math.Complex;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.example.model.transform.Transform2D;

/**
 * <h2>ChaosGameFileHandler.</h2>
 * <p>
 * A class that handles reading and writing chaos game descriptions to and from files.
 * </p>
 * <p>
 * The file handler can read descriptions from txt files, and throw exceptions if the file is not
 * found or if the file is not formatted correctly.
 * </p>
 *
 * @version 1.0.0
 * @since 0.2.0
 */
public class ChaosGameFileHandler {

  /**
   * Constructor for the ChaosGameFileHandler class.
   */
  public ChaosGameFileHandler() {
  }

  /**
   * Verifies that the given transform type is valid.
   *
   * @param transformType is the transform type to use.
   * @throws IllegalArgumentException if the given transform type is invalid.
   */
  private void verifyValidTransformType(String transformType) {
    if (!transformType.contains("Affine") && !transformType.contains("Julia")) {
      throw new MissingDataException("Invalid transform type");
    }
  }

  /**
   * Verifies that the given string is a double and if true it returns parsed double.
   *
   * @param value is the string to verify.
   * @return the double value of the string.
   * @throws IllegalArgumentException if the given string is not a double.
   */
  private double verifyDouble(String value) {
    try {
      return Double.parseDouble(value);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Non-double value found in the file");
    }
  }

  /**
   * Verifies that a file exists.
   *
   * @param path is the path to the file.
   * @return true if the file exists, false otherwise.
   * @throws IllegalArgumentException if the file is not found.
   */
  private boolean verifyFileExists(String path) {
    File file = new File(path);
    return file.exists();
  }

  /**
   * Makes a valid path to a file.
   *
   * @param path is the path to the file.
   * @return a valid path to the file.
   * @throws IllegalArgumentException if the file is not found.
   */
  private String makeValidPath(String path) {
    if (!path.contains(".txt")) {
      path += ".txt";
    }
    return path;
  }

  /**
   * Verifies that a path is valid.
   *
   * @param path is the path to the file.
   * @throws IllegalArgumentException if the file is not found.
   */
  private void verifyValidPath(String path) {
    if (!verifyFileExists(path)) {
      throw new IllegalArgumentException("File not found");
    }
  }

  /**
   * Verifies that the given description is not null.
   *
   * @param description is the description to verify.
   * @throws IllegalArgumentException if the given description is null.
   */
  private void verifyNotNullDescription(ChaosGameDescription description) {
    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }
  }


  /**
   * Removes comments from the given string. Comments in the string are everything after #
   *
   * @param str the given string
   * @return the string with comments removed
   */
  private String removeCommentsFromString(String str) {
    if (str.contains("#")) {
      return str.split("#")[0].trim();
    } else {
      return str;
    }
  }


  /**
   * Reads a chaos game description from a file.
   *
   * @param path is the path to the file.
   * @return a ChaosGameDescription object.
   * @throws IllegalArgumentException if the file is not found, or if the file is not formatted
   *                                  correctly.
   */
  public ChaosGameDescription readFromFile(String path) {
    path = makeValidPath(path);
    verifyValidPath(path);
    try (Scanner scanner = new Scanner(new File(path))) {
      if (!scanner.hasNextLine()) {
        throw new DescriptionFileEmptyException("Description file is empty");
      }
      String transformType = removeCommentsFromString(scanner.nextLine());
      verifyValidTransformType(transformType);

      if (!scanner.hasNextLine()) {
        throw new MissingDataException("Description file is missing data");
      }
      String[] minCoords = removeCommentsFromString(scanner.nextLine()).split(",");
      if (minCoords.length != 2) {
        throw new MissingDataException(
            "Description file is missing coordinate data, and cannot be used to create a "
                + "ChaosGameDescription.");
      }
      if (!scanner.hasNextLine()) {
        throw new MissingDataException("Description file is missing data");
      }
      String[] maxCoords = removeCommentsFromString(scanner.nextLine()).split(",");
      if (maxCoords.length != 2) {
        throw new MissingDataException(
            "Description file is missing coordinate data, and cannot be used to create a "
                + "ChaosGameDescription.");
      }

      List<Transform2D> transforms = new ArrayList<>();

      while (scanner.hasNextLine()) {
        String nextLine = removeCommentsFromString(scanner.nextLine());
        if (nextLine.isEmpty()) {
          continue;
        }

        String[] transform = nextLine.split(",");

        if (transformType.contains("Affine")) {
          if (transform.length != 6) {
            throw new MissingDataException("Invalid transform data");
          }
          Matrix2x2 matrix = new Matrix2x2(verifyDouble(transform[0]),
              verifyDouble(transform[1]), verifyDouble(transform[2]),
              verifyDouble(transform[3]));

          Vector2D vector = new Vector2D(verifyDouble(transform[4]),
              verifyDouble(transform[5]));

          transforms.add(new AffineTransform2D(matrix, vector));

        } else if (transformType.contains("Julia")) {
          if (transform.length != 2) {
            throw new MissingDataException("Invalid transform data");
          }
          double real = verifyDouble(transform[0]);
          double imaginary = verifyDouble(transform[1]);
          Complex complex = new Complex(real, imaginary);
          transforms.add(new JuliaTransform(complex, 1));
          transforms.add(new JuliaTransform(complex, -1));
        }
      }
      if (transforms.isEmpty()) {
        throw new MissingDataException(
            "Description file is missing transform data, and cannot be used to create a "
                + "ChaosGameDescription.");
      }
      Vector2D minCoordsVector = new Vector2D(verifyDouble(minCoords[0]),
          verifyDouble(minCoords[1]));
      Vector2D maxCoordsVector = new Vector2D(verifyDouble(maxCoords[0]),
          verifyDouble(maxCoords[1]));

      return new ChaosGameDescription(minCoordsVector, maxCoordsVector, transforms);

    } catch (MissingDataException e) {
      throw new MissingDataException(e.getMessage());
    } catch (DescriptionFileEmptyException e) {
      throw new DescriptionFileEmptyException(e.getMessage());
    } catch (IOException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }


  /**
   * Writes a chaos game description to a file.
   *
   * @param description is the ChaosGameDescription object to write.
   * @param path        is the path to the file.
   * @throws IOException if an I/O error occurs.
   */
  public void writeToFile(ChaosGameDescription description, String path) throws IOException {
    path = makeValidPath(path);
    verifyNotNullDescription(description);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      writer.write(description.toString());
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }

}
