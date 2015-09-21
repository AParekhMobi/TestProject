package com.mobiquity.testapp.testproject.com.mobiquity.testapp.testproject.models;

/**
 * Created by amitparekh on 18/09/15.
 */

import com.google.gson.annotations.Expose;

import java.io.Serializable;


public class Artist implements Serializable{

    @Expose
    private long id;
    @Expose
    private String genres;
    @Expose
    private String picture;
    @Expose
    private String name;
    @Expose
    private String description;

    /**
     *
     * @return
     * The id
     */
    public long getId() {
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
     * The genres
     */
    public String getGenres() {
        return genres;
    }

    /**
     *
     * @param genres
     * The genres
     */
    public void setGenres(String genres) {
        this.genres = genres;
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

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}