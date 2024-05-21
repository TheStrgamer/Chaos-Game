package org.example.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.example.model.chaosgame.ChaosGameDescription;
import org.example.model.chaosgame.ChaosGameFileHandler;

/**
 * <h2>FileController</h2>
 * <p>
 * The controller class for handling file operations in the application. this class has methods
 * for writing and reading chaos game descriptions to and from files through file explorer.
 * </p>
 *
 * @version 0.4.0
 * @since 0.3.0
 */
public class FileController {
  private final ChaosGameFileHandler chaosGameFileHandler;
  private final MainController mainController;
  private final FileChooser fileChooser = new FileChooser();
  private final ExtensionFilter txtExtFilter = new FileChooser
          .ExtensionFilter("Text Files (*.txt)", "*.txt");
  private final ExtensionFilter pngExtFilter = new FileChooser
          .ExtensionFilter("PNG Files (*.png)", "*.png");


  /**
   * Constructor for the FileController class.
   *
   * @param mainController        the main controller for the application. Used for switching
   *                              between views.
   */
  public FileController(MainController mainController) {
    this.chaosGameFileHandler = new ChaosGameFileHandler();
    this.mainController = mainController;
  }

  /**
   * Writes a chaos game description to a file. The description is the current description.
   *
   * @param description the description to write to file.
   */
  public void writeToFile(ChaosGameDescription description) {
    try {
      String path = chooseSavePath();
      if (path == null) {
        return;
      }
      chaosGameFileHandler.writeToFile(description, path);
    } catch (Exception e) {
      mainController.showErrorPopup("An error occurred when saving"
              + " description as a file: \n" + e.getMessage());
    }
  }

  /**
   * Reads a chaos game description from a file, and returns the description.
   * If the file is not found, the method returns null.
   *
   * @return the chaos game description read from the file.
   */
  public ChaosGameDescription readFromFile() {
    try {
      String path = chooseLoadPath();
      if (path == null) {
        return null;
      }
      return chaosGameFileHandler.readFromFile(path);

    } catch (Exception e) {
      mainController.showErrorPopup("An error occurred when trying to"
              + " load a file: \n" + e.getMessage());
      return null;
    }
  }

  /**
   * Method for saving an image to a file.
   *
   * @param image the image to save.
   */
  public void saveImageToFile(Image image) {
    BufferedImage bufferImage = SwingFXUtils.fromFXImage(image, null);
    fileChooser.setTitle("Save image");
    fileChooser.setInitialFileName("image.png");
    fileChooser.getExtensionFilters().clear();
    fileChooser.getExtensionFilters().add(pngExtFilter);
    File file = fileChooser.showSaveDialog(mainController.getStage());
    if (file != null) {
      try {
        javax.imageio.ImageIO.write(bufferImage, "png", file);
      } catch (Exception e) {
        mainController.showErrorPopup("An error occurred when trying to"
                + " save an image: \n" + e.getMessage());
      }
    }

  }

  /**
   * Method for getting the path of a file from a file chooser.
   *
   * @return the path of the file.
   */
  private String chooseSavePath() {
    fileChooser.setTitle("Save description");
    fileChooser.setInitialFileName("description.txt");
    fileChooser.getExtensionFilters().clear();
    fileChooser.getExtensionFilters().add(txtExtFilter);
    File file = fileChooser.showSaveDialog(mainController.getStage());
    return getPath(file);
  }

  /**
   * Method for getting the path of a file from a file chooser.
   *
   * @return the path of the file.
   */
  private String chooseLoadPath() {
    fileChooser.setTitle("Load description");
    fileChooser.getExtensionFilters().clear();
    fileChooser.getExtensionFilters().add(txtExtFilter);
    File file = fileChooser.showOpenDialog(mainController.getStage());
    return getPath(file);
  }

  /**
   * Method for getting the path of a file.
   *
   * @param file the file to get the path of.
   * @return the path of the file.
   */
  private String getPath(File file) {
    if (file != null) {
      return file.getAbsolutePath();
    }
    return null;

  }

}
