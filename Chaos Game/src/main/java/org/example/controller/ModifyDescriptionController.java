package org.example.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.VBox;
import org.example.model.ChaosCanvas;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameDescriptionFactory;
import org.example.model.ChaosGameObserver;
import org.example.model.Complex;
import org.example.model.JuliaTransform;
import org.example.model.AffineTransform2D;
import org.example.model.Matrix2x2;
import org.example.model.Transform2D;
import org.example.model.Vector2D;
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

  private final ChaosGameDescriptionFactory chaosGameDescriptionFactory;

  private Vector2D minCoords;
  private Vector2D maxCoords;
  private List<Transform2D> transforms;


  /**
   * Verifies that the given string is a valid number. Throws an IllegalArgumentException if the given string fails to parse as a double.
   * @param number the string to verify.
   */
  private void verifyStringIsValidNumber(String number) {
    try {
      Double.parseDouble(number);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid number");
    }
  }

  /**
   * Constructor for the ModifyDescriptionController class.
   *
   * @param mainController the main controller for the application. Used for switching between views.
   */
  public ModifyDescriptionController(MainController mainController) {
    this.mainController = mainController;
    minCoords = mainController.getCurrentDescription().getMinCoords();
    maxCoords = mainController.getCurrentDescription().getMaxCoords();
    transforms = mainController.getCurrentDescription().getTransformsAsList();
    chaosGameDescriptionFactory = new ChaosGameDescriptionFactory();
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

  public void addTransform() {
    System.out.println("Add transform");
    }

  public String getTransformType() {
    return mainController.getCurrentDescription().getTransformType();
  }

  public String getMinCoords() {
    return mainController.getCurrentDescription().getMinCoords().toString();
  }

  public String getMaxCoords() {
    return mainController.getCurrentDescription().getMaxCoords().toString();
  }

  public List<String> getTransforms() {
    List<String> transforms = new ArrayList<>();
    return mainController.getCurrentDescription().getTransformsAsStringList();
  }

  public void setDescriptionSize(int width, int height) {
    modifyDescriptionView.changeDescriptionListScale(width, height);
  }


  /**
   * Method for listening to changes in the description of the Chaos Game.
   * @param description the description of the Chaos Game.
   */
  @Override
  public void updateDescription(ChaosGameDescription description) {
    minCoords = description.getMinCoords();
    maxCoords = description.getMaxCoords();
    transforms = description.getTransformsAsList();
    modifyDescriptionView.updateDescriptionList();
  }

  /**
   * Method for listening to changes in the canvas of the Chaos Game.
   * @param canvas the canvas of the Chaos Game.
   */
  @Override
  public void updateCanvas(ChaosCanvas canvas) {

  }

  public void createDescription() {
    System.out.println(minCoords);
    System.out.println(maxCoords);
    ChaosGameDescription description = new ChaosGameDescription(minCoords, maxCoords, transforms);
    mainController.setCurrentDescription(description);
  }
  public void setMinCoords(String X0, String X1) {
    verifyStringIsValidNumber(X0);
    verifyStringIsValidNumber(X1);
    minCoords = new Vector2D(Double.parseDouble(X0), Double.parseDouble(X1));
    createDescription();
  }
  public void setMaxCoords(String X0, String X1) {
    verifyStringIsValidNumber(X0);
    verifyStringIsValidNumber(X1);
    maxCoords = new Vector2D(Double.parseDouble(X0), Double.parseDouble(X1));
    createDescription();
  }
  public void setJuliaTransforms(int index, String X0, String X1) {
    verifyStringIsValidNumber(X0);
    verifyStringIsValidNumber(X1);
    Complex c = new Complex(Double.parseDouble(X0), Double.parseDouble(X1));
    transforms.set(index, new JuliaTransform(c, 1));
    transforms.set(index + 1, new JuliaTransform(c, -1));
    createDescription();
  }
  public void setAffineTransforms(int index, String a00, String a01, String a10, String a11, String a, String b) {
    verifyStringIsValidNumber(a00);
    verifyStringIsValidNumber(a01);
    verifyStringIsValidNumber(a10);
    verifyStringIsValidNumber(a11);
    verifyStringIsValidNumber(a);
    verifyStringIsValidNumber(b);

    Matrix2x2 matrix = new Matrix2x2(Double.parseDouble(a00), Double.parseDouble(a01), Double.parseDouble(a10), Double.parseDouble(a11));
    Vector2D vector = new Vector2D(Double.parseDouble(a), Double.parseDouble(b));
    transforms.set(index, new AffineTransform2D(matrix, vector));
    createDescription();
  }


}
