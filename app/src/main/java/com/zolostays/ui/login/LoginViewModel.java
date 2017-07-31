package com.zolostays.ui.login;

import android.content.Context;
import android.view.View;

import com.zolostays.BaseViewModel;

/**
 * ViewModel for the Login screen.
 */

public class LoginViewModel extends BaseViewModel {
    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Current Context
    private Context context;
    // Variable for Login Navigator
    private LoginNavigator navigator;

    /* =================================== Constructors ========================================= */

    public LoginViewModel(Context context) {
        this.context = context;
    }

    /* ================================ Getter - Setter Method ================================== */

    public LoginNavigator getNavigator() {
        return navigator;
    }

    public void setNavigator(LoginNavigator navigator) {
        this.navigator = navigator;
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void start() {

    }

    /* =================================== User Define Methods ================================== */

    /**
     * this method is called when user choose login option.
     *
     * @param view - View of Button
     */
    public void onLoginClicked(View view) {
        // Verify enter credentials and navigate accordingly

    }


    /**
     * this method is called when user choose forgot password option.
     *
     * @param view - View
     */
    public void onForgotPasswordClicked(View view) {
        // Navigate to forgot password screen
        navigator.goForForgotPassword();
    }

    /**
     * this method is called when user choose forgot password option.
     *
     * @param view - View
     */
    public void onCreateAccountClicked(View view) {
        // Navigate to registration screen
        navigator.goForRegistration();
    }


}
