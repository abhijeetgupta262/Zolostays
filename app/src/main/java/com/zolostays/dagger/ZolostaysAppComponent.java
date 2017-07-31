package com.zolostays.dagger;

import com.zolostays.ui.login.LoginActivity;
import com.zolostays.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application Component
 */
@Singleton
@Component(modules = {ZolostaysAppModule.class, ViewModelModule.class})
public interface ZolostaysAppComponent
{
    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);
}
