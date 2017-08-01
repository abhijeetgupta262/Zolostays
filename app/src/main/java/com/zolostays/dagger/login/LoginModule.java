package com.zolostays.dagger.login;

import android.content.Context;

import com.zolostays.data.source.UsersRepository;
import com.zolostays.ui.login.LoginViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Define Module for Login Screen.
 */

@Module
public class LoginModule
{
    @Provides
    LoginViewModel provideLoginViewModel(Context context, UsersRepository usersRepository) {
        return new LoginViewModel(context, usersRepository);
    }
}
