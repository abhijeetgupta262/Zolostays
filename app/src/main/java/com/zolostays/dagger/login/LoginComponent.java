package com.zolostays.dagger.login;

import com.zolostays.dagger.ZolostaysAppModule;
import com.zolostays.data.source.UsersRepositoryComponent;
import com.zolostays.ui.login.LoginActivity;
import com.zolostays.util.ActivityScoped;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Define Component for Login Activity.
 */
@ActivityScoped
@Component(dependencies = {UsersRepositoryComponent.class}, modules = {LoginModule.class, ZolostaysAppModule.class})
public interface LoginComponent
{
    void inject(LoginActivity loginActivity);
}
