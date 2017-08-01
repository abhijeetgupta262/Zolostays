package com.zolostays.ui.home;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zolostays.R;
import com.zolostays.common.AppConstants;
import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.dagger.home.DaggerHomeComponent;
import com.zolostays.dagger.home.HomeModule;
import com.zolostays.data.User;
import com.zolostays.databinding.ActivityHomeBinding;
import com.zolostays.ui.login.LoginActivity;
import com.zolostays.util.PrefHelper;

import javax.inject.Inject;

/**
 * This class/activity is responsible for show the Ui for the Home screen.
 */

public class HomeActivity extends AppCompatActivity implements HomeNavigator
{
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for the View Model
    @Inject HomeViewModel viewModel;
    // Variable for Binding
    private ActivityHomeBinding homeBinding;
    // Variable for the Snack text observable
    private Observable.OnPropertyChangedCallback mSnackbarCallback;
    /* ================================ Getter - Setter Method ================================== */


    /* ================================== Life Cycle Method ===================================== */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (homeBinding == null) {
            homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        }

        DaggerHomeComponent.builder()
                .zolostaysAppModule(new ZolostaysAppModule(getApplication()))
                .homeModule(new HomeModule())
                .build()
                .inject(HomeActivity.this);

        // Set Navigator for the View Model
        viewModel.setNavigator(HomeActivity.this);

        // Bind Login ViewModel
        User user = new User("", PrefHelper.getInstance(HomeActivity.this).readPreference(AppConstants.SPK_USER_NAME),
                PrefHelper.getInstance(HomeActivity.this).readPreference(AppConstants.SPK_USER_PHONE_NUMBER),"","");
        homeBinding.setUser(user);
        homeBinding.setHomeViewModel(viewModel);
    }

    /* ============================= Implemented Interface Method =============================== */

    @Override
    public void goForLogin()
    {
        // Start the Login screen
        Intent loginActivityIntent = new Intent(HomeActivity.this, LoginActivity.class);
        finish();
        startActivity(loginActivityIntent);
    }

    /* ==================================== OnClick Methods ===================================== */


    /* =================================== User Define Methods ================================== */

}