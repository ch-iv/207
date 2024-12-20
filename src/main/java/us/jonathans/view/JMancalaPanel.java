package us.jonathans.view;

import us.jonathans.app.Config;
import us.jonathans.entity.rendering.geometry.Obj2;
import us.jonathans.entity.rendering.geometry.Vec2;
import us.jonathans.entity.rendering.geometry.Align;
import us.jonathans.entity.rendering.sprite.Hole;
import us.jonathans.entity.rendering.sprite.SquareHole;
import us.jonathans.entity.rendering.sprite.Stone;
import us.jonathans.entity.rendering.sprite.StoneColors;
import us.jonathans.entity.rule.MancalaBoard;
import us.jonathans.entity.rule.MancalaHole;
import us.jonathans.entity.rule.MancalaSide;
import us.jonathans.interface_adapter.make_player_move.MakePlayerMoveController;
import us.jonathans.interface_adapter.make_player_move.MakePlayerMoveState;
import us.jonathans.interface_adapter.make_player_move.MakePlayerMoveViewModel;
import us.jonathans.interface_adapter.cancel_match.CancelMatchState;
import us.jonathans.interface_adapter.cancel_match.CancelMatchViewModel;
import us.jonathans.interface_adapter.start_game.StartGameState;
import us.jonathans.interface_adapter.start_game.StartGameViewModel;
import us.jonathans.interface_adapter.make_computer_move.MakeComputerMoveController;
import us.jonathans.interface_adapter.make_computer_move.MakeComputerMoveState;
import us.jonathans.interface_adapter.make_computer_move.MakeComputerMoveViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Random;

public class JMancalaPanel extends JPanel implements MouseMotionListener, PropertyChangeListener {
    private final String viewName = "mancala_panel";
    int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    final ArrayList<Hole> holes = new ArrayList<>();
    final ArrayList<Stone> stones = new ArrayList<>();
    private SquareHole topHole;
    private SquareHole bottomHole;
    private Container parent;
    private Dimension lastSize;
    private int cellHeight = 0;
    private final int initialRandomSeed = new Random().nextInt();

    public JMancalaPanel(
            Container frame
    ) {
        super();
        this.parent = frame;
        this.addMouseMotionListener(this);
        this.setDoubleBuffered(true);
        lastSize = this.getPreferredSize();
        initSprites();
    }

    public void setBoard(int[] board) {
        this.board = fixBoard(board);
        initSprites();
        repaint();
    }

