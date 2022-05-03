package com.demo.bombe.enigma;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PlugBoard {
    private int[] wiring;

    public PlugBoard(String connections) {
        this.wiring = decodePlugboard(connections);
    }

    public int forward(int c) {
        return this.wiring[c];
    }

    private int[] identityPlugBoard() {
        int[] mapping = new int[26];
        for (int i = 0; i < 26; i++) {
            mapping[i] = i;
        }
        return mapping;
    }

    public int[] decodePlugboard(String plugboard) {
        if (plugboard == null || plugboard.equals("")) {
            return identityPlugBoard();
        }

        String[] pairings = plugboard.split("[^a-zA-Z]");
        Set<Integer> pluggedCharacters = new HashSet<>();
        int[] mapping = identityPlugBoard();

        // Validate and create mapping
        for (String pair : pairings) {
            if (pair.length() != 2)
                return identityPlugBoard();

            int c1 = pair.charAt(0) - 65;
            int c2 = pair.charAt(1) - 65;

            if (pluggedCharacters.contains(c1) || pluggedCharacters.contains(c2)) {
                return identityPlugBoard();
            }

            pluggedCharacters.add(c1);
            pluggedCharacters.add(c2);

            mapping[c1] = c2;
            mapping[c2] = c1;
        }

        return mapping;
    }

    @Override
    public String toString() {
        return "Plugboard{" +
                "wiring=" + Arrays.toString(wiring) +
                '}';
    }
}
