package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for EventJson.class
 */
class EventJsonTest {
  TimeJson timeJson = new TimeJson(1, 2, TimeIndication.AM);
  EventJson eventJson = new EventJson("name", "description", timeJson, 5);

  /**
   * Tests for name
   */
  @Test
  void name() {
    assertEquals(eventJson.name(), "name");
  }

  /**
   * Tests for descpription
   */
  @Test
  void description() {
    assertEquals(eventJson.description(), "description");
  }

  /**
   * Tests for start time
   */
  @Test
  void startTime() {
    assertEquals(eventJson.startTime(), timeJson);
  }

  /**
   * Tests for duration
   */
  @Test
  void duration() {
    assertEquals(eventJson.duration(), 5);
  }
}