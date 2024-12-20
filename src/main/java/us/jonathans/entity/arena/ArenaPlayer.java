package us.jonathans.entity.arena;

import us.jonathans.entity.engine.Engine;


public class ArenaPlayer extends Rated {
    private final Engine engine;
    private final String name;
    private ArenaPlayer lastOpponent;

    public ArenaPlayer(Engine engine, String name) {
        super(1000);
        this.engine = engine;
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public ArenaPlayer getLastOpponent() {
        return lastOpponent;
    }

    public void setLastOpponent(ArenaPlayer lastOpponent) {
        this.lastOpponent = lastOpponent;
    }

    public String toString() {
        return name;
    }
}
