package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.IndexComparator;
import cs3500.pa05.model.Time;
import cs3500.pa05.model.TimeIndication;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.DialogView;
import cs3500.pa05.view.ViewUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Represents a controller for the add event functionality.
 */
public class AddEventController implements DialogController {
  @FXML
  private TextField enterName;
  @FXML
  private MenuButton selectDay;
  @FXML
  private TextField enterDescription;
  @FXML
  private MenuButton hour;
  @FXML
  private MenuButton minute;
  @FXML
  private MenuButton amPm;
  @FXML
  private MenuItem am;
  @FXML
  private MenuItem pm;
  @FXML
  private TextField enterDuration;
  @FXML
  private Button submit;
  @FXML
  private Button cancel;
  private final Week week;
  private final DialogView view;
  private int hourSelection;
  private int minuteSelection;
  private TimeIndication indication;
  private String daySelection;
  private Map<String, ObservableList> dayEvents;
  private final JournalController masterController;

  /**
   * @param week      the current week
   * @param dayEvents all the events for the week
   * @param master    the main java journal controller
   */
  public AddEventController(Week week, Map<String, ObservableList> dayEvents,
                            JournalController master) {
    this.week = week;
    this.view = new DialogView(this, "createEvent.fxml");
    this.hourSelection = 0;
    this.minuteSelection = 0;
    this.indication = TimeIndication.AM;
    this.dayEvents = dayEvents;
    this.masterController = master;
  }

  /**
   * @param event            the current event
   * @param dayName          the day of the event
   * @param masterController the main java journal controller
   * @param dayEvents        all the events for the week
   * @param week             the current week
   */
  public AddEventController(Event event, String dayName, JournalController masterController,
                            Map<String, ObservableList> dayEvents, Week week) {
    this.week = week;
    this.view = new DialogView(this, "createEvent.fxml");
    this.daySelection = dayName;
    this.selectDay.setText(dayName);

    this.hourSelection = event.getStartTime().getHour();
    this.hour.setText(Integer.toString(hourSelection));
    this.minuteSelection = event.getStartTime().getMinute();
    this.minute.setText(Integer.toString(minuteSelection));
    this.indication = event.getStartTime().getIndication();
    this.amPm.setText(indication.toString());

    this.enterName.setText(event.getName());
    this.enterDescription.setText(event.getDescription());
    this.enterDuration.setText(Integer.toString(event.getDuration()));
    this.dayEvents = dayEvents;
    this.masterController = masterController;
    createDayItems();
    createHourItems();
    createMinuteItems();
    this.cancel.setOnAction(e -> this.view.close());
    this.submitHandle();
  }

  /**
   * Runs the add event dialog and adds a new event per user's inputs.
   */
  @Override
  public void run() {
    createDayItems();
    createHourItems();
    createMinuteItems();

    this.am.setOnAction(e -> amPmHandle(this.am.getText()));
    this.pm.setOnAction(e -> amPmHandle(this.pm.getText()));

    this.submit.setOnAction(e -> submitHandle());

    this.cancel.setOnAction(e -> this.view.close());

    view.show();
  }

  /**
   * Edits the current event
   */
  private void editEvent(String prevDay, VBox displayedEvent, Event event) {
    this.submit.setOnAction(e -> {
      this.dayEvents.get(prevDay).remove(displayedEvent);
      this.week.removeEvent(event, prevDay);
      submitHandle();
    });
    view.show();
  }

  /**
   * Creates the day menu items and sets their on action to the day handler.
   */
  private void createDayItems() {
    ObservableList<MenuItem> items = selectDay.getItems();

    for (Day day : this.week.getDays()) {
      String name = day.getName();
      MenuItem dayItem = new MenuItem(name);
      dayItem.setOnAction(e -> this.dayHandle(dayItem.getText()));
      items.add(dayItem);
    }
  }

  /**
   * Creates the hour menu items and sets their on action to the hour handler.
   */
  private void createHourItems() {
    ObservableList<MenuItem> items = hour.getItems();

    for (int i = 1; i <= 12; i += 1) {
      MenuItem newHour = new MenuItem(Integer.toString(i));
      newHour.setOnAction(e -> hourHandle(newHour.getText()));
      items.add(newHour);
    }
  }

  /**
   * Creates the minute menu items and sets their on action to the minute handler.
   */
  private void createMinuteItems() {
    ObservableList<MenuItem> items = minute.getItems();

    for (int i = 0; i < 60; i += 1) {
      String min = "";
      if (i < 10) {
        min = "0" + i;
      } else {
        min = Integer.toString(i);
      }
      MenuItem newMinute = new MenuItem(min);
      newMinute.setOnAction(e -> minuteHandle(newMinute.getText()));
      items.add(newMinute);
    }
  }

  /**
   * A day handler that sets the day selection to the given day name and changes the day menu button
   * text to display the selection.
   *
   * @param daySelection The selected day name.
   */
  private void dayHandle(String daySelection) {
    this.daySelection = daySelection;
    this.selectDay.setText(daySelection);
  }

  /**
   * An hour handler that sets the hour selection to the given hour and changes the hour menu button
   * text to display the selection.
   *
   * @param hour the starting hour for this event
   */
  private void hourHandle(String hour) {
    this.hourSelection = Integer.parseInt(hour);
    this.hour.setText(hour);
  }

  /**
   * A minute handler that sets the minute selection to the given minute and changes the minute
   * menu button text to display the selection.
   *
   * @param minute the starting minute for this event
   */
  private void minuteHandle(String minute) {
    this.minuteSelection = Integer.parseInt(minute);
    this.minute.setText(minute);
  }

  /**
   * An am/pm handler that sets the isBeforeNoon to true if the given selection is "AM", false if
   * it is "PM", and changes the amPm menu button text to display the selection.
   *
   * @param selection does this event take place in the am or pm
   */
  private void amPmHandle(String selection) {
    if (selection.equals("AM")) {
      this.indication = TimeIndication.AM;
    } else {
      this.indication = TimeIndication.PM;
    }
    this.amPm.setText(selection);
  }

  /**
   * A submit button handler that gather input from the enter name, enter description, enter time,
   * and enter duration prompts that the user has put in and creates a new event. Adds that new
   * event to the user desired day.
   */
  private void submitHandle() {
    String name = this.enterName.getText();
    String description = this.enterDescription.getText();
    int duration = Integer.parseInt(this.enterDuration.getText());

    Time time = new Time(this.hourSelection, this.minuteSelection, this.indication);

    Event event = new Event(name, description, time, duration);

    List<String> desc = extractUrl(enterDescription.getText());
    Map<String, Boolean> descMap = isLink(desc);

    this.week.addEvent(event, selectDay.getText());
    VBox displayedEvent = ViewUtils.getDisplay(event, descMap);
    displayedEvent.setOnMouseClicked(e -> editEvent(selectDay.getText(), displayedEvent, event));
    this.dayEvents.get(daySelection).add(displayedEvent);
    this.masterController.updateListView();
    this.masterController.updateStatsLabels();
    this.view.close();
  }

  /**
   * @return the current week
   */
  public Week getWeek() {
    return this.week;
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