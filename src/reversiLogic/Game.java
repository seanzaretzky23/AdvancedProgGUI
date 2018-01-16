package reversiLogic;
import java.util.ArrayList;

public class Game {
    /**************************************************************
     * function name: Game class builder
     * Input: no input
     * @return Game object instance
     * Function operation: creates a Game object,
     *      creates two human players and 8X8 board (default)
     *************************************************************/
    public Game(){

        whosTurn=true;
        noMoves=false;
        gameOver=false;
        this.players=new Player[2];
        this.players[0] = new HumanPlayer(SquareColor.Black);
        this.players[1] = new HumanPlayer(SquareColor.White);
        //calling for ConsoleBoard with the default parameters
        this.board = new ConsoleBoard();
        this.gameLogic = new StandardLogic(this.board);
    };

    /**************************************************************
     * function name: Game class builder
     * Input: int boardSize
     * @return Game object instance
     * Function operation: creates a Game object,
     *      creates two human players and boardSize X boardSize board
     **************************************************************/
    public Game(int boardSize){
        whosTurn=true;
        noMoves=false;
        gameOver=false;
            this.players[0] = new HumanPlayer(SquareColor.Black);
            this.players[1] = new HumanPlayer(SquareColor.White);
            this.board = new ConsoleBoard(boardSize, boardSize);
            this.gameLogic = new StandardLogic(this.board);
    };

    /**************************************************************
     * function name: ~Game (Game class destructor)
     * Input: no input
     * @return no output
     * Function operation: destroys the Game calss instance
     **************************************************************/
    //public ~Game(){};

    /**************************************************************
     * function name: playGame
     * Input: no input
     * @return void
     * Function operation: plays a game of reversi
     **************************************************************/
    public void playGame(){
//        while (true) {
//            if (this.board.gameBoardFull()) {
//                this.gameOver = true;
//            }
//            if (this.gameOver) {
//                int winner = this.gameLogic.whoHasMorePoints();
//                //cout << "Game is over. the final board:" << endl;
//                System.out.println( "Game is over. the final board:");
//                this.board.printBoard();
//                switch(winner) {
//                    case 1:
//                        //cout << "Player1 (X) is the winner" << endl;
//                        System.out.println("Player1 (X) is the winner");
//                        break;
//                    case 2:
//                        //cout << "player2 (O) is the winner" << endl;
//                        System.out.println("Player2 (O) is the winner");
//                        break;
//                    case 3:
//                        //cout << "Its a tie" << endl;
//                        System.out.println("Its a tie");
//                }
//                break;
//            }
//            if (!this.noMoves) {
//                //cout << "Current board:\n" << endl;
//                System.out.println("Current board:\n");
//                this.board.printBoard();
//            }
//            if (whosTurn) {
//                    //next turn belongs to player 1
//                    this.playNextTurn(0);
//                   }
//
//                else {
//                //next turn belongs to player 2
//                this.playNextTurn(1);
//                }
//        }
        while (true) {
            if (this.board.gameBoardFull()) {
                this.gameOver = true;
            }
            if (this.gameOver) {
                int winner = this.gameLogic.whoHasMorePoints();
                System.out.println("Game is over. the final board:");
                //cout << "Game is over. the final board:" << endl;
                this.board.printBoard();
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
                }
                break;
            }
            if (!this.noMoves) {
                //cout << "Current board:\n" << endl;
                this.board.printBoard();
            }
            if (whosTurn == true) {//next turn belongs to player 1
                this.playNextTurn(0);

            } else if (whosTurn == false) {//next turn belongs to player 2
                this.playNextTurn(1);

            }
        }
    };


    /**************************************************************
     * function name: printWhosNextTurn
     * Input: no input
     * @return void
     * Function operation: prints message noticing whos next turn
     **************************************************************/
    private void printWhosNextTurn() {

        if(this.whosTurn){

                //cout << "X: It's your move." << endl;
            System.out.println("X: It's your move.");
        }
        else{
                //cout << "O: It's your move." << endl;
            System.out.println("O: It's your move.");
        }
    };

    /**************************************************************
     * function name: playNextTurn
     * Input: int i (0 or 1, represents the index in the players array)
     * @return void
     * Function operation: performs the next turn in consideration
     *       to with which player it have been called (i)
     **************************************************************/
    private void playNextTurn(int i){
        ArrayList<BoardCell> possibleMoves;
        BoardCell chosenMove = null;

        this.printWhosNextTurn();

        possibleMoves = this.gameLogic.possibleMovesForColor(this.players[i].getColor());
        if (possibleMoves.size() != 0) {
            this.noMoves = false;
            chosenMove = new BoardCell(this.players[i].makeMove(possibleMoves));
            this.whosTurn = this.gameLogic.makeMove(chosenMove, this.players[i].getColor());
        } else {
            if (this.noMoves == false) {
                this.noMoves = true;
                System.out.println("No possible moves. Play passes back to " +
                        "the other player. Press enter to continue.");
                //cin.ignore(numeric_limits<streamsize >::max(), '\n');
                this.whosTurn = !this.whosTurn;
            } else {
                //neither one of the players has valid moves left to make
                this.gameOver = true;
                System.out.println("No possible moves. Press enter to continue.");
                //cout << "No possible moves. Press enter to continue." << endl;
                //cin.ignore(numeric_limits<streamsize >::max(), '\n');
            }
        }
    };



//2!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private Player[] players; // players[0] is for player 1 and players[1] is for player 2
    private GameLogic gameLogic;
    private Board board;
    //variable containing true if there has been no possible moves in the last turn (to know when the game ends)
    private boolean noMoves;
    //variable representing if the game is over
    private boolean gameOver;
    private boolean whosTurn; // who's turn to play, true for player 1 (Black pawns), false for player 2 (White pawns)

}
