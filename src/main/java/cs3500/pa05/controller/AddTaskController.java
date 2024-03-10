package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.IndexComparator;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.TaskStatus;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.DialogView;
import cs3500.pa05.view.ViewUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Represents a controller for the add task screen in a journal session
 */
public class AddTaskController implements DialogController {
  @FXML
  private MenuButton selectDay;
  @FXML
  private TextField enterTaskName;
  @FXML
  private TextField enterDescription;
  @FXML
  private CheckBox completionStatus;
  @FXML
  private Button submitTask;
  private Scanner scan;
  private Week currentWeek;
  private String userDaySelection;
  private DialogView view;
  private Map<String, ObservableList> dayTasks;
  private ListView<Node> queue;
  private JournalController masterController;

  /**
   * @param week the current week
   * @param dayTasks the tasks for this week mapped to their respective days
   * @param queue menu items for the task queue
   * @param master the master controller for this journaling session
   */
  public AddTaskController(Week week, Map<String, ObservableList> dayTasks, ListView<Node> queue,
                           JournalController master) {
    this.currentWeek = week;
    this.scan = new Scanner(System.in);
    this.view = new DialogView(this, "createTask.fxml");
    this.dayTasks = dayTasks;
    this.queue = queue;
    this.masterController = master;
  }

  /**
   * @param t the new task
   * @param queue menu items for the task queue
   * @param dayName the day this task is being added to
   * @param masterController the master controller for the journaling session
   * @param dayTasks the tasks for this week mapped to their respective days
   * @param week the current week
   */
  public AddTaskController(Task t, String dayName, JournalController masterController,
                           ListView<Node> queue, Map<String, ObservableList> dayTasks, Week week) {
    this.currentWeek = week;
    this.view = new DialogView(this, "createTask.fxml");
    this.queue = queue;

    this.selectDay.setText(dayName);
    this.userDaySelection = dayName;
    this.enterTaskName.setText(t.getName());
    this.enterDescription.setText(t.getDescription());
    this.enterDescription.setText(Integer.toString(t.getDuration()));
    this.dayTasks = dayTasks;
    this.masterController = masterController;

    this.handleInput(this.enterTaskName, this.enterTaskName);
  }

  /**
   * @throws IllegalStateException if the run fails
   */
  @Override
  public void run() throws IllegalStateException {
    this.setEventHandlers();
    this.view.show();
  }

  /**
   * Edits the current event
   */
  private void editTask(String prevDay, VBox displayedTask, VBox displayedTaskQueue, Task task) {
    this.submitTask.setOnAction(e -> {
      this.dayTasks.get(prevDay).remove(displayedTask);
      this.queue.getItems().remove(displayedTaskQueue);
      this.currentWeek.removeTask(task, prevDay);
      handleInput(this.enterTaskName, this.enterDescription);
    });
    this.view.show();
  }

  /**
   * Initializes the scene buttons
   */
  private void setEventHandlers() {
    Day[] days = this.currentWeek.getDays();
    for (Day day : days) {
      MenuItem item = new MenuItem(day.getName());
      item.setOnAction(event -> daySelection(item, selectDay));
      selectDay.getItems().add(item);
    }
    selectDay.setOnAction(event -> selectDay.show());
    enterTaskName.setOnAction(event -> userInput(enterTaskName));
    enterDescription.setOnAction(event -> userInput(enterDescription));
    submitTask.setOnAction(event -> handleInput(enterTaskName, enterDescription));
  }

  /**
   * @param item the currently selected menu item
   * @return the text within this menu item
   */
  private void daySelection(MenuItem item, MenuButton menuButton) {
    String day = item.getText();
    menuButton.setText(day);
    userDaySelection = day;
  }

  /**
   * Takes in user text input
   *
   * @param field the text field being written on
   */
  private void userInput(TextField field) {
    field.setText(scan.nextLine());
  }

  /**
   * @param name        contains the new task name
   * @param description contains the new task descprition
   * @return the user text input
   */
  private void handleInput(TextField name, TextField description) {
    Task newTask = null;
    List<String> desc = extractUrl(description.getText());
    Map<String, Boolean> descMap = isLink(desc);
    if (this.completionStatus.selectedProperty().get()) {
      newTask = new Task(name.getText(), description.getText(), TaskStatus.COMPLETE);
    } else {
      newTask = new Task(name.getText(), description.getText(), TaskStatus.INCOMPLETE);
    }
    String prevDay = this.userDaySelection;
    VBox displayedTask = ViewUtils.getDisplay(newTask, descMap);
    VBox displayedTaskQueue = ViewUtils.getDisplayQueue(newTask);
    Task finalNewTask = newTask;
    displayedTask.setOnMouseClicked(e ->
        this.editTask(prevDay, displayedTask, displayedTaskQueue, finalNewTask));
    this.dayTasks.get(userDaySelection).add(displayedTask);
    currentWeek.addTask(newTask, userDaySelection);
    this.queue.getItems().add(displayedTaskQueue);
    masterController.updateListView();
    masterController.updateStatsLabels();
    this.view.close();
  }

  /**
   * @param str the string to be read through
   * @return a list containing any http links within the given string
   */
  private List<String> extractUrl(String str) {
    List<String> list = new ArrayList<>();
    String linkPatt = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\"
        + "+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()"
        + "@:%_\\+.~#?&//=]*)";
    Pattern p = Pattern.compile(
        linkPatt, Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(str);
    int idx = 0;
    int lastEnd = 0;
    while (idx < str.length()) {
      while (m.find()) {
        list.add(str.substring(lastEnd, m.start()));
        list.add(str.substring(
            m.start(0), m.end(0)));
        idx += m.end();
        lastEnd = m.end();
      }
      list.add(str.substring(lastEnd));
      break;
    }
    return list;
  }

  /**
   * Maps the strings in the given list to a boolean representing whether
   * the string is a link or not
   *
   * @param list the list of strings to be put into the task description
   * @return maps each string in the given list to a boolean
   */
  private Map<String, Boolean> isLink(List<String> list) {
    Map<String, Boolean> linkMap = new TreeMap<>(new IndexComparator(list));
    for (String s : list) {
      if (s.matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\"
          + "+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()"
          + "@:%_\\+.~#?&//=]*)")) {
        linkMap.put(s, true);
      } else {
        linkMap.put(s, false);
      }
    }
    return linkMap;
  }
}