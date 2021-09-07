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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dog_cursta extends AppCompatActivity {

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_cursta);

        TextView textview = (TextView) findViewById(R.id.tv_receivename4);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        readUser();
        mDatabase.child("users").child("1").child("userName").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                textview.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });

        TextView tv_time = (TextView) findViewById(R.id.currentTime);
        tv_time.setText(getTime());

        Button button1 = (Button) findViewById(R.id.back_button);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplication(), Dog_menu.class);
                startActivity(intent);
            }
        });


    }

    private String getTime(){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String getTime = dateFormat.format(date);
        return getTime;
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
                    Toast.makeText(Dog_cursta.this, "데이터 없음...", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_dog_cursta, menu);
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
        }else if(id==R.id.action_dog_menu){
            Intent it = new Intent(this, Dog_menu.class);
            startActivity(it);
            finish();
            return true;
        }else if(id==R.id.action_dog_video){
            Intent it = new Intent(this, Dog_video.class);
            startActivity(it);
            finish();
            return true;
        }else if(id==R.id.action_dog_status){
            Intent it = new Intent(this, Dog_status.class);
            startActivity(it);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}