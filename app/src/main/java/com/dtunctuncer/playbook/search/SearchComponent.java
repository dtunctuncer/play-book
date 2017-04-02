package com.dtunctuncer.playbook.search;

import com.dtunctuncer.playbook.di.ActivityScope;
import com.dtunctuncer.playbook.di.ApplicationComponent;

import dagger.Component;

@ActivityScope
@Component(modules = {SearchModule.class}, dependencies = {ApplicationComponent.class})
public interface SearchComponent {
    void inject(SearchActivity searchActivity);
}