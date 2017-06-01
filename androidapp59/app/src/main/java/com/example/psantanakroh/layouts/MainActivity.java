package com.example.psantanakroh.layouts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.psantanakroh.layouts.backend.src.*;
import com.example.psantanakroh.layouts.backend.src.objects.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.*;
import android.support.v4.content.*;
import android.Manifest;
import 	android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;
public class MainActivity extends AppCompatActivity {

    /**
     * Backend instance that holds all games being played.
     * Static so all classes can see it
     * */
    public static app App = new app();
    public ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load in information
        try {

            readData();

        }catch(Exception e){

        }


        Button button0 = (Button) findViewById(R.id.NewGameButton);
        final Button dateSort = (Button) findViewById(R.id.dateSort);
        final Button nameSort = (Button) findViewById(R.id.nameSort);
        final Button deleteEverything = (Button) findViewById(R.id.LoadGameButton);


        button0.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        OpenNewGame();
                    }
                }
        );

        dateSort.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        dateSort();
                    }
                }
        );

        nameSort.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        nameSort();
                    }
                }
        );

        deleteEverything.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        deleteEverything();
                    }
                }
        );


        //List View Stuff
        listView = (ListView) findViewById(R.id.listView);


        ArrayAdapter<game> adapter = new ArrayAdapter<game>(this,
                android.R.layout.simple_list_item_1, App.info.games);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                //open game at position
                openSelectedGame(position);


            }
        });




    }

    public void readData() {
        System.out.println("reading data ");

            try {
                FileInputStream fileout= getApplicationContext().openFileInput("game");


                System.out.println("Total file size to read (in bytes) : "
                       + fileout.available());

                byte[] b = new byte[fileout.available()];

                fileout.read(b);

                ByteArrayInputStream bis = new ByteArrayInputStream(b);
                ObjectInput in = null;
                in = new ObjectInputStream(bis);
                Object o = in.readObject();

                App.info.games = (List<game>) o;

            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

    public void OpenNewGame(){
        Intent myintent = new Intent(MainActivity.this, NewGameActivity.class);
        MainActivity.this.startActivity(myintent);

    }




    public void openSelectedGame(int p){
        App.Game = app.info.games.get(p);

        if(App.Game.gameFinished) {
            Intent myintent = new Intent(MainActivity.this, loadGame.class);
            MainActivity.this.startActivity(myintent);
        }else {
            Intent myintent = new Intent(MainActivity.this, NewGameActivity.class);
            MainActivity.this.startActivity(myintent);
        }
    }



    public void dateSort(){
        Collections.sort(App.info.games,new Comparator<game>(){
            public int compare(game s1,game s2){
                return ((int) (s1.date.getTime() - s2.date.getTime()));
            }});
        ArrayAdapter<game> adapter = new ArrayAdapter<game>(this,
                android.R.layout.simple_list_item_1, App.info.games);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                //open game at position
                openSelectedGame(position);


            }
        });



    }

    public void nameSort(){
        Collections.sort(App.info.games,new Comparator<game>(){
            public int compare(game s1,game s2){
                return (s1.name.compareTo(s2.name));
            }});
        ArrayAdapter<game> adapter = new ArrayAdapter<game>(this,
                android.R.layout.simple_list_item_1, App.info.games);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                //open game at position
                openSelectedGame(position);


            }
        });
    }




    public void deleteEverything(){
        app.info.games.clear();
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



        ArrayAdapter<game> adapter = new ArrayAdapter<game>(this,
                android.R.layout.simple_list_item_1, App.info.games);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long id) {
                //open game at position
                openSelectedGame(position);


            }
        });
    }




}
