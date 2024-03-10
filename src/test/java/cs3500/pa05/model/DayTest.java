package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Day class.
 */
class DayTest {
  Day monday;
  Day tuesday;
  ArrayList<Event> events;
  Event event1;
  Time time1;
  Event event2;
  ArrayList<Task> tasks;
  Time time2;
  Task task1;
  Task task2;
  Task task3;

  /**
   * Initializes the data.
   */
  @BeforeEach
  public void initData() {
    this.monday = new Day(WeekDay.MONDAY);

    this.time1 = new Time(11, 30, TimeIndication.AM);
    this.event1 = new Event("event1", "event 1 description", time1, 20);

    this.time2 = new Time(1, 30, TimeIndication.PM);
    this.event2 = new Event("event2", "event 2 description", time2, 90);

    this.events = new ArrayList<>();
    this.events.add(event1);
    this.events.add(event2);

    this.task1 = new Task("task1", "task 1 description", TaskStatus.COMPLETE);
    this.task2 = new Task("task2", "task 2 description", TaskStatus.INCOMPLETE);
    this.task3 = new Task("task3", "task 3 description", TaskStatus.INCOMPLETE);

    this.tasks = new ArrayList<>();
    this.tasks.add(task1);
    this.tasks.add(task2);
    this.tasks.add(task3);

    this.tuesday = new Day(WeekDay.TUESDAY, tasks, events, 4, 3);
  }

  /**
   * Checks if constructor properly initializes object.
   */
  @Test
  public void testConstruct() {
    assertNotNull(this.monday);
    assertNotNull(this.tuesday);
  }

  /**
   * Checks that getName returns the correct name.
   */
  @Test
  public void testGetName() {
    assertEquals(this.monday.getName(), "Monday");
  }

  /**
   * Checks that getTasks returns the correct list of tasks.
   */
  @Test
  public void testGetTasks() {
    assertEquals(this.tuesday.getTasks(), this.tasks);
  }

  /**
   * Checks that getEvents returns the correct list of events.
   */
  @Test
  public void testGetEvents() {
    assertEquals(this.tuesday.getEvents(), this.events);
  }

  /**
   * Checks that getMaxTasks returns the correct number of max tasks.
   */
  @Test
  public void testSetMaxTasks() {
    assertEquals(this.monday.getMaxTasks(), 10000);
    this.monday.setMaxTasks(2);
    assertEquals(this.monday.getMaxTasks(), 2);
  }

  /**
   * Checks that getMaxEvents returns the correct number of max events.
   */
  @Test
  public void testSetMaxEvents() {
    assertEquals(this.monday.getMaxEvents(), 10000);
    this.monday.setMaxEvents(2);
    assertEquals(this.monday.getMaxEvents(), 2);
  }

  /**
   * Checks that addTask correctly adds a task.
   */
  @Test
  public void testAddTaskValid() {
    Task newTask = new Task("new task", "new task description", TaskStatus.INCOMPLETE);
    this.tuesday.addTask(newTask);
    this.tasks.add(newTask);

    assertEquals(this.tuesday.getTasks(), this.tasks);
  }

  /**
   * Checks that addTask throws an error when trying to add more tasks than the max.
   */
  @Test
  public void testAddTaskInvalid() {
    Task newTask1 = new Task("new task", "new task description", TaskStatus.INCOMPLETE);
    this.tuesday.addTask(newTask1);

    Task newTask2 = new Task("new task", "new task description", TaskStatus.INCOMPLETE);

    assertThrows(IllegalStateException.class,
        () -> this.tuesday.addTask(newTask2));
  }

  /**
   * Checks that addEvent correctly adds an event.
   */
  @Test
  public void testAddEventValid() {
    Event newEvent = new Event("new event", "new event description", this.time1, 50);
    this.tuesday.addEvent(newEvent);
    this.events.add(newEvent);

    assertEquals(this.tuesday.getEvents(), this.events);
  }

  /**
   * Checks that addEvent throws an error when trying to add more events than the max.
   */
  @Test
  public void testAddEventInvalid() {
    Event newEvent1 = new Event("new event", "new event description", this.time1, 50);
    this.tuesday.addEvent(newEvent1);

    Event newEvent2 = new Event("new event", "new event description", this.time1, 50);

    assertThrows(IllegalStateException.class,
        () -> this.tuesday.addEvent(newEvent2));
  }
}