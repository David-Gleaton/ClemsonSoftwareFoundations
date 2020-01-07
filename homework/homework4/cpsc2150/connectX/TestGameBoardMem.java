package cpsc2150.connectX;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestGameBoardMem  {

    //Test that the values passed into the constructor are initialized
    @Test
    public void Test_Constructor_Test_Initialization() {
        char[][] ExpectedArray = ArrayBuilder();
        IGameBoard gb = BoardFactory(5,10,3);

        assertEquals(ExpectedtoString(5,10,ExpectedArray), gb.toString());

        assertTrue(5 == gb.getNumRow());
        assertTrue(10 == gb.getNumColumns());
        assertTrue(3 == gb.getNumToWin());
    }

    //Test that the maximim size of GameBoard is created
    @Test
    public void Test_Constructor_UpperBoundary(){
        char[][] ExpectedArray = ArrayBuilder();
        IGameBoard gb = BoardFactory(100,100,25);

        assertEquals(ExpectedtoString(100,100,ExpectedArray), gb.toString());

        assertTrue(100 == gb.getNumRow());
        assertTrue(100 == gb.getNumColumns());
        assertTrue(25 == gb.getNumToWin());
    }

    //Test that the minimum size of GameBoard is created
    @Test
    public void Test_Constructor_LowerBoundary(){
        char[][] ExpectedArray = ArrayBuilder();
        IGameBoard gb = BoardFactory(3,3,3);

        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        assertTrue(3 == gb.getNumRow());
        assertTrue(3 == gb.getNumColumns());
        assertTrue(3 == gb.getNumToWin());
    }

    //Check that CheckIfFree returns false if the column is not free
    @Test
    public void Test_CheckIfFree_IsNotFree(){
        IGameBoard gb = BoardFactory(3,3,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',1);
        gb.placeToken('O',1);
        gb.placeToken('X',1);
        ExpectedArray[0][1] = 'X';
        ExpectedArray[1][1] = 'O';
        ExpectedArray[2][1] = 'X';
        assertTrue(!gb.checkIfFree(1));
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //Check that CheckIfFree returns true if the column is free
    @Test
    public void Test_CheckIfFree_IsFree(){
        IGameBoard gb = BoardFactory(3,3,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',1);
        gb.placeToken('O',1);
        ExpectedArray[0][1] = 'X';
        ExpectedArray[1][1] = 'O';

        assertTrue(gb.checkIfFree(1));
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //Check that CheckIfFree can read the zeroth column of the GameBoard
    @Test
    public void Test_CheckIfFree_IsNotFree_At_Zero(){
        IGameBoard gb = BoardFactory(3,3,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',0);
        gb.placeToken('O',0);
        gb.placeToken('O',0);
        ExpectedArray[0][0] = 'X';
        ExpectedArray[1][0] = 'O';
        ExpectedArray[2][0] = 'O';

        assertTrue(!gb.checkIfFree(0));
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check that checkHorizWin can read to the left of the placed token
    @Test
    public void Test_checkHorizWin_Left_Win(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('X',4);
        ExpectedArray[0][2] = 'X';
        ExpectedArray[0][3] = 'X';
        ExpectedArray[0][4] = 'X';

        assertTrue(gb.checkHorizWin(0,4,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check that checkHorizWin can read to the right of the placed token
    @Test
    public void Test_checkHorizWin_Right_Win(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('X',4);
        ExpectedArray[0][2] = 'X';
        ExpectedArray[0][3] = 'X';
        ExpectedArray[0][4] = 'X';

        assertTrue(gb.checkHorizWin(0,2,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check that checkHorizWin can read to the right and left of the placed token and correctly declare
    //a win or not based on the surrounding tokens
    @Test
    public void Test_checkHorizWin_Middle_Win(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',2);
        gb.placeToken('X',3);
        gb.placeToken('X',4);
        ExpectedArray[0][2] = 'X';
        ExpectedArray[0][3] = 'X';
        ExpectedArray[0][4] = 'X';

        assertTrue(gb.checkHorizWin(0,3,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check that checkhorizWin correctly returns false if the amount of tokens in a row is
    //one less than the specified Win Number
    @Test
    public void Test_checkHorizWin_Declaring_win_too_Early(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('B',0);
        gb.placeToken('F',1);
        gb.placeToken('G',2);
        gb.placeToken('T',0);
        gb.placeToken('X',1);
        gb.placeToken('X',2);
        ExpectedArray[0][0] = 'B';
        ExpectedArray[0][1] = 'F';
        ExpectedArray[0][2] = 'G';
        ExpectedArray[1][0] = 'T';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[1][2] = 'X';

        assertTrue(!gb.checkHorizWin(1,2,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check that checkHorizWin can read the Zeroth column when checking the Board
    @Test
    public void Test_checkHorizWin_Zero_Column(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',0);
        gb.placeToken('X',1);
        gb.placeToken('X',2);
        ExpectedArray[0][0] = 'X';
        ExpectedArray[0][1] = 'X';
        ExpectedArray[0][2] = 'X';

        assertTrue(gb.checkHorizWin(0,0,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check that checkVertWin declares a Win at the specified Win Number
    @Test
    public void Test_checkVertWin_Functionality(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',2);
        gb.placeToken('X',2);
        ExpectedArray[0][2] = 'O';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[3][2] = 'X';


        assertTrue(gb.checkVertWin(3,2,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check if checkVertWin reads the Zeroth Column when checking the GameBoard
    @Test
    public void Test_checkVertWin_Zeroth_Column_Win(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O',0);
        gb.placeToken('X',0);
        gb.placeToken('X',0);
        gb.placeToken('X',0);
        ExpectedArray[0][0] = 'O';
        ExpectedArray[1][0] = 'X';
        ExpectedArray[2][0] = 'X';
        ExpectedArray[3][0] = 'X';

        assertTrue(gb.checkVertWin(3,0,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check if checkVertWin will halt after encountering a char value that is not the player's token
    @Test
    public void Test_checkVertWin_Break_Check(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',2);
        gb.placeToken('X',2);
        gb.placeToken('O',2);
        gb.placeToken('X',2);
        gb.placeToken('X',2);
        ExpectedArray[0][2] = 'X';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[2][2] = 'O';
        ExpectedArray[3][2] = 'X';
        ExpectedArray[4][2] = 'X';


        assertTrue(!gb.checkVertWin(4,2,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check if checkVertWin is not declaring Victory too early
    @Test
    public void Test_checkVertWin_Declaring_win_too_Early(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O',4);
        gb.placeToken('X',4);
        gb.placeToken('X',4);
        ExpectedArray[0][4] = 'O';
        ExpectedArray[1][4] = 'X';
        ExpectedArray[2][4] = 'X';

        assertTrue(!gb.checkVertWin(2,4,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check if checkVertWin is reading the zeroth row
    @Test
    public void Test_checkVertWin_Zero_Row(){
        IGameBoard gb = BoardFactory(5,5,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X',3);
        gb.placeToken('X',3);
        gb.placeToken('X',3);
        ExpectedArray[0][3] = 'X';
        ExpectedArray[1][3] = 'X';
        ExpectedArray[2][3] = 'X';

        assertTrue(gb.checkVertWin(2,3,'X'));
        assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin is correctly reading to the Upper Right of the Token
    @Test
    public void Test_checkDiagWin_Upper_Right(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Q', 3);
        gb.placeToken('J', 3);
        gb.placeToken('K', 3);
        gb.placeToken('X', 3);
        ExpectedArray[0][1] = 'T';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[0][3] = 'Q';
        ExpectedArray[1][3] = 'J';
        ExpectedArray[2][3] = 'K';
        ExpectedArray[3][3] = 'X';

        assertTrue(gb.checkDiagWin(1,1,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin Is correctly reading to the Lower Left of the Token
    @Test
    public void Test_checkDiagWin_Lower_left(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Q', 3);
        gb.placeToken('J', 3);
        gb.placeToken('K', 3);
        gb.placeToken('X', 3);
        ExpectedArray[0][1] = 'T';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[0][3] = 'Q';
        ExpectedArray[1][3] = 'J';
        ExpectedArray[2][3] = 'K';
        ExpectedArray[3][3] = 'X';

        assertTrue(gb.checkDiagWin(3,3,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin is correctly reading to the lower right of the token
    @Test
    public void Test_checkDiagWin_Lower_Right(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 3);
        gb.placeToken('X', 3);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Q', 1);
        gb.placeToken('J', 1);
        gb.placeToken('K', 1);
        gb.placeToken('X', 1);
        ExpectedArray[0][3] = 'T';
        ExpectedArray[1][3] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[0][1] = 'Q';
        ExpectedArray[1][1] = 'J';
        ExpectedArray[2][1] = 'K';
        ExpectedArray[3][1] = 'X';


        assertTrue(gb.checkDiagWin(3,1,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin is correctly reading to the upper left of the token
    @Test
    public void Test_checkDiagWin_Upper_Left(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 3);
        gb.placeToken('X', 3);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Q', 1);
        gb.placeToken('J', 1);
        gb.placeToken('K', 1);
        gb.placeToken('X', 1);
        ExpectedArray[0][3] = 'T';
        ExpectedArray[1][3] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[0][1] = 'Q';
        ExpectedArray[1][1] = 'J';
        ExpectedArray[2][1] = 'K';
        ExpectedArray[3][1] = 'X';


        assertTrue(gb.checkDiagWin(1,3,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin is reading the the upper left and lower right in order to declare victory
    @Test
    public void Test_checkDiagWin_Middle_Left_Slant(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 3);
        gb.placeToken('X', 3);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Q', 1);
        gb.placeToken('J', 1);
        gb.placeToken('K', 1);
        gb.placeToken('X', 1);
        ExpectedArray[0][3] = 'T';
        ExpectedArray[1][3] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[0][1] = 'Q';
        ExpectedArray[1][1] = 'J';
        ExpectedArray[2][1] = 'K';
        ExpectedArray[3][1] = 'X';


        assertTrue(gb.checkDiagWin(2,2,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin is reading the lower left and the upper right correctly
    @Test
    public void Test_checkDiagWin_Middle_Right_Slant(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Q', 3);
        gb.placeToken('J', 3);
        gb.placeToken('K', 3);
        gb.placeToken('X', 3);
        ExpectedArray[0][1] = 'T';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[0][3] = 'Q';
        ExpectedArray[1][3] = 'J';
        ExpectedArray[2][3] = 'K';
        ExpectedArray[3][3] = 'X';

        assertTrue(gb.checkDiagWin(2,2,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin is not declaring victory when it counts WinNumber - 1 amount of tokens in a row
    @Test
    public void Test_checkDiagWin_Declaring_Win_Early(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('Q', 3);
        gb.placeToken('J', 3);
        gb.placeToken('K', 3);
        ExpectedArray[0][1] = 'T';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';
        ExpectedArray[0][3] = 'Q';
        ExpectedArray[1][3] = 'J';
        ExpectedArray[2][3] = 'K';


        assertTrue(!gb.checkDiagWin(2,2,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check that checkDiagWin only updates the WinNumber when the tokens are in a row.
    //Basically I had the problem that if the Board was in this arrangement:
    //          X|
    //          O|X
    //          X|O
    // It would declare a victory because it was counting the middle, upper left, and lower
    // right of the placed token.
    @Test
    public void Test_checkDiagWin_Two_Diags(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 1);
        gb.placeToken('X', 1);
        gb.placeToken('W', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Y', 2);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][1] = 'T';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[2][1] = 'W';
        ExpectedArray[3][1] = 'X';
        ExpectedArray[0][2] = 'Y';
        ExpectedArray[1][2] = 'G';
        ExpectedArray[2][2] = 'X';

        assertTrue(!gb.checkDiagWin(2,2,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check if checkDiagWin correctly reading in the Zeroth row
    @Test
    public void Test_checkDiagWin_Zeroth_Row(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 1);
        gb.placeToken('G', 2);
        gb.placeToken('X', 2);
        gb.placeToken('J', 3);
        gb.placeToken('K', 3);
        gb.placeToken('X', 3);
        ExpectedArray[0][1] = 'X';
        ExpectedArray[0][2] = 'G';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[0][3] = 'J';
        ExpectedArray[1][3] = 'K';
        ExpectedArray[2][3] = 'X';


        assertTrue(gb.checkDiagWin(2,3,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check if chceckDiagWin correctly reads in the Zeroth column
    @Test
    public void Test_checkDiagWin_Zeroth_Column(){
        IGameBoard gb = BoardFactory(10,10,3);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('T', 0);
        gb.placeToken('X', 0);
        gb.placeToken('Y', 1);
        gb.placeToken('G', 1);
        gb.placeToken('X', 1);
        gb.placeToken('Q', 2);
        gb.placeToken('J', 2);
        gb.placeToken('K', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][0] = 'T';
        ExpectedArray[1][0] = 'X';
        ExpectedArray[0][1] = 'Y';
        ExpectedArray[1][1] = 'G';
        ExpectedArray[2][1] = 'X';
        ExpectedArray[0][2] = 'Q';
        ExpectedArray[1][2] = 'J';
        ExpectedArray[2][2] = 'K';
        ExpectedArray[3][2] = 'X';

        assertTrue(gb.checkDiagWin(3,2,'X'));
        assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
    }

    //Check if CheckTie correctly declares a tie
    @Test
    public void Test_CheckTie_Functionality(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][0] = 'X';
        ExpectedArray[1][0] = 'X';
        ExpectedArray[2][0] = 'X';
        ExpectedArray[0][1] = 'X';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[2][1] = 'X';
        ExpectedArray[0][2] = 'X';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[2][2] = 'X';

        assertTrue(gb.checkTie());
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check if CheckTie correctly returns false when there is no tie
    @Test
    public void Test_CheckTie_No_Tie(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][0] = 'X';
        ExpectedArray[1][0] = 'X';

        ExpectedArray[0][1] = 'X';
        ExpectedArray[1][1] = 'X';

        ExpectedArray[0][2] = 'X';
        ExpectedArray[1][2] = 'X';


        assertTrue(!gb.checkTie());
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check that CheckTie reads in the Zeroth Column
    @Test
    public void Test_CheckTie_Check_Zeroth_Column(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][0] = 'X';
        ExpectedArray[1][0] = 'X';

        ExpectedArray[0][1] = 'X';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[2][1] = 'X';
        ExpectedArray[0][2] = 'X';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[2][2] = 'X';
        assertTrue(!gb.checkTie());
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //Check if CheckTie correctly returns false if all but one column are full
    @Test
    public void Test_CheckTie_Off_by_One(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 0);
        gb.placeToken('X', 1);
        gb.placeToken('X', 1);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][0] = 'X';
        ExpectedArray[1][0] = 'X';
        ExpectedArray[2][0] = 'X';
        ExpectedArray[0][1] = 'X';
        ExpectedArray[1][1] = 'X';

        ExpectedArray[0][2] = 'X';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[2][2] = 'X';

        assertTrue(!gb.checkTie());
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }


    //Check if WhatsAtPos returns a space if nothing has been placed in the GameBoard
    @Test
    public void Test_WhatsAtPos_Returns_Space_If_Completely_Empty(){
        IGameBoard gb = BoardFactory(3,3,5);

        assertTrue(gb.whatsAtPos(0,0) == ' ');

    }

    //Check if WhatsAtPos Correctly returns the token at the specified location in the GameBoard
    @Test
    public void Test_WhatsAtPos_Returns_Correct_token(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O', 1);
        gb.placeToken('X', 1);
        gb.placeToken('O', 1);
        ExpectedArray[0][1] = 'O';
        ExpectedArray[1][1] = 'X';
        ExpectedArray[2][1] = 'O';

        assertTrue(gb.whatsAtPos(1,1) == 'X');
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check if WhatsAtPos correctly reads the Zeroth Column
    @Test
    public void Test_WhatsAtPos_Zeroth_Column(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        ExpectedArray[0][0] = 'O';
        ExpectedArray[1][0] = 'X';
        ExpectedArray[2][0] = 'O';
        assertTrue(gb.whatsAtPos(1,0) == 'X');
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //Check if WhatsAtPos correctly reads the Zeroth row
    @Test
    public void Test_WhatsAtPos_Zeroth_Row(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        gb.placeToken('O', 0);
        ExpectedArray[0][0] = 'O';
        ExpectedArray[1][0] = 'X';
        ExpectedArray[2][0] = 'O';
        assertTrue(gb.whatsAtPos(0,0) == 'O');
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //Check if WhatsAtPos correctly returns a ' ' char if nothing is in that location
    //and after the GameBoard has had tokens placed within it
    @Test
    public void Test_WhatsAtPos_Returns_Empty_After_Placed_Tokens(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        ExpectedArray[0][0] = 'O';
        ExpectedArray[1][0] = 'X';
        assertTrue(gb.whatsAtPos(2,0) == ' ');
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //This is mainly for GameBoardMem, where the column doesn't exist in the map if there is nothing in it
    //Checks that WhatsAtPos returns a ' ' char if the column has nothing in it
    @Test
    public void Test_WhatsAtPos_Returns_Empty_For_Empty_Column(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O', 0);
        gb.placeToken('X', 0);
        ExpectedArray[0][0] = 'O';
        ExpectedArray[1][0] = 'X';
        assertTrue(gb.whatsAtPos(1,2) == ' ');
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check that WhatsAtPos can read the upper most position in the GameBoard
    @Test
    public void Test_WhatsAtPos_Upper_Boundary() {
        IGameBoard gb = BoardFactory(3, 3, 5);
        char[][]ExpectedArray = ArrayBuilder();
        gb.placeToken('O', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][2] = 'O';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[2][2] = 'X';
        assertTrue(gb.whatsAtPos(2,2) == 'X');
        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check that PlaceToken can correctly place a token into the GameBoard
    @Test
    public void Test_PlaceToken_Functionality(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][] ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 1);
        ExpectedArray[0][1] = 'X';

        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //Check that PlaceToken can correctly place a token in the Zeroth column
    @Test
    public void Test_PlaceToken_Zeroth_Column(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][] ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 0);
        ExpectedArray[0][0] = 'X';

        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check that PlaceToken correctly places a new token over an existing token,
    //and doesn't just overwrite the old token with the  newly placed one
    @Test
    public void Test_PlaceToken_Place_Over_Token(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][] ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 0);
        gb.placeToken('F',0);
        ExpectedArray[0][0] = 'X';
        ExpectedArray[1][0] = 'F';

        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check that PlaceToken can place a token in the rightmost column
    @Test
    public void Test_PlaceToken_Upper_Boundary_Column(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][] ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 2);
        ExpectedArray[0][2] = 'X';

        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
    }

    //Check that PlaceToken can place a token in the topmost row
    @Test
    public void Test_PlaceToken_Upper_Boundary_Row(){
        IGameBoard gb = BoardFactory(3,3,5);
        char[][] ExpectedArray = ArrayBuilder();
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        gb.placeToken('X', 2);
        ExpectedArray[0][2] = 'X';
        ExpectedArray[1][2] = 'X';
        ExpectedArray[2][2] = 'X';

        assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

    }

    //Factory Method that returns a GameBoard object
    private IGameBoard BoardFactory(int rows,int cols,int Number_to_Win){
        IGameBoard gb = new GameBoardMem(rows, cols, Number_to_Win);


        return gb;
    }

    //toString Method that takes in the modified array to be in the same String format as
    //the toString Method for the AbsGameBoard class
    private String ExpectedtoString(int NumRows, int NumColumns, char[][] ExpectedArray){
        //Creation of a StringBoard the same size as GameBoard in order to flip it for user viewing
        char[][] StringBoard = new char[100][100];
        //Creation of the Return String, with the top column numbers listed for useer viewing
        String ReturnBoard ="| 0";


        for(int i = 1; i<NumColumns;i++) {
            if(i>9){
                ReturnBoard = ReturnBoard + "|" + i;
            }else{
                ReturnBoard = ReturnBoard + "| " + i;
            }
            if(i==NumColumns-1){
                ReturnBoard = ReturnBoard +"|\n| ";
            }
        }
        //for loop that copys all the info from GameBoard into StringBoard, only with the rows fliped
        for(int i =0, j=NumRows-1; i<=NumRows && j>=0; i++,j--){
            for(int a = 0; a<NumColumns;a++){
                StringBoard[j][a]=ExpectedArray[i][a];
            }
        }

        //Nested for loop that feeds all the information from StringBoard into ReturnBoard, properly formatted for the user's viewing
        for(int i = 0; i<NumRows;i++){
            for(int j = 0; j<NumColumns;j++){
                if(j==(NumColumns-1)){
                    if(i!=(NumRows-1)){
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n| ";
                    }
                    if(i==(NumRows-1))
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n";

                }else{
                    ReturnBoard = ReturnBoard + StringBoard[i][j] +"| ";
                }


            }
        }

        return ReturnBoard;

    }

    //Private helper method that just creates a 2d char array full of ' ' chars
    private char[][] ArrayBuilder(){
        char [][] ExpectedArray = new char[100][100];

        for(int i =0;i<100;i++){
            for(int a = 0; a<100;a++){
                ExpectedArray[i][a] =' ';
            }
        }
        return ExpectedArray;

    }

    public static class TestGameBoard {

        //Test that the values passed into the constructor are initialized
        @Test
        public void Test_Constructor_Test_Initialization() {
            char[][] ExpectedArray = ArrayBuilder();
            IGameBoard gb = BoardFactory(5,10,3);

            assertEquals(ExpectedtoString(5,10,ExpectedArray), gb.toString());

            assertTrue(5 == gb.getNumRow());
            assertTrue(10 == gb.getNumColumns());
            assertTrue(3 == gb.getNumToWin());
        }

        //Test that the maximim size of GameBoard is created
        @Test
        public void Test_Constructor_UpperBoundary(){
            char[][] ExpectedArray = ArrayBuilder();
            IGameBoard gb = BoardFactory(100,100,25);

            assertEquals(ExpectedtoString(100,100,ExpectedArray), gb.toString());

            assertTrue(100 == gb.getNumRow());
            assertTrue(100 == gb.getNumColumns());
            assertTrue(25 == gb.getNumToWin());
        }

        //Test that the minimum size of GameBoard is created
        @Test
        public void Test_Constructor_LowerBoundary(){
            char[][] ExpectedArray = ArrayBuilder();
            IGameBoard gb = BoardFactory(3,3,3);

            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

            assertTrue(3 == gb.getNumRow());
            assertTrue(3 == gb.getNumColumns());
            assertTrue(3 == gb.getNumToWin());
        }

        //Check that CheckIfFree returns false if the column is not free
        @Test
        public void Test_CheckIfFree_IsNotFree(){
            IGameBoard gb = BoardFactory(3,3,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',1);
            gb.placeToken('O',1);
            gb.placeToken('X',1);
            ExpectedArray[0][1] = 'X';
            ExpectedArray[1][1] = 'O';
            ExpectedArray[2][1] = 'X';
            assertTrue(!gb.checkIfFree(1));
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //Check that CheckIfFree returns true if the column is free
        @Test
        public void Test_CheckIfFree_IsFree(){
            IGameBoard gb = BoardFactory(3,3,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',1);
            gb.placeToken('O',1);
            ExpectedArray[0][1] = 'X';
            ExpectedArray[1][1] = 'O';

            assertTrue(gb.checkIfFree(1));
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //Check that CheckIfFree can read the zeroth column of the GameBoard
        @Test
        public void Test_CheckIfFree_IsNotFree_At_Zero(){
            IGameBoard gb = BoardFactory(3,3,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',0);
            gb.placeToken('O',0);
            gb.placeToken('O',0);
            ExpectedArray[0][0] = 'X';
            ExpectedArray[1][0] = 'O';
            ExpectedArray[2][0] = 'O';

            assertTrue(!gb.checkIfFree(0));
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check that checkHorizWin can read to the left of the placed token
        @Test
        public void Test_checkHorizWin_Left_Win(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',2);
            gb.placeToken('X',3);
            gb.placeToken('X',4);
            ExpectedArray[0][2] = 'X';
            ExpectedArray[0][3] = 'X';
            ExpectedArray[0][4] = 'X';

            assertTrue(gb.checkHorizWin(0,4,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check that checkHorizWin can read to the right of the placed token
        @Test
        public void Test_checkHorizWin_Right_Win(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',2);
            gb.placeToken('X',3);
            gb.placeToken('X',4);
            ExpectedArray[0][2] = 'X';
            ExpectedArray[0][3] = 'X';
            ExpectedArray[0][4] = 'X';

            assertTrue(gb.checkHorizWin(0,2,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check that checkHorizWin can read to the right and left of the placed token and correctly declare
        //a win or not based on the surrounding tokens
        @Test
        public void Test_checkHorizWin_Middle_Win(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',2);
            gb.placeToken('X',3);
            gb.placeToken('X',4);
            ExpectedArray[0][2] = 'X';
            ExpectedArray[0][3] = 'X';
            ExpectedArray[0][4] = 'X';

            assertTrue(gb.checkHorizWin(0,3,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check that checkhorizWin correctly returns false if the amount of tokens in a row is
        //one less than the specified Win Number
        @Test
        public void Test_checkHorizWin_Declaring_win_too_Early(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('B',0);
            gb.placeToken('F',1);
            gb.placeToken('G',2);
            gb.placeToken('T',0);
            gb.placeToken('X',1);
            gb.placeToken('X',2);
            ExpectedArray[0][0] = 'B';
            ExpectedArray[0][1] = 'F';
            ExpectedArray[0][2] = 'G';
            ExpectedArray[1][0] = 'T';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[1][2] = 'X';

            assertTrue(!gb.checkHorizWin(1,2,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check that checkHorizWin can read the Zeroth column when checking the Board
        @Test
        public void Test_checkHorizWin_Zero_Column(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',0);
            gb.placeToken('X',1);
            gb.placeToken('X',2);
            ExpectedArray[0][0] = 'X';
            ExpectedArray[0][1] = 'X';
            ExpectedArray[0][2] = 'X';

            assertTrue(gb.checkHorizWin(0,0,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check that checkVertWin declares a Win at the specified Win Number
        @Test
        public void Test_checkVertWin_Functionality(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O',2);
            gb.placeToken('X',2);
            gb.placeToken('X',2);
            gb.placeToken('X',2);
            ExpectedArray[0][2] = 'O';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[3][2] = 'X';


            assertTrue(gb.checkVertWin(3,2,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check if checkVertWin reads the Zeroth Column when checking the GameBoard
        @Test
        public void Test_checkVertWin_Zeroth_Column_Win(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O',0);
            gb.placeToken('X',0);
            gb.placeToken('X',0);
            gb.placeToken('X',0);
            ExpectedArray[0][0] = 'O';
            ExpectedArray[1][0] = 'X';
            ExpectedArray[2][0] = 'X';
            ExpectedArray[3][0] = 'X';

            assertTrue(gb.checkVertWin(3,0,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check if checkVertWin will halt after encountering a char value that is not the player's token
        @Test
        public void Test_checkVertWin_Break_Check(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',2);
            gb.placeToken('X',2);
            gb.placeToken('O',2);
            gb.placeToken('X',2);
            gb.placeToken('X',2);
            ExpectedArray[0][2] = 'X';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[2][2] = 'O';
            ExpectedArray[3][2] = 'X';
            ExpectedArray[4][2] = 'X';


            assertTrue(!gb.checkVertWin(4,2,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check if checkVertWin is not declaring Victory too early
        @Test
        public void Test_checkVertWin_Declaring_win_too_Early(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O',4);
            gb.placeToken('X',4);
            gb.placeToken('X',4);
            ExpectedArray[0][4] = 'O';
            ExpectedArray[1][4] = 'X';
            ExpectedArray[2][4] = 'X';

            assertTrue(!gb.checkVertWin(2,4,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check if checkVertWin is reading the zeroth row
        @Test
        public void Test_checkVertWin_Zero_Row(){
            IGameBoard gb = BoardFactory(5,5,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X',3);
            gb.placeToken('X',3);
            gb.placeToken('X',3);
            ExpectedArray[0][3] = 'X';
            ExpectedArray[1][3] = 'X';
            ExpectedArray[2][3] = 'X';

            assertTrue(gb.checkVertWin(2,3,'X'));
            assertEquals(ExpectedtoString(5,5,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin is correctly reading to the Upper Right of the Token
        @Test
        public void Test_checkDiagWin_Upper_Right(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 1);
            gb.placeToken('X', 1);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('Q', 3);
            gb.placeToken('J', 3);
            gb.placeToken('K', 3);
            gb.placeToken('X', 3);
            ExpectedArray[0][1] = 'T';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[0][3] = 'Q';
            ExpectedArray[1][3] = 'J';
            ExpectedArray[2][3] = 'K';
            ExpectedArray[3][3] = 'X';

            assertTrue(gb.checkDiagWin(1,1,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin Is correctly reading to the Lower Left of the Token
        @Test
        public void Test_checkDiagWin_Lower_left(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 1);
            gb.placeToken('X', 1);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('Q', 3);
            gb.placeToken('J', 3);
            gb.placeToken('K', 3);
            gb.placeToken('X', 3);
            ExpectedArray[0][1] = 'T';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[0][3] = 'Q';
            ExpectedArray[1][3] = 'J';
            ExpectedArray[2][3] = 'K';
            ExpectedArray[3][3] = 'X';

            assertTrue(gb.checkDiagWin(3,3,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin is correctly reading to the lower right of the token
        @Test
        public void Test_checkDiagWin_Lower_Right(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 3);
            gb.placeToken('X', 3);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('Q', 1);
            gb.placeToken('J', 1);
            gb.placeToken('K', 1);
            gb.placeToken('X', 1);
            ExpectedArray[0][3] = 'T';
            ExpectedArray[1][3] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[0][1] = 'Q';
            ExpectedArray[1][1] = 'J';
            ExpectedArray[2][1] = 'K';
            ExpectedArray[3][1] = 'X';


            assertTrue(gb.checkDiagWin(3,1,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin is correctly reading to the upper left of the token
        @Test
        public void Test_checkDiagWin_Upper_Left(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 3);
            gb.placeToken('X', 3);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('Q', 1);
            gb.placeToken('J', 1);
            gb.placeToken('K', 1);
            gb.placeToken('X', 1);
            ExpectedArray[0][3] = 'T';
            ExpectedArray[1][3] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[0][1] = 'Q';
            ExpectedArray[1][1] = 'J';
            ExpectedArray[2][1] = 'K';
            ExpectedArray[3][1] = 'X';


            assertTrue(gb.checkDiagWin(1,3,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin is reading the the upper left and lower right in order to declare victory
        @Test
        public void Test_checkDiagWin_Middle_Left_Slant(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 3);
            gb.placeToken('X', 3);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('Q', 1);
            gb.placeToken('J', 1);
            gb.placeToken('K', 1);
            gb.placeToken('X', 1);
            ExpectedArray[0][3] = 'T';
            ExpectedArray[1][3] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[0][1] = 'Q';
            ExpectedArray[1][1] = 'J';
            ExpectedArray[2][1] = 'K';
            ExpectedArray[3][1] = 'X';


            assertTrue(gb.checkDiagWin(2,2,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin is reading the lower left and the upper right correctly
        @Test
        public void Test_checkDiagWin_Middle_Right_Slant(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 1);
            gb.placeToken('X', 1);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('Q', 3);
            gb.placeToken('J', 3);
            gb.placeToken('K', 3);
            gb.placeToken('X', 3);
            ExpectedArray[0][1] = 'T';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[0][3] = 'Q';
            ExpectedArray[1][3] = 'J';
            ExpectedArray[2][3] = 'K';
            ExpectedArray[3][3] = 'X';

            assertTrue(gb.checkDiagWin(2,2,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin is not declaring victory when it counts WinNumber - 1 amount of tokens in a row
        @Test
        public void Test_checkDiagWin_Declaring_Win_Early(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 1);
            gb.placeToken('X', 1);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('Q', 3);
            gb.placeToken('J', 3);
            gb.placeToken('K', 3);
            ExpectedArray[0][1] = 'T';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';
            ExpectedArray[0][3] = 'Q';
            ExpectedArray[1][3] = 'J';
            ExpectedArray[2][3] = 'K';


            assertTrue(!gb.checkDiagWin(2,2,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check that checkDiagWin only updates the WinNumber when the tokens are in a row.
        //Basically I had the problem that if the Board was in this arrangement:
        //          X|
        //          O|X
        //          X|O
        // It would declare a victory because it was counting the middle, upper left, and lower
        // right of the placed token.
        @Test
        public void Test_checkDiagWin_Two_Diags(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 1);
            gb.placeToken('X', 1);
            gb.placeToken('W', 1);
            gb.placeToken('X', 1);
            gb.placeToken('Y', 2);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][1] = 'T';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[2][1] = 'W';
            ExpectedArray[3][1] = 'X';
            ExpectedArray[0][2] = 'Y';
            ExpectedArray[1][2] = 'G';
            ExpectedArray[2][2] = 'X';

            assertTrue(!gb.checkDiagWin(2,2,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check if checkDiagWin correctly reading in the Zeroth row
        @Test
        public void Test_checkDiagWin_Zeroth_Row(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 1);
            gb.placeToken('G', 2);
            gb.placeToken('X', 2);
            gb.placeToken('J', 3);
            gb.placeToken('K', 3);
            gb.placeToken('X', 3);
            ExpectedArray[0][1] = 'X';
            ExpectedArray[0][2] = 'G';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[0][3] = 'J';
            ExpectedArray[1][3] = 'K';
            ExpectedArray[2][3] = 'X';


            assertTrue(gb.checkDiagWin(2,3,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check if chceckDiagWin correctly reads in the Zeroth column
        @Test
        public void Test_checkDiagWin_Zeroth_Column(){
            IGameBoard gb = BoardFactory(10,10,3);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('T', 0);
            gb.placeToken('X', 0);
            gb.placeToken('Y', 1);
            gb.placeToken('G', 1);
            gb.placeToken('X', 1);
            gb.placeToken('Q', 2);
            gb.placeToken('J', 2);
            gb.placeToken('K', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][0] = 'T';
            ExpectedArray[1][0] = 'X';
            ExpectedArray[0][1] = 'Y';
            ExpectedArray[1][1] = 'G';
            ExpectedArray[2][1] = 'X';
            ExpectedArray[0][2] = 'Q';
            ExpectedArray[1][2] = 'J';
            ExpectedArray[2][2] = 'K';
            ExpectedArray[3][2] = 'X';

            assertTrue(gb.checkDiagWin(3,2,'X'));
            assertEquals(ExpectedtoString(10,10,ExpectedArray), gb.toString());
        }

        //Check if CheckTie correctly declares a tie
        @Test
        public void Test_CheckTie_Functionality(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 0);
            gb.placeToken('X', 0);
            gb.placeToken('X', 0);
            gb.placeToken('X', 1);
            gb.placeToken('X', 1);
            gb.placeToken('X', 1);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][0] = 'X';
            ExpectedArray[1][0] = 'X';
            ExpectedArray[2][0] = 'X';
            ExpectedArray[0][1] = 'X';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[2][1] = 'X';
            ExpectedArray[0][2] = 'X';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[2][2] = 'X';

            assertTrue(gb.checkTie());
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check if CheckTie correctly returns false when there is no tie
        @Test
        public void Test_CheckTie_No_Tie(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 0);
            gb.placeToken('X', 0);
            gb.placeToken('X', 1);
            gb.placeToken('X', 1);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][0] = 'X';
            ExpectedArray[1][0] = 'X';

            ExpectedArray[0][1] = 'X';
            ExpectedArray[1][1] = 'X';

            ExpectedArray[0][2] = 'X';
            ExpectedArray[1][2] = 'X';


            assertTrue(!gb.checkTie());
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check that CheckTie reads in the Zeroth Column
        @Test
        public void Test_CheckTie_Check_Zeroth_Column(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 0);
            gb.placeToken('X', 0);
            gb.placeToken('X', 1);
            gb.placeToken('X', 1);
            gb.placeToken('X', 1);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][0] = 'X';
            ExpectedArray[1][0] = 'X';

            ExpectedArray[0][1] = 'X';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[2][1] = 'X';
            ExpectedArray[0][2] = 'X';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[2][2] = 'X';
            assertTrue(!gb.checkTie());
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //Check if CheckTie correctly returns false if all but one column are full
        @Test
        public void Test_CheckTie_Off_by_One(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 0);
            gb.placeToken('X', 0);
            gb.placeToken('X', 0);
            gb.placeToken('X', 1);
            gb.placeToken('X', 1);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][0] = 'X';
            ExpectedArray[1][0] = 'X';
            ExpectedArray[2][0] = 'X';
            ExpectedArray[0][1] = 'X';
            ExpectedArray[1][1] = 'X';

            ExpectedArray[0][2] = 'X';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[2][2] = 'X';

            assertTrue(!gb.checkTie());
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }


        //Check if WhatsAtPos returns a space if nothing has been placed in the GameBoard
        @Test
        public void Test_WhatsAtPos_Returns_Space_If_Completely_Empty(){
            IGameBoard gb = BoardFactory(3,3,5);

            assertTrue(gb.whatsAtPos(0,0) == ' ');

        }

        //Check if WhatsAtPos Correctly returns the token at the specified location in the GameBoard
        @Test
        public void Test_WhatsAtPos_Returns_Correct_token(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O', 1);
            gb.placeToken('X', 1);
            gb.placeToken('O', 1);
            ExpectedArray[0][1] = 'O';
            ExpectedArray[1][1] = 'X';
            ExpectedArray[2][1] = 'O';

            assertTrue(gb.whatsAtPos(1,1) == 'X');
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check if WhatsAtPos correctly reads the Zeroth Column
        @Test
        public void Test_WhatsAtPos_Zeroth_Column(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O', 0);
            gb.placeToken('X', 0);
            gb.placeToken('O', 0);
            ExpectedArray[0][0] = 'O';
            ExpectedArray[1][0] = 'X';
            ExpectedArray[2][0] = 'O';
            assertTrue(gb.whatsAtPos(1,0) == 'X');
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //Check if WhatsAtPos correctly reads the Zeroth row
        @Test
        public void Test_WhatsAtPos_Zeroth_Row(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O', 0);
            gb.placeToken('X', 0);
            gb.placeToken('O', 0);
            ExpectedArray[0][0] = 'O';
            ExpectedArray[1][0] = 'X';
            ExpectedArray[2][0] = 'O';
            assertTrue(gb.whatsAtPos(0,0) == 'O');
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //Check if WhatsAtPos correctly returns a ' ' char if nothing is in that location
        //and after the GameBoard has had tokens placed within it
        @Test
        public void Test_WhatsAtPos_Returns_Empty_After_Placed_Tokens(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O', 0);
            gb.placeToken('X', 0);
            ExpectedArray[0][0] = 'O';
            ExpectedArray[1][0] = 'X';
            assertTrue(gb.whatsAtPos(2,0) == ' ');
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //This is mainly for GameBoardMem, where the column doesn't exist in the map if there is nothing in it
        //Checks that WhatsAtPos returns a ' ' char if the column has nothing in it
        @Test
        public void Test_WhatsAtPos_Returns_Empty_For_Empty_Column(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O', 0);
            gb.placeToken('X', 0);
            ExpectedArray[0][0] = 'O';
            ExpectedArray[1][0] = 'X';
            assertTrue(gb.whatsAtPos(1,2) == ' ');
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check that WhatsAtPos can read the upper most position in the GameBoard
        @Test
        public void Test_WhatsAtPos_Upper_Boundary() {
            IGameBoard gb = BoardFactory(3, 3, 5);
            char[][]ExpectedArray = ArrayBuilder();
            gb.placeToken('O', 2);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][2] = 'O';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[2][2] = 'X';
            assertTrue(gb.whatsAtPos(2,2) == 'X');
            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check that PlaceToken can correctly place a token into the GameBoard
        @Test
        public void Test_PlaceToken_Functionality(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][] ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 1);
            ExpectedArray[0][1] = 'X';

            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //Check that PlaceToken can correctly place a token in the Zeroth column
        @Test
        public void Test_PlaceToken_Zeroth_Column(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][] ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 0);
            ExpectedArray[0][0] = 'X';

            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check that PlaceToken correctly places a new token over an existing token,
        //and doesn't just overwrite the old token with the  newly placed one
        @Test
        public void Test_PlaceToken_Place_Over_Token(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][] ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 0);
            gb.placeToken('F',0);
            ExpectedArray[0][0] = 'X';
            ExpectedArray[1][0] = 'F';

            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check that PlaceToken can place a token in the rightmost column
        @Test
        public void Test_PlaceToken_Upper_Boundary_Column(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][] ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 2);
            ExpectedArray[0][2] = 'X';

            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());
        }

        //Check that PlaceToken can place a token in the topmost row
        @Test
        public void Test_PlaceToken_Upper_Boundary_Row(){
            IGameBoard gb = BoardFactory(3,3,5);
            char[][] ExpectedArray = ArrayBuilder();
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            gb.placeToken('X', 2);
            ExpectedArray[0][2] = 'X';
            ExpectedArray[1][2] = 'X';
            ExpectedArray[2][2] = 'X';

            assertEquals(ExpectedtoString(3,3,ExpectedArray), gb.toString());

        }

        //Factory Method that returns a GameBoard object
        private IGameBoard BoardFactory(int rows,int cols,int Number_to_Win){
            IGameBoard gb = new GameBoard(rows, cols, Number_to_Win);


            return gb;
        }

        //toString Method that takes in the modified array to be in the same String format as
        //the toString Method for the AbsGameBoard class
        private String ExpectedtoString(int NumRows, int NumColumns, char[][] ExpectedArray){
            //Creation of a StringBoard the same size as GameBoard in order to flip it for user viewing
            char[][] StringBoard = new char[100][100];
            //Creation of the Return String, with the top column numbers listed for useer viewing
            String ReturnBoard ="| 0";


            for(int i = 1; i<NumColumns;i++) {
                if(i>9){
                    ReturnBoard = ReturnBoard + "|" + i;
                }else{
                    ReturnBoard = ReturnBoard + "| " + i;
                }
                if(i==NumColumns-1){
                    ReturnBoard = ReturnBoard +"|\n| ";
                }
            }
            //for loop that copys all the info from GameBoard into StringBoard, only with the rows fliped
            for(int i =0, j=NumRows-1; i<=NumRows && j>=0; i++,j--){
                for(int a = 0; a<NumColumns;a++){
                    StringBoard[j][a]=ExpectedArray[i][a];
                }
            }

            //Nested for loop that feeds all the information from StringBoard into ReturnBoard, properly formatted for the user's viewing
            for(int i = 0; i<NumRows;i++){
                for(int j = 0; j<NumColumns;j++){
                    if(j==(NumColumns-1)){
                        if(i!=(NumRows-1)){
                            ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n| ";
                        }
                        if(i==(NumRows-1))
                            ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n";

                    }else{
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"| ";
                    }


                }
            }

            return ReturnBoard;

        }

        //Private helper method that just creates a 2d char array full of ' ' chars
        private char[][] ArrayBuilder(){
            char [][] ExpectedArray = new char[100][100];

            for(int i =0;i<100;i++){
                for(int a = 0; a<100;a++){
                    ExpectedArray[i][a] =' ';
                }
            }
            return ExpectedArray;

        }

    }
}
