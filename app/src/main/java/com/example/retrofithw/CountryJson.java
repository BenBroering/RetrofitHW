package com.example.retrofithw;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CountryJson {
    public String getCountryDetails(){
        String details = "Name: " + getCname() + "\n" +
                         "Region: " + getRegion() + "\n" +
                         "Population: " + getPopulation() + "\n" +
                         "Capital: " + getCapital();
        return details;
    }

    public String getCname() {
        return cname;
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

    @SerializedName("name")
    private String cname;
    @SerializedName("capital")
    private String capital;
    @SerializedName("region")
    private String region;
    @SerializedName("population")
    private String population;
}
