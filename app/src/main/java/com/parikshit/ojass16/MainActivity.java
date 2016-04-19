package com.parikshit.ojass16;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

//import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    private static final float ROTATE_FROM = 0.0f;
    private static final float ROTATE_TO = -10.0f * 360.0f;// 3.141592654f * 32.0f;
    public static int val;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getActionBar().hide();
        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        //actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#37475E")));
        //actionBar.setTitle(Html.fromHtml("<font color='#00ff00'>OJASS '16</font>"));

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        main activity me ungli on the trial branch...
//        learning....

        final ImageButton logo_icon = (ImageButton) findViewById(R.id.logo_icon);
        Animation a1= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);

        a1.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    RotateAnimation r; // = new RotateAnimation(ROTATE_FROM, ROTATE_TO);
                    r = new RotateAnimation(ROTATE_FROM, ROTATE_TO, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    r.setDuration((long) 1 * 300);
                    r.setRepeatCount(0);
                    r.setFillAfter(true);
                    logo_icon.startAnimation(r);

                    int height = 0;
                    Display display = getWindowManager().getDefaultDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    int width = size.x;
                    height = size.y;
                    animation = new TranslateAnimation(0, 0, 0, (-height / 2) + 150);
                    animation.setDuration(1 * 300);

                    animation.setAnimationListener(new Animation.AnimationListener() {
                           @Override
                           public void onAnimationStart(Animation animation) {

                           }

                           @Override
                           public void onAnimationEnd(Animation animation) {

                               ImageView img=(ImageView)findViewById(R.id.back);

                               img.setVisibility(View.VISIBLE);

                               Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/BohemianTypewriter.ttf");
                               Typeface typeFace2 = Typeface.createFromAsset(getAssets(), "fonts/hemi_head.ttf");

                               TextView myTextView = (TextView) findViewById(R.id.festName);
                               myTextView.setTypeface(typeFace);
                               myTextView.setTextColor(Color.WHITE);
                               myTextView.setVisibility(View.VISIBLE);

                               TextView myDateView = (TextView) findViewById(R.id.festDate);
                               myDateView.setTypeface(typeFace);
                               myDateView.setTextColor(Color.WHITE);
                               myDateView.setVisibility(View.VISIBLE);

                               TextView myTag1 = (TextView) findViewById(R.id.tagLine1);
                               myTag1.setTypeface(typeFace2);
                               myTag1.setTextColor(Color.WHITE);
                               myTag1.setVisibility(View.VISIBLE);

                               TextView myTag2 = (TextView) findViewById(R.id.tagLine2);
                               myTag2.setTypeface(typeFace2);
                               myTag2.setTextColor(Color.WHITE);
                               myTag2.setVisibility(View.VISIBLE);

                               TextView myTag3 = (TextView) findViewById(R.id.tagLine3);
                               myTag3.setTypeface(typeFace2);
                               myTag3.setTextColor(Color.WHITE);
                               myTag3.setVisibility(View.VISIBLE);


                               SharedPreferences pref=getSharedPreferences("login",0);
                               int state=pref.getInt("status",0);
                               val=state;
                               if(state==1){
                                   new Handler().postDelayed(new Runnable() {
                                       @Override
                                       public void run() {
                                           final Intent mainIntent = new Intent(MainActivity.this, Activity2.class);
                                           MainActivity.this.startActivity(mainIntent);
                                           MainActivity.this.finish();
                                       }
                                   }, 1000);

                               }else {
                                   new Handler().postDelayed(new Runnable() {
                                       @Override
                                       public void run() {
                                           final Intent mainIntent = new Intent(MainActivity.this, Login.class);
                                           MainActivity.this.startActivity(mainIntent);
                                           MainActivity.this.finish();
                                       }
                                   }, 1000);
                               }
                           }

                           @Override
                           public void onAnimationRepeat (Animation animation){

                           }
                       }

                    );
                    animation.setRepeatCount(0);
                    ImageButton logo_icon_two = (ImageButton) findViewById(R.id.logo_icon);
                    animation.setFillAfter(true);
                    logo_icon_two.startAnimation(animation);
                }

                @Override
                public void onAnimationRepeat (Animation animation){

                }
            }

        );
        logo_icon.startAnimation(a1);
    }
}