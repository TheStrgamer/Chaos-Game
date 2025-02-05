package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javafx.scene.layout.VBox;
import org.example.model.chaosgame.ChaosCanvas;
import org.example.model.chaosgame.ChaosGameDescription;
import org.example.model.math.Complex;
import org.example.model.math.Matrix2x2;
import org.example.model.math.Vector2D;
import org.example.model.observer.ChaosGameObserver;
import org.example.model.transform.AffineTransform2D;
import org.example.model.transform.JuliaTransform;
import org.example.model.transform.Transform2D;
import org.example.view.ModifyDescriptionView;

/**
 * <h2>ModifyDescriptionController.</h2>
 * <p>
 * The controller class for the Modify Description page of the application. It handles the logic for
 * the Modify Description page, and is responsible for choosing and creating descriptions for the
 * Chaos Game.
 * </p>
 * <p>
 * The class implements the ChaosGameObserver interface, and listens for changes in the description
 * of the Chaos Game.
 * </p>
 *
 * @version 1.0.0
 * @since 0.3.0
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
   * Checks if the given weight is a valid number.
   * @param weight the weight to check.
   * @return true if the weight is a valid number, false otherwise.
   */
  public boolean weightIsValid(String weight) {
    try {
      Integer.parseInt(weight);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * checks if the given minCoords are valid, and if they are smaller than the maxCoords.
   *
   * @param x0 the x0 value
   * @param x1 the x1 value
   * @return true if the values are valid, false otherwise.
   */
  public boolean minCoordsIsValid(String x0, String x1) {
    if (!Stream.of(x0, x1).allMatch(this::stringIsValidNumber)) {
      return false;
    }
    double newX0 = Double.parseDouble(x0);
    double newX1 = Double.parseDouble(x1);
    return newX0 < maxCoords.getX0() && newX1 < maxCoords.getX1();
  }

  /**
   * checks if the given maxCoords are valid, and if they are larger than the minCoords.
   *
   * @param x0 the x0 value
   * @param x1 the x1 value
   * @return true if the values are valid, false otherwise.
   */
  public boolean maxCoordsIsValid(String x0, String x1) {
    if (!Stream.of(x0, x1).allMatch(this::stringIsValidNumber)) {
      return false;
    }
    double newX0 = Double.parseDouble(x0);
    double newX1 = Double.parseDouble(x1);
    return newX0 > minCoords.getX0() && newX1 > minCoords.getX1();
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
    transforms = description.getTransforms();
    modifyDescriptionView = new ModifyDescriptionView(this);
  }

  /**
   * Returns the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  public VBox getLayout() {
    return modifyDescriptionView.getLayout();
  }


  /**
   * Adds a new empty transform to the description. If the current description is using julia
   * transforms, it adds two transforms, one for each sign value.
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
   * Returns what type of transform the description is using.
   *
   * @return the type of transform.
   */
  public String getTransformType() {
    return currentDescription.getTransformType();
  }

  /**
   * Returns the minimum coordinates of the description.
   *
   * @return the minimum coordinates.
   */
  public String getMinCoords() {
    return minCoords.toString();
  }

  /**
   * Returns the maximum coordinates of the description.
   *
   * @return the maximum coordinates.
   */
  public String getMaxCoords() {
    return maxCoords.toString();
  }

  /**
   * Returns the transforms of the description.
   *
   * @return the transforms.
   */
  public List<String> getTransforms() {
    return currentDescription.getTransformsAsStringList();
  }

  /**
   * Listens to changes in the description of the Chaos Game.
   *
   * @param description the description of the Chaos Game.
   */
  @Override
  public void updateDescription(ChaosGameDescription description) {
    currentDescription = description;
    minCoords = description.getMinCoords();
    maxCoords = description.getMaxCoords();
    transforms = description.getTransforms();
    modifyDescriptionView.updateDescriptionList();
  }

  /**
   * Listens to changes in the canvas of the Chaos Game.
   *
   * @param canvas the canvas of the Chaos Game.
   */
  @Override
  public void updateCanvas(ChaosCanvas canvas) {
  }

  /**
   * Creates a new description from the current values. If the description is the same as the
   * current description, nothing happens.
   */
  public void createDescription() {
    List<Integer> weights = new ArrayList<>();
    for (int i = 0; i < transforms.size(); i++) {
      if (i < currentDescription.getWeights().size()) {
        weights.add(currentDescription.getWeights().get(i));
      } else {
        weights.add(1);
      }

    }

    ChaosGameDescription description = new ChaosGameDescription(minCoords, maxCoords, transforms,
        weights);
    if (currentDescription.equals(description)) {
      return;
    }
    mainController.setCurrentDescription(description);
  }

  /**
   * Sets the minimum coordinates of the description.
   *
   * @param x0 the x-coordinate of the minimum coordinates.
   * @param x1 the y-coordinate of the minimum coordinates.
   */
  public void setMinCoords(String x0, String x1) {
    double newX0 = Double.parseDouble(x0);
    double newX1 = Double.parseDouble(x1);
    minCoords = new Vector2D(newX0, newX1);
    createDescription();
  }

  /**
   * Sets the maximum coordinates of the description.
   *
   * @param x0 the x-coordinate of the maximum coordinates.
   * @param x1 the y-coordinate of the maximum coordinates.
   */
  public void setMaxCoords(String x0, String x1) {
    double newX0 = Double.parseDouble(x0);
    double newX1 = Double.parseDouble(x1);
    maxCoords = new Vector2D(newX0, newX1);
    createDescription();
  }

  /**
   * Sets the coordinates of a Julia transform. If the given coordinates are invalid or the same as
   * the current coordinates, nothing happens.
   *
   * @param index     the index of the transform.
   * @param real      the real part of the complex number.
   * @param imaginary the imaginary part of the complex number.
   */
  public void setJuliaTransforms(int index, String real, String imaginary) {
    if (!Stream.of(real, imaginary).allMatch(this::stringIsValidNumber)) {
      mainController.showErrorPopup("One or more values are invalid");
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
    createDescription();
  }

  /**
   * Sets the coordinates of an Affine transform. If the given coordinates are invalid or the same
   * as the current coordinates, nothing happens.
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
      mainController.showErrorPopup("One or more values are invalid");
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
    createDescription();
  }

  /**
   * Removes an affine transform from the description. If the index is invalid, nothing happens.
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
   * Removes a Julia transform from the description. If the index is invalid, nothing happens.
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

  /**
   * Sets the weight of a transform at the given index.
   *
   * @param index  the index of the transform.
   * @param weight the weight of the transform.
   */
  public void setWeight(int index, String weight) {
    if (!weightIsValid(weight)) {
      mainController.showErrorPopup("Invalid weight");
      return;
    }
    int newWeight = Integer.parseInt(weight);
    if (newWeight < 0) {
      return;
    }
    currentDescription.setWeight(index, newWeight);
  }


}
