package Baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ValueOfBracket_2504 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Solution sol = new Solution(input);
        System.out.println(sol.solve());
        return;
    }

    static class Solution{
        String input;
        Deque<Character> stack;
        int [] depth;

        Solution(String input){
            this.input = input;
            this.stack = new ArrayDeque<>();
            this.depth = new int[input.length()];
        }

        int solve(){
            for(int i = 0 ; i < input.length(); i++){
                char c = input.charAt(i);
                if(c == '(' || c == '['){
                    stack.addLast(c);
                    continue;
                }
                if(isInvalidInput(c))
                    return 0;

                int size = stack.size();
                if(depth[size+1] > 0){
                    depth[size] += getValue(c) * depth[size+1];
                    depth[size+1] = 0;
                }
                else{
                    depth[size] += getValue(c);
                }
            }

            if(!stack.isEmpty())
                return 0;
            return depth[0];
        }

        boolean isInvalidInput(char c){
            return stack.isEmpty() || getValue(stack.pollLast()) != getValue(c);
        }

        int getValue(char bracket){
            if(bracket == '(' || bracket == ')') return 2;
            else return 3;
        }
    }
}
