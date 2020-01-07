/*
David Gleaton
2/10/19
IGameBoard.java
Contains default methods for checking for victory conditions, and contains all Max and Min values for the game
 */

package cpsc2150.connectX;


/**Interface Specification
    Holds the Gameboard information, including placements of the tokens, and will
    check for victories and ties

 Initialization ensures:
    GameBoard will be able to be set to MIN_(ROW/COL) * MAX(ROW/COL)


 Defines:    MAX_ROW: 100
             MAX_COL = 100
             MAX_WIN = 25
             MIN_ROWS = 3
             MIN_COL = 3
             MIN_WIN = 3
            GameBoard = char[][]
            number.of.columns: Z
            number.of.rows: Z
            number.to.win: Z

 Constraints: 0 <= NumRow <= MAX_ROW
              0 <= NumCol <= MAX_COL


 */
public interface IGameBoard {

    static final int MAX_ROW =100, MAX_COL = 100, MAX_WIN = 25, MIN_ROWS = 3,MIN_COL = 3, MIN_WIN = 3, MAX_PLAYERS = 10, MIN_PLAYERS = 2;



    //@pre  c >=0 && <number.of.columns
    //@post Returns true if column c is empty
    //      Returns false if column c is full
    //@returns A true statement if the specified column is empty
    //          This method takes in the specified column and checks if it is empty
    //default
    default boolean checkIfFree(int c){

        for(int i = 0; i<this.getNumRow(); i++) {
            if (this.whatsAtPos(i,c) == ' ') {
                return true;
            }
        }
        return false;
    }

    //@pre  methods checkHorizWin(),checkVertWin(),checkDiagWin() must exist
    //      c >=0 && <number.of.columns
    //@post Returns true if checkHorizWin(),checkVertWin(),checkDiagWin() return true
    //      Returns false if all checkHorizWin(),checkVertWin(),checkDiagWin() return false
    //@returns A true statement iff checkHorizWin(),checkVertWin(),checkDiagWin() return true
    //      This method mainly draws out the token that was last placed, passing that information
    //      to the checkHorizWin(),checkVertWin(),checkDiagWin() methods
    boolean checkForWin(int c);

