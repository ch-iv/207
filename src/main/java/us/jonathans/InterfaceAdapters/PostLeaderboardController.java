package us.jonathans.InterfaceAdapters;

import us.jonathans.Leaderboard;
import us.jonathans.UseCase.PostLeaderboard.PostLeaderboardInteractor;

public class PostLeaderboardController {
    private final PostLeaderboardInteractor postLeaderboardInteractor;


    public PostLeaderboardController(PostLeaderboardInteractor postLeaderboardInteractor) {
        this.postLeaderboardInteractor = postLeaderboardInteractor;
    }

    public void execute(Leaderboard leaderboard) {
        postLeaderboardInteractor.postLeaderboard(leaderboard);
    }
}
