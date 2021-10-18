package SamsungExpertAcademy;

import java.util.Scanner;

public class Honey_2115 {
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int c = sc.nextInt();
            Solution_2115 sol = new Solution_2115();
            int [][] map = new int[n][n];
            sol.map = map;
            for(int i = 0; i< n; i++){
                for(int j = 0; j< n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            System.out.println("#" + test_case + " " + sol.solve(n,m,c));

        }
    }
}


class Solution_2115 {
    int [][] map;

    int solve(int n, int m, int c){
        int [][] max = new int[n][n];
        for(int i = 0 ; i < n;i++) {
            for (int j = 0; j + m <= n; j++){
                max[i][j] = dfs(i,j, m,c, 0, 0, 0);
            }
        }

        int answer = 0 ;
        for(int i = 0 ; i < n; i++) {
            for (int j = 0; j + m <= n; j++) {
                for(int k = i; k < n ; k++){
                    for(int l = 0; l + m <= n; l++){
                        if(i==k && j >= l) continue;
                        if(isConflict(i,j,k,l,m)) continue;

                        int temp = max[i][j] + max[k][l];
                        answer = Math.max(temp, answer);
                    }
                }
            }
        }
        return answer;
    }

    boolean isConflict(int r1, int c1, int r2, int c2, int m){
        if(r1 == r2 && Math.abs(c1-c2) <= m) return true;
        return false;
    }

    int dfs(int i, int j, int m, int c, int check, int curVal, int profit){
        if(curVal > c)
            return -1;
        if(check >= m){
            return profit;
        }

        int val = map[i][j];
        int t1 = dfs(i, j+1, m, c, check+1, curVal + val, profit + val*val);
        int t2 = dfs(i, j+1, m, c, check+1, curVal, profit);
        return Math.max(t1,t2);
    }

}