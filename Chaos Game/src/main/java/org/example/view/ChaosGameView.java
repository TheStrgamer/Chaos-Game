package org.example.view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    imageView.setStyle("-fx-alignment: center;");
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
    System.out.println("I AM BORN");
    VBox layout = new VBox();

    HBox buttonLayout = createTopBarLayout();
    HBox imageViewHBox = new HBox();

    imageViewHBox.getChildren().add(this.imageView);
    layout.getChildren().addAll(buttonLayout, imageViewHBox);

    //Style
    imageViewHBox.setStyle("-fx-alignment: center;");
    return layout;
  }

  /**
   * Method for creating the top bar layout of the Chaos Game page.
   *
   * @return the layout of the Chaos Game page.
   */
  private HBox createTopBarLayout() {

    HBox buttonLayout = new HBox();
    Button runButton = new Button("Run");
    runButton.setOnAction(event -> chaosGameController.runIterations());

    Button clearButton = new Button("Clear");
    clearButton.setOnAction(event -> chaosGameController.clearCanvas());

    CheckBox autoRunOnDescriptionChange = new CheckBox("Auto run ");
    autoRunOnDescriptionChange.setOnAction(event -> chaosGameController.setAutoRun(autoRunOnDescriptionChange.isSelected()));

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

    Button toModifyDescription = new Button("Modify/Save/Load Description");
    toModifyDescription.setOnAction(event -> mainController.switchToDescriptionView());

    buttonLayout.getChildren()
        .addAll(iterationsField, runButton, clearButton, autoRunOnDescriptionChange, descriptionComboBox, randomButtonLayout,
            toModifyDescription);
    randomJulia.setStyle("-fx-pref-width: 125px; -fx-font-size: 10px;");
    randomAffine.setStyle("-fx-pref-width: 125px; -fx-font-size: 10px;");
    buttonLayout.setStyle(
        "-fx-alignment: center; -fx-spacing: 5px; -fx-background-color: #b2b2b2; -fx-padding: 5px; -fx-pref-height: 60px; -fx-border-width: 0 0 2 0; -fx-border-color: #9b9b9b; -fx-border-style: solid;");

    return buttonLayout;
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
            "Flower");
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
    iterationsField.setStyle("-fx-pref-width: 100px;");
    iterationsField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue.matches("\\d*")) {
        iterationsField.setText(oldValue);
      }
      chaosGameController.setSteps(iterationsField.getText());
    });
    chaosGameController.setSteps(iterationsField.getText());
  }


}
