package com.zolostays.dagger;

import android.content.Context;

import com.zolostays.ui.login.LoginViewModel;
import com.zolostays.ui.splash.SplashViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Exposes the ViewModel to be used in the screens.
 */

@Module
public class ViewModelModule {
    @Provides
    @Singleton
    SplashViewModel provideSplashViewModel(Context context) {
        return new SplashViewModel(context);
    }


    @Provides
    @Singleton
    LoginViewModel provideLoginViewModel(Context context) {
        return new LoginViewModel(context);
    }
}
