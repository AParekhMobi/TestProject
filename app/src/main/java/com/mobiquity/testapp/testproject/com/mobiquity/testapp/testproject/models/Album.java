package com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models;

/**
 * Created by amitparekh on 18/09/15.
 */

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Album implements Serializable{

    @Expose
    private long id;
    @Expose
    private long artistId;
    @Expose
    private String title;
    @Expose
    private String type;
    @Expose
    private String picture;

    /**
     *
     * @return
     * The id
     */
    public double getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The artistId
     */
    public double getArtistId() {
        return artistId;
    }

    /**
     *
     * @param artistId
     * The artistId
     */
    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     *
     * @param picture
     * The picture
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

}