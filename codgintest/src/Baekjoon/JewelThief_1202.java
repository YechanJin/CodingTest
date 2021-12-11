package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class JewelThief_1202 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int k = Integer.parseInt(inputs[1]);

        List<Jewel> jewels = new ArrayList<>();
        for(int i = 0 ; i < n; i++){
            inputs = br.readLine().split(" ");
            jewels.add(new Jewel(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])));
        }

        List<Integer> bags = new ArrayList<>();
        for(int i = 0; i < k; i++){
            bags.add(Integer.parseInt(br.readLine()));
        }

        System.out.println(solve(jewels, bags));
        return;
    }

    public static long solve(List<Jewel> jewels, List<Integer> bags){
        long answer = 0;
        jewels.sort((x, y) -> x.m <= y.m ? -1 : 1);
        PriorityQueue<Jewel> pq = new PriorityQueue<>((x, y) -> x.v <= y.v ? 1 : -1);
        bags.sort(Comparator.naturalOrder());

        int i = 0;
        for (Integer bag : bags) {
            while(i < jewels.size() && jewels.get(i).m <= bag){
                pq.add(jewels.get(i++));
            }
            if(pq.isEmpty())
                continue;
            answer += pq.poll().v;
        }
        return answer;
    }
    static class Jewel{
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}
