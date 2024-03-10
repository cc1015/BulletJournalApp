package cs3500.pa05.view;

import cs3500.pa05.controller.DialogController;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.stage.Window;

/**
 * A view for a pop-up dialog
 */
public class DialogView {
  @FXML
  private FXMLLoader loader;
  @FXML
  private DialogPane root;
  @FXML
  private Dialog dialog;

  public boolean open;

  /**
   * Instantiates a new dialog view
   *
   * @param controller the controller for this view
   * @param filename the file for this view
   */
  public DialogView(DialogController controller, String filename) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource(filename));
    this.dialog = new Dialog<>();
    Window window = dialog.getDialogPane().getScene().getWindow();
    window.setOnCloseRequest(e -> window.hide());
    try {
      this.root = loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Cannot load");
    }
    this.open = false;
  }

  /**
   * Displays the current pop-up dialog
   */
  public void show() {
    this.dialog.setDialogPane(root);
    this.dialog.showAndWait();
    this.open = true;
  }

  /**
   * Closes this dialog.
   */
  public void close() {
    dialog.setResult(ButtonType.CLOSE);
    this.dialog.close();
    this.open = false;
  }

  /**
   * @return the dialog pane for this view
   */
  public DialogPane getDialogPane() {
    return this.root;
  }
}