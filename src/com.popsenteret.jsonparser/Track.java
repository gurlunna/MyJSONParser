package com.popsenteret.jsonparser;

/**
 * Created by IntelliJ IDEA.
 * User: popit
 * Date: 28.10.11
 * Time: 10:10
 * To change this template use File | Settings | File Templates.
 */
public class Track {
    private int ID;
    private int artistID;
    private String artistName;
    private String thumbURL;
    private String thumb2URL;
    private String imageURL;
    private String imageXLURL;
    private String URL;
    private String title;
    private String length;
    private int likes;
    private String[] tags;


    public String getImageName(String url) {
        String[] name = url.split("/");
        int i = name.length;
        return name[i - 1];
    }

    public String getImageNameWithText(String url) {
        String[] name = url.split("/");
        int i = name.length;
        String s = name[i - 1];
        String[] str = s.split("[.]");
        return str[0] + "_text.jpg";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getThumbURL() {
        return thumbURL;
    }

    public void setThumbURL(String thumbURL) {
        this.thumbURL = thumbURL;
    }

    public String getThumb2URL() {
        return thumb2URL;
    }

    public void setThumb2URL(String thumb2URL) {
        this.thumb2URL = thumb2URL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageXLURL() {
        return imageXLURL;
    }

    public void setImageXLURL(String imageXLURL) {
        this.imageXLURL = imageXLURL;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
