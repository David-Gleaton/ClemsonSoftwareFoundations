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
        int PlayerInput =0, NumCol, NumRow, NumWin;
        //@param    Creation of a holder variable to track the user's choice of quiting or not
        String menuChoice = "";
        //@param    Creation of Scanner object
        Scanner keyboard = new Scanner(System.in);

        //@param 3 do-while loops take in the user's desired rows, columns, and win number
        //       in order to pass them into the constructor
        do {
            System.out.println("How Many rows should be on the board?");
            NumRow = keyboard.nextInt();
            if(NumRow<Connect4.MIN_ROWS){
                System.out.println("Must have at least 3 rows");
            }
            else if(NumRow >Connect4.MAX_ROW){
                System.out.println("Can have at most 100 rows");
            }

        }while(NumRow<Connect4.MIN_ROWS || NumRow >Connect4.MAX_ROW);

        do {
            System.out.println("How Many columns should be on the board?");
            NumCol = keyboard.nextInt();
            if(NumCol<Connect4.MIN_COL){
                System.out.println("Must have at least 3 columns");
            }
            else if(NumCol >Connect4.MAX_COL){
                System.out.println("Can have at most 100 columns");
            }
        }while(NumCol<Connect4.MIN_COL || NumCol >Connect4.MAX_COL);

        do {
            System.out.println("How many in a row to win");
            NumWin = keyboard.nextInt();
            if(NumWin<Connect4.MIN_WIN){
                System.out.println("Must have at least 3 in a row to win");
            }
            else if(NumWin >Connect4.MAX_WIN){
                System.out.println("Can have at most 25 in a row to win");
            }
        }while(NumWin<Connect4.MIN_WIN || NumWin >Connect4.MAX_WIN);
        Connect4 = new GameBoard(NumRow, NumCol, NumWin);

        //Large do-while statement that repeats the block until the user quits the game
        do{






            do {
                //@param Calls upon the toString method so the user can see GameBoard
                System.out.println(Connect4.toString());

                //@param Do-while statement that validates that the user input correct data, and prompts for another entry if false
                do {
                    System.out.println("Player X, what column do you want to place your marker in?");
                    PlayerInput = keyboard.nextInt();
                    if (PlayerInput > (Connect4.getNumColumns()-1)) {
                        System.out.println("Column cannot be greater than " +(Connect4.getNumColumns()-1));
                    }
                    else if(PlayerInput < 0){
                        System.out.println("Column cannot be less than 0");
                    }
                }while(PlayerInput < 0 || PlayerInput >(Connect4.getNumColumns()-1));

                //@param if statement that checks if the chosen column is full
                    if (Connect4.checkIfFree(PlayerInput) == true) {
                        Connect4.placeToken('X', PlayerInput);

                    } else if (Connect4.checkIfFree(PlayerInput) == false) {
                        System.out.println("Column is full");
                        if(Connect4.checkTie()==true){
                            System.out.println("Test2");
                            break;
                        }
                    }
                    if (Connect4.checkIfFree(PlayerInput)!=true){
                        if(Connect4.checkTie()!=true){
                            break;
                        }
                    }
            }while(Connect4.checkIfFree(PlayerInput) != true&& Connect4.checkForWin(PlayerInput) !=true && Connect4.checkTie()!=true);

            //@param if statement that checks if player X has won, and prompts the user if they want to play again if need be
            if(Connect4.checkForWin(PlayerInput)==true){
                System.out.println(Connect4.toString());
                System.out.println("Player X Won!");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true || menuChoice.equals("N")==true){
                    System.exit(0);
                }else{
                    //@param 3 do-while loops take in the user's desired rows, columns, and win number
                    //       in order to pass them into the constructor
                    do {
                        System.out.println("How Many rows should be on the board?");
                        NumRow = keyboard.nextInt();
                        if(NumRow<Connect4.MIN_ROWS){
                            System.out.println("Must have at least 3 rows");
                        }
                        else if(NumRow >Connect4.MAX_ROW){
                            System.out.println("Can have at most 100 rows");
                        }

                    }while(NumRow<Connect4.MIN_ROWS || NumRow >Connect4.MAX_ROW);

                    do {
                        System.out.println("How Many columns should be on the board?");
                        NumCol = keyboard.nextInt();
                        if(NumCol<Connect4.MIN_COL){
                            System.out.println("Must have at least 3 columns");
                        }
                        else if(NumCol >Connect4.MAX_COL){
                            System.out.println("Can have at most 100 columns");
                        }
                    }while(NumCol<Connect4.MIN_COL || NumCol >Connect4.MAX_COL);

                    do {
                        System.out.println("How many in a row to win");
                        NumWin = keyboard.nextInt();
                        if(NumWin<Connect4.MIN_WIN){
                            System.out.println("Must have at least 3 in a row to win");
                        }
                        else if(NumWin >Connect4.MAX_WIN){
                            System.out.println("Can have at most 25 in a row to win");
                        }
                    }while(NumWin<Connect4.MIN_WIN || NumWin >Connect4.MAX_WIN);
                    Connect4 = new GameBoard(NumRow, NumCol, NumWin);
                    continue;
                }
            }

            //@param Checks for a tie if the game board is full and neither player has won
            if(Connect4.checkTie()==true){
                System.out.println(Connect4.toString());
                System.out.println("The Game is a Tie");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true || menuChoice.equals("N")==true){
                    System.exit(0);
                }else{
                    //@param 3 do-while loops take in the user's desired rows, columns, and win number
                    //       in order to pass them into the constructor
                    do {
                        System.out.println("How Many rows should be on the board?");
                        NumRow = keyboard.nextInt();
                        if(NumRow<Connect4.MIN_ROWS){
                            System.out.println("Must have at least 3 rows");
                        }
                        else if(NumRow >Connect4.MAX_ROW){
                            System.out.println("Can have at most 100 rows");
                        }

                    }while(NumRow<Connect4.MIN_ROWS || NumRow >Connect4.MAX_ROW);

                    do {
                        System.out.println("How Many columns should be on the board?");
                        NumCol = keyboard.nextInt();
                        if(NumCol<Connect4.MIN_COL){
                            System.out.println("Must have at least 3 columns");
                        }
                        else if(NumCol >Connect4.MAX_COL){
                            System.out.println("Can have at most 100 columns");
                        }
                    }while(NumCol<Connect4.MIN_COL || NumCol >Connect4.MAX_COL);

                    do {
                        System.out.println("How many in a row to win");
                        NumWin = keyboard.nextInt();
                        if(NumWin<Connect4.MIN_WIN){
                            System.out.println("Must have at least 3 in a row to win");
                        }
                        else if(NumWin >Connect4.MAX_WIN){
                            System.out.println("Can have at most 25 in a row to win");
                        }
                    }while(NumWin<Connect4.MIN_WIN || NumWin >Connect4.MAX_WIN);
                    Connect4 = new GameBoard(NumRow, NumCol, NumWin);
                    continue;
                }
            }


            do {
                //@param Calls upon the toString method so the user can see GameBoard
                System.out.println(Connect4.toString());
                //@param Do-while statement that validates that the user input correct data, and prompts for another entry if false
                do {
                    System.out.println("Player O, what column do you want to place your marker in?");
                    PlayerInput = keyboard.nextInt();
                    if (PlayerInput > (Connect4.getNumColumns()-1)) {
                        System.out.println("Column cannot be greater than " +(Connect4.getNumColumns()-1));
                    }
                    else if(PlayerInput < 0){
                        System.out.println("Column cannot be less than 0");
                    }
                }while(PlayerInput < 0 || PlayerInput >(Connect4.getNumColumns()-1));

                //if statement that checks if the chosen column is full
                if (Connect4.checkIfFree(PlayerInput) == true) {
                    Connect4.placeToken('O', PlayerInput);

                }else if (Connect4.checkIfFree(PlayerInput) == false) {
                    System.out.println("Column is full");
                    if(Connect4.checkTie()==true){
                        System.out.println("Test2");
                        break;
                    }

                }
                if (Connect4.checkIfFree(PlayerInput)!=true){
                    if(Connect4.checkTie()==true){
                        break;
                    }
                }

            }while(Connect4.checkIfFree(PlayerInput) != true&& Connect4.checkForWin(PlayerInput) !=true &&Connect4.checkTie()!=true);

            //if statement that checks if player X has won, and prompts the user if they want to play again if need be
            if(Connect4.checkForWin(PlayerInput)==true){
                System.out.println(Connect4.toString());
                System.out.println("Player O Won!");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true || menuChoice.equals("N")==true){
                    System.exit(0);
                }else{
                    //@param 3 do-while loops take in the user's desired rows, columns, and win number
                    //       in order to pass them into the constructor
                    do {
                        System.out.println("How Many rows should be on the board?");
                        NumRow = keyboard.nextInt();
                        if(NumRow<Connect4.MIN_ROWS){
                            System.out.println("Must have at least 3 rows");
                        }
                        else if(NumRow >Connect4.MAX_ROW){
                            System.out.println("Can have at most 100 rows");
                        }

                    }while(NumRow<Connect4.MIN_ROWS || NumRow >Connect4.MAX_ROW);

                    do {
                        System.out.println("How Many columns should be on the board?");
                        NumCol = keyboard.nextInt();
                        if(NumCol<Connect4.MIN_COL){
                            System.out.println("Must have at least 3 columns");
                        }
                        else if(NumCol >Connect4.MAX_COL){
                            System.out.println("Can have at most 100 columns");
                        }
                    }while(NumCol<Connect4.MIN_COL || NumCol >Connect4.MAX_COL);

                    do {
                        System.out.println("How many in a row to win");
                        NumWin = keyboard.nextInt();
                        if(NumWin<Connect4.MIN_WIN){
                            System.out.println("Must have at least 3 in a row to win");
                        }
                        else if(NumWin >Connect4.MAX_WIN){
                            System.out.println("Can have at most 25 in a row to win");
                        }
                    }while(NumWin<Connect4.MIN_WIN || NumWin >Connect4.MAX_WIN);
                    Connect4 = new GameBoard(NumRow, NumCol, NumWin);
                    continue;
                }
            }

            //@param if statement that checks if the chosen column is full
            if(Connect4.checkTie()==true){
                System.out.println(Connect4.toString());
                System.out.println("The Game is a Tie");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true || menuChoice.equals("N")==true){
                    System.exit(0);
                }else{
                    //@param 3 do-while loops take in the user's desired rows, columns, and win number
                    //       in order to pass them into the constructor
                    do {
                        System.out.println("How Many rows should be on the board?");
                        NumRow = keyboard.nextInt();
                        if(NumRow<Connect4.MIN_ROWS){
                            System.out.println("Must have at least 3 rows");
                        }
                        else if(NumRow >Connect4.MAX_ROW){
                            System.out.println("Can have at most 100 rows");
                        }

                    }while(NumRow<Connect4.MIN_ROWS || NumRow >Connect4.MAX_ROW);

                    do {
                        System.out.println("How Many columns should be on the board?");
                        NumCol = keyboard.nextInt();
                        if(NumCol<Connect4.MIN_COL){
                            System.out.println("Must have at least 3 columns");
                        }
                        else if(NumCol >Connect4.MAX_COL){
                            System.out.println("Can have at most 100 columns");
                        }
                    }while(NumCol<Connect4.MIN_COL || NumCol >Connect4.MAX_COL);

                    do {
                        System.out.println("How many in a row to win");
                        NumWin = keyboard.nextInt();
                        if(NumWin<Connect4.MIN_WIN){
                            System.out.println("Must have at least 3 in a row to win");
                        }
                        else if(NumWin >Connect4.MAX_WIN){
                            System.out.println("Can have at most 25 in a row to win");
                        }
                    }while(NumWin<Connect4.MIN_WIN || NumWin >Connect4.MAX_WIN);
                    Connect4 = new GameBoard(NumRow, NumCol, NumWin);
                    continue;
                }
            }

        }while(menuChoice != "n" ||menuChoice != "N");



    }



}
