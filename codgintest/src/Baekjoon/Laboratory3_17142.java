package Baekjoon;

import java.util.*;

public class Laboratory3_17142 {
    static int n,m;
    static List<State_17142> virList;
    static int [][] lab;
    final static int [] dr = {-1,1,0,0};
    final static int [] dc = {0,0,-1,1};

    public static void main(String[] args){


        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        virList = new ArrayList<>();
        lab = new int[n][n];

        for(int i = 0 ; i< n; i++){
            for(int j = 0 ; j< n; j++){
                lab[i][j] = sc.nextInt();
                if(lab[i][j] == 2){
                    virList.add(new State_17142(i,j,0));
                }
            }
        }
        int answer = -1;
        boolean [] take = new boolean[virList.size()];
        for(int i = 0 ; i < virList.size(); i++){
            take[i] = true;
            int temp = dfs(i,1,take);
            take[i] = false;

            if(temp < 0) continue;
            if(answer < 0 || temp < answer)
                answer = temp;
        }
        System.out.println(answer);
        return;
    }

    static int dfs(int now, int cnt, boolean [] take){

        if(cnt == m){
            return check(take);
        }
        int min = - 1;
        for(int i = now+1; i < virList.size(); i++){
            take[i] = true;
            int temp = dfs(i, cnt+1, take);
            take[i] = false;

            if(temp < 0) continue;
            if(min < 0 || min > temp)
                min = temp;
        }

        return min;
    }

    static int check(boolean [] take){
        boolean [][] visit = new boolean[n][n];
        Deque<State_17142> q = new ArrayDeque<>();
        int answer = 0;

        for(int i = 0; i < virList.size(); i++){
            State_17142 t = virList.get(i);
            if(take[i]) {
                q.addLast(new State_17142(t.r, t.c, t.sec));
                visit[t.r][t.c] = true;
            }

        }

        while(!q.isEmpty()){
            State_17142 now = q.pollFirst();

            if(lab[now.r][now.c] != 2 && now.sec > answer)
                answer = now.sec;

            for(int i = 0 ; i < 4; i++){
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(nr < 0 || nr >= n) continue;
                if(nc < 0 || nc >= n) continue;
                if(lab[nr][nc] == 1) continue;
                if(visit[nr][nc]) continue;

                visit[nr][nc] = true;
                q.addLast(new State_17142(nr,nc, now.sec +1));
            }
        }

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(lab[i][j] >= 1) continue;
                if(!visit[i][j])
                    return -1;
            }
        }
        return answer;
    }
}


class State_17142 {
    int r,c;
    int sec;
    State_17142(int r, int c, int sec){
        this.r = r;
        this.c = c;
        this.sec = sec;
    }
}