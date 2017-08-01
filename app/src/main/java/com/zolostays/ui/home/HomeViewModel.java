package com.zolostays.ui.home;

import android.content.Context;
import android.view.View;

import com.zolostays.BaseViewModel;
import com.zolostays.ui.splash.SplashNavigator;
import com.zolostays.util.PrefHelper;

/**
 * ViewModel for the Home screen.
 */

public class HomeViewModel extends BaseViewModel {
    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Current Context
    private Context context;
    // Variable for Splash Navigator
    private HomeNavigator navigator;

    /* =================================== Constructors ========================================= */

    public HomeViewModel(Context context) {
        this.context = context;
    }

    /* ================================ Getter - Setter Method ================================== */

    public HomeNavigator getNavigator() {
        return navigator;
    }

    public void setNavigator(HomeNavigator navigator) {
        this.navigator = navigator;
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void start() {

    }

    /* =================================== User Define Methods ================================== */

    /**
     * This method is called when user choose logout option.
     *
     * @param view - View
     */
    public void onLogoutClicked(View view) {
        // Clear all User preferences
        PrefHelper.clearPreferences(context);
        // Start Login Screen
        if(navigator != null)
            navigator.goForLogin();
    }
}