package cs3500.pa05.controller;

import cs3500.pa05.model.SortingFlag;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.DialogView;
import cs3500.pa05.view.Theme;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * Represents a controller for the settings pop-up window
 */
public class SettingsController implements DialogController {
  @FXML
  private TextField maxTasksInput;
  @FXML
  private TextField maxEventsInput;
  @FXML
  private RadioButton lightMode;
  @FXML
  private RadioButton darkMode;
  @FXML
  private RadioButton colorfulMode;
  @FXML
  private RadioButton sortByName;
  @FXML
  private RadioButton sortByDuration;
  @FXML
  private Button confirm;
  @FXML
  private Label errorLabel;
  @FXML
  private AnchorPane anchorPane;
  @FXML
  private DialogView view;
  private final ToggleGroup themeButtons;
  private final ToggleGroup sortingButtons;
  private final JournalController masterController;
  private final Week week;

  /**
   * Instantiates a controller for the settings pop-up window
   *
   * @param masterController the main journal session controller
   * @param week the current week
   */
  public SettingsController(Week week, JournalController masterController) {
    this.week = week;
    this.view = new DialogView(this, "settings.fxml");
    this.themeButtons = new ToggleGroup();
    this.sortingButtons = new ToggleGroup();
    this.masterController = masterController;
  }

  /**
   * Initializes the settings pop-up window
   *
   * @throws IllegalStateException if the game board is not defined
   */
  @Override
  public void run() throws IllegalStateException {
    this.handleChangeTheme();
    this.handleChangeSort();
    this.sortByDuration.setToggleGroup(this.sortingButtons);
    this.sortByName.setToggleGroup(this.sortingButtons);
    this.confirm.setOnAction(e -> this.handleConfirm());
    this.view.show();
  }

  /**
   * Handles the theme buttons
   */
  private void handleChangeTheme() {
    this.lightMode.setToggleGroup(this.themeButtons);
    this.darkMode.setToggleGroup(this.themeButtons);
    this.colorfulMode.setToggleGroup(this.themeButtons);
    lightMode.setOnAction(e -> this.week.updateTheme(Theme.BASIC));
    darkMode.setOnAction(e -> this.week.updateTheme(Theme.DARK_MODE));
    colorfulMode.setOnAction(e -> this.week.updateTheme(Theme.COLORFUL));
  }

  /**
   * Handles the changing of sorting flags
   */
  private void handleChangeSort() {
    sortByName.setOnAction(e -> this.masterController.updateSortingFlag(SortingFlag.NAME));
    sortByDuration.setOnAction(e -> this.masterController.updateSortingFlag(SortingFlag.DURATION));
  }

  /**
   * Handles inputs once the confirm button has been pressed
   */
  private void handleConfirm() {
    String maxTasks = this.maxTasksInput.getText();
    String maxEvents = this.maxEventsInput.getText();
    boolean validTasks = this.validLimitInput(maxTasks);
    boolean validEvents = this.validLimitInput(maxEvents);

    if (validTasks) {
      if (maxTasks.equals("")) {
        this.week.updateTasks(100000);
      } else {
        this.week.updateTasks(Integer.parseInt(maxTasks));
      }
    }

    if (validEvents) {
      if (maxEvents.equals("")) {
        this.week.updateEvents(100000);
      } else {
        this.week.updateEvents(Integer.parseInt(maxEvents));
      }
    }

    if (validTasks && validEvents) {
      errorLabel.setText("");
      this.view.close();
    } else {
      errorLabel.setText("Invalid input! Input an integer \ngreater than 0 or leave blank.");
    }
    this.masterController.updateListView();
    masterController.setTheme();
  }

  /**
   * Determines if the user's input for maximum tasks or events is valid
   *
   * @param input the input of the user
   * @return true if the input is valid (either empty or a number greater than 0)
   */
  private boolean validLimitInput(String input) {
    if (input.equals("")) {
      return true;
    }
    try {
      int number = Integer.parseInt(input);
      return number > 0;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}