package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.view.Theme;
import org.junit.jupiter.api.Test;

/**
 * Tests for WeekJson.Class
 */
class WeekJsonTest {
  DayJson[] dayJsons = new DayJson[]{};
  String[] notes = new String[]{};
  String[] quotes = new String[]{};
  WeekJson weekJson = new WeekJson(dayJsons, Theme.COLORFUL, notes, quotes);

  /**
   * Test for days
   */
  @Test
  void days() {
    assertEquals(weekJson.days(), dayJsons);
  }

  /**
   * Tests for theme
   */
  @Test
  void theme() {
    assertEquals(weekJson.theme(), Theme.COLORFUL);
  }

  /**
   * Tests for notes
   */
  @Test
  void notes() {
    assertEquals(weekJson.notes(), notes);
  }

  /**
   * Tests for quotes
   */
  @Test
  void quotes() {
    assertEquals(weekJson.quotes(), quotes);
  }
}