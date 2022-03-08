package com.demo.bombe.enigma;

import java.util.ArrayList;
import java.util.List;

public class Bombe {

    List<List<String>> rotorInputSet;
    List<List<Integer>> ringInputSet;
    char[] cipherText;
    String rotor[] = {"I", "II", "III"};
    boolean[] visited;
    String message;

    public Bombe(String message) {
        this.message = message;
    }

    public void crackCode(String input) {
        cipherText = input.toCharArray();
        setRotorInputSet();
        setRingStartSet();
        for (int i = 0; i < rotorInputSet.size(); i++) {
            for (int j = 0; j < ringInputSet.size(); j++) {
                for (int k = 0; k < ringInputSet.size(); k++) {
                    String[] rotorPos = rotorInputSet.get(i).toArray(new String[0]);
                    int[] ringStartPos = ringInputSet.get(j).stream().mapToInt(Integer::valueOf).toArray();
                    int[] ringSettings = ringInputSet.get(k).stream().mapToInt(Integer::valueOf).toArray();
                    Enigma e1 = new Enigma(rotorPos, "B", ringStartPos, ringSettings, "");
                    char[] decryptText = e1.decrypt(cipherText);
                    if (String.valueOf(decryptText).startsWith(message)) {
                        System.out.println("FOUND! " + String.valueOf(decryptText) + " : Rotor:" + rotorInputSet.get(i) + ", Ring Start: " + ringInputSet.get(i) + ", Ring Setting: " + ringInputSet.get(k));
//                        return;
                    }
                }
            }
        }
    }

    private void setRotorInputSet() {
        rotorInputSet = new ArrayList<>();
        visited = new boolean[rotor.length];
        rotorBackTrack(new ArrayList<>());
        //System.out.println("Rotor Combinations: " + rotorInputSet);
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
        //System.out.println("Ring Combinations: " + ringInputSet);
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
}
