package com.example.retrofithw;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    /*
    Retrofit get annotation with our URL
    And our method that will return us the List of ContactList
    */
    @GET("/rest/v2/name/{cname}")
    Call<ResponseBody> getCountryName(@Path("cname") String country_name);
}
