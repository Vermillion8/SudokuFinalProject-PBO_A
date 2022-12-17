package sudoku.buildlogic;

import java.io.IOException;

import sudoku.additions.DifficultyState;
import sudoku.computationlogic.GameLogic;
import sudoku.persistence.LocalStorageImpl;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.userinterface.logic.ControlLogic;

// fungsi untuk mengambil dan mencari data game, serta memulai gamenya
// jika file gamedata tidak ditemukan, maka membuat game baru
public class SudokuBuildLogic {

  public static void build(IUserInterfaceContract.View userInterface, DifficultyState difficulty) throws IOException {
      SudokuGame initialState;
      IStorage storage = new LocalStorageImpl();
      DifficultyState diffState;

      try {
          diffState = storage.getDifficultyState();
          initialState = storage.getGameData();

          // if user picks a different difficulty, we need to start a new game
          if (difficulty != diffState) {
            initialState = GameLogic.getNewGame(difficulty);
            storage.updateGameData(initialState, difficulty);
          }
      } catch (IOException e) {

          initialState = GameLogic.getNewGame(difficulty);
          storage.updateGameData(initialState, difficulty);
      }

      IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface, difficulty);
      userInterface.setListener(uiLogic);
      userInterface.updateBoard(initialState);
  }
  
}
