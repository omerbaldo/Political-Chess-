package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author      Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version     1.1         
 * @since       1.1       
 */
public class game implements Serializable{
	
	/** 
	 * To Save Game
	 */
	static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Date date;
	
	/**
	 * The game board that holds board for current move.
	 * 00 is top left
	 *
	 *	77 is bottom right
	 */
	public  piece[][] board = new piece[8][8];  

	
	/**
	 * All past moves. 
	 */
	public  List<piece[][]> pastMoves =  new ArrayList<piece[][]>();
	
	
	/**
	 * iterator for looping through the past moves  
	 */
	public int iterator = 0;
	
	/**
	 * name of current game being played   
	 */
	public String name = "";
	
	

	

	/**
	 * Whether or not current game is finished
	 */
	public boolean gameFinished = false;		


	public String result="";

	/**
	 * Method that quickly returns if a number is even
	 * @param an int
	 * @return true for even, false for odd 
	 */
	boolean isEven(int val){ return val % 2==0; }
	
	/**
	 * Players p1 is white
	 */
	public player p1 = new player(true, "white");
	
	/**
	 * Players p2 is black
	 */
	public player p2 = new player(false, "black");
		
	/**
	 * The current player
	 */
	public player currPlayer = this.p1;
	
	
	public void updateKings(){
			king wk = null;
			king bk = null;
			for(int i = 0; i<8; i++){
				for(int j = 0; j < 8; j++){
					if(board[i][j]!=null){
						if(board[i][j] instanceof king){
							king o = (king)board[i][j];
							if(o.team.equals("black")){
								bk = o;
							}else{
								wk = o;
							}
						}
					}
				}
			}


		p1.k = wk;
		p2.k = bk;
	}



	
	/**
	 * Prints the board
	 */
	public void print(){
		for (int i=0; i < 8;i++){
			for (int j = 0; j < 8; j++){
				if(board[i][j] == null){
					if (isEven(i)){ //current row is even 
						if(isEven(j)){ //current column is even
							System.out.print("  " + " ");							
						}else{
							System.out.print("##"+ " ");
						}
					}else{
						if(isEven(j)){ //current column is even
							System.out.print("##"+ " ");
						}else{
							System.out.print("  "+ " ");
						}
					}
				}else {
					System.out.print(board[i][j] + " "); //print out pieces name (fromt to string)
				}
				
			}

			System.out.println(8-(i)); //prints out the row 
		}   

		//prints out a-h
		for (int i = 97; i < 105; i++){ //ascii values  http://ee.hawaii.edu/~tep/EE160/Book/chap4/subsection2.1.1.1.html
			System.out.print((char)i + "  " );
			
		}
	}

