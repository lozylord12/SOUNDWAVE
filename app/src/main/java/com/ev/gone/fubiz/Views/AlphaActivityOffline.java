package com.ev.gone.fubiz.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ev.gone.fubiz.R;
import android.animation.ObjectAnimator;
import android.app.Service;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlphaActivityOffline extends AppCompatActivity {

    Button no_wifi;
    Button btn_backtohome;
    Button setting;
    Button time_setting;
    Button list_songs_downloaded;

    Button off_line;
    Button alpha_ic;
    Button volume_ocean;
    boolean isPlay = false;

    boolean isOcean = false;
    boolean isOcean_nd = true;
    boolean isOcean_rd = true;

    Button budhist_ic;
    boolean isPiano = false;
    boolean isPiano_nd = true;
    boolean isPiano_rd = true;

    MediaPlayer mPlayers;

    Boolean wifiConnected;
    Boolean mobileConnected;

    //extra classes
    private static class TimerStatus {
        private static final int STARTED = 188;
        private static final int STOPPED = 187;
    }

    @BindView(R.id.progressBarCircle)
    ProgressBar progressBarCircle;

    @BindView(R.id.textViewTime)
    TextView textViewTime;

    //Objects and variables
    int status = TimerStatus.STOPPED;
    ObjectAnimator smoothAnimation;

    @BindView(R.id.countdown_btn)
    Button countdown_btn;

    private long timeCountInMilliSeconds;
    private CountDownTimer countDownTimer;
    private Toast mToastToShow;
    private TextView tvTest;
    MediaPlayer mPlayerOcean = new MediaPlayer();
    MediaPlayer mPlayerAlpha = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_offline);

