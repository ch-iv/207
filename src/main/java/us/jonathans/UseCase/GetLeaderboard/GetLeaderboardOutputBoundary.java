package us.jonathans.UseCase.GetLeaderboard;

import us.jonathans.Leaderboard;

import java.util.ArrayList;
import java.util.Map;

public interface GetLeaderboardOutputBoundary {
    Map<String, ArrayList<Integer>> getLeaderboardData(Leaderboard leaderboard);

    void prepareUpdateScreen(GetLeaderboardOutputData getLeaderboardOutputData);
}
