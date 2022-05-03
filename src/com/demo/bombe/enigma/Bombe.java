package com.demo.bombe.enigma;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Bombe {

    List<List<String>> rotorInputSet;
    List<List<Integer>> ringInputSet;
    Set<String> plugBoardSet;
    Set<String> plugBoardPair;
    Set<Character> plugVisited;
    char[] cipherText;
    String rotor[] = {"I", "II", "III"};
    boolean[] visited;
    String message;
    int MAX_WIRES = 1;

    public Bombe(String message) {
        this.message = message;
    }

    public void crackCode(String input) {
        cipherText = input.toCharArray();
        setRotorInputSet();
        setRingStartSet();
        setPlugBoard();
        System.out.println("Starting!");
        for (char reflector = 'A'; reflector <= 'C'; reflector++) {
            for (int i = 0; i < rotorInputSet.size(); i++) {
                for (int j = 0; j < ringInputSet.size(); j++) {
                    for (int k = 0; k < ringInputSet.size(); k++) {
                        String[] rotorPos = rotorInputSet.get(i).toArray(new String[0]);
                        int[] ringStartPos = ringInputSet.get(j).stream().mapToInt(Integer::valueOf).toArray();
                        int[] ringSettings = ringInputSet.get(k).stream().mapToInt(Integer::valueOf).toArray();
                        for (String plugPair : plugBoardPair) {
                            Enigma enigma = new Enigma(rotorPos, String.valueOf(reflector), ringStartPos, ringSettings, plugPair);
                            char[] decryptText = enigma.decrypt(cipherText);
                            if (String.valueOf(decryptText).startsWith(message)) {
                                System.out.println("FOUND! " + String.valueOf(decryptText)
                                        + ", Reflector: " + reflector
                                        + ", Rotor:" + rotorInputSet.get(i)
                                        + ", Rotor Start: " + ringInputSet.get(j)
                                        + ", Ring Setting: " + ringInputSet.get(k)
                                        + ", Plug Board: " + plugPair);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    private void setRotorInputSet() {
        rotorInputSet = new ArrayList<>();
        visited = new boolean[rotor.length];
        rotorBackTrack(new ArrayList<>());
    }

    private void rotorBackTrack(List<String> tempList) {
        if (tempList.size() == rotor.length) {
            rotorInputSet.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < rotor.length; i++) {
                if (visited[i]) continue;
                tempList.add(rotor[i]);
                visited[i] = true;
                rotorBackTrack(tempList);
                tempList.remove(tempList.size() - 1);
                visited[i] = false;
            }
        }
    }

    private void setRingStartSet() {
        ringInputSet = new ArrayList<>();
        ringStartBackTrack(new ArrayList<>());
    }

    private void ringStartBackTrack(List<Integer> tempList) {
        if (tempList.size() == 3) {
            ringInputSet.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < 26; i++) {
                tempList.add(i);
                ringStartBackTrack(tempList);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    private void setPlugBoard() {
        setPlugBoardSet();
        plugBoardPair = new LinkedHashSet<>();
        //plugBoardPair.add("");
        plugVisited = new HashSet<>();
        setPlugBoardBackTrack(0, "");
    }

    private void setPlugBoardBackTrack(int depth, String result) {
        if (depth > MAX_WIRES) return;
        if (!result.isBlank()) {
            plugBoardPair.add(result);
        }
        for (String s : plugBoardSet) {
            if (plugVisited.contains(s.charAt(0)) || plugVisited.contains(s.charAt(1))) continue;
            plugVisited.add(s.charAt(0));
            plugVisited.add(s.charAt(1));
            setPlugBoardBackTrack(depth + 1, result.isBlank() ? s : result + "," + s);
            plugVisited.remove(s.charAt(0));
            plugVisited.remove(s.charAt(1));
        }
    }

    private void setPlugBoardSet() {
        plugBoardSet = new LinkedHashSet<>();
        for (char c1 = 'A'; c1 <= 'Z'; c1++) {
            for (char c2 = 'A'; c2 <= 'Z'; c2++) {
                if (c1 != c2) {
                    plugBoardSet.add(c1 + "" + c2);
                }
            }
        }
    }

}
