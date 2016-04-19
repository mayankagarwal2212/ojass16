package com.parikshit.ojass16;

import android.graphics.Bitmap;

/**
 * Created by rishavz_sagar on 25-Jan-16.
 */
public class Notification_item {
    String message;
    Bitmap image;
    public Notification_item(Bitmap image,String message){
        this.image=image;
        this.message=message;
    }
}
