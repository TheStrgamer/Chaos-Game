package org.example.view;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import org.example.controller.MainController;
import org.example.controller.ChaosGameController;
import org.example.view.components.WeightAndIterationsField;

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

  private final List<VBox> extraElements = new ArrayList<>();

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
    iterationsField = new WeightAndIterationsField("1000000");
    initializeIterationsField();
    layout = createLayout();
    createExtraUiElements();

  }

  /**
   * Creates the layout of the Chaos Game page. The layout consists of a top bar, an image view, and
   * a sidebar that can be toggled on or off.
   *
   * @return the layout of the Chaos Game page.
   */
  private VBox createLayout() {
    VBox layout = new VBox();
    layout.getStyleClass().add("chaosGameLayout");

    sideBar = new VBox();
    sideBar.getStyleClass().add("sideBar");
    sideBar.setVisible(false);
    topBar = new HBox();
    topBar.getStyleClass().add("topBar");
    fillTopBarDefaultElements();

    AnchorPane imageViewHBox = new AnchorPane();
    AnchorPane.setRightAnchor(sideBar, 0.0);

    imageViewHBox.getChildren().addAll(this.imageView, sideBar);
    layout.getChildren().addAll(topBar, imageViewHBox);

    imageViewHBox.getStyleClass().add("imageView");
    return layout;
  }

  /**
   * Fills the top bar with default elements. These elements are always in the top bar, regardless
   * of the width of the window.
   */
  private void fillTopBarDefaultElements() {

    Button runButton = createButton("Run", event -> chaosGameController.runIterations());

    Button clearButton = createButton("Clear", event -> chaosGameController.clearCanvas());

    Label autoRunLabel = new Label("Auto Run:");
    CheckBox autoRunOnDescriptionChange = new CheckBox();
    autoRunOnDescriptionChange.setOnAction(
        event -> chaosGameController.setAutoRun(autoRunOnDescriptionChange.isSelected()));


    Label colorLabel = new Label("Color:");
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setOnAction(event -> chaosGameController.setColor(colorPicker.getValue()));
    colorPicker.setValue(chaosGameController.getColor());

    topBar.getChildren()
        .addAll(iterationsField, runButton, clearButton, autoRunLabel, autoRunOnDescriptionChange,
            descriptionComboBox, colorLabel, colorPicker
            );
  }

  /**
   * Fills the extra UI elements with buttons that can be in topBar, or in sideBar, depending on the
   * width of the window.
   */
  public void createExtraUiElements() {

    Button randomJulia = createButton("Random Julia", event -> {
      mainController.setCurrentDescription("JuliaRandom");
      setComboBoxEmpty();
    });
    Button randomAffine = createButton("Random Affine", event -> {
      mainController.setCurrentDescription("AffineRandom");
      setComboBoxEmpty();
    });
    VBox randomButtonLayout = new VBox(randomJulia, randomAffine);
    randomJulia.getStyleClass().add("randomButton");
    randomAffine.getStyleClass().add("randomButton");

    Button toModifyDescription = createButton("Modify Description",
        event -> mainController.openModifyPopup());

    Button newAffine = createButton("New Affine", event -> {
      mainController.setCurrentDescription("EmptyAffine");
      setComboBoxEmpty();
      mainController.openModifyPopup();
    });
    Button newJulia = createButton("New Julia", event -> {
      mainController.setCurrentDescription("EmptyJulia");
      setComboBoxEmpty();
      mainController.openModifyPopup();
    });
    VBox newJuliaAffine = new VBox(newAffine, newJulia);
    newAffine.getStyleClass().add("newButton");
    newJulia.getStyleClass().add("newButton");

    Button saveDescription = new Button("Save Description");
    Button loadDescription = new Button("Load Description");

    VBox saveLoadLayout = new VBox(saveDescription, loadDescription);
    saveDescription.getStyleClass().add("saveLoadButton");
    loadDescription.getStyleClass().add("saveLoadButton");

    Button saveImage = new Button("Save Image");

    Button burgerMenu = createButton("☰", event -> sideBar.setVisible(!sideBar.isVisible()));
    burgerMenu.getStyleClass().add("burgerMenuButton");

    extraElements.add(randomButtonLayout);
    extraElements.add(new VBox(toModifyDescription));
    extraElements.add(newJuliaAffine);
    extraElements.add(saveLoadLayout);
    extraElements.add(new VBox(saveImage));
    extraElements.add(new VBox(burgerMenu));

  }

  /**
   * Returns the layout of the Chaos Game page.
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
   * Sets the image of the Chaos Game page.
   *
   * @param image the image to set.
   */
  public void setImage(Image image) {
    imageView.setImage(image);
  }

  /**
   * Sets the combo box to empty.
   */
  public void setComboBoxEmpty() {
    descriptionComboBox.setValue(null);
  }

  /**
   * Initializes the combo box.
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
   * Initializes the iterations field.
   */
  private void initializeIterationsField() {
    iterationsField.setPromptText("Iterations");
    iterationsField.getStyleClass().add("iterationsField");
    iterationsField.textProperty().addListener((observable, oldValue, newValue) -> chaosGameController.setSteps(iterationsField.getText()));
    chaosGameController.setSteps(iterationsField.getText());
  }

  /**
   * Creates a button with the given text and event handler.
   *
   * @param text         the text of the button.
   * @param eventHandler the event handler of the button.
   * @return the created button.
   */
  private Button createButton(String text, EventHandler<ActionEvent> eventHandler) {
    Button button = new Button(text);
    button.setOnAction(eventHandler);
    return button;
  }

  /**
   * Adjusts which buttons are in the top bar and which are in the sidebar depending on the width of
   * the window.
   *
   * @param width the width of the window.
   */
  public void adjustButtonLayout(int width) {
    clearBars();
    fillTopBarDefaultElements();
    sideBar.setVisible(false);

    int widthPerElement = 90;
    int defaultElementsCount = 7;
    boolean shouldAddSideBar = false;

    for (int i = 0; i < extraElements.size() - 1; i++) {
      if (shouldAddToTopBar(i, width, widthPerElement, defaultElementsCount)) {
        topBar.getChildren().add(extraElements.get(i));
      } else {
        shouldAddSideBar = true;
        sideBar.getChildren().add(extraElements.get(i));
      }
    }
    if (shouldAddSideBar) {
      topBar.getChildren().add(extraElements.get(extraElements.size() - 1));
    }
  }

  /**
   * Clears the top bar and the sidebar.
   */
  private void clearBars() {
    topBar.getChildren().clear();
    sideBar.getChildren().clear();
  }

  /**
   * Checks if an element should be added to the top bar. If not, it should be added to the
   * sidebar.
   *
   * @param index                the index of the element
   * @param width                the width of the window
   * @param widthPerElement      the width per element
   * @param defaultElementsCount the number of default elements in the top bar
   * @return true if the element should be added to the top bar, false otherwise
   */
  private boolean shouldAddToTopBar(int index, int width, int widthPerElement,
      int defaultElementsCount) {
    return widthPerElement * (index + defaultElementsCount + 1) < width;
  }


}
