package sudoku.problemdomain;

import java.io.IOException;

import sudoku.additions.DifficultyState;

// class interface untuk menyimpan fungsi yang dipakai untuk persistence
public interface IStorage {
  void updateGameData(SudokuGame game, DifficultyState difficulty) throws IOException;
  SudokuGame getGameData() throws IOException;
  DifficultyState getDifficultyState() throws IOException;
  void deleteGameData() throws IOException;
}
