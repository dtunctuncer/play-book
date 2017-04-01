package com.dtunctuncer.playbook.play;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.dtunctuncer.playbook.App;
import com.dtunctuncer.playbook.R;
import com.dtunctuncer.playbook.core.PlayType;
import com.dtunctuncer.playbook.models.Play;
import com.dtunctuncer.playbook.play.type.OnListFragmentInteractionListener;
import com.dtunctuncer.playbook.play.type.PlayTypeFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayActivity extends AppCompatActivity implements IPlayView,OnListFragmentInteractionListener {

    @Inject
    PlayPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.pager)
    ViewPager pager;

    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        /*setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }*/
        DaggerPlayComponent.builder().applicationComponent(App.getApplicationComponent()).playModule(new PlayModule(this)).build().inject(this);
        adapter = new PagerAdapter(getSupportFragmentManager());
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        presenter.getPlays();
    }

    @Override
    public void addTab(PlayType type, ArrayList<Play> playList) {
        tabs.addTab(tabs.newTab().setText(getTabNameByType(type)));
        adapter.addFragment(PlayTypeFragment.newInstance(1,playList));
    }

    @Override
    public void showTabs() {
        pager.setAdapter(adapter);
    }

    @Override
    public void onListFragmentInteraction(Play play) {
        Toast.makeText(this, play.getName(), Toast.LENGTH_SHORT).show();
    }

    private int getTabNameByType(PlayType type){
        switch (type){
            case BEGINNER:
                return R.string.beginner;
            case AMETEUR:
                return R.string.amateur;
            case WEEKEND_WARIOR:
                return R.string.warrior;
            case ADVANCED:
                return R.string.advanced;
            default:
                return 0;
        }
    }
}