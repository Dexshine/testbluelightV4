package com.example.bluelightv4;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class BlueLightFilterService extends Service {

    private WindowManager mWindowManager;
    private View mFilterView;
    public BlueLightFilterService() {
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mFilterView = new View(this);
        mFilterView.setBackgroundColor(Color.argb(70,255,60,0));

//        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
////                WindowManager.LayoutParams.FLAG_FULLSCREEN,
////                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
//                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
//                PixelFormat.TRANSLUCENT );
//        mWindowManager.addView(mFilterView, params);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                PixelFormat.TRANSLUCENT
        );
        //View overlayView = LayoutInflater.from(this).inflate(R.layout.my_overlay_layout, null);
        return super.onStartCommand(intent, flags, startId);

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mFilterView != null){
            mWindowManager.removeView(mFilterView);
            mFilterView = null;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}