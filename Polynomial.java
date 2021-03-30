package com.company;

import java.util.Scanner;

//Rijndael's Finite Field (AES): Multiplication of 2 polynomials represented in Galois Field 2^8 with a given irreducible polynomial

/*Test input:
 * (01010111) x (10000011) mod (100011011)
 * Output Expected: (11000001)*/

public class Polynomial {

    //function for XOR for 2 binary strings
    public static String XOR(String a, String b)
    {
        String answer = "";

        for(int i=0; i<a.length(); i++)
        {
            if(a.charAt(i)==b.charAt(i))
            {
                answer = answer + "0";
            }
            else
            {
                answer = answer + "1";
            }
        }
        return answer;
    }

    //function to left-shift by 1 bit
    public static String leftshift(String a)
    {
        //empty string to store data
        String answer = "";
        answer = a.substring(1, a.length())+'0';
        return answer;
    }

    //main function

    public static void polynomial_main()
    {
        Scanner sc = new Scanner(System.in);

        //Scan values of polynomials to be multiplied and m(x)

        System.out.println("Powers of f(x) in binary: ");
        String fx = sc.nextLine();
        System.out.println("Powers of g(x) in binary: ");
        String gx = sc.nextLine();
        System.out.println("Powers of m(x) in binary: ");
        String mx = sc.nextLine();

        //new value of m(x) without the first term with x^8
        String m_new = mx.substring(1, mx.length());

        int count = fx.length()-fx.indexOf('1');

        String x[] = new String[count+1];
        String temp = gx;
        //array to store x*f(x) values
        x[0] = gx;
        //Store final answer
        String tempx = "";

        //calculate x*f(x) and store in array x
        for(int i = 1; i <= count; i++)
        {
            if(temp.charAt(0) == '1') //left shift and xor with the new value of m because bit 0 is 1
            {
                temp = leftshift(temp);
                temp = XOR(temp, m_new);
                x[i]=temp;
            }
            else //left shift without xor because bit 0 is 0
            {
                temp = leftshift(temp);
                x[i] = temp;
            }
        }

        //Initialise all bits at tempx as 0
        for(int i=0;i<fx.length();i++)
        {
            tempx = tempx + "0";
        }

        //Choose only those values of x*f(x) we need, xor them and store final result in array tempx

        for(int i=0;i<fx.length();i++)
        {
            if(fx.charAt(i)=='1')
            {
                tempx = XOR(x[(fx.length()-i)-1],tempx);
            }
        }

        //Printing final answer
        System.out.println("Multiplication of ("+fx+ " * "+gx+") = " +tempx);
    }
}