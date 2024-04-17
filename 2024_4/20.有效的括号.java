class Solution {
    public boolean isValid(String s) {
        char[] ch = s.toCharArray();
        int len = s.length();
        char[] stack = new char[len];
        int j = 0;

        for (int i = 0; i < len; i++) {
            if (ch[i] == '(' || ch[i] == '[' || ch[i] == '{') {
                stack[j] = ch[i];
                j++;
            } else {
                if (j == 0)
                    return false; // Stack is empty but got a closing bracket

                char top = stack[j - 1];
                if ((ch[i] == ')' && top == '(') || (ch[i] == ']' && top == '[') || (ch[i] == '}' && top == '{')) {
                    j--;
                } else {
                    return false;
                }
            }
        }
        return j == 0; // Return true if stack is empty, false otherwise
    }
}