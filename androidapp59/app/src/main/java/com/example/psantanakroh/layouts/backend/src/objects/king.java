package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * @author      Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version     1.1         
 * @since       1.1       
 */
public class king extends piece implements Serializable{
	public boolean firstmove = true;

	/**
	 * States whether or not king can check
	 */
	boolean check;
	
	
	/**
	 * States whether or not king is in check mate
	 */
	boolean checkmate;
	
	
	/**
	 * If the king is doing a castle move
	 */	
	public boolean canCastle;
	


	/**
	 * Constructor                       
	 * @param  name of piece, team, x is row it starts, y is column it starts at 
	 * @return void
	 */
	public king(String n, String t,int x, int y) {
	    super(n,t,x,y);
	}
	
	/**
	 * Moves the piece. If there piece can castle, then it will switch the castle and the king                       
	 * @param  row of where you want to go, column of where you want to go, and current board so you can traverse
	 * @return success or not
	 */
	@Override
	public boolean move(int r, int c, game b) {

		piece temp = b.board[r][c];
		int oldr = this.r;
		int oldc = this.c;
	
	
		firstmove =false;	
		if(this.canCastle){
			canCastle = false;
			if(c>this.c){
				b.board[r][c]=b.board[this.r][this.c];
				b.board[this.r][this.c] = null;
				b.board[this.r][5] = b.board[this.r][7];
				
				castle ca = (castle)b.board[this.r][7];
				ca.firstmove =false;
				b.board[this.r][7] = null;
				this.r = r;
				this.c = c;
				return true;
			}else{
				b.board[r][c]=b.board[this.r][this.c];
				b.board[this.r][this.c] = null;
				b.board[this.r][3] = b.board[this.r][0];
				
				castle ca = (castle)b.board[this.r][0];
				ca.firstmove =false;
				b.board[this.r][0] = null;
				this.r = r;
				this.c = c;
				return true;
			}
		}
		
		System.out.println("20");

		b.board[r][c] = b.board[this.r][this.c];
		b.board[this.r][this.c] = null;
				
		this.r = r;
		this.c = c;

		System.out.println("Going to check for check my r is " + r + " my c is " + c);


		//Tests if move causes check
		if(this.team.equals(b.p1.team)){
			System.out.println("King Move causes check");

			if(b.p1.k.check(b)){
				System.out.println("30");

				//undo changes  
				
				b.board[oldr][oldc] = b.board[r][c];
				b.board[r][c] = temp;
				this.r = oldr;
				this.c = oldc;
						
				//System.out.println("false");
				return false;
			}
		}else{


			System.out.println("40");

			if(b.p2.k.check(b)){
				System.out.println("King Move causes check");


				//undo changes  
				
				b.board[oldr][oldc] = b.board[r][c];
				b.board[r][c] = temp;
				this.r = oldr;
				this.c = oldc;
						
				return false;
			}
		}
			
		
		
		
		
		
		
		return true;
	}
	

	/**
	 * Returns if move is valid
	 * <p>
	 * 1) check if piece is in range 2) check if person is switching with themselves
	 * 3) check if same team piece is there 4)check for castling
	 * 5) check 8 positions king can go
	 * </p>
	 * @param  //row of where you want to go, column of where you want to go, and current board so you can traverse
	 * @return success or not
	 */
	
