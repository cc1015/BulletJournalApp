package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa05.view.Theme;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Week class.
 */
class WeekTest {

  private Week standardWeek;
  private Day sun;
  private Day mon;
  private Day tues;
  private Day wed;
  private Day thurs;
  private Day fri;
  private Day sat;
  private Day[] days;
  private Time time1;
  private Task task1;
  private Task task2;
  private Task task3;
  private Event event1;

  /**
   * Initializes data.
   */
  @BeforeEach
  public void initData() {
    sun = new Day(WeekDay.SUNDAY);
    mon = new Day(WeekDay.MONDAY);
    tues = new Day(WeekDay.TUESDAY);
    wed = new Day(WeekDay.WEDNESDAY);
    thurs = new Day(WeekDay.THURSDAY);
    fri = new Day(WeekDay.FRIDAY);
    sat = new Day(WeekDay.SATURDAY);

    days = new Day[] {sun, mon, tues, wed, thurs, fri, sat};

    this.standardWeek = new Week(days);

    this.time1 = new Time(11, 30, TimeIndication.AM);

    this.task1 = new Task("task1", "task 1 description", TaskStatus.COMPLETE);
    this.task2 = new Task("task2", "task 2 description", TaskStatus.INCOMPLETE);
    this.task3 = new Task("task3", "task 3 description", TaskStatus.INCOMPLETE);
    this.event1 = new Event("event1", "event 1 description", time1, 20);
  }

  /**
   * Tests for standard week
   */
  @Test
  public void testStandardWeek() {
    Day sun = new Day(WeekDay.SUNDAY);
    Day mon = new Day(WeekDay.MONDAY);
    Day tues = new Day(WeekDay.TUESDAY);
    Day wed = new Day(WeekDay.WEDNESDAY);
    Day thurs = new Day(WeekDay.THURSDAY);
    Day fri = new Day(WeekDay.FRIDAY);
    Day sat = new Day(WeekDay.SATURDAY);
    Day[] week = new Day[]{sun, mon, tues, wed, thurs, fri, sat};

    Day[] testWeek = Week.standardWeek();

    assertEquals(testWeek[0].getName(), week[0].getName());
    assertEquals(testWeek[1].getName(), week[1].getName());
    assertEquals(testWeek[2].getName(), week[2].getName());
    assertEquals(testWeek[3].getName(), week[3].getName());
    assertEquals(testWeek[4].getName(), week[4].getName());
    assertEquals(testWeek[5].getName(), week[5].getName());
    assertEquals(testWeek[6].getName(), week[6].getName());
  }

  /**
   * Checks that addTask correctly adds task to Sunday.
   */
  @Test
  void addTaskValidDay() {
    standardWeek.addTask(task1, "Sunday");
    assertEquals(1, standardWeek.getNumTasks());
  }

  /**
   * Checks that addTask throws error if invalid day is given.
   */
  @Test
  void addTaskInvalidDay() {
    assertThrows(IllegalArgumentException.class,
        () -> standardWeek.addTask(task1, "Invalid"));
  }

  /**
   * Checks that addEvent correctly adds event to Sunday
   */
  @Test
  void addEventValidDay() {
    standardWeek.addEvent(event1, "Sunday");
    assertEquals(1, standardWeek.getNumEvents());
  }

  /**
   * Checks that addEvent throws an error if invalid day is given.
   */
  @Test
  void addEventInvalidDay() {
    assertThrows(IllegalArgumentException.class,
        () -> standardWeek.addEvent(event1, "Invalid"));
  }

  /**
   * Checks that getTasksCompleted returns the correct number of tasks completed.
   */
  @Test
  void tasksCompleted() {
    standardWeek.addTask(task1, "Sunday");
    standardWeek.addTask(task2, "Monday");
    standardWeek.addTask(task3, "Tuesday");
    assertEquals(1, standardWeek.getTasksCompleted());
  }

  /**
   * Checks that getDays returns the correct list of days.
   */
  @Test
  public void testGetDays() {
    assertEquals(this.standardWeek.getDays(), days);
  }

  /**
   * Checks that getTheme returns the correct theme
   */
  @Test
  public void testGetTheme() {
    assertEquals(this.standardWeek.getTheme(), Theme.BASIC);
  }

  /**
   * Checks that updateTheme correctly sets the theme to the given theme.
   */
  @Test
  public void testSetTheme() {
    assertEquals(this.standardWeek.getTheme(), Theme.BASIC);

    this.standardWeek.updateTheme(Theme.COLORFUL);

    assertEquals(this.standardWeek.getTheme(), Theme.COLORFUL);
  }

  /**
   * Checks that updateTasks correctly updates the number of max tasks for each day in the week.
   */
  @Test
  public void testUpdateTasks() {
    this.standardWeek.updateTasks(7);

    assertEquals(this.sun.getMaxTasks(), 7);
    assertEquals(this.mon.getMaxTasks(), 7);
    assertEquals(this.tues.getMaxTasks(), 7);
    assertEquals(this.wed.getMaxTasks(), 7);
    assertEquals(this.thurs.getMaxTasks(), 7);
    assertEquals(this.fri.getMaxTasks(), 7);
    assertEquals(this.sat.getMaxTasks(), 7);
  }

