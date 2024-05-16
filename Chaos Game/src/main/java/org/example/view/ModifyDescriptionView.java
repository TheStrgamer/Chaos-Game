package org.example.view;

import javafx.event.EventHandler;
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

/**
 * <h1>ModifyDescriptionView</h1>
 * The view class for the Modify Description page of the application. Responsible for displaying the
 * Modify Description page. Implements the PageView interface.
 */
public class ModifyDescriptionView implements PageViewInterface {

  private final ModifyDescriptionController modifyDescriptionController;
  private final MainController mainController;
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
    this.mainController = mainController;
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
   * Method for creating the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  private VBox createLayout() {
    VBox layout = new VBox();
    HBox content = new HBox();
    HBox topBar = createButtonLayout();

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
   * Fills the description listview with input fields for editing the current
   * description.
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
   * Method for converting a vector to a HBox used in the ui to allow for editing.
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

    TextField X0 = createNumberField(split[0]);
    TextField Y0 = createNumberField(split[1]);


    EventHandler<KeyEvent> listener = event -> {
      if (X0.getText().isEmpty() || Y0.getText().isEmpty()) {
        return;
      }
      if (event.getCode() == KeyCode.ENTER) {
        switch (name) {
          case "Min Coords: ":
            modifyDescriptionController.setMinCoords(X0.getText(), Y0.getText());
            break;
          case "Max Coords: ":
            modifyDescriptionController.setMaxCoords(X0.getText(), Y0.getText());
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
    TextField a00 = createNumberField(split[0]);
    TextField a01 = createNumberField(split[1]);
    TextField a10 = createNumberField(split[2]);
    TextField a11 = createNumberField(split[3]);
    TextField a = createNumberField(split[4]);
    TextField b = createNumberField(split[5]);

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
   * Method for converting a julia transform to a HBox used in the ui to allow for editing.
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

    TextField real = createNumberField(split[0]);
    TextField imaginary = createNumberField(split[1]);



    Label weightLabel = new Label("Weight +/-: ");
    TextField positiveWeight = createWeightField(split[2].trim(), index);
    TextField negativeWeight = createWeightField(split[3].trim(), index + 1);

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
   * Creates a textfield for the weight of a transform. Adds event listener that updates
   * weights.
   *
   * @param text  the text to set in the textfield.
   * @param index the index of the transform in the list of transforms.
   * @return the textfield for the weight of a transform.
   */
  public TextField createWeightField(String text, int index) {
    TextField weight = new TextField();
    weight.setText(text);
    weight.setOnAction(event -> modifyDescriptionController.setWeight(index, weight.getText()));
    weight.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
      if (!event.getCharacter().matches("[0-9]")) {
        event.consume();
      }
    });

    //Style
    weight.getStyleClass().add("weight");

    return weight;
  }

  /**
   * Creates a text field that only accepts numbers and periods.
   *
   * @return the created text field.
   */
  private TextField createNumberField(String text) {
    TextField field = new TextField();
    field.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
      if (!event.getCharacter().matches("[0-9.-]")) {
        event.consume();
      }
    });
    field.setText(text.trim());
    return field;
  }


}

