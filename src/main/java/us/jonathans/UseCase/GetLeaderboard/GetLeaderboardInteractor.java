package us.jonathans.UseCase.GetLeaderboard;

import us.jonathans.InterfaceAdapters.GetLeaderboardPresenter;
import us.jonathans.Leaderboard;

public class GetLeaderboardInteractor implements GetLeaderboardInputBoundary {
    GetLeaderboardRepositoryInterface getLeaderboardRepository;
    GetLeaderboardOutputBoundary getLeaderboardPresenter;


    public GetLeaderboardInteractor(GetLeaderboardRepositoryInterface getLeaderboardRepository, GetLeaderboardOutputBoundary getLeaderboardOutputBoundary) {
        this.getLeaderboardRepository = getLeaderboardRepository;
        this.getLeaderboardPresenter = getLeaderboardOutputBoundary;
    }

    public Leaderboard getLeaderboard() {
        return getLeaderboardRepository.getLeaderboard();
    }

    public void execute(){
        GetLeaderboardOutputData getLeaderboardOutputData = new
                GetLeaderboardOutputData(getLeaderboardRepository.getLeaderboard());
        getLeaderboardPresenter.prepareUpdateScreen(getLeaderboardOutputData);
    }

}
