package com.example.psantanakroh.layouts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import com.example.psantanakroh.layouts.backend.src.*;
import com.example.psantanakroh.layouts.backend.src.objects.*;
import android.content.Intent;
import android.content.*;
import android.widget.AdapterView.OnItemClickListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import android.util.Log;

public class NewGameActivity extends AppCompatActivity {

    //Button Stuff
    boolean undoButtonReset = false;
    public Button resign;
    public Button draw;
    public Button undo;
    public Button pause;
    public CheckBox ai;
    public TextView message;
    public  TextView output;
    public  TextView turn;
    public static ImageButton[][] buttons = new ImageButton[8][8];
    public static ImageButton first_move;
    public static int firstR;
    public static int firstC;
    public static ImageButton second_move;
    public static int i;
    public static int j;
    public static app App; //start new game
    public int red;
    public int blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Open up the xml
        App = MainActivity.App;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_main);

        //Create a new game
        if(App.Game==null)
             App.setUpGame();


        //Make the Image buttons point to the new game
        setupButtons();
        drawPictures();
    }

    public void setupButtons() {
        resign = (Button) findViewById(R.id.quitbutton);

        draw = (Button) findViewById(R.id.drawbutton);
        undo = (Button) findViewById(R.id.undoButton);
        pause = (Button) findViewById(R.id.pausebutton);
        ai = (CheckBox) findViewById(R.id.Aicheckbox);
        message = (TextView) findViewById(R.id.messages);
        output = (TextView)findViewById(R.id.editText);
        turn = (TextView)findViewById(R.id.turn);

         ai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                        message.setText("checkbox!");
                        AI();

                    }
                }
                );

        resign.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        message.setText("other player wins!");
                        Resign();


                    }
                }
        );

        draw.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Draw();
                        message.setText("its a draw!");


                    }
                }
        );

        undo.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Undo();


                    }
                }
        );

        pause.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        Pause();


                    }
                }
        );


        buttons[0][0] = (ImageButton) findViewById(R.id.zero_zero);
        buttons[0][0].setImageResource(R.drawable.br);


        red = buttons[0][0].getDrawingCacheBackgroundColor();

        buttons[0][1] = (ImageButton) findViewById(R.id.zero_one);
        buttons[0][1].setImageResource(R.drawable.bn);
        blue = buttons[0][1].getDrawingCacheBackgroundColor();


        buttons[0][2] = (ImageButton) findViewById(R.id.zero_two);
        buttons[0][2].setImageResource(R.drawable.bb);

        buttons[0][3] = (ImageButton) findViewById(R.id.zero_three);
        buttons[0][3].setImageResource(R.drawable.bq);

        buttons[0][4] = (ImageButton) findViewById(R.id.zero_four);
        buttons[0][4].setImageResource(R.drawable.bk);

        buttons[0][5] = (ImageButton) findViewById(R.id.zero_five);
        buttons[0][5].setImageResource(R.drawable.bb);

        buttons[0][6] = (ImageButton) findViewById(R.id.zero_six);
        buttons[0][6].setImageResource(R.drawable.bn);

        buttons[0][7] = (ImageButton) findViewById(R.id.zero_seven);
        buttons[0][7].setImageResource(R.drawable.br);

        buttons[1][0] = (ImageButton) findViewById(R.id.one_zero);
        buttons[1][0].setImageResource(R.drawable.bp);

        buttons[1][1] = (ImageButton) findViewById(R.id.one_one);
        buttons[1][1].setImageResource(R.drawable.bp);

        buttons[1][2] = (ImageButton) findViewById(R.id.one_two);
        buttons[1][2].setImageResource(R.drawable.bp);

        buttons[1][3] = (ImageButton) findViewById(R.id.one_three);
        buttons[1][3].setImageResource(R.drawable.bp);

        buttons[1][4] = (ImageButton) findViewById(R.id.one_four);
        buttons[1][4].setImageResource(R.drawable.bp);

        buttons[1][5] = (ImageButton) findViewById(R.id.one_five);
        buttons[1][5].setImageResource(R.drawable.bp);

        buttons[1][6] = (ImageButton) findViewById(R.id.one_six);
        buttons[1][6].setImageResource(R.drawable.bp);

        buttons[1][7] = (ImageButton) findViewById(R.id.one_seven);
        buttons[1][7].setImageResource(R.drawable.bp);

        buttons[2][0] = (ImageButton) findViewById(R.id.two_zero);


        buttons[2][1] = (ImageButton) findViewById(R.id.two_one);
        buttons[2][2] = (ImageButton) findViewById(R.id.two_two);
        buttons[2][3] = (ImageButton) findViewById(R.id.two_three);
        buttons[2][4] = (ImageButton) findViewById(R.id.two_four);
        buttons[2][5] = (ImageButton) findViewById(R.id.two_five);
        buttons[2][6] = (ImageButton) findViewById(R.id.two_six);
        buttons[2][7] = (ImageButton) findViewById(R.id.two_seven);
        buttons[3][0] = (ImageButton) findViewById(R.id.three_zero);
        buttons[3][1] = (ImageButton) findViewById(R.id.three_one);
        buttons[3][2] = (ImageButton) findViewById(R.id.three_two);
        buttons[3][3] = (ImageButton) findViewById(R.id.three_three);
        buttons[3][4] = (ImageButton) findViewById(R.id.three_four);
        buttons[3][5] = (ImageButton) findViewById(R.id.three_five);
        buttons[3][6] = (ImageButton) findViewById(R.id.three_six);
        buttons[3][7] = (ImageButton) findViewById(R.id.three_seven);
        buttons[4][0] = (ImageButton) findViewById(R.id.four_zero);
        buttons[4][1] = (ImageButton) findViewById(R.id.four_one);
        buttons[4][2] = (ImageButton) findViewById(R.id.four_two);
        buttons[4][3] = (ImageButton) findViewById(R.id.four_three);
        buttons[4][4] = (ImageButton) findViewById(R.id.four_four);
        buttons[4][5] = (ImageButton) findViewById(R.id.four_five);
        buttons[4][6] = (ImageButton) findViewById(R.id.four_six);
        buttons[4][7] = (ImageButton) findViewById(R.id.four_seven);
        buttons[5][0] = (ImageButton) findViewById(R.id.five_zero);
        buttons[5][1] = (ImageButton) findViewById(R.id.five_one);
        buttons[5][2] = (ImageButton) findViewById(R.id.five_two);
        buttons[5][3] = (ImageButton) findViewById(R.id.five_three);
        buttons[5][4] = (ImageButton) findViewById(R.id.five_four);
        buttons[5][5] = (ImageButton) findViewById(R.id.five_five);
        buttons[5][6] = (ImageButton) findViewById(R.id.five_six);
        buttons[5][7] = (ImageButton) findViewById(R.id.five_seven);



        buttons[6][0] = (ImageButton) findViewById(R.id.six_zero);
        buttons[6][0].setImageResource(R.drawable.wp);



        buttons[6][1] = (ImageButton) findViewById(R.id.six_one);
        buttons[6][1].setImageResource(R.drawable.wp);

        buttons[6][2] = (ImageButton) findViewById(R.id.six_two);
        buttons[6][2].setImageResource(R.drawable.wp);

        buttons[6][3] = (ImageButton) findViewById(R.id.six_three);
        buttons[6][3].setImageResource(R.drawable.wp);

        buttons[6][4] = (ImageButton) findViewById(R.id.six_four);
        buttons[6][4].setImageResource(R.drawable.wp);

        buttons[6][5] = (ImageButton) findViewById(R.id.six_five);
        buttons[6][5].setImageResource(R.drawable.wp);

        buttons[6][6] = (ImageButton) findViewById(R.id.six_six);
        buttons[6][6].setImageResource(R.drawable.wp);

        buttons[6][7] = (ImageButton) findViewById(R.id.six_seven);
        buttons[6][7].setImageResource(R.drawable.wp);

        buttons[7][0] = (ImageButton) findViewById(R.id.seven_zero);
        buttons[7][0].setImageResource(R.drawable.wr);

        buttons[7][1] = (ImageButton) findViewById(R.id.seven_one);
        buttons[7][1].setImageResource(R.drawable.wn);

        buttons[7][2] = (ImageButton) findViewById(R.id.seven_two);
        buttons[7][2].setImageResource(R.drawable.wb);

        buttons[7][3] = (ImageButton) findViewById(R.id.seven_three);
        buttons[7][3].setImageResource(R.drawable.wq);

        buttons[7][4] = (ImageButton) findViewById(R.id.seven_four);
        buttons[7][4].setImageResource(R.drawable.wk);

        buttons[7][5] = (ImageButton) findViewById(R.id.seven_five);
        buttons[7][5].setImageResource(R.drawable.wb);

        buttons[7][6] = (ImageButton) findViewById(R.id.seven_six);
        buttons[7][6].setImageResource(R.drawable.wn);


        buttons[7][7] = (ImageButton) findViewById(R.id.seven_seven);
        buttons[7][7].setImageResource(R.drawable.wr);


        buttons[0][0].setOnClickListener (
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,0);
                    }
                }
        );

        buttons[0][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,1);
                    }
                }
        );
        buttons[0][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,2);
                    }
                }
        );
        buttons[0][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,3);
                    }
                }
        );
        buttons[0][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,4);
                    }
                }
        );
        buttons[0][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,5);
                    }
                }
        );
        buttons[0][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,6);
                    }
                }
        );
        buttons[0][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(0,7);
                    }
                }
        );
        buttons[1][0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,0);
                    }
                }
        );
        buttons[1][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,1);
                    }
                }
        );
        buttons[1][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,2);
                    }
                }
        );
        buttons[1][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,3);
                    }
                }
        );
        buttons[1][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,4);
                    }
                }
        );
        buttons[1][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,5);
                    }
                }
        );
        buttons[1][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,6);
                    }
                }
        );
        buttons[1][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(1,7);
                    }
                }
        );
        buttons[2][0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,0);
                    }
                }
        );
        buttons[2][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,1);
                    }
                }
        );
        buttons[2][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,2);
                    }
                }
        );
        buttons[2][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,3);
                    }
                }
        );
        buttons[2][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,4);
                    }
                }
        );
        buttons[2][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,5);
                    }
                }
        );
        buttons[2][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,6);
                    }
                }
        );
        buttons[2][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(2,7);
                    }
                }
        );
        buttons[3][0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,0);
                    }
                }
        );
        buttons[3][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,1);
                    }
                }
        );
        buttons[3][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,2);
                    }
                }
        );
        buttons[3][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,3);
                    }
                }
        );
        buttons[3][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,4);
                    }
                }
        );
        buttons[3][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,5);
                    }
                }
        );
        buttons[3][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,6);
                    }
                }
        );
        buttons[3][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(3,7);
                    }
                }
        );
        buttons[4][0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,0);
                    }
                }
        );
        buttons[4][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,1);
                    }
                }
        );
        buttons[4][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,2);
                    }
                }
        );
        buttons[4][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,3);
                    }
                }
        );
        buttons[4][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,4);
                    }
                }
        );
        buttons[4][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,5);
                    }
                }
        );
        buttons[4][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,6);
                    }
                }
        );
        buttons[4][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(4,7);
                    }
                }
        );
        buttons[5][0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,0);
                    }
                }
        );
        buttons[5][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,1);
                    }
                }
        );
        buttons[5][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,2);
                    }
                }
        );
        buttons[5][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,3);
                    }
                }
        );
        buttons[5][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,4);
                    }
                }
        );
        buttons[5][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,5);
                    }
                }
        );
        buttons[5][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,6);
                    }
                }
        );
        buttons[5][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(5,7);
                    }
                }
        );
        buttons[6][0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,0);
                    }
                }
        );
        buttons[6][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,1);
                    }
                }
        );
        buttons[6][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,2);
                    }
                }
        );
        buttons[6][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,3);
                    }
                }
        );
        buttons[6][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,4);
                    }
                }
        );
        buttons[6][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,5);
                    }
                }
        );
        buttons[6][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,6);
                    }
                }
        );
        buttons[6][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(6,7);
                    }
                }
        );
        buttons[7][0].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(7,0);
                    }
                }
        );
        buttons[7][1].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(7,1);
                    }
                }
        );
        buttons[7][2].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(7,2);
                    }
                }
        );
        buttons[7][3].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(7,3);
                    }
                }
        );
        buttons[7][4].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(7,4);
                    }
                }
        );
        buttons[7][5].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(7,5);
                    }
                }
        );
        buttons[7][6].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        chessMove(7,6);
                    }
                }
        );
        buttons[7][7].setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v)  {
                        chessMove(7,7);
                    }
                }
        );






    }

    public void chessMove(int r, int c) {

        if (first_move==null){ //First Move
            first_move= buttons[r][c];
            firstR = r;
            firstC = c;
            buttons[r][c].setBackgroundColor(Color.DKGRAY);
            return;
        }else{//Second Move

            //System.out.println("second move row " + r + " col " + c) ;
            //System.out.println("first move row " + firstR + " col " + firstC);
            second_move= buttons[r][c];

            if(App.Game.board[firstR][firstC] instanceof pawn){ //PAWN MOVING

                if(App.Game.board[firstR][firstC].team.equals("white")){

                    if(firstR==3 && (c == firstC+1 || c==firstC-1)){ //enpassiant or attack
                        if(App.Game.board[r+1][c] instanceof pawn){
                                if(App.Game.board[r][c]==null) {
                                    if (App.Game.board[r + 1][c].team.equals("black")) {
                                        App.Game.board[r + 1][c] = null;
                                        App.Game.board[r][c] = App.Game.board[firstR][firstC];
                                        App.Game.board[firstR][firstC] = null;
                                        drawPictures();
                                        App.switchTurns();
                                        isPromotion(r, c);
                                    }
                                }
                                else{
                                    if (App.Game.board[r][c].team.equals("black")) {
                                        App.Game.board[r][c] = App.Game.board[firstR][firstC];
                                        App.Game.board[firstR][firstC] = null;
                                        drawPictures();
                                        isPromotion(r, c);
                                    }
                                }
                        }else{                                      //regular attack
                            if (App.move(firstR, firstC, r, c)) {
                                message.setText("Successful Move");

                                isPromotion(r, c);

                            } else {
                                message.setText("invalid move");
                            }
                        }
                    }else {
                        if (App.move(firstR, firstC, r, c)) {
                            message.setText("Successful Move");
                            isPromotion(r, c);
                        } else {
                            message.setText("invalid move");
                        }
                    }

                }else if(App.Game.board[firstR][firstC].team.equals("black")){


                    if(firstR==4 && (c == firstC+1 || c==firstC-1)){
                        if(App.Game.board[r-1][c] instanceof pawn){
                            if(App.Game.board[r][c]==null) {
                                if (App.Game.board[r - 1][c].team.equals("white")) {
                                        App.Game.board[r - 1][c] = null;
                                        App.Game.board[r][c] = App.Game.board[firstR][firstC];
                                        App.Game.board[firstR][firstC] = null;
                                        drawPictures();
                                        App.switchTurns();
                                        isPromotion(r, c);
                                    }
                                }
                            else{
                                if (App.Game.board[r][c].team.equals("white")) {
                                        App.Game.board[r][c] = App.Game.board[firstR][firstC];
                                        App.Game.board[firstR][firstC] = null;
                                        drawPictures();
                                        App.switchTurns();
                                       isPromotion(r, c);
                                    }
                                }
                            }else{
                                if(App.move(firstR,firstC,r,c)){
                                    message.setText("Successful Move");
                                    isPromotion(r, c);
                                }else{
                                    message.setText("invalid move");
                                }
                            }
                    }else {
                        if(App.move(firstR,firstC,r,c)){
                            message.setText("Successful Move");
                            isPromotion(r, c);
                        }else{
                            message.setText("invalid move");
                        }
                }
                }

            } else if(App.Game.board[firstR][firstC] instanceof king){//KING MOVING
                //System.out.println("king!");

                king k =(king) App.Game.board[firstR][firstC];

                if(k.firstmove && (c ==6 ||c==2)){
                    if(App.move(firstR,firstC,r,c)){
                        if(c == 6){
                            buttons[r][5].setImageDrawable(buttons[r][7].getDrawable());
                            buttons[r][7].setImageDrawable(null);
                        }
                        if(c == 2){
                            buttons[r][3].setImageDrawable(buttons[r][0].getDrawable());
                            buttons[r][0].setImageDrawable(null);
                        }
                    }
                }else {
                    if(App.move(firstR,firstC,r,c)){
                        message.setText("Successful Move");
                    }else{
                        message.setText("King cannot move there ");
                    }
                }

            } else if(App.move(firstR,firstC,r,c)){       //OTHER PIECES
                message.setText("Successful Move");
            }else{
                message.setText("Invalid Move");
            }

            //OE , EO, OO, EE
            //reset the background color
            if((firstR%2==0)&&(firstC%2==0)) { //EE
                // first_move.setBackgroundColor(Color.RED);
                first_move.setBackgroundColor(Color.rgb(204, 0, 0));
            }else if((firstR%2!=0)&&(firstC%2==0)) { //OE
                //  first_move.setBackgroundColor(Color.BLUE);
                first_move.setBackgroundColor(Color.rgb(51, 181, 229));
            }else if((firstR%2!=0)&&(firstC%2!=0)){//OO
                first_move.setBackgroundColor(Color.rgb(204, 0, 0));
            }else{ //EO
                first_move.setBackgroundColor(Color.rgb(51, 181, 229));
            }
            drawPictures();

            // moves are reset
            first_move = null;
            second_move = null;


        }

        undoButtonReset = true;
        /*
        * -1 for no end case
        * 1 for black king check
        * 2 for black king checkmate
        * 3 for white king check
        * 4 for white kind checkmate
        */
        switch(App.checkForEndCases()){
            case 2:
                message.setText("Black King Checkmate");
                App.Game.gameFinished = true;
                App.Game.result = "White Player Win";

                saveGame();
                break;
            case 4:
                message.setText("White King CheckMate");
                App.Game.result = "Black Player Win";
                App.Game.gameFinished = true;
                saveGame();
                break;
            case 1:
                message.setText("Black King Check");
                break;

            case 3:
                message.setText("White King Check");
                break;
        }

        if(App.Game.p2.turn){
            turn.setText("Black turn");
        }else{
            turn.setText("White turn");
        }




    }


    public void AI(){
        ai = (CheckBox) findViewById(R.id.Aicheckbox);
        if(ai.isChecked()){
            message.setText("ai was was used");
            App.AI();
            drawPictures();
            App.switchTurns();
            if(App.Game.p2.turn){
                turn.setText("Black turn");
            }else{
                turn.setText("White turn");
            }
            ai.setChecked(false);
        }
    }


    public void isPromotion(int r, int c){
        if(App.Game.board[r][c] instanceof pawn){
            pawn p = (pawn) App.Game.board[r][c];
            if(p.team.equals("black") && r == 7){
                promotion("black",r,c);
            }else if(p.team.equals("white") && r==0){
                promotion("white",r,c);
            }
        }
    }

     public void returnHome(){
            App.Game=null;

            Intent returnhome = new Intent(this, MainActivity.class);

            this.startActivity(returnhome);

        }

    public void saveGame(){
        App.Game.gameFinished = true;
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Save Game? ");

        final EditText et = new EditText(this);

        alertDialogBuilder.setView(et);

        // set dialog message
        alertDialogBuilder
                .setMessage("Enter name to save and click confirm!")
                .setCancelable(false)
                .setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        // NewGameActivity.this.finish();

                        //Do stuff to make sure you check if game name already exsists

                        String name = et.getText().toString();

                        if(!App.saveGame(name)){
                            //game name already exsists

                        }

                        //Store information in
                        try{
                            writeData();

                        }catch (Exception e){
                            e.printStackTrace();
                        }


                        returnHome();
                    }
                })
                .setNegativeButton("Cancel to exit, but not save game",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        // dialog.cancel();
                        returnHome();

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    public void writeData() {
        System.out.println("Write data");

        File f = new File("game");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (Exception e) {

            }
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;

        try
        {
            out = new ObjectOutputStream(bos);
            out.writeObject(App.info.games);
            out.flush();
            byte[] yourBytes = bos.toByteArray();

            System.out.println("Total file size to write (in bytes) : "
                    + yourBytes.length);


            FileOutputStream fileout= getApplicationContext().openFileOutput("game", MODE_PRIVATE);
            fileout.write(yourBytes);
            fileout.close();
            System.out.println("SUCCESS WRITING to file " + f.getAbsolutePath());

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }








    }




    public void Resign(){
        //other player wins
        //returns to main page after 5 seconds?

        if(App.Game.p2.turn) {
            App.Game.result += "Black player ";
        }else {
            App.Game.result += "White player ";
        }

        App.Game.result += "Resign";
        saveGame();
    }






    public void Draw(){
        //player set draw to true
        //shows other player that oppent offered draw
        //if both players choose draw back to back(both players draw == true
        //then game over its a tie. return to main menu after 5 seconds?

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // set title
        alertDialogBuilder.setTitle("Accept the draw? ");

        final EditText et = new EditText(this);

        // set dialog message
        alertDialogBuilder
                .setMessage("Click yes to end the game")
                .setCancelable(false)
                .setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        // NewGameActivity.this.finish();

                        App.Game.result = "Draw";

                        saveGame();
                    }
                })
                .setNegativeButton("Click to continue playing chess ",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        // dialog.cancel();

                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void Undo(){

        if(undoButtonReset == false){
            return;
        }

        undoButtonReset = false;

        App.undoMove();
        drawPictures();

        //System.out.println("Board is now ");
        App.switchTurns();

        if(App.Game.p2.turn){
            turn.setText("Black turn");
        }else{
            turn.setText("White turn");
        }



        //undoes very last move
        //makes it last players move again
        //can not be hit consecutivly
    }

    public void Pause(){
        //saves game where it is
        //can be returned to exact spot later
        //returns to home screen instantly

        saveGame();

        /*
        Intent returnhome = new Intent(this, MainActivity.class);
        this.startActivity(returnhome);
        */

        /*

        System.out.println("Moves ");

        for(int i = 0; i < App.Game.pastMoves.size(); i++){
            System.out.println("Move number " + i);
            App.Game.board = App.Game.pastMoves.get(i);
            App.Game.print();
            System.out.println("");
        }
        */

    }



    //Draw stuff on screen
    public void drawPictures(){

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                if(App.Game.board[i][j]==null){
                    buttons[i][j].setImageDrawable(null);

                }else{
                    if(App.Game.board[i][j] instanceof king && App.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bk);
                    if(App.Game.board[i][j] instanceof king && App.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wk);

                    if(App.Game.board[i][j] instanceof queen && App.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bq);
                    if(App.Game.board[i][j] instanceof queen && App.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wq);


                    if(App.Game.board[i][j] instanceof pawn && App.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bp);
                    if(App.Game.board[i][j] instanceof pawn && App.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wp);


                    if(App.Game.board[i][j] instanceof bishop && App.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bb);
                    if(App.Game.board[i][j] instanceof bishop && App.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wb);


                    if(App.Game.board[i][j] instanceof castle && App.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.br);
                    if(App.Game.board[i][j] instanceof castle && App.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wr);


                    if(App.Game.board[i][j] instanceof knight && App.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bn);
                    if(App.Game.board[i][j] instanceof knight && App.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wn);
                }


            }
        }

    }


    public void promotion(final String team, final int r, final int c){

       final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);

        alert.setTitle("Promotion, Choose Piece To Switch To");

        final ListView lv = new ListView(this);

        App.Game.board[firstR][firstC]=null;
        drawPictures();


        ArrayList<String> pieceTypes = new  ArrayList<String>();
        pieceTypes.add("queen");
        pieceTypes.add("bishop");
        pieceTypes.add("castle");
        pieceTypes.add("knight");

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, pieceTypes);

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,long id) {
                //System.out.println("position" + position);

                                switch (position){
                                    case 0:
                                        if(team.equals("white"))
                                            App.Game.board[r][c] = new queen("wQ","white",r,c);
                                        else
                                            App.Game.board[r][c] = new queen("bQ","black",r,c);
                                        break;
                                    case 1:
                                        if(team.equals("white"))
                                            App.Game.board[r][c] = new bishop("wB","white",r,c);
                                        else
                                            App.Game.board[r][c] = new bishop("bB","black",r,c);
                                        break;
                                    case 2:
                                        if(team.equals("white"))
                                            App.Game.board[r][c] = new castle("wR","white",r,c);
                                        else
                                            App.Game.board[r][c] = new castle("bR","black",r,c);
                                        break;
                                    case 3:
                                        if(team.equals("white"))
                                            App.Game.board[r][c] = new knight("wK","white",r,c);
                                        else
                                            App.Game.board[r][c] = new knight("bK","black",r,c);
                                        break;

                                    default:
                                        if(team.equals("white"))
                                            App.Game.board[r][c] = new queen("wQ","white",r,c);
                                        else
                                            App.Game.board[r][c] = new queen("bQ","black",r,c);
                                        break;
                                }

                drawPictures();
            }
        });

        alert
                .setMessage("Click yes to confirm choice")
                .setCancelable(false)
                .setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                    }
                });


        alert.setView(lv);

        AlertDialog alertDialog = alert.create();

        alertDialog.show();
    }


}