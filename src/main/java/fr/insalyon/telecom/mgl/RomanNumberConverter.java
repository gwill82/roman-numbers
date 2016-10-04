package fr.insalyon.telecom.mgl;

import java.util.HashMap;

/**
 * Helper for roman numbers conversions.
 */
public class RomanNumberConverter {

    private static final HashMap<Character, Integer> knownLetters = new HashMap<>();

    static {
        knownLetters.put('M', 1000);
        knownLetters.put('D', 500);
        knownLetters.put('C', 100);
        knownLetters.put('L', 50);
        knownLetters.put('X', 10);
        knownLetters.put('V', 5);
        knownLetters.put('I', 1);

    }

    public int convert(String number){
        int value = 0;
        char[] n = number.toUpperCase().toCharArray();
        int i = 0;
        int currentCharacterCount = 0;

        while(i<n.length){

            if(!knownLetters.containsKey(n[i])){
                throw new IllegalArgumentException("Roman number "+number+" has character "+n[i]+" invalid");
            }

            if(i == n.length-1){ // Handle first character
                if(n[i] == n[i-1]){
                    value += (currentCharacterCount+1) * knownLetters.get(n[i]);
                    currentCharacterCount = 0;
                } else if(knownLetters.get(n[i]) < knownLetters.get(n[i-1])){
                    value += (currentCharacterCount) * knownLetters.get(n[i-1]);
                    currentCharacterCount = 0;
                    value += knownLetters.get(n[i]);
                } else {
                    value -= (currentCharacterCount) * knownLetters.get(n[i-1]);
                    currentCharacterCount = 0;
                    value += knownLetters.get(n[i]);
                }
            } else if(i == 0) { // On last character
                if(i == n.length-1){ // We are in an alown number like I or X
                    value = knownLetters.get(n[i]);
                    break;
                } else {
                    currentCharacterCount++;
                }
            } else if(n[i] == n[i-1]){
                currentCharacterCount+=1;
            } else if(knownLetters.get(n[i]) < knownLetters.get(n[i-1])) {
                value += (currentCharacterCount) * knownLetters.get(n[i-1]);
                currentCharacterCount = 1;
            } else if(knownLetters.get(n[i]) > knownLetters.get(n[i-1])) {
                value -= (currentCharacterCount) * knownLetters.get(n[i-1]);
                currentCharacterCount = 1;
            } else {
                throw new IllegalArgumentException("Roman number "+number+" is invalid");
            }

            i++;
        }

        return value;
    }

    public String convert(int value){
        String number = "";

        return number;
    }

    public static void main(String... args){
        System.out.println(new RomanNumberConverter().convert("XIV"));
        System.out.println(new RomanNumberConverter().convert("LC"));
        System.out.println(new RomanNumberConverter().convert("XIX"));
        System.out.println(new RomanNumberConverter().convert("XD"));
    }

}
