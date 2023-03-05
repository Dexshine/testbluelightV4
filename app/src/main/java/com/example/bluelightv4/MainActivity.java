package com.example.bluelightv4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    SeekBar mSeekBar = findViewById(R.id.seekbar);
//    TextView mTextView = findViewById(R.id.textView);
    private Button mStartBtn, mStopBtn;
    private Intent mServiceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartBtn = findViewById(R.id.startBtn);
        mStopBtn = findViewById(R.id.stopBtn);

        mServiceIntent = new Intent(this, BlueLightFilterService2.class);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            private static final int OVERLAY_PERMISSION_CODE =5463 ; // can be anything

            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(this)) {

                        // Open the permission page
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, OVERLAY_PERMISSION_CODE);
                        return;
                    }
                }

                // if not needed, go directly on the new Activity
                goToReactActivity();
            }

            @Override
            protected void onActivityResult(int requestCode, int resultCode,  Intent data) {

                // If the permission has been checked
                if (requestCode == OVERLAY_PERMISSION_CODE) {
                    if (Settings.canDrawOverlays(this)) {
                        goToReactActivity();
                    }
                }
            }

            private void goToReactActivity() {
                //Intent intent = new Intent(this, BlueLightFilterService2.class);
                startActivity(mServiceIntent);
            }
        });
        mStopBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                stopService(mServiceIntent);
            }
        });
// send seekbar status to textview
//        SeekBar.OnSeekBarChangeListener changeListener = new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mTextView.setText(mSeekBar.getProgress());
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        };
//        mSeekBar.setOnSeekBarChangeListener(changeListener);
    }
}