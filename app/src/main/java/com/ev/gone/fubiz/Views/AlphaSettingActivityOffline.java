package com.ev.gone.fubiz.Views;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ev.gone.fubiz.Manager.ListViewAdapter;
import com.ev.gone.fubiz.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AlphaSettingActivityOffline extends AppCompatActivity {

    View alpha_btn;
    Button backto_alpha_main;

    //data
    ListView lv1;
    ArrayList<String> ListSong;
    ArrayAdapter arr;
    ListView lv2;
    String[] items;
    MediaPlayer mp;
    ListViewAdapter data_adapter_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha_setting_offiline);


        lv1 = (ListView) findViewById(R.id.lv);
        ListSong = new ArrayList<String>();
        ListSong.add("Clair de Lune"); // 0
        ListSong.add("Crystal Steel Collaboration"); // 1
        ListSong.add("Liebestraum"); // 2
        ListSong.add("Moonlight");// 3
        ListSong.add("Piano Sonata in F major"); // 4
        ListSong.add("Noctune op.9 No.2"); // 5
        ListSong.add("Piano Sonata in B major"); // 6
        ListSong.add("Piano Sonata in G major"); // 7


        alpha_btn = (View) findViewById(R.id.alpha_setting);
        alpha_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_adapter_test = new ListViewAdapter(AlphaSettingActivityOffline.this, ListSong);
                lv1.setAdapter(data_adapter_test);
            }
        });


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2Fclairdelune.mp3?alt=media&token=81849b04-de92-4568-91a2-c9cbf4006a6b");
                        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Clair de Lune");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Clair-de-Lune.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Clair-de-Lune.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }
                if (i == 1) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2FCrystalSteelCollaboration.mp3?alt=media&token=8371bf2f-093c-4b15-befa-19b5d0026846");
                        DownloadManager downloadManager1 = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Crystal Steel Collaboration");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Crystal-Steel-Collaboration.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Crystal-Steel-Collaboration.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager1.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }
                if (i == 2) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2FLiebestraum.mp3?alt=media&token=d6208532-a993-496d-b0a2-e755082c6f1c");
                        DownloadManager downloadManager2 = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Liebestraum");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Liebestraum.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Liebestraum.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager2.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }
                if (i == 3) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2FMoonlight.mp3?alt=media&token=74a9960e-e635-4658-959d-46a4616fcc3b");
                        DownloadManager downloadManager3 = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Moonlight");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Moonlight.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Moonlight.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager3.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }
                if (i == 4) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2FPiano%20Sonata%20in%20F%20major.mp3?alt=media&token=8f1f42b3-8bdf-4c74-b4a3-49f29c1c7eea");
                        DownloadManager downloadManager4 = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Piano Sonata in F major");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Piano-Sonata-in-F-major.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Piano-Sonata-in-F-major.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager4.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }
                if (i == 5) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2FNocturne%20op.9%20No.2.mp3?alt=media&token=7cbcd860-48c6-417c-b716-4634485ab3d8");
                        DownloadManager downloadManager7 = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Noctune op.9 No.2");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Noctune-op.9-No.2.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Noctune-op.9-No.2.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager7.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }
                if (i == 6) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2FPiano%20Sonata%20in%20B%20flat%20major.mp3?alt=media&token=18a3c546-386d-40cc-901f-2b74bfa3e9c8");
                        DownloadManager downloadManager5 = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Piano Sonata in B major");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Piano-Sonata-in-B-major.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Piano-Sonata-in-B-major.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager5.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }
                if (i == 7) {
                    if (isConnectingToInternet()) {
                        Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/fubiz-1210c.appspot.com/o/media%2FPiano%20Sonata%20in%20G%20major.mp3?alt=media&token=b5bd6039-6c0b-4861-baff-6b0157ae8baf");
                        DownloadManager downloadManager6 = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        //Setting title of request
                        request.setTitle("Piano Sonata in G major");

                        //Setting description of request
                        request.setDescription("Downloading...");
                        check("Piano-Sonata-in-G-major.mp3");
                        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_MUSIC + "/" + "Music Fubiz", "Piano-Sonata-in-G-major.mp3");
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        Long reference = downloadManager6.enqueue(request);
                    } else
                        Toast.makeText(AlphaSettingActivityOffline.this, "Oops!! There is no internet connection. Please enable internet connection and try again.", Toast.LENGTH_SHORT).show();


                }


            }
        });

        backto_alpha_main = (Button) findViewById(R.id.backto_alpha_main);
        backto_alpha_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent redirect_one = new Intent(AlphaSettingActivityOffline.this, AlphaActivityOffline.class);
                startActivity(redirect_one);
            }
        });

    }
    public ArrayList<File> findSongs(File root){
        ArrayList<File> a = new ArrayList<File>();
        File[] files = root.listFiles();
        for (File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){

                a.addAll(findSongs(singleFile));

            }
            else {
                if(singleFile.getName().endsWith(".mp3") || singleFile.getName().endsWith(".wav")){
                    a.add(singleFile);
                }
            }
        }
        return a;
    }
    public void toast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private void check(String name) {
        File apkStorage = new File(
                Environment.DIRECTORY_MUSIC + "/"
                        + "Music Fubiz");
        if (apkStorage.exists()) {
            apkStorage.mkdir();
            Log.e("MenuCircleActivity", "Directory Created.");
        }

        File outputFile = new File(apkStorage, name);//Create Output file in Main File

        //Create New File if not present
        if (!outputFile.exists()) {
            try {
                outputFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                outputFile = null;
                Log.e("MenuCircleActivity", "Download Error Exception " + e.getMessage());
            }
            Log.e("MenuCircleActivity", "File Created");
        }
    }
}
