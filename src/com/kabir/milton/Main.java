//package calculator;
package com.kabir.milton;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] ar = sc.nextLine().split(" ");
            if (ar[0].length() == 0) {
                continue;
            }
            if (ar[0].equals("/help")) {
                System.out.println("The program calculates the sum of numbers");
                continue;
            }
            if (ar[0].equals("/exit")) {
                System.out.println("Bye!");
                return;
            }
            int s = 0;
            for (String value : ar) {
                s += Integer.parseInt(value);
            }
            System.out.println(s);
        }

    }
}
