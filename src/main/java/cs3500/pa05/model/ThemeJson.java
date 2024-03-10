package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.view.Theme;

/**
 * @param theme this theme
 */
public record ThemeJson(@JsonProperty("themeName") Theme theme) {
}
