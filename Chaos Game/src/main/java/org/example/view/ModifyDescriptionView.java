package org.example.view;

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
 * The view class for the Modify Description page of the application.
 * Responsible for displaying the Modify Description page.
 * Implements the PageView interface.
 */
public class ModifyDescriptionView implements PageViewInterface {

  private final ModifyDescriptionController modifyDescriptionController;
  private final MainController mainController;

  private ListView<VBox> descriptionList = new ListView<>();

  private VBox layout;



  /**
   * Constructor for the ModifyDescriptionView class.
   *
   * @param modifyDescriptionController the controller for the Modify Description page.
   * @param mainController             the main controller for the application.
   */
  public ModifyDescriptionView(ModifyDescriptionController modifyDescriptionController,
      MainController mainController) {
    this.modifyDescriptionController = modifyDescriptionController;
    this.mainController = mainController;
    this.layout = createLayout();

  }

  /**
   * Method for creating the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  private VBox createLayout() {
    VBox layout = new VBox();
    HBox buttonLayout = new HBox();
    Button toChaosGame = new Button("To Chaos Game");

    HBox content = new HBox();
    VBox readAndWrite = new VBox();

    Label descriptionLabel = new Label("Description: ");
    Button readFromFile = new Button("Read description from file");
    Button saveToFile = new Button("Save current description");
    readAndWrite.getChildren().addAll(descriptionLabel, readFromFile, saveToFile);

    readFromFile.setOnAction(event -> modifyDescriptionController.readFromFile());
    saveToFile.setOnAction(event -> modifyDescriptionController.saveToFile());
    toChaosGame.setOnAction(event -> mainController.switchToChaosGameView());

    VBox description = new VBox();
    Label editDescription = new Label("Current description: ");
    descriptionList = createDescriptionList();
    description.getChildren().addAll(editDescription, descriptionList);

    content.getChildren().addAll(readAndWrite, description);
    content.getChildren().addAll(readAndWrite);


    buttonLayout.getChildren().addAll(toChaosGame);
    layout.getChildren().addAll(buttonLayout, content);

    //Style
    buttonLayout.setStyle(
        "-fx-alignment: center; -fx-spacing: 10px;  -fx-padding: 10px; -fx-background-color: #8f8f8f;");
    editDescription.setStyle("-fx-font-size: 25px;");
    readAndWrite.setStyle(" -fx-spacing: 10px; -fx-background-color: #8f8f8f; -fx-padding: 10px;");
    description.setStyle("-fx-alignment: center; -fx-spacing: 10px; -fx-background-color: #bfbfbf;");

    return layout;
  }

  /**
   * Method for getting the layout of the Modify Description page.
   *
   * @return the layout of the Modify Description page.
   */
  public VBox getLayout() {
    return createLayout();
  }


  private ListView<VBox> createDescriptionList() {
    ListView<VBox> desctiptionListView = new ListView<>();

    VBox minCoords = vector2DToHBox("Min Coords: ", modifyDescriptionController.getMinCoords());
    VBox maxCoords = vector2DToHBox("Max Coords: ", modifyDescriptionController.getMaxCoords());

    desctiptionListView.getItems().addAll(minCoords, maxCoords);

    if (modifyDescriptionController.getTransformType().equals("Affine")) {
      for (String transform : modifyDescriptionController.getTransforms()) {
        VBox affine = affineTransformToHBox(transform);
        desctiptionListView.getItems().add(affine);
      }
    } else {
      for (String transform : modifyDescriptionController.getTransforms()) {
        VBox vector = vector2DToHBox("Transform: ", transform);
        desctiptionListView.getItems().add(vector);
      }
    }

    VBox addTransform = new VBox();
    Button addTransformButton = new Button("Add Transform");
    addTransformButton.setOnAction(event -> modifyDescriptionController.addTransform());
    addTransform.getChildren().add(addTransformButton);
    desctiptionListView.getItems().add(addTransform);

    desctiptionListView.setPrefSize(600, 500);
    desctiptionListView.setStyle("-fx-background-color: #bfbfbf; -fx-alignment: center; -fx-spacing: 10px;");


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
    X0.setText(split[0]);
    Y0.setText(split[1]);

    X0.setStyle("-fx-pref-width: 80px;");
    Y0.setStyle("-fx-pref-width: 80px;");
    content.setStyle("-fx-spacing: 10px;");

    content.getChildren().addAll( X0, Y0);
    vBox.getChildren().addAll(label, content);
    return vBox;
  }

  private VBox affineTransformToHBox(String transform) {
    VBox vBox = new VBox();
    HBox content = new HBox();
    Label title = new Label("Transform: ");

    TextField a = new TextField();
    TextField b = new TextField();
    TextField c = new TextField();
    TextField d = new TextField();
    TextField e = new TextField();
    TextField f = new TextField();
    String[] split = transform.split(",");
    a.setText(split[0].trim());
    b.setText(split[1].trim());
    c.setText(split[2].trim());
    d.setText(split[3].trim());
    e.setText(split[4].trim());
    f.setText(split[5].trim());

    a.setStyle("-fx-pref-width: 80px;");
    b.setStyle("-fx-pref-width: 80px;");
    c.setStyle("-fx-pref-width: 80px;");
    d.setStyle("-fx-pref-width: 80px;");
    e.setStyle("-fx-pref-width: 80px;");
    f.setStyle("-fx-pref-width: 80px;");

    title.setAlignment(javafx.geometry.Pos.CENTER_LEFT);

    VBox matrixCol1 = new VBox();
    matrixCol1.getChildren().addAll(a, c);
    VBox matrixCol2 = new VBox();
    matrixCol2.getChildren().addAll(b, d);

    Label plus = new Label("+");

    VBox vector = new VBox();
    vector.getChildren().addAll(e, f);

    matrixCol1.setStyle("-fx-spacing: 8px;");
    matrixCol2.setStyle("-fx-spacing: 8px;");
    vector.setStyle("-fx-spacing: 8px;");
    content.setStyle("-fx-spacing:8px;");
    plus.setStyle("-fx-font-size: 20px;");

    content.getChildren().addAll(matrixCol1, matrixCol2, plus, vector);
    vBox.getChildren().addAll(title, content);

    return vBox;
  }

  public void updateDescriptionList() {
    descriptionList = createDescriptionList();
  }
}

