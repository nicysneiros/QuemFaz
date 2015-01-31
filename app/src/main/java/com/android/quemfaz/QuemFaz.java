package com.android.quemfaz;

import android.app.Application;
import android.util.Log;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by nicolle on 31/01/15.
 */
public class QuemFaz extends Application {

    public void onCreate(){
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "xC4PRvy2YXmAV2AiQQEYiDYmNQQYvcsVzHQi7fiZ", "XLsk5LfbIrdQl3ViCnf8ka4FcAeNfh1vmkMqEpAW");

        ParseUser.enableAutomaticUser();

        //Checar se já existe um usuário logado
        if(ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())){

            //Não há usuário logado -> logar com usuário anônimo
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (e != null){
                        Log.d("QuemFaz", "Erro no login com usuário anônimo");
                    } else {
                        Log.d("QuemFaz", "Sucesso no login com usuário anônimo");
                    }
                }
            });
        }

        ParseUser.getCurrentUser().saveInBackground();

    }

}
