package org.example.game;

public class GameState {

    private final int totalMoves;
    private int currentMove;
    private int playerScore;
    private int computerScore;
    private String lastCity;
    private boolean gameOver;
    private boolean playerGaveUp;

    public GameState(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public int getCurrentMove() {
        return currentMove;
    }

    public void incrementMove() {
        currentMove++;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public void addPlayerScore(int value) {
        playerScore += value;
    }

    public void addComputerScore(int value) {
        computerScore += value;
    }

    public String getLastCity() {
        return lastCity;
    }

    void setLastCity(String city) {
        this.lastCity = city;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isPlayerGaveUp() {
        return playerGaveUp;
    }

    public void setPlayerGaveUp(boolean playerGaveUp) {
        this.playerGaveUp = playerGaveUp;
    }
}
