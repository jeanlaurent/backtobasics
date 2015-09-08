package net.morlhon.exos.Ardent14;

import java.util.List;
import java.util.Stack;

import static java.util.Arrays.asList;

public class Parentheses {

    List<Character> openingParentheses = asList('(', '[', '{');
    List<Character> closingParentheses = asList(')', ']', '}');

    public boolean isBalanced(String string) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            if (openingParentheses.contains(string.charAt(i))) {
                stack.push(string.charAt(i));
            }
            int index;
            if ((index = closingParentheses.indexOf(string.charAt(i))) != -1)
                if (stack.pop() != openingParentheses.get(index)) {
                    return false;
            }
        }
        return stack.isEmpty();
    }

}
