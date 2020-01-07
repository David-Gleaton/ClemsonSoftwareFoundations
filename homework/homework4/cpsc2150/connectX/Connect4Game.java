package cpsc2150.connectX;

/*
David Gleaton
2/10/19
Connect4Game.java
Contains main class that takes in the user's choices, prints the gameboard, and validates input
 */

import java.util.*;

public class Connect4Game {
    public static void main(String args[]){
        /*@param   Creation of the GameBoard, set to null, in order to access Interface variables

         */
        IGameBoard Connect4 = null;
        //@param    Creation of several holder variables to be passed into the constructor
        int PlayerInput =0, NumCol = 0, NumRow = 0, NumWin = 0, NumPlayers = 0, PlayerRotation = 1;
        char TokenInput, GameType;
        //Creation of a PlayerTokens list in order to correctly pass in the token based on the player
        List<Character> PlayerTokens = new ArrayList<>();

        //@param    Creation of a holder variable to track the user's choice of quiting or not
        String menuChoice = "", StringInput;
        //@param    Creation of Scanner object
        Scanner keyboard = new Scanner(System.in);

        //This calls upon the setNumPlayer method
        NumPlayers = setNumPlayer(NumPlayers, Connect4);

        int i = 0;
        int Player = 1;
        //This do while loop takes in the player's choices for their tokens to be used in the game
        do{
            System.out.println("Enter the character to represent player "+Player);
            StringInput = keyboard.next();
            TokenInput = StringInput.charAt(0);
            TokenInput = Character.toUpperCase(TokenInput);

            if(PlayerTokens.contains(TokenInput)){
                System.out.println(TokenInput+" is already taken as a player token!");
                continue;
            }

            PlayerTokens.add(TokenInput);

            i++;
            Player++;
        }while(i<NumPlayers);
        i = 0;
        Player = 1;



        //In order to more efficiently get the basic construction of the GameBoard, I moved the process to three helper methods
        NumRow = setRows(NumRow, Connect4);

        NumCol = setColumns(NumCol, Connect4);

        NumWin = setWinNumber(NumWin, Connect4);

        //This do-while loop askes the players what implementation of the GameBoard they desire
        do{
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game(M/m)?");
            StringInput = keyboard.next();
            GameType = StringInput.charAt(0);
            GameType = Character.toUpperCase(GameType);
            if(GameType == 'F'){
                Connect4 = new GameBoard(NumRow, NumCol, NumWin);
            }else if(GameType == 'M'){
                Connect4 = new GameBoardMem(NumRow, NumCol, NumWin);
            }else{
                System.out.println("Please enter F or M.");
            }


        }while(GameType != 'F' && GameType != 'M');

        //Large do-while statement that repeats the block until the user quits the game
        do{



            do {
                //@param Calls upon the toString method so the user can see GameBoard
                System.out.println(Connect4.toString());

                //@param Do-while statement that validates that the user input correct data, and prompts for another entry if false
                do {
                    System.out.println("Player "+PlayerRotation+", what column do you want to place your marker in?");
                    PlayerInput = keyboard.nextInt();
                    if (PlayerInput > (Connect4.getNumColumns()-1)) {
                        System.out.println("Column cannot be greater than " +(Connect4.getNumColumns()-1));
                    }
                    else if(PlayerInput < 0){
                        System.out.println("Column cannot be less than 0");
                    }
                }while(PlayerInput < 0 || PlayerInput >(Connect4.getNumColumns()-1));

                //@param if statement that checks if the chosen column is full
                if (Connect4.checkIfFree(PlayerInput)) {
                    Connect4.placeToken(PlayerTokens.get(i), PlayerInput);

                } else if (!Connect4.checkIfFree(PlayerInput)) {
                    System.out.println("Column is full");
                    if(Connect4.checkTie()){
                        break;
                    }
                    continue;
                }
                if (!Connect4.checkIfFree(PlayerInput)){
                    break;
                }
            }while(!Connect4.checkIfFree(PlayerInput));


            //@param if statement that checks if player X has won, and prompts the user if they want to play again if need be
            if(Connect4.checkForWin(PlayerInput)){
                System.out.println(Connect4.toString());
                System.out.println("Player "+PlayerTokens.get(i)+" Won!");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true || menuChoice.equals("N")==true){
                    System.exit(0);
                }else{
                    //This calls upon the setNumPlayer method
                    NumPlayers = setNumPlayer(NumPlayers, Connect4);
                    PlayerTokens.clear();

                    i = 0;
                    Player = 1;
                    //This do while loop takes in the player's choices for their tokens to be used in the game
                    do{
                        System.out.println("Enter the character to represent player "+Player);
                        StringInput = keyboard.next();
                        TokenInput = StringInput.charAt(0);
                        TokenInput = Character.toUpperCase(TokenInput);

                        if(PlayerTokens.contains(TokenInput)){
                            System.out.println(TokenInput+" is already taken as a player token!");
                            continue;
                        }

                        PlayerTokens.add(TokenInput);

                        i++;
                        Player++;
                    }while(i<NumPlayers);
                    i = 0;
                    Player = 1;



                    //@param 3 do-while loops take in the user's desired rows, columns, and win number
                    //       in order to pass them into the constructor
                    NumRow = setRows(NumRow, Connect4);

                    NumCol = setColumns(NumCol, Connect4);

                    NumWin = setWinNumber(NumWin, Connect4);


                    do{
                        System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game(M/m)?");
                        StringInput = keyboard.next();
                        GameType = StringInput.charAt(0);
                        GameType = Character.toUpperCase(GameType);
                        if(GameType == 'F'){
                            Connect4 = new GameBoard(NumRow, NumCol, NumWin);
                        }else if(GameType == 'M'){
                            Connect4 = new GameBoardMem(NumRow, NumCol, NumWin);
                        }else{
                            System.out.println("Please choose the correct inputs.");
                        }


                    }while(GameType != 'F' && GameType != 'M');

                    continue;
                }
            }

            //@param Checks for a tie if the game board is full and neither player has won
            if(Connect4.checkTie()){
                System.out.println(Connect4.toString());
                System.out.println("The Game is a Tie");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true || menuChoice.equals("N")==true){
                    System.exit(0);
                }else{
                    //This calls upon the setNumPlayer method
                    NumPlayers = setNumPlayer(NumPlayers, Connect4);
                    PlayerTokens.clear();

                    i = 0;
                    Player = 1;
                    do{
                        System.out.println("Enter the character to represent player "+Player);
                        StringInput = keyboard.next();
                        TokenInput = StringInput.charAt(0);
                        TokenInput = Character.toUpperCase(TokenInput);

                        if(PlayerTokens.contains(TokenInput)){
                            System.out.println(TokenInput+" is already taken as a player token!");
                            continue;
                        }

                        PlayerTokens.add(TokenInput);

                        i++;
                        Player++;
                    }while(i<NumPlayers);
                    i = 0;
                    Player = 1;



                    //@param 3 do-while loops take in the user's desired rows, columns, and win number
                    //       in order to pass them into the constructor
                    NumRow = setRows(NumRow, Connect4);

                    NumCol = setColumns(NumCol, Connect4);

                    NumWin = setWinNumber(NumWin, Connect4);


                    do{
                        System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game(M/m)?");
                        StringInput = keyboard.next();
                        GameType = StringInput.charAt(0);
                        GameType = Character.toUpperCase(GameType);
                        if(GameType == 'F'){
                            Connect4 = new GameBoard(NumRow, NumCol, NumWin);
                        }else if(GameType == 'M'){
                            Connect4 = new GameBoardMem(NumRow, NumCol, NumWin);
                        }else{
                            System.out.println("Please choose the correct inputs.");
                        }


                    }while(GameType != 'F' && GameType != 'M');

                    continue;
                }
            }

