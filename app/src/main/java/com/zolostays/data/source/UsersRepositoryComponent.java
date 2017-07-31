package com.zolostays.data.source;

import com.zolostays.application.ZolostaysApplication;
import com.zolostays.dagger.ZolostaysAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This is a Dagger component. Refer to {@link com.zolostays.application.ZolostaysApplication} for the list of Dagger components
 * used in this application.
 */
@Singleton
@Component(modules = {UsersRepositoryModule.class, ZolostaysAppModule.class})
public interface UsersRepositoryComponent {

    UsersRepository getUsersRepository();
}
