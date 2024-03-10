package cs3500.pa05.controller;

import java.util.Comparator;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * Represents a comparator for a node in java journal
 */
public class NodeComparator implements Comparator<Node> {
  private BoxComparator boxComp;

  /**
   * @param comp the VBox comparator for this node comparator
   */
  public NodeComparator(BoxComparator comp) {
    this.boxComp = comp;
  }

  /**
   * Compares two nodes by their VBoxes
   */
  @Override
  public int compare(Node o1, Node o2) {
    if (o1 instanceof VBox && o2 instanceof VBox) {
      VBox vb1 = (VBox) o1;
      VBox vb2 = (VBox) o2;
      return boxComp.compare(vb1, vb2);
    }
    return 0;
  }
}
