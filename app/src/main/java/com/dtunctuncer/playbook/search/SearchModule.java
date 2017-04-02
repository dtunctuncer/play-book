package com.dtunctuncer.playbook.search;

import com.dtunctuncer.playbook.di.ActivityScope;
import com.dtunctuncer.playbook.models.Play;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    private ISearchView view;
    private ArrayList<Play> plays;

    public SearchModule(ISearchView view, ArrayList<Play> plays) {
        this.view = view;
        this.plays = plays;
    }

    @Provides
    @ActivityScope
    ISearchView provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    ArrayList<Play> providePlays() {
        return plays;
    }
}
