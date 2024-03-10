package cs3500.pa05.view;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Useful view methods
 */
public class ViewUtils {
  /**
   * Formats this task into a displayable vbox
   *
   * @param event the event being displayed
   * @param descStrings the descprtion mapped to whether it is a link
   * @return the vbox containing the information for this event
   */
  public static VBox getDisplay(Event event, Map<String, Boolean> descStrings) {
    Label name = new Label(event.getName());
    Label time = new Label("Starts: " + event.getStartTime().toString());
    Label duration = new Label("Lasts: " + event.getDuration() + " minutes");
    VBox box = new VBox();
    box.getChildren().add(name);
    box.getChildren().add(time);
    box.getChildren().add(duration);

    for (String s : descStrings.keySet()) {
      if (descStrings.get(s)) {
        Hyperlink link = new Hyperlink(s);
        link.setOnAction(e -> {
          try {
            Desktop.getDesktop().browse(new URL(link.getText()).toURI());
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
          }
        });
        box.getChildren().add(link);
      } else {
        box.getChildren().add(new Label(s));
      }
    }
    return box;
  }

  /**
   * Formats this task into a displayable vbox
   *
   * @param task the task being displayed
   * @param descStrings the strings in the task descrption mapped to whether they are a link or not
   * @return the vbox containing the information for this task
   */
  public static VBox getDisplay(Task task, Map<String, Boolean> descStrings) {
    Label name = new Label(task.getName());
    VBox box = new VBox();
    box.getChildren().add(name);
    for (String s : descStrings.keySet()) {
      if (descStrings.get(s)) {
        Hyperlink link = new Hyperlink(s);
        link.setOnAction(e -> {
          try {
            Desktop.getDesktop().browse(new URL(link.getText()).toURI());
          } catch (IOException ex) {
            throw new RuntimeException(ex);
          } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
          }
        });
        box.getChildren().add(link);
      } else {
        box.getChildren().add(new Label(s));
      }
    }
    return box;
  }

  /**
   * Formats this task into a displayable hbox for the task queue
   *
   * @param task the task being displayed
   * @return the hbox containing the task info for the queue
   */
  public static VBox getDisplayQueue(Task task) {
    Label name = new Label(task.getName());

    VBox box = new VBox();

    box.getChildren().add(name);
    if (task.isComplete()) {
      box.getChildren().add(new Rectangle(15, 15, Color.GREEN));
    } else {
      box.getChildren().add(new Rectangle(15, 15, Color.RED));
    }
    return box;
  }
}
