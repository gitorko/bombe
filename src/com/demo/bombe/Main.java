package com.demo.bombe;

import java.util.ArrayList;
import java.util.Arrays;
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

        Main main = new Main();
        main.crackCode(String.valueOf(cipherText));
    }

    List<List<String>> result;
    char[] cipherText;
    String rotor[] = {"I", "II", "III"};

    public void crackCode(String input) {
        result = new ArrayList<>();
        backtrack(new ArrayList<>(), 0);
        System.out.println(result);
    }

    private void backtrack(List<String> tempList, int start) {
        if (tempList.size() == 3) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i < rotor.length; i++) {
            tempList.add(rotor[i]);
            backtrack(tempList, i);
            tempList.remove(tempList.size() - 1);
        }
    }

//    public static void crackCode2(char[] cipherText) {
//        System.out.println("Cracking!");
//        String reflector[] = {"B"};
//        for (int rotoType1 = 0; rotoType1 < rotor.length; rotoType1++) {
//            for (int rotoType2 = rotoType1; rotoType2 < rotor.length; rotoType2++) {
//                for (int rotoType3 = rotoType2; rotoType3 < rotor.length; rotoType3++) {
//                    String rotorInput[] = new String[]{rotor[rotoType1], rotor[rotoType2], rotor[rotoType3]};
//                    System.out.println(Arrays.toString(rotorInput));
//                    for (int rotorPos1 = 0; rotorPos1 < 26; rotorPos1++) {
//                        for (int rotorPos2 = 0; rotorPos2 < 26; rotorPos2++) {
//                            for (int rotorPos3 = 0; rotorPos3 < 26; rotorPos3++) {
//                                for (int ringSet1 = 0; ringSet1 < 26; ringSet1++) {
//                                    for (int ringSet2 = 0; ringSet2 < 26; ringSet2++) {
//                                        for (int ringSet3 = 0; ringSet3 < 26; ringSet3++) {
//                                            Enigma e2 = new Enigma(rotorInput, "B", new int[]{rotorPos1, rotorPos2, rotorPos3}, new int[]{ringSet1, ringSet2, ringSet3}, "");
//                                            System.out.println(e2);
//                                            char[] decryptText = e2.decrypt(cipherText);
//                                            if (decryptText.toString().contains("WEATHERREPORT")) {
//                                                System.out.println("Found! rotorPos1:" + rotorPos1 + ",rotorPos2:" + rotorPos2 + ",rotorPos3:" + rotorPos3 +
//                                                        ",ringSet1:" + ringSet1 + ",ringSet2:" + ringSet2 + ",ringSet3:" + ringSet3);
//                                                return;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}
