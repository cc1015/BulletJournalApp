package cs3500.pa05.view;

import cs3500.pa05.controller.FileController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

/**
 * Represents a view for the select file screen of java journal
 */
public class SelectFileView {
  private final FXMLLoader loader;
  private final AnchorPane root;
  private boolean fileSelected;

  /**
   * @param controller the controller for this screen
   * @param filename the screen file
   */
  public SelectFileView(FileController controller, String filename) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource(filename));
    this.fileSelected = true;
    try {
      this.root = this.loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * @param fileSelected has the user selected a file
   */
  public void setFileSelected(boolean fileSelected) {
    this.fileSelected = fileSelected;
  }

  /**
   * Loads a scene from a java journal GUI layout.
   *
   * @return the layout
   */
  public Scene load() {
    return new Scene(this.root);
  }
}
