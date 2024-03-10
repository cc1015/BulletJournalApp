package cs3500.pa05.model;

import cs3500.pa05.view.Theme;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a single week in the journal
 */
public class Week {
  private Day[] days;
  private List<Task> tasks;
  private Theme theme;
  private ArrayList<String> notes;
  private ArrayList<String> quotes;
  private SortingFlag sortingFlag;

  /**
   * Instantiates a new week with the given days
   *
   * @param days The String name the name field is set to.
   */
  public Week(Day[] days) {
    this.days = days;
    this.tasks = new ArrayList<>();
    this.theme = Theme.BASIC;
    this.notes = new ArrayList<>();
    this.quotes = new ArrayList<>();
    this.sortingFlag = SortingFlag.DEFAULT;
  }


  /**
   * @return the standard week
   */
  public static Day[] standardWeek() {
    Day sun = new Day(WeekDay.SUNDAY);
    Day mon = new Day(WeekDay.MONDAY);
    Day tues = new Day(WeekDay.TUESDAY);
    Day wed = new Day(WeekDay.WEDNESDAY);
    Day thurs = new Day(WeekDay.THURSDAY);
    Day fri = new Day(WeekDay.FRIDAY);
    Day sat = new Day(WeekDay.SATURDAY);
    Day[] week = new Day[]{sun, mon, tues, wed, thurs, fri, sat};
    return week;
  }

  /**
   * Adds an task to the specified day
   *
   * @param t adds the given task for this week
   * @param day the day for the task to be added
   */
  public void addTask(Task t, String day) {
    boolean validDay = false;
    for (Day d : this.days) {
      if (d.getName().equals(day)) {
        validDay = true;
        d.addTask(t);
        this.tasks.add(t);
        Collections.sort(this.tasks, new PlanComparator(this.sortingFlag));
        break;
      }
    }
    if (!validDay) {
      throw new IllegalArgumentException("Invalid day");
    }
  }

  /**
   * Adds an event to the specified day
   *
   * @param e adds the given event for this week
   * @param day the day for the event to be added
   */
  public void addEvent(Event e, String day) {
    boolean validDay = false;
    for (Day d : this.days) {
      if (d.getName().equals(day)) {
        validDay = true;
        d.addEvent(e);
      }
    }
    if (!validDay) {
      throw new IllegalArgumentException("Invalid day");
    }
  }

  /**
   * @return the days in this week
   */
  public Day[] getDays() {
    return this.days;
  }

  /**
   * Updates the theme of this week
   *
   * @param theme the theme to update to
   */
  public void updateTheme(Theme theme) {
    this.theme = theme;
  }

  public Theme getTheme() {
    return this.theme;
  }

  /**
   * Updates the max number of tasks
   *
   * @param numTasks the theme to update to
   */
  public void updateTasks(int numTasks) {
    for (Day d : this.days) {
      d.setMaxTasks(numTasks);
    }
  }

  /**
   * Updates the theme of this week
   *
   * @param numEvents the theme to update to
   */
  public void updateEvents(int numEvents) {
    for (Day d : this.days) {
      d.setMaxEvents(numEvents);
    }
  }

  /**
   * Sets the days for this week to the supplied days
   *
   * @param days the days to set
   */
  public void setDays(Day[] days) {
    this.days = days;
  }

  /**
   * @return all the notes for this week
   */
  public ArrayList<String> getNotes() {
    return this.notes;
  }

  /**
   * @return all the quotes for this week
   */
  public ArrayList<String> getQuotes() {
    return this.quotes;
  }

  /**
   * Adds a note to this week
   *
   * @param note the note to be added
   */
  public void addNote(String note) {
    this.notes.add(note);
  }

  /**
   * Adds a quote to this week
   *
   * @param quote the quote to be added
   */
  public void addQuote(String quote) {
    this.quotes.add(quote);
  }

  /**
   * Gets the total number of tasks for the week
   *
   * @return the number of tasks
   */
  public int getNumTasks() {
    int tasks = 0;
    for (Day d : this.days) {
      tasks += d.getNumTasks();
    }
    return tasks;
  }

  /**
   * Gets the total number of events for the week
   *
   * @return the number of events
   */
  public int getNumEvents() {
    int events = 0;
    for (Day d : this.days) {
      events += d.getNumEvents();
    }
    return events;
  }

  /**
   * Gets the total number of tasks completed for the week
   *
   * @return the number of completed tasks
   */
  public int getTasksCompleted() {
    int tasksComplete = 0;
    for (Day d : this.days) {
      tasksComplete += d.getTasksComplete();
    }
    return tasksComplete;
  }

  /**
   * Gets the percentage of tasks completed for this week
   *
   * @return the percentage rounded to the nearest .1
   */
  public double getPercentComplete() {
    BigDecimal roundedValue =
        new BigDecimal(((double) getTasksCompleted() / (double) getNumTasks()) * 100.0);
    roundedValue.setScale(3, RoundingMode.UP);
    return roundedValue.doubleValue();
  }

  /**
   * @param e the event to be removed
   * @param day the day of the event
   */
  public void removeEvent(Event e, String day) {
    boolean removed = false;
    for (Day d : this.days) {
      if (d.getName().equals(day)) {
        d.getEvents().remove(e);
        removed = true;
      }
    }
    if (!removed) {
      throw new IllegalArgumentException("Invalid event or day.");
    }
  }

  /**
   * @param t the task to be removed
   * @param day the day of the task
   */
  public void removeTask(Task t, String day) {
    boolean removed = false;
    for (Day d : this.days) {
      if (d.getName().equals(day)) {
        d.getTasks().remove(t);
        removed = true;
        this.tasks.remove(t);
      }
    }
    if (!removed) {
      throw new IllegalArgumentException("Invalid task or day.");
    }
  }
}