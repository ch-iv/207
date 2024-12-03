package us.jonathans.entity.rule;

public enum MancalaSide {
    PLAYER1{
        @Override
        public MancalaSide opposite() {
            return PlAYER2;
        }

        @Override
        public MancalaHole getGoal() {
            return MancalaHole.G;
        }

        @Override
        public MancalaHole[] getHoles() {
            return new MancalaHole[] {
                    MancalaHole.A,
                    MancalaHole.B,
                    MancalaHole.C,
                    MancalaHole.D,
                    MancalaHole.E,
                    MancalaHole.F,
            };
        }
    },

    PlAYER2{
        @Override
        public MancalaSide opposite() {
            return PLAYER1;
        }

        @Override
        public MancalaHole getGoal() {
            return MancalaHole.g;
        }

        @Override
        public MancalaHole[] getHoles() {
            return new MancalaHole[] {
                    MancalaHole.a,
                    MancalaHole.b,
                    MancalaHole.c,
                    MancalaHole.d,
                    MancalaHole.e,
                    MancalaHole.f,
            };
        }
    };

    public abstract MancalaSide opposite();

    public abstract  MancalaHole getGoal();

    public abstract MancalaHole[] getHoles();
}