	@Override
	public boolean isValid(int r, int c, game b) {
		//System.out.println("trying to get this.r " + this.r + " this.c " + this.c +" to r " + r + " c " + c );
		

		if((!(r>=0 && r<=7)) ||(!(c>=0 && c<=7)))	 //not in range
			return false;
		
		if(this.r == r && this.c== c)				 //person wants to switch with them selves
				return false; 
			
		if(b.board[r][c]!=null)
			if(b.board[r][c].team.equals(this.team)) //its own piece is there
						return false;	
		

		
														//check for castle
		
		if(firstmove && (this.r == r) && (c==2 || c== 6)){

				//System.out.println("castling");
				if(this.check)		//cant castle if your in check 
					return false;
		
				if(c==2){
					
					if(b.board[this.r][0]==null)			//no castle there
						return false;
					

					if(!(b.board[this.r][0] instanceof castle))//not a castle
						return false;
					
					castle ca = (castle)b.board[r][0];	
					if(!ca.firstmove)						//castle already moved
						return false;
					

					for(int i = this.c-1; i>=0; i--){
						if(b.board[this.r][i]!=null){
							if(b.board[this.r][i].name.charAt(1) != 'R')//something in between 
								return false;
						}
					}
					
					canCastle = true;
					return true;
					
					
					
				}else{

					if(b.board[this.r][7]==null)			//no castle there
						return false;
					
					if(!(b.board[this.r][7] instanceof castle))//not a castle
						return false;
					
					castle ca = (castle)b.board[r][7];	
					if(!ca.firstmove)						//castle already moved
						return false;
					

					for(int i = this.c+1; i<=7; i++){
						if(b.board[this.r][i]!=null){
							if(b.board[this.r][i].name.charAt(1) != 'R')
								return false;
						}	
					}
					canCastle = true;
					return true;
				
				}
		
			

		
				
			
		}
		
		
													//check 8 locations the king can go 
		if( (Math.abs(this.r - r)>1) || (Math.abs(this.c - c)>1) ){
			
			return false;
		}
		
		return true;
	}




	ArrayList<piece> getPieces(game b){
		ArrayList<piece> pieces = new ArrayList<piece>();


		for(int i = 0; i <=  7;i++){
			for(int j = 0; j <=  7;j++) {
				if(b.board[i][j]!=null){

					if(this.team.equals("black")){
						if(b.board[i][j].team.equals("black"))
							pieces.add(b.board[i][j]);

					}else{
						if(b.board[i][j].team.equals("white"))
							pieces.add(b.board[i][j]);

					}



				}
			}
		}
		return pieces;

	}


	
	/**
	 * returns true if king is in checkmate
	 * <p>
	 * Works by checking if every valid position that the kings (that is in check) pieces 
	 * stops a check. If so, then not checkmate. Otherwise checkmate
	 * 
	 * </p>
	 * @param  llgame
	 * @return true or false if king is in checkmate
	 */	










