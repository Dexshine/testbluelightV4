package com.example.bluelightv4;

import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;

public class BlueLightFilterService extends Service {
    public BlueLightFilterService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();
        // Check if the app has overlay permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            // Request overlay permission
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            stopSelf();
        } else {
            //create a windowmanager instance
            WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            //create a new view
            View overlayView = new View(this);
            //set the background color of the view to the desire color
            overlayView.setBackgroundColor(Color.argb(100,255,60,0));
            //set the layout parameters of the view
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                            | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,//allow user to click through filter
                    PixelFormat.TRANSLUCENT
            );
            //add the view to the windowmanager
            windowManager.addView(overlayView, layoutParams);
        }

    }
    //    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        if(overlayView != null){
//            windowManager.removeView(overlayView);
//        }
//    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}