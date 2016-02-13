package com.dar.movil.onparking;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dar.movil.onparking.util.AppUtil;

public class TimeActivity extends AppCompatActivity {


    int min;
    int seg;
    boolean running;

    ProgressBar bar;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        min = AppUtil.MIN;
        seg = 0;

        bar = (ProgressBar) findViewById(R.id.progressBar);
        time = (TextView) findViewById(R.id.time);

        time.setText(getTimeFormat());

        bar.setMax(min*60);
        Timer timer =  new Timer();
        timer.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;

    }

    public String getTimeFormat() {
        String m = ""+min;
        String s = ""+seg;
        if(min <10)
            m = "0"+m;
        if(seg<10)
            s = "0"+s;

        return m+":"+s;
    }


    private  class Timer extends AsyncTask<Integer, Integer, Integer>{


        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                running = true;
                while (running) {
                    Thread.sleep(1000);

                    if (min == 0 && seg == 1) {
                        running = false;
                    }else {
                       if (seg == 0) {
                            min--;
                            seg = 59;
                        } else {
                            seg--;
                        }
                    }
                    publishProgress();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            bar.setProgress((bar.getProgress() + 1));
            time.setText(getTimeFormat());

        }
    }

}
