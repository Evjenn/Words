package org.example.util;

public class WordsFormatter {

    private WordsFormatter() {
        /* This utility class should not be instantiated */
    }

    public static String format(String city) {

        if (city == null || city.isBlank()) {
            return city;
        }
        String[] words = city.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String[] parts = word.split("-");
            for (int i = 0; i < parts.length; i++) {
                if (!parts[i].isEmpty()) {
                    parts[i] = Character.toUpperCase(parts[i].charAt(0))
                            + parts[i].substring(1);
                }
            }
            result.append(String.join("-", parts)).append(" ");
        }
        return result.toString().trim();
    }
}
