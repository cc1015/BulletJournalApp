package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for Event class.
 */
class EventTest {
  Event event;
  Time time;


  /**
   * Tests for init data
   */
  @BeforeEach
  public void initData() {
    this.time = new Time(1, 1, TimeIndication.AM);
    this.event = new Event("event", "description", this.time, 5);
  }

  /**
   * Constructor testss
   */
  @Test
  public void testConstruct() {
    assertNotNull(this.event);
  }

  /**
   * Test for get duration
   */
  @Test
  public void testGetDuration() {
    assertEquals(this.event.getDuration(), 5);
  }

  /**
   * Tests for get start time
   */
  @Test
  public void testGetStartTime() {
    assertEquals(this.event.getStartTime(), this.time);
  }

}