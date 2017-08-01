package com.zolostays.ui.splash;

import android.content.Context;

import com.zolostays.BaseViewModel;
import com.zolostays.common.AppConstants;
import com.zolostays.util.PrefHelper;

/**
 * ViewModel for the Splash screen.
 */
public class SplashViewModel extends BaseViewModel {
    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Current Context
    private Context context;
    // Boolean variable to handle the back button press
    private boolean isBackPressed = false;
    // Variable for Splash Navigator
    private SplashNavigator navigator;

    /* =================================== Constructors ========================================= */

    public SplashViewModel(Context context) {
        this.context = context;
    }

    /* ================================ Getter - Setter Method ================================== */

    public boolean isBackPressed() {
        return isBackPressed;
    }

    public void setBackPressed(boolean backPressed) {
        isBackPressed = backPressed;
    }

    public SplashNavigator getNavigator() {
        return navigator;
    }

    public void setNavigator(SplashNavigator navigator) {
        this.navigator = navigator;
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void start() {
        // Start thread for splash screen delay
        startSplashDelayThread();
    }

    /* =================================== User Define Methods ================================== */

    /**
     * This method is used to start the delay thread for the splash screen and make decision for
     * the next screen.
     */
    private void startSplashDelayThread() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                // Wait for the splash timeout.
                try {
                    Thread.sleep(AppConstants.splashScreenDelay);
                    // Check for the back pressed or not
                    if (!isBackPressed) {
                        // Check the Login Status and open appropriate screen
                        checkUserLoginStatus();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    /**
     * This method is sued to check the user login status and open the appropriate screen.
     */
    private void checkUserLoginStatus()
    {
        // Check for the User Info in Shared Preferences
        if (PrefHelper.getInstance(context).getPrefrence().contains(AppConstants.SPK_USER_PHONE_NUMBER))
        {
            // Start the Home Screen
            navigator.goForHome();

        } else {
            // Start the Login Screen
            navigator.goForLogin();
        }
    }
}
