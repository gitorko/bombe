package com.demo.bombe;

import com.demo.bombe.enigma.Enigma;

public class Main {

    public static void main(String[] args) {
        Enigma e1 = new Enigma(new String[] {"I", "II", "III"}, "B", new int[] {0,0,0}, new int[] {0,0,0}, "");
        String input = "HELLOWORLD";
        char[] cipherText = e1.encrypt(input.toCharArray());
        System.out.println(cipherText);

        Enigma e2 = new Enigma(new String[] {"I", "II", "III"}, "B", new int[] {0,0,0}, new int[] {0,0,0}, "");
        char[] decryptText = e2.decrypt(cipherText);
        System.out.println(decryptText);
    }
}
