package cs3500.pa05.model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents an event in a day.
 */
public class Event extends AbstractPlan {

  private final Time startTime;
  private final int duration;

  /**
   * Sets the given name, description, time and duration to the fields.
   *
   * @param name The String name the name field is set to.
   * @param description The String description the description field is set to.
   * @param time The time the startTime field is set to.
   * @param duration The event duration the duration field is set to (in minutes).
   */
  public Event(String name, String description, Time time, int duration) {
    super(name, description);
    this.startTime = time;
    this.duration = duration;
  }

  /**
   * @return the duration of this event
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * @return the start time for this event
   */
  public Time getStartTime() {
    return this.startTime;
  }
}
