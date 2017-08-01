package com.zolostays.application;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.data.source.DaggerUsersRepositoryComponent;
import com.zolostays.data.source.UsersRepositoryComponent;


public class ZolostaysApplication extends MultiDexApplication {
    /* ====================================== Interface ========================================= */


    /* ================================== Constant Variable ===================================== */


    /* =================================== Class Variable ======================================= */

    // Variable for Zolostays App Component
    private UsersRepositoryComponent usersRepositoryComponent;

    /* ================================ Getter - Setter Method ================================== */

    public UsersRepositoryComponent getUsersRepositoryComponent() {
        return usersRepositoryComponent;
    }

    public void setUsersRepositoryComponent(UsersRepositoryComponent usersRepositoryComponent) {
        this.usersRepositoryComponent = usersRepositoryComponent;
    }

    /* ================================== Life Cycle Method ===================================== */

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the App Component
        this.usersRepositoryComponent = initDagger(ZolostaysApplication.this);
    }

    /* ============================= Implemented Interface Method =============================== */



    /* =================================== User Define Methods ================================== */

    private UsersRepositoryComponent initDagger(ZolostaysApplication application) {
        return DaggerUsersRepositoryComponent.builder()
                .zolostaysAppModule(new ZolostaysAppModule(application))
                .build();
    }
}
