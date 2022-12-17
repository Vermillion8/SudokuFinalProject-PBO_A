package sudoku.additions;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ViewManager {
  private static final int WINDOW_HEIGHT = 668;
  private static final int WINDOW_WIDTH = 668;

  private Stage menuStage;
  private Scene menuScene;
  private AnchorPane menuPane;

  public ViewManager() {
    menuPane = new AnchorPane();
    menuScene = new Scene(menuPane, WINDOW_WIDTH, WINDOW_HEIGHT);
    menuStage = new Stage();
    menuStage.setScene(menuScene);

    createMainText();
    createButton();
  }

  public Stage getMenuStage() {
    return menuStage;
  }

  private void createMainText() {
    Text mainText = new Text();
    mainText.setText("Sudoku");
    mainText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
    mainText.setTextAlignment(TextAlignment.CENTER);
    mainText.setFill(Color.PINK);
    mainText.setLayoutX(WINDOW_WIDTH/2 - 100);
    mainText.setLayoutY(100);

    menuPane.getChildren().add(mainText);
  }

  private void createButton() {
    createPlayButton();
    createExitButton();
  }

  private void createExitButton() {
    CreateButton exitButton = new CreateButton("Exit");
    exitButton.setLayoutX(WINDOW_WIDTH/2 - 100);
    exitButton.setLayoutY(300);
    menuPane.getChildren().add(exitButton);

    exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        menuStage.close();
      }
    });
  }

  private void createPlayButton() {
    CreateButton playButton = new CreateButton("Play");
    playButton.setLayoutX(WINDOW_WIDTH/2 - 100);
    playButton.setLayoutY(200);

    menuPane.getChildren().add(playButton);
    
    playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent event) {
        DifficultyPicker difficultyPicker = new DifficultyPicker();
        difficultyPicker.createDiffStage(menuStage);
      }
    });
  }

}
