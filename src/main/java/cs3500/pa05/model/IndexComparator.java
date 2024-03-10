package cs3500.pa05.model;

import java.util.Comparator;
import java.util.List;

/**
 * Compares two objects by their index in the given list
 */
public class IndexComparator implements Comparator<String> {
  private List<String> stringList;

  /**
   * @param list the list of strings for this comparator
   */
  public IndexComparator(List<String> list) {
    this.stringList = list;
  }

  /**
   * Compares the two strings by their index in the given list
   */
  @Override
  public int compare(String o1, String o2) {
    return stringList.indexOf(o1) - stringList.indexOf(o2);
  }
}
