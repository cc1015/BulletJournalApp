package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.Driver;
import cs3500.pa05.model.BujoCollector;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayJson;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.EventJson;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskJson;
import cs3500.pa05.model.TaskStatus;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.TimeIndication;
import cs3500.pa05.model.TimeJson;
import cs3500.pa05.model.Week;
import cs3500.pa05.model.WeekDay;
import cs3500.pa05.model.WeekJson;
import cs3500.pa05.view.DialogView;
import cs3500.pa05.view.SelectFileView;
import cs3500.pa05.view.Theme;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * Represents a controller for java journal file selection/week creation screen
 */
public class FileController {
  @FXML
  private MenuButton fileSelector;
  @FXML
  private Button confirm;
  @FXML
  private Button makeNewWeek;
  private DialogView view;
  private SelectFileView sfView;
  private Week week;
  private boolean fileSelected;

  /**
   * @param week the current week
   */
  public FileController(Week week) {
    this.week = week;
    this.sfView = new SelectFileView(this, "selectFile.fxml");
    this.fileSelected = false;
  }

  /**
   * @return the view for this controller
   */
  public SelectFileView getSfView() {
    return this.sfView;
  }

  /**
   * @param st the stage currently being displayed on
   */
  public void run(Stage st) {
    this.makeNewWeek.setOnAction(e -> {
      st.close();
      Driver.startMainApp(new Stage(), this.week);
    });
    this.confirm.setOnAction(e -> this.confirmHandler(st));
    ArrayList<String> files = new ArrayList<>();
    FileVisitor visitor = new BujoCollector(files);
    Path path = Path.of("bujo");
    try {
      Files.walkFileTree(path, visitor);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    this.constructFileItems(files);
  }

  /**
   * @param files a list of previously saved .bujo files
   */
  private void constructFileItems(ArrayList<String> files) {
    ObservableList<MenuItem> items = this.fileSelector.getItems();
    for (String file : files) {
      MenuItem newItem = new MenuItem(file);
      newItem.setOnAction(e -> fileHandler(file));
      items.add(newItem);
    }
  }

  /**
   * @param file the desired .bujo file
   */
  private void fileHandler(String file) {
    this.fileSelector.setText(file);
  }

  /**
   * handles confirm button events
   */
  private void confirmHandler(Stage st) {
    String selectedFile = this.fileSelector.getText();

    String pathName = "bujo/" + selectedFile;

    Path path = Path.of(pathName);

    File file = new File(path.toString());

    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonParser parser = mapper.getFactory().createParser(file);
      WeekJson week = parser.readValueAs(WeekJson.class);
      createWeek(week);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    st.close();
    Driver.startMainApp(new Stage(), this.week);
    this.sfView.setFileSelected(false);
  }

  /**
   * @return the current week
   */
  public Week getWeek() {
    return this.week;
  }

  /**
   * @param weekJson a week from a saved .bujo file
   */
  private void createWeek(WeekJson weekJson) {
    DayJson[] dayJsons = weekJson.days();
    Day[] days = new Day[dayJsons.length];

    for (int i = 0; i < days.length; i += 1) {
      DayJson dayJson = dayJsons[i];
      String name = dayJson.name();
      EventJson[] eventJsons = dayJson.events();
      ArrayList<Event> events = this.createEvents(eventJsons);
      TaskJson[] taskJsons = dayJson.tasks();
      ArrayList<Task> tasks = this.createTasks(taskJsons);
      int maxTasks = dayJson.maxTasks();
      int maxEvents = dayJson.maxEvents();

      Day day = new Day(WeekDay.getCorrectDay(name), tasks, events, maxTasks, maxEvents);
      days[i] = day;
    }

    String[] notes = weekJson.notes();
    this.week.getNotes().addAll(Arrays.asList(notes));

    String[] quotes = weekJson.quotes();
    this.week.getQuotes().addAll(Arrays.asList(quotes));

    Theme theme = weekJson.theme();
    this.week.updateTheme(theme);

    this.week.setDays(days);
  }

  /**
   * @param eventJsons events from a saved .bujo file
   * @return the given json events as a list of event objects
   */
  private ArrayList<Event> createEvents(EventJson[] eventJsons) {
    ArrayList<Event> events = new ArrayList<>();

    for (int i = 0; i < eventJsons.length; i += 1) {
      EventJson eventJson = eventJsons[i];
      String name = eventJson.name();
      String description = eventJson.description();
      int duration = eventJson.duration();

      TimeJson timeJson = eventJson.startTime();
      int hour = timeJson.hour();
      int minute = timeJson.minute();
      TimeIndication indication = timeJson.indication();
      Time time = new Time(hour, minute, indication);

      Event event = new Event(name, description, time, duration);
      events.add(event);
    }

    return events;
  }

  /**
   * @param taskJsons tasks from a saved .bujo file
   * @return the given json tasks as a list of task objects
   */
  private ArrayList<Task> createTasks(TaskJson[] taskJsons) {
    ArrayList<Task> tasks = new ArrayList<>();

    for (int i = 0; i < taskJsons.length; i += 1) {
      TaskJson taskJson = taskJsons[i];
      String name = taskJson.name();
      String description = taskJson.description();
      TaskStatus status = taskJson.status();

      Task task = new Task(name, description, status);
      tasks.add(task);
    }

    return tasks;
  }
}
