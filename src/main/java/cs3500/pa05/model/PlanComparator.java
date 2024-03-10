package cs3500.pa05.model;

import java.util.Comparator;

/**
 * Represents a comparator for an AbstractPlan
 */
public class PlanComparator implements Comparator<AbstractPlan> {
  private final SortingFlag flag;

  /**
   * @param flag the sorting flag for this comparator
   */
  public PlanComparator(SortingFlag flag) {
    this.flag = flag;
  }

  /**
   * Compares two plans by their duration or name based on the sorting flag
   */
  @Override
  public int compare(AbstractPlan o1, AbstractPlan o2) {
    if (this.flag.equals(SortingFlag.DURATION)) {
      return o1.getDuration() - o2.getDuration();
    } else if (this.flag.equals(SortingFlag.NAME)) {
      return o1.getName().compareTo(o2.getName());
    } else {
      return 0;
    }
  }
}
