package com.dtunctuncer.playbook.search;


import android.content.Context;

import com.dtunctuncer.playbook.models.Play;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

public class SearchPresenter {

    private ISearchView view;
    private Context context;
    private ArrayList<Play> plays;

    @Inject
    public SearchPresenter(ISearchView view, Context context, ArrayList<Play> plays) {
        this.view = view;
        this.context = context;
        this.plays = plays;
    }

    public void performSearch(String query) {
        List<Play> searchResult = new ArrayList<>();
        for (Play play : plays) {
            if (play.getName().toLowerCase(Locale.ENGLISH).contains(query.toLowerCase(Locale.ENGLISH)))
                searchResult.add(play);
        }

        view.showResult(searchResult);

    }
}
