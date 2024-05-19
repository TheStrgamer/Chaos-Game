package org.example.controller;
import java.util.Objects;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import org.example.view.PageViewInterface;

/**
 * <h1>PopupController</h1>
 * The controller class for the popup window of the application.
 */
public class PopupController {

  private final Stage popupStage;

  /**
   * Constructor for the PopupController class.
   */
  public PopupController() {
    popupStage = new Stage();
  }

  /**
   * Shows a popup window in the popup stage. Only one popup window can be shown at a time.
   *
   * @param layout the layout of the popup window.
   * @param width the width of the popup window.
   * @param height the height of the popup window.
   */
  public void showPopup(VBox layout, int width, int height) {
    if (popupStage.isShowing()) {
      popupStage.close();
    }
    Scene popupScene = new Scene(layout, width, height);
    setSceneSizeLimit(popupScene, width, (int) (height*0.75));
    popupScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    popupStage.setScene(popupScene);
    popupStage.show();
  }

  /**
   * Shows a popup window in the popup stage. Only one popup window can be shown at a time.
   *
   * @param pageView the page view to show in the popup window.
   * @param width the width of the popup window.
   * @param height the height of the popup window.
   */
  public void showPopup(PageViewInterface pageView, int width, int height) {
    if (popupStage.isShowing()) {
      popupStage.close();
    }
    Scene popupScene = new Scene(pageView.getLayout(), width, height);
    setSceneSizeLimit(popupScene, width, (int) (height*0.75));
    popupScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
    popupStage.setScene(popupScene);
    popupStage.show();
  }

  /**
   * Sets the minimum size of the popup scene.
   *
   * @param scene the scene to set the minimum size for.
   * @param minWidth the minimum width of the scene.
   * @param minHeight the minimum height of the scene.
   */
  private void setSceneSizeLimit(Scene scene, int minWidth, int minHeight) {
    scene.widthProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal.intValue() < minWidth) {
        scene.getWindow().setWidth(minWidth);
      }
    });
    scene.heightProperty().addListener((obs, oldVal, newVal) -> {
      if (newVal.intValue() < minHeight) {
        scene.getWindow().setHeight(minHeight);
      }
    });
  }

  /**
   * Closes the popup window.
   */
  public void closePopup() {
    popupStage.close();
  }

}