package org.example.controller;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class PopupController {

  private final Stage popupStage;

  public PopupController() {
    popupStage = new Stage();
  }

  public void showPopup(VBox layout, int width, int height) {
    if (popupStage.isShowing()) {
      popupStage.close();
    }
    Scene popupScene = new Scene(layout, width, height);
    popupScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    popupStage.setScene(popupScene);
    popupStage.show();
  }

}
