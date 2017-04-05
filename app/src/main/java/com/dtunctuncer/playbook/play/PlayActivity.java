package com.dtunctuncer.playbook.play;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dtunctuncer.playbook.App;
import com.dtunctuncer.playbook.R;
import com.dtunctuncer.playbook.core.PlayType;
import com.dtunctuncer.playbook.detail.PlayDetailActivity;
import com.dtunctuncer.playbook.models.Play;
import com.dtunctuncer.playbook.play.type.OnListFragmentInteractionListener;
import com.dtunctuncer.playbook.play.type.PlayTypeFragment;
import com.dtunctuncer.playbook.search.SearchActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity implements IPlayView, OnListFragmentInteractionListener {

    @Inject
    PlayPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    ViewPager pager;

    private PagerAdapter adapter;
    private ArrayList<Play> plays = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        DaggerPlayComponent.builder().applicationComponent(App.getApplicationComponent()).playModule(new PlayModule(this)).build().inject(this);
        adapter = new PagerAdapter(getSupportFragmentManager());
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        presenter.getPlays();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.play_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.search == item.getItemId()) {
            Intent intent = new Intent(this, SearchActivity.class);
            intent.putParcelableArrayListExtra(SearchActivity.ARG_SEARCH_LIST, plays);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addTab(PlayType type, ArrayList<Play> playList) {
        tabs.addTab(tabs.newTab().setText(getTabNameByType(type)));
        adapter.addFragment(PlayTypeFragment.newInstance(1, playList));
        if (plays.size() <= 76)
            plays.addAll(playList);
    }

    @Override
    public void showTabs() {
        pager.setAdapter(adapter);
    }

    @Override
    public void onListFragmentInteraction(Play play) {
        Intent intent = new Intent(this, PlayDetailActivity.class);
        intent.putExtra(PlayDetailActivity.ARG_PLAY, play);
        startActivity(intent);
    }

    private int getTabNameByType(PlayType type) {
        switch (type) {
            case BEGINNER:
                return R.string.beginner;
            case AMATEUR:
                return R.string.amateur;
            case WEEKEND_WARRIOR:
                return R.string.warrior;
            case ADVANCED:
                return R.string.advanced;
            default:
                return 0;
        }
    }
}