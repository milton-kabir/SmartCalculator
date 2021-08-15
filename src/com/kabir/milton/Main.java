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
            for (int i = 0; i < ar.length; i++) {
//                System.out.print(ar[i]+"-->");
                String ss = ar[i].trim();
//                System.out.println(ss);
                if (ss.length() == 0) {
                    continue;
                }
                if (ss.charAt(0) == '-') {
                    int ck = 0;
                    for (int j = 1; j < ss.length(); j++) {
                        if (ss.charAt(j) != '-') {
                            ck = 1;
                            break;
                        }
                    }
                    if (ck == 1) {
                        s += Integer.parseInt(ss);
                    } else {
                        int xx = ss.length();
                        if (xx % 2 == 1) {
                            ar[i + 1] = "-" + ar[i + 1];
                        }
                    }
                } else if (ss.charAt(0) == '+') {
                    continue;
                } else {
                    s += Integer.parseInt(ss);
                }
            }
            System.out.println(s);
        }

    }
}
