package com.zolostays.dagger;

import android.content.Context;

import com.zolostays.ui.splash.SplashViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by standarduser on 30/07/17.
 */

@Module
public class ViewModelModule {
    @Provides
    @Singleton
    SplashViewModel provideSplashViewModel(Context context) {
        return new SplashViewModel(context);
    }
}
