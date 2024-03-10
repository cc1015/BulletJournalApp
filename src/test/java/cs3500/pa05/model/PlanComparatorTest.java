package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for plan comparator
 */
class PlanComparatorTest {
  PlanComparator nameComp;
  PlanComparator durationComp;
  SortingFlag nameFlag;
  SortingFlag durationFlag;
  Task task1;
  Task task2;
  Task task3;

  /**
   * Intializing the data
   */
  @BeforeEach
  public void initData() {
    this.task1 = new Task("a", "desc", TaskStatus.COMPLETE);
    this.task2 = new Task("b", "desc", TaskStatus.COMPLETE);
    this.task3 = new Task("b", "desc", TaskStatus.COMPLETE);
    this.nameFlag = SortingFlag.NAME;
    this.durationFlag = SortingFlag.DURATION;
    this.nameComp = new PlanComparator(this.nameFlag);
    this.durationComp = new PlanComparator(this.durationFlag);
  }

  /**
   * Tests for a greater than comparison
   */
  @Test
  public void testCompareGreater() {
    assertEquals(this.nameComp.compare(this.task2, this.task1),
        1);
  }

  /**
   * Tests for a less than comparison
   */
  @Test
  public void testCompareLess() {
    assertEquals(this.nameComp.compare(this.task1, this.task2), -1);
  }

  /**
   * Tests for an equals to comparison
   */
  @Test
  public void testCompareEqual() {
    assertEquals(this.nameComp.compare(this.task2, this.task3), 0);
  }

  @Test
  public void testCompareGreater2() {
    assertEquals(this.durationComp.compare(this.task2, this.task1),
        0);
  }

  @Test
  public void testCompareLess2() {
    assertEquals(this.durationComp.compare(this.task1, this.task2), 0);
  }

  @Test
  public void testCompareEqual2() {
    assertEquals(this.durationComp.compare(this.task2, this.task3), 0);
  }

}