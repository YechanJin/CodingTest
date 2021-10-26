package Baekjoon;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Exit_3055 {

    final static int [] dr = {-1,1,0,0};
    final static int [] dc = {0,0,-1,1};

    static int n,m;
    static char [][] map;

    static Loc from;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        map = new char[n][m];

        for(int i = 0 ; i < n; i++){
            String s = sc.nextLine();
            for(int j = 0 ; j < m; j++){
                char c = s.charAt(j);
                map[i][j] = c;
                if(c == 'S')
                    from = new Loc(i, j);
            }
        }
        int answer = solve();
        System.out.println(answer < 0 ? "KAKTUS" : answer);

        return;
    }

    static int solve(){
        Queue<Loc> q = new ArrayDeque<>();
        Queue<Loc> wq = new ArrayDeque<>();

        q.add(from);
        for(int i = 0; i< n; i++)
            for(int j = 0; j < m; j++)
                if(map[i][j] == '*')
                    wq.add(new Loc(i,j));

        int time = 0;
        boolean find = false;
        while(!q.isEmpty() && !find){
            flowWater(wq);
            find = searching(q);
            time++;
        }
        return find ? time : -1;
    }

    static void flowWater(Queue<Loc> wq){
        int size = wq.size();
        while(size-- > 0){
            Loc now = wq.poll();
            for(int i = 0 ; i< 4; i++){
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(isOut(nr,nc) || isDest(nr,nc) || isWall(nr,nc) || isWater(nr,nc)) continue;

                map[nr][nc] = '*';
                wq.add(new Loc(nr, nc));
            }
        }
    }

    static boolean searching(Queue<Loc> q){
        boolean find = false;
        int size = q.size();

        while(size-- > 0 && !find){
            Loc now = q.poll();
            for(int i = 0 ; i< 4; i++){
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];
                if(isOut(nr,nc) || isWall(nr,nc) || isWater(nr,nc) || visit(nr,nc)) continue;

                if(isDest(nr,nc)){
                    find = true;
                    break;
                }
                map[nr][nc] = 'S';
                q.add(new Loc(nr, nc));
            }
        }
        return find;
    }

    static boolean visit(int r, int c){
        return map[r][c] == 'S';
    }
    static boolean isWater(int r, int c){
        return map[r][c] == '*';
    }
    static boolean isDest(int r, int c){
        return map[r][c] == 'D';
    }
    static boolean isWall(int r, int c){
        return map[r][c] == 'X';
    }
    static boolean isOut(int r, int c){
        if(r < 0 || r >=n) return true;
        if(c < 0 || c >=m) return true;
        return false;
    }
    static class Loc{
        int r, c;
        Loc(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}