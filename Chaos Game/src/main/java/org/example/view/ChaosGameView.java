package org.example.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import org.example.controller.MainController;
import org.example.controller.ChaosGameController;

/**
 * <h1>ChaosGameView</h1>
 * The view class for the Chaos Game page of the application. Responsible for displaying the Chaos
 * Game page. Implements the PageView interface.
 */
public class ChaosGameView implements PageViewInterface {

  private final ChaosGameController chaosGameController;
  private final MainController mainController;
  private VBox layout;

  private final ImageView imageView;
  private final TextField iterationsField;

  private final ComboBox<String> descriptionComboBox;

  private HBox topBar;
  private VBox sideBar;


  /**
   * Constructor for the ChaosGameView class.
   *
   * @param chaosGameController the controller for the Chaos Game page.
   * @param mainController      the main controller for the application.
   */
  public ChaosGameView(ChaosGameController chaosGameController, MainController mainController) {
    this.chaosGameController = chaosGameController;
    this.mainController = mainController;
    this.imageView = new ImageView();
    this.descriptionComboBox = new ComboBox<>();
    initializeComboBox();
    iterationsField = new TextField();
    initializeIterationsField();
    layout = createLayout();

  }

  /**
   * Method for creating the layout of the Chaos Game page.
   *
   * @return the layout of the Chaos Game page.
   */
  private VBox createLayout() {
    VBox layout = new VBox();

    sideBar = createSideBar();
    sideBar.setVisible(false);
    topBar = createTopBarLayout();

    AnchorPane imageViewHBox = new AnchorPane();
    AnchorPane.setRightAnchor(sideBar, 0.0);

    imageViewHBox.getChildren().addAll(this.imageView, sideBar);
    layout.getChildren().addAll(topBar, imageViewHBox);

    imageViewHBox.getStyleClass().add("imageView");
    return layout;
  }

  /**
   * Method for creating the bottom bar layout of the Chaos Game page.
   *
   * @return the layout of the Chaos Game page.
   */
  private VBox createSideBar() {

    Button toModifyDescription = new Button("Modify Description");
    toModifyDescription.setOnAction(event -> mainController.switchToDescriptionView());

    Button newAffine = new Button("New Affine");
    newAffine.setOnAction(event -> {
      mainController.setCurrentDescription("EmptyAffine");
      setComboBoxEmpty();
      mainController.switchToDescriptionView();
    });
    Button newJulia = new Button("New Julia");
    newJulia.setOnAction(event -> {
      mainController.setCurrentDescription("EmptyJulia");
      setComboBoxEmpty();
      mainController.switchToDescriptionView();
    });

    Button saveDescription = new Button("Save Description");
    Button loadDescription = new Button("Load Description");
    Button saveImage = new Button("Save Image");
    VBox bottomBar = new VBox(toModifyDescription, newAffine, newJulia, saveDescription,
        loadDescription, saveImage);
    bottomBar.getStyleClass().add("sideBar");
    return bottomBar;
  }

  /**
   * Method for creating the top bar layout of the Chaos Game page.
   *
   * @return the layout of the Chaos Game page.
   */
  private HBox createTopBarLayout() {

    HBox topBar = new HBox();
    Button runButton = new Button("Run");
    runButton.setOnAction(event -> chaosGameController.runIterations());

    Button clearButton = new Button("Clear");
    clearButton.setOnAction(event -> chaosGameController.clearCanvas());

    CheckBox autoRunOnDescriptionChange = new CheckBox("Auto run ");
    autoRunOnDescriptionChange.setOnAction(
        event -> chaosGameController.setAutoRun(autoRunOnDescriptionChange.isSelected()));

    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setOnAction(event -> chaosGameController.setColor(colorPicker.getValue()));
    colorPicker.setValue(javafx.scene.paint.Color.BLACK);

    VBox randomButtonLayout = new VBox();
    Button randomJulia = new Button("Random Julia Set");
    randomJulia.setOnAction(
        event -> {
          mainController.setCurrentDescription("JuliaRandom");
          setComboBoxEmpty();
        });
    Button randomAffine = new Button("Random Affine Set");
    randomAffine.setOnAction(
        event -> {
          mainController.setCurrentDescription("AffineRandom");
          setComboBoxEmpty();
        });

    randomButtonLayout.getChildren().addAll(randomJulia, randomAffine);

    Button burgerMenu = new Button("☰");
    burgerMenu.setOnAction(event -> sideBar.setVisible(!sideBar.isVisible()));
    burgerMenu.getStyleClass().add("burgerMenuButton");

    topBar.getChildren()
        .addAll(iterationsField, runButton, clearButton, colorPicker, autoRunOnDescriptionChange,
            descriptionComboBox, randomButtonLayout, burgerMenu);

    randomJulia.getStyleClass().add("randomButton");
    randomAffine.getStyleClass().add("randomButton");
    topBar.getStyleClass().add("topBar");

    return topBar;
  }

  /**
   * Method for getting the layout of the Chaos Game page.
   *
   * @return the layout of the Chaos Game page.
   */
  public VBox getLayout() {
    if (layout == null) {
      layout = createLayout();
    }
    return layout;
  }

  /**
   * Method for setting the image of the Chaos Game page.
   *
   * @param image the image to set.
   */
  public void setImage(Image image) {
    imageView.setImage(image);
  }

  /**
   * Method for setting the combo box to empty.
   */
  public void setComboBoxEmpty() {
    descriptionComboBox.setValue(null);
  }

  /**
   * Method for initializing the combo box.
   */
  private void initializeComboBox() {
    descriptionComboBox.setValue("Sierpinski");
    descriptionComboBox.getItems()
        .addAll("Sierpinski", "Barnsley", "Julia", "Julia2", "Julia3", "Diamond", "Plant",
            "Flower", "Snake");
    descriptionComboBox.setOnAction(
        event -> {
          if (descriptionComboBox.getValue() != null) {
            mainController.setCurrentDescription(descriptionComboBox.getValue());
          }
        });

  }

  /**
   * Method for initializing the iterations field.
   */
  private void initializeIterationsField() {
    iterationsField.setPromptText("Iterations");
    iterationsField.setText("1000000");
    iterationsField.getStyleClass().add("iterationsField");
    iterationsField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        iterationsField.setText(oldValue);
      }
      chaosGameController.setSteps(iterationsField.getText());
    });
    chaosGameController.setSteps(iterationsField.getText());
  }


}
