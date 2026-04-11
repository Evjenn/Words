package org.example.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CityRepository {

    private final Map<Character, List<String>> citiesByLetter = new HashMap<>();
    private final Set<String> allCities = new HashSet<>();
    private final Random random = new Random();

    public CityRepository() {
        loadCities();
    }

    private void loadCities() {

        InputStream inputS = getClass()
                .getClassLoader()
                .getResourceAsStream("UkraineCities.txt");
        if (inputS == null) {
            throw new IllegalStateException("File 'UkraineCities.txt' not found");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String city = line.trim().toLowerCase();
                if (city.isEmpty()) {
                    continue;
                }
                allCities.add(city);
                char first = city.charAt(0);
                citiesByLetter
                        .computeIfAbsent(first, k -> new ArrayList<>())
                        .add(city);
            }

        } catch (Exception e) {
            throw new RuntimeException("Loading Exception", e);
        }
    }

    public boolean exists(String city) {
        return allCities.contains(city.toLowerCase());
    }

    public boolean hasAvailableCities(char letter, Set<String> used) {
        List<String> list = citiesByLetter.get(letter);
        if (list == null) {
            return false;
        }
        for (String city : list) {
            if (!used.contains(city)) {
                return true;
            }
        }
        return false;
    }

    public String getRandomCity(char letter, Set<String> used) {

        List<String> list = citiesByLetter.get(letter);
        if (list == null) {
            return null;
        }
        List<String> available = new ArrayList<>();
        for (String city : list) {
            if (!used.contains(city)) {
                available.add(city);
            }
        }
        if (available.isEmpty()) {
            return null;
        }
        int index = this.random.nextInt(available.size());
        return available.get(index);
    }
}
