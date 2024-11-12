package us.jonathans.UseCase.PostLeaderboard;

import us.jonathans.Leaderboard;

public class PostLeaderboardInteractor {
    PostLeaderboardRepositoryInterface postLeaderboardRepository;

    public PostLeaderboardInteractor(PostLeaderboardRepositoryInterface postLeaderboardRepository) {
        this.postLeaderboardRepository = postLeaderboardRepository;
    }

    public void postLeaderboard(Leaderboard leaderboard){
        postLeaderboardRepository.postLeaderboard(leaderboard);
    }
}
