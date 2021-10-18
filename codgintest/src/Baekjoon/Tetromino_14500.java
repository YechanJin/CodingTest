package Baekjoon;

import java.util.Scanner;

public class Tetromino_14500 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Solution_14500 sol = new Solution_14500();

        sol.n = sc.nextInt();
        sol.m = sc.nextInt();

        int [][] board = new int[sol.n][sol.m];
        for(int i = 0 ; i < sol.n; i++){
            for(int j = 0 ; j < sol.m; j++){
                board[i][j] = sc.nextInt();
            }
        }
        sol.board = board;
        System.out.println(sol.solve());
        return;
    }

}
class Solution_14500 {
    int n,m;
    int [][] board;

    int solve(){
        int answer = 0;
        int temp = 0;
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m; j++){
                temp = getBlue(i,j);
                answer = Math.max(answer, temp);
                temp = getYellow(i,j);
                answer = Math.max(answer, temp);
                temp = getOrange(i,j);
                answer = Math.max(answer, temp);
                temp = getGreen(i,j);
                answer = Math.max(answer, temp);
                temp = getPink(i,j);
                answer = Math.max(answer, temp);
            }
        }

        return answer;
    }

    int getBlue(int r , int c){
        int [][] dr = {{0, 0, 0, 0}, {0,1,2,3}};
        int [][] dc = {{0,1,2,3} , {0,0,0,0}};
        int size = 2;
        return getMax(r, c, size, dr,dc);
    }

    int getYellow(int r, int c){
        int [][] dr = {{0,0,1,1}};
        int [][] dc = {{0,1,0,1}};
        int size =  1;
        return getMax(r, c, size, dr,dc);
    }
    int getOrange(int r, int c){
        int [][] dr = {{0,0,0,1} , {0,1,1,1} , {0,0,1,2} , {0,0,1,2} , {0,0,0,-1} , {0,0,0,1} , {0,1,2,2} , {0,1,2,2}};
        int [][] dc = {{0,1,2,0} , {0,0,1,2} , {0,1,1,1} , {0,1,0,0} , {0,1,2,2} , {0,1,2,2} , {0,0,0,1} , {0,0,0,-1}};
        int size = 8;
        return getMax(r, c, size, dr,dc);
    }

    int getGreen(int r, int c){
        int [][] dr = {{0,1,1,2} , {0,1,1,2} , {0,0,-1,-1} , {0,0,1,1}};
        int [][] dc = {{0,0,1,1} , {0,0,-1,-1} , {0,1,1,2} , {0,1,1,2}};
        int size = 4;
        return getMax(r, c, size, dr,dc);

    }
    int getPink(int r, int c){
        int [][] dr = {{0,0,0,1} , {0,0,0,-1}, {0,1,1,2}, {0,1,1,2}};
        int [][] dc = {{0,1,2,1} , {0,1,2,1}, {0,0,1,0}, {0,0,-1,0}};
        int size = 4;
        return getMax(r, c, size, dr,dc);
    }

    int getMax(int r, int c, int size, int[][] dr, int[][] dc){
        int max = 0;
        for(int i = 0 ; i < size; i++){
            int sum = 0;
            for(int j = 0 ; j < 4; j++){
                int nr = r + dr[i][j];
                int nc = c + dc[i][j];
                if(nr < 0 || nr >= this.n){
                    sum = 0;
                    break;
                }
                if(nc < 0 || nc >= this.m){
                    sum = 0;
                    break;
                }
                sum += this.board[nr][nc];
            }
            if(max < sum)
                max = sum;
        }
        return max;
    }
}
