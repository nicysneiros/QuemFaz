package com.android.quemfaz;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseUser;

/**
 * Created by nicolle on 31/01/15.
 */
public class QuemFaz extends Application {

    public void onCreate(){
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "xC4PRvy2YXmAV2AiQQEYiDYmNQQYvcsVzHQi7fiZ", "XLsk5LfbIrdQl3ViCnf8ka4FcAeNfh1vmkMqEpAW");
        ParseUser.getCurrentUser().saveInBackground();
    }
}
