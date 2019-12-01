/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

/**
 *
 * @author hp
 */
public class LinkyListo 
{
    static Node head;
    static Node curr;
    public void insert(String name, int score)
    {
        Node n = new Node(name, score);
        if (head == null)
        {
            head = n;
            curr = n;
            n.next = head;
        }
        else
        {
            Node temp = head;
            while (temp.next != head)
            {
                temp = temp.next;
            }
            if (temp.next == head)
            {
                temp.next = n;
                n.next = head;
                
            }
        }
    }
    public int findScore(String name, Node root)
    {
        if (root.name.equals(name)) return root.score;
        else
        {
            Node temp = root.next;
            while (temp != root)
            {
                if (temp.name.equals(name))
                {
                    return temp.score;
                }
                temp = temp.next;
            }
        }
        return 0;
    }
    public void updateScore(String name, int score, Node root)
    {
        Node temp = root;
        while(temp.next != root)
        {
            if (temp.name.equals(name)) 
            {
                temp.score = score;
                break;
            }
            else temp = temp.next;
        }
    }
    public String[] Players(Node root)
    {
        String[] arr = new String[4];
        Node temp = root;
        for (int i = 0; i < arr.length; i++) 
        {
            if (temp == null)
            {
                break;
            }
            arr[i] = temp.name;
            temp = temp.next;
        }
        for (int i = 0; i < arr.length; i++) 
        {
            if (arr[i] == null) arr[i] = "";
        }
        return arr;
    }
    
    public void Print(Node root)
    {
        Node temp = root;
        while (temp.next != root) 
        {
            System.out.println(temp.name);
            temp = temp.next;
        }
        System.out.println(temp.name);
    }
}

class Node
{
    Node next;
    Node prev;
    String name;
    int score;
    Node(String name, int score)
    {
        this.name = name;
        this.score = score;
    }
}
