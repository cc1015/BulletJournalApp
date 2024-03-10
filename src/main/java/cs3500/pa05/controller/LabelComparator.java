package cs3500.pa05.controller;

import java.util.Comparator;
import javafx.scene.control.Label;

/**
 * Represents a comparator for a java fx label
 */
public class LabelComparator implements Comparator<Label> {
  /**
   * Compares two labels by the text in them
   */
  @Override
  public int compare(Label o1, Label o2) {
    return o1.getText().compareTo(o2.getText());
  }
}
