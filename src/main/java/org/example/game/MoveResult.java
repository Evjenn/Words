package org.example.game;

public class MoveResult {

    private final boolean valid;
    private final String message;
    private final String city;
    private final Character nextLetter;

    public MoveResult(boolean valid, String message, String city, Character nextLetter) {
        this.valid = valid;
        this.message = message;
        this.city = city;
        this.nextLetter = nextLetter;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

    public String getCity() {
        return city;
    }

    public Character getNextLetter() {
        return nextLetter;
    }

}
