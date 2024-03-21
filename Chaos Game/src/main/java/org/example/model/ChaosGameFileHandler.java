package org.example.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChaosGameFileHandler {

  /**
   * Verifies that the given transform type is valid.
   *
   * @param transformType is the transform type to use.
   * @throws IllegalArgumentException if the given transform type is invalid.
   */
  private void verifyValidTransformType(String transformType) {
    if (!transformType.equals("Affine") && !transformType.equals("Julia")) {
      throw new IllegalArgumentException("Invalid transform type");
    }
  }

  /**
   * Verifies that a file exists.
   *
   * @param path is the path to the file.
   * @return true if the file exists, false otherwise.
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
   */
  private String makeValidPath(String path) {
    if (!path.contains(".txt")) {
      path += ".txt";
    }
    if (!path.contains("chaosFiles/")) {
      path = "chaosFiles/" + path;
    }
    return path;
  }

  /**
   * Verifies that a path is valid.
   *
   * @param path is the path to the file.
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
      String transformType = scanner.nextLine();
      verifyValidTransformType(transformType);

      String minCoords = scanner.nextLine();
      Vector2D minCoordsVector = new Vector2D(Double.parseDouble(minCoords.split(",")[0]),
          Double.parseDouble(minCoords.split(",")[1]));

      String maxCoords = scanner.nextLine();
      Vector2D maxCoordsVector = new Vector2D(Double.parseDouble(maxCoords.split(",")[0]),
          Double.parseDouble(maxCoords.split(",")[1]));

      List<Transform2D> transforms = new ArrayList<>();

      while (scanner.hasNextLine()) {
        String nextLine = scanner.nextLine();
        if (nextLine.contains("#")) {
          nextLine = nextLine.split("#")[0].trim();
        }
        if (nextLine.isEmpty()) {
          continue;
        }

        String[] transform = nextLine.split(",");

        if (transformType.equals("Affine")) {
          Matrix2x2 matrix = new Matrix2x2(Double.parseDouble(transform[0]),
              Double.parseDouble(transform[1]), Double.parseDouble(transform[2]),
              Double.parseDouble(transform[3]));

          Vector2D vector = new Vector2D(Double.parseDouble(transform[4]),
              Double.parseDouble(transform[5]));

          transforms.add(new AffineTransform2D(matrix, vector));

        } else if (transformType.equals("Julia")) {
          double real = Double.parseDouble(transform[0]);
          double imaginary = Double.parseDouble(transform[1]);
          Complex complex = new Complex(real, imaginary);
          int sign = Integer.parseInt(transform[2]);

          transforms.add(new JuliaTransform(complex, sign));
        }
      }
      return new ChaosGameDescription(minCoordsVector, maxCoordsVector, transforms);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
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
    verifyValidPath(path);
    verifyNotNullDescription(description);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
      writer.write(description.toString());
    }
    catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
