package com.example.fernana6.sqliteserverr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.registry:
                Intent registry = new Intent(IndexActivity.this, RegistryActivity.class);
                startActivity(registry);
                break;
            case R.id.showUser:
                Intent show = new Intent(IndexActivity.this, ShowUserActivity.class);
                startActivity(show);
                break;
        }
    }
}
