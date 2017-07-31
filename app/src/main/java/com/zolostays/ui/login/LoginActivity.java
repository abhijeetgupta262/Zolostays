package com.zolostays.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zolostays.R;
import com.zolostays.application.ZolostaysApplication;
import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.dagger.login.DaggerLoginComponent;
import com.zolostays.dagger.login.LoginModule;
import com.zolostays.databinding.ActivityLoginBinding;
import com.zolostays.ui.registration.RegistrationActivity;
import com.zolostays.util.SnackbarUtils;

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
    @Inject LoginViewModel loginViewModel;
    // Variable for Binding
    private ActivityLoginBinding loginBinding;

    private Observable.OnPropertyChangedCallback mSnackbarCallback;

    /* ================================ Getter - Setter Method ================================== */


    /* ================================== Life Cycle Method ===================================== */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loginBinding == null) {
            loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        }
        // Inject the Dependencies
        DaggerLoginComponent.builder()
                .zolostaysAppModule(new ZolostaysAppModule(getApplication()))
                .loginModule(new LoginModule())
                .usersRepositoryComponent(((ZolostaysApplication)getApplication()).getUsersRepositoryComponent())
                .build()
                .inject(LoginActivity.this);

        // Set Navigator for view model
        loginViewModel.setNavigator(LoginActivity.this);

        // Bind Login ViewModel
        loginBinding.setLoginViewModel(loginViewModel);

        setupSnackbar();
    }

    @Override
    public void onDestroy() {
        if (mSnackbarCallback != null) {
            loginViewModel.snackbarText.removeOnPropertyChangedCallback(mSnackbarCallback);
        }
        super.onDestroy();
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void goForForgotPassword() {

    }

    @Override
    public void goForRegistration() {
        //Start the registration screen
        Intent registrationActivityIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(registrationActivityIntent);
    }

    @Override
    public void goForHome() {

    }

    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */

    private void setupSnackbar() {
        mSnackbarCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                SnackbarUtils.showSnackbar(loginBinding.getRoot(), loginViewModel.getSnackbarText());
            }
        };
        loginViewModel.snackbarText.addOnPropertyChangedCallback(mSnackbarCallback);
    }
}
