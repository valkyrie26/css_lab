package com.company;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
//KOYQKOYQFI
public class Playfair
{
    static String alphabets = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    static char [][] matrix = new char[5][5];

    //Encryption

    static void encrypt(char [][] matrix, String input) {
        //System.out.println(("Matrix Received")+matrix[0][0]);

        String output = input;
        int f = 0;
        //separate duplicate letters with X
        StringBuffer str = new StringBuffer(output);
        for (int a = 0; a < input.length(); a++) {
            if (a + 1 > input.length() - 1) {
                break;
            }
            if (input.charAt(a) == input.charAt(a + 1)) {
                f = f + a + 1;
                str.insert(f, 'X');
                f = f - 1;
            }
        }
        System.out.println("New String: " + str);

        StringBuffer cipher_string = new StringBuffer();
        char cipher_char1=' ';
        char cipher_char2=' ';


        //Even length (No padding required)
        if (input.length() % 2 == 0) {
            //Directly move forward
        }
        //Odd Length (Padding Required)
        else {
            str = str.append('X');
            System.out.println("Input padded is " + str);
        }


        for(int p=0;p<str.length();p+=2)
        {
            char current_char = str.charAt(p);
            char next_char = str.charAt(p+1);

            System.out.println("Pair: "+current_char+ " and "+next_char);//check

            int current_row = position(current_char, matrix, "row");
            int next_row = position(next_char, matrix, "row");
            int current_col = position(current_char, matrix, "column");
            int next_col = position(next_char, matrix, "column");

            //Same row, move to right
            if(current_row == next_row)
            {
                //increment column by 1 and wrap
                if(current_col==4)
                {
                    cipher_char1 = matrix[current_row][0];
                }
                else if(next_col==4)
                {
                    cipher_char2 = matrix[next_row][0];
                }
                else
                {
                    cipher_char1 = matrix[current_row][current_col+1];
                    cipher_char2 = matrix[next_row][next_col+1];
                }
                cipher_string.append(cipher_char1);
                cipher_string.append(cipher_char2);
            }
            //Same column, move down
            else if(current_col == next_col)
            {
                if(current_row==4)
                {
                    cipher_char1 = matrix[current_col][0];
                }
                else if(next_row==4)
                {
                    cipher_char2 = matrix[next_col][0];
                }
                else
                {
                    cipher_char1 = matrix[current_row+1][current_col];
                    cipher_char2 = matrix[next_row+1][next_col];
                }
                cipher_string.append(cipher_char1);
                cipher_string.append(cipher_char2);
            }
            //Rectangle: Take opposite sides
            else
            {
                cipher_char1 = matrix[current_row][next_col];
                cipher_char2 = matrix[next_row][current_col];

                cipher_string.append(cipher_char1);
                cipher_string.append(cipher_char2);
            }

        }
        System.out.println("Encrypted Text: "+cipher_string);

    }

    static int position(char c, char [][] matrix, String pos)
    {
        int output=0;
        for(int i=0;i < 5;i++)
        {
            for(int j=0;j < 5;j++)
            {
                if(matrix[i][j]==c)
                {
                    if(pos == "row")
                        output =i;
                    else
                        output =j;
                }
            }
        }
        return output;
    }

    //Decryption

    public static void decrypt(char [][] matrix, String input)
    {
        StringBuffer str = new StringBuffer(input);
        StringBuffer plain_string = new StringBuffer();
        char plain_char1=' ';
        char plain_char2=' ';
        //System.out.println("Matrix Received" + Arrays.deepToString(matrix));
        for(int p=0;p<str.length();p+=2)
        {
            char current_char = str.charAt(p);
            char next_char = str.charAt(p+1);

            System.out.println("Pair: "+current_char+ " and "+next_char);//check

            int current_row = position(current_char, matrix, "row");
            int next_row = position(next_char, matrix, "row");
            int current_col = position(current_char, matrix, "column");
            int next_col = position(next_char, matrix, "column");

            //Same row, move to left
            if(current_row == next_row)
            {
                //increment column by 1 and wrap
                if(current_col==0)
                {
                    plain_char1 = matrix[current_row][4];
                }
                else if(next_col==0)
                {
                    plain_char2 = matrix[next_row][4];
                }
                else
                {
                    plain_char1 = matrix[current_row][current_col-1];
                    plain_char2 = matrix[next_row][next_col-1];
                }
                plain_string.append(plain_char1);
                plain_string.append(plain_char2);
            }
            //Same column, move up
            else if(current_col == next_col)
            {
                if(current_row==0)
                {
                    plain_char1 = matrix[current_col][4];
                }
                else if(next_row==0)
                {
                    plain_char2 = matrix[next_col][4];
                }
                else
                {
                    plain_char1 = matrix[current_row-1][current_col];
                    plain_char2 = matrix[next_row-1][next_col];
                }
                plain_string.append(plain_char1);
                plain_string.append(plain_char2);
            }
            //Rectangle: Take opposite sides
            else
            {
                plain_char1 = matrix[current_row][next_col];
                plain_char2 = matrix[next_row][current_col];

                plain_string.append(plain_char1);
                plain_string.append(plain_char2);
            }

        }

        System.out.println("Decrypted Text with Xs: "+plain_string);

        //Remove all Xs (Assuming message has no X)
        String new_string = plain_string.toString();
        String print_string = new_string.replaceAll("X","");
        System.out.println("Decrypted Text without Xs: "+print_string);

    }

    //Main
    public static void playfair_main()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Keyword:");


        Scanner scc = new Scanner(System.in);
        System.out.println("This is Playfair Cipher.\nEnter keyword: ");
        String keyword = scc.nextLine();

        System.out.println("Keyword is "+ keyword);

        //Make a string for key matrix

        String intermediate_str = keyword + alphabets;

        //Remove duplicates from intermediate string for key matrix string
        StringBuilder sb = new StringBuilder();

        char[] chars = intermediate_str.toCharArray();
        Set<Character> charSet = new LinkedHashSet<Character>();
        for (char c : chars) {
            charSet.add(c);
        }

        for (Character character : charSet) {
            sb.append(character);
        }

        System.out.println("Matrix is : "+sb.toString());

        //sb contains matrix


        System.out.println("Key Matrix:");

        //Make and display matrix

        String matrix_str = sb.toString();
        int s=0;

        for(int j=0;j<5;j++)
        {
            for(int k=0;k<5;k++)
            {

                matrix[j][k] = matrix_str.charAt(s);
                System.out.print(" "+matrix[j][k]+" ");
                s++;
            }
            System.out.println("\n");
        }

        //Input
        System.out.println("Enter Input: ");
        String input = scc.nextLine();
        input = input.replaceAll("\\s","");

        //Encryption or decryption
        System.out.println("1. Encryption\n2. Decryption\n");
        int choice = scc.nextInt();

        if(choice==1)
        {
            encrypt(matrix, input);
        }
        else if(choice==2)
        {
            decrypt(matrix, input);
        }
        else
        {
            System.out.println("Please enter valid choice");
        }
    }
}
