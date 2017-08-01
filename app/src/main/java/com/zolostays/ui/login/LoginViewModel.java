package com.zolostays.ui.login;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.zolostays.BaseViewModel;
import com.zolostays.R;
import com.zolostays.common.AppConstants;
import com.zolostays.data.User;
import com.zolostays.data.source.UsersDataSource;
import com.zolostays.data.source.UsersRepository;
import com.zolostays.util.PrefHelper;

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

    /**
     * This method is responsible for checking user input validity and database for user.
     * @param phoneNumber - Phone Number
     * @param password - Password
     */
    private void performLoginWithCredentials(String phoneNumber, String password) {
        if (validateCredentials(phoneNumber, password)) {
            usersRepository.getUser(phoneNumber, password, new UsersDataSource.GetUserCallback() {
                @Override
                public void onUserLoaded(User user)
                {
                    if(user != null)
                    {
                        // Store values in shared preferences
                        PrefHelper.getInstance(context).writePreference(AppConstants.SPK_USER_PHONE_NUMBER, user.getPhoneNumber() != null ? user.getPhoneNumber() : "");
                        PrefHelper.getInstance(context).writePreference(AppConstants.SPK_USER_NAME, user.getName() != null ? user.getName() : "");

                        // Inform Navigator
                        if (navigator != null)
                            navigator.goForHome();
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    snackbarText.set(context.getString(R.string.invalid_credentials));
                }
            });
        }
    }

    /**
     * This method is used to validate the user inputs such ad Phone amd Password.
     *
     * @return - True for validation otherwise False
     */
    private boolean validateCredentials(String phoneNumber, String password) {
        if (phoneNumber == null || phoneNumber.length() != 10) {
            snackbarText.set(context.getString(R.string.invalid_phone_number_message));
            return false;
        } else if (password == null || password.contains(" ") || TextUtils.isEmpty(password)) {
            snackbarText.set(context.getString(R.string.invalid_password_message));
            return false;
        } else {
            return true;
        }
    }
}
