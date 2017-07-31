package com.zolostays.data.source;

import com.zolostays.data.source.localdb.UsersLocalDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * This is used by Dagger to inject the required arguments into the {@link UsersRepository}.
 */
@Module
abstract class UsersRepositoryModule {

    @Singleton
    @Binds
    abstract UsersDataSource provideUsersLocalDataSource(UsersLocalDataSource dataSource);

}

