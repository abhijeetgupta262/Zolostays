package com.zolostays.dagger.registration;

import android.content.Context;

import com.zolostays.data.source.UsersRepository;
import com.zolostays.ui.registration.RegistrationViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Define Module for Registration Activity.
 */
@Module
public class RegistrationModule {
    @Provides
    RegistrationViewModel provideRegistrationViewModel(Context context, UsersRepository usersRepository) {
        return new RegistrationViewModel(context, usersRepository);
    }
}
