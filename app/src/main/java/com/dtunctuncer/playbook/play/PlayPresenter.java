package com.dtunctuncer.playbook.play;

import android.content.Context;

import com.dtunctuncer.playbook.R;
import com.dtunctuncer.playbook.core.PlayType;
import com.dtunctuncer.playbook.models.Play;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.dtunctuncer.playbook.core.PlayType.ADVANCED;
import static com.dtunctuncer.playbook.core.PlayType.AMATEUR;
import static com.dtunctuncer.playbook.core.PlayType.BEGINNER;
import static com.dtunctuncer.playbook.core.PlayType.WEEKEND_WARRIOR;

public class PlayPresenter {

    private List<String> beginner,amateur,warrior,advanced;
    private IPlayView view;
    private Context context;

    @Inject
    public PlayPresenter(IPlayView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void getPlays() {
        String[] titles = context.getResources().getStringArray(R.array.title);
        String[] success = context.getResources().getStringArray(R.array.success);
        String[] attracts = context.getResources().getStringArray(R.array.attracts);
        String[] requirements = context.getResources().getStringArray(R.array.requirements);
        String[] bummers = context.getResources().getStringArray(R.array.bummers);
        String[] plays = context.getResources().getStringArray(R.array.plays);
        String[] time = context.getResources().getStringArray(R.array.time);
        List<Play> playList = new ArrayList<>();
        ArrayList<Play> beginnerPlayList = new ArrayList<>();
        ArrayList<Play> amateurPlayList = new ArrayList<>();
        ArrayList<Play> warriorPlayList = new ArrayList<>();
        ArrayList<Play> advancedPlayList = new ArrayList<>();

        for (int i = 0; i < titles.length; i++) {
            Play play = new Play();
            play.setName(titles[i]);
            play.setSucces(success[i]);
            play.setAttracts(attracts[i]);
            play.setRequirements(requirements[i]);
            play.setBummers(bummers[i]);
            play.setPlay(plays[i]);
            play.setTime(time[i]);
            play.setType(getPlayTypeByName(titles[i]));
            playList.add(play);
        }

        for (Play play : playList) {
            switch (play.getType()){
                case BEGINNER:
                    beginnerPlayList.add(play);
                    break;
                case AMATEUR:
                    amateurPlayList.add(play);
                    break;
                case WEEKEND_WARRIOR:
                    warriorPlayList.add(play);
                    break;
                case ADVANCED:
                    advancedPlayList.add(play);
                    break;
            }
        }

        view.addTab(BEGINNER,beginnerPlayList);
        view.addTab(AMATEUR, amateurPlayList);
        view.addTab(WEEKEND_WARRIOR, warriorPlayList);
        view.addTab(ADVANCED,advancedPlayList);
        view.showTabs();
    }


    private List<String> getBeginnerPlays() {
        if (beginner != null) {
            return beginner;
        }
        beginner = new ArrayList<>();
        beginner.add("The SNASA");
        beginner.add("The One Week to Live");
        beginner.add("The Blinde Date");
        beginner.add("The Don't Drink That");
        beginner.add("The Terminator");
        beginner.add("The Hot Dude");
        beginner.add("The European");
        beginner.add("The Fireman");
        beginner.add("The Naked Man");
        beginner.add("The Rumspringa");
        beginner.add("The Escaped Convict");
        beginner.add("The Author");
        beginner.add("The Olympian");
        beginner.add("The Robot");
        beginner.add("The I'm Joining The Marines");
        beginner.add("The Little Orphan Barney");
        beginner.add("The Grandpa Wonka");
        beginner.add("The Shotgun");
        beginner.add("The He's Not Comming");
        return beginner;
    }

    private List<String> getAmateurPlays() {
        if (amateur != null) {
            return amateur;
        }
        amateur = new ArrayList<>();
        amateur.add("The My Penis Grants Wishes");
        amateur.add("The Mannequin");
        amateur.add("The Abracadabra");
        amateur.add("The Brian's Friend");
        amateur.add("The Ted Mosby");
        amateur.add("The Leo");
        amateur.add("The Bionic Man");
        amateur.add("The Jorge Posada");
        amateur.add("The Anniversary Of My Wife's Death");
        amateur.add("The Love At First Sight");
        amateur.add("The Biker");
        amateur.add("The Stanley Cup");
        amateur.add("The Befuddled Puppey Owner");
        amateur.add("The Portrait");
        amateur.add("The Jim Nacho");
        amateur.add("The Confused Inheritor");
        amateur.add("The Other Jonas");
        return amateur;
    }

    private List<String> getWarriorPlays() {
        if (warrior != null) {
            return warrior;
        }
        warrior = new ArrayList<>();
        warrior.add("The Lorenzo Von Matterhorn");
        warrior.add("The area 69");
        warrior.add("The Ghost");
        warrior.add("The Call Barney Stinson");
        warrior.add("The Missing Cat");
        warrior.add("The Barney Identity");
        warrior.add("The Pinocchio Puppy");
        warrior.add("The Duffel Bag");
        warrior.add("The Prince Akeem");
        warrior.add("The Moviegoer");
        warrior.add("The Cool Priest");
        warrior.add("The Rorschach");
        warrior.add("The Cheap Trick");
        warrior.add("The Ballet Defector");
        warrior.add("The Doggie");
        warrior.add("The Au Pair");
        warrior.add("The Lottery");
        warrior.add("The Time Traveler");
        warrior.add("The Vampire");
        warrior.add("The Young Man And The Sea");
        return warrior;
    }

    private List<String> getAdvancedPlays() {
        if (advanced != null) {
            return advanced;
        }
        advanced = new ArrayList<>();
        advanced.add("The Déjà Vu");
        advanced.add("The Fall In Love");
        advanced.add("The I Can Land This Plane");
        advanced.add("The Billionaire");
        advanced.add("The I Can Guess Your Weight");
        advanced.add("The Project X");
        advanced.add("The Life Guard");
        advanced.add("The Diet Guru");
        advanced.add("The Boy In The Bubble");
        advanced.add("The Mrs. Stinsfire");
        advanced.add("The Bouncer");
        advanced.add("The Trojan Lesbian");
        advanced.add("The Footloose");
        advanced.add("The Grieving Chicks");
        advanced.add("The E Pluribus Unum");
        advanced.add("The Mr. President");
        advanced.add("The Kidney Scheme");
        advanced.add("The Heimlich Maneuver");
        advanced.add("The Ghost Of Christmas Future");
        advanced.add("The Scuba Diver");
        return advanced;
    }

    private PlayType getPlayTypeByName(String name) {
        if (getBeginnerPlays().contains(name))
            return BEGINNER;
        else if (getAmateurPlays().contains(name))
            return AMATEUR;
        else if (getWarriorPlays().contains(name))
            return PlayType.WEEKEND_WARRIOR;
        else if (getAdvancedPlays().contains(name))
            return PlayType.ADVANCED;
        return PlayType.NONE;
    }
}
