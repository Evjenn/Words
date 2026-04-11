package org.example.util;

public class ScoreUtil {

    private ScoreUtil() {
        /* This utility class should not be instantiated */
    }

    public static int countLetters(String city) {
        int count = 0;
        for (char ch : city.toCharArray()) {
            if (Character.isLetter(ch)) {
                count++;
            }
        }
        return count;
    }
}
