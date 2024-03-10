package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Task class.
 */
class TaskTest {
  Task task;

  /**
   * Intializing the data
   */
  @BeforeEach
  public void initData() {
    this.task = new Task("task", "description");
  }

  /**
   * Tests for get status
   */
  @Test
  public void testGetStatus() {
    assertEquals(this.task.getStatus(), TaskStatus.INCOMPLETE);
  }

  /**
   * Tests for is complete
   */
  @Test
  public void testIsComplete() {
    assertFalse(this.task.isComplete());
  }

  /**
   * Tests for get duration
   */
  @Test
  public void testIsComplete2() {
    Task task = new Task("task", "description", TaskStatus.COMPLETE);
    assertTrue(task.isComplete());
  }

  @Test
  public void testGetDuration() {
    assertEquals(this.task.getDuration(), 0);
  }
}