package sudoku.additions;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sudoku.buildlogic.SudokuBuildLogic;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.userinterface.UserInterfaceImpl;

public class DifficultyPicker {

  private static final int WINDOW_HEIGHT = 668;
  private static final int WINDOW_WIDTH = 668;
  private AnchorPane diffPane;
  private Scene diffScene;
  private Stage diffStage;
  private Stage menuStage;

  public DifficultyPicker() {
    initializeStage();
    createDiffButtons();
    createBackButton();
  }

  private void createDiffButtons() {
    easyDiffButton();
    mediumDiffButton();
    hardDiffButton();
  }

  private void easyDiffButton() {
    CreateButton easyButton = new CreateButton("Easy");
    easyButton.setLayoutX(WINDOW_WIDTH/2 - 100);
    easyButton.setLayoutY(200);
    diffPane.getChildren().add(easyButton);

    easyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        startGame(diffStage, DifficultyState.EASY);
      }
    });
  }

  private void mediumDiffButton() {
    CreateButton mediumButton = new CreateButton("Medium");
    mediumButton.setLayoutX(WINDOW_WIDTH/2 - 100);
    mediumButton.setLayoutY(300);
    diffPane.getChildren().add(mediumButton);

    mediumButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        startGame(diffStage, DifficultyState.MEDIUM);
      }
    });
  }

  private void hardDiffButton() {
    CreateButton hardButton = new CreateButton("Hard");
    hardButton.setLayoutX(WINDOW_WIDTH/2 - 100);
    hardButton.setLayoutY(400);
    diffPane.getChildren().add(hardButton);

    hardButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        startGame(diffStage, DifficultyState.HARD);
      }
    });
  }
  private void createBackButton() {
    CreateButton backButton = new CreateButton("Back");
    backButton.setLayoutX(WINDOW_WIDTH/2 - 100);
    backButton.setLayoutY(500);
    diffPane.getChildren().add(backButton);

    backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        diffStage.close();
        menuStage.show();
      }
    });
  }

  public void initializeStage() {
    diffPane = new AnchorPane();
    diffScene = new Scene(diffPane, WINDOW_WIDTH, WINDOW_HEIGHT);
    diffStage = new Stage();
    diffStage.setScene(diffScene);
  }

  public void createDiffStage(Stage stage) {
    this.menuStage = stage;
    this.menuStage.hide();
    this.diffStage.show();
  }

  
  public void startGame(Stage game, DifficultyState difficulty) {
    
    IUserInterfaceContract.View uiImpl;
    uiImpl = new UserInterfaceImpl(game, difficulty);

    try {
        SudokuBuildLogic.build(uiImpl, difficulty);
    } catch (IOException e) {
        e.printStackTrace();
    }
  }
}
