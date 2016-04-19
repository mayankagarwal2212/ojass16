package com.parikshit.ojass16;

/**
 * Created by mayank on 28-01-2016.
 */

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Youtubeclass extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener {

    public static final String API_KEY = "AIzaSyDZm3ls3VGwz9nR2r3A2zljgfukUjRxWJE";
    public static final String VIDEO_ID = "fzpZ8-a_WUc";

    private YouTubePlayer youTubePlayer;
    private YouTubePlayerFragment youTubePlayerFragment;
    private TextView textVideoLog;
    private Button btnViewFullScreen, morevideos;

    private static final int RQS_ErrorDialog = 1;

    private MyPlayerStateChangeListener myPlayerStateChangeListener;
    private MyPlaybackEventListener myPlaybackEventListener;

    String log = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_utube);

        youTubePlayerFragment = (YouTubePlayerFragment) getFragmentManager()
                .findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(API_KEY, this);

        //textVideoLog = (TextView) findViewById(R.id.videolog);
        morevideos = (Button) findViewById(R.id.more_videos);
        //https://www.youtube.com/user/OJASS2016


        morevideos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com/user/OJASS2016");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        myPlayerStateChangeListener = new MyPlayerStateChangeListener();
        myPlaybackEventListener = new MyPlaybackEventListener();

        btnViewFullScreen = (Button) findViewById(R.id.btnviewfullscreen);
        btnViewFullScreen.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                try {
                    youTubePlayer.setFullscreen(true);
                } catch (Exception e) {
                    Toast.makeText(Youtubeclass.this, "Please download the youtube app", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, Activity2.class));
        super.onBackPressed();
    }

    @Override
    public void onInitializationFailure(Provider provider,
                                        YouTubeInitializationResult result) {

        if (result.isUserRecoverableError()) {
            result.getErrorDialog(this, RQS_ErrorDialog).show();
        } else {
            Toast.makeText(this,
                    "YouTubePlayer.onInitializationFailure(): " + result.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {

        youTubePlayer = player;

        //Toast.makeText(getApplicationContext(),
        //      "YouTubePlayer.onInitializationSuccess()",
        //    Toast.LENGTH_LONG).show();

        youTubePlayer.setPlayerStateChangeListener(myPlayerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(myPlaybackEventListener);

        if (!wasRestored) {
            player.cueVideo(VIDEO_ID);
        }

    }

    private final class MyPlayerStateChangeListener implements PlayerStateChangeListener {

        private void updateLog(String prompt) {
            log += "MyPlayerStateChangeListener" + "\n" +
                    prompt + "\n\n=====";
            // textVideoLog.setText(log);
        }

        ;

        @Override
        public void onAdStarted() {
            //updateLog("onAdStarted()");
        }

        @Override
        public void onError(
                com.google.android.youtube.player.YouTubePlayer.ErrorReason arg0) {
            // updateLog("onError(): " + arg0.toString());
        }

        @Override
        public void onLoaded(String arg0) {
            //updateLog("onLoaded(): " + arg0);
        }

        @Override
        public void onLoading() {
            //updateLog("onLoading()");
        }

        @Override
        public void onVideoEnded() {
            //updateLog("onVideoEnded()");
        }

        @Override
        public void onVideoStarted() {
            // updateLog("onVideoStarted()");
        }

    }

    private final class MyPlaybackEventListener implements PlaybackEventListener {

        private void updateLog(String prompt) {
            log += "MyPlaybackEventListener" + "\n-" +
                    prompt + "\n\n=====";
            //textVideoLog.setText(log);
        }

        ;

        @Override
        public void onBuffering(boolean arg0) {
            // updateLog("onBuffering(): " + String.valueOf(arg0));
        }

        @Override
        public void onPaused() {
            //updateLog("onPaused()");
        }

        @Override
        public void onPlaying() {
            //updateLog("onPlaying()");
        }

        @Override
        public void onSeekTo(int arg0) {
            // updateLog("onSeekTo(): " + String.valueOf(arg0));
        }

        @Override
        public void onStopped() {
            //updateLog("onStopped()");
        }

    }
}
