package Baekjoon;

import java.util.Scanner;

public class Teaching_1062 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n,k;
        int [] words;

        n = sc.nextInt();
        k = sc.nextInt();
        sc.nextLine();
        words = new int[n];

        for(int i = 0 ; i < n; i++){
            String input = sc.nextLine();
            for(int j = 0 ; j < input.length(); j++){
                int t = input.charAt(j) - 'a';
                words[i] |= 1 << t;
            }
        }

        Solution sol = new Solution();
        sol.n = n;
        sol.k = k;
        sol.words = words;

        System.out.println(sol.solve());
        return;
    }

    static class Solution{

        int n, k;
        int [] words;
        boolean [] visit = new boolean[26];

        int init(){
            int ret = 0;
            char [] base = {'a','c','i','n','t'};
            for(int i = 0 ; i < 5; i++) {
                ret = addSet(ret, base[i]);
                visit[base[i]-'a'] = true;
            }
            return ret;
        }

        int solve(){
            int base = init();
            if(k < 5)
                return 0;

            if(k == 5)
                return getReadNum(base);

            return dfs(base, 5, 0);
        }

        int dfs(int set, int cnt, int now){
            if(cnt >= k){
                return 0;
            }
            int res = 0;
            for(int i = now+1; i < 26; i++){
                if(visit[i]) continue;
                char c = (char)(i +'a');
                set = addSet(set, c);
                visit[i] = true;

                if(cnt+1 == k)
                    res = Math.max(getReadNum(set), res);
                else
                    res = Math.max(dfs(set, cnt+1, i), res);

                visit[i] = false;
                set = removeSet(set, c);
            }
            return res;
        }
        int getReadNum(int set){
            int cnt = 0;
            for(int i = 0; i < n; i++){
                cnt = isPossible(set, words[i]) ? cnt + 1 : cnt;
            }
            return cnt;
        }

        int addSet(int val, char c){
            int t = c - 'a';
            val |= 1 << t;
            return val;
        }
        int removeSet(int val, char c){
            int t = c - 'a';
            val ^= 1 << t;
            return val;
        }
        boolean isPossible(int val1, int val2){
            int ret = val1 & val2;
            return ret == val2;
        }
    }
}
