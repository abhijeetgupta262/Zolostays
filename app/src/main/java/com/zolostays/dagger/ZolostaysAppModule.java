package com.zolostays.dagger;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by standarduser on 30/07/17.
 */
@Module
public class ZolostaysAppModule
{
    private Application application;

    public ZolostaysAppModule(Application application)
    {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext()
    {
        return application;
    }
}
