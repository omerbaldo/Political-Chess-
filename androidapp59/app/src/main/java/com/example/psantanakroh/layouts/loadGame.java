package com.example.psantanakroh.layouts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import com.example.psantanakroh.layouts.backend.src.app;
import com.example.psantanakroh.layouts.backend.src.objects.*;

import java.util.ArrayList;
import java.util.List;

public class loadGame extends AppCompatActivity {

    Button prev;
    Button next;
    Button createNewGameAtThisPoint;
    public static ImageButton[][] buttons = new ImageButton[8][8];


    //loads ended game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);

        prev = (Button) findViewById(R.id.prevTurn);
        next = (Button) findViewById(R.id.nextTurn);

        createNewGameAtThisPoint = (Button) findViewById(R.id.newGame);

        createNewGameAtThisPoint.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {

                        /*
                        List<piece[][]> pastMoves =  new ArrayList<piece[][]>();
                        for(piece[][] board : app.Game.pastMoves){
                            pastMoves.add(board);
                        }

                        MainActivity.App.setUpGame();
                        int i = 0;
                        for( i = 0; i <= MainActivity.App.Game.iterator; i++){
                            MainActivity.App.switchTurns();
                            piece[][] board = pastMoves.get(i);
                            MainActivity.App.Game.board = board;
                            MainActivity.App.Game.saveMove();
                        }
                        */
                        piece[][] b = MainActivity.App.Game.pastMoves.get(MainActivity.App.Game.iterator);
                        MainActivity.App.setUpGame();
                        MainActivity.App.Game.board = b;
                        MainActivity.App.Game.saveMove();
                        Intent myintent = new Intent(loadGame.this, NewGameActivity.class);
                        loadGame.this.startActivity(myintent);


                    }
                }
        );


        prev.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        app.Game.prevMove();
                        drawPictures();
                    }
                }
        );

        next.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        app.Game.nextMove();
                        drawPictures();
                    }
                }
        );


        buttons[0][0] = (ImageButton) findViewById(R.id.zero_zero);
        buttons[0][0].setImageResource(R.drawable.br);

        buttons[0][1] = (ImageButton) findViewById(R.id.zero_one);
        buttons[0][1].setImageResource(R.drawable.bn);

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





    }










    public void drawPictures(){

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                if(app.Game.board[i][j]==null){
                    buttons[i][j].setImageDrawable(null);

                }else{
                    if(app.Game.board[i][j] instanceof king && app.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bk);
                    if(app.Game.board[i][j] instanceof king && app.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wk);

                    if(app.Game.board[i][j] instanceof queen && app.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bq);
                    if(app.Game.board[i][j] instanceof queen && app.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wq);


                    if(app.Game.board[i][j] instanceof pawn && app.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bp);
                    if(app.Game.board[i][j] instanceof pawn && app.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wp);


                    if(app.Game.board[i][j] instanceof bishop && app.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bb);
                    if(app.Game.board[i][j] instanceof bishop && app.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wb);


                    if(app.Game.board[i][j] instanceof castle && app.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.br);
                    if(app.Game.board[i][j] instanceof castle && app.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wr);


                    if(app.Game.board[i][j] instanceof knight && app.Game.board[i][j].team.equals("black"))
                        buttons[i][j].setImageResource(R.drawable.bn);
                    if(app.Game.board[i][j] instanceof knight && app.Game.board[i][j].team.equals("white"))
                        buttons[i][j].setImageResource(R.drawable.wn);
                }


            }
        }

    }








}
