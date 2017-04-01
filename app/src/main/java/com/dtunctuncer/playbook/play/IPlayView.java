package com.dtunctuncer.playbook.play;

import com.dtunctuncer.playbook.core.PlayType;
import com.dtunctuncer.playbook.models.Play;

import java.util.ArrayList;
import java.util.List;

public interface IPlayView {
    void addTab(PlayType type, ArrayList<Play> playList);

    void showTabs();
}
