package com.example.cars.Helper;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Developer on 4/7/2018.
 */

public class Helper {

    public static final String NAME = "Name";

    public static final String EMAIL = "Email";


    public static final int SELECT_PICTURE = 2000;

    public static boolean isValidEmail(String email){
        if(email.contains("@")){
            return true;
        }
        return false;
    }

    public static void displayMessageToast(Context context, String displayMessage){
        Toast.makeText(context, displayMessage, Toast.LENGTH_LONG).show();

    }
}
