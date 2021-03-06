/*
David Gleaton
2/10/19
GameBoard.java
Contains the GameBoard, and will add tokens, check for victory, and give a string representation of the board
 */

package cpsc2150.connectX;

/**
 * Created by David Gleaton
 * This Implementation Holds the information of the GameBoard in a 2d array
 *
 * @invariant MIN_ROW <= NumRow <= MAX_ROW
 * @invariant MIN_COL <= NumCol <= MAX_COL
 * @invariant MIN_WIN <= WinNumber <= MAX_WIN
 *
 * Correspondence number.of.rows = NumRow
 *                 number.of.columns = NumCol
 *                 this = GameBoard[0...NumRow-1][0...NumCol-1]
 *                 number.to.win = WinNumber

 */

public class GameBoard extends AbsGameBoard implements IGameBoard{


    private int NumRow, NumCol,WinNumber;
    char[][] GameBoard = new char[MAX_ROW][MAX_COL];


    //@pre  MIN_ROW <= NumRow <= MAX_ROW
    //      MIN_COL <= NumCol <= MAX_COL
    //      MIN_WIN <= WinNumber <= MAX_WIN
    //@post GameBoard instantiated and set to ' '
    //      WinNumber = Number_to_Win;
    //        NumRow = rows;
    //        NumCol = cols;
    //Constructor that takes no parameters, and initializes GameBoard to be empty, full of ' ' chars
    public GameBoard(int rows, int cols, int Number_to_Win)
    {
        WinNumber = Number_to_Win;
        NumRow = rows;
        NumCol = cols;


        cleanSlate();
    }


    public boolean checkForWin(int c)
    {
            //Holder variables in order to pass the values to the other methods
            int r =0;
            char p =0;

            //for loop that finds the top token in the column and places its information into the necessary variables
            for(int i = 0; i<NumRow; i++)
            {
                if(whatsAtPos(i,c) == ' ')
                {
                    p = GameBoard[i-1][c];
                    r = (i-1);
                    i = (NumRow-1);
                }
            }

            //Calls upon the checkHorizWin method
            if(checkHorizWin(r,c,p) == true)
            {
                return true;
            }
            //Calls upon the checkVertWin method
            if(checkVertWin(r,c,p)==true)
            {
                return true;
            }
            //Calls upon the checkDiagWin method
            if(checkDiagWin(r,c,p)==true)
            {
                return true;
            }

            return false;

    }


    public void placeToken(char p, int c)
    {
            //for-loop that runs thru the column until it finds the first amiable slot
            for(int i = 0; i<NumRow;i++){
                if(whatsAtPos(i,c)==' '){
                    GameBoard[i][c] = p;
                    i=(NumRow-1);
                }
            }


    }


    //@pre  NONE, can be called at any time
    //@post Sets GameBoard[][] = ' '
    //@param Basically I just use this as a failsafe reset
    private void cleanSlate(){
        for(int i =0;i<MAX_ROW;i++){
            for(int a = 0; a<MAX_COL;a++){
                GameBoard[i][a] =' ';
            }
        }
    }


    public char whatsAtPos(int r, int c){
        return GameBoard[r][c];
    }

    public int getNumRow(){
        return NumRow;
    }

    public int getNumColumns(){
        return NumCol;
    }

    public int getNumToWin(){
        return WinNumber;
    }

}



