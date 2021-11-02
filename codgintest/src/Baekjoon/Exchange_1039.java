package Baekjoon;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Exchange_1039 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Solution sol = new Solution(sc.nextInt(), sc.nextInt());
        System.out.println(sol.bfs());
        return;
    }

    static class Solution{

        final static int maximum = 1000000;
        int n, k, m;
        boolean [][] visit;

        Solution(int n, int k){
            this.n = n;
            this.k = k;
            this.m = Integer.toString(n).length();
            this.visit = new boolean[k][maximum+1];
        }

        int bfs(){
            Queue<Integer> q = new ArrayDeque<>();
            q.add(n);

            int max = -1;
            int cnt = 0;
            while(cnt < k && !q.isEmpty()){
                int size = q.size();
                for(int i = 0 ; i < size ; i++){
                    int num = q.poll();
                    StringBuilder now = new StringBuilder(Integer.toString(num));
                    for(int j = 0 ; j < m; j++){
                        for(int k = j+1 ; k < m; k++){
                            if(j == 0 && now.charAt(k) == '0') continue;

                            swap(j,k, now);
                            int next = Integer.parseInt(now.toString());
                            swap(j,k, now);

                            if(visit[cnt][next]) continue;
                            visit[cnt][next] = true;

                            if(cnt + 1 == this.k)
                                max = Math.max(max, next);
                            else
                                q.add(next);

                        }
                    }
                }
                cnt++;
            }
            return max;
        }

        void swap(int i, int j, StringBuilder num){
            char c = num.charAt(i);
            num.setCharAt(i,num.charAt(j));
            num.setCharAt(j, c);
        }
    }
}
