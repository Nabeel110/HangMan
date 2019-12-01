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
/*
 *  Java Program to Implement Trie
 */
 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
 
class TrieNode 
{
    char content; 
    public TrieNode left, right, middle;
    boolean isEnd; 
    int count;  
    LinkedList<TrieNode> childList; 
 
    /* Constructor */
    public TrieNode(char c)
    {
        childList = new LinkedList<TrieNode>();
        isEnd = false;
        content = c;
        count = 0;
    }  
    public TrieNode subNode(char c)
    {
        if (childList != null)
            for (TrieNode eachChild : childList)
                if (eachChild.content == c)
                    return eachChild;
        return null;
    }
}
 
class Trie2
{
    private TrieNode root;
 
     /* Constructor */
    public Trie2()
    {
        root = new TrieNode(' '); 
    }
     /* Function to insert word */
    public void insert(String word)
    {
        if (search(word) == true) 
            return;        
        TrieNode current = root; 
        for (char ch : word.toCharArray() )
        {
            TrieNode child = current.subNode(ch);
            if (child != null)
                current = child;
            else 
            {
                 current.childList.add(new TrieNode(ch));
                 current = current.subNode(ch);
            }
            current.count++;
        }
        current.isEnd = true;
    }
    /* Function to search for word */
    public boolean search(String word)
    {
        TrieNode current = root;  
        for (char ch : word.toCharArray() )
        {
            if (current.subNode(ch) == null)
                return false;
            else
                current = current.subNode(ch);
        }      
        if (current.isEnd == true) 
            return true;
        return false;
    }
    /* Function to remove a word */
    public void remove(String word)
    {
        if (search(word) == false)
        {
            System.out.println(word +" does not exist in trie\n");
            return;
        }             
        TrieNode current = root;
        for (char ch : word.toCharArray()) 
        { 
            TrieNode child = current.subNode(ch);
            if (child.count == 1) 
            {
                current.childList.remove(child);
                return;
            } 
            else 
            {
                child.count--;
                current = child;
            }
        }
        current.isEnd = false;
    }
    public boolean checkGuessedWord(String OriginalWord, String UserGuessedWord)
    {
        if(UserGuessedWord.equals(OriginalWord))
        {
          return true; // System.out.println("CONGRATULATIONS!!! You have guessed the word correctly");
        }
        else{
        TrieNode cur = root;
        
            for(char ch : UserGuessedWord.toCharArray())
            {
                if (cur.subNode(ch) == null)
                    break;
//                    System.out.println("you have guessed it incorrectly. The word was "+ OriginalWord + " TRY AGAIN!!"); //Nebsie commented this out
                else
                cur = cur.subNode(ch);
            }
            if(cur.isEnd == true)
            {
               return true;// System.out.println("you have guessed it right.");
            }
            else
               return false;// System.out.println("you have guessed it incorrectly. The word was "+ OriginalWord + " TRY AGAIN!!");
        }
        
    }
    public void readFromDict()
    {
         String strLine = "";
        try
        {
         FileInputStream fstream = new FileInputStream("C:\\Users\\hp\\Desktop\\DS Project\\Hangman-Project\\HangMan\\src\\hangman\\ShuffledDict.txt");   
         DataInputStream in = new DataInputStream(fstream);
         BufferedReader br = new BufferedReader(new InputStreamReader(in));
         
          String[] arr = null;
         while((strLine =br.readLine()) != null)
         {
           int indexOfComma = strLine.indexOf(',');

//              String meaning = "";
              String word = "";

                if(indexOfComma != -1){
              word = strLine.substring(0,indexOfComma-1);
//                    System.out.println(word);
                    
//              meaning = strLine.substring(indexOfComma+1,strLine.length());
                }
                else
                {
                    continue;
                }
              
                insert(word);
                
         }
//         System.out.println("total word are " + root.count);
         in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
    Random r = new Random();
    public String randomWordPick()
    {
        
        String randomWOrd = "";
        
        String strLine = "";
       ArrayList<String> words = new ArrayList<>();
        try
        {
         FileInputStream fstream = new FileInputStream("C:\\Users\\hp\\Desktop\\DS Project\\Hangman-Project\\HangMan\\src\\hangman\\ShuffledDict.txt");   
         DataInputStream in = new DataInputStream(fstream);
         BufferedReader br = new BufferedReader(new InputStreamReader(in));
         int i = 0;
         while((strLine =br.readLine()) != null)
         {
           int indexOfComma = strLine.indexOf(',');
              String word = "";
                if(indexOfComma != -1)
                {
                    words.add(strLine.substring(0,indexOfComma-1));
                }
                else
                {
                    continue;
                }
              
                i++;
         }
         in.close();
        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }
        randomWOrd = words.get(r.nextInt(words.size()));
        if(randomWOrd.contains("-") || randomWOrd.length() <4 || randomWOrd.matches(".*\\d+.*"))
        {
            while((randomWOrd.contains("-")) || (randomWOrd.length() <4)||randomWOrd.matches(".*\\d+.*"))
            {
                randomWOrd = words.get(r.nextInt(words.size()));
            }
        }
        return randomWOrd;
    }
    
    public void inorderTraversal(TrieNode node, String word) {
    // handle end of word
    if (node.content != 0) {
        System.out.println(word + node.content + ": " + node.content);
    }

    if(node.left != null) {
        inorderTraversal(node.left, word);
    }

    if (node.middle != null) {
        inorderTraversal(node.middle, word + node.content);
    }

    if(node.right != null) {
        inorderTraversal(node.right, word);
    }
}

public void traverse() {
    inorderTraversal(root, "");
}
}    
