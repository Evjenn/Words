package org.example.game;

public class LetterResult {

    private final Character letter;
    private final String message;

    public LetterResult(Character letter, String message) {
        this.letter = letter;
        this.message = message;
    }

    public Character getLetter() {
        return letter;
    }

    public String getMessage() {
        return message;
    }

}
