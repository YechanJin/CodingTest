package Baekjoon;

import java.io.*;
import java.util.StringTokenizer;

public class CoffeeShop2_1275 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String [] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int q = Integer.parseInt(inputs[1]);

        StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
        long [] numbers = new long[n];
        for(int i = 0 ; i < n; i++){
            numbers[i]  = Integer.parseInt(stk.nextToken());
        }

        Solution sol = new Solution(n, q, numbers);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0 ; i < q; i++){
            inputs = br.readLine().split(" ");
            int x = Integer.parseInt(inputs[0])-1;
            int y = Integer.parseInt(inputs[1])-1;
            int a = Integer.parseInt(inputs[2])-1;
            int b = Integer.parseInt(inputs[3]);
            bw.write(sol.turn(x,y,a,b) + "\n");
        }
        bw.flush();
        bw.close();
        return;
    }

    static class Solution{
        int n, q;
        long [] numbers;
        long [] seg;

        public Solution(int n, int q, long [] numbers) {
            this.n = n;
            this.q = q;
            this.numbers = numbers;
            this.seg = new long[n*4];
            init(0, n-1, 0);
        }

        long init(int s, int e, int node){
            if(s==e){
                return seg[node] = numbers[s];
            }
            int mid = (s+e)/2;
            return seg[node] = init(s,mid,node*2+1) + init(mid+1, e, node*2+2);
        }

        long getSum(int s, int e, int node, int from, int to){
            if(e < from || s > to) return 0;
            if(from <= s && to >= e) return seg[node];
            int mid = (s+e)/2;
            return getSum(s, mid, node*2+1, from, to) + getSum(mid+1, e,node*2+2, from, to);
        }

        void update(int s, int e, int node, int idx, long key){
            if(idx < s || idx > e) return;
            seg[node] += key - numbers[idx];
            if(s==e) return;

            int mid = (s+e)/2;
            if(idx <= mid)
                update(s, mid, node*2+1, idx, key);
            else
                update(mid+1, e, node*2+2, idx, key);
        }

        long turn(int x, int y, int a, long b){
            long answer = getSum(0, n-1, 0, Math.min(x,y), Math.max(x,y));
            update(0, n-1, 0, a, b);
            numbers[a] = b;
            return answer;
        }
    }
}
