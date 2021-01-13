// /**
//  * The class <b>TicTacToeGame</b> is the
//  * class that implements the Tic Tac Toe Game.
//  * It contains the grid and tracks its progress.
//  * It automatically maintain the current state of
//  * the game as players are making moves.
//  *
//  * @author Guy-Vincent Jourdan, University of Ottawa
//  */

import java.lang.*;

public class TicTacToeGame {

   /**
	* The board of the game, stored as a one dimension array.
	*/
	private CellValue[] board;

   /**
	* level records the number of rounds that have been
	* played so far. 
	*/
	private int level;

   /**
	* gameState records the current state of the game.
	*/
	private GameState gameState;


   /**
	* lines is the number of lines in the grid
	*/
	private int lines;
	

   /**
	* columns is the number of columns in the grid
	*/
	private int columns;


   /**
	* sizeWin is the number of cell of the same type
	* that must be aligned to win the game
	*/
	private int sizeWin;

   /**
	* default constructor, for a game of 3x3, which must
	* align 3 cells
	*/
	public TicTacToeGame(){

		board = new CellValue[9];
		for (int i = 0; i < board.length; i++)
		{
			board[i] = CellValue.EMPTY;
		}
		this.lines = 3;
		this.columns = 3;
		this.sizeWin = 3;
		this.level = 0;
		this.gameState = GameState.PLAYING; 
	}


   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game. 3 cells must
	* be aligned.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
  	*/
	public TicTacToeGame(int lines, int columns){

		 board = new CellValue[lines * columns];
		 for (int i = 0; i < board.length; i++)
		 {
			board[i] = CellValue.EMPTY;
		 }
		 this.lines = lines;
		 this.columns = columns;
		 this.sizeWin = 3;
		 this.level = 0;
		 this.gameState = GameState.PLAYING;

	}

   /**
	* constructor allowing to specify the number of lines
	* and the number of columns for the game, as well as 
	* the number of cells that must be aligned to win.
   	* @param lines
    *  the number of lines in the game
    * @param columns
    *  the number of columns in the game
    * @param sizeWin
    *  the number of cells that must be aligned to win.
  	*/
	public TicTacToeGame(int lines, int columns, int sizeWin){

		board = new CellValue[lines * columns];
		for (int i = 0; i < board.length; i++)
		{
			board[i] = CellValue.EMPTY;
		}
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;
		this.level = 0;
		this.gameState = GameState.PLAYING;

	}



   /**
	* getter for the variable lines
	* @return
	* 	the value of lines
	*/
	public int getLines(){

		return lines;

	}

   /**
	* getter for the variable columns
	* @return
	* 	the value of columns
	*/
	public int getColumns(){

		return columns;

	}

   /**
	* getter for the variable level
	* @return
	* 	the value of level
	*/
	public int getLevel(){

		return level; 

	}

  	/**
	* getter for the variable sizeWin
	* @return
	* 	the value of sizeWin
	*/
	public int getSizeWin(){

		return sizeWin;

	}

   /**
	* getter for the variable gameState
	* @return
	* 	the value of gameState
	*/
	public GameState getGameState(){

		return gameState; 

	}

   /**
	* returns the cellValue that is expected next,
	* in other word, which played (X or O) should 
	* play next.
	* This method does not modify the state of the
	* game.
	* @return 
    *  the value of the enum CellValue corresponding
    * to the next expected value.
  	*/
 
 	public CellValue nextCellValue(){

		CellValue s1 = CellValue.EMPTY;

		if (this.getLevel() % 2 == 0) {
			s1 = CellValue.X;
			System.out.print("X to play: ");
		}
		else {
			s1 = CellValue.O; 
			System.out.print("O to play: ");
		}
		return s1;
 	}
		

   /**
	* returns the value  of the cell at
	* index i.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
   	* @param i
    *  the index of the cell in the array board
    * @return 
    *  the value at index i in the variable board.
  	*/
 	public CellValue valueAt(int i) {

 		if(i < 0 || i > board.length) {
 			System.out.println("The value should be between 1 and " + board.length); 
 		} 
 		return board[i];
 	}

	

   /**
	* This method is called when the next move has been
	* decided by the next player. It receives the index
	* of the cell to play as parameter.
	* If the index is invalid, an error message is
	* printed out. The behaviour is then unspecified
	* If the chosen cell is not empty, an error message is
	* printed out. The behaviour is then unspecified
	* If the move is valide, the board is updated, as well
	* as the state of the game.
	* To faciliate testing, is is acceptable to keep playing
	* after a game is already won. If that is the case, the
	* a message should be printed out and the move recorded. 
	* the  winner of the game is the player who won first
   	* @param i
    *  the index of the cell in the array board that has been 
    * selected by the next player
  	*/
 	public void play(int i) {

		if(i < 0 || i > board.length)
		{
			System.out.println("The value should be between 1 and " + board.length);
		}
		else if(board[i] != CellValue.EMPTY) {
			System.out.println("This cell has already been played");
		}

		else
		{

			if(this.getLevel() % 2 == 0)
			{
				board[i] = CellValue.X;
			}
			else
			{
				board[i] = CellValue.O;
			}
			setGameState(i);
			this.level++; 

		}
 	}


