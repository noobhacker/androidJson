package com.example.song.myapplication;

import android.databinding.BaseObservable;

/**
 * Created by Song on 4/14/2017.
 */

public class Message extends BaseObservable {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }
}
