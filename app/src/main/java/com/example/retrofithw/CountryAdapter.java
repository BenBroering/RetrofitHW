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

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the Country data.
 */
class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<Country> mCountryData;
    private Context mContext;

    /**
     * Constructor that passes in the Country data and the context.
     *
     * @param context Context of the application.
     * @param countryData ArrayList containing the Country data.
     */
    CountryAdapter(Context context, ArrayList<Country> countryData) {
        this.mCountryData = countryData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(CountryAdapter.ViewHolder holder,
                                 int position) {
        // Get current Country.
        Country currentCountry = mCountryData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentCountry);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mCountryData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView mCountryImage;
        private TextView mCountryDetails;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mCountryImage = itemView.findViewById(R.id.CountryImage);
            mCountryDetails = itemView.findViewById(R.id.subTitleDetail);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Country currentCountry){
            // Populate the textviews with data.
            mTitleText.setText(currentCountry.getTitle());
            mInfoText.setText(currentCountry.getInfo());


            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentCountry.getImageResource()).into(mCountryImage);
        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */
        @Override
        public void onClick(View view) {
            Country currentCountry = mCountryData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, DetailActivity.class);
            detailIntent.putExtra("title", currentCountry.getTitle());
            detailIntent.putExtra("image_resource",
                    currentCountry.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}