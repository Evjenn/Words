package org.example.game;

public enum GameMode {
    FIXED_20(20),
    FIXED_40(40),
    FIXED_80(80),
    UNTIL_END(Integer.MAX_VALUE);
    private final int moves;

    GameMode(int moves) {
        this.moves = moves;
    }

    public int getMoves() {
        return moves;
    }
}