	public boolean checkMate(game b){
		

		if(this.team.equals("black")){

			for(piece wp : getPieces(b)){//iterate through your own pieces
				
				for(int i = 0; i <=  7;i++){		
						for(int j = 0; j <=  7;j++){
				
							if(wp==null){continue;}
								if(wp.isValid(i,j,b)){
															//System.out.println("bp "+ bp.name + "at r " + bp.r + " c " + bp.c + "can go to " + "i " +i + " j "+j );
									
										//spot you are going to is null
										if(b.board[i][j]==null){
										//	System.out.println(wp.name + "trying to go to" + i +" , " + j);

											int oldr = wp.r;
											int oldc = wp.c;
											
											//move
											b.board[i][j]=b.board[wp.r][wp.c];
											b.board[wp.r][wp.c] = null;
											wp.r = i;
											wp.c = j;
											
										//	System.out.println("currently king is at " + this.r + " , " + this.c);

											
											if(this.check(b)==false){
												
											//	System.out.println(wp.name + "at r " +oldr +" c " + oldc + "saves the day by going to r " + i + " c " + j);

												
												b.board[oldr][oldc] = b.board[i][j];
												wp.r = oldr;
												wp.c = oldc;
												b.board[i][j] = null;
												
												return false;
											}else{
												b.board[oldr][oldc] = b.board[i][j];
												wp.r = oldr;
												wp.c = oldc;
												b.board[i][j] = null;
											}	
										}else if(!b.board[i][j].team.equals(this.team)){										//spot you go to has enemy 
											
											piece en = b.board[i][j];
											
											int oldr = wp.r;
											int oldc = wp.c;
											
											//move
											b.board[i][j]=b.board[wp.r][wp.c];
											b.board[wp.r][wp.c] = null;
											wp.r = i;
											wp.c = j;
											

											
											
											if(this.check(b)==false){

												b.board[oldr][oldc] = b.board[i][j];
												b.board[i][j] = en;
												System.out.println("i is " + i + "j is " + j + "oldr "+ oldr + "oldc "+oldc);
												b.board[oldr][oldc].r= oldr;//!!!
												b.board[oldr][oldc].c = oldc;
												

											
											//	System.out.println(this.name + "saves the day by killing" + en.name);

					
												return false;
											}else{
												b.board[oldr][oldc] = b.board[i][j];
												b.board[i][j] = en;
												b.board[oldr][oldc].r= oldr;
												b.board[oldr][oldc].c = oldc;
											
											}	
																
											
										}else{	//spot you are going to has team mate

											continue;
										}

								}
						}
				}
		}
			
			
			
				
		}else if(this.team.equals("white")){

			for(piece wp : getPieces(b)){//iterate through your own pieces
				
				for(int i = 0; i <=  7;i++){		
						for(int j = 0; j <=  7;j++){
				
							if(wp==null){continue;}
								if(wp.isValid(i,j,b)){
															//System.out.println("bp "+ bp.name + "at r " + bp.r + " c " + bp.c + "can go to " + "i " +i + " j "+j );
									
										//spot you are going to is null
										if(b.board[i][j]==null){
											//System.out.println(wp.name + "trying to go to" + i +" , " + j);

											int oldr = wp.r;
											int oldc = wp.c;
											
											//move
											b.board[i][j]=b.board[wp.r][wp.c];
											b.board[wp.r][wp.c] = null;
											wp.r = i;
											wp.c = j;
											
											//System.out.println("currently king is at " + this.r + " , " + this.c);

											
											if(this.check(b)==false){
												
											//	System.out.println(wp.name + "at r " +oldr +" c " + oldc + "saves the day by going to r " + i + " c " + j);

												
												b.board[oldr][oldc] = b.board[i][j];
												wp.r = oldr;
												wp.c = oldc;
												b.board[i][j] = null;
												
												return false;
											}else{
												b.board[oldr][oldc] = b.board[i][j];
												wp.r = oldr;
												wp.c = oldc;
												b.board[i][j] = null;
											}	
										}else if(!b.board[i][j].team.equals(this.team)){										//spot you go to has enemy 
											
											piece en = b.board[i][j];
											
											int oldr = wp.r;
											int oldc = wp.c;
											
											//move
											b.board[i][j]=b.board[wp.r][wp.c];
											b.board[wp.r][wp.c] = null;
											wp.r = i;
											wp.c = j;
											

											
											
											if(this.check(b)==false){
												b.board[oldr][oldc] = b.board[i][j];
												b.board[i][j] = en;
												b.board[oldr][oldc].r= oldr;
												b.board[oldr][oldc].c = oldc;

											
												//System.out.println(this.name + "saves the day by killing" + en.name);

					
												return false;
											}else{
												b.board[oldr][oldc] = b.board[i][j];
												b.board[i][j] = en;
												b.board[oldr][oldc].r= oldr;
												b.board[oldr][oldc].c = oldc;
												
												

											
											}	
																
											
										}else{	//spot you are going to has team mate

											continue;
										}

								}
						}
				}
		}
			
					
		}
			
					
		return true;

	}
		
	/**
	 * returns true if king is in check (can be taken by an enemy team) 
	 * @param ;; game
	 * @return true or false if king is in check 
	 */	
	public boolean check(game b){


		for(int i = 0; i <=7; i++){
				for(int j = 0; j <=7; j++){
					if(b.board[i][j]!=null){
						
						if(!b.board[i][j].team.equals(this.team)){
							if(b.board[i][j].isValid(this.r,this.c,b)){
							//	System.out.println(b.board[i][j] + "can reach king");
								return true;
							}
							//System.out.println(b.board[i][j] + "cant reach king at " +this.r + this.c);

						}
					}
				}
			}
			
			return false;
	}	
}
