package sudoku.userinterface;

import sudoku.problemdomain.SudokuGame;

// class interface untuk menyimpan beberapa fungsi yang dipakai di class-class lain
public interface IUserInterfaceContract {

  interface EventListener {
    void onSudokuInput(int x, int y, int input);

    void onDialogClick();

    void onClearClick();
  }

  interface View {
    void setListener(IUserInterfaceContract.EventListener listener);

    void updateSquare(int x, int y, int input);

    void updateBoard(SudokuGame game);

    void showDialog(String message);

    void showError(String message);
  }
}
