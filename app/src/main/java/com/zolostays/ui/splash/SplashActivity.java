package com.zolostays.ui.splash;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zolostays.R;
import com.zolostays.common.AppConstants;

/**
 * This class/activity is responsible for the Splash of the application.
 * The Splash shows for the 5 sec and after that it redirect the app to the login screen.
 */
public class SplashActivity extends AppCompatActivity implements SplashNavigator
{
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for the View Model
    private SplashViewModel splashViewModel;

    /* ================================ Getter - Setter Method ================================== */


    /* ================================== Life Cycle Method ===================================== */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialize the ViewModel
        splashViewModel = new SplashViewModel(SplashActivity.this);
        // Set Navigator for view model
        splashViewModel.setNavigator(SplashActivity.this);
        // Start view model
        splashViewModel.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        splashViewModel.setBackPressed(true);
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void goForLogin()
    {
        // Start the Login Screen

    }

    @Override
    public void goForHome()
    {
        // Start the Home Screen
    }

    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */
}
