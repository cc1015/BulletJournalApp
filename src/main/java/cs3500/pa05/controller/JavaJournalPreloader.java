package cs3500.pa05.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Represents a java journal preloader screen
 */
public class JavaJournalPreloader {
  private FXMLLoader loader;
  private AnchorPane root;

  /**
   * The constructor
   */
  public JavaJournalPreloader() {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader().getResource("welcomeScreen.fxml"));
    try {
      this.root = this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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