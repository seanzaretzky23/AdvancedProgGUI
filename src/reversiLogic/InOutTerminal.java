package reversiLogic;
import java.util.ArrayList;
import java.util.Scanner;

import reversiLogic.Board.SquareColor;

public class InOutTerminal implements InOutAbs {

	private Scanner reader;
	
	public InOutTerminal() {
		reader = new Scanner(System.in);
	}
	
	@Override
    public BoardCell receiveMove(){
        int row = reader.nextInt(); // check for input validity!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int col = reader.nextInt();
        //adjusting the received co'ordinates (the board starts at 1 but stored as starting at 0)
        row = row - 1;
        col = col - 1;
        return new BoardCell(row, col);
    }

    @Override
    public void PrintString(String str){
        System.out.print(str);
    }

    @Override
    public void PrintWhosTurn(boolean whosTurn) {
        if(whosTurn){

            //cout << "X: It's your move." << endl;
            System.out.println("X: It's your move.");
        }
        else{
            //cout << "O: It's your move." << endl;
            System.out.println("O: It's your move.");
        }
    }
    @Override
    public void PrintOptions(ArrayList<BoardCell> possibleMoves){

        System.out.print("Your possible moves: ");
        //cout << "Your possible moves: ";
        for (int i = 0; i < possibleMoves.size(); i++) {
            if (i < possibleMoves.size() - 1) {
                System.out.print(possibleMoves.get(i)
                        .boardCellToString() + ",");
                //cout << possibleMoves[i].boardCellToString() + ",";
            } else {
                System.out.println(possibleMoves.get(i)
                        .boardCellToString());
                // cout << possibleMoves[i].boardCellToString() << endl;
            }
        }
    };
    @Override
    public void PrintNotPosMove(){
        System.out.println("your choice isn't one of the possible moves");
    }

    @Override
   public void PrintEnterMovesInForm(){
       System.out.print("\nPlease enter your move in the format: row col\n");
       System.out.println("(two integers with a space between them, for example: 3 4  )");
       System.out.println("notice: only the first two integers will be taken");
   }
   @Override
    public void PrintInvaidInput(){
        System.out.println("invalid input, try again");
   }
   @Override
    public void PrintBoard(Board board)
    {
	   SquareColor[][] existingBoard = board.getBoard();
    	System.out.print(" |");
        //cout << " " << '|';
        for (int j = 1; j <= existingBoard.length; j++) {
            //cout << ' ' << j << ' ' << '|';
            System.out.print(" " + j + " |");

        }
        //cout << "" << endl;
        System.out.println("");
        for (int j = 0; j < (4 * existingBoard.length) + 2; j++) {
            //cout << '.';
            System.out.print(".");
        }
        System.out.println("");
        //cout << "" << endl;
        for (int i = 0; i < existingBoard[0].length; i++) {
            //cout << i + 1 << '|';
            System.out.print(i+1 + "|");
            for (int j = 0; j < existingBoard.length; j++) {

                char charToPrint = 0;
                switch (existingBoard[i][j]) {
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
            for (int j = 0; j < (4 * existingBoard.length) + 2; j++) {
                System.out.print(".");
                //cout << '.';
            }
            System.out.println("");
            //cout << "" << endl;
        }
    }
   @Override
    public void PrintGameOver(){
        System.out.println("Game is over. the final board:");
    }

    @Override
    public void PrintNoMoves(boolean isEnd) {
        if(isEnd)
            System.out.println("No possible moves. Press enter to continue.");
        else
            System.out.println("No possible moves. Play passes back to " +
                    "the other player. Press enter to continue.");


    }

    @Override
    public void PrintWhoWon(int winner){
        switch(winner) {
        	case 1:
        		System.out.println("Player1 (X) is the winner");
        		//cout << "Player1 (X) is the winner" << endl;
        		break;
        	case 2:
        		System.out.println("player2 (O) is the winner");
        		//cout << "player2 (O) is the winner" << endl;
        		break;
        	case 3:
        		System.out.println("Its a tie");
        		//cout << "Its a tie" << endl;
        		break;
        }
    }
}

