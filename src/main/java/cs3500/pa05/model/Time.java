package cs3500.pa05.model;

/**
 * Represents a time.
 */
public class Time {
  int hour;
  int minute;
  TimeIndication indication;

  /**
   * Sets fields to the given arguments.
   *
   * @param hour The hour time (1-12).
   * @param minute The minute time (0-59).
   * @param indication Whether it is AM or PM.
   */
  public Time(int hour, int minute, TimeIndication indication) {
    this.hour = hour;
    this.minute = minute;
    this.indication = indication;
  }

  /**
   * Returns this time in String format.
   *
   * @return This time as a String.
   */
  @Override
  public String toString() {
    return this.hour + ":" + this.minute + indication.toString();
  }

  public int getHour() {
    return this.hour;
  }

  public int getMinute() {
    return this.minute;
  }

  public TimeIndication getIndication() {
    return this.indication;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Time)) {
      return false;
    }
    Time that = (Time) other;
    return this.hour == that.hour && this.minute == that.minute
        && this.indication.equals(that.indication);
  }
}
