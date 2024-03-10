package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.SortingFlag;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.Theme;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * The controller for java journal
 */
public class JournalController {
  @FXML
  private Button addEvent;
  @FXML
  private Button addTask;
  @FXML
  private Button saveWeek;
  @FXML
  private Button changeThemeButton;
  @FXML
  private Button setMaxTasksButton;
  @FXML
  private Button settings;
  @FXML
  private ListView<Node> sundayEvents;
  @FXML
  private ListView<Node> mondayEvents;
  @FXML
  private ListView<Node> tuesdayEvents;
  @FXML
  private ListView<Node> wednesdayEvents;
  @FXML
  private ListView<Node> thursdayEvents;
  @FXML
  private ListView<Node> fridayEvents;
  @FXML
  private ListView<Node> saturdayEvents;
  @FXML
  private ListView<Node> sundayTasks;
  @FXML
  private ListView<Node> mondayTasks;
  @FXML
  private ListView<Node> tuesdayTasks;
  @FXML
  private ListView<Node> wednesdayTasks;
  @FXML
  private ListView<Node> thursdayTasks;
  @FXML
  private ListView<Node> fridayTasks;
  @FXML
  private ListView<Node> saturdayTasks;
  @FXML
  private Label addEventLabel;
  @FXML
  private Label addTaskLabel;
  @FXML
  private Label sundayLabel;
  @FXML
  private Label sundayEventsLabel;
  @FXML
  private Label sundayTasksLabel;
  @FXML
  private Label mondayLabel;
  @FXML
  private Label mondayEventsLabel;
  @FXML
  private Label mondayTaskLabel;
  @FXML
  private Label fridayLabel;
  @FXML
  private Label fridayEventsLabel;
  @FXML
  private Label fridayTaskLabel;
  @FXML
  private Label tuesdayLabel;
  @FXML
  private Label tuesdayEventsLabel;
  @FXML
  private Label tuesdayTaskLabel;
  @FXML
  private Label wednesdayLabel;
  @FXML
  private Label wednesdayEventsLabel;
  @FXML
  private Label wednesdayTaskLabel;
  @FXML
  private Label thursdayLabel;
  @FXML
  private Label thursdayEventsLabel;
  @FXML
  private Label thursdayTaskLabel;
  @FXML
  private Label saturdayLabel;
  @FXML
  private Label saturdayEventsLabel;
  @FXML
  private Label saturdayTaskLabel;
  @FXML
  private Label taskQueueLabel;
  private Week week;
  private JournalView view;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private VBox notes;
  @FXML
  private TextField note;
  @FXML
  private Button submitNote;
  @FXML
  private Pane quotes;
  @FXML
  private TextField quote;
  @FXML
  private Button submitQuote;
  @FXML
  private Label totalNumEvents;
  @FXML
  private Label totalNumTasks;
  @FXML
  private Label percentTasksComplete;
  @FXML
  private ListView<Node> weeklyTasks;

  @FXML
  private ImageView themeImage;
  private final Label[] allLabels;
  private final ListView[] allListViews;
  private final SettingsController settingsController;
  private SortingFlag sortingFlag;

  /**
   * Instantiates a new controller
   *
   * @param week the current week
   */
  public JournalController(Week week) {
    this.week = week;
    this.view = new JournalView(this, "journal.fxml");
    this.settingsController = new SettingsController(this.week, this);
    allLabels = new Label[]{addEventLabel, addTaskLabel, sundayLabel, sundayEventsLabel,
        sundayTasksLabel, mondayLabel, mondayEventsLabel, mondayTaskLabel, tuesdayLabel,
        tuesdayEventsLabel, tuesdayTaskLabel, wednesdayLabel, wednesdayTaskLabel,
        wednesdayEventsLabel, thursdayLabel, thursdayEventsLabel, thursdayTaskLabel, fridayLabel,
        fridayEventsLabel, fridayTaskLabel, saturdayLabel, saturdayEventsLabel, saturdayTaskLabel,
        taskQueueLabel};
    allListViews = new ListView[]{sundayEvents, mondayEvents, tuesdayEvents, wednesdayEvents,
        thursdayEvents, fridayEvents, saturdayEvents, sundayTasks, mondayTasks, tuesdayTasks,
        wednesdayTasks, thursdayTasks, fridayTasks, saturdayTasks, weeklyTasks};
    this.sortingFlag = SortingFlag.DEFAULT;
  }

