# SMART CALCULATOR

The program supports for multiplication *, integer division / , parentheses (...), addition + and subtraction -.

Here is an example of an expression that contains all possible operations:

    3 + 8 * ((4 + 3) * 2 + 1) - 6 / (2 + 1)
The result is 121.

A general expression can contain many parentheses and operations with different priorities. It is difficult to calculate such expressions if you do not use special methods.

That's why I used `postfix notation`.

##Input/Output example

    >8 * 3 + 12 * (4 - 2)
    48
    >2 - 2 + 3
    3
    >4 * (2 + 3
    Invalid expression
    >-10
    -10
    >a=4
    >b=5
    >c=6
    a*2+b*3+c*(2+3)
    53
    >1 +++ 2 * 3 -- 4
    11
    >3 *** 5
    Invalid expression
    >4+3)
    Invalid expression