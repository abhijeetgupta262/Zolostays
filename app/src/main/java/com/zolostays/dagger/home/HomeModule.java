package com.zolostays.dagger.home;

import android.content.Context;

import com.zolostays.ui.home.HomeViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Define Module for Login Screen.
 */

@Module
public class HomeModule {

    @Provides
    HomeViewModel provideHomeViewModel(Context context) {
        return new HomeViewModel(context);
    }
}
