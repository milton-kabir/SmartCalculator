//package calculator;
package com.kabir.milton;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String ALPHABET = "[a-zA-Z]+";
    private static final String NUMBER = "[+-]?\\d+";
    private static final Pattern ASSIGNMENT_PATTERN = Pattern.compile("^(.+?)=(.+?)$");
    private static final Map<String, String> variable_storage = new HashMap<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            String input = s.nextLine();
            if (input.matches("/\\w+")) {
                if (input.equals("/exit")) {
                    System.out.println("Bye!");
                    return;
                } else if (input.equals("/help")) {
                    System.out.println("The program calculates the sum of numbers using addition and subtraction");
                } else {
                    System.out.println("Unknown command");
                }
                continue;
            }
            if (assignVariable(input) || isEmptyInput(input)) {
                continue;
            }
            try {
                System.out.println(calculate(convertInfixToPostfix(input)));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static boolean assignVariable(String input) {
        Matcher matcher = ASSIGNMENT_PATTERN.matcher(removeSpaces(input));
        if (matcher.matches()) {
            String identifier = matcher.group(1);
            if (!identifier.matches(ALPHABET)) {
                System.out.println("Invalid identifier");
                return true;
            }
            String assignment = matcher.group(2);
            if (assignment.matches(ALPHABET)) {
                if (variable_storage.containsKey(assignment)) {
                    variable_storage.put(identifier, variable_storage.get(assignment));
                } else {
                    if (!variable_storage.containsKey(assignment)) {
                        System.out.println("Unknown variable");
                        return true;
                    } else {
                        variable_storage.put(identifier, variable_storage.get(assignment));
                    }
                }
            } else if (assignment.matches(NUMBER)) {
                variable_storage.put(identifier, assignment);
            } else {
                System.out.println("Invalid assignment");
            }
            return true;
        }
        return false;
    }

    static String removeSpaces(String input) {
        return input.replaceAll("\\s+", "");
    }

    static boolean isEmptyInput(String input) {
        return input == null || input.isBlank();
    }

    static String calculate(Deque<String> postfix) {
        Deque<String> stack = new ArrayDeque<>();

        while (!postfix.isEmpty()) {
            final String incoming = postfix.removeFirst();

            if (incoming.matches(NUMBER)) {
                stack.offerFirst(incoming);
            } else if (incoming.matches(ALPHABET)) {
                if (variable_storage.containsKey(incoming)) {
                    stack.offerFirst(variable_storage.get(incoming));
                } else {
                    if (variable_storage.containsKey(incoming)) {
                        stack.offerFirst(variable_storage.get(incoming));
                    } else {
                        throw new IllegalArgumentException("Unknown variable");
                    }
                }
            } else {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression");
                } else {
                    final String second = stack.removeFirst();
                    final String first = stack.removeFirst();
                    stack.offerFirst(processNumber(incoming, first, second));

                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return stack.peekFirst();
    }

    static String processNumber(String operator, String firstNum, String secondNum) {
        switch (operator) {
            case "*":
                return new BigInteger(firstNum).multiply(new BigInteger(secondNum)).toString();
            case "/":
                return new BigInteger(firstNum).divide(new BigInteger(secondNum)).toString();
            case "+":
                return new BigInteger(firstNum).add(new BigInteger(secondNum)).toString();
            case "-":
                return new BigInteger(firstNum).subtract(new BigInteger(secondNum)).toString();
            default:
                throw new IllegalArgumentException("Unknown Operator");
        }
    }

    static Deque<String> convertInfixToPostfix(String expression) {
        Deque<String> result = new ArrayDeque<>();
        Deque<String> stack = new ArrayDeque<>();
        final Pattern pattern = Pattern.compile("(^[+-]?\\d+)|(\\d+)|([a-zA-Z]+)|(([-+^/*])\\5*)|([()])");
        final Matcher matcher = pattern.matcher(expression);
        while (matcher.find()) {
            String incoming = matcher.group();
            if (incoming.matches("[+-]?\\d+|[a-zA-Z]+")) {
                result.add(incoming);
            } else {
                String operator;
                if (incoming.length() == 1) {
                    operator = incoming;
                } else {
                    String first = incoming.substring(0, 1);
                    if (!"+".equals(first) && !"-".equals(first)) {
                        throw new IllegalArgumentException("Invalid expression");
                    }
                    if (incoming.length() % 2 == 0) {
                        operator = "+";
                    } else operator = first;
                }
                final String leftParenthesis = "(";
                final String rightParenthesis = ")";
                if (rightParenthesis.equals(operator)) {
                    boolean leftParenthesisFound = false;
                    while (!stack.isEmpty()) {
                        final String top = stack.removeFirst();
                        if (leftParenthesis.equals(top)) {
                            leftParenthesisFound = true;
                            break;
                        }
                        result.add(top);
                    }
                    if (!leftParenthesisFound) {
                        throw new IllegalArgumentException("Invalid expression");
                    }
                    continue;
                }
                if (leftParenthesis.equals(operator) || stack.isEmpty() || leftParenthesis.equals(stack.peekFirst())) {
                    stack.offerFirst(operator);
                    continue;
                }
                final int incomingPrecedence = getOperatorPrecedence(operator);
                if (incomingPrecedence <= getOperatorPrecedence(stack.peekFirst())) {
                    result.add(stack.removeFirst());
                    while (!stack.isEmpty()) {
                        final String top = stack.peekFirst();
                        if (leftParenthesis.equals(top) || incomingPrecedence > getOperatorPrecedence(top)) {
                            break;
                        }
                        result.add(stack.removeFirst());
                    }
                }
                stack.offerFirst(operator);
            }
        }
        result.addAll(stack);
        if (result.contains("(")) {
            throw new IllegalArgumentException("Invalid expression");
        }
        return result;
    }

    static int getOperatorPrecedence(String operator) {
        switch (operator) {
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                throw new IllegalArgumentException("Unknown Operator");
        }
    }
}