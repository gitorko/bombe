package com.demo.bombe.enigma;

import java.util.HashSet;
import java.util.Set;

public class Plugboard {
    private int[] wiring;

    public Plugboard(String connections) {
        this.wiring = decodePlugboard(connections);
    }

    public int forward(int c) {
        return this.wiring[c];
    }

    private static int[] identityPlugboard() {
        int[] mapping = new int[26];
        for (int i = 0; i < 26; i++) {
            mapping[i] = i;
        }
        return mapping;
    }

    public static int[] decodePlugboard(String plugboard) {
        if (plugboard == null || plugboard.equals("")) {
            return identityPlugboard();
        }

        String[] pairings = plugboard.split("[^a-zA-Z]");
        Set<Integer> pluggedCharacters = new HashSet<>();
        int[] mapping = identityPlugboard();

        // Validate and create mapping
        for (String pair : pairings) {
            if (pair.length() != 2)
                return identityPlugboard();

            int c1 = pair.charAt(0) - 65;
            int c2 = pair.charAt(1) - 65;

            if (pluggedCharacters.contains(c1) || pluggedCharacters.contains(c2)) {
                return identityPlugboard();
            }

            pluggedCharacters.add(c1);
            pluggedCharacters.add(c2);

            mapping[c1] = c2;
            mapping[c2] = c1;
        }

        return mapping;
    }
}
