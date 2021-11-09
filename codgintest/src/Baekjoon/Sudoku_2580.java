package Baekjoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sudoku_2580 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int [][] map = new int[9][9];
        List<Loc> blanks = new ArrayList<>();
        for(int i = 0 ; i < 9; i++){
            for(int j = 0 ; j < 9 ; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 0)
                    blanks.add(new Loc(i,j));
            }
        }

        Solution sol = new Solution();
        sol.map = map;
        sol.blanks = blanks;
        sol.solve();
        return;

    }
    static class Solution{

        final static int full = Integer.parseInt("1111111110", 2);

        List<Loc> blanks;
        int [][] map;
        int [] verCheck = new int[9];
        int [] horCheck = new int[9];
        int [][] rectCheck = new int[3][3];

        void init(){
            for(int i = 0 ; i < 9; i++){
                for(int j = 0 ; j < 9 ; j++){
                    if(map[i][j] == 0) continue;
                    addNumber(i,j, map[i][j]);
                }
            }
        }
        void solve(){
            init();
            dfs(0);
        }
        boolean dfs(int now){
            if(now >= blanks.size()){
                for(int i = 0 ; i < 9; i++){
                    for(int j = 0 ; j < 8 ; j++){
                        System.out.print(map[i][j] + " ");
                    }
                    System.out.println(map[i][8]);
                }
                return true;
            }
            int r = blanks.get(now).r;
            int c = blanks.get(now).c;

            for(int i = 1; i <= 9; i++){
                if(!isPossible(r,c, i)) continue;
                map[r][c] = i;
                addNumber(r,c,i);
                boolean finish = dfs(now+1);
                if(finish) return true;
                map[r][c] = 0;
                removeNumber(r,c,i);
            }
            return false;
        }
        boolean isPossible(int r, int c, int num){
            if(contains(verCheck[r], num)) return false;
            if(contains(horCheck[c], num)) return false;
            if(contains(rectCheck[r/3][c/3], num)) return false;
            return true;
        }
        void addNumber(int r, int c, int num){
            verCheck[r] = add(verCheck[r], num);
            horCheck[c] = add(horCheck[c], num);
            rectCheck[r/3][c/3] = add(rectCheck[r/3][c/3], num);
        }
        void removeNumber(int r, int c, int num){
            verCheck[r] = remove(verCheck[r], num);
            horCheck[c] = remove(horCheck[c], num);
            rectCheck[r/3][c/3] = remove(rectCheck[r/3][c/3], num);
        }
        boolean contains(int set,int num){
            return (set & (1 << num)) > 0;
        }
        int add(int set, int num){
            return set | (1 << num);
        }
        int remove(int set, int num){
            return set ^ (1 << num);
        }
    }
    static class Loc{
        int r,c;
        Loc(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
