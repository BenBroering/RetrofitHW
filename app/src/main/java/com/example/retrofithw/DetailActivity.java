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

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

/***
 * Detail Activity that is launched when a list item is clicked.
 * It shows more info on the sport.
 */
public class DetailActivity extends AppCompatActivity {


    /**
     * Initializes the activity, filling in the data from the Intent.
     *
     * @param savedInstanceState Contains information about the saved state
     *                           of the activity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        // Initialize the views.
        TextView CountryTitle = findViewById(R.id.titleDetail);
        ImageView CountryImage = findViewById(R.id.CountryImageDetail);

        // Set the text from the Intent extra.
        CountryTitle.setText(getIntent().getStringExtra("title"));

        callAPI(CountryTitle.getText().toString());

        // Load the image using the Glide library and the Intent extra.
        Glide.with(this).load(getIntent().getIntExtra("image_resource",0))
                .into(CountryImage);


    }


    private void callAPI(String countryTitle){

        final ProgressDialog dialog;
        /**
         * Progress Dialog for User Interaction
         */
        dialog = new ProgressDialog(DetailActivity.this);
        dialog.setTitle("Getting JSON data");
        dialog.setMessage("Please wait...");
        dialog.show();

        ApiService api = RetroClient.getApiService();
        Call<ResponseBody> call = api.getCountryName(countryTitle);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Dismiss Dialog
                //dialog.dismiss();

                if (response.isSuccessful()) {
                    dialog.dismiss();
                    Log.d("RESTFUL DEBUG", call.toString());
                    try {
                        String json = response.body().string().toString();
                        json = json.substring(1,json.length()-1);
                        Gson gson = new Gson();
                        CountryJson classData = gson.fromJson(json, CountryJson.class);
                        Log.d("RESTFUL DEBUG", classData.getCname());
                        TextView mCountryDetails = findViewById(R.id.subTitleDetail);
                        mCountryDetails.setText(classData.getCountryDetails());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.dismiss();
                Log.d("RESTFUL DEBUG", call.request().toString());
                Log.d("RESTFUL DEBUG", t.getMessage());

            }
        });
    }
}