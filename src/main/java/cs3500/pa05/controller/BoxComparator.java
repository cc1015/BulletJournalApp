package cs3500.pa05.controller;

import cs3500.pa05.model.SortingFlag;
import java.util.Comparator;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents a comparator for two v boxes in a journal gui
 */
public class BoxComparator implements Comparator<VBox> {
  private final LabelComparator labelComp = new LabelComparator();
  private final String planType;
  private final SortingFlag sortingFlag;

  /**
   * @param planType task or event
   * @param sortingFlag name, duration, or default (natural ordering)
   */
  public BoxComparator(String planType, SortingFlag sortingFlag) {
    this.planType = planType;
    this.sortingFlag = sortingFlag;
  }

  /**
   * Compares two VBoxes by the text inside them
   */
  @Override
  public int compare(VBox o1, VBox o2) {
    if (o1.getChildren().get(0) instanceof Label && o2.getChildren().get(0) instanceof Label) {
      if (this.sortingFlag == SortingFlag.NAME) {
        Label l1 = (Label) o1.getChildren().get(0);
        Label l2 = (Label) o2.getChildren().get(0);
        return labelComp.compare(l1, l2);
      } else if (this.sortingFlag == SortingFlag.DURATION && this.planType.equals("EVENT")) {
        Label l1 = (Label) o1.getChildren().get(2);
        String l1Text = l1.getText();
        Label l2 = (Label) o2.getChildren().get(2);
        String l2Text = l2.getText();
        return compareInts(l1Text, l2Text);
      }
    }
    return 0;
  }

  /**
   * @param s1 first string
   * @param s2 second string to compare to first string
   * @return the difference between any numbers within the given strings
   */
  private int compareInts(String s1, String s2) {
    int s1Duration = Integer.parseInt(s1.replaceAll("[^0-9]", ""));
    int s2Duration = Integer.parseInt(s2.replaceAll("[^0-9]", ""));
    return s1Duration - s2Duration;
  }
}
