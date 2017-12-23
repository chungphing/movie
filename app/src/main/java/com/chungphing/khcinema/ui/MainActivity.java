/*
 * Copyright 2017, The Android Open Source Project
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

package com.chungphing.khcinema.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.chungphing.khcinema.R;
import com.chungphing.khcinema.model.Movie;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Add product list fragment if this is first creation
        if (savedInstanceState == null) {
            MovieListFragment fragment = new MovieListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, MovieListFragment.TAG).commit();
        }
    }

    /** Shows the product detail fragment */
    public void show(Movie movie) {

        MovieFragment movieFragment = MovieFragment.forMovie(movie.getId());

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("movie")
                .replace(R.id.fragment_container,
                        movieFragment, null).commit();
    }
}