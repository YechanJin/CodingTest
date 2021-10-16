package Baekjoon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

public class Easy2048_12100 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [][] game = new int[n][n];
        int max = 0;
        for(int i = 0 ; i < n; i++){
            for(int j =0 ; j < n ; j++){
                game[i][j] = sc.nextInt();
                if(max < game[i][j])
                    max = game[i][j];
            }
        }
        Solution_12100 sol = new Solution_12100(n, max, game);
        sol.solve();
        System.out.println(sol.getResult());
        return;
    }
}

class Solution_12100 {
    int max =0;
    int n;
    int [][] game;

    Solution_12100(int n, int max, int [][] game){
        this.n = n;
        this.max = max;
        this.game = game;
    }

    void solve(){
        Queue<State_12100> q = new ArrayDeque<>();
        q.offer(new State_12100(0, game, this.max));
        while(!q.isEmpty()){
            State_12100 now = q.poll();
            if(this.max < now.max)
                this.max = now.max;
            if(now.cnt >= 5) continue;
            q.offer(swipeUp(now.game,now.cnt, now.max));
            q.offer(swipeDown(now.game,now.cnt, now.max));
            q.offer(swipeLeft(now.game,now.cnt, now.max));
            q.offer(swipeRight(now.game,now.cnt, now.max));
        }
    }

    State_12100 swipeLeft(int [][] game, int cnt, int max){
        int [][] newGame = new int[n][n];
        Deque<Integer> merged = new ArrayDeque<>();
        for(int i = 0; i< n; i++){
            merged.clear();
            int prev = 0;
            for(int j = 0 ; j < n; j++){
                if(game[i][j] == 0) continue;
               prev = merging(prev, merged, i, j, game);
            }
            if(prev != 0)
                merged.offerLast(prev);
            int j = 0;
            for(Integer t : merged){
                newGame[i][j++] = t;
                if(max < t)
                    max = t;
            }
        }
        return new State_12100(cnt+1, newGame, max);
    }
    State_12100 swipeRight(int [][] game, int cnt, int max){
        int [][] newGame = new int[n][n];
        Deque<Integer> merged = new ArrayDeque<>();

        for(int i = 0; i< n; i++){
            merged.clear();
            int prev = 0;
            for(int j = n-1 ; j >= 0; j--){
                if(game[i][j] == 0) continue;
                prev = merging(prev, merged, i, j, game);
            }
            if(prev != 0)
                merged.offerLast(prev);
            int j = n-1;
            for(Integer t : merged){
                newGame[i][j--] = t;
                if(max < t)
                    max = t;
            }
        }
        return new State_12100(cnt+1, newGame, max);
    }
    State_12100 swipeUp(int [][] game, int cnt, int max){
        int [][] newGame = new int[n][n];
        Deque<Integer> merged = new ArrayDeque<>();

        for(int i = 0; i< n; i++){
            merged.clear();
            int prev = 0;
            for(int j = 0 ; j < n; j++){
                if(game[j][i] == 0) continue;
                prev = merging(prev, merged, j, i, game);
            }
            if(prev != 0)
                merged.offerLast(prev);
            int j = 0;
            for(Integer t : merged){
                newGame[j++][i] = t;
                if(max < t)
                    max = t;
            }
        }
        return new State_12100(cnt +1, newGame, max);
    }
    State_12100 swipeDown(int [][] game, int cnt, int max){
        int [][] newGame = new int[n][n];
        Deque<Integer> merged = new ArrayDeque<>();
        for(int i = 0; i< n; i++){
            merged.clear();
            int prev = 0;
            for(int j = n-1 ; j >= 0; j--){
                if(game[j][i] == 0) continue;
                prev = merging(prev, merged, j, i, game);
            }
            if(prev != 0)
                merged.offerLast(prev);
            int j = n-1;
            for(Integer t : merged){
                newGame[j--][i] = t;
                if(max < t)
                    max = t;
            }
        }
        return new State_12100(cnt+1, newGame, max);
    }

    int merging(int prev, Deque<Integer> merged, int i, int j, int[][] game){
        if(prev == 0) return game[i][j];
        if(prev == game[i][j]) {
            merged.offerLast(game[i][j] * 2);
            return 0;
        }
        else {
            merged.offerLast(prev);
            return game[i][j];
        }
    }

    int getResult(){
        return max;
    }
}

class State_12100 {
    int [][] game;
    int cnt;
    int max;
    State_12100(int cnt, int[][] game, int max){
        this.cnt = cnt;
        this.game = game;
        this.max = max;

    }
}
