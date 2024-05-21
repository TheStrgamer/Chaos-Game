package org.example.view;

import javafx.event.EventHandler;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.controller.MainController;
import org.example.controller.ModifyDescriptionController;
import org.example.view.components.DoubleNumberField;
import org.example.view.components.WeightAndIterationsField;

/**
 * <h2>ModifyDescriptionView</h2>
 * <p>
 * The view class for the Modify Description page of the application. Responsible for displaying the
 * Modify Description page. Implements the PageView interface.
 * </p>
 * <p>
 * The Modify Description page allows the user to edit the description of a chaos game.
 * </p>
 *
 * @version 0.4.0
 * @since 0.3.0
 */
public class ModifyDescriptionView implements PageViewInterface {

  private final ModifyDescriptionController modifyDescriptionController;
  private VBox layout;

  private final ListView<VBox> descriptionList = new ListView<>();

  /**
   * Constructor for the ModifyDescriptionView class.
   *
   * @param modifyDescriptionController the controller for the Modify Description page.
   * @param mainController              the main controller for the application.
   */
  public ModifyDescriptionView(ModifyDescriptionController modifyDescriptionController,
      MainController mainController) {
    this.modifyDescriptionController = modifyDescriptionController;
    layout = createLayout();


  }

  /**
   * the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  public VBox getLayout() {
    if (layout == null) {
      layout = createLayout();
    }
    VBox newLayout = new VBox();
    newLayout.getChildren().add(layout);
    return newLayout;
  }

  /**
   * Creates the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  private VBox createLayout() {
    VBox layout = new VBox();
    HBox content = new HBox();

    VBox editDescription = new VBox();
    Label editDescriptionLabel = new Label("Current description: ");
    fillDescriptionList();
    editDescription.getChildren().addAll(editDescriptionLabel, descriptionList);

    content.getChildren().addAll(editDescription);
    layout.getChildren().addAll(content);

    //Style
    editDescription.getStyleClass().add("editDescription");

    return layout;
  }

  /**
   * Fills the description listview with input fields for editing the current description.
   */
  private void fillDescriptionList() {
    descriptionList.getItems().clear();
    VBox minCoords = vector2DToHBox("Min Coords: ", modifyDescriptionController.getMinCoords());
    VBox maxCoords = vector2DToHBox("Max Coords: ", modifyDescriptionController.getMaxCoords());

    descriptionList.getItems().addAll(minCoords, maxCoords);
    int index = 0;
    List<String> transforms = modifyDescriptionController.getTransforms();
    if (modifyDescriptionController.getTransformType().equals("Affine")) {
      for (String transform : transforms) {
        VBox affine = affineTransformToHBox(transform, index, transforms.size() > 1);
        descriptionList.getItems().add(affine);
        index++;
      }
    } else {
      for (String transform : transforms) {
        VBox julia = juliaTransformToHBox(transform, index, transforms.size() > 1);
        descriptionList.getItems().add(julia);
        index += 2;
      }
    }

    VBox addTransform = new VBox();
    HBox addTransformButtonBox = new HBox();
    Button addTransformButton = new Button("Add Transform");
    addTransformButtonBox.getChildren().add(addTransformButton);

    addTransformButton.setOnAction(event -> modifyDescriptionController.addTransform());
    addTransform.getChildren().add(addTransformButtonBox);
    descriptionList.getItems().add(addTransform);

    //Style
    descriptionList.getStyleClass().add("descriptionList");

  }

