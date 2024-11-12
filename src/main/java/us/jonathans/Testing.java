package us.jonathans;

import us.jonathans.DataAccess.LeaderboardRepository;
import us.jonathans.InterfaceAdapters.GetLeaderboardController;
import us.jonathans.InterfaceAdapters.GetLeaderboardPresenter;
import us.jonathans.InterfaceAdapters.GetLeaderboardViewModel;
import us.jonathans.UseCase.GetLeaderboard.GetLeaderboardInteractor;
import us.jonathans.UseCase.GetLeaderboard.GetLeaderboardOutputBoundary;
import us.jonathans.View.GetLeaderboardView;

import java.io.IOException;

public class Testing {
    public static void main(String[] args) throws IOException {
        LeaderboardRepository repository = new LeaderboardRepository();
        GetLeaderboardViewModel viewModel = new GetLeaderboardViewModel("leaderboard");
        GetLeaderboardOutputBoundary presenter = new GetLeaderboardPresenter(viewModel);
        GetLeaderboardInteractor interactor = new GetLeaderboardInteractor(repository, presenter);
        GetLeaderboardController controller = new GetLeaderboardController(interactor);
        GetLeaderboardView view = new GetLeaderboardView(viewModel);

        controller.execute();


    }

}
