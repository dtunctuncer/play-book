package com.dtunctuncer.playbook.di;

import android.content.Context;

import com.dtunctuncer.playbook.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    App app();
    Context context();
}
