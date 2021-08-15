//package calculator;
package com.kabir.milton;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        var myMap = new HashMap<String, String>();

        while (true) {
            int inv = 0;
            String st = sc.nextLine();
            String[] ar = st.split("\\s+");
            var br = new ArrayList<String>();
            for (String s : ar) {
                br.add(s.trim());
            }
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

            for (int i = 0; i < st.length(); i++) {
                if (st.charAt(i) == '=') {
                    inv++;
                }
            }
            if (inv > 1) {
                System.out.println("Invalid assignment");
            } else if (inv == 1) {
                br.clear();
                ar = st.split("=");
                for (String s : ar) {
                    s = s.trim();
                    br.add(s);
                }
                String s1 = br.get(0);
                String s2 = br.get(1);
                int ck1 = 0;
                for (int i = 0; i < s1.length(); i++) {
                    if ((s1.charAt(i) < 'a' || s1.charAt(i) > 'z') && (s1.charAt(i) < 'A' || s1.charAt(i) > 'Z')) {
                        ck1 = 1;
                        break;
                    }
                }
                if (ck1 == 1) {
                    System.out.println("Invalid identifier");
                    continue;
                }

                int ck2 = 0, ck3 = 0;
                for (int i = 0; i < s2.length(); i++) {
                    if ((s2.charAt(i) < 'a' || s2.charAt(i) > 'z') && (s2.charAt(i) < 'A' || s2.charAt(i) > 'Z')) {
                        ck2 = 1;
                    } else {
                        ck3 = 1;
                    }
                }
//                System.out.println(ck2+" --)"+ck3);
                if (ck2 == 1 && ck3 == 1) {
                    System.out.println("Invalid assignment");
                    continue;
                }
                if (ck3 == 1) {
//                    System.out.println("kjfhvlkjshdkjlhf");
                    if (myMap.containsKey(s2)) {
                        String xx = myMap.get(s2);
                        myMap.put(s1, xx);
                    } else {
                        System.out.println("Unknown variable");
                    }
                } else if (ck2 == 1) {
                    myMap.put(s1, s2);
                }

            } else {
                br.clear();
                for (String value : ar) {
                    br.add(value.trim());
                }
                for (int j = 0; j < br.size(); j++) {
                    if (br.get(j).charAt(0) == '+') {
                        br.set(j, "+");
                    } else if (br.get(j).charAt(0) == '-') {
                        if (br.get(j).length() % 2 == 1) {
                            br.set(j, "-");
                        } else {
                            br.set(j, "+");
                        }
                    }
                }
                var ii = new ArrayList<Integer>();
                var op = new ArrayList<String>();
                int cc = 0;
                for (String value : br) {
                    if (value.equals("+") || value.equals("-")) {
                        op.add(value);
                    } else {
                        if (myMap.containsKey(value)) {
                            ii.add(Integer.parseInt(myMap.get(value)));
                        } else {
                            int ca = 0;
                            for (int i = 0; i < value.length(); i++) {
                                if ((value.charAt(i) < 'a' || value.charAt(i) > 'z') && (value.charAt(i) < 'A' || value.charAt(i) > 'Z')) {
                                    ca = 1;
                                    break;
                                }
                            }
                            if (ca == 1) {
                                ii.add(Integer.parseInt(value));
                            } else {
                                System.out.println("Unknown variable");
                                cc = 1;
                                break;
                            }
                        }
                    }
                }
                if (cc == 0) {
                    int s = ii.get(0);
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
    }
}
