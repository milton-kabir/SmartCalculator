//package calculator;
package com.kabir.milton;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc= new Scanner(System.in);
        String[] ar=sc.nextLine().split(" ");
        int x=Integer.parseInt(ar[0]);
        int y=Integer.parseInt(ar[1]);
        System.out.print(x+y);
    }
}
