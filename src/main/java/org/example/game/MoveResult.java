package org.example.game;

public record MoveResult(boolean valid, String message, String city, Character nextLetter) {

}
