/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

/**
 *
 * @author Dell
 */
import java.util.Scanner;
public class Hangman {
    Trie2 tri;
    private final String secretWord;
    private static String disguisedWord;
    private int totalGuesses;
    private int incorrectGuesses;
    public Hangman() {
        tri = new Trie2();
        secretWord = tri.randomWordPick();
        disguisedWord = secretWord.charAt(0) +"";
        for (int i = 1; i < secretWord.length(); i++) disguisedWord += "?";
        System.out.println("The secret word is : " +secretWord);
        System.out.println(disguisedWord);
    }
    public void userGuess()
    {
        String updatedWord = secretWord.charAt(0) +"";
        Scanner sc = new Scanner(System.in);
        int len = secretWord.length();
        System.out.println("The first letter is given to you.\n"
                        + "The number of alphabets u have to guessed to get the word are " + len +"\n"
                        + "Guess the word character by character in order.");
        for (int i = 1; i < len; i++) 
        {
            System.out.print("Guess the next character in order:");
            char ch = sc.next().charAt(0);
            updatedWord += java.lang.Character.toLowerCase(ch);
            System.out.println("The updated word is " + updatedWord);
        }
        tri.checkGuessedWord(secretWord, updatedWord);
    }
    public void makeGuess(char c) {
        char[] check = disguisedWord.toCharArray();
        c = java.lang.Character.toLowerCase(c);
        totalGuesses++;
        int ctr = 0;
        for (int i = 0; i < secretWord.length(); i++) {
            if (c == secretWord.charAt(i)) {
                check[i] = c;
                ctr++;
            }
        }
        if (ctr == 0) incorrectGuesses++;
        String res = "";
        for (int i = 0; i < check.length; i++) res += check[i];
        disguisedWord = res;
    }
    public String getSecretWord() {
        return secretWord;
    }
    public int guessCount() {
        return totalGuesses;
    }
    public boolean isFound() {
        return secretWord.equalsIgnoreCase(disguisedWord);
    }
    public String getDistinguishedWord() {
        return disguisedWord;
    }
    public int incorrectGuessCount() {
        return incorrectGuesses;
    }
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
      
        Hangman h1 = new Hangman();
        h1.userGuess();
//    h1.makeGuess('c');
    
    }
}
    
    
   