	/**
	 * Board constructor
	 * <p>
	 * Fills up the 2-D array with objects that extend piece class
	 * </p>
	 */
	public game(String n){
		
		this.name = n;
		
		//pawns are initilizes first 
		for(int i = 0; i < 8; i++){
			board[1][i] = new pawn("bp","black",1,i);
			//p2.pieces.add(board[1][i]);
			
			
			board[6][i] = new pawn("wp","white",6,i);
			//p1.pieces.add(board[6][i]);
		}

		//castle is next
		
		//!!!
			//
			board[0][0] = new castle("bR","black",0,0);
			//p2.pieces.add(board[0][0]);
		

			board[0][7] = new castle("bR","black",0,7);
			//p2.pieces.add(board[0][7]);
			
			//7 0 
			//3 , 2
			board[7][0] = new castle("wR","white",7,0);
		//	p1.pieces.add(board[3][2]);
			
			board[7][7] = new castle("wR","white",7,7);
		//	p1.pieces.add(board[7][7]);
			
		//knight is next
			
			board[0][1] = new knight("bN","black",0,1);
		//	p2.pieces.add(board[0][1]);
			
			
			board[0][6] = new knight("bN","black",0,6);
		//	p2.pieces.add(board[0][6]);
			
			board[7][1] = new knight("wN","white",7,1);
		//	p1.pieces.add(board[7][1]);
			
			board[7][6] = new knight("wN","white",7,6);
		//	p1.pieces.add(board[7][6]);
			
		//bishop 
			
			board[0][2] = new bishop("bB","black",0,2);
		//	p2.pieces.add(board[0][2]);
			
			board[0][5] = new bishop("bB","black",0,5);
		//		p2.pieces.add(board[0][5]);
			
			board[7][2] = new bishop("wB","white",7,2);
		//	p1.pieces.add(board[7][2]);
			
			board[7][5] = new bishop("wB","white",7,5);
		//	p1.pieces.add(board[7][5]);
			
			//queen
			
			//!!	0 3
			
			//2 3
			board[0][3] = new queen("bQ","black",0,3);
		//	p2.pieces.add(board[0][3]);
			
			
			//7 3
			board[7][3] = new queen("wQ","white",7,3);
		//	p1.pieces.add(board[7][3]);
		
			//king
				
			// 4,4 
			board[0][4] = new king("bK","black",0,4);
		//	p2.pieces.add(board[0][4]);
			p2.k = (king)board[0][4];
		
			board[7][4] = new king("wK","white",7,4);
		//	p1.pieces.add(board[7][4]);
			p1.k = (king )board[7][4];
			
			//Set up time 
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MILLISECOND,0);
			
			this.date = new Date();
			
			//add initial board to list
			
					
			//this.pastMoves.add(this.board);
			//this.board = this.pastMoves.get(0);
			
	}
	
	
	/**
	 * Load Previous Game 
	 */
	public boolean loadGame(){
		if(pastMoves.size() == 0){return false;}//no previous moves set 
		
		board = pastMoves.get(pastMoves.size()-1);
		this.updateKings();

		return true;
	}
	
	/**
	 * Save current move to board 
	 */
	public void saveMove(){
		//if there is an error, create a new data board.

		piece[][] temp = new piece[8][8];

		for(int i = 0; i< 8;i++){
			for(int j = 0; j< 8;j++){
				piece p = board[i][j];
				if(p!=null){
					if(p instanceof bishop){
						temp[i][j] = new bishop(p.name, p.team, p.r, p.c);
					}else if(p instanceof castle){
						temp[i][j] = new castle(p.name, p.team, p.r, p.c);
					}else if(p instanceof king){
						temp[i][j] = new king(p.name, p.team, p.r, p.c);
					}else if(p instanceof knight){
						temp[i][j] = new knight(p.name, p.team, p.r, p.c);
					}else if(p instanceof pawn){
						temp[i][j] = new pawn(p.name, p.team, p.r, p.c);
					}else if(p instanceof queen){
						temp[i][j] = new queen(p.name, p.team, p.r, p.c);
					}	
				}
			}
		}


		pastMoves.add(temp);


		board = temp;



		this.updateKings();
	}
	
	/**
	 * For slide show option
	 * @return false if there are no more moves 
	 */
	public boolean nextMove(){	
		if((iterator+1)== pastMoves.size()){
			return false;
		}
		iterator = iterator + 1;
		
		board = pastMoves.get(iterator); 
		this.updateKings();

		return true;
	}
	
	/**
	 * For slide show option
	 * @return false if there are no more moves 
	 */
	public boolean prevMove(){
		if(iterator ==  0 ){
			return false;
		}
		iterator = iterator - 1;
		
		board = pastMoves.get(iterator); 
		this.updateKings();

		return true;
	}
	
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || (!(obj instanceof game)) ){
			return false;
		}
		game other = (game) obj;
		return this.name.equals(other.name);
		
		
	}
	
	@Override
	public String toString(){
		String s = this.name + "\nDate:  " + date.toString();
		if(this.gameFinished){
			s += "\nResult: " + result;
		}else{
			s += "\nResult: Ongoing Game. Click to continue ";
		}

		return s;
	}
}