package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.view.Theme;
import org.junit.jupiter.api.Test;

/**
 * Tests for ThemeJson.Class
 */
class ThemeJsonTest {
  ThemeJson themeJson = new ThemeJson(Theme.COLORFUL);

  /**
   * Tests for theme
   */
  @Test
  void theme() {
    assertEquals(themeJson.theme(), Theme.COLORFUL);
  }
}