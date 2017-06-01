package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;

public class castle extends piece implements Serializable{
	
	boolean firstmove = true;
	
	
	
	
	
	public castle(String n, String t,int x, int y) {
		// TODO Auto-generated constructor stub
	    super(n,t,x,y);
	}

	@Override
	public boolean isValid(int r, int c, game b ) {
		//ERROR CASES
		
			//NOT SAME ROW OR COLUMN. 
			if(r!=this.r && c!=this.c)
				return false;
			
			//ROW OR COLUMN NOT IN RANGE
			if((!(r>=0 && r<=7)) ||(!(c>=0 && c<=7)))
				return false;
			
			//PERSON WANTS TO SWITCH WITH ITS SELF 
			if(this.r == r && this.c== c)
				return false; 
			
			//ITS OWN PEICE IS THERE
			if(b.board[r][c]!=null)
				if(b.board[r][c].team == this.team)
					return false;	
			
		
			
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
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		return false;
	}


	@Override
	public boolean move(int r, int c,game b ) {
		
		
		
		//------------------------------
				piece temp = b.board[r][c];
				int oldr = this.r;
				int oldc = this.c;
		//------------------------------
				
				
		
		
		firstmove =false;
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
