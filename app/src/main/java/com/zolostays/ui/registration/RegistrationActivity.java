package com.zolostays.ui.registration;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zolostays.R;
import com.zolostays.application.ZolostaysApplication;
import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.dagger.registration.DaggerRegistrationComponent;
import com.zolostays.dagger.registration.RegistrationModule;
import com.zolostays.databinding.ActivityRegistrationBinding;

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
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void goForHome() {

    }

    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */
}