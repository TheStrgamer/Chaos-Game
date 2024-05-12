package org.example.view;

import javafx.beans.value.ChangeListener;
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

  private VBox layout;



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
    this.layout = createLayout();

  }
  /**
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
    HBox buttonLayout = createButtonLayout();
    VBox readAndWrite = createSideBar();


    editDescription = new VBox();
    Label editDescriptionLabel = new Label("Current description: ");
    descriptionList = createDescriptionList();
    editDescription.getChildren().addAll(editDescriptionLabel, descriptionList);

    content.getChildren().addAll(readAndWrite, editDescription);
    layout.getChildren().addAll(buttonLayout, content);

    //Style
    buttonLayout.setStyle(
        "-fx-alignment: center; -fx-spacing: 10px;  -fx-padding: 10px; -fx-background-color: #8f8f8f;");
    editDescription.setStyle("-fx-font-size: 25px;");
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

    if (modifyDescriptionController.getTransformType().equals("Affine")) {
      int index = 0;
      for (String transform : modifyDescriptionController.getTransforms()) {
        VBox affine = affineTransformToHBox(transform, index);
        desctiptionListView.getItems().add(affine);
        index++;
      }
    } else {
      int index = 0;
      for (String transform : modifyDescriptionController.getTransforms()) {
        VBox julia = juliaTransformToHBox(transform, index);
        desctiptionListView.getItems().add(julia);
        index+=2;
      }
    }

    VBox addTransform = new VBox();

    Button addTransformButton = new Button("Add Transform");
    addTransformButton.setOnAction(event -> modifyDescriptionController.addTransform());
    addTransform.getChildren().add(addTransformButton);
    desctiptionListView.getItems().add(addTransform);

    desctiptionListView.setPrefSize(600, 500);
    desctiptionListView.setStyle(
        "-fx-background-color: #bfbfbf; -fx-alignment: center; -fx-spacing: 10px;");

    return desctiptionListView;

  }

  public void changeDescriptionListScale(int width, int height) {
    descriptionList.setPrefSize(width, height);
  }

  private VBox vector2DToHBox(String name, String vector) {
    VBox vBox = new VBox();
    HBox content = new HBox();
    Label label = new Label(name);

    TextField X0 = new TextField();
    TextField Y0 = new TextField();
    String[] split = vector.split(",");
    X0.setText((split[0]));
    Y0.setText(split[1]);

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
          // Handle default case if needed
          break;
      }
    };

    X0.textProperty().addListener(listener);
    Y0.textProperty().addListener(listener);

    content.getChildren().addAll(X0, Y0);
    vBox.getChildren().addAll(label, content);
    return vBox;
  }

  private VBox affineTransformToHBox(String transform, int index) {
    VBox vBox = new VBox();
    HBox content = new HBox();
    Label title = new Label("Transform: ");

    TextField a00 = new TextField();
    TextField a01 = new TextField();
    TextField a10 = new TextField();
    TextField a11 = new TextField();
    TextField a = new TextField();
    TextField b = new TextField();
    String[] split = transform.split(",");
    a00.setText(split[0].trim());
    a01.setText(split[1].trim());
    a10.setText(split[2].trim());
    a11.setText(split[3].trim());
    a.setText(split[4].trim());
    b.setText(split[5].trim());

    a00.setStyle("-fx-pref-width: 80px;");
    a01.setStyle("-fx-pref-width: 80px;");
    a10.setStyle("-fx-pref-width: 80px;");
    a11.setStyle("-fx-pref-width: 80px;");
    a.setStyle("-fx-pref-width: 80px;");
    b.setStyle("-fx-pref-width: 80px;");

    title.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

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
    matrixCol1.getChildren().addAll(a00, a10);
    VBox matrixCol2 = new VBox();
    matrixCol2.getChildren().addAll(a01, a11);

    Label plus = new Label("+");

    VBox vector = new VBox();
    vector.getChildren().addAll(a, b);

    matrixCol1.setStyle("-fx-spacing: 8px;");
    matrixCol2.setStyle("-fx-spacing: 8px;");
    vector.setStyle("-fx-spacing: 8px;");
    content.setStyle("-fx-spacing:8px;");
    plus.setStyle("-fx-font-size: 20px;");

    content.getChildren().addAll(matrixCol1, matrixCol2, plus, vector);
    vBox.getChildren().addAll(title, content);

    return vBox;
  }

  private VBox juliaTransformToHBox(String transform, int index) {
    VBox vBox = new VBox();
    HBox content = new HBox();
    Label label = new Label("Transform: ");
    TextField real = new TextField();
    TextField imaginary = new TextField();
    String[] split = transform.split(",");
    real.setText(split[0]);
    imaginary.setText(split[1]);

    real.setStyle("-fx-pref-width: 80px;");
    imaginary.setStyle("-fx-pref-width: 80px;");
    content.setStyle("-fx-spacing: 10px;");

    ChangeListener<String> listener = (observable, oldValue, newValue) -> {
      if (real.getText().isEmpty() || imaginary.getText().isEmpty()) {
        return;
      }
      modifyDescriptionController.setJuliaTransforms(index, real.getText(), imaginary.getText());
    };

    real.textProperty().addListener(listener);
    imaginary.textProperty().addListener(listener);

    content.getChildren().addAll(real, imaginary);
    vBox.getChildren().addAll(label, content);
    return vBox;
  }

  public void updateDescriptionList() {
    editDescription.getChildren().remove(descriptionList);
    descriptionList = createDescriptionList();
    editDescription.getChildren().add(descriptionList);
  }



}

