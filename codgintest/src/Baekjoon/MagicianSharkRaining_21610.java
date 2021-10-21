package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MagicianSharkRaining_21610 {

    static int n, m;
    static int [] dr = {0,-1,-1,-1,0,1,1,1};
    static int [] dc = {-1,-1,0,1,1,1,0,-1};
    static int [][] map;
    static boolean [][] cloud;
    static int [] direction;
    static int [] distance;
    static List<Integer> rows = new ArrayList<>();
    static List<Integer> cols = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];
        cloud = new boolean[n][n];
        for(int i = 0 ; i<n;i++){
            for(int j = 0 ; j< n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        for(int i = 1; i <= 2; i++){
            cloud[n-i][0] = cloud[n-i][1] = true;
        }

        direction = new int[m];
        distance = new int[m];
        for(int i = 0; i < m;i++){
            direction[i] = sc.nextInt()-1;
            distance[i] = sc.nextInt();
        }

        for(int i = 0 ; i < m; i++){
            move(direction[i], distance[i]);
            bug();
            makeCloud();
            rows.clear();
            cols.clear();
        }

        int answer = 0;
        for(int i = 0 ; i<n;i++){
            for(int j = 0 ; j< n; j++){
                answer += map[i][j];
            }
        }
        System.out.println(answer);
        return;
    }

    static void move(int dir, int dist){
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                if(cloud[i][j]){
                    cloud[i][j] = false;
                    int nr = bounding(i + dist*dr[dir]);
                    int nc = bounding(j + dist*dc[dir]);
                    rows.add(nr);
                    cols.add(nc);
                }
            }
        }

        for(int i = 0 ; i < rows.size(); i++){
            int r = rows.get(i);
            int c = cols.get(i);
            cloud[r][c] = true;
            map[r][c]++;
        }
    }

    static int bounding(int num){
        if(num >=n) return num%n;
        if(num < 0){
            while(num < 0)
                num += n;
            return num;
        }
        return num;

    }
    static void bug(){
        for(int i = 0 ; i < rows.size(); i++){
            int r = rows.get(i);
            int c = cols.get(i);
            int cnt = 0;
            for(int j = 0; j < 4; j++){
                int nr = r + dr[j*2+1];
                int nc = c + dc[j*2+1];
                if(nr < 0 || nr >= n) continue;
                if(nc < 0 || nc >= n) continue;
                if(map[nr][nc] < 1) continue;
                cnt++;
            }
            map[r][c] += cnt;
        }
    }

    static void makeCloud(){
        for(int i = 0; i< n; i++){
            for(int j = 0; j<n;j++){
                if(cloud[i][j]){
                    cloud[i][j] = false;
                    continue;
                }
                if(map[i][j] < 2) continue;
                map[i][j] -= 2;
                cloud[i][j] = true;
            }
        }
    }
}