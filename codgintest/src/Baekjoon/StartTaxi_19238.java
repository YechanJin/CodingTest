package Baekjoon;

import java.util.*;

public class StartTaxi_19238 {

    final static int[] dr = {-1,1,0,0};
    final static int[] dc = {0,0,-1,1};
    static int n,m;
    static int gas;
    static int [][] map;
    static Loc taxi;
    static List<Loc> targets;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        gas = sc.nextInt();
        map = new int[n][n];

        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }

        taxi = new Loc(sc.nextInt()-1,sc.nextInt()-1,0);

        targets = new ArrayList<>();
        for(int i = 0 ; i < m;i++){
            int r1 = sc.nextInt()-1;
            int c1 = sc.nextInt()-1;
            int r2 = sc.nextInt()-1;
            int c2 = sc.nextInt()-1;
            targets.add(new Loc(r2,c2,0));
            map[r1][c1] = targets.size()+1;
        }

        while(true){
            int idx = findPassenger();
            //승객을 태울 수 없거나 연료가 모자랄 때
            if(idx < 0){
                gas = -1;
                break;
            }
            int use = goTarget(idx);
            //목적지로 가는 연료가 모자랄 때
            if(use < 0){
                gas = use;
                break;
            }
            gas += use;
            //더이상 태울 승객이 없을 때
            if(noMorePassenger()){
                break;
            }
        }
        System.out.println(gas);
        return;
    }

    static int findPassenger(){
        Deque<Loc> q = new ArrayDeque<>();
        boolean [][] visit = new boolean[n][n];
        boolean take = false;
        visit[taxi.r][taxi.c] = true;
        q.addLast(new Loc(taxi.r,taxi.c,0));

        Loc result = new Loc(n,n,n*n);
        while(!q.isEmpty()){
            Loc now = q.pollFirst();
            if(map[now.r][now.c] >= 2 && betterThanResult(result, now)){
                take = true;
                result = now;
            }
            for(int i = 0 ; i<4;i++){
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(cannotGo(nr,nc,visit)) continue;

                visit[nr][nc] = true;
                q.addLast(new Loc(nr,nc,now.dist+1));
            }
        }
        if(!take || gas < result.dist){
            return -1;
        }

        return takePassenger(result);
    }

    static boolean betterThanResult(Loc result, Loc compare){
        if(result.dist > compare.dist) return true;
        else if(result.dist < compare.dist) return false;
        if(result.r > compare.r) return true;
        else if(result.r < compare.r) return false;
        if(result.c > compare.c) return true;
        else if(result.c < compare.c) return false;
        return true;
    }

    static int takePassenger(Loc passenger){
        taxi.r = passenger.r;
        taxi.c = passenger.c;
        gas -= passenger.dist;
        int ret = map[taxi.r][taxi.c];
        map[taxi.r][taxi.c] = 0;
        return ret-2;
    }

    static int goTarget(int idx){
        Loc target = targets.get(idx);
        Deque<Loc> q = new ArrayDeque<>();
        boolean [][] visit = new boolean[n][n];
        q.add(new Loc(taxi.r,taxi.c, 0));
        visit[taxi.r][taxi.c] = true;

        int answer = -1;
        while(!q.isEmpty()){
            Loc now = q.pollFirst();

            if(now.r == target.r && now.c == target.c){
                taxi.r = now.r;
                taxi.c = now.c;
                answer = now.dist;
                break;
            }
            for(int i = 0; i< 4; i++){
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(cannotGo(nr,nc, visit)) continue;
                visit[nr][nc] = true;
                q.addLast(new Loc(nr,nc, now.dist+1));
            }
        }

        return answer >= 0 && answer <= gas ? answer : -1;
    }

    static boolean cannotGo(int r, int c, boolean [][] visit){
        if(r < 0 || r >= n) return true;
        if(c < 0 || c >= n) return true;
        if(map[r][c] == 1) return true;
        if(visit[r][c]) return true;
        return false;
    }

    static boolean noMorePassenger(){
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                if(map[i][j] >= 2)
                    return false;
            }
        }
        return true;
    }

    static class Loc{
        int r,c, dist;
        Loc(int r, int c, int dist){
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

    }
}