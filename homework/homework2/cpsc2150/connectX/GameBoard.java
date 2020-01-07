/*
David Gleaton
2/10/19
GameBoard.java
Contains the GameBoard, and will add tokens, check for victory, and give a string representation of the board
 */

package cpsc2150.connectX;

/**
 * @invariant MIN_ROW <= NumRow <= MAX_ROW
 * @invariant MIN_COL <= NumCol <= MAX_COL
 * @invariant MIN_WIN <= WinNumber <= MAX_WIN
 *
 * Correspondence number.of.rows = NumRow
 *                 number.of.columns = NumCol
 *                 this = GameBoard[0...NumRow-1][0...NumCol-1]
 *                 number.to.win = WinNumber

 */

public class GameBoard implements IGameBoard{


    private int NumRow, NumCol,WinNumber;



    //@pre  MIN_ROW <= NumRow <= MAX_ROW
    //      MIN_COL <= NumCol <= MAX_COL
    //      MIN_WIN <= WinNumber <= MAX_WIN
    //@post GameBoard instantiated and set to ' '
    //      WinNumber = Number_to_Win;
    //        NumRow = rows;
    //        NumCol = cols;
    //Constructor that takes no parameters, and initializes GameBoard to be empty, full of ' ' chars
    GameBoard(int rows,int cols,int Number_to_Win)
    {
        WinNumber = Number_to_Win;
        NumRow = rows;
        NumCol = cols;


        cleanSlate();
    }


    public boolean checkIfFree(int c)
    {

        if (whatsAtPos(NumRow-1, c) == ' ') {
            return true;
        } else {
            return false;
        }
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



    @Override
    public String toString(){
        //Creation of a StringBoard the same size as GameBoard in order to flip it for user viewing
        char[][] StringBoard = new char[MAX_ROW][MAX_COL];
        //Creation of the Return String, with the top column numbers listed for useer viewing
        String ReturnBoard ="|0";


        for(int i = 1; i<NumCol;i++) {
            ReturnBoard = ReturnBoard + "|" + i;
            if(i==NumCol-1){
                ReturnBoard = ReturnBoard +"|\n|";
            }
        }
        //for loop that copys all the info from GameBoard into StringBoard, only with the rows fliped
        for(int i =0, j=NumRow-1; i<=NumRow && j>=0; i++,j--){
            for(int a = 0; a<NumCol;a++){
                StringBoard[j][a]=GameBoard[i][a];
            }
        }

        //Nested for loop that feeds all the information from StringBoard into ReturnBoard, properly formatted for the user's viewing
        for(int i = 0; i<NumRow;i++){
            for(int j = 0; j<NumCol;j++){
                if(j==(NumCol-1)){
                    if(i!=(NumRow-1)){
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n|";
                    }
                    if(i==(NumRow-1))
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n";

                }
                else if(j>=9){
                    ReturnBoard = ReturnBoard + StringBoard[i][j] +"| ";
                }
                else{
                    ReturnBoard = ReturnBoard + StringBoard[i][j] +"|";
                }


            }
        }

        return ReturnBoard;

    }


    public boolean checkTie(){

        for(int i =0; i<NumCol;i++){
            if(whatsAtPos((NumRow-1),i)==' '){

                    return false;

            }
        }
        return true;
    }


    public void cleanSlate(){
        for(int i =0;i<MAX_ROW;i++){
            for(int a = 0; a<MAX_COL;a++){
                GameBoard[i][a] =' ';
            }
        }
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



