package com.dtunctuncer.playbook.play;

import com.dtunctuncer.playbook.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class PlayModule {
    private IPlayView view;

    public PlayModule(IPlayView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    IPlayView provideView(){
        return view;
    }
}