   /**
	* A helper method which updates the gameState variable
	* correctly after the cell at index i was just set in
	* the method play(int i)
	* The method assumes that prior to setting the cell
	* at index i, the gameState variable was correctly set.
	* it also assumes that it is only called if the game was
	* not already finished when the cell at index i was played
	* (i.e. the game was playing). Therefore, it only needs to 
	* check if playing at index i has concluded the game, and if
	* set the oucome correctly
	* 
   	* @param
    *  the index of the cell in the array board that has just 
    * been set
  	*/


	private void setGameState(int i) {
 
 	int xcount = 1;
 	int ocount = 1;
 	
 

 	//columns

 	for (int j = (i % this.columns); j<board.length - (this.columns); j+=this.columns)
 	{
 		if (board[j] == CellValue.X && board[j+this.columns] == CellValue.X)
 		{
 			xcount++;
 			if (xcount >= this.sizeWin)
 			{
 			this.gameState = GameState.XWIN;
 			break;
 			}
 		}

 		else
 		{
 			xcount = 1;
 		}

 		if (board[j] ==  CellValue.O && board[j+this.columns] == CellValue.O)
 		{
 			ocount++;
 			if (ocount >= this.sizeWin)
 			{
 			this.gameState = GameState.OWIN;
 			break;
 			}
 		}
 		else
 		{
 			ocount = 1;
 		}

 	}

 	//rows

 	
 	for (int k = (i / this.columns) * this.columns ; k < (this.columns) + ((i / columns) * columns) - 1; k++)
 	{
 		if (board[k] == CellValue.X && board[k+1] == CellValue.X)
 		{
 			xcount++;
 			if (xcount >= this.sizeWin)
 			{
 			this.gameState = GameState.XWIN;
 			break;
 			}
 		}

 		else 
 		{
 			xcount = 1;
 		}

 		if (board[k] == CellValue.O && board[k+1] == CellValue.O)
 		{
 			ocount++;
 			if (ocount >= this.sizeWin)
 			{
 				this.gameState = GameState.OWIN;
 				break;
 			}
 		}

 		else
 		{
 			ocount = 1;
 		}
 	}


 	// checking for diagonals of SQAURE GRIDS( n x n)
 	// Diagonals from (left to right)
	//int boardSize = (int)(board.length/Math.sqrt(board.length));
	if(columns >= sizeWin) {
		int pivotLeft = columns+1;
	xcount = 0;
	ocount = 0;
	for(int w = 0; w < board.length; w=w+pivotLeft) {
		if(board[w] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[w] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	} 


	// Diagonals from (right to left)
	int pivotRight = columns-1;
	xcount = 0;
	ocount = 0;
	for(int d = pivotRight; d < board.length-1; d=d+pivotRight) {
		if(board[d] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[d] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	}



	// checking for Diagonals of NON-SQUARE GRIDS( n x m )
	// left side
	int pivotLeftNonSquare = columns+1;
	xcount = 0;
	ocount = 0;
	for(int w = 0; w < board.length; w=w+pivotLeftNonSquare) {
		if(board[w] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[w] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	} 

	// left side 2
	xcount = 0;
	ocount = 0;
	for(int w = 1; w < board.length; w=w+pivotLeftNonSquare) {
		if(board[w] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[w] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	} 

	// right side 
	int pivotRightNonSquare = columns-1;
	xcount = 0;
	ocount = 0;
	for(int d = pivotRightNonSquare; d < board.length-1; d=d+pivotRightNonSquare) {
		if(board[d] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[d] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	}

	// right side 2
	xcount = 0;
	ocount = 0;
	for(int d = pivotRightNonSquare-1; d < board.length-1; d=d+pivotRightNonSquare) {
		if(board[d] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[d] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	}

	// diagonals for square matrices higher than 4 x 4
	// left side 3
	xcount = 0;
	ocount = 0;
	for(int w = 2; w < board.length; w=w+pivotLeftNonSquare) {
		if(board[w] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[w] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	} 

	// right side 3 
	xcount = 0;
	ocount = 0;
	for(int d = pivotRightNonSquare-2; d < board.length-1; d=d+pivotRightNonSquare) {
		if(board[d] == CellValue.X) {
			xcount++;
			if(xcount >= sizeWin) {
				this.gameState = gameState.XWIN;
			}
		} else if(board[d] == CellValue.O) {
			ocount++;
			if(ocount >= sizeWin) {
				this.gameState = gameState.OWIN;
			}
		}
	}
	}
	

 	// checking for a draw
 	if(this.level == board.length-1) {
 		this.gameState = gameState.DRAW;
 	}

 	}
 	

   /**
	* Returns a String representation of the game matching
	* the example provided in the assignment's description
	* 
   	* @return
    *  String representation of the game
  	*/
		public String toString()
		{
		
		
		
		final String NL;

        NL = System.getProperty("line.separator");

        String str = "";
		int numberOfDashes = (columns * 2);

		int j = 0;
	
		for(int i = 0; i < board.length; i++)
		{
			j++;

			if(board[i] == CellValue.EMPTY) {
				str += " " + "|";
			} else {
				str += board[i] + "|";
			}
			
			if(j == columns)
			{
				
				str+= NL;
				j=0;
				for (int m = 0; m < numberOfDashes; m++) {
					str+="-";
				}
				str+=NL;
			}

		}
		return str;

		}
}




