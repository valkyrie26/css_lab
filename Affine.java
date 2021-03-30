package com.company;

import java.util.Scanner;

public class Affine {

    static String alphabets = "abcdefghijklmnopqrstuvwxyz ";
    static char current_char;
    static int intermediate =0;
    static int flag;

    //Encryption

    public static void encrypt(int a, int b, String input){
        String e = " ";
        char ec;

        for(int i=0;i<input.length();i++)
        {
            current_char = input.charAt(i);
            flag =0;

            //find index in alphabets for current character
            //encode (5x+b)mod 26
            //find letter corresponding to new encoded number

            int index = alphabets.indexOf(current_char);
            if(index==26)
            {
                e = e + " ";
                flag = 1;
            }

            if(flag !=1)
            {
                //find new index
                intermediate = (a*index+b) % 26;
                //find alphabet corresponding to new index
                ec = alphabets.charAt(intermediate);

                e = e + ec;
            }

        }

        System.out.println("Encrypted Text: " + e);

    }

    //Decryption

    public static void decrypt(int a, int b, String input){

        String d = " ";
        char dc;
        int a_inverse;

        a_inverse = modInverse(a,26);

        for(int j=0;j<input.length();j++)
        {
            current_char = input.charAt(j);//c
            flag =0;

            int index = alphabets.indexOf(current_char);//encrypted value//check

            if(index==26)
            {
                d = d + " ";
                flag = 1;
            }

            if(flag !=1)
            {
                //find new index
                intermediate = (a_inverse*(index-b)) % 26;//intermediate index

                //find alphabet corresponding to new index
                if(intermediate<0)
                {
                    intermediate = 26 + intermediate;
                }
                dc = alphabets.charAt(intermediate);

                d = d + dc;
            }

        }

        System.out.println("Decrypted Text: " + d);
    }

    static int modInverse(int a, int m)
    {

        for (int x = 1; x < m; x++)
            if (((a%m) * (x%m)) % m == 1)
                return x;
        return 1;
    }

    public static void affine_main() {
        //Scan the key
        Scanner scc = new Scanner(System.in);
        System.out.println("This is Affine Cipher.\nEnter key (a,b): ");
        String key = scc.nextLine();
        char ac = key.charAt(1);
        char bc = key.charAt(3);
        int a = Character.getNumericValue(ac);
        int b = Character.getNumericValue(bc);

        //KeyCheck System.out.println("Key is "+ a+ b);

        //Input
        System.out.println("Enter Input: ");
        String input = scc.nextLine();

        //Encryption or decryption
        System.out.println("1. Encryption\n2. Decryption\n");
        int choice = scc.nextInt();

        if(choice==1)
        {
            encrypt(a,b,input);
        }
        else if(choice==2)
        {
            decrypt(a,b,input);
        }
        else
        {
            System.out.println("Please enter valid choice");
        }
    }
}
