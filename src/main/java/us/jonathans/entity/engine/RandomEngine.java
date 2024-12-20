package us.jonathans.entity.engine;

import us.jonathans.entity.rule.*;

import java.util.Random;
import java.util.Set;

public class RandomEngine implements Engine {
    final Random r = new Random();
    final MancalaRuleSet ruleSet = new JonathanMancalaRuleSet();

    @Override
    public MancalaHole findBestMove(MancalaBoard board, MancalaSide player) {
        Set<MancalaHole> legalMoves = ruleSet.getLegalMoves(board, player);
        return legalMoves.stream().toList().get(r.nextInt(0, legalMoves.size()));
    }
}