  /**
   * Converts a vector to a HBox used in the ui to allow for editing.
   *
   * @param name   the name of the vector.
   * @param vector the vector to convert.
   * @return the HBox containing the vector.
   */
  private VBox vector2DToHBox(String name, String vector) {
    VBox row = new VBox();
    HBox nameLabelBox = new HBox(new Label(name));
    HBox content = new HBox();

    String[] split = vector.split(",");

    TextField X0 = new DoubleNumberField(split[0]);
    TextField Y0 = new DoubleNumberField(split[1]);

    EventHandler<KeyEvent> listener = event -> {
      if (X0.getText().isEmpty() || Y0.getText().isEmpty()) {
        return;
      }
      if (event.getCode() == KeyCode.ENTER) {
        switch (name) {
          case "Min Coords: ":
            if (modifyDescriptionController.minCoordsIsValid(X0.getText(), Y0.getText())) {
              modifyDescriptionController.setMinCoords(X0.getText(), Y0.getText());
              setCoordFieldsValid(X0, Y0);
            } else {
              setCoordFieldsInvalid(X0, Y0, "Min Coords must be less than Max Coords");
            }
            modifyDescriptionController.setMinCoords(X0.getText(), Y0.getText());
            break;
          case "Max Coords: ":
            if (modifyDescriptionController.maxCoordsIsValid(X0.getText(), Y0.getText())) {
              modifyDescriptionController.setMaxCoords(X0.getText(), Y0.getText());
              setCoordFieldsValid(X0, Y0);
            } else {
              setCoordFieldsInvalid(X0, Y0, "Max Coords must be greater than Min Coords");
            }
            break;
          default:
            System.out.println("Error in vector2DToHBox");
            break;
        }
      }
    };
    X0.setOnKeyPressed(listener);
    Y0.setOnKeyPressed(listener);

    content.getChildren().addAll(X0, Y0);
    row.getChildren().addAll(nameLabelBox, content);

    //Style
    content.getStyleClass().add("content");

    return row;
  }

  /**
   * Sets the coordinate field styles to valid for better user feedback.
   *
   * @param X0 the X0 field.
   * @param Y0 the Y0 field.
   */

  private void setCoordFieldsValid(TextField X0, TextField Y0) {
    X0.getStyleClass().remove("invalid");
    Y0.getStyleClass().remove("invalid");
    X0.getStyleClass().remove("unsaved");
    Y0.getStyleClass().remove("unsaved");
    X0.setTooltip(null);
    Y0.setTooltip(null);
  }

  /**
   * Sets the coordinate field styles to invalid for better user feedback.
   *
   * @param X0      the X0 field.
   * @param Y0      the Y0 field.
   * @param tooltip the tooltip to display.
   */
  private void setCoordFieldsInvalid(TextField X0, TextField Y0, String tooltip) {
    X0.getStyleClass().add("invalid");
    Y0.getStyleClass().add("invalid");
    X0.setTooltip(new Tooltip(tooltip));
    Y0.setTooltip(new Tooltip(tooltip));
  }

  /**
   * Creates a VBox that represents an affine transform in the UI. The transform can be edited, and
   * deleted.
   *
   * @param transform the transform to convert.
   * @param index     the index of the transform in the list of transforms.
   * @param removable if the transform is removable.
   * @return the VBox containing the affine transform.
   */
  private VBox affineTransformToHBox(String transform, int index, boolean removable) {
    VBox row = new VBox();
    HBox content = new HBox();

    HBox titleBox = new HBox(new Label("Transform: "));

    String[] split = transform.split(",");
    TextField a00 = new DoubleNumberField(split[0]);
    TextField a01 = new DoubleNumberField(split[1]);
    TextField a10 = new DoubleNumberField(split[2]);
    TextField a11 = new DoubleNumberField(split[3]);
    TextField a = new DoubleNumberField(split[4]);
    TextField b = new DoubleNumberField(split[5]);

    EventHandler<KeyEvent> listener = event -> {
      if (a00.getText().isEmpty() || a01.getText().isEmpty() || a10.getText().isEmpty()
          || a11.getText().isEmpty() || a.getText().isEmpty() || b.getText().isEmpty()) {
        return;
      }
      if (event.getCode() == KeyCode.ENTER) {
        modifyDescriptionController.setAffineTransforms(index, a00.getText(), a01.getText(),
            a10.getText(), a11.getText(), a.getText(), b.getText());
      }
    };

    a00.setOnKeyPressed(listener);
    a01.setOnKeyPressed(listener);
    a10.setOnKeyPressed(listener);
    a11.setOnKeyPressed(listener);
    a.setOnKeyPressed(listener);
    b.setOnKeyPressed(listener);

    VBox matrixCol1 = new VBox(a00, a10);
    VBox matrixCol2 = new VBox(a01, a11);

    Label plus = new Label("+");

    VBox vector = new VBox(a, b);

    Label weightLabel = new Label("Weight: ");
    TextField weight = createWeightField(split[6].trim(), index);
    HBox weightBox = new HBox(weightLabel, weight);

    VBox rightSide = new VBox(weightBox);
    if (removable) {
      Button removeTransform = new Button("Remove Transform");
      removeTransform.setOnAction(
          event -> modifyDescriptionController.removeAffineTransform(index));
      removeTransform.getStyleClass().add("removeTransform");

      rightSide.getChildren().add(removeTransform);
    }
    content.getChildren().addAll(matrixCol1, matrixCol2, plus, vector, rightSide);
    row.getChildren().addAll(titleBox, content);

    //Style
    plus.getStyleClass().add("plus");

    return row;
  }

