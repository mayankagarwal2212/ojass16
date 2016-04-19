package com.parikshit.ojass16;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static java.lang.Math.sqrt;

/**
 * Created by rishavz_sagar on 31-Dec-15.
 */
public class Itinerary extends Fragment implements View.OnTouchListener {
    NavigationDrawerFragment.NavigationDrawerCallbacks obj;

    int ctr;
    ImageView iv1, iv2, iv3;
    Button btn1, btn2;
    private static final String TAG = "Touch";
    @SuppressWarnings("unused")
    private static final float MIN_ZOOM = 1f, MAX_ZOOM = 1f;

    // These matrices will be used to scale points of the image
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();

    // The 3 states (events) which the user is trying to perform
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    // these PointF objects are used to record the point(s) the user is touching
    PointF start = new PointF();
    PointF mid = new PointF();
    float oldDist = 1f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_itinerary, container, false);
        matrix.preScale(0.25f, 0.25f);

        ctr = 0;
        iv1 = (ImageView) v.findViewById(R.id.imageEnhance1);
        iv2 = (ImageView) v.findViewById(R.id.imageEnhance2);
        iv3 = (ImageView) v.findViewById(R.id.imageEnhance3);
        btn1 = (Button) v.findViewById(R.id.left);
        btn2 = (Button) v.findViewById(R.id.right);

        try {
            Picasso.with(getActivity()).load("http://www.ojass.in/2016/img/event_1.jpg").fit().centerCrop().into(iv1);
            Picasso.with(getActivity()).load("http://www.ojass.in/2016/img/event_2.jpg").fit().centerCrop().into(iv2);
            Picasso.with(getActivity()).load("http://www.ojass.in/2016/img/event_3.jpg").fit().centerCrop().into(iv3);
        } catch (Exception e) {
            Picasso.with(getActivity()).load(R.drawable.ojass_logo).into(iv1);
            e.printStackTrace();
        }

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn1.setVisibility(View.VISIBLE);
                ctr++;
                ctr = ctr % 3;
                if (ctr == 2) {
                    btn2.setVisibility(View.INVISIBLE);
                }
                switch (ctr) {
                    case 0:
                        iv2.setVisibility(View.INVISIBLE);
                        iv3.setVisibility(View.INVISIBLE);
                        iv1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        iv1.setVisibility(View.INVISIBLE);
                        iv3.setVisibility(View.INVISIBLE);
                        iv2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        iv2.setVisibility(View.INVISIBLE);
                        iv1.setVisibility(View.INVISIBLE);
                        iv3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn2.setVisibility(View.VISIBLE);
                ctr--;
                ctr = ctr % 3;
                if (ctr == 0)
                    btn1.setVisibility(View.INVISIBLE);

                switch (ctr) {
                    case 0:
                        iv2.setVisibility(View.INVISIBLE);
                        iv3.setVisibility(View.INVISIBLE);
                        iv1.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        iv1.setVisibility(View.INVISIBLE);
                        iv3.setVisibility(View.INVISIBLE);
                        iv2.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        iv2.setVisibility(View.INVISIBLE);
                        iv1.setVisibility(View.INVISIBLE);
                        iv3.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        iv1.setImageMatrix(matrix);
        iv1.setOnTouchListener(this);
        iv2.setImageMatrix(matrix);
        iv2.setOnTouchListener(this);
        iv3.setImageMatrix(matrix);
        iv3.setOnTouchListener(this);
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        obj = (NavigationDrawerFragment.NavigationDrawerCallbacks) activity;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        ImageView view = (ImageView) v;
        view.setScaleType(ImageView.ScaleType.MATRIX);
        float scale;

        // Handle touch events here...

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:   // first finger down only
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                Log.d(TAG, "mode=DRAG"); // write to LogCat
                mode = DRAG;
                break;

            case MotionEvent.ACTION_UP: // first finger lifted

            case MotionEvent.ACTION_POINTER_UP: // second finger lifted

                mode = NONE;
                Log.d(TAG, "mode=NONE");
                break;

            case MotionEvent.ACTION_POINTER_DOWN: // first and second finger down

                oldDist = spacing(event);
                Log.d(TAG, "oldDist=" + oldDist);
                if (oldDist > 5f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                    Log.d(TAG, "mode=ZOOM");
                }
                break;

            case MotionEvent.ACTION_MOVE:

                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y); // create the transformation in the matrix  of points
                } else if (mode == ZOOM) {
                    // pinch zooming
                    float newDist = spacing(event);
                    Log.d(TAG, "newDist=" + newDist);
                    if (newDist > 5f) {
                        matrix.set(savedMatrix);
                        scale = newDist / oldDist; // setting the scaling of the
                        // matrix...if scale > 1 means
                        // zoom in...if scale < 1 means
                        // zoom out
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix); // display the transformation on screen

        return true; // indicate event was handled
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }
}