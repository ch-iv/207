package us.jonathans.UseCase.GetLeaderboard;

import us.jonathans.Leaderboard;

import java.util.ArrayList;
import java.util.Map;

public class GetLeaderboardOutputData {
    private final Leaderboard leaderboard;

    public GetLeaderboardOutputData(Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }
}
