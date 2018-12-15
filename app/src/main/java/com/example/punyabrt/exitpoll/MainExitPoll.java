package com.example.punyabrt.exitpoll;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.punyabrt.exitpoll.Database.Databasevote;
import com.example.punyabrt.exitpoll.model.Votemodel;

import static com.example.punyabrt.exitpoll.Database.Databasevote.TABLE_NAME;
import static com.example.punyabrt.exitpoll.Database.Databasevote.COL_ID;
import static com.example.punyabrt.exitpoll.Database.Databasevote.COL_SCORE;
import static com.example.punyabrt.exitpoll.Database.Databasevote.COL_NAME;
import static com.example.punyabrt.exitpoll.Database.Databasevote.COL_IMAGE;

import java.util.ArrayList;
import java.util.List;

public class MainExitPoll extends AppCompatActivity {

    private Databasevote mHelper;
    private SQLiteDatabase mDb;
    private List<Votemodel> mVoteItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_exit_poll);

        mHelper = new Databasevote(MainExitPoll.this);
        mDb = mHelper.getWritableDatabase();

        Button vote_one = findViewById(R.id.shoes1_button);
        Button vote_two = findViewById(R.id.shoes2_button);
        Button vote_three = findViewById(R.id.shoes3_button);
        Button vote_no = findViewById(R.id.shoes4_button);
        Button score_view = findViewById(R.id.view_button);

        loadPhoneData();

        vote_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Votemodel item = mVoteItemList.get(0);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "no");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );
                loadPhoneData();


            }
        });

        vote_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Votemodel item = mVoteItemList.get(1);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "1");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );
                loadPhoneData();

            }
        });

        vote_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Votemodel item = mVoteItemList.get(2);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "2");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );
                loadPhoneData();

            }
        });

        vote_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Votemodel item = mVoteItemList.get(3);
                String textscore = item.score.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "3");
                cv.put(COL_SCORE,newscore);
                cv.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );
                loadPhoneData();

            }
        });



        score_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainExitPoll.this, Viewvote.class);
                startActivity(intent);
            }
        });



        //todo


    }
    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mVoteItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String name = c.getString(c.getColumnIndex(COL_NAME));
            String score = c.getString(c.getColumnIndex(COL_SCORE));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            Votemodel item = new Votemodel(id, name, score,image);
            mVoteItemList.add(item);
        }
        c.close();
    }
}
