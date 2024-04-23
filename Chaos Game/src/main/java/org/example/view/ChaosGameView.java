package org.example.view;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;
import org.example.controller.MainController;
import org.example.model.ChaosGame;
import org.example.controller.ChaosGameController;
public class ChaosGameView implements PageViewInterface {
  private final ChaosGameController chaosGameController;
  private final MainController mainController;

  private VBox layout;
  private ImageView imageView;
  private TextField iterationsField;

  public ChaosGameView(ChaosGameController chaosGameController, MainController mainController) {
    this.chaosGameController = chaosGameController;
    this.mainController = mainController;
    this.imageView = new ImageView();
    imageView.setStyle("-fx-alignment: center;");
    this.layout = createLayout();

  }

  private VBox createLayout() {
    VBox layout = new VBox();
    HBox buttonLayout = new HBox();
    iterationsField = new TextField();
    iterationsField.setPromptText("Iterations");
    Button runButton = new Button("Run");

    runButton.setOnAction(event -> {
      chaosGameController.runIterations(iterationsField.getText());
    });


    Button toModifyDescription = new Button("Change Description");
    toModifyDescription.setOnAction(event -> mainController.switchToDescriptionView());

    buttonLayout.getChildren().addAll(iterationsField, runButton, toModifyDescription);
    layout.getChildren().addAll(buttonLayout, imageView);

    //Style
    buttonLayout.setStyle("-fx-alignment: center; -fx-spacing: 10px; -fx-background-color: #8f8f8f; -fx-padding: 10px;");


    return layout;
  }

  public VBox getLayout() {
    return createLayout();
  }
  public void setImage(Image image) {
    imageView.setImage(image);
  }


}
