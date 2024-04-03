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

    //Todo gjør om så den bruker fileHandler fremfor hardkoda verdier
    //kan også begynne på terminal grensesnittet

    Gui gui = new Gui();
    gui.run();

  }
}