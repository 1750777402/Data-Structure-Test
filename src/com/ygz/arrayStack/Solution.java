package com.ygz.arrayStack;

/**
 * 括号匹配问题
 */
public class Solution {

    YgzArrayStack<Character> stack = new YgzArrayStack<>();

    public boolean isValid(String s) {
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty())
                    return false;
                char curC = stack.pop();
                if(')' == c && curC != c)
                    return false;
                if(']' == c && curC != c)
                    return false;
                if('}' == c && curC != c)
                    return false;
            }
        }
        return stack.isEmpty();
    }

}
