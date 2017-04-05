package com.dtunctuncer.playbook.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dtunctuncer.playbook.R;
import com.dtunctuncer.playbook.models.Play;
import com.dtunctuncer.playbook.utils.widgets.PlayTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayDetailActivity extends AppCompatActivity {
    public static final String ARG_PLAY = "play";
    @BindView(R.id.title)
    PlayTextView title;
    @BindView(R.id.play)
    TextView playTextView;
    @BindView(R.id.success)
    TextView success;
    @BindView(R.id.attracts)
    TextView attracts;
    @BindView(R.id.requirements)
    TextView requirements;
    @BindView(R.id.prepTime)
    TextView prepTime;
    @BindView(R.id.bummers)
    TextView bummers;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.share)
    FloatingActionButton share;

    private Play play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_detail);
        ButterKnife.bind(this);
        play = getIntent().getParcelableExtra(ARG_PLAY);

        if (play != null) {
            title.setText(play.getName());
            playTextView.setText(play.getPlay());
            success.setText(play.getSucces().replace("%%", "%"));
            attracts.setText(play.getAttracts());
            requirements.setText(play.getRequirements());
            prepTime.setText(play.getTime());
            bummers.setText(play.getBummers());


            scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY > oldScrollY)
                        share.hide();
                    else
                        share.show();
                }
            });
        }
    }

    @OnClick(R.id.share)
    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, play.toString());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_play)));
    }
}