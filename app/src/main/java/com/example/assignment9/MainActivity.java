package com.example.assignment9;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Kuuntelija extends GestureDetector.SimpleOnGestureListener {
        int status = 0; //0 = valkoinen, 1 = musta
        final int JAKAJA = 94; //94 antaa rgb arvon maksimiksi 255 OnePlus 3T:llä

        @Override
        public boolean onDown(MotionEvent e){
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            if (status == 0){
                status = 1;
                background.setBackgroundColor(Color.BLACK);
            }
            else if (status == 1){
                status = 0;
                background.setBackgroundColor(Color.WHITE);
            }
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
            Log.d("OnFling", "Väri: r=" + Math.abs((int)velocityX / JAKAJA) + ", b=" + Math.abs((int)velocityY / JAKAJA));

            background.setBackgroundColor(Color.rgb(Math.abs((int)velocityX / JAKAJA ), 0, Math.abs((int)velocityY / JAKAJA )));
            return true;
        }
    }

    GestureDetector myGD;
    LinearLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        background = findViewById(R.id.activity_main);
        this.myGD = new GestureDetector(this, new Kuuntelija());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        myGD.onTouchEvent(event);
        return true;
    }
}
