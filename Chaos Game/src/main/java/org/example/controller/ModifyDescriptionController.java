package org.example.controller;

import java.util.List;
import java.util.stream.Stream;
import javafx.scene.layout.VBox;
import org.example.model.chaosGame.ChaosGameDescription;
import org.example.model.math.Complex;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.observer.ChaosGameObserver;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.example.model.transform.Transform2D;
import org.example.model.chaosGame.ChaosCanvas;

import org.example.view.ModifyDescriptionView;

/**
 * <h1>ModifyDescriptionController</h1>
 * The controller class for the Modify Description page of the application. It handles the logic for
 * the Modify Description page, and is responsible for choosing and creating descriptions for the
 * Chaos Game.
 */
public class ModifyDescriptionController implements ChaosGameObserver {

  private final MainController mainController;
  private final ModifyDescriptionView modifyDescriptionView;

  private ChaosGameDescription currentDescription;

  private Vector2D minCoords;
  private Vector2D maxCoords;
  private List<Transform2D> transforms;


  /**
   * Verifies that the given string is a valid number.
   *
   * @param number the string to verify.
   * @return true if the string is a valid number, false otherwise.
   */
  private boolean stringIsValidNumber(String number) {
    try {
      Double.parseDouble(number);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }

  }

  /**
   * Constructor for the ModifyDescriptionController class.
   *
   * @param mainController the main controller for the application. Used for switching between
   *                       views.
   * @param description    the description of the Chaos Game.
   */
  public ModifyDescriptionController(MainController mainController,
      ChaosGameDescription description) {
    this.mainController = mainController;
    this.currentDescription = description;

    minCoords = description.getMinCoords();
    maxCoords = description.getMaxCoords();
    transforms = description.getTransformsAsList();
    modifyDescriptionView = new ModifyDescriptionView(this, mainController);
  }

  /**
   * Method for getting the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  public VBox getLayout() {
    return modifyDescriptionView.getLayout();
  }


  /**
   * Method for reading a description from a file.
   */
  public void readFromFile() {
    //TODO
    System.out.println("Read from file");
  }

  /**
   * Method for saving the current description to a file.
   */
  public void saveToFile() {
    //TODO
    System.out.println("Save to file");
  }

