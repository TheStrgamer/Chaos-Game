package org.example.view;


import java.util.ArrayList;
import java.util.List;
import org.example.model.AffineTransform2D;
import org.example.model.ChaosGame;
import org.example.model.ChaosGameDescription;
import org.example.model.Matrix2x2;
import org.example.model.Transform2D;
import org.example.model.Vector2D;

public class Main {

  public static void main(String[] args) {

    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0)));
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0)));
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5)));
    ChaosGameDescription description = new ChaosGameDescription(new Vector2D(0,0), new Vector2D(1,1),transforms );
    ChaosGame chaosGame = new ChaosGame(description, 60,60);
    chaosGame.runSteps(10000);
    System.out.println(chaosGame.getCanvas().toString());

  }
}