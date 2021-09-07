package com.example.dog_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.reg_button);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplication(), Reg_name.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) findViewById(R.id.menu_button);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplication(), Dog_menu.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_reg_name) {
            Intent it = new Intent(this, Reg_name.class);
            startActivity(it);
            finish();
            return true;
        }else if(id==R.id.action_dog_menu){
            Intent it = new Intent(this, Dog_menu.class);
            startActivity(it);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}