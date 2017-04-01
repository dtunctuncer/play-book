package com.dtunctuncer.playbook.di;

import android.content.Context;

import com.dtunctuncer.playbook.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private App app;

    public ApplicationModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    App provideApp(){
        return app;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return app.getApplicationContext();
    }
}
