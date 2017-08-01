package com.zolostays.dagger.registration;

import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.data.source.UsersRepositoryComponent;
import com.zolostays.ui.registration.RegistrationActivity;
import com.zolostays.util.ActivityScoped;

import dagger.Component;

/**
 * Define Component for Registration Activity.
 */

@ActivityScoped
@Component(dependencies = {UsersRepositoryComponent.class}, modules = {RegistrationModule.class, ZolostaysAppModule.class})
public interface RegistrationComponent {
    void inject(RegistrationActivity registrationActivity);
}
