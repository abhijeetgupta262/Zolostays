package com.zolostays.dagger.splash;

import android.content.Context;

import com.zolostays.ui.splash.SplashViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by standarduser on 31/07/17.
 */

@Module
public class SplashModule
{
    @Provides
    @Singleton
    SplashViewModel provideSplashViewModel(Context context) {
        return new SplashViewModel(context);
    }
}
