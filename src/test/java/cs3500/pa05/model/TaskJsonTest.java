package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for TaskJson.Class
 */
class TaskJsonTest {
  TaskJson taskJson = new TaskJson("name", "description", TaskStatus.COMPLETE);

  /**
   * Tests for name
   */
  @Test
  void name() {
    assertEquals(taskJson.name(), "name");
  }

  /**
   * Tests for description
   */
  @Test
  void description() {
    assertEquals(taskJson.description(), "description");
  }

  /**
   * Tests for status
   */
  @Test
  void status() {
    assertEquals(taskJson.status(), TaskStatus.COMPLETE);
  }
}