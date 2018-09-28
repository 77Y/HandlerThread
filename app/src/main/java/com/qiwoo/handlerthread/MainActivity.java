package com.qiwoo.handlerthread;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    HandlerThread handlerThread;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.send).setOnClickListener(this);
        findViewById(R.id.send_delay).setOnClickListener(this);
        findViewById(R.id.quit).setOnClickListener(this);
        findViewById(R.id.safe_quit).setOnClickListener(this);

        handlerThread = new HandlerThread("qiwoo");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.start:
                handlerThread.start();
                handler = new Handler(handlerThread.getLooper());
                break;
            case R.id.send:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "message", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.send_delay:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "delay message", Toast.LENGTH_SHORT).show();
                    }
                }, 5000);
                break;
            case R.id.quit:
                handlerThread.quit();
                break;
            case R.id.safe_quit:
                handlerThread.quitSafely();
                break;
        }
    }
}
