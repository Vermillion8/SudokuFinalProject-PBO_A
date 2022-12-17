package sudoku.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sudoku.additions.DifficultyState;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;

// update file gamedata dan difficultydata setelah setiap input user
// mengambil data gamedata dan difficultydata untuk memulai game
public class LocalStorageImpl implements IStorage {

  private static File GAME_DATA = new File(
      "src/sudoku/persistence",
      "gamedata.txt");
  private static File DIFFICULTY_DATA = new File(
      "src/sudoku/persistence",
      "difficultydata.txt");

  @Override
  public void updateGameData(SudokuGame game, DifficultyState difficulty) throws IOException {
    try {

      FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(game);
      objectOutputStream.close();
      fileOutputStream.close();

      FileOutputStream diffFileOutputStream = new FileOutputStream(DIFFICULTY_DATA);
      ObjectOutputStream diffObjectOutputStream = new ObjectOutputStream(diffFileOutputStream);
      diffObjectOutputStream.writeObject(difficulty);
      diffObjectOutputStream.close();
      diffFileOutputStream.close();

    } catch (IOException e) {
      throw new IOException("Unable to access Game Data");
    }
  }

  @Override
  public SudokuGame getGameData() throws IOException {

    FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
    try {
      SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
      objectInputStream.close();
      return gameState;
    } catch (ClassNotFoundException e) {
      objectInputStream.close();
      throw new IOException("File Not Found");
    }
  }

  @Override
  public DifficultyState getDifficultyState() throws IOException {
    FileInputStream diffFileInputStream = new FileInputStream(DIFFICULTY_DATA);
    ObjectInputStream diffObjectInputStream = new ObjectInputStream(diffFileInputStream);

    try {
      DifficultyState difficultyState = (DifficultyState) diffObjectInputStream.readObject();
      diffObjectInputStream.close();
      return difficultyState;
    } catch (ClassNotFoundException e) {
      diffObjectInputStream.close();
      throw new IOException("File Not Found");
    }
  }

  @Override
  public void deleteGameData() throws IOException {
    GAME_DATA.delete();
  }
}
