package com.zolostays.ui.registration;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zolostays.R;
import com.zolostays.application.ZolostaysApplication;
import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.dagger.registration.DaggerRegistrationComponent;
import com.zolostays.dagger.registration.RegistrationModule;
import com.zolostays.databinding.ActivityRegistrationBinding;
import com.zolostays.ui.home.HomeActivity;
import com.zolostays.util.SnackbarUtils;

import javax.inject.Inject;

/**
 * This class/activity is responsible for the Registration screen of the application.
 */

public class RegistrationActivity extends AppCompatActivity implements RegistrationNavigator {
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for the View Model
    @Inject
    RegistrationViewModel viewModel;
    // Variable for Binding
    private ActivityRegistrationBinding registrationBinding;
    // Variable for the Snack text observable
    private Observable.OnPropertyChangedCallback mSnackbarCallback;
    /* ================================ Getter - Setter Method ================================== */


    /* ================================== Life Cycle Method ===================================== */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (registrationBinding == null) {
            registrationBinding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        }

        // Inject the Dependencies
        DaggerRegistrationComponent.builder()
                .zolostaysAppModule(new ZolostaysAppModule(getApplication()))
                .registrationModule(new RegistrationModule())
                .usersRepositoryComponent(((ZolostaysApplication) getApplication()).getUsersRepositoryComponent())
                .build()
                .inject(RegistrationActivity.this);

        // Set Navigator for view model
        viewModel.setNavigator(RegistrationActivity.this);
        // Bind Login ViewModel
        registrationBinding.setRegistrationViewHolder(viewModel);
        // Setup the Snackbar text observable
        setupSnackbar();
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void goForHome() {
        // Start the Home screen
        Intent homeActivityIntent = new Intent(RegistrationActivity.this, HomeActivity.class);
        homeActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeActivityIntent);
    }

    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */

    /**
     * This method is used setup the {@link android.support.design.widget.Snackbar} callback to the appropriate message on screen.
     */
    private void setupSnackbar() {
        mSnackbarCallback = new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                SnackbarUtils.showSnackbar(registrationBinding.getRoot(), viewModel.getSnackbarText());
            }
        };
        viewModel.snackbarText.addOnPropertyChangedCallback(mSnackbarCallback);
    }
}