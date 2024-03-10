package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.view.Theme;

/**
 * Represents a week's JSON format.
 *
 * @param days The weeks days.
 */
public record WeekJson(
    @JsonProperty("days") DayJson[] days,
    @JsonProperty("theme") Theme theme,
    @JsonProperty("notes") String[] notes,
    @JsonProperty("quotes") String[] quotes) {
}