  /**
   * Method for adding a new empty transform to the description. If the current description is using
   * julia transforms, it adds two transforms, one for each sign value.
   */
  public void addTransform() {
    if (currentDescription.getTransformType().equals("Julia")) {
      transforms.add(new JuliaTransform(new Complex(0, 0), 1));
      transforms.add(new JuliaTransform(new Complex(0, 0), -1));
    } else {
      transforms.add(new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0)));
    }
    createDescription();
  }

  /**
   * Method for getting what type of transform the description is using.
   *
   * @return the type of transform.
   */
  public String getTransformType() {
    return currentDescription.getTransformType();
  }

  /**
   * Method for getting the minimum coordinates of the description.
   *
   * @return the minimum coordinates.
   */
  public String getMinCoords() {
    return minCoords.toString();
  }

  /**
   * Method for getting the maximum coordinates of the description.
   *
   * @return the maximum coordinates.
   */
  public String getMaxCoords() {
    return maxCoords.toString();
  }

  /**
   * Method for getting the transforms of the description.
   *
   * @return the transforms.
   */
  public List<String> getTransforms() {
    return currentDescription.getTransformsAsStringList();
  }

  /**
   * Method for changing the size of the description list.
   *
   * @param width  the width of the description list.
   * @param height the height of the description list.
   */
  public void setDescriptionSize(int width, int height) {
    modifyDescriptionView.changeDescriptionListScale(width, height);
  }


  /**
   * Method for listening to changes in the description of the Chaos Game.
   *
   * @param description the description of the Chaos Game.
   */
  @Override
  public void updateDescription(ChaosGameDescription description) {
    currentDescription = description;
    minCoords = description.getMinCoords();
    maxCoords = description.getMaxCoords();
    transforms = description.getTransformsAsList();
    modifyDescriptionView.updateDescriptionList();
  }

  /**
   * Method for listening to changes in the canvas of the Chaos Game.
   *
   * @param canvas the canvas of the Chaos Game.
   */
  @Override
  public void updateCanvas(ChaosCanvas canvas) {
  }

  /**
   * Method for creating a new description from the current values. If the description is the same
   * as the current description, nothing happens.
   */
  public void createDescription() {
    ChaosGameDescription description = new ChaosGameDescription(minCoords, maxCoords, transforms);
    if (currentDescription.equals(description)) {
      return;
    }
    mainController.setCurrentDescription(description);
  }

  /**
   * Method for setting the minimum coordinates of the description. If the given coordinates are
   * invalid, nothing happens.
   *
   * @param X0 the x-coordinate of the minimum coordinates.
   * @param X1 the y-coordinate of the minimum coordinates.
   */
  public void setMinCoords(String X0, String X1) {
    if (!Stream.of(X0, X1).allMatch(this::stringIsValidNumber)) {
      return;
    }
    double x0 = Double.parseDouble(X0);
    double x1 = Double.parseDouble(X1);
    Vector2D newMinCoords = new Vector2D(x0, x1);
    if (minCoords.equals(newMinCoords)) {
      return;
    }
    minCoords = newMinCoords;
  }

  /**
   * Method for setting the maximum coordinates of the description. If the given coordinates are
   * invalid or the same as the current coordinates, nothing happens.
   *
   * @param X0 the x-coordinate of the maximum coordinates.
   * @param X1 the y-coordinate of the maximum coordinates.
   */
  public void setMaxCoords(String X0, String X1) {
    if (!Stream.of(X0, X1).allMatch(this::stringIsValidNumber)) {
      return;
    }
    double x0 = Double.parseDouble(X0);
    double x1 = Double.parseDouble(X1);
    if (x0 < minCoords.getX0() || x1 < minCoords.getX1()) {
      return;
    }
    Vector2D newMaxCoords = new Vector2D(x0, x1);
    if (maxCoords.equals(newMaxCoords)) {
      return;
    }
    maxCoords = newMaxCoords;
  }

  /**
   * Method for setting the coordinates of a Julia transform. If the given coordinates are invalid
   * or the same as the current coordinates, nothing happens.
   *
   * @param index     the index of the transform.
   * @param real      the real part of the complex number.
   * @param imaginary the imaginary part of the complex number.
   */
  public void setJuliaTransforms(int index, String real, String imaginary) {
    if (!Stream.of(real, imaginary).allMatch(this::stringIsValidNumber)) {
      return;
    }
    Complex c = new Complex(Double.parseDouble(real), Double.parseDouble(imaginary));

    JuliaTransform newTransform1 = new JuliaTransform(c, 1);
    if (newTransform1.equals(transforms.get(index))) {
      return;
    }
    JuliaTransform newTransform2 = new JuliaTransform(c, -1);
    if (newTransform2.equals(transforms.get(index + 1))) {
      return;
    }
    transforms.set(index, newTransform1);
    transforms.set(index + 1, newTransform2);
  }

  /**
   * Method for setting the coordinates of an Affine transform. If the given coordinates are invalid
   * or the same as the current coordinates, nothing happens.
   *
   * @param index the index of the transform.
   * @param a00   the a00 value of the matrix.
   * @param a01   the a01 value of the matrix.
   * @param a10   the a10 value of the matrix.
   * @param a11   the a11 value of the matrix.
   * @param a     the x-coordinate of the vector.
   * @param b     the y-coordinate of the vector.
   */
  public void setAffineTransforms(int index, String a00, String a01, String a10, String a11,
      String a, String b) {
    if (!Stream.of(a00, a01, a10, a11, a, b).allMatch(this::stringIsValidNumber)) {
      return;
    }
    Matrix2x2 matrix = new Matrix2x2(Double.parseDouble(a00), Double.parseDouble(a01),
        Double.parseDouble(a10), Double.parseDouble(a11));
    Vector2D vector = new Vector2D(Double.parseDouble(a), Double.parseDouble(b));
    AffineTransform2D newTransform = new AffineTransform2D(matrix, vector);
    if (newTransform.equals(transforms.get(index))) {
      return;
    }
    transforms.set(index, newTransform);
  }

  /**
   * Method for removing an affine transform from the description. If the index is invalid, nothing
   * happens.
   *
   * @param index the index of the transform to remove.
   */
  public void removeAffineTransform(int index) {
    if (index < 0 || index >= transforms.size()) {
      return;
    }
    transforms.remove(index);
    createDescription();
  }

  /**
   * Method for removing a Julia transform from the description. If the index is invalid, nothing
   * happens.
   *
   * @param index the index of the transform to remove.
   */
  public void removeJuliaTransform(int index) {
    if (index < 0 || index >= transforms.size()) {
      return;
    }
    transforms.remove(index);
    transforms.remove(index);
    createDescription();
  }


}
