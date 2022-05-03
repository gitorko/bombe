package com.demo.bombe.enigma;

import java.util.Arrays;

public class Reflector {
    int[] forwardWiring;

    public Reflector(String encoding) {
        this.forwardWiring = decodeWiring(encoding);
    }

    public static Reflector Create(String name) {
        switch (name) {
            case "A":
                return new Reflector("ZYXWVUTSRQPONMLKJIHGFEDCBA");
            case "B":
                return new Reflector("YRUHQSLDPXNGOKMIEBFZCWVJAT");
            case "C":
                return new Reflector("FVPJIAOYEDRZXWGCTKUQSBNMHL");
            default:
                throw new RuntimeException("No such reflector");
        }
    }

    private int[] decodeWiring(String encoding) {
        char[] charWiring = encoding.toCharArray();
        int[] wiring = new int[charWiring.length];
        for (int i = 0; i < charWiring.length; i++) {
            wiring[i] = charWiring[i] - 65;
        }
        return wiring;
    }

    public int forward(int c) {
        return this.forwardWiring[c];
    }

    @Override
    public String toString() {
        return "Reflector{" +
                "forwardWiring=" + Arrays.toString(forwardWiring) +
                '}';
    }
}
