package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for Time.Class
 */
class TimeTest {
  Time time;

  /**
   * Intializing the data
   */
  @BeforeEach
  public void initData() {
    this.time = new Time(1, 2, TimeIndication.AM);
  }

  /**
   * Tests for get minute
   */
  @Test
  public void testGetMinute() {
    assertEquals(this.time.getMinute(), 2);
  }

  /**
   * Tests for get hour
   */
  @Test
  public void testGetHour() {
    assertEquals(this.time.getHour(), 1);
  }

  /**
   * Tests for get indication
   */
  @Test
  public void testGetIndication() {
    assertEquals(this.time.getIndication(), TimeIndication.AM);
  }

  /**
   * Tests for to string
   */
  @Test
  public void testToString() {
    assertEquals(this.time.toString(), "1:2AM");
  }

  /**
   * Tests for a passing equals comparison
   */
  @Test
  public void testEqualsPass() {
    Time testTime = new Time(1, 2, TimeIndication.AM);
    assertTrue(this.time.equals(testTime));
  }

  /**
   * Test for equals failure
   */
  @Test
  public void testEqualsFail() {
    Time testTime = new Time(12, 2, TimeIndication.AM);
    assertFalse(this.time.equals(testTime));
  }

  /**
   * Other equals failure test
   */
  @Test
  public void testEqualsFail2() {
    assertFalse(this.time.equals("invalid"));
  }

  @Test
  public void testEqualsFail3() {
    Time testTime = new Time(1, 22, TimeIndication.AM);
    assertFalse(this.time.equals(testTime));
  }

  @Test
  public void testEqualsFail4() {
    Time testTime = new Time(1, 2, TimeIndication.PM);
    assertFalse(this.time.equals(testTime));
  }

  @Test
  public void testEqualsFail5() {
    Time testTime = new Time(11, 21, TimeIndication.PM);
    assertFalse(this.time.equals(testTime));
  }
}