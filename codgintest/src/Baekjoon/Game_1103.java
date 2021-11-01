package Baekjoon;

import java.util.Scanner;

public class Game_1103 {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int n,m;
        n = sc.nextInt();
        m = sc.nextInt();

        int[][] map = new int[n][m];
        sc.nextLine();
        for(int i = 0 ; i < n ; i++){
            String input = sc.nextLine();
            for(int j = 0 ; j < m; j++){
                char c = input.charAt(j);
                if(c != 'H')
                    map[i][j] = c - '0';
            }
        }

        Solution sol = new Solution(n,m,map);
        System.out.println(sol.solve());
        return;
    }

    static class Solution{

        final static int [] dr = {-1,1,0,0};
        final static int [] dc = {0,0,-1,1};
        final static int INF = 2123456789;

        int n,m;
        int [][] map;
        int [][] moveCnt;
        boolean [][] visit;

        Solution(int n, int m, int [][] map){
            this.n = n;
            this.m = m;
            this.map = map;
            moveCnt = new int[n][m];
            visit = new boolean[n][m];
        }
        int solve(){
            visit[0][0] = true;
            int answer = dfs(0,0);
            return answer == INF ? -1 : answer;
        }

        int dfs(int r, int c){
            if(moveCnt[r][c] > 0)
                return moveCnt[r][c];

            int ret = 0;
            int dist = map[r][c];
            for(int i = 0; i < 4; i++){
                int nr = r + dr[i] * dist;
                int nc = c + dc[i] * dist;
                if(isOut(nr,nc) || isHole(nr,nc)) continue;
                if(visit[nr][nc]){
                    ret = INF;
                    continue;
                }
                visit[nr][nc] = true;
                ret = Math.max(ret, dfs(nr, nc));
                visit[nr][nc] = false;
            }
            moveCnt[r][c] = ret == INF ? INF : ret + 1;
            return moveCnt[r][c];
        }

        boolean isOut(int r, int c){
            if(r < 0 || r >= n) return true;
            else if(c < 0 || c >= m) return true;
            else return false;
        }
        boolean isHole(int r, int c){
            return map[r][c] <= 0;
        }
    }
}
