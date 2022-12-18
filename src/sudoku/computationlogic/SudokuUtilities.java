package sudoku.computationlogic;

import sudoku.problemdomain.SudokuGame;

// fungsi yang menyimpan beberapa fungsi yang dipakai di class-class lain
// terdapat fungsi untuk menyalin nilai dari sudoku grid
// dan fungsi untuk menyalin array ke dalam array lain
public class SudokuUtilities {

  public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray) {
    for (int xIndex = 0; xIndex < SudokuGame.GRID_BOUNDARY; xIndex++) {
      for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUNDARY; yIndex++) {
        newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
      }
    }
  }

  public static int[][] copyToNewArray(int[][] oldArray) {
    int[][] newArray = new int[SudokuGame.GRID_BOUNDARY][SudokuGame.GRID_BOUNDARY];
    for (int xIndex = 0; xIndex < SudokuGame.GRID_BOUNDARY; xIndex++) {
      for (int yIndex = 0; yIndex < SudokuGame.GRID_BOUNDARY; yIndex++) {
        newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
      }
    }

    return newArray;
  }
}
