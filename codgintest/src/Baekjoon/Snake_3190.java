package Baekjoon;

import java.util.*;

public class Snake_3190 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int n, k, l;
        n = sc.nextInt();

        k = sc.nextInt();
        List<Loc_3190> apples = new ArrayList<>();
        for(int i = 0 ; i < k; i++){
            apples.add(new Loc_3190(sc.nextInt()-1, sc.nextInt()-1));
        }

        l = sc.nextInt();
        sc.nextLine();
        Deque<Turn_3190> turns = new ArrayDeque<>();
        for(int i = 0 ; i< l; i++){
            String[] temp = sc.nextLine().split(" ");
            turns.addLast(new Turn_3190(Integer.parseInt(temp[0]), temp[1].charAt(0)));
        }

        Solution_3190 sol = new Solution_3190();
        sol.n = n;
        sol.apples = apples;
        sol.turns = turns;

        System.out.println(sol.solve());
        return;
    }
}
class Solution_3190 {
    int n;
    int [][] board;
    List<Loc_3190> apples;
    Deque<Turn_3190> turns;

    int [] dr = {0, 0, 1, 0, -1};
    int [] dc = {0, 1, 0, -1, 0};

    int solve(){
        this.board = new int[n][n];
        Loc_3190 head = new Loc_3190(0,0);
        Loc_3190 tail = new Loc_3190(0,0);
        int dir = 1;
        board[0][0] = dir;
        int time = 0;

        while(dir > 0 && dir <= 4){
            time++;
            dir = next(head, tail, time);
        }

        return time;
    }

    int next(Loc_3190 head, Loc_3190 tail, int time){

        int dir = board[head.r][head.c];
        head.r += dr[dir];
        head.c += dc[dir];

        if(crash(head)) return -1;
        if(!getApple(head)){
            int r = tail.r;
            int c = tail.c;
            tail.r += dr[board[r][c]];
            tail.c += dc[board[r][c]];
            board[r][c] = 0;
        }
        dir = getDir(dir,time);
        board[head.r][head.c] = dir;
        return dir;
    }

    boolean crash(Loc_3190 head){
        if(head.r < 0 || head.r >= this.n) return true;
        if(head.c < 0 || head.c >= this.n) return true;
        if(board[head.r][head.c] > 0) return true;
        return false;
    }

    boolean getApple(Loc_3190 head){
        for(int i = 0 ; i < apples.size(); i++){
            if(head.same(apples.get(i))){
                apples.remove(i);
                return true;
            }
        }
        return false;
    }

    int getDir(int dir, int time){
        if(turns.isEmpty() || turns.peekFirst().sec != time) return dir;

        Turn_3190 t = turns.pollFirst();
        if(t.dir == 'L')
            dir = --dir < 1 ? 4 : dir;
        else if (t.dir == 'D')
            dir = ++dir > 4 ? 1 : dir;

       return dir;
    }
}
class Loc_3190 {
    int r,c;
    Loc_3190(int r, int c){
        this.r = r;
        this.c =c;
    }

    boolean same(Loc_3190 a){
        return this.r == a.r && this.c == a.c;
    }
}
class Turn_3190 {
    int sec;
    char dir;
    Turn_3190(int sec, char dir){
        this.sec = sec;
        this.dir = dir;
    }

}