  /**
   * Checks that updateEvents correctly updates the number of max events for each day in the week.
   */
  @Test
  public void testUpdateEvents() {
    this.standardWeek.updateEvents(7);

    assertEquals(this.sun.getMaxEvents(), 7);
    assertEquals(this.mon.getMaxEvents(), 7);
    assertEquals(this.tues.getMaxEvents(), 7);
    assertEquals(this.wed.getMaxEvents(), 7);
    assertEquals(this.thurs.getMaxEvents(), 7);
    assertEquals(this.fri.getMaxEvents(), 7);
    assertEquals(this.sat.getMaxEvents(), 7);
  }

  /**
   * Checks that setDays correctly sets the given days as the days of the week.
   */
  @Test
  public void testSetDays() {
    Day sun2 = new Day(WeekDay.SUNDAY, new ArrayList<>(), new ArrayList<>(),
        7, 7);
    Day mon2 = new Day(WeekDay.MONDAY, new ArrayList<>(), new ArrayList<>(),
        7, 7);
    Day tues2 = new Day(WeekDay.TUESDAY, new ArrayList<>(), new ArrayList<>(),
        7, 7);
    Day wed2 = new Day(WeekDay.WEDNESDAY, new ArrayList<>(), new ArrayList<>(),
        7, 7);
    Day thurs2 = new Day(WeekDay.THURSDAY, new ArrayList<>(), new ArrayList<>(),
        7, 7);
    Day fri2 = new Day(WeekDay.FRIDAY, new ArrayList<>(), new ArrayList<>(),
        7, 7);
    Day sat2 = new Day(WeekDay.SATURDAY, new ArrayList<>(), new ArrayList<>(),
        7, 7);


    Day[] days2 = new Day[] {sun2, mon2, tues2, wed2, thurs2, fri2, sat2};

    this.standardWeek.setDays(days2);
    assertEquals(this.standardWeek.getDays(), days2);
  }

  /**
   * Checks that an empty list if returned for getNotes.
   */
  @Test
  public void testGetNotes() {
    assertEquals(this.standardWeek.getNotes(), new ArrayList<String>());
  }

  /**
   * Checks that an empty list if returned for getQuotes.
   */
  @Test
  public void testGetQuotes() {
    assertEquals(this.standardWeek.getQuotes(), new ArrayList<String>());
  }

  /**
   * Checks that a note gets added for addNote.
   */
  @Test
  public void testAddNotes() {
    assertEquals(this.standardWeek.getNotes(), new ArrayList<String>());

    this.standardWeek.addNote("note");

    ArrayList<String> notes = new ArrayList<>();
    notes.add("note");

    assertEquals(this.standardWeek.getNotes(), notes);

  }

  /**
   * Checks that a quote gets added for addQuote.
   */
  @Test
  public void testAddQuotes() {
    assertEquals(this.standardWeek.getQuotes(), new ArrayList<String>());

    this.standardWeek.addQuote("quote");

    ArrayList<String> quotes = new ArrayList<>();
    quotes.add("quote");

    assertEquals(this.standardWeek.getQuotes(), quotes);
  }

  /**
   * Checks that getNumTasks returns 0 for a week with no tasks
   */
  @Test
  public void testGetNumTasks() {
    assertEquals(this.standardWeek.getNumTasks(), 0);
  }

  /**
   * Checks that getNumEvents returns 0 for a week with no events
   */
  @Test
  public void testGetNumEvents() {
    assertEquals(this.standardWeek.getNumEvents(), 0);
  }

  /**
   * CHecks that getTasksCompleted returns the correct number of tasks completed
   */
  @Test
  public void testGetTasksCompleted() {
    this.standardWeek.addTask(this.task1, "Sunday");
    this.standardWeek.addTask(this.task2, "Monday");
    this.standardWeek.addTask(this.task3, "Tuesday");

    assertEquals(this.standardWeek.getTasksCompleted(), 1);
  }

  /**
   * Checks that getPercentComplete returns the correct percentage of completed tasks.
   */
  @Test
  public void testGetPercentComplete() {
    this.standardWeek.addTask(this.task1, "Sunday");
    this.standardWeek.addTask(this.task2, "Monday");
    this.standardWeek.addTask(this.task3, "Tuesday");


    assertEquals(this.standardWeek.getPercentComplete(), 33.3, 0.1);
  }

  /**
   * Checks that removeEvent removes an event correctly.
   */
  @Test
  public void testRemoveEventValid() {
    this.standardWeek.addEvent(this.event1, "Sunday");

    assertEquals(this.standardWeek.getNumEvents(), 1);

    this.standardWeek.removeEvent(this.event1, "Sunday");

    assertEquals(this.standardWeek.getNumEvents(), 0);
  }

  /**
   * Checks that an invalid day causes an error to be thrown for removeEvent.
   */
  @Test
  public void testRemoveEventInvalid() {
    assertThrows(IllegalArgumentException.class,
        () -> this.standardWeek.removeEvent(this.event1, "invalid"));
  }

  /**
   * Checks that a task gets removed correctly.
   */
  @Test
  public void testRemoveTaskValid() {
    this.standardWeek.addTask(this.task3, "Sunday");

    assertEquals(this.standardWeek.getNumTasks(), 1);

    this.standardWeek.removeTask(this.task3, "Sunday");

    assertEquals(this.standardWeek.getNumTasks(), 0);
  }

  /**
   * Checks that an invalid day causes an error to be thrown for removeTask.
   */
  @Test
  public void testRemoveTaskInvalid() {
    assertThrows(IllegalArgumentException.class,
        () -> this.standardWeek.removeTask(this.task3, "invalid"));
  }
}