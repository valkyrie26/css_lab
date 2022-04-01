package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	//Enter practical number
        Scanner sc = new Scanner (System.in);

        System.out.println("Enter number: ");
        int prn = sc.nextInt();

        switch (prn) {
            case 1:
                System.out.println("\n1: Affine Cipher");
                Affine.affine_main();
                break;
            case 2:
                System.out.println("\n2: Playfair Cipher");
                Playfair.playfair_main();
                break;
            case 3:
                System.out.println("\n3: Polynomial Multiplication");
                Polynomial.polynomial_main();
                break;
            case 4:
                System.out.println("\n4: Primitive Roots");
                PrimitiveRoots.primitiveroots_main();
                break;
            case 5:
                System.out.println("\n5: RSA Algorithm");
                RSA.rsa_main();
                break;
            case 7:
                System.out.println("\n7: Diffie-Hellman Key Exchange");
                DiffieHellman.diffie_main();
                break;
            default:
                System.out.println("\nPlease enter valid number");
        }

    }
}
