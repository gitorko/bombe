package com.demo.bombe;

import java.util.ArrayList;
import java.util.List;

import com.demo.bombe.enigma.Enigma;

public class Main {

    public static void main(String[] args) {
        Enigma e1 = new Enigma(new String[]{"I", "II", "III"}, "B", new int[]{0, 0, 0}, new int[]{0, 0, 0}, "");
        String input = "WEATHERREPORT";
        char[] cipherText = e1.encrypt(input.toCharArray());
        System.out.println(cipherText);

        Enigma e2 = new Enigma(new String[]{"I", "II", "III"}, "B", new int[]{0, 0, 0}, new int[]{0, 0, 0}, "");
        char[] decryptText = e2.decrypt(cipherText);
        System.out.println(decryptText);
        System.out.println();
        Main main = new Main();
        main.crackCode(String.valueOf(cipherText));
    }

    List<List<String>> rotorInputSet;
    List<List<Integer>> ringInputSet;
    char[] cipherText;
    String rotor[] = {"I", "II", "III"};
    boolean[] visited;

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
                    if (String.valueOf(decryptText).equals("WEATHERREPORT")) {
                        System.out.println("FOUND! : Rotor:" + rotorInputSet.get(i) + ", Ring Start: " + ringInputSet.get(i) + ", Ring Setting: " + ringInputSet.get(k));
                        return;
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
