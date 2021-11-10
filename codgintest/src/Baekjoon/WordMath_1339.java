package Baekjoon;

import java.util.*;

public class WordMath_1339 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();
        List<String> words = new ArrayList<>();
        for(int i = 0; i < n; i++)
            words.add(sc.nextLine());

        Solution sol = new Solution(n, words);
        System.out.println(sol.solve());
        return;
    }

    static class Solution{

        int n;
        List<String> words;
        List<Integer> alphas = new ArrayList<>();
        int [] priority = new int[26];

        public Solution(int n, List<String> words) {
            this.n = n;
            this.words = words;
        }

        int solve(){
            getPriority();

            for(int i = 0 ; i < 26; i++)
                alphas.add(i);
            alphas.sort((x, y) -> priority[x] < priority[y] ? 1 : -1);

            return getSum();
        }

        void getPriority(){
            for(String s : words){
                int size = s.length();
                for(int i = 0 ; i < size ; i++){
                    int alpha = s.charAt(i) - 'A';
                    priority[alpha] += Math.pow(10, size - i - 1);
                }
            }
        }

        int getSum(){
            int value = 9;
            int sum = 0;
            for(Integer alpha : alphas){
                if(priority[alpha] == 0) continue;
                sum += priority[alpha] * value--;
            }
            return sum;
        }
    }
}
