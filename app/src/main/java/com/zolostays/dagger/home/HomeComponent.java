package com.zolostays.dagger.home;

import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.ui.home.HomeActivity;
import com.zolostays.util.ActivityScoped;

import dagger.Component;

/**
 * Define Component for Home Screen.
 */
@ActivityScoped
@Component(modules = {HomeModule.class, ZolostaysAppModule.class})
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
