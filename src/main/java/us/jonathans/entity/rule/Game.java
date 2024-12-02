package us.jonathans.entity.rule;


public class Game {
    private final static MancalaSide startingSide = MancalaSide.PLAYER1;

    private final MancalaBoard board = new JonathanMancalaBoard(1);
    private final MancalaRuleSet ruleSet = new JonathanMancalaRuleSet();

    private MancalaSide currentSide = startingSide;
    private MancalaSide winner = null;
    private MancalaSide loser = null;

    public Boolean makeMove(MancalaHole hole) {
        // Can't make moves in a game that has already ended.
        if (isGameOver()) {
            return false;
        }

        // Mutates the board to make a move.
        final MoveResult moveResult = getRuleSet().makeMove(
                getBoard(),
                getCurrentSide(),
                hole
        );

        // Move was rejected by the rule set.
        if (!moveResult.isLegal()) {
            return false;
        }

        if (isGameOver()) {
            // Move made by the current side ended the game.
            // Not null since we checked the game to be over.
            final MancalaSide winner = getRuleSet().checkWin(getBoard());
            setWinner(winner);
            setLoser(winner.opposite());
        } else {
            if (!moveResult.movesAgain()) {
                // If the player doesn't get a second turn, flip the current side.
                setCurrentSide(getCurrentSide().opposite());
            }
        }

        return true;
    }

    public MancalaSide getCurrentSide() {
        return currentSide;
    }

    public boolean isGameOver() {
        return getRuleSet().isGameOver(getBoard());
    }

    public MancalaSide getWinner() {
        return winner;
    }

    public MancalaSide getLoser() {
        return loser;
    }

    public MancalaBoard getBoard() {
        return board;
    }

    public MancalaRuleSet getRuleSet() {
        return ruleSet;
    }

    public void setCurrentSide(MancalaSide currentSide) {
        this.currentSide = currentSide;
    }

    public void setWinner(MancalaSide winner) {
        this.winner = winner;
    }

    public void setLoser(MancalaSide loser) {
        this.loser = loser;
    }
}
