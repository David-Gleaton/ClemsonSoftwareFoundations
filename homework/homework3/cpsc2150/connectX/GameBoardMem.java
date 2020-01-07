package cpsc2150.connectX;

import java.util.*;

/**
 * Created by David Gleaton 3/14/2019
 * This Implementation constructs a Map that will hold the information in the Gameboard
 *
 * @invariant MIN_ROW <= NumRow <= MAX_ROW
 * @invariant MIN_COL <= NumCol <= MAX_COL
 * @invariant MIN_WIN <= WinNumber <= MAX_WIN
 *
 * Correspondence number.of.rows = NumRow
 *                 number.of.columns = NumCol
 *                number.to.win = WinNumber

 */

public class GameBoardMem extends AbsGameBoard implements IGameBoard{

    private int NumRow, NumCol,WinNumber;

    private Map<Integer, List<Character>> GameBoard = new HashMap<>();

    //@pre  MIN_ROW <= NumRow <= MAX_ROW
    //      MIN_COL <= NumCol <= MAX_COL
    //      MIN_WIN <= WinNumber <= MAX_WIN
    //@post GameBoard instantiated and set to ' '
    //      WinNumber = Number_to_Win;
    //        NumRow = rows;
    //        NumCol = cols;
    //Constructor takes in the desired values the user input
    public GameBoardMem(int rows, int cols, int Number_to_Win){
        WinNumber = Number_to_Win;
        NumRow = rows;
        NumCol = cols;



    }

    public boolean checkIfFree(int c) {
        if(!GameBoard.containsKey(c)){
            return true;
        }
        if(GameBoard.get(c).size() == NumRow){
            return false;
        }
        return true;
    }


    public boolean checkForWin(int c){
        //Holder variables in order to pass the values to the other methods
        int TokenRowPosition = GameBoard.get(c).size() - 1;
        int r = TokenRowPosition;
        char p = GameBoard.get(c).get(TokenRowPosition);




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


    public void placeToken(char p, int c){

        if(!GameBoard.containsKey(c)){
            GameBoard.put(c, new ArrayList<Character>());
        }

        GameBoard.get(c).add(p);

    }



    public char whatsAtPos(int r, int c) {

        if(!GameBoard.containsKey(c)){
            return ' ';
        }
        if(GameBoard.get(c).size()<=r){
           return ' ';
        }

        return GameBoard.get(c).get(r);

    }


    public boolean checkTie(){

        if(GameBoard.size() != NumCol){
            return false;
        }
        for(int i = 0; i<NumCol; i++){
            if(GameBoard.get(i).size() != NumRow){
                return false;
            }
        }


        return true;
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
