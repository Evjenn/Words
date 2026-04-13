package org.example.game;

import java.util.HashSet;
import java.util.Set;
import org.example.data.CityRepository;
import org.example.util.LetterResolver;
import org.example.util.ScoreUtil;

public class GameService {

    private final GameState state;
    private final CityRepository repository;
    private final LetterResolver resolver;
    private final Set<String> usedCities = new HashSet<>();

    public GameService(GameMode mode) {
        this.repository = new CityRepository();
        this.resolver = new LetterResolver(repository);
        this.state = new GameState(mode.getMoves());
    }

    public GameState getState() {
        return state;
    }

    public MoveResult playerMove(String input) {

        if (input == null || input.isBlank()) {
            return new MoveResult(false, "Введіть назву міста:", null, null);
        }

        if (state.isGameOver()) {
            return new MoveResult(false, "Кінець ігри!", null, null);
        }

        String city = input.trim().toLowerCase();
        if (!repository.exists(city)) {
            return new MoveResult(false, "Місто не існує", null, null);
        }

        if (usedCities.contains(city)) {
            return new MoveResult(false, "Це місто вже оголошено", null, null);
        }
        //first move
        if (state.getLastCity() == null) {
            savePlayerMove(city);
            return new MoveResult(true, "", city, null);
        }
        LetterResult letterResult = resolver.resolveNextLetter(state.getLastCity(), usedCities);
        Character expected = letterResult.letter();

        if (city.charAt(0) != expected) {
            return new MoveResult(false, "Невірна літера", null, expected);
        }

        savePlayerMove(city);
        return new MoveResult(true, letterResult.message(), city, expected);

    }

    private void savePlayerMove(String city) {
        usedCities.add(city);
        state.setLastCity(city);
        state.addPlayerScore(ScoreUtil.countLetters(city));
        state.incrementMove();
    }

    private void saveCompMove(String city) {
        usedCities.add(city);
        state.setLastCity(city);
        state.addComputerScore(ScoreUtil.countLetters(city));
        state.incrementMove();
    }

    public MoveResult computerMove() {

        LetterResult letterResult = resolver.resolveNextLetter(state.getLastCity(), usedCities);
        Character letter = letterResult.letter();
        String message = letterResult.message();
        String city = repository.getRandomCity(letter, usedCities);

        if (city == null) {
            state.setGameOver(true);
            return new MoveResult(false, "Комп'ютер не має ходу", null, letter);
        }
        saveCompMove(city);
        LetterResult next = resolver.resolveNextLetter(city, usedCities);

        return new MoveResult(true, message, city, next.letter());
    }

    public boolean isGameOver() {
        return state.isGameOver() || state.getCurrentMove() >= state.getTotalMoves();
    }

    public void giveUpService() {
        state.setGameOver(true);
        state.setPlayerGaveUp(true);
    }

    public String getWinner() {
        if (state.isPlayerGaveUp()) {
            return "Ви здались. Переміг комп'ютер!";
        }
        if (state.getPlayerScore() > state.getComputerScore()) {
            return "Ігрок переміг!";
        }
        if (state.getPlayerScore() < state.getComputerScore()) {
            return "Комп'ютер переміг!";
        } else {
            return "Нічия!";
        }
    }

}
