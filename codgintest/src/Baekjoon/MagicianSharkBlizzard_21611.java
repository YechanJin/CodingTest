package Baekjoon;

import java.util.*;

public class MagicianSharkBlizzard_21611 {

    static int [] dr = {-1, 1, 0, 0};
    static int [] dc = {0,0, -1,1};
    static int [] turnRight = {3,2,0,1};
    static int [][]map;
    static int [] arr;
    static int n;
    static int tail;
    static int [] marble = new int[3];


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int m = sc.nextInt();
        map = new int[n][n];
        arr = new int[n*n];
        for(int i = 0 ; i< n;i++){
            for(int j = 0 ; j < n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        numbering();
        for(int i = 0; i < m; i++){
            int dir = sc.nextInt();
            int dist = sc.nextInt();
            blizzard(n/2,n/2,dir-1,dist);
        }
        int answer = 0;
        for(int i = 0 ; i < 3; i++){
            answer += (i+1) * marble[i];
        }
        System.out.println(answer);
        return;
    }

    static void numbering(){
        int r = 0, c = 0;
        int nr = r, nc = c;
        int dir = 3;
        int num = n*n-1;
        tail = -1;
        arr[0] = 100;
        while(r != n/2 || c != n/2){
            arr[num] = map[r][c];
            if(tail < 0 && arr[num] != 0)
                tail = num;
            map[r][c] = num;
            nr = r + dr[dir];
            nc = c + dc[dir];

            if(isTurningPoint(nr,nc, n,num)){
                dir = turnRight[dir];
                nr = r + dr[dir];
                nc = c + dc[dir];
            }
            r = nr;
            c = nc;
            num--;
        }
    }

    static boolean isTurningPoint(int r, int c, int n, int num){
        if(r >= n || r < 0) return true;
        if(c >= n || c < 0) return true;
        if(map[r][c] > num) return true;
        return false;
    }

    static void blizzard(int r, int c, int dir, int dist) {
        for (int i = 0; i < dist; i++) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            arr[map[nr][nc]] = 0;
            r = nr;
            c = nc;
        }

        while(moveMarble()){
            boom();
        }
        grouping();
    }

    static boolean moveMarble(){
        boolean move = false;

        for(int i = tail = getTail() ; i > 0; i--){
            if(arr[i] != 0) continue;
            move = true;
            int gap = 0;
            while(i > 0 && arr[i] == 0){
                gap++;
                i--;
            }
            i++;
            for(int j = i; j+gap <= tail; j++)
                arr[j] = arr[j+gap];
            for(int j = tail; j > tail-gap; j--){
                arr[j] = 0;
            }
            tail -= gap;
        }
        return move;
    }

    static void boom(){
        int now = tail = getTail();
        while(now > 0){
            int val = arr[now];
            int cnt = 0;
            while(arr[now] == val){
                cnt++;
                now--;
            }
            if(cnt < 4) continue;
            marble[val-1] += cnt;
            for(int i = 1; i <= cnt; i++){
                arr[i+now] = 0;
            }
        }
    }

    static void grouping() {
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < n * n && arr[i] != 0; i++) {
            int cnt = 0;
            int now = arr[i];
            while (arr[i + cnt] == now) {
                cnt++;
            }
            q.addLast(cnt);
            q.addLast(now);
            i += cnt - 1;
        }

        for (int i = 1; i < n * n; i++) {
            if(!q.isEmpty())
                arr[i] = q.pollFirst();
            else
                arr[i] = 0;
        }
    }
    static int getTail(){
        int tail = n*n-1;
        while(arr[tail] == 0){
            tail--;
        }
        return tail;
    }
}

