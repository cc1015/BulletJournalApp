package cs3500.pa05.model;

/**
 * Represents an abstract plan in a day.
 */
public abstract class AbstractPlan {
  /**
   * the name of this plan
   */
  protected String name;

  /**
   * the description for this plan
   */
  protected String description;

  /**
   * Sets given name and description to the name and description fieds.
   *
   * @param name The String name the name field is set to.
   * @param description The String description the description field is set to.
   */
  public AbstractPlan(String name, String description) {
    this.name = name;
    this.description = description;
  }

  /**
   * @return the name of this plan
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the description of this plan
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return the duration of this plan
   */
  public abstract int getDuration();
}
