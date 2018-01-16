package reversiLogic;
public class ConsoleBoard1 extends Board{

    public ConsoleBoard1(int length, int width)
    {
        boardLength=length;
        boardWidth=width;
        //checking for valid board, if valid initialize
        if (boardLength >= 2 && (boardLength % 2) == 0 && boardWidth >= 2 && (boardWidth % 2) == 0) {
            this.initializeBoard();
        } else {
            throw new IllegalArgumentException("board length and width should be greater than 2 and ");
        }

    }
    public ConsoleBoard1()
    {
        boardLength=8;
        boardWidth=8;
        //checking for valid board, if valid initialize
        if (boardLength >= 2 && (boardLength % 2) == 0 && boardWidth >= 2 && (boardWidth % 2) == 0) {
            this.initializeBoard();
        } else {
            throw new IllegalArgumentException("board length and width should be greater than 2 and ");
        }

    }


    public void printBoard() {
//        System.out.print(" |");
//        //cout << " " << '|';
//        for (int j = 1; j <= boardLength; j++) {
//            System.out.print(" ");
//            System.out.print(j);
//            System.out.print(" ");
//            System.out.print('|');
//            //cout << ' ' << j << ' ' << '|';
//        }
//        System.out.print("");
//        //cout << "" << endl;
//        for (int j = 0; j < (4 * boardLength) + 2; j++) {
//            System.out.print('.');
//            //cout << '.';
//        }
//        System.out.print("");
//        //cout << "" << endl;
//        for (int i = 0; i < boardWidth; i++) {
//            System.out.print(i+1);
//            System.out.print('|');
//            //cout << i + 1 << '|';
//            for (int j = 0; j < boardLength; j++) {
//
//                char charToPrint;
//                switch (this.gameBoard.get(i).get(j)){
//                    case Black:
//                        charToPrint = 'X';
//                        break;
//                    case White:
//                        charToPrint = 'O';
//                        break;
//                    default:
//                        charToPrint = ' ';
//                        break;
//                }
//                System.out.print(" |");
//                //cout << ' ' << charToPrint << " |";
//            }
//            System.out.print("");
//            //cout << "" << endl;
//            for (int j = 0; j < (4 * boardLength) + 2; j++) {
//                System.out.print('.');
//                //cout << '.';
//            }
//            System.out.print("");
//            //cout << "" << endl;
//        }
        System.out.print(" |");
        //cout << " " << '|';
        for (int j = 1; j <= boardLength; j++) {
            //cout << ' ' << j << ' ' << '|';
            System.out.print(" " + j + " |");

        }
        //cout << "" << endl;
        System.out.println("");
        for (int j = 0; j < (4 * boardLength) + 2; j++) {
            //cout << '.';
            System.out.print(".");
        }
        System.out.println("");
        //cout << "" << endl;
        for (int i = 0; i < boardWidth; i++) {
            //cout << i + 1 << '|';
            System.out.print(i+1 + "|");
            for (int j = 0; j < boardLength; j++) {

                char charToPrint = 0;
                switch (this.gameBoard.get(i).get(j)) {
                    case Black:
                        charToPrint = 'X';
                        break;
                    case White:
                        charToPrint = 'O';
                        break;
                    default:
                        charToPrint = ' ';
                        break;
                }
                System.out.print(" ");
                System.out.print(charToPrint);
                System.out.print(" |");
                //cout << ' ' << charToPrint << " |";
            }
            System.out.println("");
            //cout << "" << endl;
            for (int j = 0; j < (4 * boardLength) + 2; j++) {
                System.out.print(".");
                //cout << '.';
            }
            System.out.println("");
            //cout << "" << endl;
        }
    }

}
