package com.zolostays.ui.registration;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.zolostays.BaseViewModel;
import com.zolostays.R;
import com.zolostays.data.User;
import com.zolostays.data.source.UsersRepository;

/**
 * ViewModel for the Registration screen.
 */

public class RegistrationViewModel extends BaseViewModel {
    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Current Context
    private Context context;
    private UsersRepository usersRepository;
    // Variable for Login Navigator
    private RegistrationNavigator navigator;
    // Variable for Observer
    public final ObservableField<String> phoneNumber = new ObservableField<>();
    public final ObservableField<String> email = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> password = new ObservableField<>();
    public final ObservableField<String> snackbarText = new ObservableField<>();

    /* =================================== Constructors ========================================= */

    public RegistrationViewModel(Context context, UsersRepository usersRepository) {
        this.context = context;
        this.usersRepository = usersRepository;
    }

    /* ================================ Getter - Setter Method ================================== */

    public RegistrationNavigator getNavigator() {
        return navigator;
    }

    public void setNavigator(RegistrationNavigator navigator) {
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
     * this method is called when user choose forgot password option.
     *
     * @param view - View
     */
    public void onRegisterClicked(View view) {
        // Verify enter credentials and navigate accordingly
        performRegisterWithCredentials(phoneNumber.get(), email.get(), name.get(), password.get());
    }

    /**
     * This method is responsible for checking user input validity and database for user.
     *
     * @param phoneNumber - Phone Number
     * @param password    - Password
     */
    private void performRegisterWithCredentials(String phoneNumber, String email, String name, String password) {
        if (validateCredentials(phoneNumber, email, name, password)) {
            User user = new User("", name, phoneNumber, email, password);
            usersRepository.saveTask(user);

            if (navigator != null)
                navigator.goForHome();
        }
    }

    /**
     * This method is used to validate the user inputs such ad Phone amd Password.
     *
     * @return - True for validation otherwise False
     */
    private boolean validateCredentials(String phoneNumber, String email, String name, String password) {
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
