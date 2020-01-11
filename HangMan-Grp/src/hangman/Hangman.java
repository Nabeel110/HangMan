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
  
    public Hangman() {
        tri = new Trie2();
        secretWord = tri.randomWordPick();
        disguisedWord = secretWord.charAt(0) +"";
        for (int i = 1; i < secretWord.length(); i++) disguisedWord += "?";
        //System.out.println("The secret word is : " +secretWord);
        //System.out.println(disguisedWord);
      
        
    }
    public void userGuess()
    {
        String updatedWord = secretWord.charAt(0) +"";
        Scanner sc = new Scanner(System.in);
        int len = secretWord.length();
        System.out.println("The first letter is given to you.\n"
                        + "The number of alphabets u have to guessed to get the word are " + (len-1) +"\n"
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
     
        int ctr = 0; String res = "";
        for (int i = 0; i < secretWord.length(); i++) {
            if (c == secretWord.charAt(i)) {
                check[i] = c;
                ctr++;
            }
        }
        if (ctr == 0) 
         res = "";
        for (int i = 0; i < check.length; i++) res += check[i];
        disguisedWord = res;
    }
    public String getSecretWord() {
        return secretWord;
    }
  public boolean checkGuessedWord(String word){
      return tri.checkGuessedWord(secretWord, word);
  }
    public boolean isFound() {
        return secretWord.equalsIgnoreCase(disguisedWord);
    }
    public String getDistinguishedWord() {
        return disguisedWord;
    }

    public static void main(String[] args) 
    {
        MainScreen msc = new MainScreen();
        msc.setVisible(true);
        sound.sound1.loop();
       // Scanner sc = new Scanner(System.in);
      
        //Hangman h1 = new Hangman();
        //h1.userGuess();
//    h1.makeGuess('c'); // Nebsie commented this out
    
    }
}
    
    
   

