package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param hour the hour of this time
 * @param minute the minute for this time
 * @param indication am or pm
 */
public record TimeJson(
    @JsonProperty("hour") int hour,
    @JsonProperty("minute") int minute,
    @JsonProperty("isBeforeNoon") TimeIndication indication
) {
}
