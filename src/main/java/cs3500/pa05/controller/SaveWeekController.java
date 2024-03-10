package cs3500.pa05.controller;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.JsonUtils;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.TaskStatus;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.TimeIndication;
import cs3500.pa05.model.TimeJson;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.WeekJson;
import cs3500.pa05.view.DialogView;
import cs3500.pa05.view.Theme;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * Represents a controller for the save week screen in a journal session
 */
public class SaveWeekController implements DialogController {
  @FXML
  Button ok;
  @FXML
  Button cancel;
  @FXML
  TextField fileName;
  DialogView view;
  Week week;

  /**
   * @param week the week being saved
   */
  public SaveWeekController(Week week) {
    this.week = week;
    this.view =  new DialogView(this, "saveFile.fxml");
  }

  /**
   * Initializes the save week screen
   */
  @Override
  public void run() {
    this.cancel.setOnAction(e -> this.view.close());
    this.ok.setOnAction(e -> this.okHandler());
    this.view.show();
  }

  /**
   * Handles events on the ok button
   */
  private void okHandler() {
    WeekJson weekJson = createWeekJson();
    JsonNode weekNode = JsonUtils.serializeRecord(weekJson);
    String fileName = this.fileName.getText();

    String pathName = "bujo/" + fileName + ".bujo";

    try {
      FileWriter writer = new FileWriter(pathName);
      writer.write(weekNode.toString());
      writer.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.view.close();
  }

  /**
   * @return a json representation of the current week
   */
  private WeekJson createWeekJson() {
    Day[] days = this.week.getDays();
    DayJson[] dayJsons = new DayJson[days.length];

    for (int i = 0; i < days.length; i += 1) {
      Day day = days[i];
      String name = day.getName();
      ArrayList<Task> tasks = day.getTasks();
      TaskJson[] taskJsons = this.createTaskJsons(tasks);
      ArrayList<Event> events = day.getEvents();
      EventJson[] eventJsons = this.createEventJsons(events);
      int maxTasks = day.getMaxTasks();
      int maxEvents = day.getMaxEvents();
      DayJson dayJson = new DayJson(name, eventJsons, taskJsons, maxTasks, maxEvents);
      dayJsons[i] = dayJson;
    }

    Theme theme = this.week.getTheme();
    String[] notes = new String[week.getNotes().size()];
    week.getNotes().toArray(notes);
    String[] quotes = new String[week.getQuotes().size()];
    week.getQuotes().toArray(quotes);
    WeekJson weekJson = new WeekJson(dayJsons, theme, notes, quotes);
    return weekJson;
  }

  /**
   * @param tasks the tasks for this week
   * @return a json representation of this week's tasks
   */
  private TaskJson[] createTaskJsons(ArrayList<Task> tasks) {
    TaskJson[] taskJsons = new TaskJson[tasks.size()];
    for (int i = 0; i < tasks.size(); i += 1) {
      Task task = tasks.get(i);
      String name = task.getName();
      String description = task.getDescription();
      TaskStatus status = task.getStatus();

      TaskJson taskJson = new TaskJson(name, description, status);
      taskJsons[i] = taskJson;
    }
    return taskJsons;
  }

  /**
   * @param events the events for this week
   * @return a json representation of the events for this week
   */
  private EventJson[] createEventJsons(ArrayList<Event> events) {
    EventJson[] eventJsons = new EventJson[events.size()];
    for (int i = 0; i < events.size(); i += 1) {
      Event event = events.get(i);
      String name = event.getName();
      String description = event.getDescription();
      Time time = event.getStartTime();
      TimeJson timeJson = this.createTimeJson(time);
      int duration = event.getDuration();

      EventJson eventJson = new EventJson(name, description, timeJson, duration);
      eventJsons[i] = eventJson;
    }
    return eventJsons;
  }

  /**
   * @param time a time for an event
   * @return a json representation of the given time
   */
  private TimeJson createTimeJson(Time time) {
    int hour = time.getHour();
    int minute = time.getMinute();
    TimeIndication indication = time.getIndication();
    TimeJson timeJson = new TimeJson(hour, minute, indication);
    return timeJson;
  }
}
