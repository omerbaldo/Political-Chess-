package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;

/**
 * @author      Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version     1.1         
 * @since       1.1       
 */
public class knight extends piece implements Serializable {
	/**
	 * Constructor for knight
	 * @param n is name, t is team, x is row, and y is column 
	 */
	public knight(String n, String t,int x, int y) {
	    super(n,t,x,y);
	}
	
	/**
	 * Checks if r move is valid      
	 * @param row and column and board      						   
	 * @return true or false
	 */
	

	@Override
	public boolean isValid(int r, int c,game b  ) {
		
	
		//ROW OR COLUMN NOT IN RANGE
		if((!(r>=0 && r<=7)) ||(!(c>=0 && c<=7)))
			return false;
		
		//PERSON WANTS TO SWITCH WITH ITS SELF 
		if(this.r == r && this.c== c)
			return false; 
		
		//ITS OWN PEICE IS THERE
		if(b.board[r][c]!=null)
			if(b.board[r][c].team.equals(this.team))
				return false;	

		//check 8 things 
		//top 4
		if ( (r==this.r-2)&&(c==this.c+1) ){
			return true;

		}else if((r==this.r-2)&&(c==this.c-1) ){
			return true;

		}else if((r==this.r-1)&&(c==this.c+2) ){
			return true;

		}else if((r==this.r-1)&&(c==this.c-2) ){
			return true;

		}else if ((r==this.r+2)&&(c==this.c+1) ){ //bottom four
			return true;

		}else if((r==this.r+2)&&(c==this.c-1) ){
			return true;

		}else if((r==this.r+1)&&(c==this.c+2) ){
						return true;

		}else if((r==this.r+1)&&(c==this.c-2) ){

			return true;
		}
		

		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Moves the piece
	 * @param row and column and board    						   
	 * @return true if success 
	 */

	@Override
	public boolean move(int r, int c,game b ) {
		
		//------------------------------
		piece temp = b.board[r][c];
		int oldr = this.r;
		int oldc = this.c;
//------------------------------
		
		
		
		
		
		b.board[r][c] = b.board[this.r][this.c];
		b.board[this.r][this.c] = null;
				
		this.r = r;
		this.c = c;		
		

		//Tests if move causes check
		if(this.team.equals(b.p1.team)){
			if(b.p1.k.check(b)){
				
				//undo changes  
				
				b.board[oldr][oldc] = b.board[r][c];
				b.board[r][c] = temp;
				this.r = oldr;
				this.c = oldc;
						
				return false;
			}
		}else{
			
			if(b.p2.k.check(b)){
				
				//undo changes  
				
				b.board[oldr][oldc] = b.board[r][c];
				b.board[r][c] = temp;
				this.r = oldr;
				this.c = oldc;
						
				return false;
			}
		}
				//------------------------------------------------
		
		return true;
	}


}
