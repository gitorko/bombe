package com.demo.bombe;

import java.time.Duration;
import java.time.Instant;

import com.demo.bombe.enigma.Bombe;
import com.demo.bombe.enigma.Enigma;

public class Main {

    public static String[] rotorSetup = new String[]{"I", "II", "III"};
    public static int[] ringSetup = new int[]{0, 0, 0};
    public static int[] ringSettingSetup = new int[]{0, 2, 0};
    public static String plugBoardSetup = "";
    public static String reflector = "B";

    public static void main(String[] args) {
        String message = "ATTENTIONXWEATHERXREPORTX";
//        String encrypted = encrypt(message);
//       System.out.println("encrypted: " + encrypted);

//        String decrypted = decrypt("BZHWCRPQVRCTBABNPBSPRDKWI");
//        System.out.println("decrypted: " + decrypted);
//        System.out.println();

        String encrypted = "BZHWCRPQVRCTBABNPBSPRDKWI";
        crackEnigma(encrypted, message);
    }

    public static void crackEnigma(String cipherText, String message) {
        Instant start = Instant.now();
        Bombe bombe = new Bombe(message);
        bombe.crackCode(String.valueOf(cipherText));
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Completed in :" + timeElapsed + " milli seconds");
    }

    public static String encrypt(String message) {
        Enigma encryptor = new Enigma(rotorSetup, reflector, ringSetup, ringSettingSetup, plugBoardSetup);
        char[] cipherText = encryptor.encrypt(message.toCharArray());
        return String.valueOf(cipherText);
    }

    public static String decrypt(String message) {
        Enigma decryptor = new Enigma(rotorSetup, "B", ringSetup, ringSettingSetup, plugBoardSetup);
        char[] decryptText = decryptor.decrypt(message.toCharArray());
        return String.valueOf(decryptText);
    }

}
