package sudoku;

import javafx.application.Application;
import javafx.stage.Stage;
import sudoku.additions.ViewManager;

public class SudokuApp extends Application {
  public static void main(String[] args) {
		launch(args);
	}

  @Override
  public void start(Stage primaryStage) throws Exception {
    ViewManager manager = new ViewManager();
    primaryStage = manager.getMenuStage();
    primaryStage.setTitle("Sudoku");
    primaryStage.show();
  }
}