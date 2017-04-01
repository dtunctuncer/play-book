package com.dtunctuncer.playbook.play;

import com.dtunctuncer.playbook.di.ActivityScope;
import com.dtunctuncer.playbook.di.ApplicationComponent;

import dagger.Component;

@ActivityScope
@Component(modules = {PlayModule.class}, dependencies = {ApplicationComponent.class})
public interface PlayComponent {
    void inject(PlayActivity playActivity);
}
