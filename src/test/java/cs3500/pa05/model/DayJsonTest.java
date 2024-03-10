package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for DayJson record.
 */
class DayJsonTest {
  EventJson[] events = new EventJson[]{};
  TaskJson[] tasks = new TaskJson[]{};
  DayJson dayJson = new DayJson("name", events, tasks, 1, 1);

  /**
   * Tests for name
   */
  @Test
  public void testName() {
    assertEquals(dayJson.name(), "name");
  }

  /**
   * Tests for events
   */
  @Test
  public void testEvents() {
    assertEquals(dayJson.events(), events);
  }

  /**
   * Tests for tasks
   */
  @Test
  public void testTasks() {
    assertEquals(dayJson.tasks(), tasks);
  }

  /**
   * Tests for max tasks
   */
  @Test
  public void testMaxTasks() {
    assertEquals(dayJson.maxTasks(), 1);
  }

  /**
   * Tests for max events
   */
  @Test
  public void testMaxEvents() {
    assertEquals(dayJson.maxEvents(), 1);
  }
}