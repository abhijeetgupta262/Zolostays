package com.zolostays.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zolostays.R;

/**
 * This class/activity is responsible for show the Ui for the login feature. The User provides
 * credentials (i.e. Phone Number and Password) for login.
 */

public class LoginActivity extends AppCompatActivity {
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for the View Model


    /* ================================ Getter - Setter Method ================================== */


    /* ================================== Life Cycle Method ===================================== */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /* ============================= Implemented Interface Method =============================== */


    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */
}
