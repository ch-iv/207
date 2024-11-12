package us.jonathans.InterfaceAdapters;

import us.jonathans.UseCase.GetLeaderboard.GetLeaderboardInputBoundary;

public class GetLeaderboardController {
    private final GetLeaderboardInputBoundary getLeaderboardInteractor;


    public GetLeaderboardController(GetLeaderboardInputBoundary getLeaderboardInteractor) {
        this.getLeaderboardInteractor = getLeaderboardInteractor;
    }

    public void execute(){
        getLeaderboardInteractor.execute();
    }
}