  /**
   * Delegates to the according theme setting method
   */
  public void setTheme() {
    if (this.week.getTheme() == Theme.DARK_MODE) {
      this.setDark();
    } else if (this.week.getTheme() == Theme.COLORFUL) {
      this.setColorful();
    } else {
      this.setBasic();
    }
  }

  /**
   * Sets week view to the dark theme colors
   */
  private void setDark() {
    anchorPane.setStyle("-fx-background-color: #36454F");
    for (ListView listView : allListViews) {
      listView.setStyle("-fx-background-color: #808080");
    }
    for (Label label : allLabels) {
      label.setTextFill(Paint.valueOf("#FFFFFF"));
    }
    this.themeImage.setImage(new Image("skullSpin.gif"));
  }

  /**
   * Sets the week view theme back to default
   */
  public void setBasic() {
    anchorPane.setStyle("");
    for (ListView listView : allListViews) {
      listView.setStyle("");
    }
    for (Label label : allLabels) {
      label.setTextFill(Paint.valueOf("#000000"));
    }
    this.themeImage.setImage(new Image("explodingSkull.gif"));
  }

  /**
   * Sets week view to colorful theme colors
   */
  private void setColorful() {
    anchorPane.setStyle("-fx-background-color: #ADD8E6");
    sundayEvents.setStyle("-fx-background-color: #FF0000");
    sundayTasks.setStyle("-fx-background-color: #FF0000");
    mondayEvents.setStyle("-fx-background-color: #FF7F00");
    mondayTasks.setStyle("-fx-background-color: #FF7F00");
    tuesdayEvents.setStyle("-fx-background-color: #FFFF00");
    tuesdayTasks.setStyle("-fx-background-color: #FFFF00");
    wednesdayEvents.setStyle("-fx-background-color: #00FF00");
    wednesdayTasks.setStyle("-fx-background-color: #00FF00");
    thursdayEvents.setStyle("-fx-background-color: #0000FF");
    thursdayTasks.setStyle("-fx-background-color: #0000FF");
    fridayEvents.setStyle("-fx-background-color: #4B0082");
    fridayTasks.setStyle("-fx-background-color: #4B0082");
    saturdayEvents.setStyle("-fx-background-color: #9400D3");
    saturdayTasks.setStyle("-fx-background-color: #9400D3");
    for (Label label : allLabels) {
      label.setTextFill(Paint.valueOf("#FFFFFF"));
    }
    this.themeImage.setImage(new Image("skeleDance.gif"));
  }

  /**
   * Initializes the journal to be interacted with by the user
   */
  public void run() {
    this.setTheme();
    this.initEventListView();
    this.initTaskListView();
    this.initNotes();
    this.initQuotes();
    this.settings.setOnAction(e -> this.settingsController.run());
    this.addTask.setOnAction(e -> {
      new AddTaskController(this.week, this.constructTasksMap(), this.weeklyTasks, this).run();
      this.updateStatsLabels();
    });
    this.addEvent.setOnAction(e -> {
      new AddEventController(this.week, this.constructEventsMap(), this).run();
      this.updateStatsLabels();
    });
    this.saveWeek.setOnAction(e -> new SaveWeekController(this.week).run());
    this.submitNote.setOnAction(e -> this.noteHandle());
    this.submitQuote.setOnAction(e -> this.quoteHandle());
    this.updateStatsLabels();
  }

  /**
   * @return the view for this controller
   */
  public JournalView getView() {
    return this.view;
  }

  /**
   * @param flag the new sorting flag for this journal
   */
  public void updateSortingFlag(SortingFlag flag) {
    this.sortingFlag = flag;
  }

  /**
   * Updates the menu items in each list view
   */
  public void updateListView() {
    this.updateListViewSort(this.constructTasksMap(), "TASK");
    this.updateListViewSort(this.constructEventsMap(), "EVENT");
    Collections.sort((List) weeklyTasks.getItems(), new BoxComparator("TASK", this.sortingFlag));
  }

