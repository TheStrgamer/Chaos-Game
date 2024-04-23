package org.example.controller;

import com.sun.tools.javac.Main;
import javafx.scene.layout.VBox;
import org.example.model.ChaosGame;
import org.example.model.ChaosGameDescription;
import org.example.model.ChaosGameFileHandler;

import java.util.Scanner;
import org.example.model.ImageFactory;
import org.example.view.ChaosGameView;

/**
 * <h1>ChaosGameController</h1>
 * The controller class for the Chaos Game program. it contains the methods that the user can
 * interact with in the terminal.
 */
public class ChaosGameController {

  private ChaosGame chaosGame;
  private final ChaosGameView chaosGameView;

  private final MainController mainController;

  private final ImageFactory imageFactory;

  /**
   * Constructor for the ChaosGameController class.
   */
  public ChaosGameController(MainController mainController, ChaosGame chaosGame) {
    this.mainController = mainController;
    this.chaosGame = chaosGame;
    this.chaosGameView = new ChaosGameView( this, mainController);
    this.imageFactory = new ImageFactory();

  }

  public void runIterations(String iterations) {
    int steps = getIterations(iterations);
    chaosGame.runSteps(steps);
    chaosGameView.setImage(imageFactory.createImage(chaosGame.getCanvas()));
  }

  public void runIterations(int steps) {
    chaosGame.runSteps(steps);
    chaosGameView.setImage(imageFactory.createImage(chaosGame.getCanvas()));
  }

  public int getIterations(String iterations) {
    int defaultIterations = 10000000;
    int minIterations = 1;
    try {
      return Math.max(Integer.parseInt(iterations), minIterations);
    } catch (NumberFormatException e) {
      System.out.println(e.getMessage());
      return defaultIterations;
    }
  }

  public VBox getLayout() {
    return chaosGameView.getLayout();
  }

  public void setChaosGame(ChaosGame chaosGame) {
    this.chaosGame = chaosGame;
  }

  public void refreshImage() {
    chaosGameView.setImage(imageFactory.createImage(chaosGame.getCanvas()));
  }




}
