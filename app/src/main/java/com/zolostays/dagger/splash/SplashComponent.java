package com.zolostays.dagger.splash;

import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by standarduser on 31/07/17.
 */
@Singleton
@Component(modules = {SplashModule.class, ZolostaysAppModule.class})
public interface SplashComponent
{
    void inject(SplashActivity splashActivity);
}
