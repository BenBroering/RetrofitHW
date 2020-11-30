/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.retrofithw;

import com.google.gson.annotations.SerializedName;

/**
 * Data model for each row of the RecyclerView
 */
class Country {

    // Member variables representing the title and information about the sport.
    private String title;
    private String info;
    private final int imageResource;
    @SerializedName("name")
    private String name;
    @SerializedName("capital")
    private String capital;
    @SerializedName("region")
    private String region;
    @SerializedName("population")
    private String population;


    /**
     * Constructor for the Sport data model.
     *
     * @param title The name if the sport.
     * @param info Information about the sport.
     */
    public Country(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }

    /**
     * Gets the title of the sport.
     *
     * @return The title of the sport.
     */
    String getTitle() {
        return title;
    }

    /**
     * Gets the info about the sport.
     *
     * @return The info about the sport.
     */
    String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getPopulation() {
        return population;
    }

    public String getCountryDetails(){
        String details = "Name: " + getName() + "\n" +
                "Region: " + getRegion() + "\n" +
                "Population: " + getPopulation() + "\n" +
                "Capital: " + getCapital();
        return details;
    }
    public int getImageResource() {
        return imageResource;
    }

}