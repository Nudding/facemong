package com.example.dog_info;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Dog_status extends AppCompatActivity {

    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_status);

        Button button1 = (Button) findViewById(R.id.button);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        readUser();
        mDatabase.child("users").child("1").child("userName").addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                //textview.setText(value);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplication(), Dog_menu.class);
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
                    Toast.makeText(Dog_status.this, "데이터 없음...", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menu_dog_status, menu);
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
        }else if(id==R.id.action_dog_cursta){
            Intent it = new Intent(this, Dog_cursta.class);
            startActivity(it);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}