package com.zolostays.ui.login;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.view.View;

import com.zolostays.BaseViewModel;
import com.zolostays.data.source.UsersRepository;

/**
 * ViewModel for the Login screen.
 */

public class LoginViewModel extends BaseViewModel {
    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Current Context
    private Context context;
    private UsersRepository usersRepository;
    // Variable for Login Navigator
    private LoginNavigator navigator;
    // Variable for Observer
    public final ObservableField<String> phoneNumber = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<String> snackbarText = new ObservableField<>();

    /* =================================== Constructors ========================================= */

    public LoginViewModel(Context context, UsersRepository usersRepository) {
        this.context = context;
        this.usersRepository = usersRepository;
    }

    /* ================================ Getter - Setter Method ================================== */

    public LoginNavigator getNavigator() {
        return navigator;
    }

    public void setNavigator(LoginNavigator navigator) {
        this.navigator = navigator;
    }

    @Nullable
    public String getSnackbarText() {
        return snackbarText.get();
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
        performLoginWithCredentials(phoneNumber.get(), password.get());
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

    private void performLoginWithCredentials(String phoneNumber, String password)
    {

    }
}
