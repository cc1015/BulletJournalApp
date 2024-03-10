package cs3500.pa05.model;

/**
 * Represents a week day
 */
public enum WeekDay {
  /**
   * sunday
   */
  SUNDAY("Sunday"),
  /**
   * monday
   */
  MONDAY("Monday"),
  /**
   * tuesday
   */
  TUESDAY("Tuesday"),
  /**
   * wednesday
   */
  WEDNESDAY("Wednesday"),
  /**
   * thursday
   */
  THURSDAY("Thursday"),
  /**
   * friday
   */
  FRIDAY("Friday"),
  /**
   * saturday
   */
  SATURDAY("Saturday");

  private final String dayName;

  /**
   * Sets dayName to the given String.
   *
   * @param dayName The day name.
   */
  WeekDay(String dayName) {
    this.dayName = dayName;
  }

  /**
   * Returns the WeekDay based on the given name.
   *
   * @param name The name of the day.
   *
   * @return The Weekday with the name of the given name.
   */
  public static WeekDay getCorrectDay(String name) {
    for (WeekDay d : WeekDay.values()) {
      if (d.dayName.equals(name)) {
        return d;
      }
    }
    throw new IllegalArgumentException("Invalid day");
  }

  /**
   * Returns dayName.
   *
   * @return This Weekday's dayName.
   */
  @Override
  public String toString() {
    return this.dayName;
  }
}