            //Incrementation of the PlayerRoatation and i variables in order to
            //correctly print out the input text and to correctly pass in the right token
            //into the gameboard
            PlayerRotation++;
            i++;

            if(i == NumPlayers){
                i = 0;
            }
            if(PlayerRotation > NumPlayers){
                PlayerRotation = 1;
            }


        }while(menuChoice != "n" ||menuChoice != "N");








    }

    //@pre  Connect4 needs to be created
    //@post Returns NumPlayers
    //@returns  This method prompts the user to input the number of players and validates their input
    public static int setNumPlayer(int NumPlayers, IGameBoard Connect4){
        Scanner keyboard = new Scanner(System.in);

        do{
            System.out.println("How many players?");
            NumPlayers = keyboard.nextInt();
            if(NumPlayers<Connect4.MIN_PLAYERS){
                System.out.println("Must be at least" + Connect4.MIN_PLAYERS+" players");
            }
            if(NumPlayers > Connect4.MAX_PLAYERS){
                System.out.println("Must be " + Connect4.MAX_PLAYERS+" players or fewer");
            }
        }while(NumPlayers < Connect4.MIN_PLAYERS || NumPlayers > Connect4.MAX_PLAYERS);

        return NumPlayers;
    }
    //@pre  Connect4 needs to be created
    //@post Returns NumCol
    //@returns  This method prompts the user to input the number of columns and validates their input
    public static int setColumns(int NumCol, IGameBoard Connect4){
        Scanner keyboard = new Scanner(System.in);
        do {
            System.out.println("How Many columns should be on the board?");
            NumCol = keyboard.nextInt();
            if(NumCol<Connect4.MIN_COL){
                System.out.println("Must have at least "+Connect4.MIN_COL+" columns");
            }
            else if(NumCol >Connect4.MAX_COL){
                System.out.println("Can have at most "+Connect4.MAX_COL+" columns");
            }
        }while(NumCol<Connect4.MIN_COL || NumCol >Connect4.MAX_COL);

        return NumCol;
    }
    //@pre  Connect4 needs to be created
    //@post Returns NumRow
    //@returns  This method prompts the user to input the number of rows and validates their input
    public static int setRows(int NumRow, IGameBoard Connect4){
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println("How Many rows should be on the board?");
            NumRow = keyboard.nextInt();
            if(NumRow<Connect4.MIN_ROWS){
                System.out.println("Must have at least "+Connect4.MIN_ROWS+" rows");
            }
            else if(NumRow >Connect4.MAX_ROW){
                System.out.println("Can have at most "+Connect4.MAX_ROW+" rows");
            }

        }while(NumRow<Connect4.MIN_ROWS || NumRow >Connect4.MAX_ROW);

        return NumRow;
    }
    //@pre  Connect4 needs to be created
    //@post Returns NumWin
    //@returns  This method prompts the user to input the number of tokens in a row to win and validates their input
    public static int setWinNumber(int NumWin, IGameBoard Connect4) {
        Scanner keyboard = new Scanner(System.in);
        do {
            System.out.println("How many in a row to win");
            NumWin = keyboard.nextInt();
            if(NumWin<Connect4.MIN_WIN){
                System.out.println("Must have at least "+Connect4.MIN_WIN+" in a row to win");
            }
            else if(NumWin >Connect4.MAX_WIN){
                System.out.println("Can have at most "+Connect4.MAX_WIN+" in a row to win");
            }
        }while(NumWin<Connect4.MIN_WIN || NumWin >Connect4.MAX_WIN);
        return NumWin;
    }

}
