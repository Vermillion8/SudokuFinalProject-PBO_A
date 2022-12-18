package sudoku.computationlogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sudoku.additions.DifficultyState;
import sudoku.problemdomain.Coordinates;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

// 1. fungsi untuk menghasilkan game baru
// 2. mengalokasikan sebuah nomor pada tiap kotak
// 3. sambil memastikan bahwa nomor tersebut tidak mengganggu ketentuan game
// 4. membuat grid awal dan mengreturn grid yang sudah dihapus sebagian nomornya
public class GameGenerator {
  private static int[][] baseArray;

  public static int[][] getNewGameGrid(DifficultyState difficulty) {
    // try {
    //   clearArray(baseArray);
    // } catch (Exception e) {
    //   e.printStackTrace();
    // }
    baseArray = unsolveGame(getSolvedGame(), difficulty);
    return baseArray;
  }

  private static int[][] getSolvedGame() {
    Random random = new Random(System.currentTimeMillis());
    int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];

    for (int value = 1; value <= GRID_BOUNDARY; value++) {

      int allocations = 0;

      int interrupt = 0;

      List<Coordinates> allocTracker = new ArrayList<>();

      int attempts = 0;

      while (allocations < GRID_BOUNDARY) {

        if (interrupt > 200) {
          allocTracker.forEach(coord -> {
            newGrid[coord.getX()][coord.getY()] = 0;
          });

          interrupt = 0;
          allocations = 0;
          allocTracker.clear();
          attempts++;

          if (attempts > 500) {
            clearArray(newGrid);
            attempts = 0;
            value = 1;
          }
        }

        int xCoordinate = random.nextInt(GRID_BOUNDARY);
        int yCoordinate = random.nextInt(GRID_BOUNDARY);

        if (newGrid[xCoordinate][yCoordinate] == 0) {
          newGrid[xCoordinate][yCoordinate] = value;

          if (GameLogic.sudokuIsInvalid(newGrid)) {
            newGrid[xCoordinate][yCoordinate] = 0;
            interrupt++;
          }

          else {
            allocTracker.add(new Coordinates(xCoordinate, yCoordinate));
            allocations++;
          }
        }
      }
    }
    return newGrid;
  }

  private static int[][] unsolveGame(int[][] solvedGame, DifficultyState difficulty) {
    Random random = new Random(System.currentTimeMillis());

    boolean solvable = false;

    int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

    while (solvable == false) {

      SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);

      int index = 0;
      if (DifficultyState.EASY == difficulty) {
        while (index < 40) {
          int xCoordinate = random.nextInt(GRID_BOUNDARY);
          int yCoordinate = random.nextInt(GRID_BOUNDARY);

          if (solvableArray[xCoordinate][yCoordinate] != 0) {
            solvableArray[xCoordinate][yCoordinate] = 0;
            index++;
          }
        }
      } else if (DifficultyState.MEDIUM == difficulty) {
        while (index < 45) {
          int xCoordinate = random.nextInt(GRID_BOUNDARY);
          int yCoordinate = random.nextInt(GRID_BOUNDARY);

          if (solvableArray[xCoordinate][yCoordinate] != 0) {
            solvableArray[xCoordinate][yCoordinate] = 0;
            index++;
          }
        }
      } else if (DifficultyState.HARD == difficulty) {
        while (index < 50) {
          int xCoordinate = random.nextInt(GRID_BOUNDARY);
          int yCoordinate = random.nextInt(GRID_BOUNDARY);

          if (solvableArray[xCoordinate][yCoordinate] != 0) {
            solvableArray[xCoordinate][yCoordinate] = 0;
            index++;
          }
        }
      }

      int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
      SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);

      solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);

    }

    return solvableArray;
  }

  // hard reset
  private static void clearArray(int[][] newGrid) {
    for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
      for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
        newGrid[xIndex][yIndex] = 0;
      }
    }
  }

  public static int[][] getBaseArray(int[][] gameGrid) {
    // clear the grid
    // get the original unsolved grid back
    clearArray(gameGrid);
    return baseArray;
  }
}