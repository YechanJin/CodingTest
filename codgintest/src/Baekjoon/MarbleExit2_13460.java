package Baekjoon;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class MarbleExit2_13460 {

    public static void main(String[] args){

        int n, m;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sc.nextLine();
        int [][] board = new int[n][m];

        Solution_13460 sol = new Solution_13460(board);
        Loc_13460 R =null, B = null;

        for(int i = 0 ; i < n; i++){
            String input = sc.nextLine();
            for(int j = 0 ; j < m ; j++){
                char c = input.charAt(j);
                if(c == '#') continue;
                board[i][j] = 1;
                if(c == 'R'){
                    R = new Loc_13460(i,j);
                }
                else if(c == 'B'){
                    B = new Loc_13460(i,j);
                }
                else if(c == 'O'){
                    sol.O = new Loc_13460(i,j);
                }
            }
        }

        int ans = sol.solve(R, B);
        System.out.println(ans);
    }

}
class Solution_13460{


    final int [] dr = {0,1,0,-1};
    final int [] dc = {1, 0, -1, 0};
    int [][] board;
    Loc_13460 O;
    Loc_13460 out = new Loc_13460(-1,-1);

    Solution_13460(int [][] board){
        this.board = board;
    }

    int solve(Loc_13460 R, Loc_13460 B){
        int answer = 11;

        Queue<State_13460> q = new ArrayDeque<>();
        q.add(new State_13460(R,B,0));

        while(!q.isEmpty()){
            State_13460 state = q.poll();
            if(state.cnt > 10) continue;
            if(out.equals(state.R)){
                answer = state.cnt;
                break;
            }
            for(int i = 0 ; i < 4; i++){
                State_13460 next = tilt(state.R, state.B, i, state.cnt);
                if(next == null) continue;
                q.add(next);
            }
        }
        return answer <= 10 ? answer : -1;
    }

    State_13460 tilt(Loc_13460 R, Loc_13460 B, int dir, int cnt){
        Loc_13460 NR = new Loc_13460(R.r, R.c);
        Loc_13460 NB = new Loc_13460(B.r, B.c);
        Loc_13460 TR, TB ;

        while(true){
            TR = next(NR, dir);
            TB = next(NB, dir);
            if(TR.equals(NR) && TB.equals(NB))
                break;
            if(TR.equals(TB))
                break;
            NR = TR;
            NB = TB;

            if(O.equals(NR))
                NR = out;
            if(O.equals(NB))
                NB = out;
        }
        if(R.equals(NR) && B.equals(NB)) return null;
        if(NB.equals(out)) return null;
        return new State_13460(NR, NB, cnt +1);

    }

    Loc_13460 next(Loc_13460 loc, int i){
        int nr = loc.r + dr[i];
        int nc = loc.c + dc[i];
        if(out.equals(loc))
            return loc;
        if(board[nr][nc] != 0){
            return new Loc_13460(nr,nc);
        }
        return loc;
    }
}
class State_13460{
    Loc_13460 R;
    Loc_13460 B;
    int cnt;

    State_13460(Loc_13460 R, Loc_13460 B, int cnt){
        this.R = R;
        this.B = B;
        this.cnt = cnt;
    }
}
class Loc_13460{
    int r,c;
    Loc_13460(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object a){
        Loc_13460 b = (Loc_13460) a;
        return this.r == b.r && this.c == b.c;
    }
}