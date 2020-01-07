package cpsc2150.connectX;

/*
David Gleaton
2/10/19

 */

public class GameBoard {

    //invariant MAX_ROW = 6, MAX_COL=7, WinNumber = 4
    private int MAX_ROW = 6;
    private int MAX_COL = 7;
    private char[][] GameBoard;
    private int WinNumber = 4;


    //Constructor that takes no parameters, and initializes GameBoard to be empty, full of ' ' chars
    GameBoard()
    {
        GameBoard = new char[MAX_ROW][MAX_COL];
        for(int i =0;i<MAX_ROW;i++){
            for(int a = 0; a<MAX_COL;a++){
                GameBoard[i][a] =' ';
            }
        }
    }

    //@pre  int PlayerInput must be >0 and <=6
    //@post Returns true or false depending on the state of the column
    public boolean checkIfFree(int c)
    {

        if (whatsAtPos(MAX_ROW-1, c) == ' ') {
            return true;
        } else {
            return false;
        }
    }
    //@pre  methods checkHorizWin(),checkVertWin(),checkDiagWin() must exist
    //      int PlayerInput must be >0 and <=6
    //@post Returns true or false if the conditions for victory are met
    public boolean checkForWin(int c)
    {
            //Holder variables in order to pass the values to the other methods
            int r =0;
            char p =0;

            //for loop that finds the top token in the column and places its information into the necessary variables
            for(int i = 0; i<MAX_ROW; i++)
            {
                if(whatsAtPos(i,c) == ' ')
                {
                    p = GameBoard[i-1][c];
                    r = (i-1);
                    i = (MAX_ROW-1);
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

    //@pre  int PlayerInput must be >0 and <=6
    //@post Sets the char input into the bottom most slot in the specified column
    public void placeToken(char p, int c)
    {
            //for-loop that runs thru the column until it finds the first amiable slot
            for(int i = 0; i<MAX_ROW;i++){
                if(whatsAtPos(i,c)==' '){
                    GameBoard[i][c] = p;
                    i=(MAX_ROW-1);
                }
            }


    }

    //@pre  int PlayerInput must be >0 and <=6
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if the conditions are met
    private boolean checkHorizWin(int r, int c, char p)
    {
        //Creation of the WinCounter local variable that keeps track of how many in a row of the token there are
        int WinCounter=0;
        //Large if-statement that deals with the X Player tokens
        if(p == 'X')
        {
            //for loop that reads each token to the right of the column in the row of the token, incrementing the WinCounter variable for each continuous token
            for(int i = c; i<MAX_COL;i++)
            {
                if(whatsAtPos(r,i)=='X'){
                    WinCounter++;
                }
                else{
                    i = (MAX_COL-1);
                }
            }

            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the left section of the reading
            if(WinCounter>=WinNumber){
                return true;
            }else if(c>3){
                WinCounter--;
            }

            //for loop that deals with reading to the left of the placed token
            for(int i = c; i>0; i--){

                if(whatsAtPos(r,i)=='X'){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if win conditions have been met
            if(WinCounter>=WinNumber){
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
            for(int i = c; i<MAX_COL;i++)
            {
                if(whatsAtPos(r,i)=='O'){
                    WinCounter++;
                }
                else{
                    i = (MAX_COL-1);
                }
            }
            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the left section of the reading
            if(WinCounter>=WinNumber){
                return true;
            }else if(c>3){
                WinCounter--;
            }

            //for loop that deals with reading to the left of the placed token
            for(int i = c; i>0; i--){

                if(whatsAtPos(r,i)=='O'){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if the Win conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }


            return false;
        }

        return false;
    }
    //@pre  int PlayerInput must be >0 and <=6
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if the conditions are met
    private boolean checkVertWin(int r, int c, char p)
    {
        //Creation of the WinCounter local variable that keeps track of how many in a row of the token there are
        int WinCounter=0;
        //Large if-statement that deals with the X Player tokens
        if(p == 'X')
        {
            //for loop that reads each token above in column of the token, incrementing the WinCounter variable for each continuous token
            for(int i = r; i<MAX_ROW;i++)
            {
                if(whatsAtPos(i,c)=='X'){
                    WinCounter++;
                }
                else{
                    i = (MAX_ROW-1);
                }
            }
            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }

            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the below section of the reading
            for(int i = r; i>0; i--){

                if(whatsAtPos(i,c)=='X'){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
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
            for(int i = r; i<MAX_ROW;i++)
            {
                if(whatsAtPos(i,c)=='O'){
                    WinCounter++;
                }
                else{
                    i = (MAX_ROW-1);
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }

            //Checks if the Win conditions have been met
            //WinCounter is not reset in order to keep continouity for the below section of the reading
            for(int i = r; i>0; i--){

                if(whatsAtPos(i,c)=='O'){
                    WinCounter++;
                }
                else{
                    i=1;
                }

            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }


        }

        return false;
    }
    //@pre  int PlayerInput must be >0 and <=6
    //      int r and int c must be within the GameBoard index
    //@post Returns true or false to the checkForWin method if the conditions are met
    private boolean checkDiagWin(int r, int c, char p){
        int WinCounter = 0;

        //Large if statement that deals with the X section of the code
        if(p == 'X'){
            //This for loop read the tokens to the upper right of the placed token
            for(int i=r, j=c; i>0 && j<MAX_COL;i--,j++){
                if(whatsAtPos(i,j)=='X'){
                    WinCounter++;

                }
                else{
                    break;
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }


            //This for loop read the tokens to the lower right of the placed token
            for(int i = r, j=c; i<MAX_ROW && j<MAX_COL;i++,j++){
                if(whatsAtPos(i,j)=='X'){
                    WinCounter++;

                }
                else{
                    break;
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }
            else{
                //Reset the WinCounter for the Left slant reading
                WinCounter =0;
            }

            //This for loop read the tokens to the Upper left of the placed token
            for(int i=r,j=c;i>0&&j>0;i--,j--){
                if(whatsAtPos(i,j)=='X'){
                    WinCounter++;

                }
                else{
                    i=1;
                }
            }
            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }

            //This for loop read the tokens to the lower left of the placed token
            for(int i=r,j=c;i<MAX_ROW&&j>0;i++,j--){
                if(whatsAtPos(i,j)=='X'){
                    WinCounter++;

                }
                else{
                    break;
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }else{
                return false;
            }


        }

        //Large if statement that is the exact same as the X section, but deals with O
        if(p == 'O'){
            //This for loop read the tokens to the upper right of the placed token
            for(int i=r, j=c; i>0 && j<MAX_COL;i--,j++){
                if(whatsAtPos(i,j)=='O'){
                    WinCounter++;

                }
                else{
                    break;
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }


            //This for loop read the tokens to the lower right of the placed token
            for(int i = r, j=c; i<MAX_ROW && j<MAX_COL;i++,j++){
                if(whatsAtPos(i,j)=='O'){
                    WinCounter++;

                }
                else{
                    break;
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }
            else{
                //Reset the WinCounter for the Left slant reading
                WinCounter =0;
            }

            //This for loop read the tokens to the upper left of the placed token
            for(int i=r,j=c;i>0&&j>0;i--,j--){
                if(whatsAtPos(i,j)=='O'){
                    WinCounter++;

                }
                else{
                    i=1;
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }

            //This for loop read the tokens to the lower left of the placed token
            for(int i=r,j=c;i<MAX_ROW&&j>0;i++,j--){
                if(whatsAtPos(i,j)=='O'){
                    WinCounter++;

                }
                else{
                    break;
                }
            }

            //Checks if the Win Conditions have been met
            if(WinCounter>=WinNumber){
                return true;
            }else{
                return false;
            }


        }

        return false;
    }

    //@pre  int PlayerInput must be >0 and <=6
    //      int r and int c must be within the GameBoard index
    //@post returns the character that is within that location of the GameBoard
    private char whatsAtPos(int r, int c){
        if(GameBoard[r][c]=='X'){
            return 'X';
        }
        if(GameBoard[r][c]=='O'){
            return 'O';
        }
        return ' ';
    }

    @Override
    public String toString(){
        //Creation of a StringBoard the same size as GameBoard in order to flip it for user viewing
        char[][] StringBoard = new char[MAX_ROW][MAX_COL];
        //Creation of the Return String, with the top column numbers listed for useer viewing
        String ReturnBoard ="|0|1|2|3|4|5|6|\n|";

        //for loop that copys all the info from GameBoard into StringBoard, only with the rows fliped
        for(int i =0, j=MAX_ROW-1;i<MAX_ROW;i++,j--){
            for(int a = 0; a<MAX_COL;a++){
                //System.out.println("test");
                StringBoard[j][a]=GameBoard[i][a];
            }
        }

        //Nested for loop that feeds all the information from StringBoard into ReturnBoard, properly formatted for the user's viewing
        for(int i = 0; i<MAX_ROW;i++){
            for(int j = 0; j<MAX_COL;j++){
                if(j==(MAX_COL-1)){
                    if(i!=(MAX_ROW-1)){
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n|";
                    }
                    else{
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n";
                    }
                }
                else{
                    ReturnBoard = ReturnBoard + StringBoard[i][j] +"|";
                }
            }
        }

        return ReturnBoard;

    }

    //@pre  GameBoard must be initialized to ' ' chars
    //@post Returns true or false based on if the top value in all columns are full or empty respectively
    public boolean checkTie(){
        for(int i =0; i<MAX_COL;i++){
            if(whatsAtPos(MAX_ROW-1,i)==' '){
                return false;
            }
        }
        return true;
    }

    //@pre  A victory or tie must have been met
    //@post wipss the GameBoard array to be full of ' ' chars
    //@param Basically I just use this to reset
    public void cleanSlate(){
        for(int i =0;i<MAX_ROW;i++){
            for(int a = 0; a<MAX_COL;a++){
                GameBoard[i][a] =' ';
            }
        }
    }

    }



