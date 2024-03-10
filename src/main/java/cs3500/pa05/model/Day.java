package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a day in a week.
 */
public class Day {
  private WeekDay name;
  private ArrayList<Task> tasks;
  private ArrayList<Event> events;
  private int maxTasks;
  private int maxEvents;
  private SortingFlag sortingFlag;

  /**
   * Sets fields to the given arguments.
   *
   * @param name The String name the name field is set to.
   * @param tasks The list of tasks the tasks fields is set to.
   * @param events The list of events the tasks events is set to.
   * @param maxTasks  The max number of tasks the maxTasks field is set to.
   * @param maxEvents The max number of events the maxEvents field is set to.
   */
  public Day(WeekDay name, ArrayList<Task> tasks, ArrayList<Event> events,
             int maxTasks, int maxEvents) {
    this.name = name;
    this.tasks = tasks;
    this.events = events;
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
    this.sortingFlag = SortingFlag.DEFAULT;
  }

  /**
   * Convenience constructor that sets name to the given name, tasks and events to an empty lists,
   * and the maxTasks and maxEvents to -1 (default- no max yet).
   *
   * @param name The String name the name field is set to.
   */
  public Day(WeekDay name) {
    this.name = name;
    this.tasks = new ArrayList<>();
    this.events = new ArrayList<>();
    this.maxTasks = 10000;
    this.maxEvents = 10000;
    this.sortingFlag = SortingFlag.DEFAULT;
  }

  /**
   * Returns this day's name.
   *
   * @return This day's name.
   */
  public String getName() {
    return this.name.toString();
  }

  /**
   * Returns this day's tasks.
   *
   * @return This day's tasks.
   */
  public ArrayList<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Returns this day's events.
   *
   * @return This day's events.
   */
  public ArrayList<Event> getEvents() {
    return this.events;
  }

  /**
   * Returns this day's max tasks.
   *
   * @return This day's max tasks.
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Returns this day's max events.
   *
   * @return This day's max events.
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }


  /**
   * Sets this day's maxTasks to the given int.
   *
   * @param maxTasks The max number of tasks.
   */
  public void setMaxTasks(int maxTasks) {
    this.maxTasks = maxTasks;
  }

  /**
   * Sets this day's maxEvents to the given int.
   *
   * @param maxEvents The max number of events.
   */
  public void setMaxEvents(int maxEvents) {
    this.maxEvents = maxEvents;
  }

  /**
   * Adds the given task to this day's list of tasks.
   *
   * @param task The task to be added to the list.
   */
  public void addTask(Task task) {
    if ((this.tasks.size() + 1) > this.maxTasks) {
      throw new IllegalStateException("Reached maximum tasks");
    }
    this.tasks.add(task);
    Collections.sort(this.tasks, new PlanComparator(this.sortingFlag));
  }

  /**
   * Adds the given event to this day's list of events.
   *
   * @param event The event to be added to the list.
   */
  public void addEvent(Event event) {
    if ((this.events.size() + 1) > this.maxEvents) {
      throw new IllegalStateException("Reached maximum events");
    }
    this.events.add(event);
    Collections.sort(this.tasks, new PlanComparator(this.sortingFlag));
  }

  /**
   * @return the number of tasks in this day
   */
  public int getNumTasks() {
    return this.tasks.size();
  }

  /**
   * @return the number of events in this day
   */
  public int getNumEvents() {
    return this.events.size();
  }

  /**
   * @return the number of completed tasks in this day
   */
  public int getTasksComplete() {
    int count = 0;
    for (Task t : this.tasks) {
      if (t.getStatus() == TaskStatus.COMPLETE) {
        count += 1;
      }
    }
    return count;
  }
}
