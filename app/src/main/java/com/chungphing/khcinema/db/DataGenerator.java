package com.chungphing.khcinema.db;

import com.chungphing.khcinema.db.entities.MovieEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by chungphing
 * DataGenerator: generating random data for testing purpose
 */

class DataGenerator {

    private static final String[] FIRST = new String[]{
            "Special edition", "New", "Cheap", "Quality", "Used"};
    private static final String[] SECOND = new String[]{
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"};
    private static final String[] DESCRIPTION = new String[]{
            "is finally here", "is recommended by Stan S. Stanman",
            "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine"};
    private static final String[] COMMENTS = new String[]{
            "Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6",
    };

    static List<MovieEntity> generateProducts() {
        List<MovieEntity> movies = new ArrayList<>(FIRST.length * SECOND.length);
        Random rnd = new Random();
        for (String aFIRST : FIRST) {
            for (int j = 0; j < SECOND.length; j++) {
                MovieEntity movie = new MovieEntity();
                movie.setName(aFIRST + " " + SECOND[j]);
                movie.setDescription(movie.getName() + " " + DESCRIPTION[j]);
                movies.add(movie);
            }
        }
        return movies;
    }


}
