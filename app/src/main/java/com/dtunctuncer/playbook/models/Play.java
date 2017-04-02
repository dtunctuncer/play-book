package com.dtunctuncer.playbook.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.dtunctuncer.playbook.core.PlayType;

public class Play implements Parcelable {
    public static final Parcelable.Creator<Play> CREATOR = new Parcelable.Creator<Play>() {
        @Override
        public Play createFromParcel(Parcel source) {
            return new Play(source);
        }

        @Override
        public Play[] newArray(int size) {
            return new Play[size];
        }
    };
    private String name;
    private String succes;
    private String attracts;
    private String time;
    private String bummers;
    private PlayType type;
    private String requirements;
    private String play;

    public Play() {
    }

    protected Play(Parcel in) {
        this.name = in.readString();
        this.succes = in.readString();
        this.attracts = in.readString();
        this.time = in.readString();
        this.bummers = in.readString();
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : PlayType.values()[tmpType];
        this.requirements = in.readString();
        this.play = in.readString();
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSucces() {
        return succes;
    }

    public void setSucces(String succes) {
        this.succes = succes;
    }

    public String getAttracts() {
        return attracts;
    }

    public void setAttracts(String attracts) {
        this.attracts = attracts;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBummers() {
        return bummers;
    }

    public void setBummers(String bummers) {
        this.bummers = bummers;
    }

    public PlayType getType() {
        return type;
    }

    public void setType(PlayType type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.succes);
        dest.writeString(this.attracts);
        dest.writeString(this.time);
        dest.writeString(this.bummers);
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeString(this.requirements);
        dest.writeString(this.play);
    }

    @Override
    public String toString() {
        return "Title : " + getName()
                + "\n Success Rate : " + getSucces().replace("%%", "%")
                + "\n Attracts : " + getAttracts()
                + "\n Requirements : " + getRequirements()
                + "\n Prep Time : " + getTime()
                + "\n Bummers : " + getBummers()
                + "\n Play : " + getPlay();
    }
}