  /**
   * @param map maps the name of a day to the list of items for that day
   * @param planType task or event
   */
  private void updateListViewSort(Map<String, ObservableList> map, String planType) {
    for (ObservableList ol : map.values()) {
      Collections.sort(ol, new BoxComparator(planType, this.sortingFlag));
    }
  }

  /**
   * Constructs a map from the days in the week to the tasks for each day
   *
   * @return the map from day to the list of tasks
   */
  private Map<String, ObservableList> constructTasksMap() {
    Map<String, ObservableList> map = new HashMap<>();
    map.put("Sunday", this.sundayTasks.getItems());
    map.put("Monday", this.mondayTasks.getItems());
    map.put("Tuesday", this.tuesdayTasks.getItems());
    map.put("Wednesday", this.wednesdayTasks.getItems());
    map.put("Thursday", this.thursdayTasks.getItems());
    map.put("Friday", this.fridayTasks.getItems());
    map.put("Saturday", this.saturdayTasks.getItems());
    return map;
  }

  /**
   * Constructs a map from the days in the week to the events for each day
   *
   * @return the map from day to the list of events
   */
  private Map<String, ObservableList> constructEventsMap() {
    HashMap<String, ObservableList> map = new HashMap<>();
    map.put("Sunday", this.sundayEvents.getItems());
    map.put("Monday", this.mondayEvents.getItems());
    map.put("Tuesday", this.tuesdayEvents.getItems());
    map.put("Wednesday", this.wednesdayEvents.getItems());
    map.put("Thursday", this.thursdayEvents.getItems());
    map.put("Friday", this.fridayEvents.getItems());
    map.put("Saturday", this.saturdayEvents.getItems());
    return map;
  }


  /**
   * Initializes the task list views when loading a week
   */
  private void initTaskListView() {
    Day[] days = this.week.getDays();
    for (Day d : days) {
      for (Task t : d.getTasks()) {
        this.week.removeTask(t, d.getName());
        new AddTaskController(t, d.getName(), this, this.weeklyTasks,
            this.constructTasksMap(), this.week);
      }
    }
  }

  /**
   * Initializes the events list views when loading a week
   */
  private void initEventListView() {
    Day[] days = this.week.getDays();
    for (Day d : days) {
      for (Event e : d.getEvents()) {
        this.week.removeEvent(e, d.getName());
        new AddEventController(e, d.getName(), this,
            this.constructEventsMap(), this.week);
      }
    }
  }


  /**
   * Handles adding a note to the week
   */
  private void noteHandle() {
    String note = this.note.getText();
    Label label = new Label(note);
    this.notes.getChildren().add(label);
    this.week.addNote(note);
  }

  /**
   * Handles adding a quote to the week
   */
  private void quoteHandle() {
    String quote = this.quote.getText();
    Label label = new Label(quote);
    this.quotes.getChildren().add(label);
    this.week.addQuote(quote);
  }

  /**
   * Initializes the notes when loading a week
   */
  private void initNotes() {
    ArrayList<Node> labels = new ArrayList<>();
    for (String notes : this.week.getNotes()) {
      Label label = new Label(notes);
      labels.add(label);
    }
    this.notes.getChildren().addAll(labels);
    this.notes.setStyle("-fx-background-color: #FF00");
  }

  /**
   * Initializes the quotes when loading a week
   */
  private void initQuotes() {
    ArrayList<Node> labels = new ArrayList<>();
    for (String quote : this.week.getQuotes()) {
      Label label = new Label(quote);
      labels.add(label);
    }
    this.quotes.getChildren().addAll(labels);
  }

  /**
   * Updates the stats labels in the weekly overview
   */
  public void updateStatsLabels() {
    this.totalNumTasks.setText(Integer.toString(this.week.getNumTasks()));
    this.totalNumEvents.setText(Integer.toString(this.week.getNumEvents()));
    try {
      this.percentTasksComplete.setText(Double.toString(this.week.getPercentComplete()));
    } catch (NumberFormatException e) {
      this.percentTasksComplete.setText("N/A");
    }
  }
}