package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;

/**
 * @author      Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version     1.1         
 * @since       1.1       
 */

public class queen extends piece implements Serializable{

	/**
	 * Constructor for queen 
	 */
	public queen(String n, String t,int x, int y) {
		// TODO Auto-generated constructor stub
	    super(n,t,x,y);
	}
	/**
	 * Checks if r and c is in range       
	 * @param row and column        						   
	 * @return true if row and column fall between 0 and 7
	 */
	
	boolean inRange(int r, int c){
		if((!(r>=0 && r<=7)) ||(!(c>=0 && c<=7)))
			return false;
		return true;
	}
	/**
	 * Checks if r move is valid      
	 * @param row and column and board      						   
	 * @return true or false
	 */
	
	
	@Override
	public boolean isValid(int r, int c,game b  ) {
				
		//ROW OR COLUMN NOT IN RANGE
	if(!inRange(r,c))
		return false;
		
		//PERSON WANTS TO SWITCH WITH ITS SELF 
	if(this.r == r && this.c== c)
		return false; 
		
		//ITS OWN PEICE IS THERE
	if(b.board[r][c]!=null)
		if(b.board[r][c].team.equals(this.team)){
			return false;
		}
		
	//same row
	if(r==this.r){ 
			
		if(c>this.c){//MOVING IT RIGHT

			for(int i = this.c+1;i!=c;i++){
				if(c==i){break;}
				
				if(b.board[this.r][i]!=null)
					return false;
			}
			return true;
			
		}else{ //MOVING IT LEFT
			
			for(int i = this.c-1;i!=c;i--){
				if(c==i){break;}
				if(b.board[this.r][i]!=null)
					return false;
			}
			
			return true;
			
		}
	}
	
	
	//SAME COLUMN. YOU WANT 
	if(c==this.c){ //same column

		if(r>this.r){ //MOVE IT DOWN
			
			for(int i = this.r+1;i!=r;i++){
				if(r==i){break;}

				if(b.board[i][this.c]!=null)
					return false;
			}
			return true;
				
				
				
				
			
		}else{ //MOVE IT UP
			
			for(int i = this.r-1;i!=r;i--){
				if(r==i){break;}

				if(b.board[i][this.c]!=null)
					return false;
			}
			return true;

		}	
	}
		
	//CHECK DIRECTION. NORTH EAST, SOUTH EAST, SOUTH WEST, NORTH WEST
	
	//System.out.println("acting as a bishop");
	
	//NORTH EAST
	if(r<this.r && c > this.c){
		//System.out.println("north east");

		
		int i = this.r-1;
		int j = this.c+1;
		
		while(inRange(i,j)){
			if(i==r && j==c)
				return true;
			
			if(b.board[i][j]!=null)
					return false;
			
			i--;
			j++;
			//System.out.println("at i " + i + " j " + j);
			
		}
		return false;
	}

	//SOUTH EAST
	if(r>this.r && c > this.c){
		//System.out.println("south east");

		int i = this.r+1;
		int j = this.c+1;
		
		
		while(inRange(i,j)){
			
			if(i==r && j==c)
				return true;
			if(b.board[i][j]!=null)
				return false;
			i++;
			j++;
		
		}

		return false;

	}
	
	//SOUTH WEST
	if(r>this.r && c <this.c){
		//System.out.println("south west");

		int i = this.r+1;
		int j = this.c-1;
		
		
		while(inRange(i,j)){

			if(i==r && j==c)
				return true;
			
				if(b.board[i][j]!=null)
					return false;
			i++;
			j--;	
		}
		return false;	
	}

	//NORTH WEST

	if(r<this.r && c <this.c){
		//System.out.println("north west ");

		int i = this.r-1;
		int j = this.c-1;
		
		while(inRange(i,j)){

			if(i==r && j==c)
				return true;
			
			if(b.board[i][j]!=b.board[this.r][this.c])
				if(b.board[i][j]!=null){						
					return false;
				}
			i--;
			j--;	
		}
		return false;
	}
	
	
	
	return false;
}

	/**
	 * Moves the piece
	 * @param row and column and board    						   
	 * @return true if success 
	 */
	@Override
	public boolean move(int r, int c,game b   ) {
		
		
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
