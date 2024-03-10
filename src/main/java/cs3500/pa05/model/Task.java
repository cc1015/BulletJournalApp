package cs3500.pa05.model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a task in a day.
 */
public class Task extends AbstractPlan {
  private TaskStatus status;

  /**
   * Sets the given name, description, and status to the fields.
   *
   * @param name The String name the name field is set to.
   * @param description The String description the description field is set to.
   * @param status The status determining whether the task is complete or not.
   */
  public Task(String name, String description, TaskStatus status) {
    super(name, description);
    this.status = status;
  }

  /**
   * Convenience constructor, creates an incomplete task
   *
   * @param name the name of this task
   * @param description description of this task
   */
  public Task(String name, String description) {
    super(name, description);
    this.status = TaskStatus.INCOMPLETE;
  }

  /**
   * @return the status of this task
   */
  public TaskStatus getStatus() {
    return this.status;
  }

  /**
   * @return has this task been completed
   */
  public boolean isComplete() {
    return this.status == TaskStatus.COMPLETE;
  }

  /**
   * @return the duration of this plan
   */
  @Override
  public int getDuration() {
    return 0;
  }
}
