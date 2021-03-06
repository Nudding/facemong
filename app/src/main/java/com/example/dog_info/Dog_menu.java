package com.example.dog_info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dog_menu extends AppCompatActivity {

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_menu);

        TextView textview1 = (TextView) findViewById(R.id.tv_receivename2);
        TextView textview2 = (TextView) findViewById(R.id.tv_receivename5);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        readUser();
        mDatabase.child("users").child("1").child("userName").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                textview1.setText(value);
                textview2.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });

        Button button1 = (Button) findViewById(R.id.video_btn);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplication(), Dog_video.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.status_btn);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplication(), Dog_cursta.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplication(), Dog_status.class);
                startActivity(intent);
            }
        });

    }

    private void readUser(){
        mDatabase.child("users").child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                if(dataSnapshot.getValue(User.class) != null){
                    User post = dataSnapshot.getValue(User.class);
                    Log.w("FireBaseData", "getData" + post.toString());
                } else {
                    Toast.makeText(Dog_menu.this, "????????? ??????...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FireBaseData", "loadPost:onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dog_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_reg_main) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
            finish();
            return true;
        }else if(id==R.id.action_dog_video){
            Intent it = new Intent(this, Dog_video.class);
            startActivity(it);
            return true;
        }
        else if(id==R.id.action_dog_cursta){
            Intent it = new Intent(this, Dog_cursta.class);
            startActivity(it);
            return true;
        }
        else if(id==R.id.action_dog_status){
            Intent it = new Intent(this, Dog_status.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}