    private void initSprites() {
        this.stones.clear();
        this.holes.clear();

        cellHeight = (getPreferredSize().height - 50) / 8;
        int holeRadius = (int) (cellHeight * 0.9 / 2);
        int marginX = 15;

        int col1X = getPreferredSize().width / 2 - holeRadius - marginX;
        int col2X = getPreferredSize().width / 2 + holeRadius + marginX;

        for (int i = 0; i < 6 ; i++) {
            holes.add(new Hole(
                    col1X,
                    (i + 1) * cellHeight + cellHeight / 2,
                    holeRadius * 2,
                    holeRadius * 2,
                    Align.CENTER,
                    i
            ));
        }
        for (int i = 12; i > 6; i--) {
            holes.add(new Hole(
                    col2X,
                    (i-7+1) * (cellHeight) + cellHeight / 2,
                    holeRadius * 2,
                    holeRadius * 2,
                    Align.CENTER,
                    i
            ));
        }
        Random r = new Random(initialRandomSeed);

        int stoneRadius = 15;
        holes.forEach(hole -> {
            int nStones = board[hole.getId()];
            for (int i = 0; i < 20; i++) {
                Vec2 stonePos = pointInsideCircle(hole, stoneRadius, r);
                Color color = StoneColors.getRandom(r);

                if (i >= nStones) {
                    continue;
                }

                stones.add(
                        new Stone(
                                stonePos.x,
                                stonePos.y,
                                stoneRadius * 2,
                                stoneRadius * 2,
                                Align.CENTER,
                                color
                        )
                );
            }
        });

        this.topHole = new SquareHole(
                getPreferredSize().width / 2,
                cellHeight / 2,
                cellHeight * 2,
                cellHeight,
                Align.CENTER,
                -1

        );

        this.bottomHole = new SquareHole(
                getPreferredSize().width / 2,
                cellHeight * 7 + cellHeight / 2,
                cellHeight * 2,
                cellHeight,
                Align.CENTER,
                -1
        );

        int nStones = board[13];
        for (int i = 0; i < 20; i++) {
            Vec2 stonePos = pointInsideSquare(topHole, stoneRadius, r);
            Color color = StoneColors.getRandom(r);
            if (i >= nStones) {
                continue;
            }
            stones.add(
                    new Stone(
                            stonePos.x,
                            stonePos.y,
                            stoneRadius * 2,
                            stoneRadius * 2,
                            Align.CENTER,
                            color
                    )
            );
        }

        nStones = board[6];
        for (int i = 0; i < 20; i++) {
            Vec2 stonePos = pointInsideSquare(bottomHole, stoneRadius, r);
            Color color = StoneColors.getRandom(r);
            if (i >= nStones) {
                continue;
            }
            stones.add(
                    new Stone(
                            stonePos.x,
                            stonePos.y,
                            stoneRadius * 2,
                            stoneRadius * 2,
                            Align.CENTER,
                            color
                    )
            );
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        if (lastSize.height != getPreferredSize().height || lastSize.width != getPreferredSize().width) {
            initSprites();
            lastSize = getPreferredSize();
        }

        Point mousePoint = this.getMousePosition();
        holes.forEach(hole -> {
            if (mousePoint != null) {
                hole.setHovered(hole.contains(mousePoint.x, mousePoint.y));
            } else {
                hole.setHovered(false);
            }
            hole.draw(g);
        });
        topHole.draw(g);
        bottomHole.draw(g);

        if (Config.PAINT_STONES) {
            stones.forEach(stone -> {
                stone.draw(g);
            });
        }

        if (Config.PAINT_STONE_COUNT) {
            paintStoneCount(graphics2D);
        }
    }

    private void paintStoneCount(Graphics2D graphics2D) {
        int fontSize = cellHeight / 5;
        graphics2D.setFont(new Font("Arial", Font.PLAIN, fontSize));
        graphics2D.setColor(Color.WHITE);

        holes.forEach(hole -> {
            int nStones = board[hole.getId()];
            int x = 0;
            if (hole.getId() < 6) {
                x = hole.cx() - hole.radius() - fontSize;
            } else {
                x = hole.cx() + hole.radius() + 5;
            }
            graphics2D.drawString(Integer.toString(nStones), x, hole.cy() + fontSize / 2);
        });

        graphics2D.drawString(
                Integer.toString(board[13]),
                topHole.cx() + topHole.radius() + 6,
                topHole.cy() + fontSize / 2
        );
        graphics2D.drawString(
                Integer.toString(board[6]),
                bottomHole.cx() - bottomHole.radius() - fontSize,
                bottomHole.cy() + fontSize / 2
        );
    }

    private Vec2 pointInsideCircle(Obj2 obj, int radius, Random r) {
        double radians = 2.0d * Math.PI * r.nextDouble(1.0d);
        double deviation = (obj.radius() - radius) * Math.sqrt(r.nextDouble(1.0d));
        return new Vec2(
                (int) (obj.cx() + deviation * Math.cos(radians)),
                (int) (obj.cy() + deviation * Math.sin(radians))
        );
    }

    private Vec2 pointInsideSquare(Obj2 obj, int radius, Random r) {
        double radians = 2.0d * Math.PI * r.nextDouble(1.0d);
        double deviation = (obj.height() / 2 - radius) * Math.sqrt(r.nextDouble(1.0d));
        return new Vec2(
                (int) (obj.cx() + deviation * Math.cos(radians)),
                (int) (obj.cy() + deviation * Math.sin(radians))
        );
    }

    @Override
    public Dimension getPreferredSize() {
        return this.parent.getSize();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof StartGameState state) {
            if (state.isSuccessful()) {
                this.board = state.getBoard();
                initSprites();
                repaint();
            }
        } else if (evt.getNewValue() instanceof MakePlayerMoveState makePlayerMoveState) {
            if (makePlayerMoveState.getSuccess()) {
                this.board = fixBoard(makePlayerMoveState.getBoard());
                initSprites();
                repaint();
            }
        } else if(evt.getNewValue() instanceof MakeComputerMoveState state) {
            this.board = fixBoard(state.getBoard());
            initSprites();
            repaint();
        } else if (evt.getNewValue() instanceof CancelMatchState) {
            this.board = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            initSprites();
            repaint();
        }
    }

    private int[] fixBoard(int[] board) {
        return new int[] {
                board[7],
                board[8],
                board[9],
                board[10],
                board[11],
                board[12],
                board[13],
                board[5],
                board[4],
                board[3],
                board[2],
                board[1],
                board[0],
                board[6]
        };
    }
}
