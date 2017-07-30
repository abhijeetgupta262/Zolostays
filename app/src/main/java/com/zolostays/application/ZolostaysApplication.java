package com.zolostays.application;

import android.app.Application;

import com.zolostays.dagger.DaggerZolostaysAppComponent;
import com.zolostays.dagger.ZolostaysAppComponent;
import com.zolostays.dagger.ZolostaysAppModule;


public class ZolostaysApplication extends Application
{
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Zolostays App Component
    private ZolostaysAppComponent appComponent;

    /* ================================ Getter - Setter Method ================================== */

    public ZolostaysAppComponent getAppComponent() {
        return appComponent;
    }

    /* ================================== Life Cycle Method ===================================== */

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the App Component
        this.appComponent = initDagger(ZolostaysApplication.this);
    }

    /* ============================= Implemented Interface Method =============================== */



    /* =================================== User Define Methods ================================== */

    private ZolostaysAppComponent initDagger(ZolostaysApplication application)
    {
        return DaggerZolostaysAppComponent.builder()
                .zolostaysAppModule(new ZolostaysAppModule(application))
                .build();
    }
}
