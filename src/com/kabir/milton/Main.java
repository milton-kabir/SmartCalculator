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
            if (ar[0].equals("/exit")) {
                System.out.println("Bye!");
                return;
            }
            int x = Integer.parseInt(ar[0]);
            if (ar.length == 1) {
                System.out.println(ar[0]);
                continue;
            }
            int y = Integer.parseInt(ar[1]);
            System.out.println(x + y);
        }

    }
}
