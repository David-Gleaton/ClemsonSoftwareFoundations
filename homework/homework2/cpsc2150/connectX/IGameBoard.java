/*
David Gleaton
2/10/19
IGameBoard.java
Contains default methods for checking for victory conditions, and contains all Max and Min values for the game
 */

package cpsc2150.connectX;


/**Interface Specification

 Initialization ensures:
    GameBoard array contains only blank characters and
    is set to MAX_ROW x MAX_COL


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

    static final int MAX_ROW =100, MAX_COL = 100, MAX_WIN = 25, MIN_ROWS = 3,MIN_COL = 3, MIN_WIN = 3;
    char[][] GameBoard = new char[MAX_ROW][MAX_COL];


    //@pre  c >=0 && <number.of.columns
    //@post Returns true if column c is empty
    //      Returns false if column c is full
    boolean checkIfFree(int c);

    //@pre  methods checkHorizWin(),checkVertWin(),checkDiagWin() must exist
    //      c >=0 && <number.of.columns
    //@post Returns true if checkHorizWin(),checkVertWin(),checkDiagWin() return true
    //      Returns false if all checkHorizWin(),checkVertWin(),checkDiagWin() return false
    //
    boolean checkForWin(int c);

    //@pre  c >=0 && <number.of.columns
    //@post Sets char p into the bottom most slot in the specified column
    void placeToken(char p, int c);

    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if there are tokens that are
    //      horizontally in a row >=number.to.win
    default boolean checkHorizWin(int r, int c, char p){
        //Creation of the WinCounter local variable that keeps track of how many in a row of the token there are
        int WinCounter=0;

        //Large if-statement that deals with the X Player tokens
        if(p == 'X')
        {
            //for loop that reads each token to the right of the column in the row of the token, incrementing the WinCounter variable for each continuous token
            for(int i = c; i<this.getNumColumns();i++)
            {
                if(this.whatsAtPos(r,i)=='X'){
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
                    if(this.whatsAtPos(r,(i-1))=='X'){
                        WinCounter++;
                    }
                }
                if(this.whatsAtPos(r,i)=='X'){
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
        //Reseting of the WinCounter for the O Section
        WinCounter = 0;
        //Large if statement that is the exact same as the X section, but deals with O
        if(p == 'O')
        {
            //for loop that reads each token to the right of the column in the row of the token, incrementing the WinCounter variable for each continuous token
            for(int i = c; i<this.getNumColumns();i++)
            {
                if(this.whatsAtPos(r,i)=='O'){
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
                    if(this.whatsAtPos(r,(i-1))=='O'){
                        WinCounter++;
                    }
                }
                if(this.whatsAtPos(r,i)=='O'){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if the Win conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }


            return false;
        }

        return false;
    }

    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if there are tokens that are
    //      vertically in a row >=number.to.win
    default boolean checkVertWin(int r, int c, char p){
        //Creation of the WinCounter local variable that keeps track of how many in a row of the token there are
        int WinCounter=0;

        //Large if-statement that deals with the X Player tokens
        if(p == 'X')
        {
            //for loop that reads each token above in column of the token, incrementing the WinCounter variable for each continuous token
            for(int i = r; i<this.getNumRow();i++)
            {
                if(this.whatsAtPos(i,c)=='X'){
                    WinCounter++;
                }
                else{
                    i = (this.getNumRow()-1);
                }
            }
            //Checks if the Win Conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }
            WinCounter--;


            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the below section of the reading
            for(int i = r; i>0; i--){

                if(i==1){
                    if(this.whatsAtPos(i--,c)=='X'){
                        WinCounter++;
                    }
                }
                if(this.whatsAtPos(i,c)=='X'){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }


            return false;
        }
        //Reseting of the WinCounter for the O section
        WinCounter = 0;
        //Large if statement that is the exact same as the X section, but deals with O
        if(p == 'O')
        {
            //for loop that reads each token above in column of the token, incrementing the WinCounter variable for each continuous token
            for(int i = r; i<this.getNumRow();i++)
            {
                if(this.whatsAtPos(i,c)=='O'){
                    WinCounter++;
                }
                else{
                    i = (this.getNumRow()-1);
                }
            }
            //Checks if the Win Conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }
            WinCounter--;


            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the below section of the reading
            for(int i = r; i>0; i--){

                if(i==1){
                    if(this.whatsAtPos(i--,c)=='O'){
                        WinCounter++;
                    }
                }
                if(this.whatsAtPos(i,c)=='O'){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=this.getNumToWin()){
                return true;
            }


            return false;
        }

        return false;
    }

    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if there are tokens that are
    //      diagonally in a row >=number.to.win
    default boolean checkDiagWin(int r, int c, char p){
        int WinCounter = 0;

        //Large if statement that deals with the X section of the code
        if(p == 'X'){
            //This for loop read the tokens to the upper right of the placed token


            for(int i=r, j=c; i>=0 && j<this.getNumRow();i--,j++){

                if(this.whatsAtPos(i,j)=='X'){
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
                if(this.whatsAtPos(i,j)=='X'){

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
                if(this.whatsAtPos(i,j)=='X'){
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
                if(this.whatsAtPos(i,j)=='X'){
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

        //Large if statement that is the exact same as the X section, but deals with O
        if(p == 'O'){
            //This for loop read the tokens to the upper right of the placed token


            for(int i=r, j=c; i>=0 && j<this.getNumRow();i--,j++){

                if(this.whatsAtPos(i,j)=='O'){
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
                if(this.whatsAtPos(i,j)=='O'){

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
                if(this.whatsAtPos(i,j)=='O'){
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
                if(this.whatsAtPos(i,j)=='O'){
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

        return false;
    }
    //@pre  c >=0 && <number.of.columns
    //      int r and int c must be within the GameBoard index
    //@post Returns GameBoard[r][c]
    default char whatsAtPos(int r, int c){
        if(GameBoard[r][c]=='X'){
            return 'X';
        }
        if(GameBoard[r][c]=='O'){
            return 'O';
        }
        return ' ';
    }
    //@pre  checkForWin == false
    //
    //@post Returns true or false based on if the top value in all columns are full or empty respectively
    boolean checkTie();
    //@pre  NONE, can be called at any time
    //@post Sets GameBoard[][] = ' '
    //@param Basically I just use this as a failsafe reset
    void cleanSlate();

    //@pre
    //@post Returns NumRow
    int getNumRow();

    //@pre
    //@post Returns NumCol
    int getNumColumns();

    //@pre
    //@post Returns WinNumber
    int getNumToWin();
}
