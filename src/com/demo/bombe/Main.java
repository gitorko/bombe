package com.demo.bombe;

import java.time.Duration;
import java.time.Instant;

import com.demo.bombe.enigma.Bombe;
import com.demo.bombe.enigma.Enigma;

public class Main {

    public static String[] rotorSetup = new String[]{"I", "III", "II"};
    public static int[] ringSetup = new int[]{0, 23, 0};
    public static int[] ringSettingSetup = new int[]{0, 0, 12};
    public static String plugBoardSetup = "";

    public static void main(String[] args) {
        Enigma encryptor = new Enigma(rotorSetup, "B", ringSetup, ringSettingSetup, plugBoardSetup);
        String input = "WEATHERREPORT";
        char[] cipherText = encryptor.encrypt(input.toCharArray());
        System.out.println("cipherText: " + String.valueOf(cipherText));

        Enigma decryptor = new Enigma(rotorSetup, "B", ringSetup, ringSettingSetup, plugBoardSetup);
        char[] decryptText = decryptor.decrypt(cipherText);
        System.out.println("decryptText: " + String.valueOf(decryptText));

        Instant start = Instant.now();
        Bombe bombe = new Bombe();
        bombe.crackCode(String.valueOf(cipherText));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Completed in :" + timeElapsed);
    }

}
