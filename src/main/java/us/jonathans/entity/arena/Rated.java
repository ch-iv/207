package us.jonathans.entity.arena;

import java.util.ArrayList;
import java.util.Date;

public class Rated {
    private double rating;
    private final ArrayList<RatingMark> ratingHistory = new ArrayList<>();

    public Rated(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
        this.ratingHistory.add(new RatingMark(System.currentTimeMillis(), rating));
    }

    public ArrayList<RatingMark> getRatingHistory() {
        return ratingHistory;
    }
}
