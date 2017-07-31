package com.zolostays.ui.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zolostays.R;
import com.zolostays.application.ZolostaysApplication;

import javax.inject.Inject;

/**
 * This class/activity is responsible for show the Ui for the login feature. The User provides
 * credentials (i.e. Phone Number and Password) for login.
 */

public class LoginActivity extends AppCompatActivity implements LoginNavigator {
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for the View Model
    @Inject
    LoginViewModel loginViewModel;

    /* ================================ Getter - Setter Method ================================== */


    /* ================================== Life Cycle Method ===================================== */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inject the Dependencies
        ((ZolostaysApplication) getApplication()).getAppComponent().inject(LoginActivity.this);

        // Set Navigator for view model
        loginViewModel.setNavigator(LoginActivity.this);

    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void goForForgotPassword() {

    }

    @Override
    public void goForRegistration() {

    }

    @Override
    public void goForHome() {

    }

    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */
}
