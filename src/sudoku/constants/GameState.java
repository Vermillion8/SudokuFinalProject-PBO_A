package sudoku.constants;

// class enum untuk konstanta game state



public enum GameState {
  COMPLETE, //digunakan ketika game sudoku sudah complete
  ACTIVE, //digunakan ketika grid sudoku sedang dimainkan, namun user melakukan
          //close tab sebelum game selesai

  NEW //digunakan ketika grid sudoku baru dibuat
}
