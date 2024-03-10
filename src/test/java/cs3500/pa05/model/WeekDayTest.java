package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for WeekDay enum.
 */
class WeekDayTest {
  /**
   * Checks that getCorrectDay gets the correct day.
   */
  @Test
  public void testGetCorrectDay() {
    assertEquals(WeekDay.getCorrectDay("Sunday"), WeekDay.SUNDAY);
  }

  /**
   * Checks that getCorrectDay throws an error when given an invalid day.
   */
  @Test
  public void testGetCorrectDayInvalid() {
    assertThrows(IllegalArgumentException.class, () -> WeekDay.getCorrectDay("invalid"));
  }
}