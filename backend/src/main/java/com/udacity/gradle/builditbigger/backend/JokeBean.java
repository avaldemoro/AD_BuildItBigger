package com.udacity.gradle.builditbigger.backend;

/** The object model for the data we are sending through endpoints */
public class JokeBean {

    private String mJoke;

    public String getJokeData() {
        return mJoke;
    }

    public void setJokeData(String joke) { mJoke = joke; }
}