//        no_wifi = (Button) findViewById(R.id.no_wifi);
//        no_wifi.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Intent redirect = new Intent(AlphaActivityOffline.this, AlphaActivityOnline.class);
////                timeCountInMilliSeconds
////                stopCountDownTimer();
//                startActivityForResult(redirect,2);
//
//            }
//        });

        checkConnection();

        mPlayers = new MediaPlayer();
        mPlayerOcean = MediaPlayer.create(this, R.raw.oceansounds);
        mPlayerAlpha = MediaPlayer.create(this, R.raw.alpha10dot0hz);

        tvTest = findViewById(R.id.tvTest_alpha);
        Intent intent = getIntent();
        tvTest.setText(intent.getStringExtra("push_song"));
        final String str = intent.getStringExtra("push_url");
        if (str == null){

            mPlayers = MediaPlayer.create(this, R.raw.clairdelune);

        }else{
            try{
                mPlayers.setDataSource(str);
                mPlayers.prepare();

            }catch (IOException e){
                Log.v("lost data", e.getMessage());
            }
        }

        alpha_ic = (Button) findViewById(R.id.alpha_ic);
        btn_backtohome = (Button) findViewById(R.id.backtohome);
        btn_backtohome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                mPlayers.reset();
                mPlayers.pause();
                mPlayerAlpha.reset();
                mPlayerAlpha.pause();
                mPlayerOcean.reset();
                mPlayerOcean.pause();
                Intent redirect = new Intent(AlphaActivityOffline.this, MenuCircleActivity.class);
                startActivity(redirect);
            }

        });

        setting = (Button) findViewById(R.id.setting);
        setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mPlayers.reset();
                mPlayers.pause();
                mPlayerAlpha.reset();
                mPlayerAlpha.pause();
                mPlayerOcean.reset();
                mPlayerOcean.pause();
                Intent redirect = new Intent(AlphaActivityOffline.this, AlphaSettingActivityOffline.class);
                startActivity(redirect);
            }
        });

        time_setting = (Button) findViewById(R.id.time_setting);
        time_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent redirect = new Intent(AlphaActivityOffline.this, SettingCountdown.class);
                startActivityForResult(redirect,2);
            }
        });


        list_songs_downloaded = (Button) findViewById(R.id.list_songs_downloaded);
        list_songs_downloaded.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent redirect = new Intent(AlphaActivityOffline.this, ListSongOfflineDownloaded.class);
                startActivityForResult(redirect,2);
            }
        });

        no_wifi = (Button) findViewById(R.id.no_wifi);
        no_wifi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent redirect = new Intent(AlphaActivityOffline.this, AlphaActivityOnline.class);
                startActivityForResult(redirect,2);
                finish();
                Toast.makeText(AlphaActivityOffline.this, "Switched to online mode!",
                        Toast.LENGTH_LONG).show();
            }
        });


        alpha_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPlay) {
                    v.setBackgroundResource(R.mipmap.alphaic);
                    mPlayerAlpha.pause();
                } else {
                    v.setBackgroundResource(R.mipmap.alphaic_active);
                    mPlayerAlpha.start();
                    mPlayerAlpha.setVolume((float) 0.3, (float) 0.3);
                    mPlayerAlpha.setLooping(true);
                }
                isPlay = !isPlay; // reverse
            }
        });

        // piano button
        budhist_ic = (Button) findViewById(R.id.budhist_ic);
        budhist_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                System.out.println(str);

                if (isPiano && isPiano_nd && isPiano_rd) {

                    v.setBackgroundResource(R.mipmap.volume_piano_max);

                } else if (!isPiano && isPiano_nd && isPiano_rd) {

                    isPiano_nd = !isPiano_nd;
                    v.setBackgroundResource(R.mipmap.volume_piano_low);
                    mPlayers.start();
                    mPlayers.setVolume((float) 0.4, (float) 0.4);
                    mPlayers.setLooping(true);

                } else if (isPiano && !isPiano_nd && isPiano_rd) {

                    v.setBackgroundResource(R.mipmap.volume_piano_middle);
                    isPiano = !isPiano;
                    isPiano_rd = !isPiano_rd;
                    mPlayers.start();
                    mPlayers.setVolume((float) 0.7, (float) 0.7);
                    mPlayers.setLooping(true);

                } else if (isPiano && !isPiano_nd && !isPiano_rd) {

                    v.setBackgroundResource(R.mipmap.volume_piano_max);
                    isPiano_nd = !isPiano_nd;
                    mPlayers.start();
                    mPlayers.setVolume((float) 1.0, (float) 1.0);

                } else if (!isPiano && isPiano_nd && !isPiano_rd) {
                    v.setBackgroundResource(R.mipmap.budhist);
                    isPiano_rd = !isPiano_rd;
                    mPlayers.pause();

                } else if (!isPiano && isPiano_nd && !isPiano_rd) {

                    v.setBackgroundResource(R.mipmap.volume_piano_low);
                    mPlayers.start();
                    mPlayers.setVolume((float) 0.4, (float) 0.4);
                    mPlayers.setLooping(true);

                }
                isPiano = !isPiano;
            }
        });


        volume_ocean = (Button) findViewById(R.id.ocean_volume);
        volume_ocean.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (isOcean && isOcean_nd && isOcean_rd) {
                    v.setBackgroundResource(R.mipmap.volume_ocean_max);
                    mPlayerOcean.pause();

                } else if (!isOcean && isOcean_nd && isOcean_rd) {
                    v.setBackgroundResource(R.mipmap.volume_ocean_low);
                    isOcean_nd = !isOcean_nd;
                    mPlayerOcean.start();
                    mPlayerOcean.setVolume((float) 0.4, (float) 0.4);

                } else if (isOcean && !isOcean_nd && isOcean_rd) {
                    v.setBackgroundResource(R.mipmap.volume_ocean_middle);
                    isOcean = !isOcean;
                    isOcean_rd = !isOcean_rd;
                    mPlayerOcean.setVolume((float) 0.7, (float) 0.7);

                } else if (isOcean && !isOcean_nd && !isOcean_rd) {
                    v.setBackgroundResource(R.mipmap.volume_ocean_max);
                    isOcean_nd = !isOcean_nd;
                    mPlayerOcean.setVolume((float) 1.0, (float) 1.0);

                } else if (!isOcean && isOcean_nd && !isOcean_rd) {
                    v.setBackgroundResource(R.mipmap.volume_ocean_mute);
                    isOcean_rd = !isOcean_rd;
                    mPlayerOcean.pause();

                } else if (isOcean && isOcean_nd && isOcean_rd) {
                    v.setBackgroundResource(R.mipmap.volume_ocean_low);
                    mPlayerOcean.start();
                    mPlayerOcean.setVolume((float) 0.4, (float) 0.4);
                }
                isOcean = !isOcean;
            }
        });

        ButterKnife.bind(this);

        //call to initialize the timer values
        setTimerValues();
        textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
        countdown_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });

    }

    private void startStop() {

        if (status == TimerStatus.STOPPED) {

            setProgressBarValues();
            status = TimerStatus.STARTED;
            startCountDownTimer();
            countdown_btn.setBackgroundResource(R.drawable.buddhist_countdown);

        } else {
            status = TimerStatus.STOPPED;
            stopCountDownTimer();
            countdown_btn.setBackgroundResource(R.mipmap.countdownic);
        }
    }

    private void startCountDownTimer() {

        smoothAnimation = ObjectAnimator.ofInt(progressBarCircle, "progress", progressBarCircle.getProgress(), progressBarCircle.getMax());
        smoothAnimation.setDuration(500);
        smoothAnimation.setInterpolator(new AccelerateInterpolator());

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                textViewTime.setText(hmsTimeFormatter(millisUntilFinished));
                progressBarCircle.setProgress((int) (timeCountInMilliSeconds / 10 - millisUntilFinished / 10));
            }

            @Override
            public void onFinish() {

                textViewTime.setText("Done");

                mPlayers.reset();
                mPlayers.pause();
                mPlayerAlpha.reset();
                mPlayerAlpha.pause();
                mPlayerOcean.reset();
                mPlayerOcean.pause();

                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();

                // call to initialize the progress bar values
                setProgressBarValues();
                // changing the timer status to stopped
                status = TimerStatus.STOPPED;

                Intent intent = getIntent();
                setResult(RESULT_OK,intent);
                finish();

                smoothAnimation.end();
                countdown_btn.setBackgroundResource(R.mipmap.countdownic);

            }
        }.start();
        smoothAnimation.start();
        countDownTimer.start();
    }

    private void stopCountDownTimer() {
        countDownTimer.cancel();
        smoothAnimation.end();
    }

    private void setProgressBarValues() {
        progressBarCircle.setMax((int) (timeCountInMilliSeconds / 10));
        progressBarCircle.setProgress((int) (timeCountInMilliSeconds / 10));
    }

    private String hmsTimeFormatter(long milliSeconds) {
        String hms = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return hms;
    }

    private void setTimerValues() {
        Intent intent = getIntent();
        // assigning values after converting to milliseconds
        timeCountInMilliSeconds = intent.getLongExtra("minutes", 1) * 60 * 1000;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            try {
                timeCountInMilliSeconds = data.getLongExtra("minutes", 1) * 60 * 1000;
                textViewTime.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                if (countDownTimer != null) {
                    stopCountDownTimer();
                }
                status = TimerStatus.STOPPED;
                countdown_btn.setBackgroundResource(R.mipmap.countdownic);
                progressBarCircle.setProgress(0);
            }catch (Exception ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private void checkConnection(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Service.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){

            wifiConnected = networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;

            if (wifiConnected){

            }else{

            }
        }else {
            showToast();
        }
    }

    public void showToast() {
        // Set the toast and duration
        int toastDurationInMilliSeconds = 10000;
        mToastToShow = Toast.makeText(this, "You are in offline-mode", Toast.LENGTH_LONG);

        // Set the countdown to display the toast
        CountDownTimer toastCountDown;
        toastCountDown = new CountDownTimer(toastDurationInMilliSeconds, 1000 /*Tick duration*/) {
            public void onTick(long millisUntilFinished) {
                mToastToShow.show();
            }
            public void onFinish() {
                mToastToShow.cancel();
            }
        };
        // Show the toast and starts the countdown
        mToastToShow.show();
        toastCountDown.start();
    }
}