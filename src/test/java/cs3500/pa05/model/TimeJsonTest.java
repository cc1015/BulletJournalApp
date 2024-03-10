package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test for TimeJson.Class
 */
class TimeJsonTest {
  TimeJson timeJson = new TimeJson(1, 2, TimeIndication.AM);

  /**
   * Test for hour
   */
  @Test
  void hour() {
    assertEquals(timeJson.hour(), 1);
  }

  /**
   * Tests for minute
   */
  @Test
  void minute() {
    assertEquals(timeJson.minute(), 2);
  }

  /**
   * Tests for indicaiton
   */
  @Test
  void indication() {
    assertEquals(timeJson.indication(), TimeIndication.AM);
  }
}