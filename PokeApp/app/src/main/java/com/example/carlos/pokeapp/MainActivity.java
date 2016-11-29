package com.example.carlos.pokeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call();

    }


    public void call(){
        btn = (Button)findViewById(R.id.btnjogo);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id;
        id = view.getId();
        switch (id){
            case R.id.btnjogo:
                Intent act2 = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(act2);
                break;
        }
    }
}
