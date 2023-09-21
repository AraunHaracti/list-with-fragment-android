package com.example.actorfragment;

import java.util.Date;

public class Actor {
    private String fullName;
    private Date dateOfBorn;
    private int photoResource;

    public Actor(String fullName, Date dateOfBorn, int photoResource) {
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

    public int getPhotoResource() {
        return photoResource;
    }

    public void setPhotoResource(int photoResource) {
        this.photoResource = photoResource;
    }
}
