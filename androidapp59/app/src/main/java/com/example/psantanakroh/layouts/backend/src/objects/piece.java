package com.example.psantanakroh.layouts.backend.src.objects;

import java.io.Serializable;

/**
 * @author      Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version     1.1         
 * @since       1.1       
 */
public abstract class piece implements Serializable{
	/**
	 * Name of piece (ex wp)
	 */
	public String name;
	/**
	 * Team black or white
	 */
	public String team;
	/**
	 *Row
	 */
	public int r;
	/**
	 *Column
	 */
	public int c;
	
	/**
	 * Constructor for piece
	 * @param n is name, t is team, r is row, and c is colum  
	 */
	public piece(String n, String t, int r, int c){
		this.name = n;
		this.team = t;
		this.r = r;
		this.c = c;
	}
	
	/**
	 * Method that checks if a move is valid 
	 * @param row, column, current board
	 * @return boolean 
	 */
	abstract public boolean isValid(int r, int c, game b );
    
	/**
	 * To string just returns name (ex wp)
	 */
	public String toString(){
		return this.name;
	}
	
	/**
	 * Method that moves piece based on what type of piece it is 
	 * @param row, column, current board
	 */
	abstract public boolean move(int r, int c, game b );
	
}
