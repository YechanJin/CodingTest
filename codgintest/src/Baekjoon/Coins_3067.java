package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Coins_3067 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        List<Integer> coins = new ArrayList<>();

        for(int i = 0 ; i < t; i++){
            coins.clear();
            int n = sc.nextInt();
            for(int j = 0; j < n; j++)
                coins.add(sc.nextInt());
            int value = sc.nextInt();
            System.out.println(solve(coins, value));
        }
        return;
    }

    public static int solve(List<Integer> coins, int value){
        int [][] dp = new int[coins.size()][value+1];
        dp[0][0] = 1;

        for(int i = 0; i < coins.size(); i++){
            int coin = coins.get(i);
            for(int j = 0 ; j <= value; j++){
                dp[i][j] += i > 0 ? dp[i-1][j] : 0;
                dp[i][j] += j - coin >= 0 ? dp[i][j-coin] : 0;
            }
        }
        return dp[coins.size()-1][value];
    }
}
