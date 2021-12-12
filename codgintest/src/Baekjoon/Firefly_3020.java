package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Firefly_3020 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int h = Integer.parseInt(input[1]);

        List<Integer> top = new ArrayList<>();
        List<Integer> bottom = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            if (i % 2 == 0)
                bottom.add(t);
            else
                top.add(t);
        }
        Solution sol = new Solution(n, h, top, bottom);
        sol.solve();
        System.out.println(sol.min + " " + sol.minNum);
        return;
    }

    static class Solution {

        final static int INF = 2123456789;
        int n, h;
        List<Integer> top;
        List<Integer> bottom;
        int min = INF;
        int minNum = 0;

        public Solution(int n, int h, List<Integer> top, List<Integer> bottom) {
            this.n = n;
            this.h = h;
            this.top = top;
            this.bottom = bottom;
        }

        public void solve() {
            top.sort(Comparator.naturalOrder());
            bottom.sort(Comparator.naturalOrder());
            for (int i = 0; i < h; i++) {
                int size = top.size() - binarySearch(top, h - i);
                size += bottom.size() - binarySearch(bottom, i + 1);
                if (size < min) {
                    min = size;
                    minNum = 1;
                } else if (size == min)
                    minNum++;
            }
        }

        public int binarySearch(List<Integer> list, int key) {
            int s = 0, e = list.size();
            while (s < e) {
                int mid = s + (e - s) / 2;
                if (key <= list.get(mid)) {
                    e = mid;
                } else
                    s = mid + 1;
            }
            return s;
        }
    }
}