  /**
   * Converts a julia transform to a HBox used in the ui to allow for editing.
   *
   * @param transform the transform to convert.
   * @param index     the index of the transform in the list of transforms.
   * @param removable if the transform is removable.
   * @return the VBox containing the julia transform.
   */
  private VBox juliaTransformToHBox(String transform, int index, boolean removable) {
    VBox row = new VBox();
    HBox content = new HBox();

    HBox titleBox = new HBox(new Label("Transform: "));
    String[] split = transform.split(",");

    TextField real = new DoubleNumberField(split[0]);
    TextField imaginary = new DoubleNumberField(split[1]);

    Label weightLabel = new Label("Weight +/-: ");
    TextField positiveWeight = createWeightField(split[2], index);
    TextField negativeWeight = createWeightField(split[3], index + 1);

    HBox weightBox = new HBox(weightLabel, positiveWeight, negativeWeight);
    VBox rightSide = new VBox(weightBox);

    if (removable) {
      Button removeTransform = new Button("Remove Transform");
      removeTransform.setOnAction(event -> modifyDescriptionController.removeJuliaTransform(index));
      removeTransform.getStyleClass().add("removeTransform");
      rightSide.getChildren().add(removeTransform);
    }

    EventHandler<KeyEvent> listener = event -> {
      if (real.getText().isEmpty() || imaginary.getText().isEmpty()) {
        return;
      }
      if (event.getCode() == KeyCode.ENTER) {
        modifyDescriptionController.setJuliaTransforms(index, real.getText(), imaginary.getText());
      }
    };

    real.setOnKeyPressed(listener);
    imaginary.setOnKeyPressed(listener);

    content.getChildren().addAll(real, imaginary, rightSide);
    row.getChildren().addAll(titleBox, content);

    //Style
    content.getStyleClass().add("content");

    return row;
  }

  /**
   * Updates the description list. It updates based on the current description.
   */
  public void updateDescriptionList() {
    fillDescriptionList();
  }

  /**
   * Creates a TextField for the weight of a transform. Adds event listener that updates weights.
   * Uses the WeightAndIterationsField class.
   *
   * @param text  the text to set in the TextField.
   * @param index the index of the transform in the list of transforms.
   * @return the TextField for the weight of a transform.
   */
  public TextField createWeightField(String text, int index) {
    TextField weight = new WeightAndIterationsField(text.trim());

    weight.textProperty().addListener((observable, oldValue, newValue) -> {
      weight.getStyleClass().add("unsaved");
      String tooltipText = "Press Enter to save changes";
      if (weight.getText().isEmpty()) {
        tooltipText = "Type a number and press Enter to save changes";
      }
      weight.setTooltip(new Tooltip(tooltipText));

    });
    weight.setOnAction(event -> {
      modifyDescriptionController.setWeight(index, weight.getText());
      weight.getStyleClass().remove("unsaved");
      weight.setTooltip(null);
    });

    //Style
    weight.getStyleClass().add("weight");

    return weight;
  }


}

