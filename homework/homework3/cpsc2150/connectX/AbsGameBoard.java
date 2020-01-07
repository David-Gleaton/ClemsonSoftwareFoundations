package cpsc2150.connectX;

public abstract class AbsGameBoard implements IGameBoard {


    /**
     *
     * @return the string representation of the GameBoard
     * @requires this != null
     * @ensures toString = the string value of the GameBoard
     */



    @Override
    public String toString(){
        //Creation of a StringBoard the same size as GameBoard in order to flip it for user viewing
        char[][] StringBoard = new char[MAX_ROW][MAX_COL];
        //Creation of the Return String, with the top column numbers listed for useer viewing
        String ReturnBoard ="|0";


        for(int i = 1; i<this.getNumColumns();i++) {
            ReturnBoard = ReturnBoard + "|" + i;
            if(i==this.getNumColumns()-1){
                ReturnBoard = ReturnBoard +"|\n|";
            }
        }
        //for loop that copys all the info from GameBoard into StringBoard, only with the rows fliped
        for(int i =0, j=getNumRow()-1; i<=getNumRow() && j>=0; i++,j--){
            for(int a = 0; a<this.getNumColumns();a++){
                StringBoard[j][a]=this.whatsAtPos(i,a);
            }
        }

        //Nested for loop that feeds all the information from StringBoard into ReturnBoard, properly formatted for the user's viewing
        for(int i = 0; i<getNumRow();i++){
            for(int j = 0; j<this.getNumColumns();j++){
                if(j==(this.getNumColumns()-1)){
                    if(i!=(getNumRow()-1)){
                        ReturnBoard = ReturnBoard + StringBoard[i][j] +"|\n|";
                    }
                    if(i==(getNumRow()-1))
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

}
