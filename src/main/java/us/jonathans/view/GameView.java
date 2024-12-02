package us.jonathans.view;

import us.jonathans.interface_adapter.cancel_match.CancelMatchController;
import us.jonathans.interface_adapter.make_player_move.MakePlayerMoveController;
import us.jonathans.interface_adapter.make_player_move.MakePlayerMoveViewModel;
import us.jonathans.interface_adapter.cancel_match.CancelMatchViewModel;
import us.jonathans.interface_adapter.start_game.StartGameViewModel;
import us.jonathans.interface_adapter.make_computer_move.MakeComputerMoveController;
import us.jonathans.interface_adapter.make_computer_move.MakeComputerMoveViewModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private final static String viewName = "Game";

    public GameView(
            StartGameViewModel startGameViewModel,
            MakePlayerMoveViewModel makePlayerMoveViewModel,
            MakePlayerMoveController makePlayerMoveController,
            MakeComputerMoveController makeComputerMoveController,
            MakeComputerMoveViewModel makeComputerMoveViewModel,
            CancelMatchViewModel cancelMatchViewModel,
            CancelMatchController cancelMatchController
    ) {
        setBorder(BorderFactory.createTitledBorder(viewName));
        setLayout(new GridLayout());
        add(new JMancalaPanel(
                this,
                startGameViewModel,
                makePlayerMoveViewModel,
                makePlayerMoveController,
                makeComputerMoveController,
                makeComputerMoveViewModel,
                cancelMatchViewModel,
                cancelMatchController
        ));
    }
}
