package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for AbstractPlan class.
 */
class AbstractPlanTest {
  AbstractPlan task;

  @BeforeEach
  public void initData() {
    this.task = new Task("task", "description", TaskStatus.COMPLETE);
  }

  /**
   * Checks that getName returns the correct name.
   */
  @Test
  public void testGetName() {
    assertEquals(this.task.getName(), "task");
  }

  /**
   * Checks that getDescription returns the correct description.
   */
  @Test
  public void testGetDescription() {
    assertEquals(this.task.getDescription(), "description");
  }
}