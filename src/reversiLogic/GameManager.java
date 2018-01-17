package reversiLogic;
import java.util.ArrayList;

public class GameManager {
    /**************************************************************
     * function name: Game class builder
     * Input: no input, InOutAbs communicationAdapt
     * @return Game object instance
     * Function operation: creates a Game object,
     *      creates two human players and 8X8 board (default)
     *************************************************************/
    public GameManager(InOutAbs communicationAdapt){

        whosTurn=true;
        noMoves=false;
        gameOver=false;
        this.players=new Player[2];
        this.players[0] = new HumanPlayer(Board.SquareColor.Black, communicationAdapt);
        this.players[1] = new HumanPlayer(Board.SquareColor.White, communicationAdapt);
        //calling for ConsoleBoard with the default parameters
        this.board = new ConsoleBoard();
        this.gameLogic = new StandardLogic(this.board);
        command=communicationAdapt;
    };

    /**************************************************************
     * function name: Game class builder
     * Input: int boardSize, InOutAbs communicationAdapt
     * @return Game object instance
     * Function operation: creates a Game object,
     *      creates two human players and boardSize X boardSize board
     **************************************************************/
    public GameManager(int boardSize, InOutAbs communicationAdapt){
        whosTurn=true;
        noMoves=false;
        gameOver=false;
        this.players=new Player[2];
        this.players[0] = new HumanPlayer(Board.SquareColor.Black, communicationAdapt);
        this.players[1] = new HumanPlayer(Board.SquareColor.White, communicationAdapt);
        this.board = new ConsoleBoard(boardSize, boardSize);
        this.gameLogic = new StandardLogic(this.board);
        command=communicationAdapt;
    };

    /**************************************************************
     * function name: playNextGameIteration
     * Input: no input
     * @return void
     * Function operation: plays a game of reversi
     **************************************************************/
    public void playNextGameIteration(){
    	    
    	if (checkForEndOfGame())
    		return;
            
    	if (!this.noMoves) {
    		command.PrintBoard(this.board);
    	}
    	if (whosTurn == true) {//next turn belongs to player 1
    		this.playNextTurn(0);
    	} else {
    		//next turn belongs to player 2
    		this.playNextTurn(1);
    	}
    	command.PrintBoard(this.board); 
    	checkForEndOfGame();
    }
    
    /**************************************************************
     * function name: checkForEndOfGame
     * Input: no input
     * @return boolean
     * Function operation: checks if its the game ended, if it did
     *	performs end of game proceeder
     **************************************************************/
    private boolean checkForEndOfGame() {
    	boolean returnValue = false;
    	if (this.board.gameBoardFull()) {
    		this.gameOver = true;
    	}
        if (this.gameOver) {
            int winner = this.gameLogic.whoHasMorePoints();
            command.PrintGameOver();
            command.PrintWhoWon(winner);
            returnValue = true;
        }
        return returnValue;
    }


    /**************************************************************
     * function name: printWhosNextTurn
     * Input: no input
     * @return void
     * Function operation: prints message noticing whos next turn
     **************************************************************/
    private void printWhosNextTurn() {

        command.PrintWhosTurn(this.whosTurn);
//        if(this.whosTurn){
//
//                //cout << "X: It's your move." << endl;
//            System.out.println("X: It's your move.");
//        }
//        else{
//                //cout << "O: It's your move." << endl;
//            System.out.println("O: It's your move.");
//        }
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
            BoardCell playersChoiceTmp = this.players[i].makeMove(possibleMoves);
            //if the players choice was invalid
            if (playersChoiceTmp == null)
            	return;
            chosenMove = new BoardCell(playersChoiceTmp);
            this.whosTurn = this.gameLogic.makeMove(chosenMove, this.players[i].getColor());
            this.printWhosNextTurn();
            //checking if there are options for the next player, to maybe skip his turn or finish the game (if no more moves for anybody)
            //getting the index of the other player
        	int j = Math.abs(i - 1);
            ArrayList<BoardCell> possibleMovesForNextTurn = this.gameLogic.possibleMovesForColor(this.players[j].getColor());
            if (possibleMovesForNextTurn.size() == 0) {
            	this.checkIfNoOptionsInNextMove(i);
            	this.printWhosNextTurn();
            } 
        } else {
        	//getting the index of the other player
        	int j = Math.abs(i - 1);
        	this.checkIfNoOptionsInNextMove(j);
        	this.printWhosNextTurn();
        }
    }
    
    private void checkIfNoOptionsInNextMove(int j) {
    	ArrayList<BoardCell> possibleMovesForNextTurn = this.gameLogic.possibleMovesForColor(this.players[j].getColor());
    	if (possibleMovesForNextTurn.size() != 0) {
    		//there are no moves only for the current player, the game isn't over yet
    		this.noMoves = true;
    		command.PrintNoMoves(false);
    		this.whosTurn = !this.whosTurn;
    	} else {
    		//the game is over because non of the players has possible moves left
    		this.gameOver = true;
    		command.PrintNoMoves(true);
    	}
    }
    
    public Board.SquareColor[][] getGameBoard() {
    	return this.board.getBoard();
    }

    private Player[] players; // players[0] is for player 1 and players[1] is for player 2
    private GameLogic gameLogic;
    private Board board;
    //variable containing true if there has been no possible moves in the last turn (to know when the game ends)
    private boolean noMoves;
    //variable representing if the game is over
    private boolean gameOver;
    private boolean whosTurn; // who's turn to play, true for player 1 (Black pawns), false for player 2 (White pawns)
    InOutAbs command;

}
