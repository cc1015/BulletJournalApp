package cs3500.pa05.view;

import cs3500.pa05.controller.JournalController;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * The master view for java journal
 */
public class JournalView {
  private FXMLLoader loader;
  private AnchorPane root;
  private Theme theme;
  private String mainFile;
  private JournalController controller;

  /**
   * Instantiates a new view
   *
   * @param controller The controller
   */
  public JournalView(JournalController controller, String filename) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource(filename));
    try {
      this.root = this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void loadSplash() {
    PauseTransition pt = new PauseTransition(Duration.millis(1000));
    pt.setOnFinished(e -> init());
    pt.play();
  }

  private void init() {
    this.loader.setLocation(getClass().getClassLoader()
        .getResource(mainFile));
    try {
      this.root = this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    controller.run();
  }

  /**
   * Loads a scene from a java journal GUI layout.
   *
   * @return the layout
   */
  public Scene load() {
    Scene scene = new Scene(this.root);
    return scene;
  }
}
