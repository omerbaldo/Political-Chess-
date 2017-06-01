package com.example.psantanakroh.layouts.backend.src;

import android.content.Context;

import com.example.psantanakroh.layouts.MainActivity;
import com.example.psantanakroh.layouts.backend.src.objects.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import android.content.Context;
import java.net.*;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import static android.content.Context.MODE_PRIVATE;



/**
 * @author Omer Baldo, Peter Santana-Kroh <address @ osb5@scarletmail.rutgers.edu> <address @ psantana94@gmail.com>
 * @version 1.1
 * @see .UML at https://bitbucket.org/psantanakroh/chess59/src/523d14c0781920b769f912c2adf351aa6c47eaae/uml.jpg?at=master&fileviewer=file-view-default
 * @since 1.1
 */

public class app {

    /**
     * The game board. Holds the player objects and a 2-D array to represent the board.
     */
    public static game Game;

    /**
     * The input / output tokenizer
     */
    static Scanner input = new Scanner(System.in);


    /**
     * Stores input from Scanner
     */
    //static String line;

    /**
     * Whether or not a draw was offered
     */
    static boolean drawOffered;


    /**
     * Data object that holds all games the user has
     */
    public static data info = new data();


    //--------------------------------------------------------------------------------------------------------------------

    public static boolean AI() {

        for (int i = 0; i < 8; i++) {                    // For each piece on current players team
            for (int j = 0; j < 8; j++) {                    //
                if (Game.board[i][j] != null) {            //
                    if (Game.board[i][j].team.equals(Game.currPlayer.team)) {

                        for (int k = 0; k < 8; k++) {                            // For everyone of the pieces valid moves
                            for (int l = 0; l < 8; l++) {

                                if (Game.board[k][l] != null)                //same team
                                    if (Game.board[k][l].team.equals(Game.currPlayer))
                                        continue;

                                if (Game.board[i][j].isValid(k, l, Game)) {

                                    if (!Game.board[i][j].move(k, l, Game)) {
                                        continue;
                                    } else {
                                        Game.saveMove(); //save AI Move
                                        return true;
                                    }


                                }
                            }
                        }
                    }
                }
            }
        }


        return false;//no possible move without check
    }

    public static boolean undoMove() {


        if (Game.pastMoves.size() == 1) {
            return false;
        }

        Game.pastMoves.remove(Game.pastMoves.size() - 1);
        Game.board = Game.pastMoves.get(Game.pastMoves.size() - 1);

        return true;
    }







    public static boolean saveGame(String name) {

        Game.name = name;

        //another game has same name
        if (info.games.contains(Game)) {
            return false;
        }

        info.games.add(Game);
        return true;
    }


    /**
     * Deletes a directory recursively
     */
    static public void deleteDirectory(File ff) {
        if (ff.exists() == true) {
            File[] files = ff.listFiles();

            int j = 0;
            while (j < files.length) {

                if (files[j].isDirectory() == true) {
                    deleteDirectory(files[j]);
                } else {
                    files[j].delete();
                }
                j++;
            }

        }

        ff.delete();
    }

    //-----------------------------------------------------------------------------------------------------------------------


    /**
     * Sees if an instruction is valid or not
     * <p>
     * Breaks the instruction into a split array of words. It checks the length of the words
     * and if the words are valid based on the amount of words there is
     * </p>
     *
     * @return true or false if the instruction is valid.
     */
    public static boolean validInstruction(int r, int c, int r1, int c1) {

        //Piece you are trying to move
        if (Game.board[r][c] == null) {                //nothing there
            return false;
        }

        if (!Game.board[r][c].team.equals(Game.currPlayer.team))//not on the same team
            return false;

        //Piece you are trying to go to
        if (Game.board[r1][c1] != null) {
            if (Game.board[r1][c1].team.equals(Game.currPlayer.team))//trying to kill your own player
                return false;
        }

        if (Game.board[r][c].isValid(r1, c1, Game) == false) {    //check unique is valid method for each piece
            //System.out.println("not valid move trying to go to" + r1 + c1);
            return false;
        }

        return true;
    }


    /**
     * Reads input.
     *
     */
    public static boolean move(int r, int c, int r1, int c1)  {

        if (!validInstruction(r,c,r1,c1)) {
            System.out.println("error, not valid instruction");
            return false;
        }

        //MOVEMENT====================================================================================
        piece temp = null;

        if (Game.board[r1][c1] != null) {                            //piece already there

            if (Game.board[r1][c1].team.equals("black")) {
                temp = Game.board[r1][c1];
                //Game.p2.pieces.remove(Game.board[r1][c1]);            //black piece lost, remove it from black player list
            } else {
                temp = Game.board[r1][c1];
                //Game.p1.pieces.remove(Game.board[r1][c1]);            //white piece lost, remove it from white player list
            }
        }
        Game.saveMove();

        //MOVEMEMENT

        if (!Game.board[r][c].move(r1, c1, Game)) {
            undoMove();
            return false;
        }



        switchTurns();
        return true;
    }

    /**
     * Sitches players turns using player object inside of board.
     */
    public static void switchTurns() {
        if (Game.p1.turn) {
            Game.p1.turn = false;
            Game.p2.turn = true;
            Game.currPlayer = Game.p2;
        } else {
            Game.p2.turn = false;
            Game.p1.turn = true;
            Game.currPlayer = Game.p1;
        }
    }


    public void setUpGame(){
        Game = new game("");
        Game.saveMove();
    }


    public app() {
    }


    /**
     * Checks for check and checkmate
     *
     * @return Return codes
     * <p>
     * -1 for no end case
     * 1 for black king check
     * 2 for black king checkmate
     * 3 for white king check
     * 4 for white kind checkmate
     * </p>
     */

    public int checkForEndCases() {
        if (Game.p1.k.check(Game)) {
            if (Game.p1.k.checkMate(Game)) {
                return 4;
            } else {
                return 3;
            }
        } else if (Game.p2.k.check(Game)) {

            if (Game.p2.k.checkMate(Game)) {
                return 2;

            } else {
                return 1;
            }
        }
        return -1;
    }


}