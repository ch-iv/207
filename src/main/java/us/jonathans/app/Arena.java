package us.jonathans.app;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatMonokaiProIJTheme;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import us.jonathans.entity.arena.ArenaPlayer;
import us.jonathans.entity.arena.Elo;
import us.jonathans.entity.arena.EloResult;
import us.jonathans.entity.engine.MancalaMinimax;
import us.jonathans.entity.engine.RandomEngine;
import us.jonathans.entity.rule.*;
import us.jonathans.view.JMancalaPanel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Arena {
    private final JFrame frame = new JFrame("Arena");
    private List<ArenaPlayer> players = Collections.synchronizedList(new ArrayList<>());
    private List<ArenaPlayer> allPlayers = new ArrayList<>();
    private JFreeChart chart = createChart(createDataset());
    private JPanel chartPanel = createEloPanel();

    public Arena() {
        FlatLaf.setup(new FlatMonokaiProIJTheme());
        JPanel mainView = new JPanel();
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Game view", mainView);
        tabbedPane.addTab("Elo Chart", chartPanel);



        JMancalaPanel jMancalaPanel = new JMancalaPanel(mainView);
        mainView.add(jMancalaPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(tabbedPane);

        allPlayers.add(new ArenaPlayer(new RandomEngine(), "r1"));
        allPlayers.add(new ArenaPlayer(new RandomEngine(), "r2"));
        allPlayers.add(new ArenaPlayer(new MancalaMinimax(new JonathanMancalaRuleSet(), 8), "d8"));
        allPlayers.add(new ArenaPlayer(new MancalaMinimax(new JonathanMancalaRuleSet(), 7), "d7"));
        allPlayers.add(new ArenaPlayer(new MancalaMinimax(new JonathanMancalaRuleSet(), 6), "d6"));
        allPlayers.add(new ArenaPlayer(new MancalaMinimax(new JonathanMancalaRuleSet(), 5), "d5"));

        allPlayers.forEach(player -> {
            players.add(player);
        });

        Thread.startVirtualThread(() -> {
           while (true) {
               if (players.size() >= 2) {
                   Collections.shuffle(players);
                   ArenaPlayer player = players.removeFirst();
                   ArenaPlayer player2 = players.removeFirst();
                   if (player.getLastOpponent() == player2) {
                        players.add(player);
                        players.add(player2);
                        continue;
                   } else {
                       startMatch(player, player2, jMancalaPanel);
                   }
               }
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
        });
    }

    private void startMatch(ArenaPlayer arenaPlayer1, ArenaPlayer arenaPlayer2, JMancalaPanel jMancalaPanel) {
        Thread.startVirtualThread(() -> {
            Game game = new Game();
            SwingUtilities.invokeLater(() -> {
                jMancalaPanel.setBoard(game.getBoard().asArray());
            });
            while (!game.isGameOver()) {
                if (game.getCurrentSide() == MancalaSide.PLAYER1) {
                    game.makeMove(arenaPlayer1.getEngine().findBestMove(game.getBoard(), MancalaSide.PLAYER1));
                } else {
                    game.makeMove(arenaPlayer2.getEngine().findBestMove(game.getBoard(), MancalaSide.PLAYER2));
                }
                SwingUtilities.invokeLater(() -> {
                    jMancalaPanel.setBoard(game.getBoard().asArray());
                });
            }

            int k = 32;
            double outcome = 1;
            switch (game.getWinner()) {
                case PLAYER1:
                    outcome = 1;
                    break;
                case PLAYER2:
                    outcome = 0;
                    break;
                case DRAW:
                    outcome = 0.5;
                    break;
            }
            EloResult newElo = Elo.EloRating(arenaPlayer1.getRating(), arenaPlayer2.getRating(), k, outcome);
            arenaPlayer1.setRating(newElo.first());
            arenaPlayer2.setRating(newElo.second());
            arenaPlayer1.setLastOpponent(arenaPlayer2);
            arenaPlayer2.setLastOpponent(arenaPlayer1);
            players.add(arenaPlayer1);
            players.add(arenaPlayer2);
            chart.getXYPlot().setDataset(createDataset());
//            System.out.println(Double.toString(outcome) + ": " + arenaPlayer1.toString() + " " + Double.toString(arenaPlayer1.getRating()) +
//                    " - " + arenaPlayer2.toString() + " " +  Double.toString(arenaPlayer2.getRating()));
        });
    }

    public void run() {
        frame.setVisible(true);
    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Engine Ratings",
                "Date",
                "Elo",
                dataset);

        chart.setBackgroundPaint(Color.WHITE);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.LIGHT_GRAY);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(true);
            renderer.setDefaultShapesFilled(true);
            renderer.setDrawSeriesLineAsPath(true);
        }

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));

        return chart;

    }


    private XYDataset createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        allPlayers.forEach(player -> {
            TimeSeries ts = new TimeSeries(player.toString());
            player.getRatingHistory().forEach(ratingMark -> {
                ts.addOrUpdate(new FixedMillisecond(ratingMark.date()), ratingMark.rating());
            });
            dataset.addSeries(ts);
        });
        return dataset;
    }

    public JPanel createEloPanel() {
        ChartPanel panel = new ChartPanel(chart, false);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
        return panel;
    }
}
