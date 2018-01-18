package reversilogic;
public class BoardCell {
    private int xCor;
    private int yCor;
    /**************************************************************
     * function name: BoardCell class builder
     * Input:int x,int y
     * @return BoardCell object instance
     * Function operation: creates a BoardCell object
     **************************************************************/
    public BoardCell(int x, int y){
        xCor=x;
        yCor=y;
    };

    /**************************************************************
     * function name: BoardCell copy constructor
     * Input: no input
     * @return BoardCell object instance
     * Function operation: creates a BoardCell object that is a copy
     *       of the received instance of BoardCell
     **************************************************************/
    public BoardCell(BoardCell existingBoardCell){
        xCor=existingBoardCell.getXCor();
        yCor=existingBoardCell.getYCor();
    };

    /**************************************************************
     * function name: getXCor
     * Input: no input
     * @return int
     * Function operation: returns the xCor of the BoardCell instance
     **************************************************************/
    public int getXCor(){
        return this.xCor;
    };

    /**************************************************************
     * function name: getYCor
     * Input: no input
     * @return int
     * Function operation: returns the yCor of the BoardCell instance
     **************************************************************/
    public int getYCor(){
        return this.yCor;

    };

    /**************************************************************
     * function name: boardCellToString
     * Input: no input
     * @return string
     * Function operation: returns the string that represents
     *       the BoardCell instance
     **************************************************************/
    public String boardCellToString() {
        String str;
        Integer convertX, convertY;
        //adjusting the co'ordinates because the board starts at 1 not 0
        convertX=this.xCor + 1;
        convertY= this.yCor + 1;
        str = "(" + convertX.toString() + "," + convertY.toString() + ")";
        return str;
    };

    /**************************************************************
     * function name: operator ==
     * Input: const BoardCell &boardCell
     * @return bool
     * Function operation: overloads the == operator for BoardCell class
     *       returns true if received instance equals to this instance,
     *       false otherwise
     **************************************************************/
    @Override
    public boolean equals(Object o) {
        final BoardCell secondCell = (BoardCell) o;
        return((this.xCor==secondCell.xCor)
                &&(this.yCor==secondCell.yCor));
    }

}
