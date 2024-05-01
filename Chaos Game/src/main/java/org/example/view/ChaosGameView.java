package org.example.view;

import javafx.scene.control.Button;
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
  private ImageView imageView;
  private TextField iterationsField;

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
    imageView.setStyle("-fx-alignment: center;");
    this.layout = createLayout();

  }

  /**
   * Method for creating the layout of the Chaos Game page.
   *
   * @return the layout of the Chaos Game page.
   */
  private VBox createLayout() {
    VBox layout = new VBox();
    HBox buttonLayout = new HBox();
    iterationsField = new TextField();
    iterationsField.setPromptText("Iterations");
    iterationsField.setText("1000000");
    Button runButton = new Button("Run");

    runButton.setOnAction(event -> chaosGameController.runIterations(iterationsField.getText()));

    ComboBox<String> descriptionComboBox = new ComboBox<>();
    descriptionComboBox.setValue("Barnsley");
    descriptionComboBox.getItems()
        .addAll("Barnsley", "Sierpinski", "Julia", "Julia2", "Julia3", "Julia4", "Grain", "Snowflake", "Wave", "Diamond");
    descriptionComboBox.setOnAction(
        event -> mainController.setCurrentDescription(descriptionComboBox.getValue()));

    Button randomJulia = new Button("Random Julia Set");
    randomJulia.setOnAction(
        event -> {
          mainController.setCurrentDescription("JuliaRandom");
          chaosGameController.runIterations(iterationsField.getText());
        });

    Button randomAffine = new Button("Random Affine Set");
    randomAffine.setOnAction(
        event -> {
          mainController.setCurrentDescription("AffineRandom");
          chaosGameController.runIterations(iterationsField.getText());
        });



    Button toModifyDescription = new Button("Modify/Save/Load Description");
    toModifyDescription.setOnAction(event -> mainController.switchToDescriptionView());

    buttonLayout.getChildren()
        .addAll(iterationsField, runButton, descriptionComboBox, randomJulia, randomAffine, toModifyDescription);
    layout.getChildren().addAll(buttonLayout, imageView);

    //Style
    buttonLayout.setStyle(
        "-fx-alignment: center; -fx-spacing: 10px; -fx-background-color: #8f8f8f; -fx-padding: 10px;");

    return layout;
  }

  /**
   * Method for getting the layout of the Chaos Game page.
   *
   * @return the layout of the Chaos Game page.
   */
  public VBox getLayout() {
    return createLayout();
  }

  /**
   * Method for setting the image of the Chaos Game page.
   *
   * @param image the image to set.
   */
  public void setImage(Image image) {
    imageView.setImage(image);
  }


}
