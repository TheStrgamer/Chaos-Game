package org.example.view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
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
  private final CheckBox autoRunOnDescriptionChange;

  private final List<VBox> extraElements = new ArrayList<>();

  private HBox topBar;
  private VBox sideBar;
  private HBox zoomHBox;

  private Button zoomInButton;
  private Button zoomOutButton;
  private Button moveLeftButton;
  private Button moveRightButton;
  private Button moveUpButton;
  private Button moveDownButton;
  private Button runButton;
  private Button clearButton;
  private Button randomJulia;
  private Button randomAffine;
  private Button toModifyDescription;

  private VBox iterationsLayout;


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
    autoRunOnDescriptionChange = new CheckBox();
    initializeAutoRunOnDescriptionChange();

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

    zoomHBox = new HBox();
    fillZoomHBox();
    TitledPane zoomPane = new TitledPane("Zoom", zoomHBox);

    AnchorPane imageViewHBox = new AnchorPane();
    AnchorPane.setRightAnchor(sideBar, 0.0);
    AnchorPane.setLeftAnchor(zoomPane, 0.0);

    imageViewHBox.getChildren().addAll(this.imageView, sideBar, zoomPane);
    layout.getChildren().addAll(topBar, imageViewHBox);

    imageViewHBox.getStyleClass().add("imageView");
    return layout;
  }

  /**
   * Fills the zoom HBox with buttons that control the zoom and movement of the image.
   */
  private void fillZoomHBox() {
    zoomInButton = createButton("+", event -> chaosGameController.changeZoom(-0.1),
        "zoomButton");
    zoomOutButton = createButton("-", event -> chaosGameController.changeZoom(0.1),
        "zoomButton");
    moveLeftButton = createButton("←", event -> chaosGameController.moveCanvas(5, 0),
        "moveButtonLeftRight");
    moveRightButton = createButton("→", event -> chaosGameController.moveCanvas(-5, 0),
        "moveButtonLeftRight");
    moveUpButton = createButton("↑", event -> chaosGameController.moveCanvas(0, -5),
        "moveButton");
    moveDownButton = createButton("↓", event -> chaosGameController.moveCanvas(0, 5),
        "moveButton");
    Button moveUpLeftButton = createButton("↖", event -> chaosGameController.moveCanvas(5, -5),
        "moveButton");
    Button moveUpRightButton = createButton("↗", event -> chaosGameController.moveCanvas(-5, -5),
        "moveButton");
    Button moveDownLeftButton = createButton("↙", event -> chaosGameController.moveCanvas(5, 5),
        "moveButton");
    Button moveDownRightButton = createButton("↘", event -> chaosGameController.moveCanvas(-5, 5),
        "moveButton");

    zoomHBox.getStyleClass().add("zoomHBox");

    VBox leftColumn = new VBox(moveUpLeftButton, moveLeftButton, moveDownLeftButton);
    VBox centerColumn = new VBox(moveUpButton, zoomInButton, zoomOutButton, moveDownButton);
    VBox rightColumn = new VBox(moveUpRightButton, moveRightButton, moveDownRightButton);

    zoomHBox.getChildren().addAll(leftColumn, centerColumn, rightColumn);


  }

  /**
   * Fills the top bar with default elements. These elements are always in the top bar, regardless
   * of the width of the window.
   */
  private void fillTopBarDefaultElements() {

    Label juliaSetModeLabel = new Label("Julia set Mode:");
    juliaSetModeLabel.getStyleClass().add("juliaSetModeLabel");
    CheckBox juliaSetMode = new CheckBox();
    juliaSetMode.setTooltip(new Tooltip("Toggle weather julia sets are ran as julia set or chaos game"));
    juliaSetMode.setSelected(chaosGameController.getJuliaSetMode());
    juliaSetMode.setOnAction(event -> {
      chaosGameController.setJuliaSetMode(juliaSetMode.isSelected());
      fillIterationsLayout();
    });

    iterationsLayout = new VBox();
    fillIterationsLayout();

    runButton = createButton("Run", event -> chaosGameController.runIterations());

    clearButton = createButton("Clear", event -> chaosGameController.clearCanvas());

    Label autoRunLabel = new Label("Auto Run:");

    Label colorLabel = new Label("Color:");
    ColorPicker colorPicker = new ColorPicker();
    colorPicker.setOnAction(event -> chaosGameController.setColor(colorPicker.getValue()));
    colorPicker.setValue(chaosGameController.getColor());

    topBar.getChildren()
        .addAll(juliaSetModeLabel, juliaSetMode, iterationsLayout, runButton, clearButton,
            autoRunLabel, autoRunOnDescriptionChange,
            descriptionComboBox, colorLabel, colorPicker
        );

    // Tooltips
    Tooltip autoRunTooltip = new Tooltip(
        "Automatically run the Chaos Game when the description is changed.");
    autoRunOnDescriptionChange.setTooltip(autoRunTooltip);
    Tooltip iterationsTooltip = new Tooltip("The number of iterations to run the Chaos Game.");
    iterationsField.setTooltip(iterationsTooltip);

    Tooltip colorTooltip = new Tooltip("The color of the Chaos Game fractal.");
    colorPicker.setTooltip(colorTooltip);

  }

  /**
   * Fills the extra UI elements with buttons that can be in topBar, or in sideBar, depending on the
   * width of the window.
   */
  public void createExtraUiElements() {

    randomJulia = createButton("Random Julia", event -> {
      mainController.setCurrentDescription("JuliaRandom");
      setComboBoxEmpty();
    }, "randomButton");
    randomAffine = createButton("Random Affine", event -> {
      mainController.setCurrentDescription("AffineRandom");
      setComboBoxEmpty();
    }, "randomButton");
    VBox randomButtonLayout = new VBox(randomJulia, randomAffine);
    randomJulia.getStyleClass().add("randomButton");
    randomAffine.getStyleClass().add("randomButton");

    toModifyDescription = createButton("Modify Description",
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
    VBox newJuliaAffine = new VBox(newJulia, newAffine);
    newAffine.getStyleClass().add("newButton");
    newJulia.getStyleClass().add("newButton");

    Button saveDescription = createButton("Save Description",
        event -> mainController.saveToFile(), "saveLoadButton");
    Button loadDescription = createButton("Load Description",
        event -> mainController.readFromFile(), "saveLoadButton");

    VBox saveLoadLayout = new VBox(saveDescription, loadDescription);

    Button saveImage = createButton("Save Image", event -> mainController.saveImageToFile());

    Button burgerMenu = createButton("☰", event -> sideBar.setVisible(!sideBar.isVisible()),
        "burgerMenuButton");

    extraElements.add(randomButtonLayout);
    extraElements.add(new VBox(toModifyDescription));
    extraElements.add(newJuliaAffine);
    extraElements.add(saveLoadLayout);
    extraElements.add(new VBox(saveImage));
    extraElements.add(new VBox(burgerMenu));

    // Tooltips
    Tooltip randomJuliaTooltip = new Tooltip("Generate a random Julia fractal.");
    randomJulia.setTooltip(randomJuliaTooltip);

    Tooltip randomAffineTooltip = new Tooltip("Generate a random Affine fractal.");
    randomAffine.setTooltip(randomAffineTooltip);

    Tooltip saveDescriptionTooltip = new Tooltip("Save the current description to file.");
    saveDescription.setTooltip(saveDescriptionTooltip);

    Tooltip loadDescriptionTooltip = new Tooltip("Load a description from file.");
    loadDescription.setTooltip(loadDescriptionTooltip);

    Tooltip saveImageTooltip = new Tooltip("Save the current image to file.");
    saveImage.setTooltip(saveImageTooltip);


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
            "Flower", "Snake", "Spine");
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
    iterationsField.textProperty().addListener(
        (observable, oldValue, newValue) -> chaosGameController.setSteps(
            iterationsField.getText()));
    chaosGameController.setSteps(iterationsField.getText());
  }

  /**
   * Creates a layout for the iterations field.
   */
  private void fillIterationsLayout() {

    iterationsLayout.getChildren().clear();

    if (chaosGameController.getJuliaSetMode()) {
      HBox maxIterationsHBox = new HBox();

      Label maxIterationsLabel = new Label("Max iterations: 255");
      Slider maxIterationsSlider = new Slider(1, 755, 255);
      maxIterationsSlider.valueProperty().addListener(
          (observable, oldValue, newValue) -> {
            chaosGameController.setMaxIterations(
                (int) maxIterationsSlider.getValue());
            maxIterationsLabel.setText("Max iterations: " + (int) maxIterationsSlider.getValue());
          });

      maxIterationsLabel.getStyleClass().add("juliaSetLabel");
      maxIterationsHBox.getChildren().addAll(maxIterationsLabel,
          maxIterationsSlider);

      HBox escapeRadiusHBox = new HBox();
      Label escapeRadiusLabel = new Label("Escape radius 3.0:");

      Slider escapeRadiusSlider = new Slider(0, 10, 2);
      escapeRadiusSlider.valueProperty().addListener(
          (observable, oldValue, newValue) -> {
            chaosGameController.setEscapeRadius(
                escapeRadiusSlider.getValue());
            escapeRadiusLabel.setText("Escape radius: " + Math.round(escapeRadiusSlider.getValue()*100)/100);
          });

      escapeRadiusLabel.getStyleClass().add("juliaSetLabel");

      escapeRadiusHBox.getChildren().addAll(escapeRadiusLabel,
          escapeRadiusSlider);

      iterationsLayout.getChildren().addAll(maxIterationsHBox, escapeRadiusHBox);
    } else {

      HBox iterationsHBox = new HBox();
      iterationsHBox.getChildren().addAll(new Label("Steps:"),
          iterationsField);
      iterationsLayout.getChildren().add(iterationsHBox);
    }
  }

  /**
   * Initializes the auto run on description change checkbox.
   */
  private void initializeAutoRunOnDescriptionChange() {
    autoRunOnDescriptionChange.setSelected(true);
    chaosGameController.setAutoRun(true);
    autoRunOnDescriptionChange.setOnAction(
        event -> chaosGameController.setAutoRun(autoRunOnDescriptionChange.isSelected()));
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
   * Creates a button with the given text and event handler.
   *
   * @param text         the text of the button.
   * @param eventHandler the event handler of the button.
   * @param styleClass   the style class of the button.
   * @return the created button.
   */
  private Button createButton(String text, EventHandler<ActionEvent> eventHandler,
      String styleClass) {
    Button button = new Button(text);
    button.setOnAction(eventHandler);
    button.getStyleClass().add(styleClass);
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
    int defaultElementsCount = 8;
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

  /**
   * Sets the key listeners for core buttons in the Chaos Game page.
   *
   * @param scene the scene to set the key listeners on.
   */
  public void setKeyListeners(Scene scene) {
    scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
      switch (event.getCode()) {
        case PLUS -> zoomInButton.fire();
        case MINUS -> zoomOutButton.fire();
        case A -> moveLeftButton.fire();
        case D -> moveRightButton.fire();
        case W -> moveUpButton.fire();
        case S -> moveDownButton.fire();
        case R -> runButton.fire();
        case C -> clearButton.fire();
        case M -> toModifyDescription.fire();
        case J -> randomJulia.fire();
        case K -> randomAffine.fire();
      }
    });
  }

}
