package org.example.model.observer;

import org.example.model.chaosGame.ChaosCanvas;
import org.example.model.chaosGame.ChaosGameDescription;

public interface ChaosGameObserver {

  void updateDescription(ChaosGameDescription description);

  void updateCanvas(ChaosCanvas canvas);

}
