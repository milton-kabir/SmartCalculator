//package calculator;
package com.kabir.milton;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);

        while (true) {
            int inv = 0;
            String[] ar = sc.nextLine().split(" ");
            if (ar[0].length() == 0) {
                continue;
            }

            if (ar[0].charAt(0) == '/') {
                if (ar[0].equals("/help")) {
                    System.out.println("The program calculates the sum of numbers");
                    continue;
                } else if (ar[0].equals("/exit")) {
                    System.out.println("Bye!");
                    return;
                } else {
                    System.out.println("Unknown command");
                    continue;
                }

            }
            int s;
            var ii = new ArrayList<Integer>();
            var op = new ArrayList<String>();
            for (String value : ar) {
//                System.out.print(ar[i]+"-->");
                String ss = value.trim();
//                System.out.println(ss);
                if (ss.length() == 0) {
                    continue;
                }

                for (int j = 0; j < ss.length(); j++) {
                    if (ss.charAt(j) != '+' && ss.charAt(j) != '-' && (ss.charAt(j) < '0' || ss.charAt(j) > '9')) {
                        inv = 1;
                        break;
                    }
                }
                if (inv == 1) {
                    break;
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
                        ii.add(Integer.parseInt(ss));
                    } else {
                        if (ss.length() % 2 == 1) {
                            op.add("-");
                        } else {
                            op.add("+");
                        }
                    }
                } else if (ss.charAt(0) == '+') {
                    int ck = 0;
                    for (int j = 1; j < ss.length(); j++) {
                        if (ss.charAt(j) != '+') {
                            ck = 1;
                            break;
                        }
                    }
                    if (ck == 1) {
                        ii.add(Integer.parseInt(ss));
                    } else {
                        op.add("+");
                    }
                } else {
                    int ck = 0;
                    for (int j = 0; j < ss.length(); j++) {
                        if (ss.charAt(j) == '+' || ss.charAt(j) == '-') {
                            ck = 1;
                            break;
                        }
                    }
                    if (ck == 1) {
                        inv = 1;
                        break;
                    }
                    ii.add(Integer.parseInt(ss));
                }
            }
            if (inv == 1) {
                System.out.println("Invalid expression");
                continue;
            }
            s = ii.get(0);
//            System.out.println(ii.size()+"  --  "+op.size());
            if (ii.size() == op.size() + 1) {
                for (int i = 1; i < ii.size(); i++) {
                    if (op.get(i - 1).equals("+")) {
                        s += ii.get(i);
                    } else {
                        s -= ii.get(i);
                    }
                }

                System.out.println(s);
            } else {
                System.out.println("Invalid expression");
            }
        }

    }
}