    //@pre  c >=0 && <number.of.columns
    //@post Sets char p into the bottom most slot in the specified column
    //@returns  This method takes in the specified character and column number
    //      in order to place that character to the bottom of the column.
    //
    void placeToken(char p, int c);


    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if there are tokens that are
    //      horizontally in a row >=number.to.win
    //@returns  This method takes in the parameters and checks to the right and left of the position
    //      If there are >=Number.to.Win in a row from that position, it returns true, else, false
    default boolean checkHorizWin(int r, int c, char p){
        //Creation of the WinCounter local variable that keeps track of how many in a row of the token there are
        int WinCounter=0;
        //Large if-statement that deals with the Player tokens

            //for loop that reads each token to the right of the column in the row of the token, incrementing the WinCounter variable for each continuous token
            for(int i = c; i<this.getNumColumns();i++)
            {
                if(this.whatsAtPos(r,i)==p){
                    WinCounter++;
                }
                else{
                    i = (this.getNumColumns()-1);
                }
            }

            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the left section of the reading
            if(WinCounter>=this.getNumToWin()){
                return true;
            }else{
                WinCounter--;
            }

            //for loop that deals with reading to the left of the placed token
            for(int i = c; i>0; i--){
                if(i==1){
                    if(this.whatsAtPos(r,(i-1))==p){
                        WinCounter++;
                    }
                }
                if(this.whatsAtPos(r,i)==p){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if win conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }


            return false;



    }

    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if there are tokens that are
    //      vertically in a row >=number.to.win
    //@returns  This method takes in the parameters and checks above and below the position
    //      If there are >=Number.to.Win in a row from that position, it returns true, else, false
    default boolean checkVertWin(int r, int c, char p){
        //Creation of the WinCounter local variable that keeps track of how many in a row of the token there are
        int WinCounter=0;
        //Large if-statement that deals with the Player tokens

            //for loop that reads each token above in column of the token, incrementing the WinCounter variable for each continuous token
            for(int i = r; i<this.getNumRow();i++)
            {


                if(this.whatsAtPos(i,c)==p){
                    WinCounter++;


                    //Checks if the Win Conditions have been met
                    if(WinCounter==this.getNumToWin()){
                        return true;
                    }
                }
                else{

                    break;
                }
            }
            //Checks if the Win Conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }else{
                WinCounter--;
            }

            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the below section of the reading
            for(int i = r; i>=0; i--){

                if(this.whatsAtPos(i,c)==p){
                    WinCounter++;


                    //Checks if the Win Conditions have been met
                    if(WinCounter==this.getNumToWin()){
                        return true;
                    }
                }
                else{

                    break;
                }

            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }


            return false;




    }

    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if there are tokens that are
    //      diagonally in a row >=number.to.win
    //@returns  This method takes in the parameters and checks all diagonals from the position
    //      If there are >=Number.to.Win in a row from that position, it returns true, else, false
    default boolean checkDiagWin(int r, int c, char p){
        int WinCounter = 0;

        //Large if statement that deals with the X section of the code

            //This for loop read the tokens to the upper right of the placed token


            for(int i=r, j=c; i>=0 && j<this.getNumRow();i--,j++){

                if(this.whatsAtPos(i,j)==p){
                    WinCounter++;


                    //Checks if the Win Conditions have been met
                    if(WinCounter==this.getNumToWin()){
                        return true;
                    }
                }
                else{

                    break;
                }
            }






            WinCounter--;


            //This for loop read the tokens to the lower left of the placed token
            for(int i = r, j=c; i<this.getNumRow() && j>=0;i++,j--){
                if(this.whatsAtPos(i,j)==p){

                    WinCounter++;
                    //Checks if the Win Conditions have been met
                    if(WinCounter==this.getNumToWin()){
                        return true;

                    }
                }
                else{

                    break;
                }
            }






            WinCounter =0;


            //This for loop read the tokens to the Upper left of the placed token
            for(int i=r,j=c;i>=0&&j>=0;i--,j--){
                if(this.whatsAtPos(i,j)==p){
                    WinCounter++;

                    //Checks if the Win Conditions have been met
                    if(WinCounter==this.getNumToWin()){
                        return true;
                    }
                }
                else{

                    break;
                }
            }



            WinCounter--;

            //This for loop read the tokens to the lower right of the placed token
            for(int i=r,j=c;i<this.getNumRow()&&j<this.getNumColumns();i++,j++){
                if(this.whatsAtPos(i,j)==p){
                    WinCounter++;

                    //Checks if the Win Conditions have been met
                    if(WinCounter==this.getNumToWin()){
                        return true;
                    }
                }
                else{

                    break;
                }
            }



            return false;






    }
    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns the value at that given position
    //@returns  This method takes in the position parameters and returns the value at said position
    char whatsAtPos(int r, int c);
    //@pre  checkForWin == false
    //      checkIfFree(c) == false
    //@post Returns true or false based on if the top value in all columns are full or empty respectively
    //@returns  This method checks to see if any more columns are empty, and if not, returns true
    default boolean checkTie(){
        for(int i = 0; i<this.getNumColumns(); i++){
            if(this.whatsAtPos(this.getNumRow()-1,i) == ' '){
                return false;
            }
        }
        return true;
    }


    //@pre
    //@post Returns NumRow
    int getNumRow();

    //@pre
    //@post Returns NumCol
    int getNumColumns();

    //@pre
    //@post Returns WinNumber
    int getNumToWin();

    //@pre
    //@post Returns a string representation of the GameBoard, formatted for the user's ease of viewing
    String toString();


}
