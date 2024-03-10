package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a day's JSON format.
 *
 * @param name The day's name.
 * @param events The day's events.
 * @param tasks The day's tasks.
 */
public record DayJson(
    @JsonProperty("name") String name,
    @JsonProperty("events") EventJson[] events,
    @JsonProperty("tasks") TaskJson[] tasks,
    @JsonProperty("maxTasks") int maxTasks,
    @JsonProperty("maxEvents") int maxEvents
) {
}
