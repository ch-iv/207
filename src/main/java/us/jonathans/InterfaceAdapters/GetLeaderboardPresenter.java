package us.jonathans.InterfaceAdapters;

import us.jonathans.Leaderboard;
import us.jonathans.UseCase.GetLeaderboard.GetLeaderboardOutputBoundary;
import us.jonathans.UseCase.GetLeaderboard.GetLeaderboardOutputData;

import java.util.ArrayList;
import java.util.Map;

public class GetLeaderboardPresenter implements GetLeaderboardOutputBoundary {
    GetLeaderboardViewModel getLeaderboardViewModel;

    public GetLeaderboardPresenter(GetLeaderboardViewModel getLeaderboardViewModel) {
        this.getLeaderboardViewModel = getLeaderboardViewModel;
    }

    @Override
    public Map<String, ArrayList<Integer>> getLeaderboardData(Leaderboard leaderboard) {
        return leaderboard.getData();
    }

    public void prepareUpdateScreen(GetLeaderboardOutputData getLeaderboardOutputData) {

        getLeaderboardViewModel.firePropertyChanged("leaderboard",
                this.getLeaderboardData(getLeaderboardOutputData.getLeaderboard()));
    }

}
