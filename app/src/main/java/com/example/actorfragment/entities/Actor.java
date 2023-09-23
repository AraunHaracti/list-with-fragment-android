package com.example.actorfragment.entities;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class Actor {
    private String fullName;
    private Date dateOfBorn;
    private Drawable photoResource;

    public Actor(String fullName, Date dateOfBorn, Drawable photoResource) {
        this.fullName = fullName;
        this.dateOfBorn = dateOfBorn;
        this.photoResource = photoResource;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(Date dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public Drawable getPhotoResource() {
        return photoResource;
    }

    public void setPhotoResource(Drawable photoResource) {
        this.photoResource = photoResource;
    }
}
