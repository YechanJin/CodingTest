package Baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BabyShark_16236 {


    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        Solution_16236 sol = new Solution_16236();
        sol.n = sc.nextInt();
        sol.map = new int[sol.n][sol.n];
        int r = 0,c = 0;
        for(int i = 0 ; i < sol.n; i++){
            for(int j = 0; j < sol.n;j++){
                sol.map[i][j] = sc.nextInt();
                if(sol.map[i][j] == 9){
                    sol.sr = i;
                    sol.sc = j;
                    sol.map[i][j] = 0;
                }
            }
        }
        System.out.println(sol.solve(r,c));
        return;
    }
}

class Solution_16236 {
    final int [] dr = {-1,0,0,1};
    final int [] dc = {0,-1,1,0};
    int n;
    int [][] map;
    int sharkSize = 2;
    int eatCnt = 0;
    int sr, sc;

    int solve(int r, int c){
        int answer = 0;

        while(true){
            int sec = searching();
            if(sec < 0)
                break;
            answer += sec;
            if(eatCnt == sharkSize){
                sharkSize++;
                eatCnt = 0;
            }
        }

        return answer;
    }
    int searching(){
        boolean [][] visit = new boolean[n][n];
        boolean eat = false;
        Deque<State_16236> q = new ArrayDeque<>();
        q.add(new State_16236(sr,sc, 0));

        State_16236 result = new State_16236(n,n,n*n);
        while(!q.isEmpty()){
            State_16236 now = q.pollFirst();

            if(map[now.r][now.c] > 0 && map[now.r][now.c] < sharkSize){
                eat = true;
                if(better(result, now))
                    result = now;
            }

            for(int i = 0 ; i < 4; i++){
                //왔던길로 다시갈때
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(nr < 0 || nr >= n) continue;
                if(nc < 0 || nc >= n) continue;
                //나보다 큰 물고기
                if(map[nr][nc] > sharkSize) continue;
                //방문한 곳
                if(visit[nr][nc]) continue;

                visit[nr][nc] = true;
                q.addLast(new State_16236(nr,nc, now.sec+1));
            }
        }
        if(eat){
            eatCnt++;
            map[result.r][result.c] = 0;
            sr = result.r;
            sc = result.c;
        }
        return eat ? result.sec : -1;
    }


    boolean better(State_16236 a, State_16236 b){
        if(a.sec < b.sec) return false;
        else if(a.sec > b.sec) return true;
        if(a.r < b.r) return false;
        else if(a.r > b.r) return true;
        if(a.c < b.c) return false;
        else if(a.c > b.c) return true;
        return true;
    }
}

class State_16236 {
    int r;
    int c;
    int sec;

    State_16236(int r, int c, int sec){
        this.r = r;
        this.c = c;
        this.sec = sec;
    }
}