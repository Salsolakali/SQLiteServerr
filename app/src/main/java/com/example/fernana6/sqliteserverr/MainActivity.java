package com.example.fernana6.sqliteserverr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.registry:
                Intent registry = new Intent(MainActivity.this, RegistryActivity.class);
                startActivity(registry);
                break;
            case R.id.showUser:
                Intent show = new Intent(MainActivity.this, ShowUserActivity.class);
                startActivity(show);
                break;
        }
    }
}
