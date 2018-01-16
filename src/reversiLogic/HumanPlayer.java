package reversiLogic;
import java.util.ArrayList;
import java.util.Scanner;
public class HumanPlayer extends Player {
    /**************************************************************
     * function name: HumanPlayer class builder
     * Input: SquareColor color
     *
     * Function operation: creates a HumanPlayer object of the specified color.
     *      throws an exception if color is Blank
     **************************************************************/
    public HumanPlayer(SquareColor color){
        if (color == SquareColor.Blank) {
            throw new IllegalArgumentException("player's color can only be black or white");
        } else {
            this.playersColor = color;
        }
        command=new InOutTerminal();
    }

    /**************************************************************
     * function name: makeMove
     * Input: vector<BoardCell> possibleMoves
     * @return BoardCell representing player's choice for the next move
     * Function operation: asks the player for input on its next move
     *   untill a valid choice is entered
     **************************************************************/
    public BoardCell makeMove(ArrayList<BoardCell> possibleMoves) {
        if (possibleMoves.size() == 0) {
            throw new IllegalArgumentException("empty possibleMoves vector is invalid");
        }
        int row=0, col=0;
        //variable contains if the user's choice for a move was valid
        boolean ok = false;

        while (!ok) {
//            System.out.print("Your possible moves: ");
//            //cout << "Your possible moves: ";
//            for (int i = 0; i < possibleMoves.size(); i++) {
//                if (i < possibleMoves.size() - 1) {
//                    System.out.print(possibleMoves.get(i)
//                            .boardCellToString() + ",");
//                    //cout << possibleMoves[i].boardCellToString() + ",";
//                } else {
//                    System.out.println(possibleMoves.get(i)
//                            .boardCellToString());
//                   // cout << possibleMoves[i].boardCellToString() << endl;
//                }
//            }
            command.PrintOptions(possibleMoves);
//            System.out.print("\nPlease enter your move in the format: row col\n");
//            System.out.println("(two integers with a space between them, for example: 3 4  )");
//            System.out.println("notice: only the first two integers will be taken");
            command.PrintEnterMovesInForm();
            //Scanner reader = new Scanner(System.in);
            row=command.InInt();
            //row=reader.nextInt();
            //cin >> row;
            //if (!cin.fail()) {
            //if(true){
                //cin >> col;
                //col=reader.nextInt();
                col=command.InInt();
                if(true){
                //if (!cin.fail()) {
                    //adjusting the received co'ordinates (the board starts at 1 but stored as starting at 0)
                    row = row - 1;
                    col = col - 1;
                    BoardCell tmp=new BoardCell(row, col);
                    //checking if the choice is one of the offered moves
                    //if (find(possibleMoves.begin(), possibleMoves.end(), tmp) != possibleMoves.end()) {
                    if(possibleMoves.contains(tmp)){
                        ok = true;
                    } else {
                        //cout << "your choice isn't one of the offered moves" << endl;
                        command.PrintNotPosMove();
                    }
                }
            //}
            if (!ok) {
                //cout << "invalid input, try again\n" << endl;
//                System.out.println("invalid input, try again");
                command.PrintInvaidInput();
            }
            //clearing input error flag and ignoring previous bad input
            //cin.clear();
            //cin.ignore(numeric_limits<streamsize >::max(), '\n');
        }
        BoardCell chosenCell=new BoardCell(row, col);
        return chosenCell;
    }
    private InOutTerminal command;
}
