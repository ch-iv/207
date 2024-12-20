package us.jonathans.app;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import us.jonathans.entity.engine.Engine;
import us.jonathans.entity.engine.MancalaMinimax;
import us.jonathans.entity.rule.*;
import us.jonathans.view.JMancalaPanel;

import javax.swing.*;

public class Arena {
    private final JFrame frame = new JFrame("Arena");

    public Arena() {
        FlatLaf.setup(new FlatMonokaiProIJTheme());
        JPanel mainView = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(mainView);

        JMancalaPanel jMancalaPanel = new JMancalaPanel(mainView);
        mainView.add(jMancalaPanel);

        Thread.startVirtualThread(() -> {
            while (true) {
                Game game = new Game();
                Engine engine1 = new MancalaMinimax(game.getRuleSet(), 4);
                Engine engine2 = new MancalaMinimax(game.getRuleSet(), 4);
                SwingUtilities.invokeLater(() -> {
                    jMancalaPanel.setBoard(game.getBoard().asArray());
                });
                while (!game.isGameOver()) {
                    if (game.getCurrentSide() == MancalaSide.PLAYER1) {
                        game.makeMove(engine1.findBestMove(game.getBoard(), MancalaSide.PLAYER1));
                    } else {
                        game.makeMove(engine2.findBestMove(game.getBoard(), MancalaSide.PlAYER2));
                    }
                    SwingUtilities.invokeLater(() -> {
                        jMancalaPanel.setBoard(game.getBoard().asArray());
                    });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(game.getWinner());
            }
        });
    }

    public void run() {
        frame.setVisible(true);
    }
}
