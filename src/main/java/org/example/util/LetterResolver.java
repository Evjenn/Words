package org.example.util;

import java.util.Set;
import org.example.data.CityRepository;
import org.example.game.LetterResult;

public class LetterResolver {

    private static final Set<Character> IGNORED = Set.of('ї', 'и', 'й', 'ь','ц');
    private final CityRepository repository;

    public LetterResolver(CityRepository repository) {
        this.repository = repository;
    }

    public LetterResult resolveNextLetter(String city, Set<String> used) {
        if (city == null || city.isBlank()) {
            return new LetterResult(null, "");
        }

        StringBuilder message = new StringBuilder();
        city = city.toLowerCase();

        for (int i = city.length() - 1; i >= 0; i--) {
            char ch = city.charAt(i);
            boolean isValidChar = Character.isLetter(ch) && !IGNORED.contains(ch);

            if (isValidChar) {
                boolean hasCities = repository.hasAvailableCities(ch, used);

                if (hasCities) {
                    return new LetterResult(ch, message.toString());
                } else {
                    message.append("Міста на літеру ")
                            .append(Character.toUpperCase(ch))
                            .append(" закінчилися.\n");
                }
            }
        }

        return new LetterResult(city.charAt(0), message.toString());
    }
}
