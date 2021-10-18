package Baekjoon;

import java.util.Scanner;

public class LadderManipulate_15684 {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n,m, h;
        n = sc.nextInt();
        m = sc.nextInt();
        h = sc.nextInt();
        int [][] lad = new int[h][n-1];
        for(int i = 0 ; i < m; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            lad[a-1][b-1] = 1;
        }

        Solution_15684 sol = new Solution_15684();
        sol.lad = lad;
        sol.n = n;
        sol.h = h;
        System.out.println(sol.solve());
        return;
    }
}
class Solution_15684 {
    int [][] lad;
    int n;
    int h;
    int min = 10;

    int solve(){
        if(isNtoN())
            return 0;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < n-1; j++) {
                dfs(i,j,1);
            }
        }
        return min > 3 ? -1 : min;
    }
    void dfs(int hn, int now, int cnt){

        if(cnt > 3) return ;
        if(lad[hn][now] == 1) return ;
        if(now > 0 && lad[hn][now-1] == 1) return ;
        if(now + 1 < n - 1 && lad[hn][now+1] == 1) return ;

        lad[hn][now] = 1;
        if(isNtoN() && min > cnt)
            min = cnt;

        for(int i = hn; i < h; i++){
            for(int j = 0; j < n-1; j++){
                if(i == hn && j <= now) continue;
                dfs(i, j, cnt+1);
            }
        }

        lad[hn][now] = 0;

    }

    boolean isNtoN(){
        for(int i = 0 ; i < n; i++){
            if(evaluate(i) != i){
                return false;
            }
        }
        return true;
    }

    int evaluate(int now){
        for(int i = 0 ; i < h; i++){
            if(now-1 >= 0 && lad[i][now-1] == 1){
                now = now-1;
            }
            else if(now+1 < n && lad[i][now] == 1){
                now = now + 1;
            }
        }
        return now;
    }
}
