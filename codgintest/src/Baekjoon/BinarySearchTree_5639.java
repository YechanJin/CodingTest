package Baekjoon;

import java.util.*;

public class BinarySearchTree_5639 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> list = new ArrayList<>();
        while(sc.hasNext()){
            list.add(sc.nextInt());
        }

        Solution sol = new Solution();
        sol.solve(list);
        Deque<Integer> dq = sol.dq;
        while(!dq.isEmpty()){
            System.out.println(dq.pollLast());
        }
        return;
    }

    static class Solution{
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> input;

        void solve(List<Integer> list){
            input = list;
            dc(0, input.size()-1);
        }

        void dc(int start, int end){
            if(start > end) return;
            int num = input.get(start);
            dq.addLast(num);
            if(start == end)
                return;

            start++;
            int mid = start;
            while(mid < input.size() && input.get(mid) < num){
                mid++;
            }

            dc(mid, end);
            dc(start, mid-1);
        }

    }
}
