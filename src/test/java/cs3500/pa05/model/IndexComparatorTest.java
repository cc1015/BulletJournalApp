package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for indexComparator class.
 */
class IndexComparatorTest {
  IndexComparator comp;
  List<String> list;

  /**
   * Initializing the data
   */
  @BeforeEach
  public void initData() {
    this.list = new ArrayList<>();
    this.list.add("a");
    this.list.add("b");
    this.list.add("c");
    this.list.add("d");
    this.comp = new IndexComparator(this.list);
  }

  /**
   * Tests for a greater than comparison
   */
  @Test
  public void testCompareGreater() {
    assertEquals(this.comp.compare("d", "a"), 3);
  }

  /**
   * Tests for a less than comparison
   */
  @Test
  public void testCompareLess() {
    assertEquals(this.comp.compare("a", "d"), -3);
  }

  /**
   * Tests for an equals to comparison
   */
  @Test
  public void testCompareEqual() {
    assertEquals(this.comp.compare("a", "a"), 0);
  }
}