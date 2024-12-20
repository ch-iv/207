package us.jonathans.entity.match;

import us.jonathans.entity.rule.Game;
import us.jonathans.entity.rule.MancalaSide;
import java.util.UUID;

public class EngineMatch {
    final UUID playerId;
    final String engineId;
    final Game game = new Game();
    final MancalaSide engineSide = MancalaSide.PLAYER2;
    final MancalaSide playerSide = MancalaSide.PLAYER1;

    public EngineMatch(UUID playerId, String engineId) {
        this.playerId = playerId;
        this.engineId = engineId;
    }

    public Game getGame() {
        return game;
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public String getEngineId() {
        return engineId;
    }

    public MancalaSide getEngineSide() {
        return engineSide;
    }

    public MancalaSide getPlayerSide() {
        return playerSide;
    }
}
