package use_case.make_player_move;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import us.jonathans.data_access.match.MatchDataAccessInterface;
import us.jonathans.entity.match.EngineMatch;
import us.jonathans.entity.rule.Game;
import us.jonathans.entity.rule.MancalaHole;
import us.jonathans.entity.rule.MancalaMove;
import us.jonathans.entity.rule.MancalaSide;
import us.jonathans.observable.publisher.MatchEndPublisher;
import us.jonathans.use_case.make_player_move.MakePlayerMoveInputData;
import us.jonathans.use_case.make_player_move.MakePlayerMoveInteractor;
import us.jonathans.use_case.make_player_move.MakePlayerMoveOutputBoundary;
import us.jonathans.use_case.make_player_move.MakePlayerMoveOutputData;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MakePlayerMoveInteractorTest {
    @Mock
    private MatchDataAccessInterface matchDataAccessObject;
    @Mock
    private MakePlayerMoveOutputBoundary makePlayerMovePresenter;

    @Mock
    private MatchEndPublisher matchEndPublisher;

    @InjectMocks
    private MakePlayerMoveInteractor makePlayerMoveInteractor;


    @Test
    void testInit() {
        assertNotNull(makePlayerMoveInteractor);
    }

    @Test
    void updateBoardTest() {
        when(matchDataAccessObject.getCurrentMatch()).thenReturn(new EngineMatch(UUID.randomUUID(), "asdf"));

        makePlayerMoveInteractor.execute(
                new MakePlayerMoveInputData(new MancalaMove(MancalaSide.PLAYER1, MancalaHole.A))
        );

        verify(makePlayerMovePresenter).presentUpdatedBoard(any(MakePlayerMoveOutputData.class));
    }

    @Test
    void gameIsOverTest() {
        when(matchDataAccessObject.getCurrentMatch()).thenReturn(new EngineMatch(UUID.randomUUID(), "asdf"));
        Game game = matchDataAccessObject.getCurrentMatch().getGame();
        while (!game.isGameOver()) {
            for (MancalaSide mancalaSide: new MancalaSide[]{MancalaSide.PLAYER1, MancalaSide.PlAYER2}) {
                for (MancalaHole hole: mancalaSide.getHoles()) {
                    makePlayerMoveInteractor.execute(
                            new MakePlayerMoveInputData(new MancalaMove(mancalaSide, hole))
                    );
                }
            }
        }
        // verify(matchEndPublisher).publish(any());
    }
}