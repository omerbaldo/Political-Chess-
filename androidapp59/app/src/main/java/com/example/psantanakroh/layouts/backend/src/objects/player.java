package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;
import java.util.*; 
/**
 * @author      Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version     1.1         
 * @since       1.1       
 */

public class player implements Serializable{
	

	/**
	 * Is it players turn 
	 */
	public boolean turn;
	

	/**
	 * Array list of pieces that belong to player
	 */
	//public ArrayList<piece> pieces;
	

	/**
	 * current team
	 */
	public String team;
	

	/**
	 * Its king
	 */
	public king k;
	

	/**
	 * Constructor for player
	 * @param t is turn, s is team name  
	 */
	public player(boolean t, String s){
		this.turn = t;
		this.team = s;
		//pieces = new ArrayList<piece>();
		
	}
	
}
