package com.zolostays.dagger;

import com.zolostays.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by standarduser on 30/07/17.
 */
@Singleton
@Component(modules = {ZolostaysAppModule.class, ViewModelModule.class})
public interface ZolostaysAppComponent
{
    void inject(SplashActivity splashActivity);
}
