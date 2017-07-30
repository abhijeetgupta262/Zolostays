package com.zolostays.ui.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zolostays.R;
import com.zolostays.common.AppConstants;

/**
 * This class/activity is responsible for the Splash of the application.
 * The Splash shows for the 5 sec and after that it redirect the app to the login screen.
 */
public class SplashActivity extends AppCompatActivity
{
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */


    /* ================================ Getter - Setter Method ================================== */


    /* ================================== Life Cycle Method ===================================== */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    /* ============================= Implemented Interface Method =============================== */


    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */
}
