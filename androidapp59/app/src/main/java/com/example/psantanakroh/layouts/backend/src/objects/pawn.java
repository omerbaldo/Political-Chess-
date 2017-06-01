package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;

/**
 * @author      Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version     1.1         
 * @since       1.1       
 */
public class pawn extends piece implements Serializable {
	
	
	/**
	 * Is it the pieces first turn
	 */
	boolean fistTurn =true;
	

	/**
	 * If you can enpassant
	 */
	public boolean enpassant;
	
	/**
	 * If piece is white
	 */
	public boolean enpassantingw;
	
	/**
	 * If piece is black
	 */
	public boolean enpassantingb;

	public int turn;

	public pawn(String n, String t, int x, int y) {
		// TODO Auto-generated constructor stub
		super(n, t, x, y);
		enpassant = false;
		enpassantingb = false;
		enpassantingw = false;
        turn = 0;

	}
	
	
	/**
	 * Checks if r move is valid
	 * @return true or false
	 */
	@Override
	public boolean isValid(int r, int c, game b) {
		// TODO Auto-generated method stub
		//System.out.println("ig got here");
		// if its not on board
		if ((!(r >= 0 && r <= 7)) || (!(c >= 0 && c <= 7)))
			return false;
		// if it tries to move to current spot
		if ((r == this.r) && (c == this.c))
			return false;

		if(this.team.equals("black") &&(r<this.r)){
			return false;
		}
		if(this.team.equals("white") &&(r>this.r)){
			return false;
		}
		if((!this.fistTurn)&&((Math.abs(this.r - r)>1)||(Math.abs(this.c - c)>1))){
			return false;
		}
		if(Math.abs(this.r-r)==1 && (Math.abs(this.c-c)>1))
			return false;




		// ITS OWN PEICE IS THERE
		if (b.board[r][c] != null) {
			if (b.board[r][c].team.equals(this.team))
				return false;
		}
		// check for basic legal move within same column
		if (c == this.c) {

			// if its in starting location & moves 2 spaces for white team
			if (this.team.equals("white") && (this.r == 6) && (r == this.r - 2)) {
				if (b.board[r][c] != null || b.board[r + 1][c] != null)
					return false;
				else {
					enpassant = true;
					return true;
				}
			}
			// if its in starting location & moves 2 spaces for black team
			else if (this.team.equals("black") && (this.r == 1) && (r == this.r + 2)) {
				if (b.board[r][c] != null || b.board[r - 1][c] != null)
					return false;
				else {
					enpassant = true;
					return true;
				}
			}
			// if its in starting location & moves 1 spaces for white team
			else if (this.team.equals("white") && (this.r == 6) && (r == this.r - 1)) {
				if (b.board[r][c] != null)
					return false;
				else {
					enpassant = false;
					return true;
				}
			}
			// if its in starting location & moves 1 spaces for black team
			else if (this.team.equals("black") && (this.r == 1) && (r == this.r + 1)) {
				if (b.board[r][c] != null)
					return false;
				else {
					enpassant = false;
					return true;
				}
			}

			// can always move forward once (white)
			else if (this.team.equals("white") && (r == this.r - 1)) {
				if (b.board[r][c] != null)
					return false;
				else {
					enpassant = false;
					return true;
				}
			}
			// can always move forward once (white)
			else if (this.team.equals("black") && (r == this.r + 1)) {
				if (b.board[r][c] != null)
					return false;
				else {
					enpassant = false;
					return true;
				}
			} else
				return false;

		}

		// attacking on diagonal
		else if ((c >= 0 && c <= 7) && ((c == this.c + 1) && (r == this.r + 1) || (r == this.r - 1))
				|| ((c == this.c - 1) && (r == this.r + 1) || (r == this.r - 1))) {


			if (b.board[r][c] != null) {
				return true;

			}else if(b.board[r][c] == null){

				//check for el passant

				//white check
				if(this.team.equals("white"))
				{
					//check spot under asking spot
					if(b.board[r+1][c] instanceof pawn)
					{
						pawn p = (pawn)b.board[r+1][c];
						if(p.enpassant == true)
						{
							enpassantingw = true;
							return true;
						}
						else
							return false;
					}
				}
				//check for black
				else if(this.team.equals("black"))
				{
					//check spot above asking spot


					if(b.board[r-1][c] instanceof pawn)
					{
						pawn p = (pawn)b.board[r-1][c];
						if(p.enpassant == true)
						{
							enpassantingb = true;
							return true;
						}
						else
							return false;
					}




				}
				else{
					return false;
				}

			}
		}

		// enpassont(pawn can kill another pawn that is right next to it. but
		// only if that
		// pawn just moved forward two spaces.)

		return false;

	}







	/**
	 * Moves the piece
	 * @param r and column and board
	 * @return true if success 
	 */



	@Override
	public boolean move(int r, int c, game b) {
		// TODO Auto-generated method stub
		
		//------------------------------
		piece temp = b.board[r][c];
		int oldr = this.r;
		int oldc = this.c;
//------------------------------
		
		
		this.fistTurn = false;
		
		b.board[r][c] = b.board[this.r][this.c];
		b.board[this.r][this.c] = null;
		
		if(enpassantingw == true)
		{
			b.board[r+1][c] = null;
			enpassantingw = false;
		}
		if(enpassantingb == true)
		{
			b.board[r-1][c] = null;
			enpassantingb = false;
		} 

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
				
				//System.out.println("causes check");
				return false;
			}
		}else{
			
			if(b.p2.k.check(b)){
				
				//undo changes  
				
				b.board[oldr][oldc] = b.board[r][c];
				b.board[r][c] = temp;
				this.r = oldr;
				this.c = oldc;
				
				//System.out.println("causes check");

						
				return false;
			}
		}
				//------------------------------------------------

        System.out.println("uping the turn count");
		turn = turn +1;
		return true;

	}

	
	
	
	
	
	
	/**
	 * Checks if piece is in promotion by checking its position       						   
	 * @return true if piece is in promotion 
	 */
	public boolean promotion() {

		if (this.team.equals("white") && (this.r == 0))
			return true;
		// perform promotion: default queen unless specified
		if (this.team.equals("black") && (this.r == 7))
			return true;
		// perform promotion: default queen unless specified
		return false;
	}

}
