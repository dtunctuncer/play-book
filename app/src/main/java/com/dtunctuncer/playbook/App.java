package com.dtunctuncer.playbook;

import android.app.Application;

import com.dtunctuncer.playbook.di.ApplicationComponent;
import com.dtunctuncer.playbook.di.ApplicationModule;
import com.dtunctuncer.playbook.di.DaggerApplicationComponent;

public class App extends Application {

    private static ApplicationComponent applicationComponent;

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //di
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

    }
}
