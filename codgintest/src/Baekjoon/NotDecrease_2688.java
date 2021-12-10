package Baekjoon;

import java.util.Scanner;

public class NotDecrease_2688 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for(int i = 0 ; i < t; i++){
            int n = sc.nextInt();
            System.out.println(solve(n));
        }
        return;
    }
    public static long solve(int n){
        long [][]dp = new long[10][n+1];

        for(int i = 0 ; i < 10; i++){
            dp[i][0] = 1;
            for(int j = 1 ; j < n+1; j++){
                dp[i][j] += i > 0 ? dp[i-1][j] : 0;
                dp[i][j] += dp[i][j-1];
            }
        }
        return dp[9][n];
    }
}
