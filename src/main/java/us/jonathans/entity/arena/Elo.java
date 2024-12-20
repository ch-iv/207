package us.jonathans.entity.arena;

public class Elo {
    public static double Probability(double rating1, double rating2) {
        return 1.0 / (1 + Math.pow(10, (rating1 - rating2) / 400.0));
    }

    public static EloResult EloRating(double Ra, double Rb, int K, double outcome) {
        double Pb = Probability(Ra, Rb);
        double Pa = Probability(Rb, Ra);

        Ra = Ra + K * (outcome - Pa);
        Rb = Rb + K * ((1 - outcome) - Pb);

        return new EloResult(Ra, Rb);
    }

}

