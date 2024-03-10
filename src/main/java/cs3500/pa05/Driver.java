package cs3500.pa05;

import cs3500.pa05.controller.FileController;
import cs3500.pa05.controller.JavaJournalPreloader;
import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.Week;
import cs3500.pa05.view.JournalView;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents a Java Journal application
 */
public class Driver extends Application {
  private Stage stage;

  /**
   * Entry point for the java journal
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch();
  }

  /**
   * Starts the GUI for the java journal
   *
   * @param stage the JavaFX stage to add elements to
   */
  @Override
  public void start(Stage stage) {
    this.stage = stage;
    JavaJournalPreloader preloader = new JavaJournalPreloader();
    try {
      stage.setScene(preloader.load());
      PauseTransition pt = new PauseTransition(Duration.millis(1000));
      pt.setOnFinished(e -> setFileSelect());
      pt.play();
      stage.show();
    } catch (IllegalStateException e) {
      System.out.print("Unable to load GUI");
    }
  }

  /**
   * Initalizes the file seleciton controller
   */
  private void setFileSelect() {
    FileController fileController = new FileController(new Week(Week.standardWeek()));
    this.stage.setScene(fileController.getSfView().load());
    fileController.run(this.stage);
  }


  /**
   * @param stage the new stage
   * @param week the current week
   */
  public static void startMainApp(Stage stage, Week week) {
    JournalController controller = new JournalController(week);
    JournalView view = controller.getView();
    System.out.println("main app started");
    stage.setScene(view.load());
    controller.run();
    stage.show();
  }
}