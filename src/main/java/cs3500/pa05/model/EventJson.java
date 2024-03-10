package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an event's JSON format
 *
 * @param name The event's name.
 * @param description The event's description.
 * @param startTime The event's start time.
 * @param duration The event's duration.
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("start") TimeJson startTime,
    @JsonProperty("duration") int duration
) {
}
