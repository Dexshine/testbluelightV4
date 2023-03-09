package com.example.bluelightv4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bluelightBtn;
        bluelightBtn = findViewById(R.id.startBtn);
        Context context = this;
        Intent intent = new Intent(this, BlueLightFilterService.class);
        bluelightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });






    }
}