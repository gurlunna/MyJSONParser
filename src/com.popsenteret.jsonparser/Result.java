package com.popsenteret.jsonparser;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: popit
 * Date: 28.10.11
 * Time: 10:16
 * To change this template use File | Settings | File Templates.
 */
public class Result {
    private String listName;
    private int genreID;
    private int numberOfTracks;
    private int skip;
    private int totalTracks;
    private List<Track> tracks;

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.totalTracks = totalTracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
