package sudoku.userinterface.logic;

import java.io.IOException;

import sudoku.additions.DifficultyState;
import sudoku.computationlogic.GameLogic;
import sudoku.constants.GameState;
import sudoku.constants.Messages;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.computationlogic.GameGenerator;

// class untuk update gamedata ketika ada input dari user
// membuat game baru ketika user menekan tombol dialog ketika game selesai
// menangani ketika user clear grid sudoku
public class ControlLogic implements IUserInterfaceContract.EventListener {

  private IStorage storage;
  private IUserInterfaceContract.View view;
  private DifficultyState difficulty;

  public ControlLogic(IStorage storage, IUserInterfaceContract.View view, DifficultyState difficulty) {
    this.storage = storage;
    this.view = view;
    this.difficulty = difficulty;
  }

  @Override
  public void onSudokuInput(int x, int y, int input) {
    try {
      SudokuGame gameData = storage.getGameData();
      int[][] newGridState = gameData.getCopyOfGridState();
      newGridState[x][y] = input;

      gameData = new SudokuGame(
          GameLogic.checkForCompletion(newGridState),
          newGridState);

      storage.updateGameData(gameData, difficulty);

      view.updateSquare(x, y, input);

      if (gameData.getGameState() == GameState.COMPLETE)
        view.showDialog(Messages.GAME_COMPLETE);
    } catch (IOException e) {
      e.printStackTrace();
      view.showError(Messages.ERROR);
    }
  }

  @Override
  // memunculkan message saat game selesai
  public void onDialogClick() {
    try {
      storage.updateGameData(
          GameLogic.getNewGame(difficulty), difficulty);

      view.updateBoard(storage.getGameData());
    } catch (IOException e) {
      view.showError(Messages.ERROR);
    }
  }

  @Override
  // menghapus semua input user
  public void onClearClick() {
    try {
      SudokuGame gameData = storage.getGameData();
      int clearBoard[][] = gameData.getCopyOfGridState();
      SudokuGame clear = new SudokuGame(GameState.ACTIVE, GameGenerator.getBaseArray(clearBoard));
      view.updateBoard(clear);
    } catch (IOException e) {
      view.showError(Messages.ERROR);
    }
  }
}