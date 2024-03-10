package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a task's JSON format.
 *
 * @param name The task's name.
 * @param description The task's description.
 * @param status The task's completion status.
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("status") TaskStatus status
) {
}
