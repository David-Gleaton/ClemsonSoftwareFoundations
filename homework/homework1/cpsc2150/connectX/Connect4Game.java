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
        //Creation of the GameBoard Class, two input variables, and a Scanner object
        GameBoard Connect4 = new GameBoard();
        int PlayerInput =0;
        String menuChoice = "";
        Scanner keyboard = new Scanner(System.in);

        //Large do-while statement that repeats the block until the user quits the game
        do{
            //Calls upon the toString method
            System.out.println(Connect4.toString());
            do {

                //Do-while statement that validates that the user input correct data, and prompts for another entry if false
                do {
                    System.out.println("Player X, what column do you want to place your marker in?");
                    PlayerInput = keyboard.nextInt();
                    if (PlayerInput < 0 || PlayerInput > 6) {
                        System.out.println("Column choice must be between 0-6");
                    }
                }while(PlayerInput < 0 || PlayerInput >6);

                //if statement that checks if the chosen column is full
                    if (Connect4.checkIfFree(PlayerInput) == true) {
                        Connect4.placeToken('X', PlayerInput);
                    } else if (Connect4.checkIfFree(PlayerInput) == false) {
                        System.out.println("Column is full");
                    }

            }while(Connect4.checkIfFree(PlayerInput) == false&& Connect4.checkForWin(PlayerInput) !=true);

            //if statement that checks if player X has won, and prompts the user if they want to play again if need be
            if(Connect4.checkForWin(PlayerInput)==true){
                System.out.println(Connect4.toString());
                System.out.println("Player X Won!");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true){
                    System.exit(0);
                }else{
                    Connect4.cleanSlate();
                    continue;
                }
            }

            //Checks for a tie if the game board is full and neither player has won
            if(Connect4.checkTie()==true){
                System.out.println(Connect4.toString());
                System.out.println("The Game is a Tie");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true){
                    System.exit(0);
                }else{
                    Connect4.cleanSlate();
                    continue;
                }
            }

            //Calls upon the toString method
            System.out.println(Connect4.toString());
            do {
                //Do-while statement that validates that the user input correct data, and prompts for another entry if false
                do {
                    System.out.println("Player O, what column do you want to place your marker in?");
                    PlayerInput = keyboard.nextInt();
                    if (PlayerInput < 0 || PlayerInput > 6) {
                        System.out.println("Column choice must be between 0-6");
                    }
                }while(PlayerInput < 0 || PlayerInput >6);

                //if statement that checks if the chosen column is full
                if (Connect4.checkIfFree(PlayerInput) == true) {
                    Connect4.placeToken('O', PlayerInput);
                } else if (Connect4.checkIfFree(PlayerInput) == false) {
                    System.out.println("Column is full");
                }

            }while(Connect4.checkIfFree(PlayerInput) == false&& Connect4.checkForWin(PlayerInput) !=true);

            //if statement that checks if player X has won, and prompts the user if they want to play again if need be
            if(Connect4.checkForWin(PlayerInput)==true){
                System.out.println(Connect4.toString());
                System.out.println("Player O Won!");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true){
                    System.exit(0);
                }else{
                    Connect4.cleanSlate();
                }
            }

            //if statement that checks if the chosen column is full
            if(Connect4.checkTie()==true){
                System.out.println(Connect4.toString());
                System.out.println("The Game is a Tie");
                System.out.println("Would you like to play again? y/n");
                menuChoice = keyboard.next();
                if(menuChoice.equals("n")==true){
                    System.exit(0);
                }else{
                    Connect4.cleanSlate();
                }
            }

        }while(menuChoice != "n");



    }
}
