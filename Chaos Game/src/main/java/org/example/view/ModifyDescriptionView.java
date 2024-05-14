package org.example.view;

import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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

  private ListView<VBox> descriptionList = new ListView<>();
  private VBox editDescription;

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



  }

  /**
>>>>>>> Chaos Game/src/main/java/org/example/view/ModifyDescriptionView.java
   * Method for getting the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  public VBox getLayout() {
    return createLayout();
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
    VBox sideBar = createSideBar();

    editDescription = new VBox();
    Label editDescriptionLabel = new Label("Current description: ");
    descriptionList = createDescriptionList();
    editDescription.getChildren().addAll(editDescriptionLabel, descriptionList);

    content.getChildren().addAll(sideBar, editDescription);
    layout.getChildren().addAll(topBar, content);

    //Style
    topBar.setStyle(
        "-fx-alignment: center; -fx-spacing: 10px;  -fx-padding: 10px; -fx-background-color: #8f8f8f;");
    editDescription.setStyle(
        "-fx-alignment: center; -fx-spacing: 10px; -fx-background-color: #bfbfbf;");

    return layout;
  }

  private VBox createSideBar() {
    VBox readAndWrite = new VBox();

    Label descriptionLabel = new Label("Description: ");
    Button readFromFile = new Button("Read description from file");
    Button saveToFile = new Button("Save current description");
    readAndWrite.getChildren().addAll(descriptionLabel, readFromFile, saveToFile);

    readFromFile.setOnAction(event -> modifyDescriptionController.readFromFile());
    saveToFile.setOnAction(event -> modifyDescriptionController.saveToFile());
    readAndWrite.setStyle(" -fx-spacing: 10px; -fx-background-color: #8f8f8f; -fx-padding: 10px;");

    return readAndWrite;
  }

  private HBox createButtonLayout() {
    HBox buttonLayout = new HBox();
    Button toChaosGame = new Button("To Chaos Game");
    buttonLayout.getChildren().addAll(toChaosGame);

    toChaosGame.setOnAction(event -> {
      modifyDescriptionController.createDescription();
      mainController.switchToChaosGameView();
    });

    return buttonLayout;

  }


  private ListView<VBox> createDescriptionList() {
    ListView<VBox> desctiptionListView = new ListView<>();

    VBox minCoords = vector2DToHBox("Min Coords: ", modifyDescriptionController.getMinCoords());
    VBox maxCoords = vector2DToHBox("Max Coords: ", modifyDescriptionController.getMaxCoords());

    desctiptionListView.getItems().addAll(minCoords, maxCoords);
    int index = 0;
    List<String> transforms = modifyDescriptionController.getTransforms();
    if (modifyDescriptionController.getTransformType().equals("Affine")) {
      for (String transform : transforms) {
        VBox affine = affineTransformToHBox(transform, index, transforms.size() > 1);
        desctiptionListView.getItems().add(affine);
        index++;
      }
    } else {
      for (String transform : transforms) {
        VBox julia = juliaTransformToHBox(transform, index, transforms.size() > 1);
        desctiptionListView.getItems().add(julia);
        index += 2;
      }
    }

    VBox addTransform = new VBox();
    HBox addTransformButtonBox = new HBox();
    Button addTransformButton = new Button("Add Transform");
    addTransformButtonBox.setAlignment(Pos.CENTER);
    addTransformButtonBox.getChildren().add(addTransformButton);

    addTransformButton.setOnAction(event -> modifyDescriptionController.addTransform());
    addTransform.getChildren().add(addTransformButtonBox);
    desctiptionListView.getItems().add(addTransform);

    desctiptionListView.setPrefSize(600, 500);
    desctiptionListView.setStyle(
        "-fx-background-color: #bfbfbf; -fx-alignment: center; -fx-spacing: 10px;");

    return desctiptionListView;

  }

  /**
   * Method for changing the scale of the description list.
   *
   * @param width  the new width of the description list.
   * @param height the new height of the description list.
   */
  public void changeDescriptionListScale(int width, int height) {
    descriptionList.setPrefSize(width, height);
  }

  /**
   * Method for converting a vector to a HBox used in the ui to allow for editing.
   *
   * @param name   the name of the vector.
   * @param vector the vector to convert.
   * @return the HBox containing the vector.
   */
  private VBox vector2DToHBox(String name, String vector) {
    VBox vBox = new VBox();
    HBox content = new HBox();
    HBox nameLabelBox = new HBox();
    Label nameLabel = new Label(name);

    String[] split = vector.split(",");

    TextField X0 = createNumberField(split[0]);
    TextField Y0 = createNumberField(split[1]);

    nameLabelBox.setAlignment(Pos.CENTER);
    content.setAlignment(Pos.CENTER);

    X0.setStyle("-fx-pref-width: 80px;");
    Y0.setStyle("-fx-pref-width: 80px;");
    content.setStyle("-fx-spacing: 10px;");

    ChangeListener<String> listener = (observable, oldValue, newValue) -> {
      if (X0.getText().isEmpty() || Y0.getText().isEmpty()) {
        return;
      }
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
    };
    X0.textProperty().addListener(listener);
    Y0.textProperty().addListener(listener);


    nameLabelBox.getChildren().add(nameLabel);
    content.getChildren().addAll(X0, Y0);
    vBox.getChildren().addAll(nameLabelBox, content);
    return vBox;
  }

  private VBox affineTransformToHBox(String transform, int index, boolean removable) {
    VBox row = new VBox();
    HBox content = new HBox();

    HBox titleBox = new HBox();
    Label title = new Label("Transform: ");
    String[] split = transform.split(",");

    TextField a00 = createNumberField(split[0]);
    TextField a01 = createNumberField(split[1]);
    TextField a10 = createNumberField(split[2]);
    TextField a11 = createNumberField(split[3]);
    TextField a = createNumberField(split[4]);
    TextField b = createNumberField(split[5]);

    titleBox.setAlignment(Pos.CENTER);
    content.setAlignment(Pos.CENTER);

    ChangeListener<String> listener = (observable, oldValue, newValue) -> {
      if (a00.getText().isEmpty() || a01.getText().isEmpty() || a10.getText().isEmpty()
          || a11.getText().isEmpty() || a.getText().isEmpty() || b.getText().isEmpty()) {
        return;
      }
      modifyDescriptionController.setAffineTransforms(index, a00.getText(), a01.getText(),
          a10.getText(), a11.getText(), a.getText(), b.getText());
    };

    a00.textProperty().addListener(listener);
    a01.textProperty().addListener(listener);
    a10.textProperty().addListener(listener);
    a11.textProperty().addListener(listener);
    a.textProperty().addListener(listener);
    b.textProperty().addListener(listener);

    VBox matrixCol1 = new VBox();
    VBox matrixCol2 = new VBox();
    matrixCol1.getChildren().addAll(a00, a10);
    matrixCol2.getChildren().addAll(a01, a11);

    Label plus = new Label("+");

    VBox vector = new VBox();
    vector.getChildren().addAll(a, b);

    content.getChildren().addAll(matrixCol1, matrixCol2, plus, vector);

    if (removable) {
      Button removeTransform = new Button("Remove Transform");
      removeTransform.setOnAction(
          event -> modifyDescriptionController.removeAffineTransform(index));
      removeTransform.setStyle("-fx-font-size: 10px; -fx-background-color: #cb7f7f; width: 90px;");

      content.getChildren().add(removeTransform);
    }

    matrixCol1.setStyle("-fx-spacing: 8px;");
    matrixCol2.setStyle("-fx-spacing: 8px;");
    vector.setStyle("-fx-spacing: 8px;");
    content.setStyle("-fx-spacing:8px;");
    plus.setStyle("-fx-font-size: 20px;");

    titleBox.getChildren().add(title);
    row.getChildren().addAll(titleBox, content);

    return row;
  }

  private VBox juliaTransformToHBox(String transform, int index, boolean removable) {
    VBox row = new VBox();
    HBox content = new HBox();

    HBox titleBox = new HBox();
    Label title = new Label("Transform: ");
    String[] split = transform.split(",");

    TextField real = createNumberField(split[0]);
    TextField imaginary = createNumberField(split[1]);

    titleBox.setAlignment(Pos.CENTER);
    content.setAlignment(Pos.CENTER);

    content.getChildren().addAll(real, imaginary);

    if (removable) {
      Button removeTransform = new Button("Remove Transform");
      removeTransform.setOnAction(event -> modifyDescriptionController.removeJuliaTransform(index));
      removeTransform.setStyle("-fx-font-size: 10px; -fx-background-color: #cb7f7f; width: 90px;");
      content.getChildren().add(removeTransform);
    }
    content.setStyle("-fx-spacing: 10px;");

    ChangeListener<String> listener = (observable, oldValue, newValue) -> {
      if (real.getText().isEmpty() || imaginary.getText().isEmpty()) {
        return;
      }
      modifyDescriptionController.setJuliaTransforms(index, real.getText(), imaginary.getText());
    };

    real.textProperty().addListener(listener);
    imaginary.textProperty().addListener(listener);

    titleBox.getChildren().add(title);
    row.getChildren().addAll(titleBox, content);
    return row;
  }

  /**
   * Method for updating the description list. It updates based on the current description.
   */
  public void updateDescriptionList() {
    if (editDescription == null || !editDescription.getChildren().contains(descriptionList)) {return;}
    editDescription.getChildren().remove(descriptionList);
    descriptionList = createDescriptionList();
    editDescription.getChildren().add(descriptionList);
  }

  /**
   * Method for creating a text field that only accepts numbers and periods.
   * @return the created text field.
   */
  private TextField createNumberField(String text) {
    TextField field = new TextField();
    field.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
      if (!event.getCharacter().matches("[0-9.]")) {
        event.consume();
      }
    });
    field.setText(text.trim());
    field.setStyle("-fx-pref-width: 80px;");

    return field;
  }


}

