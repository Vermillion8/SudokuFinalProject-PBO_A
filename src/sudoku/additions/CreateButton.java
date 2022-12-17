package sudoku.additions;

import javafx.scene.control.Button;

public class CreateButton extends Button {
  
  private final String UNPRESSED_BUTTON_STYLE = "-fx-background-color: #ff007f; -fx-text-fill: #ffffff; -fx-font-size: 20px;";
  private final String PRESSED_BUTTON_STYLE = "-fx-background-color: #8c0046; -fx-text-fill: #ffffff; -fx-font-size: 20px;";

  public CreateButton (String text) {
    setText(text);
    setPrefWidth(200);
    setPrefHeight(50);
    setStyle(UNPRESSED_BUTTON_STYLE);
    initializeButtonListeners();
  }

  private void initializeButtonListeners() {
    setOnMousePressed(e -> setStyle(PRESSED_BUTTON_STYLE));
    setOnMouseReleased(e -> setStyle(UNPRESSED_BUTTON_STYLE));
  }
}
