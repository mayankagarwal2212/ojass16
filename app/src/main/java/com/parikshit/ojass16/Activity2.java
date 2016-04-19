package com.parikshit.ojass16;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Stack;

public class Activity2 extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private Stack<Fragment> stack;
    public static String stringMessage[][];
    public static int messageLength[];
    public static final String MESSAGES_ARRAY = "STRING_MESSAGES";
    public static final String INCLUDE_STACK = "STACK_NO";
    public static int pgroup, pchild;
    public static String str[] = new String[5];
    public static Context context;
    public static Bitmap images[][];
    public static Bitmap img;
    public static Bitmap defImage;
    public int state = 0;
    SQLiteDatabase db;
    public static String current_head = "";
    String[] heads = new String[16];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_2);

        new DownloadImageTask().execute("http://www.ojass.in/2016/img/sponsor_app.jpg");

        heads = new String[]{"ASHUTOSH SINGH\n7857827075\n\n" +
                "SAKET JHA\n9835241079\n\n" +
                "VIKAS DIXIT\n9472706225\n\n" +
                "SHIKHA SHARMA\n9031262374\n\n", "HARSHIT BARNWAL\n8252516429\n\n" +
                "SHASHWAT\n9472791275\n\n" +
                "PRIYANKA\n8235240933\n\n", "SHIVAM SAXENA\n8808931728\n\n" +
                "HAMIM RAAVI\n7549117469\n\n" +
                "SANDHYA KUMARI\n8651385532\n\n", "ABHISHEK GUPTA\n9031815146\n\n" +
                "ANURUDRA\n7209608531\n\n", "ANKAN SIKDAR \n8986765584\n\n" +
                "JAYESH XALXO\n9430703407\n\n" +
                "SHIVANI SINGH\n7209470194\n\n", "SHUBHAM\n9955828878\n\n" +
                "RAHUL\n9835885792\n\n" +
                "AKRITI PRASAD\n7762844333\n\n", "ABHISHEK GUPTA\n7870575709\n\n" +
                "AYUSH CHANDRA\n9504792536\n\n" +
                "PINAKI SHAW\n8092233263\n\n", "DILIP KUMAR\n8603217538\n\n" +
                "PRASHANT KUMAR\n9608945146\n\n" +
                "ADITI KUMARI\n9852888769\n\n", "MANISH RAJAK \n8092763775\n\n" +
                "ATUL RAI\n9431786128\n\n" +
                "PRANITA CHAKRAVORTY\n9570192023\n\n", "AMAN KHULLAR\n7543926474\n\n" +
                "ANOOP JAISWAL\n7070450430\n\n" +
                "SHIKSHA KUMARI\n8541947984\n\n",
                "abdf",
                "ANIT DIWAKAR\n9031741117\n\n" + "SAKET SATYARTHI\n08507083580\n\n" + "ANKIT KUMAR SHRIVASTAVA\n9472706227\n\n", "SOVAM TALUKDAR\n8102556806\n\n" +
                "PRANAY\n9122513422\n\n" +
                "KHUSHBOO SINGHANIA\n8235208288\n\n", "SARTHAK KAPOOR\n9470918688\n\n" +
                "SAI SREE RAM\n9493245421\n\n", "HIMANSHU\n8102460900\n\n" +
                "K.PAVAN\n9471580311\n\n" +
                "ANVITA JHA\n8292204688\n\n", "MAYANK\n9031975746\n\n" +
                "SANJAY\n8538923604\n\n" +
                "SHREMA SINGH\n8092380137\n\n"};

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#3F51B5"));
        actionBar.setBackgroundDrawable(colorDrawable);

        stack = new Stack<Fragment>();
        context = Activity2.context;
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            db = myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static void setImages(int group, int column, Bitmap im) {
        images[group][column] = im;
    }

    public void getMessages() {
        GraphRequest request;
        if (isValid()) {
            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/586755524720318/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[0] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[0][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(0, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(0, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();

            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/920410684675105/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[3] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[3][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(3, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(3, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();

            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/1736467089905480/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[4] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[4][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(4, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(4, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //                 Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/479682758886371/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[7] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[7][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(7, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(7, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/201066206908405/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[9] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[9][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(9, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(9, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/891114264340733/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[17] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[17][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(17, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(17, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();
            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/1018426491533575/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[15] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[15][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(15, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(15, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/465522460262315/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[5] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[5][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(5, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(5, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/1552931738358468/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[14] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[14][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(14, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(14, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/147515848957592/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[2] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[2][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(2, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(2, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/193746530983930/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[6] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[6][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(6, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(6, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();

            //1382448548733381


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/1382448548733381/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[11] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[11][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(11, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(11, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/630116117134486/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[8] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[8][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(8, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(8, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();




            /*
            * Begin here */

            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/211063815909792/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[12] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[12][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(12, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(12, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/952578268131160/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[10] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[10][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(10, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(10, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/1103057189728494/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[16] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[16][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(16, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(16, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();


            request = GraphRequest.newGraphPathRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/506867176044405/posts",
                    new GraphRequest.Callback() {
                        @Override
                        public void onCompleted(GraphResponse response) {
                            JSONObject ob = response.getJSONObject();
                            Target target;
                            try {
                                JSONArray arr = ob.getJSONArray("data");
                                messageLength[13] = arr.length();
                                int i;
                                for (i = 0; i < arr.length() && i < 20; i++) {
                                    final int j = i;
                                    JSONObject tob = arr.getJSONObject(i);
                                    stringMessage[13][i] = tob.optString("message");
                                    target = new Target() {
                                        @Override
                                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                            setImages(13, j, bitmap);
                                        }

                                        @Override
                                        public void onBitmapFailed(Drawable errorDrawable) {
                                            setImages(13, j, defImage);
                                        }

                                        @Override
                                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                                        }
                                    };
                                    if (tob.optString("picture").length() != 0)
                                        Picasso.with(Activity2.this).load(tob.optString("picture")).into(target);
                                }
                                //Toast.makeText(Activity2.this, stringMessage[0][0], Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

            parameters = new Bundle();
            parameters.putString("fields", "picture,message");
            request.setParameters(parameters);
            request.executeAsync();

        } else {
            Toast.makeText(Activity2.this, "Please Login to get notifications", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNavigationDrawerItemSelected(int groupPosition, int childPosition) {
        // update the main content by replacing fragments

        pgroup = groupPosition;
        pchild = childPosition;

        if (groupPosition >= 2 && groupPosition <= 17)
            current_head = heads[groupPosition - 2];

        if (stringMessage == null) {
            stringMessage = new String[24][20];
            images = new Bitmap[24][20];
            messageLength = new int[20];
            context = Activity2.this;
            defImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);
            getMessages();
        }

        if (groupPosition != 0) {
            Cursor cs = db.rawQuery("SELECT * from Event WHERE EventId=" + groupPosition + " AND SubEventId=" + childPosition, null);
            while (cs.moveToNext()) {
                int i, j = 0;
                for (i = 2; i < 7; i++) {
                    str[j] = cs.getString(i);
                    j++;
                }
                for (i = 0; i < 5; i++) {
                    str[i] = str[i].replace("$", "\n");
                }
            }
        }

        Fragment frag;
        if (stack != null && stack.isEmpty())
            stack.push(new Home_frag_effect());
        FragmentManager manager;
        FragmentTransaction transaction;
        manager = getFragmentManager();
        transaction = manager.beginTransaction();
        switch (groupPosition) {
            case 0:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Home_frag_effect(), "Home");
                        transaction.commit();
                        mTitle = "Ojass\'16";
                }
                break;
            case 1:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "SciQMetry").commit();
                        mTitle = "ScIQMetry";
                        break;
                }
                break;
            case 2:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Arthshastra");
                        transaction.commit();
                        mTitle = "ARTHASHASHTRA FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Arthshastra");
                        transaction.commit();
                        mTitle = "ABC";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Arthshastra");
                        transaction.commit();
                        mTitle = "NEETISHASHTRA";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Arthshastra");
                        transaction.commit();
                        mTitle = "LET\'S STARTUP";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Arthshastra");
                        transaction.commit();
                        mTitle = "CORPORATE ROADIES";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Arthshastra");
                        transaction.commit();
                        mTitle = "BIZZATHLON 3.0";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Arthshastra");
                        transaction.commit();
                        mTitle = "WOLF OF DALAL STREET";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "Arthshastra");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 3:
                switch (childPosition) {
                    case 0:
                        frag = new Notification_Fragmen();
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, frag, "notification");
                        transaction.commit();
                        mTitle = "CIRCUIT HOUSE FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "CIRCUIT HOUSE");
                        transaction.commit();
                        mTitle = "HVC";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "CIRCUIT HOUSE");
                        transaction.commit();
                        mTitle = "ELIXIR OF ELECTRICITY";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "CIRCUIT HOUSE");
                        transaction.commit();
                        mTitle = "PRO-LO-CO";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "CIRCUIT HOUSE");
                        transaction.commit();
                        mTitle = "MAT-SIM";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "CIRCUIT HOUSE");
                        transaction.commit();
                        mTitle = "NEXUS";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "CIRCUIT HOUSE");
                        transaction.commit();
                        mTitle = "ELECTRO-Q";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "CIRCUIT HOUSE");
                        transaction.commit();
                        mTitle = "EMBETRIX";
                        break;
                    case 8:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "Arthshastra");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 4:
                switch (childPosition) {
                    //kalp it
                    case 0:
                        frag = new Notification_Fragmen();
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, frag, "notification");
                        transaction.commit();
                        mTitle = "KALP IT FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "APP-DROID";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "CODATHON";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "CODEMANIA";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "CODE YOUR BOT";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "DECODE";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "TUX";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "WEB VOGUE";
                        break;
                    case 8:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 5:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "No_Ground_Zone");
                        transaction.commit();
                        mTitle = "NO GROUND ZONE FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "No_Ground_Zone");
                        transaction.commit();
                        mTitle = "RC PLANE";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Arthshastra");
                        transaction.commit();
                        mTitle = "MICAV";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 6:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Paraphernalia");
                        transaction.commit();
                        mTitle = "PARAPHERNALIA FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Paraphernalia");
                        transaction.commit();
                        mTitle = "THE GREAT OJASS RACE";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Paraphernalia");
                        transaction.commit();
                        mTitle = "SI-FI";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Paraphernalia");
                        transaction.commit();
                        mTitle = "MAD AD";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Paraphernalia");
                        transaction.commit();
                        mTitle = "LENS VIEW";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Paraphernalia");
                        transaction.commit();
                        mTitle = "DIRECTOR'S CUT";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 7:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Pie_Throne");
                        transaction.commit();
                        mTitle = "PRODUS FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Pie_Throne");
                        transaction.commit();
                        mTitle = "INDUSTRIAL TYCOON";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Pie_Throne");
                        transaction.commit();
                        mTitle = "UTPREKSH";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Pie_Throne");
                        transaction.commit();
                        mTitle = "ARTIFACT";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Pie_Throne");
                        transaction.commit();
                        mTitle = "DRONGYAN";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Pie_Throne");
                        transaction.commit();
                        mTitle = "UDYOG";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 8:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "SILLICON VALLEY FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "TUKVILLA";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "JIGYASA";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "THE PIXEL GAME";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "CODESENSE";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "ANALOG HUNTER";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "DIGIZONE";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "NETKRAFT";
                        break;
                    case 8:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Silicon_Valley");
                        transaction.commit();
                        mTitle = "EMBETRIX";
                        break;
                    case 9:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 9:
                switch (childPosition) {
                    case 0:
                        frag = new Notification_Fragmen();
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, frag, "Rise_Of_Machina");
                        mTitle = "RISE OF MACHINA FEED";
                        transaction.commit();
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "AUTO QUIZ";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "ACCELERODROME";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "MOTO VIRTUALE";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "JUNKYARD WARS";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "SAMVEG";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "PRAKSHEPAN";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "ENIGMA";
                        break;
                    case 8:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 10:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "NSCET");
                        transaction.commit();
                        mTitle = "NSCET FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "NSCET");
                        transaction.commit();
                        mTitle = "NSCET";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 11:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Neo_Drishti");
                        transaction.commit();
                        mTitle = "NEO DRISHTI FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Neo_Drishti");
                        transaction.commit();
                        mTitle = "CODIYAPA";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Neo_Drishti");
                        transaction.commit();
                        mTitle = "GAME OF TROVES";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Neo_Drishti");
                        transaction.commit();
                        mTitle = "SCRATCH EASY";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Neo_Drishti");
                        transaction.commit();
                        mTitle = "SIMPLY SQL";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Neo_Drishti");
                        transaction.commit();
                        mTitle = "WEB 2.0";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 12:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Guru_Gyan");
                        transaction.commit();
                        mTitle = "GURU GYAN UPDATES";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Guest_Lecture(), "Guru Gyan");
                        transaction.commit();
                        mTitle = "GUEST LECTURE";
                        break;
                }
                break;
            case 13:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Deus-X-Machina");
                        transaction.commit();
                        mTitle = "DEUS-X-MACHINA FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Deus-X-Machina");
                        transaction.commit();
                        mTitle = "360 MANIA";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Deus-X-Machina");
                        transaction.commit();
                        mTitle = "VIC-TOY-RIE";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Deus-X-Machina");
                        transaction.commit();
                        mTitle = "BATTLESHIP";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Deus-X-Machina");
                        transaction.commit();
                        mTitle = "KURUKSHETRA";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Deus-X-Machina");
                        transaction.commit();
                        mTitle = "ROBO SOCCER";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Deus-X-Machina");
                        transaction.commit();
                        mTitle = "SHAPE SHIFTER";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 14:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Avartan");
                        transaction.commit();
                        mTitle = "AVARTAN FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Rise_Of_Machina");
                        transaction.commit();
                        mTitle = "INDUS SOLVA";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "AGNIKUND";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "METAL TRIVIA";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "CHITRANKIT";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "KALP_IT");
                        transaction.commit();
                        mTitle = "FUNTOOSH";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 15:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Armagedon");
                        transaction.commit();
                        mTitle = "ARMAGEDDON FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Armagedon");
                        transaction.commit();
                        mTitle = "FIFA";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Armagedon");
                        transaction.commit();
                        mTitle = "CS";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Armagedon");
                        transaction.commit();
                        mTitle = "NFS";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Armagedon");
                        transaction.commit();
                        mTitle = "ANGRY BIRD";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Armagedon");
                        transaction.commit();
                        mTitle = "DOTA";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Armagedon");
                        transaction.commit();
                        mTitle = "AGE OF EMPIRES 2";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 16:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Aakriti");
                        transaction.commit();
                        mTitle = "AAKRITI FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Aakriti");
                        transaction.commit();
                        mTitle = "ACUMEN";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Aakriti");
                        transaction.commit();
                        mTitle = "SANRACHNA";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Aakriti");
                        transaction.commit();
                        mTitle = "ARCHMADEEASE";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Aakriti");
                        transaction.commit();
                        mTitle = "EXEMPLAR";
                        break;
                    case 5:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Aakriti");
                        transaction.commit();
                        mTitle = "PIPE-O-MANIA";
                        break;
                    case 6:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Aakriti");
                        transaction.commit();
                        mTitle = "METROPOLIS";
                        break;
                    case 7:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 17:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "Prayas");
                        transaction.commit();
                        mTitle = "PRAYAS FEED";
                        break;
                    case 1:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Prayas");
                        transaction.commit();
                        mTitle = "JAGRITI";
                        break;
                    case 2:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Prayas");
                        transaction.commit();
                        mTitle = "SAMVEDNA";
                        break;
                    case 3:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new ScIQMetry(), "Prayas");
                        transaction.commit();
                        mTitle = "PRATIBIMB";
                        break;
                    case 4:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Head_Branchs(), "KALP_IT");
                        transaction.commit();
                        mTitle = "PIONEERS";
                        break;
                }
                break;
            case 18:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Notification_Fragmen(), "EXPOSICION");
                        transaction.commit();
                        mTitle = "EXPOCISION UPDATES";
                        break;

                }
                break;
            case 19:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Itinerary(), "Itinerary");
                        transaction.commit();
                        mTitle = "ITINERARY";
                        break;

                }
                break;
            case 20:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Sponsers(), "Sponsors");
                        transaction.commit();
                        mTitle = "SPONSORS";
                        break;
                }
                break;
            case 21:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Contact_Us(), "Contact_Us");
                        transaction.commit();
                        mTitle = "CORE TEAM";
                        break;
                }
                break;
            case 22:
                switch (childPosition) {
                    case 0:
                        transaction.setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, new Developers(), "Developers");
                        transaction.commit();
                        mTitle = "DEVELOPERS";
                        break;
                }
                break;
            case 23:
                switch (childPosition) {
                    case 0:
                        Intent i = new Intent(Activity2.this, Youtubeclass.class);
                        startActivity(i);
                        mTitle = "OJASS-STREAMING";
                        finish();
                        break;
                }
                break;
        }
    }

    public boolean isValid() {
        SharedPreferences pref = getSharedPreferences("login", 0);
        state = pref.getInt("status", 0);
        if (state == 1)
            return true;
        else
            return false;
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {
            if (stack.isEmpty())
                super.onBackPressed();
            else {
                Fragment f = new Home_frag_effect();
                stack.pop();
                pgroup = 0;
                getFragmentManager().beginTransaction().setCustomAnimations(R.animator.cross_fade_out, R.animator.cross_fade).replace(R.id.frame1, f).commit();
                getSupportActionBar().setTitle("HOME");
            }
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.activity2, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    public static void update() {
        GraphRequest request;
        Activity2 obj = new Activity2();
        if (stringMessage.length != 0) {
            if (MainActivity.val == 1) {
                switch (pgroup) {
                    case 0:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/586755524720318/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[0] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[0][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(0, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(0, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //                                           Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 2:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/147515848957592/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[2] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[2][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(2, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(2, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 3:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/920410684675105/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[3] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[3][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(3, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(3, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 4:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/1736467089905480/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[4] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[4][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(4, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(4, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 5:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/465522460262315/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[5] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[5][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(5, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(5, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;

                    case 6:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/193746530983930/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[6] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[6][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(6, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(6, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 7:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/479682758886371/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[7] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[7][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(7, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(7, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 8:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/630116117134486/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[8] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[8][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(8, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(8, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //  Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 9:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/201066206908405/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[9] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[9][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(9, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(9, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 10:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/952578268131160/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[10] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[10][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(10, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(10, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 11:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/1382448548733381/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[11] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[11][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(11, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(11, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                    case 12:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/211063815909792/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[12] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[12][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(12, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(12, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            // Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 13:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/50687176044405/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[13] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[13][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(13, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(13, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            // Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 14:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/1552931738358468/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[14] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[14][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(14, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(14, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 15:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/1018426491533575/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[15] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[15][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(15, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(15, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            // Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 16:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/1103057189728494/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[16] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[16][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(16, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(16, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 17:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/891114264340733/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[17] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[17][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(17, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(17, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;
                    case 18:
                        request = GraphRequest.newGraphPathRequest(
                                AccessToken.getCurrentAccessToken(),
                                "/198765310481979/posts",
                                new GraphRequest.Callback() {
                                    @Override
                                    public void onCompleted(GraphResponse response) {
                                        JSONObject ob = response.getJSONObject();
                                        Target target;
                                        try {
                                            JSONArray arr = ob.getJSONArray("data");
                                            messageLength[18] = arr.length();
                                            int i;
                                            for (i = 0; i < arr.length() && i < 20; i++) {
                                                final int j = i;
                                                JSONObject tob = arr.getJSONObject(i);
                                                stringMessage[18][i] = tob.optString("message");
                                                target = new Target() {
                                                    @Override
                                                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                                        setImages(18, j, bitmap);
                                                    }

                                                    @Override
                                                    public void onBitmapFailed(Drawable errorDrawable) {
                                                        setImages(18, j, defImage);
                                                    }

                                                    @Override
                                                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                                                    }
                                                };

                                                if (tob.optString("picture").length() != 0)
                                                    Picasso.with(context).load(tob.optString("picture")).into(target);

                                            }
                                            //Toast.makeText(context, stringMessage[0][0], Toast.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });

                        parameters = new Bundle();
                        parameters.putString("fields", "picture,message");
                        request.setParameters(parameters);
                        request.executeAsync();
                        break;

                }
            } else {
                Toast.makeText(context, "Please Login to get notifications", Toast.LENGTH_SHORT).show();
            }
        } else
            Toast.makeText(context, "Updating Notifications...\nRestart your app if it takes longer duration", Toast.LENGTH_SHORT).show();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                mIcon11=BitmapFactory.decodeResource(getResources(),R.drawable.sponsors);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            img = result;
        }
    }

}