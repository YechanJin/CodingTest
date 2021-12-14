package Baekjoon;

import java.util.*;

public class PrimeMultiple_2014 {

    public static final int MAX = (int)Math.pow(2, 31);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        List<Integer> primes= new ArrayList<>();

        for(int i = 0 ; i < k; i++){
            primes.add(sc.nextInt());
        }
        System.out.println(solve(k, n, primes));
        return;
    }

    public static int solve(int k, int n, List<Integer> primes){
        PriorityQueue<Integer> numbers = new PriorityQueue<>(primes);

        int answer = 0;
        for(int i = 1 ; !numbers.isEmpty() && i <= n; i++){
            int now = numbers.poll();
            answer = now;
            for(int j = 0 ; j < k; j++) {
                if(primes.get(j) *(long)now >= MAX) continue;
                numbers.add(primes.get(j) * now);
                if(now % primes.get(j) == 0)
                    break;
            }
        }

        return answer;
    }
}
