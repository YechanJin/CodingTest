package Baekjoon;

import java.io.*;
import java.util.*;

public class Running_2517 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int [] input = new int[n];
        for(int i = 0; i < n; i++)
            input[i] = Integer.parseInt(br.readLine());

        Solution sol = new Solution(n, input);
        sol.solve();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0 ; i < sol.n; i++)
            bw.write(sol.input[i] + "\n");
        bw.flush();
        bw.close();
        return;
    }
    static class Solution{
        int n;
        int [] input;
        int [] tree;

        public Solution(int n, int[] input) {
            this.n = n;
            this.input = input;
            this.tree = new int[4*n];
        }

        int getSum(int s, int e, int left, int right, int node){
            if(e < left || s > right) return 0;
            if(s >= left && e <= right)
                return tree[node];
            int mid = (s+e)/2;
            return getSum(s,mid, left,right, node*2) + getSum(mid+1, e, left, right, node*2+1);
        }

        void insertNum(int s, int e, int num, int node){
            tree[node]++;
            if(s == e) return;
            int mid = (s+e)/2;
            if(mid < num)
                insertNum(mid+1, e, num, node*2+1);
            else
                insertNum(s, mid, num, node*2);
        }

        void solve(){
            List<Runner> runners = new ArrayList<>();
            for(int i = 0 ; i < n; i++){
                runners.add(new Runner(i+1, input[i]));
            }
            runners.sort((x, y) -> x.ability < y.ability ? -1 : 1);
            for(int i = 0; i < n; i++){
                Runner runner = runners.get(i);
                runner.ability = i;
            }
            runners.sort((x, y) -> x.grade < y.grade ? -1 : 1);

            for(int i = 0 ; i < n; i++){
                Runner runner = runners.get(i);
                input[i] = i+1 - getSum(0, n-1, 0, runner.ability,1);
                insertNum(0, n-1, runner.ability, 1);
            }
        }

        static class Runner{
            int grade, ability;
            Runner(int grade, int ability){
                this.grade = grade;
                this.ability = ability;
            }
        }
    }

}
